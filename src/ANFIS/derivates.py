from functions import Function


def b_derivative(point, system, function_value, rule, index, ai, x, mi):
    derivation_result = 0
    for j in range(0, system.get_number_of_rules()):
        if j != index:
            rule_j = system.get_rule(j)
            derivation_result += rule_j.t_norm(point) * (rule.get_consequence().value(point) - rule_j.get_consequence().value(point))
    derivation_result *= (x - ai)*rule.t_norm(point)*(1 - mi)
    derivation_result /= system.normalize_value(point)
    return (function_value - system.get_pred()) * derivation_result



def a_derivative(point, system, function_value, rule, index, bi, mi):
    derivation_result = 0
    for j in range(0, system.get_number_of_rules()):
        if j != index:
            rule_j = system.get_rule(j)
            derivation_result += rule_j.t_norm(point) * (rule.get_consequence().value(point) - rule_j.get_consequence().value(point))
    derivation_result *= bi*rule.t_norm(point)*(1 - mi)
    derivation_result /= system.normalize_value(point)
    return -(function_value - system.get_pred()) * derivation_result

def p_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * ((rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1]))/ system.normalize_value(point)) * point[0]


def q_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * ((rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1]))/ system.normalize_value(point)) * point[1]


def r_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * ((rule.get_first_antecedent().value(point[0]) * rule.get_second_antecedent().value(point[1]))/ system.normalize_value(point))
