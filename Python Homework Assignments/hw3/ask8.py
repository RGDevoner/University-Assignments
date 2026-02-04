def all_rec(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_rec(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_rec(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_rec(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE ANADROMH.
    MHN XRHSIMOPOIHSETE LIST COMPREHENSIONS 'h EPANALHPTIKO YPOLOGISMO 
    (for 'h while)."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    def func1(func,ls):
        if len(ls)==1:
         return func(ls[0])
        else:
            return len(ls)*func1(func(ls[len(ls-1)]))
        
    return func1(func,ls)
   