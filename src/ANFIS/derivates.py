from functions import Function


def a_derivative_x(point, system, function_value, rule, index):
    sum1 = 0
    sum2 = 0
    for i in range(0, system.get_number_of_rules()):
        if i != index:
            sum1 += system.get_rule(i).get_first_antecedent().value(point[0])*system.get_rule(i).get_second_antecedent().value(
                point[1])*(rule.get_consequense().value(point) - system.get_rule(i).get_consequense().value(point))*rule.get_second_antecedent().value(point[1]) * rule.get_second_antecedent().get_b() * rule.get_first_antecedent().value(point[0]) * (1 - rule.get_first_antecedent().value(point[0]))
            sum2 += system.get_rule(i).get_first_antecedent().value(point[0]) * \
                system.get_rule(i).get_second_antecedent().value(point[1])
    return (system.get_pred() - function_value) * sum1/pow(sum2, 2)


def a_derivative_y(point, system, function_value, rule, index):
    sum1 = 0
    sum2 = 0
    for i in range(0, system.get_number_of_rules()):
        if i != index:
            sum1 += system.get_rule(i).get_first_antecedent().value(point[0])*system.get_rule(i).get_second_antecedent().value(
                point[1])*(rule.get_consequense().value(point) - system.get_rule(i).get_consequense().value(point))*rule.get_first_antecedent().value(point[0]) * rule.get_first_antecedent().get_a() * rule.get_second_antecedent().value(point[1]) * (1 - rule.get_first_antecedent().value(point[1]))
            sum2 += system.get_rule(i).get_first_antecedent().value(point[0]) * \
                system.get_rule(i).get_second_antecedent().value(point[1])
    return (system.get_pred() - function_value) * sum1/pow(sum2, 2)


def b_derivative_x(point, system, function_value, rule, index):
    sum1 = 0
    sum2 = 0
    for i in range(0, system.get_number_of_rules()):
        if i != index:
            sum1 += system.get_rule(i).get_first_antecedent().value(point[0])*system.get_rule(i).get_second_antecedent().value(
                point[1])*(rule.get_consequense().value(point) - system.get_rule(i).get_consequense().value(point))*rule.get_second_antecedent().value(point[1]) * (pow(rule.get_first_antecedent().value(point[0]), 2) - rule.get_first_antecedent().value(point[0])) * (point[0] - rule.get_first_antecedent().get_a())
            sum2 += system.get_rule(i).get_first_antecedent().value(point[0]) * \
                system.get_rule(i).get_second_antecedent().value(point[1])
    return (system.get_pred() - function_value) * sum1/pow(sum2, 2)


def b_derivative_y(point, system, function_value, rule, index):
    sum1 = 0
    sum2 = 0
    for i in range(0, system.get_number_of_rules()):
        if i != index:
            sum1 += system.get_rule(i).get_first_antecedent().value(point[0])*system.get_rule(i).get_second_antecedent().value(
                point[1])*(rule.get_consequense().value(point) - system.get_rule(i).get_consequense().value(point))*rule.get_first_antecedent().value(point[0]) * (pow(rule.get_second_antecedent().value(point[1]), 2) - rule.get_second_antecedent().value(point[1])) * (point[1] - rule.get_second_antecedent().get_a())
            sum2 += system.get_rule(i).get_first_antecedent().value(point[0]) * \
                system.get_rule(i).get_second_antecedent().value(point[1])
    return (system.get_pred() - function_value) * sum1/pow(sum2, 2)


def p_derivative(point, system, function_value, rule):
    return system.get_pred() - function_value * rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1]) * point[0]


def q_derivative(point, system, function_value, rule):
    return system.get_pred() - function_value * rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1]) * point[1]


def r_derivative(point, system, function_value, rule):
    return system.get_pred() - function_value * rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1])
