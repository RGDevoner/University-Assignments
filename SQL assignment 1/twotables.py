import csv
import ast

try:
    with open("keywords.csv", encoding="utf-8") as file:
        
        haskey = open("haskeyword.csv", 'w', newline='', encoding='utf-8')
        keyword = open("Keyword.csv", 'w', newline='', encoding='utf-8')
        haskey_writer = csv.writer(haskey)
        keyword_writer = csv.writer(keyword)
        
        haskey_writer.writerow(['movie_id', 'keyword_id'])
        keyword_writer.writerow(['id', 'name'])
        
        csvreader = csv.reader(file)
        header = next(csvreader)
        
        
        for row in csvreader:
            fp = row[0] #first part (id)
            sp = row[1] #second part (name,movie id)
            data = ast.literal_eval(sp) 
            
            
            for item in data:
                keyword_writer.writerow([item['id'], item['name']]) #id,name
                
                haskey_writer.writerow([fp, item['id']]) #movie_id,keyword_id

except FileNotFoundError:  #exceptions
    print("File not found.")
except UnicodeDecodeError:
    print("Error decoding the file. Check the encoding.")