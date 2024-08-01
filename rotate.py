# -*- coding: utf-8 -*-
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

# Variável global para controlar o ângulo de rotação
angle = 0.0

# Função para desenhar o triângulo
def draw_triangle():
	glColor3f(1.0, 0.0, 0.0) 
	glBegin(GL_TRIANGLES)
	glVertex2f(-0.5, -0.5)
	glVertex2f(0.5, -0.5)
	glVertex2f(0.0, 0.5)
	glEnd()

# Função de exibição do GLUT
def display():
    global angle

    glClear(GL_COLOR_BUFFER_BIT)  # Limpa o buffer de cores
    glLoadIdentity()

    # Aplicar rotação
    glTranslatef(0.0, 0.0, 0.0)
    glRotatef(angle, 0.0, 0.0, 1.0)

    draw_triangle()
    glutSwapBuffers()

# Função de ociosidade do GLUT para atualizar o ângulo de rotação
def idle():
    global angle
    angle += 0.5  # Incrementar o ângulo
    if angle > 360:
        angle -= 360
    glutPostRedisplay()  # Solicitar atualização da tela

# Função para configurar o ambiente OpenGL
def init():
    glClearColor(0.0, 0.0, 0.0, 1.0)  # Cor de fundo (preto)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluOrtho2D(-1, 1, -1, 1)  # Configura a projeção ortográfica

# Função principal
def main():
    glutInit()  # Inicializa o GLUT
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB)  # Modo de exibição com buffer duplo e cores RGB
    glutInitWindowSize(800, 600)  # Tamanho da janela
    glutCreateWindow(b"OpenGL com GLUT")  # Cria a janela com o título especificado

    init()  # Configura o ambiente OpenGL
    glutDisplayFunc(display)  # Define a função de exibição
    glutIdleFunc(idle)  # Define a função de ociosidade
    glutMainLoop()  # Entra no loop principal do GLUT

if __name__ == "__main__":
    main()
