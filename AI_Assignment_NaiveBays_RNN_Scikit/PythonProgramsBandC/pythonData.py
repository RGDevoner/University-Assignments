import os
import re
import math
import numpy as np
from collections import Counter
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report
import matplotlib.pyplot as plt
from sklearn.model_selection import learning_curve

def log2(x):
    return math.log(x, 2) if x > 0 else 0

# Edo einai i sinartisi poy apeikonizei kampiles mathisis
def plot_learning_curve(estimator, title, X, y, cv=5, n_jobs=1, train_sizes=np.linspace(0.1, 1.0, 5)):
    plt.figure()
    plt.title(title)
    plt.xlabel("Training examples:")
    plt.ylabel("Score:")
    train_sizes, train_scores, test_scores = learning_curve(estimator, X, y, cv=cv, n_jobs=n_jobs, train_sizes=train_sizes)
    train_scores_mean = np.mean(train_scores, axis=1)
    train_scores_std = np.std(train_scores, axis=1)
    test_scores_mean = np.mean(test_scores, axis=1)
    test_scores_std = np.std(test_scores, axis=1)
    plt.grid()
    plt.fill_between(train_sizes, train_scores_mean - train_scores_std, train_scores_mean + train_scores_std, alpha=0.1, color="r")
    plt.fill_between(train_sizes, test_scores_mean - test_scores_std, test_scores_mean + test_scores_std, alpha=0.1, color="g")
    plt.plot(train_sizes, train_scores_mean, 'o-', color="r", label="Training Points")
    plt.plot(train_sizes, test_scores_mean, 'o-', color="g", label="Points me cross-validation")
    plt.legend(loc="best")
    return plt

# Edo ginete i fortosi dedwmenwn

def load_imdb_dataset(DATA):
    
    #Gia na leitoyrgisei thelei tin domi typou
    #DATA/aclImdb/train/pos, train/neg, test/pos, test/neg
    #Se kathe ena fakelo mas, kathe ariexei kai ena review.
    
    train_texts, train_labels = [], []
    test_texts, test_labels = [], []

    for dataset in ['train', 'test']:
        for sentiment, label in [('pos', 1), ('neg', 0)]:
            folder = os.path.join(DATA, 'aclImdb', dataset, sentiment)
            for filename in os.listdir(folder):
                file_path = os.path.join(folder, filename)
                if os.path.isfile(file_path):
                    with open(file_path, 'r', encoding='utf-8') as f:
                        text = f.read()
                    if dataset == 'train':
                        train_texts.append(text)
                        train_labels.append(label)
                    else:
                        test_texts.append(text)
                        test_labels.append(label)
    return train_texts, np.array(train_labels), test_texts, np.array(test_labels)

# Edo ksekinaei i dimioyrgia tou leksilogiou mas 
def build_vocabulary(texts, labels, n, k, m):
    
      #To leksilogio mas dimioyrgeite me gnomona to poses fores iparxei i leksi kai to kerdos pliroforias.
      # Afoy aporripsei tis n kai k lekseis epilogei tis m leksis me to megalitero kerdos pliroforias
      
    
    total_docs = len(texts)
    doc_freq = {}
    doc_freq_by_class = {}
    count_class0 = 0
    count_class1 = 0

    for text, label in zip(texts, labels):
        text = text.lower()
        if label == 0:
            count_class0 += 1
        else:
            count_class1 += 1
        words = set(re.split(r'\W+', text))
        for word in words:
            if word == '':
                continue
            doc_freq[word] = doc_freq.get(word, 0) + 1
            if word not in doc_freq_by_class:
                doc_freq_by_class[word] = [0, 0]
            doc_freq_by_class[word][label] += 1

    p0 = count_class0 / total_docs
    p1 = count_class1 / total_docs
    entropy = 0
    if p0 > 0:
        entropy -= p0 * log2(p0)
    if p1 > 0:
        entropy -= p1 * log2(p1)

    # Edo ginetai i aporripsi twn n kai bottom k leksewn vasi tin sixnotita twn documents
    words_sorted_by_freq = sorted(doc_freq.keys(), key=lambda w: doc_freq[w], reverse=True)
    candidate_words = set(doc_freq.keys())
    if 0 < n < len(words_sorted_by_freq):
        top_n = set(words_sorted_by_freq[:n])
        candidate_words -= top_n
    words_sorted_by_freq_asc = sorted(doc_freq.keys(), key=lambda w: doc_freq[w])
    if 0 < k < len(words_sorted_by_freq_asc):
        bottom_k = set(words_sorted_by_freq_asc[:k])
        candidate_words -= bottom_k

    # Edo ipologizetai to kerdos pliroforias gia kathe leksi.
    info_gain_map = {}
    for word in candidate_words:
        df = doc_freq[word]
        pw = df / total_docs
        pNotW = 1 - pw
        df0 = doc_freq_by_class[word][0]
        df1 = doc_freq_by_class[word][1]

        p0_given_w = df0 / df if df > 0 else 0
        p1_given_w = df1 / df if df > 0 else 0
        entropy_w = 0
        if p0_given_w > 0:
            entropy_w -= p0_given_w * log2(p0_given_w)
        if p1_given_w > 0:
            entropy_w -= p1_given_w * log2(p1_given_w)

        not_df = total_docs - df
        not0 = count_class0 - df0
        not1 = count_class1 - df1
        p0_given_not_w = not0 / not_df if not_df > 0 else 0
        p1_given_not_w = not1 / not_df if not_df > 0 else 0
        entropy_not_w = 0
        if p0_given_not_w > 0:
            entropy_not_w -= p0_given_not_w * log2(p0_given_not_w)
        if p1_given_not_w > 0:
            entropy_not_w -= p1_given_not_w * log2(p1_given_not_w)

        conditional_entropy = pw * entropy_w + pNotW * entropy_not_w
        ig = entropy - conditional_entropy
        info_gain_map[word] = ig

    sorted_by_ig = sorted(info_gain_map.keys(), key=lambda w: info_gain_map[w], reverse=True)
    selected_words = sorted_by_ig[:m]
    # Edo gineai epistrofi tou leksilogiou os leksi,index (0/1)
    vocabulary = {word: idx for idx, word in enumerate(selected_words)}
    return vocabulary


def main():
    # Parametroi gia leksilogio mas
    n = 75      # n pio sixnes leksis poy aporriptoume
    k = 1250    # k pio spaniesleksis poy aporripotoume 
    m = 800     # Teliko megethos leksilogiou

    #Aftoi einai oi yperparametroi toy algorithoy
    nb_alpha = 0.1
    lr_reg_lambda = 0.01  #I Logistic Regression poy einai C = 1/λ
    lr_epochs = 1000      # edo einai to max_iter
    rf_n_estimators = 1000
    rf_max_depth = 10
    rf_min_samples_split = 5
    rf_max_features = int(np.sqrt(m))

    DATA = 'data'  # Edw pernei to path gia ta data mas
    print("Fortwsi dedwmenwn...")
    train_texts, train_labels, test_texts, test_labels = load_imdb_dataset(DATA)

    print("Dimiourgia leksilogiou...")
    vocabulary = build_vocabulary(train_texts, train_labels, n, k, m)

    # Edo xrisimopoioyme countvectorizer me to custom mas leksilogio gia ta binary characteristics
    vectorizer = CountVectorizer(vocabulary=vocabulary, binary=True)
    X_train = vectorizer.transform(train_texts)
    X_test = vectorizer.transform(test_texts)

    # Naïve Bayes
    print("Training Naïve Bayes...")
    nb_model = MultinomialNB(alpha=nb_alpha)
    nb_model.fit(X_train, train_labels)
    pred_nb = nb_model.predict(X_test)
    print("Naïve Bayes Accuracy :", accuracy_score(test_labels, pred_nb))
    print("Classification Report for Naïve Bayes :\n", classification_report(test_labels, pred_nb))

    # Logistic Regression 
    print("Training Logistic Regression...")
    lr_model = LogisticRegression(max_iter=lr_epochs, C=1 / lr_reg_lambda, solver='lbfgs')
    lr_model.fit(X_train, train_labels)
    pred_lr = lr_model.predict(X_test)
    print("Logistic Regression Accuracy:", accuracy_score(test_labels, pred_lr))
    print("Classification Report for Logistic Regression:\n", classification_report(test_labels, pred_lr))

    # Random Forest
    print("Training Random Forest...")
    rf_model = RandomForestClassifier(n_estimators=rf_n_estimators, max_depth=rf_max_depth,
                                      min_samples_split=rf_min_samples_split,
                                      max_features=rf_max_features, random_state=42)
    rf_model.fit(X_train, train_labels)
    pred_rf = rf_model.predict(X_test)
    print("Random Forest Accuracy :", accuracy_score(test_labels, pred_rf))
    print("Classification Report for Random Forest:\n", classification_report(test_labels, pred_rf))

    # learning Curve
    print("Calculating and creating Learning Curve...")

    # Καμπύλη μάθησης για Naïve Bayes
    plt_nb = plot_learning_curve(MultinomialNB(alpha=nb_alpha),
                                 "Learning Curve of - Naïve Bayes",
                                 X_train, train_labels, cv=5, n_jobs=-1)
    plt_nb.show()

    # Καμπύλη μάθησης για Logistic Regression
    plt_lr = plot_learning_curve(LogisticRegression(max_iter=lr_epochs, C=1 / lr_reg_lambda, solver='lbfgs'),
                                 "Learning Curve of - Logistic Regression",
                                 X_train, train_labels, cv=5, n_jobs=-1)
    plt_lr.show()

    # Καμπύλη μάθησης για Random Forest
    plt_rf = plot_learning_curve(RandomForestClassifier(n_estimators=rf_n_estimators, max_depth=rf_max_depth,
                                                          min_samples_split=rf_min_samples_split,
                                                          max_features=rf_max_features, random_state=42),
                                 "Learning Curve of - Random Forest",
                                 X_train, train_labels, cv=5, n_jobs=-1)
    plt_rf.show()

if __name__ == '__main__':
    main()
