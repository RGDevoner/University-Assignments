import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader, random_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import precision_recall_fscore_support


# Dataset και DataLoader
class TextDataset(Dataset):

    # Τo dataset thelei:
    # ta texts: einai lista apo sequences  poy  deixnoyn tis leksis kathe keimenou.
    # ta labels: exoyn 0 h 1 analoga me tin leksi

    def __init__(self, texts, labels):
        self.texts = texts  # padded sequences
        self.labels = labels

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        return torch.tensor(self.texts[idx], dtype=torch.long), torch.tensor(self.labels[idx], dtype=torch.long)


class StackedBiRNN(nn.Module):
    def __init__(self, vocab_size, embed_dim, hidden_dim, num_layers, num_classes, dropout=0.5, use_gru=False):

        # vocab_size - Megethos liskilogiou
        # embed_dim - Diastasi twn word embeddings
        # hidden_dim - Megethos twn hideen states twn RNN
        # num_layers -lwthos stacked levels
        # num_classes: Plithos katigoriwn
        # dropout:stis metaksi twn epipedwn
        # use_gru:Ean isxyei, xrisimopoiei GRU , alliws to LSTM

        super(StackedBiRNN, self).__init__()
        self.embedding = nn.Embedding(vocab_size, embed_dim)
        # Edo fortonei to training embeddings
        if use_gru:
            self.rnn = nn.GRU(embed_dim, hidden_dim, num_layers=num_layers,
                              dropout=dropout if num_layers > 1 else 0,
                              bidirectional=True, batch_first=True)
        else:
            self.rnn = nn.LSTM(embed_dim, hidden_dim, num_layers=num_layers,
                               dropout=dropout if num_layers > 1 else 0,
                               bidirectional=True, batch_first=True)
        # Global Max Pooling dim=1
        self.pool = nn.AdaptiveMaxPool1d(1)
        self.fc = nn.Linear(hidden_dim * 2, num_classes)  # hidden_dim*2 λόγω bidirectional

    def forward(self, x):
        # x: (batch_size, seq_length)
        embedded = self.embedding(x)  # (batch_size, seq_length, embed_dim)
        rnn_out, _ = self.rnn(embedded)  # (batch_size, seq_length, hidden_dim*2)
        # Edo ginetai metatropi gia max pooling an apaitite allagi diastasewn
        # Transpose sto batch_size, hidden_dim*2, seq_length
        rnn_out = rnn_out.transpose(1, 2)
        pooled = self.pool(rnn_out)  # batch_size, hidden_dim*2, 1
        pooled = pooled.squeeze(2)  # batch_size, hidden_dim*2
        logits = self.fc(pooled)  # batch_size, num_classes
        return logits


# Edo ginetai to training toy monteloy kai i aksiologisi

def train_model(model, train_loader, dev_loader, num_epochs, criterion, optimizer, device):
    model.to(device)
    best_dev_loss = float('inf')
    history = {'train_loss': [], 'dev_loss': []}

    for epoch in range(1, num_epochs + 1):
        model.train()
        train_losses = []
        for texts, labels in train_loader:
            texts, labels = texts.to(device), labels.to(device)
            optimizer.zero_grad()
            outputs = model(texts)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()
            train_losses.append(loss.item())
        avg_train_loss = np.mean(train_losses)

        # Edo epilietai to development set
        model.eval()
        dev_losses = []
        with torch.no_grad():
            for texts, labels in dev_loader:
                texts, labels = texts.to(device), labels.to(device)
                outputs = model(texts)
                loss = criterion(outputs, labels)
                dev_losses.append(loss.item())
        avg_dev_loss = np.mean(dev_losses)

        history['train_loss'].append(avg_train_loss)
        history['dev_loss'].append(avg_dev_loss)

        print(f"Epoch {epoch}: Train Loss = {avg_train_loss:.4f}, Dev Loss = {avg_dev_loss:.4f}")

    return history


def evaluate_model(model, data_loader, device):
    model.eval()
    all_preds = []
    all_labels = []
    with torch.no_grad():
        for texts, labels in data_loader:
            texts = texts.to(device)
            outputs = model(texts)
            preds = torch.argmax(outputs, dim=1)
            all_preds.extend(preds.cpu().numpy())
            all_labels.extend(labels.numpy())
    return np.array(all_labels), np.array(all_preds)


# Edo ginontai metrikes aksiologisis

def compute_metrics(true_labels, predicted_labels):
    # Edo ginetai xrisi tis sinartisis precision_recall_fscore_support apo  scikit-learn
    precision, recall, f1, _ = precision_recall_fscore_support(true_labels, predicted_labels, average=None,
                                                               labels=[0, 1])
    # Edo ypologizetai i micro average
    micro_precision, micro_recall, micro_f1, _ = precision_recall_fscore_support(true_labels, predicted_labels,
                                                                                 average='micro')
    # Edo ypologizeta i macro average
    macro_precision, macro_recall, macro_f1, _ = precision_recall_fscore_support(true_labels, predicted_labels,
                                                                                 average='macro')

    metrics = {
        'precision0': precision[0],
        'recall0': recall[0],
        'f10': f1[0],
        'precision1': precision[1],
        'recall1': recall[1],
        'f11': f1[1],
        'microPrecision': micro_precision,
        'microRecall': micro_recall,
        'microF1': micro_f1,
        'macroPrecision': macro_precision,
        'macroRecall': macro_recall,
        'macroF1': macro_f1,
    }
    return metrics


# Edo ekteloume ena paradeigma ektelesis

if __name__ == '__main__':

    # Dummy δεδομένα για παράδειγμα (πρέπει να αντικατασταθούν με τα πραγματικά σας δεδομένα) #Edw exoume dummy_texts kai dummy_labels gia ta paradeigmata
    num_samples = 500
    seq_length = 100
    vocab_size = 10000
    np.random.seed(42)
    dummy_texts = np.random.randint(1, vocab_size, size=(num_samples, seq_length)).tolist()
    dummy_labels = np.random.randint(0, 2, size=(num_samples,)).tolist()

    # Edo dimioyrgioume to dummy dataset.
    dataset = TextDataset(dummy_texts, dummy_labels)

    # diaxwrismos se training 80% kai development 20%
    train_size = int(0.8 * len(dataset))
    dev_size = len(dataset) - train_size
    train_dataset, dev_dataset = random_split(dataset, [train_size, dev_size])

    # Edw dimiourgite dataloader.
    batch_size = 32
    train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True)
    dev_loader = DataLoader(dev_dataset, batch_size=batch_size)

    # Edo ginetai o orismos twn uperparametrwn gia to montelo mas
    embed_dim = 300
    hidden_dim = 128
    num_layers = 2  # to plitos twn stoibaggmenwn epipedwn
    num_classes = 2
    dropout = 0.5
    use_gru = False  # edw xrisimopoioume to  LSTM afou einai false

    # edw ginetai i dimioyrgia tou montelou
    model = StackedBiRNN(vocab_size=vocab_size, embed_dim=embed_dim, hidden_dim=hidden_dim,
                         num_layers=num_layers, num_classes=num_classes, dropout=dropout, use_gru=use_gru)

    # edw orizetai to optimizer kai to loss edw orizoyme to optimizer kai to loss
    optimizer = optim.Adam(model.parameters(), lr=0.001)
    criterion = nn.CrossEntropyLoss()

    # edw energopoioume tin GPU an iparxei
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    # Edo kanoyme train to montelo mas
    num_epochs = 10
    history = train_model(model, train_loader, dev_loader, num_epochs, criterion, optimizer, device)

    #  Sxediazoume tin kampili toy loss
    epochs = range(1, num_epochs + 1)
    plt.plot(epochs, history['train_loss'], label='Train Loss')
    plt.plot(epochs, history['dev_loss'], label='Dev Loss')
    plt.xlabel('Epochs')
    plt.ylabel('Loss')
    plt.legend()
    plt.title('Learning Curves (Loss)')
    plt.show()

    # Edw aksiologoyme to development set.
    true_dev, preds_dev = evaluate_model(model, dev_loader, device)
    dev_metrics = compute_metrics(true_dev, preds_dev)
    print("Development Metrics:")
    for key, value in dev_metrics.items():
        print(f"{key}: {value:.3f}")