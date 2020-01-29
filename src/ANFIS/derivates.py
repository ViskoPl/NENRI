from functions import Function


def b_derivative(point, system, function_value, rule, index, ai, x, mi, D):
    derivation_result = 0
    for j in range(0, system.get_number_of_rules()):
        if j != index:
            rule_j = system.get_rule(j)
            derivation_result += rule_j.t_norm(point) * (rule.get_consequence().value(point) - rule_j.get_consequence().value(point))
    derivation_result /= pow(system.normalize_value(point), 2)
    derivation_result *= D
    derivation_result *= -(x - ai)*mi*(1 - mi)
    return -(function_value - system.get_pred()) * derivation_result



def a_derivative(point, system, function_value, rule, index, bi, mi, D):
    derivation_result = 0
    for j in range(0, system.get_number_of_rules()):
        if j != index:
            rule_j = system.get_rule(j)
            derivation_result += rule_j.t_norm(point) * (rule.get_consequence().value(point) - rule_j.get_consequence().value(point))

    derivation_result /= pow(system.normalize_value(point), 2)
    derivation_result *= D
    derivation_result *= bi*mi*(1 - mi)
    return -(function_value - system.get_pred()) * derivation_result

def p_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * (rule.t_norm(point) / system.normalize_value(point)) * point[0]


def q_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * (rule.t_norm(point) / system.normalize_value(point)) * point[1]


def r_derivative(point, system, function_value, rule):
    return -(function_value - system.get_pred()) * (rule.t_norm(point) / system.normalize_value(point))
