# -*- coding: utf-8 -*-
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

# Variável global para controlar o ângulo de rotação
angle = 0.0

# Função para desenhar o triângulo
def draw_triangle():
	glClear(GL_COLOR_BUFFER_BIT) 
	glColor3f(0.5, 0.6, 0.0) 
	glBegin(GL_TRIANGLES)
	glVertex3f(-0.5, -0.5, 2.1)
	glVertex3f(0.5, -0.5, 2.1)
	glVertex3f(0.0, 0.5, 2.1)
	glEnd()

# Função de exibição do GLUT
def display():
    global angle

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)  # Limpa o buffer de cores e profundidade
    glLoadIdentity()

    # Posicionar a câmera
    glTranslatef(0.0, 0.0, -3.0)
    glRotatef(angle, 0.0, 1.0, 0.0)  # Rotação ao redor do eixo Y

    # Definir material do triângulo
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, [1.0, 0.0, 0.0, 1.0])  # Cor vermelha

    draw_triangle()
    glutSwapBuffers()

# Função de ociosidade do GLUT para atualizar o ângulo de rotação
def idle():
    global angle
    angle += 0.1  # Incrementar o ângulo
    if angle > 360:
        angle -= 360
    glutPostRedisplay()  # Solicitar atualização da tela

# Função para configurar o ambiente OpenGL
def init():
	glClearColor(0.5, 0.6, 0.9, 1.0)  # Cor de fundo (preto)
	glEnable(GL_DEPTH_TEST)  # Habilitar teste de profundidade
	glEnable(GL_LIGHTING)  # Habilitar iluminação
	glEnable(GL_LIGHT0)  # Habilitar luz 0

	# Configurar a fonte de luz
	light_position = [1.0, 1.0, 1.0, 0.0]
	light_ambient = [0.2, 0.2, 0.2, 1.0]
	light_diffuse = [0.8, 0.8, 0.8, 1.0]
	light_specular = [1.0, 1.0, 1.0, 1.0]

	glLightfv(GL_LIGHT0, GL_POSITION, light_position)
	glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient)
	glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse)
	glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular)

	# Configuração da projeção
	glMatrixMode(GL_PROJECTION)
	glLoadIdentity()
	gluPerspective(45, 10, 0.0, 50.0)  # Configura a projeção perspectiva

	# Configuração do material para reflexão especular
	material_specular = [1.0, 1.0, 1.0, 1.0]
	material_shininess = [50.0]
	glMaterialfv(GL_FRONT, GL_SPECULAR, material_specular)
	glMaterialfv(GL_FRONT, GL_SHININESS, material_shininess)

# Função principal
def main():
	glutInit()  # Inicializa o GLUT
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH)  # Modo de exibição com buffer duplo, cores RGB e profundidade
	glutInitWindowSize(800, 600)  # Tamanho da janela
	glutCreateWindow(b"OpenGL com GLUT")  # Cria a janela com o título especificado

	init()  # Configura o ambiente OpenGL
	glutDisplayFunc(display)  # Define a função de exibição
	glutIdleFunc(idle)  # Define a função de ociosidade
	glutMainLoop()  # Entra no loop principal do GLUT

if __name__ == "__main__":
    main()
