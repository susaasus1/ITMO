from math import cos, factorial

segment = {}
all = {}
dots = []


def is_number():
    try:
        inp = input()
        a, b = map(float, inp.split())
        float(a)
        float(b)
        segment['a'] = a
        segment['b'] = b
        return True
    except ValueError:
        return False


def errorMessage(text):
    print("\033[31m\033[6m{}\033[0m".format(text))


def inputFromTable():
    print("Введите координаты через пробел сначала X, потом Y, для окончания прцоесса напишите EXIT:")
    print("Минимум точек для работы программы - две")
    while True:
        dot = input()
        if dot == 'EXIT':
            if len(dots) < 2:
                errorMessage("Вы ввели меньше двух точек, введите 2 или более точек!")
                continue
            break
        x, y = map(float, dot.split())
        dots.append((x, y))
    all['dots'] = dots


def inputFromFunction():
    print("Выберите функцию:\n1 - cos(x)\n2 - x²\n3 - x³")
    choice = input()
    while True:
        if choice == '1':
            function = (lambda x: cos(x))
            break
        elif choice == '2':
            function = (lambda x: x ** 2)
            break
        elif choice == '3':
            function = (lambda x: x ** 3)
            break
        else:
            errorMessage("Выберите 1 или 2 или 3, не нужно писать другие цифры или буквы!")
    all['function'] = function
    while True:
        print("Введите границы отрезка:")
        if (is_number() == False):
            print("Вы ввели что-то не то")
            continue
        if segment['a'] > segment['b']:
            segment['a'], segment['b'] = segment['b'], segment['a']
        break
    print("Введите количество желаемых точек:")
    while True:
        n = int(input())
        if n < 2:
            errorMessage("Количество точек должно быть больше двух!!!!!")
            continue
        break
    temp = (segment['b'] - segment['a']) / (n - 1)
    k = segment['a']
    for i in range(n):
        dots.append((round(k,4), round(all['function'](k),4)))
        k += temp
    all['dots'] = dots
    print("Точки полученные с помощью функции:")
    print(dots)

def inputConsole():
    print("Выберите метод интерполяции:\n1 - Многочлен Лагранжа\n2 - Многочлен Гаусса")
    choice = input()
    while True:
        if choice == '1':
            all['method'] = '1'
            break
        elif choice == '2':
            all['method'] = '2'
            break
        else:
            errorMessage("Выберите 1 или 2, не нужно писать другие цифры или буквы!")
            choice = input()
    print("Выберите вид ввода данных:\n1 - Таблица значений X и Y\n2 - Функция")
    choice = input()
    while True:
        if choice == '1':
            inputFromTable()
            break
        elif choice == '2':
            inputFromFunction()
            break
        else:
            errorMessage("Выберите 1 или 2, не нужно писать другие цифры или буквы!")
            choice = input()
    print("Введите значение которое будем интерполировать:")
    choice = input()
    all['x'] = float(choice)


def inputFile():
    print("Сказано же что в будущих Версиях")


def methodLargange(dots, x):
    fx = 0
    n = len(dots)
    for i in range(n):
        p1 = p2 = 1
        for j in range(n):
            if i != j:
                p1 = p1 * (x - dots[j][0])
                p2 = p2 * (dots[i][0] - dots[j][0])
        fx = fx + dots[i][1] * (p1 / p2)
    return fx


def methodGaussa(dots, x):
    n = len(dots)
    y = [[0 for i in range(n)] for j in range(n)]
    for i in range(n):
        y[i][0] = dots[i][1]
    for i in range(1, n):
        for j in range(n - i):
            y[j][i] = y[j + 1][i - 1] - y[j][i - 1]
    sum = y[int(n / 2)][0]
    t = (x - dots[int(n / 2)][0]) / (dots[1][0] - dots[0][0])
    for i in range(1, n):
        sum += (calculateT(t, i) * y[int((n - i) / 2)][i]) / factorial(i)
    return sum


def calculateT(p, n):
    temp = p
    for i in range(1, n):
        if (i % 2 == 1):
            temp *= (p - i)
        else:
            temp *= (p + i)
    return temp


def main():
    print("Выберите метод ввода:\n1 - Ввести с файла(будущая версия)\n2 - Ввести с консоли")
    choice = input()
    while True:
        if choice == '1':
            inputFile()
            break
        elif choice == '2':
            inputConsole()
            break
        else:
            errorMessage("Выберите 1 или 2, не нужно писать другие цифры или буквы!")
            choice = input()
    if (all['method'] == '1'):
        fx = methodLargange(all['dots'], all['x'])
    elif (all['method'] == '2'):
        fx = methodGaussa(all['dots'], all['x'])
    print("Полученое значение:\nf(x)={0}".format(fx))


main()
