class Rule:

    def __init__(self, a, b, z):
        self.first_antecedent = a
        self.second_antecedent = b
        self.consequense = z

    def get_first_antecedent(self):
        return self.first_antecedent

    def get_second_antecedent(self):
        return self.second_antecedent

    def get_consequense(self):
        return self.consequense

    def set_first_antecedent(self, first_antecedent):
        self.first_antecedent = first_antecedent

    def set_second_antecedent(self, second_antecedent):
        self.second_antecedent = second_antecedent

    def set_consequense(self, consequense):
        self.consequense = consequense
