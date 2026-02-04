def all_lc(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_lc(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_lc(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_lc(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPE3ERGASIA AKOLOU8IWN ME LIST COMPREHENSIONS.
    MHN XRHSIMOPOIHSETE ANADROMH 'h EPANALHPTIKO YPOLOGISMO 
    (for 'h while)."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    p=[]
    p=[func(ls) for func in ls if "True" in ls]  
    if p==[]:
        return True
    else:
        return False


#--------------------- Askisi 11 -----------------------

def all_hof(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_hof(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_hof(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_hof(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPE3ERGASIA AKOLOU8IWN ME SYNARTHSEIS ANWTEROY EPIPEDOY
    (map, filter, reduce).
    MHN XRHSIMOPOIHSETE ANADROMH, EPANALHPTIKO YPOLOGISMO (for 'h while)
    'h LIST COMPREHENSIONS."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    ls1=ls
    ls=list(filter(func,ls))
    if ls1==ls:
        return True
    else:
        return False