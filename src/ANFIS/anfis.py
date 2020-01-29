import random
from rule import Rule
from functions import ConsequenceFunction, MemberShipFunction, Function


class ANFIS:

    def __init__(self, number_of_rules):
        self.number_of_rules = int(number_of_rules)
        self.rules = self.generate_rules()
        self.pred = 0

    def generate_rules(self):
        rules = []
        for i in range(0, self.number_of_rules):
            rules.append(Rule(MemberShipFunction(random.uniform(0, 1), random.uniform(0, 1)), MemberShipFunction(random.uniform(
                0, 1), random.uniform(0, 1)), ConsequenceFunction(random.uniform(0, 1), random.uniform(0, 1), random.uniform(0, 1))))

        return rules

    def normalize_value(self, point):
        value = 0
        for rule in self.rules:
            value += rule.t_norm(point)

        return value

    def get_rules(self):
        return self.rules

    def get_number_of_rules(self):
        return self.number_of_rules

    def get_rule(self, index):
        return self.rules[index]

    def get_pred(self):
        return self.pred

    def set_rule(self,index, rule):
        self.rules[index] = rule

    def update_pred(self, value):
        self.pred = value

    def value(self, point):
        value1 = 0
        for rule in self.rules:
            value1 += rule.value(point)
        result = value1/self.normalize_value(point)
        self.update_pred(result)
        return result

    def calc_error(self, function, variables):
        error = 0
        for variable in variables:
            error += pow(variable[2] - self.value((variable[0], variable[1])), 2)

        return error/2

