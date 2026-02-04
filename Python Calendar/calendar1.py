import calendar
evn=[] #πινακας που θα χρησιμοποιηθεί για τα γεγονότα
date=[] #πινακς που θα χρησιμοποιηθεί για τις ημερομηνιες των γεγονοτων 
Events=[]
nevn=[]
newevents=open('events.txt','r')    #λογο του ότι το αρχείο events.csv μου παρουσίζε προβλήματα προτίμησα στην εισαγωγή των δεδομενων να την κάνω απο το notepad (.txt)
i=0
for line in newevents:
    line=line.strip()
    f=line.split(',')   #βγαζει τα κομματα μεταξυ των δεδομενων
    if i>0:
        evn.append(f) #βαζει τα δεδομενα στον πινακα
    i=i+1
    

for n in range(0,i-1):
    k=evn[n][0].split('-')
    date.append(str(k)) #τοποθετεί την ημερα,μηνα και χρονο του γεγονοτος σε ξεχωριστό πίνακα
    
newevents.close()
dpinakas=n+1 #χωρος του πινακα

yy = 2022 #τωρινος χρονος
mm = 12 #τωρινος μηνας 

import datetime
import locale
locale.setlocale(category=locale.LC_ALL,locale="Greek")  #ετσι ωστε να γυρισω τον μηνα στα Ελληνικά

# display the calendar

date=[]
mr=calendar.monthrange(yy, mm)
s=[]
s=(calendar.monthcalendar(yy, mm))


choice='p'  #μεταβλητή που θα χρησιμοποιείτε για την επιλογή των ενεργιών στο ημερολόγιο
day=['  ΔΕΥ |','  ΤΡΙ |','  ΤΕΤ |','  ΠΕΜ |','  ΠΑΡ |','  ΣΑΒ |','  ΚΥΡ']
while choice!='q': #επαναληπτική διαδικασία που με το q τερματίζει το πρόγραμμα
      
    s=(calendar.monthcalendar(yy, mm))
    
    r=calendar.monthrange(yy, mm)
   
    line='________________________________________________________'
    print(line)
    print(line.replace('_',' '))

    print(calendar.month_abbr[mm].upper(),'   ',yy)
    print(line.replace('_',' '))
    print(line)
    print(line.replace('_',' '))
    print(day[0],day[1],day[2],day[3],day[4],day[5],day[6])
    hlp2=0
    hlp1=0
    for i in range(0,6):
        if s[0][i]==0: #βρισκει που εχει μηδενικα στο πινακα (στην πρωτη γραμμη του ημερολογιου)
            hlp1=hlp1+1
        if s[len(s)-1][i]==0:#βρίσκει που εχει μηδενικά στο πίνκα(στην τελευταία γραμμη του ημερολογίου)
            hlp2=hlp2+1
    if mm!=12:
        mmb=mm+1
        mr=calendar.monthrange(yy, mmb) 
        q=mr[1] 
    else:
        mmb=1
        yy=yy+1
        mr=calendar.monthrange(yy, mmb) 
        q=mr[1]
        yy=yy-1 
    hlp1=hlp1-1
    while hlp1!=-1:#αντικαθηστεί τα μηδενικα στην πρωτη σειρα με τις ημερες του προηγουμενου μηνα 
        k=r[0]-hlp1
        
        s[0][hlp1]=q
        
        hlp1=hlp1-1
        q=q-1
    hlp2=hlp2
    w=1
    hlp=hlp2
    for i in range(6-hlp2,7):#αντικαθηστεί τα μηνδενικά στην τελευταία σειρα με τις ημερες του επομενου μηνα
        s[len(s)-1][i]=w
        w=w+1
     

    for i in range(0,len(s)):
        hlp=0
    
        for y in range(0,7):
            ev='no'
            for z in range(0,dpinakas):
                t=str(yy)+'-'+str(mm)+'-'+str(s[i][y])
                if t==evn[z][0]:
                    ev='yes'#yes αν υπαρχει γεγονος την ημερα αυτην 
            if s[i][y]<10 and i==len(s)-1 and ev=='no':#για τις ημερες του μηνα στο τελος του ημερολογιου που ανηκουν στον επομενο μηνα 
                s[i][y]='   '+str(s[i][y])+' '   
            elif s[i][y]<10 and ev=='no':   #γενικα για τις μερες του ημερολογιου που ειναι πριν τις 10 του μηνους και δεν εχουν καποιο γεγονος
                s[i][y]='[  '+str(s[i][y])+']'
            elif s[i][y]>10 and i==0 and ev=='no':#το ιδιο με το πανω απλα για τις μερες που ειναι απο τις 10 και μετα 
                s[i][y]='  '+str(s[i][y])+' ' 
            elif s[i][y]<10 and ev=='yes'  :#για τις μερες που εχουν γεγονος και ειναι κατω του 10
                 s[i][y]='[ *'+str(s[i][y])+']'
            elif s[i][y]>10 and ev=='yes': #το ιδιο για το πανω απλα για πανω του 10
                if i==0:#για την πρωτη γραμμη 
                    if hlp>z:#αν ανικει σε αυτον τον μηνα η μερα τοτε περνει *
                        s[i][y]='[*'+str(s[i][y])+']'
                    else:#αλλιως δεν περνει 
                        s[i][y]='[ '+str(s[i][y])+']'
                else:#για οποιαδηποτε αλλη βδομαδα
                   s[i][y]='[*'+str(s[i][y])+']' 
            else:
                s[i][y]='[ '+str(s[i][y])+']'
            
        print(str(s[i][0])+' |  '+str(s[i][1])+'|  '+str(s[i][2])+'|  '+str(s[i][3])+'|  '+str(s[i][4])+'| '+str(s[i][5])+' |  '+str(s[i][6]))#εκπτυπωση ημερολογιου 
    print(line.replace('_',' '))                 
    print(line)                
    
    print('Πατήστε ENTER για προβολή του επόμενου μήνα, "q" για έξοδο ή κάποια από τις')
    print('παρακάτω επιλογές:')
    print('"-" για πλοήγηση στον προηγούμενο μήνα')
    print('"+" για διαχείριση των γεγονότων του ημερολογίου')
    print('"*" για εμφάνιση των γεγονότων ενός επιλεγμένου μήνα')
    choice=input('->')
    if choice=='':
        if mm!=12:
            mm=mm+1
        else:
            mm=1
            yy=yy+1
    elif choice=='-':
        if mm!=1:
            mm=mm-1
        else:
            mm=12
            yy=yy-1
    elif choice=='+':
        print('Διαχείριση γεγονότων ημερολογίου, επιλέξτε ενέργεια:')
        print('1 Καταγραφή νέου γεγονότος')
        print('2 Διαγραφή γεγονότος')
        print('3 Ενημέρωση γεγονότος')
        print('0 Επιστροφή στο κυρίως μενού')
        ch=input('->')
        
        if ch=='1':#καταγραφη νεου γεγονότος με ελεγχό εγκυρότητας
            dpinakas=dpinakas+1
            print('Καταχωρίστε ημερομηνία')
            nyear=int(input('Eισάγετε Έτος:'))
            nyear=int(nyear)
            if int(nyear)<2022:
                while nyear<2022:
                    nyear=int(input('Μη έγκυρο έτος,προσπαθήστε πάλι:'))
            nmonth=int(input('Εισάγετε Μήνας:'))
            nmonth=int(nmonth)
            if nmonth<0 or nmonth>12:
                while nmonth<0 or nmonth>12:
                    nmonth=int(input('Μη έγκυρος μήνας,προσπαθήστε πάλι:'))
            nday=int(input('Εισάγετε Μέρα:'))
            nday=int(nday)
            k=calendar.monthrange(yy, mm)
            if nday<0 or nday>int(k[1]):
                while nday<0 or nday>k:
                    nday=int(input('Μη έγκυρη μέρα,προσπαθήστε πάλι:'))
                    
            nevn.append(str(nyear)+'-'+str(nmonth)+'-'+str(nday))
            nhour=int(input('Εισάγετε Ώρα:'))
            nhour=int(nhour)
            if nhour<0 or nhour>23:
                while nhour<0 or nhour>23:
                    nhour=int(input('Μη έγκυρη ώρα,προσπαθήστε πάλι:'))
            nminute=int(input('Εισάγετε λεπτά:'))
            nminute=int(nminute)
            if nminute<0 or nminute>59:
                while nminute<0 or nminute>59:
                    nminute=int(input('Μη έγκυρα λεπτά,προσπαθήστε πάλι:'))
            nevn.append(str(nhour)+':'+str(nminute))
            nduration=int(input('Εισάγετε Διάρκεια:'))
            
            if nduration<0:
                nduration=int(input('Μη έγκυρη διάρκεια,προσπαθήστε πάλι:'))
            nevn.append(str(nduration))
            nevent=input('Εισάγετε γεγονός:')
            if nevent.find(',')=='-1':
                nevent=input('Μη έγκυρο γεγονός,προσπαθήστε πάλι:')
            nevn.append(nevent)
            evn.append(nevn)
            nevn=[]#αφου χρησιμοποιησα τον πινακα τον ανακυκλώνω αδιάζοντας τον 
            nevent=[]#αφου χρησιμοποιησα τον πινακα τον ανακυκλώνω αδιάζοντας τον 
            
            nevn=[]
        if ch=='2':#Διαγραφή γεγονότος με ελεγχο εγκυρότητας
            print('=== Αναζήτηση γεγονότων ====')
            dyear=int(input('Εισάγετε έτος:'))
            if dyear<2022:
                dyear=int(input('Μη έγκυρο έτος,προσπαθήστε ξανά'))
            dmonth=int(input('Εισαγετε μήνα:'))
            if dmonth<0 or dmonth>12:
                dmonth=int(input('Μη έγκυρος μήνας,προσπαθήστε ξανά'))
            for n in range(0,dpinakas):
                
                date=evn[n][0].split('-')
                if int(date[0])==dyear and int(date[1])==dmonth:
                    nevn.append(int(n)) 
                
               
            for q in range(0,len(nevn)):#εκτυπώνει τα γεγονοτα
                print(str(q)+'.  '+'['+str(evn[nevn[q]][3])+']'+'  -> '+'Date: '+str(evn[nevn[q]][0])+', '+'Time: '+str(evn[nevn[q]][1])+', '+'Duration: '+str(evn[nevn[q]][2]))
                
            q=int(input("Επιλέξτε γεγονός προς διαγραφή:"))
            
            evn.remove(evn[nevn[q]])
            dpinakas=dpinakas-1
          
            nevn=[]
                
        if ch=='3':#Ενημέρωση γεγονότος με ελεγχο εγκυρότητας
                print('=== Αναζήτηση γεγονότων ====')
                dyear=int(input('Εισάγετε έτος:'))
                if dyear<2022:
                    dyear=int(input('Μη έγκυρο έτος,προσπαθήστε ξανά'))
                dmonth=int(input('Εισαγετε μήνα:'))
                if dmonth<0 or dmonth>12:
                    dmonth=int(input('Μη έγκυρος μήνας,προσπαθήστε ξανά'))
                for n in range(0,dpinakas):
                    
                    date=evn[n][0].split('-')
                    if int(date[0])==dyear and int(date[1])==dmonth:
                        nevn.append(int(n)) 
                    
                   
                for q in range(0,len(nevn)):#εκτυπώνει τα γεγονοτα(εδω το q για καποιο λογο στο str(q) δεν μου αυξανόταν)
                    print(str(q)+'.  '+'['+str(evn[nevn[q]][3])+']'+'  -> '+'Date: '+str(evn[nevn[q]][0])+', '+'Time: '+str(evn[nevn[q]][1])+', '+'Duration: '+str(evn[nevn[q]][2]))
                    
                
                
                q=int(input('Επιλέξτε γεγονός προς ενημέρωση:'))
                cdate=input('Ημερομηνία γεγονός  ('+str(evn[nevn[q]][0])+'):')
                if cdate!='':
                    evn[nevn[q]][0]=cdate
                chour=input('Ώρα γεγονότος'+' ('+str(evn[nevn[q]][1])+'):')
                if chour!='':
                    evn[nevn[q]][1]=chour
                cdur=input('Διάρκεια γεγονότος'+' ('+str(evn[nevn[q]][2])+'):')
                if cdur!='':
                    evn[nevn[q]][2]=cdur
                cev=input('Τίτλος γεγονότος'+' ('+str(evn[nevn[q]][3])+'):')
                if cev!='':
                    evn[nevn[q]][3]=cev
                    
                print('Το γεγονός ενημερώθηκε:'+' <['+str(evn[nevn[q]][3])+']'+' -> '+'Date: '+str(evn[nevn[q]][0])+', '+'Time: '+str(evn[nevn[q]][1])+', Duration: '+str(evn[nevn[q]][2]),'>')
                    
                nevn=[] 
    if choice=='*':#προβολη γεγονότων σε εναν συγκεκριμένο μήνα 
         for n in range(0,dpinakas):
            date=evn[n][0].split('-')
            if int(date[0])==yy and int(date[1])==mm:
                nevn.append(int(n)) 
                   
            for q in range(0,len(nevn)):
                print(str(q)+' .  '+'['+str(evn[nevn[q]][3])+']'+'  -> '+'Date: '+str(evn[nevn[q]][0])+', '+'Time: '+str(evn[nevn[q]][1])+', '+'Duration: '+str(evn[nevn[q]][2]))
                
                
                nevn=[]
                    
updatefile=[]#πινακας που θα χρησιμοποιηθεί για την μεταφορθωση των ανανεωμένων γεγονότων 
newevents.close()
for i in range(0,dpinakas):#τα γεγονοτα γινονται ενα string που μπαινουν στον updatefile
    updatefile.append(str(evn[i][0])+','+str(evn[i][1])+','+str(evn[i][2])+','+str(evn[i][3]))
    

newevents=open('events.txt','w')#εισαγωγη των νεων γεγονότων στον φάκελο
newevents.write(str('Date,Hour,Duration,Title')+'\n')
for i in range(0,dpinakas):
  newevents.write(str(updatefile[i])+'\n')
  
   

 





