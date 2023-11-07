#pragma once
#include <iostream>

using namespace std;

const int MAX = 25;

// Opçoes/menus do programa
class Menus {
public:
    static void menuEstruturas();
    static void listaSimplesOperations();
    static void listaDinamicaOperations();
    static void pilhaOperations();
    static void filaOperations();
};

// Lista estatica
class Nod {
public:
    int info;
    Nod* next;
    Nod();
};

class Lista {
private:
    int tamanho;
    int ocupado;
public:
    Lista(int tam);
  
    Nod* inicio;
    Nod* fim;

    /*---Funçoes Lista---*/
    void imprime();
    void showLista();
    void insereFim(int valor);
    void inserePosicao();
    void popItem();
    //void deletElem(Lista* L, int Elem);
    void delLista();
    int getTamanho();
    int getOcupado();
    Nod* getInicio() const;
    Nod* getFim() const;

};

//Lista Dinamica
class Nodu {
public:
    int info;
    Nodu* prev;
    Nodu* next;
    Nodu();
};

class ListaD {
private:
    int tamanho;
public:
    ListaD();
    Nodu* inicio;
    Nodu* fim;

    void popItem();
    void delListaD();
    void insereFim(int);
    void showListaD();
    int getTamanho();
    int getInicio() const;
    int getFim() const;

};
//Pilha estatica
class Pilha {
private:
    int tamanho;
    int ocupado;
public:
    Nodu* topo;
    Nodu* inicio;

    Pilha(int tam);
    void popTopo();
    void delPilha();
    void insereTopo(int);
    void showPilha();
    int getTamanho();
    int getInicio() const;
    int getTopo() const;
};
//Pilha Dinamica
class PilhaD{
private:
    int ocupado;
public:
    Nodu* topo;
    Nodu* inicio;

    PilhaD();
    void popTopo();
    void delPilhaD();
    void insereTopo(int);
    void showPilhaD();
    int getInicio() const;
    int getTopo() const;
};
//Fila Estatica
class Fila {
private:
    int tamanho;
    int ocupado;
public:
    Nodu* inicio;
    Nodu* fim;

    Fila(int tam);
    int getInicio();
    int getFim();
    int getOcupado();
    int getTamanho();
    void inserir(int valor);
    void pop();
    void delFila();
    void showFila();
};

//Fila Dinamica
class FilaD {
private:
    int ocupado;
public:
    Nodu* inicio;
    Nodu* fim;

    FilaD();
    int getInicio();
    int getFim();
    int getOcupado();
    
    void inserir(int valor);
    void pop();
    void delFilaD();
    void showFilaD();
};