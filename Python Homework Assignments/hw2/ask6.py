def askisi6():
    """Ypologismoi gewmetrikwn sximatwn.
    >>> askisi6()
    Perifereies kyklwn me aktina 1 ews 10
    6.283185307179586
    12.566370614359172
    18.84955592153876
    25.132741228718345
    31.41592653589793
    37.69911184307752
    43.982297150257104
    50.26548245743669
    56.548667764616276
    62.83185307179586
    Emvada tetragwnwn me pleura 1 ews 8
    1
    4
    9
    16
    25
    36
    49
    64
    Emvado kyklou aktinas 1 = 3.141592653589793
    Emvado kyklou aktinas 2 = 12.566370614359172
    Emvado kyklou aktinas 3 = 28.274333882308138
    Emvado kyklou aktinas 4 = 50.26548245743669
    Emvado kyklou aktinas 5 = 78.53981633974483
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW, APOFEYGONTAS
    TIN EPANALIPSI PAROMOIWN ENTOLWN """
    
    print("Perifereies kyklwn me aktina 1 ews 10")
    pi=3.141592653589793
    r=1
    exit=False
    exit1=1
    while exit==False:
        base_com=pi*r
        if exit1==1:
            print(base_com*2)
            r=r+1
        if r==11:
            exit1=2
            r=1
            print("Emvada tetragwnwn me pleura 1 ews 8")
        if exit1==2:
            print(r*r)
            r=r+1
            if r==9:
                exit1=3
                r=1
        if exit1==3:
            print('Emvado kyklou aktinas '+str(r)+' = '+str(pi*r*r))
            r=r+1
            if r==6:
                exit=True
    return
            