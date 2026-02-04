import turtle
a=4
t = turtle.Turtle()

t.pensize(2)
t.speed()
for z in range(0,a*a):
    for h in range(0, a):
        for z in range(0, a):
            for i in range(4):
                t.forward(90)
                t.left(90)
            t.forward(90)
    t.left(90)
    t.forward(90)
    t.left(90)
    t.forward(90)
    t.right(90)
    t.left(90)
    for k in range(0,a+1):
        t.forward(90)
    if z!=(a*a-1):
        t.left(90)
        t.forward(90)
        t.forward(90)
        t.left(90)

