import random
from functions import Function
from anfis import ANFIS
from rule import Rule
import copy
from derivates import q_derivative, p_derivative, r_derivative, a_derivative, b_derivative
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
    error = float('inf')
    if type_of_backpropagation == 'stohastic':
        for i in range(1, 15000):
            print("Iteration " + str(i))
            point = (input_variables[index][0], input_variables[index][1])
            function_value = function.value(point)

            temp_error = system.calc_error(function, input_variables)

            if(temp_error < pow(10, -5)):
                break

            if(i % 2000 == 0 ):
                learning_rate /=2

            error = temp_error
            print("Error " + str(error))


            for j in range(0, system.get_number_of_rules()):
                system.value(point)
                current_rule = system.get_rule(j)
                first_antecedent = copy.deepcopy(current_rule.get_first_antecedent())
                second_antecedent = copy.deepcopy(current_rule.get_second_antecedent())
                consequence = copy.deepcopy(current_rule.get_consequence())
                # p derivation
                consequence.set_p(consequence.get_p(
                ) - learning_rate * p_derivative(point, system, function_value, current_rule))
                # q derivation
                consequence.set_q(consequence.get_q(
                ) - learning_rate * q_derivative(point, system, function_value, current_rule))
                # r derivation
                consequence.set_r(consequence.get_r(
                ) - learning_rate * r_derivative(point, system, function_value, current_rule))
                # ax derivation
                first_antecedent.set_a(
                    first_antecedent.get_a() - learning_rate * a_derivative(point, system, function_value, current_rule, j, first_antecedent.get_b(), first_antecedent.value(point[0])))
                # bx derivation
                first_antecedent.set_b(first_antecedent.get_b(
                ) - learning_rate * b_derivative(point, system, function_value, current_rule, j, first_antecedent.get_a(), point[0], first_antecedent.value(point[0])))
                # ay
                second_antecedent.set_a(second_antecedent.get_a(
                ) - learning_rate * a_derivative(point, system, function_value, current_rule, j, second_antecedent.get_b(), second_antecedent.value(point[1])))
                # by
                second_antecedent.set_b(second_antecedent.get_b(
                ) - learning_rate * b_derivative(point, system, function_value, current_rule, j, second_antecedent.get_a(), point[1], second_antecedent.value(point[1])))

                system.set_rule(j, Rule(first_antecedent, second_antecedent, consequence))

            index += 1

            if(index > 80):
                index = 0
    else:
        for epoch in range(0, 10000):

            print("Epoch " + str(epoch))

            temp_error = system.calc_error(function, input_variables)

            if(temp_error < pow(10, -5)):
                break

            if(epoch % 2000 == 0 ):
                learning_rate /=2

            error = temp_error
            print("Error " + str(error))


            for y in range(0, len(input_variables)):
                weight_errors = [0, 0, 0, 0, 0, 0, 0]
                point = (input_variables[y][0], input_variables[y][1])
                function_value = function.value(point)
                system.value(point)
                for j in range(0, system.get_number_of_rules()):
                    current_rule = system.get_rule(j)
                    first_antecedent = copy.deepcopy(current_rule.get_first_antecedent())
                    second_antecedent = copy.deepcopy(current_rule.get_second_antecedent())
                    consequence = copy.deepcopy(current_rule.get_consequence())
                    weight_errors[0] += p_derivative(point, system, function_value, current_rule)
                    weight_errors[1] += q_derivative(point, system, function_value, current_rule)
                    weight_errors[2] += r_derivative(point, system, function_value, current_rule)
                    weight_errors[3] += a_derivative(point, system, function_value, current_rule, j, first_antecedent.get_b(), first_antecedent.value(point[0]))
                    weight_errors[4] += b_derivative(point, system, function_value, current_rule, j, first_antecedent.get_a(), point[0], first_antecedent.value(point[0]))
                    weight_errors[5] += a_derivative(point, system, function_value, current_rule, j, second_antecedent.get_b(), second_antecedent.value(point[1]))
                    weight_errors[6] += b_derivative(point, system, function_value, current_rule, j, second_antecedent.get_a(), point[1], second_antecedent.value(point[1]))
                for j in range(0, system.get_number_of_rules()):
                    current_rule = system.get_rule(j)
                    first_antecedent = copy.deepcopy(current_rule.get_first_antecedent())
                    second_antecedent = copy.deepcopy(current_rule.get_second_antecedent())
                    consequence = copy.deepcopy(current_rule.get_consequence())

                    consequence.set_p(consequence.get_p(
                    ) - learning_rate * weight_errors[0])
                    consequence.set_q(consequence.get_q(
                    ) - learning_rate * weight_errors[1])
                    consequence.set_r(consequence.get_r(
                    ) - learning_rate * weight_errors[2])
                    first_antecedent.set_a(
                        first_antecedent.get_a() - learning_rate * weight_errors[3])
                    first_antecedent.set_b(first_antecedent.get_b(
                    ) - learning_rate * weight_errors[4])
                    second_antecedent.set_a(second_antecedent.get_a(
                    ) - learning_rate * weight_errors[5])
                    second_antecedent.set_b(second_antecedent.get_b(
                    ) - learning_rate * weight_errors[6])

                    system.set_rule(j, Rule(first_antecedent, second_antecedent, consequence))



    while True:
        x = int(input("Enter the integer x >>> "))
        y = int(input("Enter the  integer y >>> "))

        print("The anfis value is: " + str(system.value((x,y))))
        print("The function value is: " + str(function.value((x,y))))

        user_input = input("If you want to continue enter 'yes' >>> ")
        if user_input != 'yes':
            break

if __name__ == "__main__":

    main()
