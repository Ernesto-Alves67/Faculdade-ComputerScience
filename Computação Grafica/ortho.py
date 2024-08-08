# -*- coding: utf-8 -*-
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

# Variável global para controlar o ângulo de rotação
angle = 5.0

# Função para desenhar o triângulo
def draw_triangle():
    glColor3f(0.5, 0.6, 0.0)  # Define a cor do triângulo
    glBegin(GL_TRIANGLES)
    glVertex3f(-0.5, -0.5, 0.0)  # Coordenada Z é 0.0 para ortografia
    glVertex3f(0.5, -0.5, 0.0)
    glVertex3f(0.0, 0.5, 0.0)
    glEnd()
    
    glColor3f(0.5, 0.6, 0.0)

def draw_cube():
    # Define as cores das faces (opcional, você pode usar cores diferentes para cada face)
    glColor3f(0.2, 0.0, 0.0)  # Vermelho
    
    glBegin(GL_QUADS)
    # Frente
    glVertex3f(-0.5, -0.5, 0.5)
    glVertex3f(0.5, -0.5, 0.5)
    glVertex3f(0.5, 0.5, 0.5)
    glVertex3f(-0.5, 0.5, 0.5)
    # Trás
    glColor3f(1.0, 0.0, 0.0)
    glVertex3f(-0.5, -0.5, -0.5)
    glVertex3f(-0.5, 0.5, -0.5)
    glVertex3f(0.5, 0.5, -0.5)
    glVertex3f(0.5, -0.5, -0.5)
    # Direita
    glColor3f(0.0, 1.0, 0.0)
    glVertex3f(0.5, -0.5, -0.5)
    glVertex3f(0.5, 0.5, -0.5)
    glVertex3f(0.5, 0.5, 0.5)
    glVertex3f(0.5, -0.5, 0.5)
    # Esquerda
    glColor3f(1.0, 1.0, 1.0)
    glVertex3f(-0.5, -0.5, -0.5)
    glVertex3f(-0.5, -0.5, 0.5)
    glVertex3f(-0.5, 0.5, 0.5)
    glVertex3f(-0.5, 0.5, -0.5)
    # Topo
    glColor3f(0.0, 0.0, 0.5)
    glVertex3f(-0.5, 0.5, -0.5)
    glVertex3f(-0.5, 0.5, 0.5)
    glVertex3f(0.5, 0.5, 0.5)
    glVertex3f(0.5, 0.5, -0.5)
    # Base
    glVertex3f(-0.5, -0.5, -0.5)
    glVertex3f(0.5, -0.5, -0.5)
    glVertex3f(0.5, -0.5, 0.5)
    glVertex3f(-0.5, -0.5, 0.5)
    glEnd()



def luz():
    glEnable(GL_DEPTH_TEST)  # Habilitar teste de profundidade
    glEnable(GL_LIGHTING)  # Habilitar iluminação
    glEnable(GL_LIGHT)  # Habilitar luz 0

    light_position = [-10.0, 1.0, 1.0, 0.0]  # Luz direcional
    light_ambient = [0.1, 0.1, 0.1, 1.0]
    light_diffuse = [0.1, 0.1, 0.1, 1.0]
    light_specular = [1.0, 1.0, 1.0, 1.0]

    glLightfv(GL_LIGHT0, GL_POSITION, light_position)
    glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient)
    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse)
    glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular)


# Função de exibição do GLUT
def display():
	global angle

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)  # Limpa o buffer de cores e profundidade
	glLoadIdentity()  # Reseta a matriz de modelo-visualização
	#luz()
	# Aplicar rotação
	glTranslatef(0.0, 0.0, -1.0)  # Move o triângulo para o fundo da cena
	glRotatef(angle, 0.5, 0.0, 0.0)  # Rotação ao redor do eixo X
	#glRotatef(angle*2, 0.0, 0.5, 0.0)  # Rotação ao redor do eixo X
	#glRotatef(angle, 0.0, 0.0, 5.0)

	#draw_triangle()
	draw_cube()
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

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(-5, 5, -1, 1, -1, 1)  # Configura a projeção ortográfica
    #gluPerspective(5, 2, 0.0, 50.0)  # Configura a projeção perspectiva
    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity()

# Função principal
def main():
    glutInit()  # Inicializa o GLUT
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH)  # Modo de exibição com buffer duplo, cores RGB e profundidade
    glutInitWindowSize(1200, 800)  # Tamanho da janela
    glutCreateWindow(b"OpenGL com GLUT")  # Cria a janela com o título especificado

    init()  # Configura o ambiente OpenGL
    glutDisplayFunc(display)  # Define a função de exibição
    glutIdleFunc(idle)  # Define a função de ociosidade
    glutMainLoop()  # Entra no loop principal do GLUT

if __name__ == "__main__":
    main()
