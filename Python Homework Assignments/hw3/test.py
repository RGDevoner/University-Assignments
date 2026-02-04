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
    t=len(ls)
    u='True,'*t
    print(u)
    z=u.split(',')
    z.remove('')
    print(z)
   
    p=['True' for i in ls if func(ls[i])== True]  
    print(p)
    if len(p)==len(z):
        return True
    else:
        return False

   
    