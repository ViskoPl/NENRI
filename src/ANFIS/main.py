import random
from functions import Function
from anfis import ANFIS
from rule import Rule
import copy
from derivates import q_derivative, p_derivative, r_derivative, a_derivative, b_derivative
import sys
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

function = Function()
learning_rate = 0
number_of_rules = 0


def generate_input_variables():
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
    input_variables = generate_input_variables()

    system = ANFIS(number_of_rules)
    index = 0
    error = float('inf')

    draw_function();
    draw_learning_graphs()
    draw_function_graphs()

    return

    if type_of_backpropagation == 'stohastic':
        f1 = open("./errors/functions/stohastic_function" + str(number_of_rules) + "." + str(learning_rate) + ".txt", "w+")
        f2 = open("./errors/functions/stohastic_error" + str(number_of_rules) + "." + str(learning_rate) + ".txt", "w+")
        for i in range(1, 100001):
            print("Iteration " + str(i))
            point = (input_variables[index][0], input_variables[index][1])
            function_value = function.value(point)

            temp_error = system.calc_error(function, input_variables)

            if(temp_error < pow(10, -5)):
                break

            error = temp_error
            print("Error " + str(error))

            system.value(point)
            for j in range(0, system.get_number_of_rules()):

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
                    first_antecedent.get_a() - learning_rate * a_derivative(point, system, function_value, current_rule, j, first_antecedent.get_b(), first_antecedent.value(point[0]), second_antecedent.value(point[1])))
                # bx derivation
                first_antecedent.set_b(first_antecedent.get_b(
                ) - learning_rate * b_derivative(point, system, function_value, current_rule, j, first_antecedent.get_a(), point[0], first_antecedent.value(point[0]), second_antecedent.value(point[1])))
                # ay
                second_antecedent.set_a(second_antecedent.get_a(
                ) - learning_rate * a_derivative(point, system, function_value, current_rule, j, second_antecedent.get_b(), second_antecedent.value(point[1]), first_antecedent.value(point[0])))
                # by
                second_antecedent.set_b(second_antecedent.get_b(
                ) - learning_rate * b_derivative(point, system, function_value, current_rule, j, second_antecedent.get_a(), point[1], second_antecedent.value(point[1]), first_antecedent.value(point[0])))

                system.set_rule(j, Rule(first_antecedent, second_antecedent, consequence))

            index += 1

            if(index > 80):
                index = 0
    else:
        f1 = open("./errors/functions/batch_function" + str(number_of_rules) + "." + str(learning_rate) + ".txt", "w+")
        f2 = open("./errors/functions/batch_error" + str(number_of_rules) + "." + str(learning_rate) + ".txt", "w+")
        for epoch in range(0, 5000):

            print("Epoch " + str(epoch))

            temp_error = system.calc_error(function, input_variables)

            if(temp_error < pow(10, -5)):
                break

            error = temp_error
            print("Error " + str(error))

            weight_errors = np.zeros((int(number_of_rules), 7))
            for y in range(0, len(input_variables)):
                point = (input_variables[y][0], input_variables[y][1])
                function_value = function.value(point)
                system.value(point)
                for j in range(0, system.get_number_of_rules()):
                    current_rule = system.get_rule(j)
                    first_antecedent = copy.deepcopy(current_rule.get_first_antecedent())
                    second_antecedent = copy.deepcopy(current_rule.get_second_antecedent())
                    consequence = copy.deepcopy(current_rule.get_consequence())
                    weight_errors[j][0] += p_derivative(point, system, function_value, current_rule)
                    weight_errors[j][1] += q_derivative(point, system, function_value, current_rule)
                    weight_errors[j][2] += r_derivative(point, system, function_value, current_rule)
                    weight_errors[j][3] += a_derivative(point, system, function_value, current_rule, j, first_antecedent.get_b(), first_antecedent.value(point[0]), second_antecedent.value(point[1]))
                    weight_errors[j][4] += b_derivative(point, system, function_value, current_rule, j, first_antecedent.get_a(), point[0], first_antecedent.value(point[0]), second_antecedent.value(point[1]))
                    weight_errors[j][5] += a_derivative(point, system, function_value, current_rule, j, second_antecedent.get_b(), second_antecedent.value(point[1]), first_antecedent.value(point[0]))
                    weight_errors[j][6] += b_derivative(point, system, function_value, current_rule, j, second_antecedent.get_a(), point[1], second_antecedent.value(point[1]), first_antecedent.value(point[0]))
            for j in range(0, system.get_number_of_rules()):
                        current_rule = system.get_rule(j)
                        first_antecedent = copy.deepcopy(current_rule.get_first_antecedent())
                        second_antecedent = copy.deepcopy(current_rule.get_second_antecedent())
                        consequence = copy.deepcopy(current_rule.get_consequence())

                        consequence.set_p(consequence.get_p(
                        ) - learning_rate * weight_errors[j][0])
                        consequence.set_q(consequence.get_q(
                        ) - learning_rate * weight_errors[j][1])
                        consequence.set_r(consequence.get_r(
                        ) - learning_rate * weight_errors[j][2])
                        first_antecedent.set_a(
                            first_antecedent.get_a() - learning_rate * weight_errors[j][3])
                        first_antecedent.set_b(first_antecedent.get_b(
                        ) - learning_rate * weight_errors[j][4])
                        second_antecedent.set_a(second_antecedent.get_a(
                        ) - learning_rate * weight_errors[j][5])
                        second_antecedent.set_b(second_antecedent.get_b(
                        ) - learning_rate * weight_errors[j][6])

                        system.set_rule(j, Rule(first_antecedent, second_antecedent, consequence))

    f1.truncate(0)
    f2.truncate(0)
    b = np.arange(-4, 5, 1)
    d = np.arange(-4, 5, 1)

    for x in range(-4, 5):
        for y in range(-4, 5):
            f1.write(str(system.value((x,y))) + "\n")
            f2.write(str(system.value((x,y)) - function.value((x,y))) + "\n")

    f1.close()
    f2.close()


def draw_graphs(system, number_of_rules, learning_rate):
    b = np.arange(-4, 5, 1)
    d = np.arange(-4, 5, 1)
    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = system.value((x, y))

    X, Y = np.meshgrid(d, b)

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()


    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = system.value((x, y)) - function.value((x, y))

    X, Y = np.meshgrid(d, b)

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()




    # while True:
    #     x = int(input("Enter the integer x >>> "))
    #     y = int(input("Enter the  integer y >>> "))

    #     print("The anfis value is: " + str(system.value((x,y))))
    #     print("The function value is: " + str(function.value((x,y))))

    #     user_input = input("If you want to continue enter 'yes' >>> ")
    #     if user_input != 'yes':
    #         break
    # draw_graphs(system)


def draw_function():
    b = np.arange(-4, 5, 1)
    d = np.arange(-4, 5, 1)
    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = function.value((x, y))

    X, Y = np.meshgrid(d, b)

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()


def draw_graphs(system, number_of_rules, learning_rate):
    b = np.arange(-4, 5, 1)
    d = np.arange(-4, 5, 1)
    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = system.value((x, y))

    X, Y = np.meshgrid(d, b)

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()


    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = system.value((x, y)) - function.value((x, y))

    X, Y = np.meshgrid(d, b)

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

def draw_function_graphs():
    fb1_error=open("./errors/functions/batch_error" + str(1) + "." + str(0.0001) + ".txt", "r").readlines()
    fb2_error=open("./errors/functions/batch_error" + str(2) + "." + str(0.0001) + ".txt", "r").readlines()
    fb3_error=open("./errors/functions/batch_error" + str(10) + "." + str(0.0001) + ".txt", "r").readlines()
    fs1_error=open("./errors/functions/stohastic_error" + str(1) + "." + str(0.001) + ".txt", "r").readlines()
    fs2_error=open("./errors/functions/stohastic_error" + str(2) + "." + str(0.001) + ".txt", "r").readlines()
    fs3_error=open("./errors/functions/stohastic_error" + str(10) + "." + str(0.001) + ".txt", "r").readlines()

    fb1_function=open("./errors/functions/batch_function" + str(1) + "." + str(0.0001) + ".txt", "r").readlines()
    fb2_function=open("./errors/functions/batch_function" + str(2) + "." + str(0.0001) + ".txt", "r").readlines()
    fb3_function=open("./errors/functions/batch_function" + str(10) + "." + str(0.0001) + ".txt", "r").readlines()
    fs1_function=open("./errors/functions/stohastic_function" + str(1) + "." + str(0.001) + ".txt", "r").readlines()
    fs2_function=open("./errors/functions/stohastic_function" + str(2) + "." + str(0.001) + ".txt", "r").readlines()
    fs3_function=open("./errors/functions/stohastic_function" + str(10) + "." + str(0.001) + ".txt", "r").readlines()
    contentsb_1_error = [float(numeric_string.strip()) for numeric_string in fb1_error]
    contentsb_2_error = [float(numeric_string.strip()) for numeric_string in fb2_error]
    contentsb_3_error = [float(numeric_string.strip()) for numeric_string in fb3_error]
    contentss_1_error = [float(numeric_string.strip()) for numeric_string in fs1_error]
    contentss_2_error= [float(numeric_string.strip()) for numeric_string in fs2_error]
    contentss_3_error = [float(numeric_string.strip()) for numeric_string in fs3_error]

    contentsb_1_function = [float(numeric_string.strip()) for numeric_string in fb1_function]
    contentsb_2_function = [float(numeric_string.strip()) for numeric_string in fb2_function]
    contentsb_3_function = [float(numeric_string.strip()) for numeric_string in fb3_function]
    contentss_1_function = [float(numeric_string.strip()) for numeric_string in fs1_function]
    contentss_2_function= [float(numeric_string.strip()) for numeric_string in fs2_function]
    contentss_3_function = [float(numeric_string.strip()) for numeric_string in fs3_function]

    b = np.arange(-4, 5, 1)
    d = np.arange(-4, 5, 1)
    X, Y = np.meshgrid(d, b)
    nu = np.zeros( (b.size, d.size) )

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_1_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_1_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_1_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_1_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    print('Number of rules 2')
    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_2_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_2_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_2_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_2_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    print("number of rules 10")

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_3_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentss_3_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_3_function[x*9 + y]

    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()

    for x in range(-4, 5):
        for y in range(-4, 5):
            nu[x][y] = contentsb_3_error[x*9 + y]
    fig = plt.figure()
    ax = fig.add_subplot(projection = '3d')
    ax.plot_surface(X, Y, nu)
    plt.show()


def draw_learning_graphs():
    fb1=open("./errors/learning_rate/batch_error" + str(10) + "." + str(0.001) + ".txt", "r").readlines()
    fb2=open("./errors/learning_rate/batch_error" + str(10) + "." + str(0.0001) + ".txt", "r").readlines()
    fb3=open("./errors/learning_rate/batch_error" + str(10) + "." + str(0.000001) + ".txt", "r").readlines()
    fs1=open("./errors/learning_rate/stohastic_error" + str(10) + "." + str(0.01) + ".txt", "r").readlines()
    fs2=open("./errors/learning_rate/stohastic_error" + str(10) + "." + str(0.001) + ".txt", "r").readlines()
    fs3=open("./errors/learning_rate/stohastic_error" + str(10) + "." + str(0.00001) + ".txt", "r").readlines()
    contentsb1 = [float(numeric_string.strip()) for numeric_string in fb1]
    contentsb2 = [float(numeric_string.strip()) for numeric_string in fb2]
    contentsb3 = [float(numeric_string.strip()) for numeric_string in fb3]
    contentss1 = [float(numeric_string.strip()) for numeric_string in fs1]
    contentss2 = [float(numeric_string.strip()) for numeric_string in fs2]
    contentss3 = [float(numeric_string.strip()) for numeric_string in fs3]
    content = []
    content.append(contentsb1)
    content.append(contentss1)
    content.append(contentsb2)
    content.append(contentss2)
    content.append(contentsb3)
    content.append(contentss3)

    batch_range = np.arange(0,1500)
    stohastic_range = np.arange(0,30000)

    fig = plt.figure(figsize=(16,8))

    fig.add_subplot(3,2, 1)
    plt.plot(batch_range, contentsb1)
    fig.add_subplot(3,2, 2)
    plt.plot(stohastic_range, contentss1)
    fig.add_subplot(3,2, 3)
    plt.plot(batch_range, contentsb2)
    fig.add_subplot(3,2, 4)
    plt.plot(stohastic_range, contentss2)
    fig.add_subplot(3,2, 5)
    plt.plot(batch_range, contentsb3)
    fig.add_subplot(3,2, 6)
    plt.plot(stohastic_range, contentss3)

    plt.show()
if __name__ == "__main__":

    main()
