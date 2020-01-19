import random
from functions import Function
from anfis import ANFIS
from rule import Rule
from derivates import q_derivative, p_derivative, r_derivative, a_derivative_x, a_derivative_y, b_derivative_x, b_derivative_y
import sys


def generate_input_variables(function):
    in_variables = []
    for x in range(-4, 5):
        for y in range(-4, 5):
            in_variables.append((x,
                                 y, function.value((x, y))))

    return in_variables


def main():
    number_of_rules = sys.argv[1]
    learning_rate = float(sys.argv[2])
    type_of_backpropagation = sys.argv[3]
    function = Function()
    input_variables = generate_input_variables(function)

    system = ANFIS(number_of_rules)
    index = 0
    error = 0
    if type_of_backpropagation == 'stohastic':
        for i in range(0, 100000):
            print("Iteration " + str(i))
            point = (input_variables[index][0], input_variables[index][1])
            function_value = input_variables[index][2]
            temp_error = pow(system.value(point) - function_value, 2)/2
            if temp_error < pow(10, -5):
                break

            if(temp_error > error):
                learning_rate /= 2
            error = temp_error

            print("Error " + str(error))

            for y in range(0, system.get_number_of_rules()):
                system.value(point)
                current_rule = system.get_rule(y)
                first_antecedent = current_rule.get_first_antecedent()
                second_antecedent = current_rule.get_second_antecedent()
                consequense = current_rule.get_consequense()
                consequense.set_p(consequense.get_p(
                ) - learning_rate * p_derivative(point, system, function_value, current_rule))
                consequense.set_q(consequense.get_q(
                ) - learning_rate * q_derivative(point, system, function_value, current_rule))
                consequense.set_r(consequense.get_r(
                ) - learning_rate * r_derivative(point, system, function_value, current_rule))
                first_antecedent.set_a(
                    first_antecedent.get_a() + learning_rate * a_derivative_x(point, system, function_value, current_rule, index))
                first_antecedent.set_b(first_antecedent.get_b(
                ) - learning_rate * b_derivative_x(point, system, function_value, current_rule, index))
                second_antecedent.set_a(second_antecedent.get_a(
                ) - learning_rate * a_derivative_y(point, system, function_value, current_rule, index))
                second_antecedent.set_b(second_antecedent.get_b(
                ) - learning_rate * b_derivative_y(point, system, function_value, current_rule, index))

                system.set_rule(y, Rule(first_antecedent, second_antecedent, consequense))

            index += 1

            if(index > 80):
                index = 0
    else:
        for epoch in range(0, 10000):
            weight_errors = []
            for y in range(0, len(input_variables)):
                point = (input_variables[y][0], input_variables[y][1])
                function_value = input_variables[index][2]
                current_rule = system.get_rule(y)
                first_antecedent = current_rule.get_first_antecedent()
                second_antecedent = current_rule.get_second_antecedent()
                consequense = current_rule.get_consequense()
                weight_errors[2] += p_derivative(point,
                                                 system, function_value, current_rule)
                weight_errors[3] += q_derivative(point,
                                                 system, function_value, current_rule)
                weight_errors[4] += r_derivative(point,
                                                 system, function_value, current_rule)
            for y in range(0, system.get_number_of_rules):
                current_rule = system.get_rule(y)
                first_antecedent = current_rule.get_first_antecedent
                second_antecedent = current_rule.get_second_antecedent
                consequense = current_rule.get_consequense
                consequense.set_p(consequense.get_p(
                ) + learning_rate * weight_errors[2])
                consequense.set_q(consequense.get_q(
                ) + learning_rate * weight_errors[3])
                consequense.set_r(consequense.get_r(
                ) + learning_rate * weight_errors[4])


if __name__ == "__main__":

    main()
