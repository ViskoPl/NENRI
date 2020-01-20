class Rule:

    def __init__(self, a, b, z):
        self.first_antecedent = a
        self.second_antecedent = b
        self.consequence = z

    def get_first_antecedent(self):
        return self.first_antecedent

    def get_second_antecedent(self):
        return self.second_antecedent

    def get_consequence(self):
        return self.consequence

    def set_first_antecedent(self, first_antecedent):
        self.first_antecedent = first_antecedent

    def set_second_antecedent(self, second_antecedent):
        self.second_antecedent = second_antecedent

    def set_consequence(self, consequence):
        self.consequence = consequence

    def t_norm(self, point):
        return self.first_antecedent.value(point[0]) * self.second_antecedent.value(point[1])

    def value(self, point):
        return self.first_antecedent.value(point[0]) * self.second_antecedent.value(point[1]) * self.consequence.value(point)
