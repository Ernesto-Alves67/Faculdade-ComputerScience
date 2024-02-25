#ifndef CALC_H_INCLUDED
#define CALC_H_INCLUDED

void derivada(int a);
void integral(int a);
void esfera(int b);
void identForm(int b);


void derivada_parcial(int argumento, int var_derivacao);
void gradiente(int funcao);
void ponto_minimo(int funcao);
void ponto_maximo(int funcao);
void integral_dupla(int funcao, int lim_a, int lim_b, int lim_c, int lim_d);
void integral_triple(int funcao);


void jacobiano(int funcao);
void laplace_function();



#endif
