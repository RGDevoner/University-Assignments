def all_iter(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_iter(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_iter(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_iter(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPANALHPTIKO YPOLOGISMO (for 'h while).
    MHN XRHSIMOPOIHSETE LIST COMPREHENSIONS 'h ANADROMI."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    s=len(ls)
    c=0
    k=0
    for y in ls[:]:
        
        if func(ls[y-1])==True:
            c=c+1
    
    if c==s:
        return True
    else:
        return False