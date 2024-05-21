from calculadora import soma

try:
    print(soma('1', 3))
except AssertionError as e:
    print(f'Conta Invalida: {e}')
