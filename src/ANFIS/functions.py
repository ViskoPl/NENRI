import math


class Function:

    def value(self, point):
        return (pow((point[0] - 1), 2) + pow((point[1] + 2), 2) - 5 * point[0] * point[1] + 3) * pow(math.cos(point[0]/5), 2)


class MemmberShipFunction:

    def __init__(self, a, b):
        self.a = a
        self.b = b

    def value(self, x):
        return 1.0/(1+math.exp(self.b*(x - self.a)))

    def get_a(self):
        return self.a

    def get_b(self):
        return self.b

    def set_a(self, a):
        self.a = a

    def set_b(self, b):
        self.b = b


class ConsequenceFunction:

    def __init__(self, p, q, r):
        self.p = p
        self.q = q
        self.r = r

    def value(self, point):
        return self.p*point[0] + self.q*point[1] + self.r

    def get_p(self):
        return self.p

    def get_q(self):
        return self.q

    def get_r(self):
        return self.r

    def set_p(self, p):
        self.p = p

    def set_q(self, q):
        self.q = q

    def set_r(self, r):
        self.r = r
