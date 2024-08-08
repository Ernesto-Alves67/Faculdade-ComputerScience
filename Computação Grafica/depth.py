# -*- coding: utf-8 -*-
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

"""
	Estudo de vizualização de formas 3D
	Este programa renderiza uma piramide com efeitos de iluminação basicos
	
	Author: ernesto-alves67
"""

# Variável global para controlar o ângulo de rotação
angle = 0.0

# Função para desenhar o triângulo com iluminação
def draw_triangle():
    glBegin(GL_TRIANGLES)
    
    # Definir a cor e normal para cada vértice
    glColor3f(0.5, 0.6, 0.0)  # Define a cor do triângulo
    glNormal3f(0.0, 0.0, 1.0)  # Normal para iluminação
    
    
    glVertex3f(-0.8, -0.8, 0.0)
    glVertex3f(0.8, -0.8, 0.0)
    glVertex3f(0.0, 0.5, 0.0)
    
    glColor3f(1.0, 0.0, 1.0)
    glVertex3f(0.0, 0.0, -1.0)
    glVertex3f(0.5, 0.5, 0.0)
    glVertex3f(0.0, 1.0, 0.0)
    glEnd()

# Função de exibição do GLUT
def display():
    global angle

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)  # Limpa o buffer de cores e profundidade
    glLoadIdentity()  # Reseta a matriz de modelo-visualização

    # Aplicar transformação para dar uma aparência 3D
    glTranslatef(0.0, 0.0, -5.0)  # Translada o triângulo para o fundo da cena
    glRotatef(angle, 0.0, 1.0, 0.0)  # Rotaciona o triângulo ao redor do eixo Y

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
    glEnable(GL_DEPTH_TEST)  # Habilitar teste de profundidade
    glEnable(GL_LIGHTING)  # Habilitar iluminação
    glEnable(GL_LIGHT0)  # Habilitar luz 0

    light_position = [1.0, 1.0, 1.0, 0.0]  # Luz direcional
    light_ambient = [0.2, 0.2, 0.2, 1.0]
    light_diffuse = [0.8, 0.8, 0.8, 1.0]
    light_specular = [1.0, 1.0, 1.0, 1.0]

    glLightfv(GL_LIGHT0, GL_POSITION, light_position)
    glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient)
    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse)
    glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular)

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(-1, 1, -1, 1, -10, 10)  # Configura a projeção ortográfica

    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity()

# Função principal
def main():
    glutInit()  # Inicializa o GLUT
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH)  # Modo de exibição com buffer duplo, cores RGB e profundidade
    glutInitWindowSize(800, 600)  # Tamanho da janela
    glutCreateWindow(b"OpenGL com GLUT")  # Cria a janela com o título especificado

    init()  # Configura o ambiente OpenGL
    glutDisplayFunc(display)  # Define a função de exibição
    glutIdleFunc(idle)  # Define a função de ociosidade
    glutMainLoop()  # Entra no loop principal do GLUT

if __name__ == "__main__":
    main()
