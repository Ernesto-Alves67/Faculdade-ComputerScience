#include <iostream>
#include "dstruct.h"


// definição dos menus
void Menus::menuEstruturas() {
    cout << "Escolha uma Estrutura de dados para brincar!\n" << endl;
    cout << "\t[1] -> lista estatica | [2] -> lista dinamica | [3] -> pilha estatica | [4] -> pilha dinamica" << endl;
    cout << "\t[5] -> fila estatica | [6] -> fila dinamica | [7] -> grafo(dinamico) | [8] -> Arvore Binaria " << endl;
    cout << "\t[9] -> Arvore Avl | [10] -> hash table | [11] -> conjunto | [12] -> Arvore Rubro-Negra " << endl;
    cout << "\t[cls] -> limpa a tela. | [menu] -> exibe este menu novamente | [0] -> Finaliza o programa." << endl;
}

void Menus::listaSimplesOperations() {
    cout << "[crt] -> cria uma lista vazia " << endl;
    cout << "[add:valor] -> adiciona na lista | [pop] -> remove ultimo elem. | [del] -> deleta a lista | [sair] -> volta para o menu de Estruturas" << endl;
}

void Menus::listaDinamicaOperations() {
    cout << "[crt] -> cria uma lista vazia " << endl;
    cout << "[add:valor] -> adiciona na lista | [pop] -> remove ultimo elem. | [del] -> deleta a lista | [sair] -> volta para o menu de Estruturas" << endl;
}

void Menus::pilhaOperations() {
    cout << "[crt] -> cria uma pilha vazia " << endl;
    cout << "[add:valor] -> adiciona na pilha \t [pop] -> desempilhar item | [sair] -> volta para o menu de Estruturas | [del] -> deleta a pilha" << endl;
    
}

void Menus::filaOperations(){
    cout << "[crt] -> cria uma fila vazia " << endl;
    cout << "[add:valor] -> adiciona na fila \t [pop] -> anda a fila | [show] -> mostra a fila | [sair] -> volta para o menu de Estruturas | [del] -> deleta a fila" << endl;
}

// Implementação do construtor da classe Nod
Nod::Nod() {
    info = 0;
    next = NULL;
}

// Implementação do construtor da lista
Lista::Lista(int tam) {
    this->tamanho = tam;
    this->inicio = NULL;
    this->fim = NULL;
    this->ocupado = 0;
}

int Lista::getTamanho() {
    return this->tamanho;
}
int Lista::getOcupado() {
    return this->ocupado;
}

Nod* Lista::getInicio() const {
    return this->inicio;
}

Nod* Lista::getFim() const {
    return this->fim;
}

void Lista::insereFim(int valor) {
    Nod* novoNod = new Nod();
    novoNod->info = valor;
    novoNod->next = NULL;

    if (inicio == NULL) {
        inicio = novoNod;
        fim = novoNod;
        ocupado++;
    }
    else {
        if (ocupado != tamanho) {
            fim->next = novoNod;
            fim = novoNod;
            ocupado++;
        }
        else {
            cout << "\nLista Cheia" << endl;
        }
        
    }
}

void Lista::popItem(){
    if (inicio == NULL) {
        // A lista está vazia, não há nada a ser removido.
        cout << "\nLista Vazia" << endl;
    }
    else if (inicio == fim) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        fim = NULL;
        ocupado--;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nod* penultimo = NULL;
        Nod* atual = inicio;
        while (atual->next != NULL) {
            penultimo = atual;
            atual = atual->next;
        }

        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;
        penultimo->next = NULL;
        fim = penultimo;
        ocupado--;
    }
}

void Lista::imprime() {
    Nod* atual = this->inicio; // Começa a partir do primeiro elemento

    while (atual != NULL) {
        cout << atual->info << " "; // Imprime o valor do elemento
        atual = atual->next; // Move para o próximo elemento
    }
}

void Lista::showLista()
{
    Nod* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "[";
    while (atual != NULL) {
        cout << atual->info <<""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento
        
    }
    cout << "]";
}

void Lista::delLista(){
    Nod* atual = this->inicio;

    while (atual != NULL) {
        Nod* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->tamanho = 0;
    this->fim = NULL;
    cout << "Lista deletada!\n" << endl;
}

// --- implementação dos metodos da Lista Dinamica
Nodu::Nodu() {
    info = 0;
    prev = NULL;
    next = NULL;

}

ListaD::ListaD() {
    this->tamanho = 0;
    this->inicio = NULL;
    this->fim = NULL;
}

int ListaD::getTamanho() { return this->tamanho; }

int ListaD::getInicio() const
{
    if (inicio != NULL) {
        return this->inicio->info;
    }
    return 0;
}

int ListaD::getFim() const
{
    if (inicio != NULL) {
        return this->fim->info;
    }
    return 0;
}

void ListaD::delListaD(){
    Nodu* atual = this->inicio;

    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->tamanho = 0;
    this->fim = NULL;
    cout << "Lista deletada!\n" << endl;
}

void ListaD::insereFim(int valor){
    Nodu* novo_no = new Nodu();
    novo_no->info = valor;
    novo_no->next = NULL;

    if (inicio == NULL) {
        novo_no->prev = NULL;
        inicio = novo_no;
        fim = novo_no;
        tamanho++;
    }
    else {
        novo_no->prev = fim;
        fim->next = novo_no;
        fim = novo_no;
        tamanho++;
    }
}

void ListaD::showListaD(){
    Nodu* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "[";
    while (atual != NULL) {
        cout << atual->info << ""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento

    }
    cout << "]";
}

void ListaD::popItem() {
    if (inicio == NULL) {
        // A lista está vazia, não há nada a ser removido.
        cout << "\nLista Vazia" << endl;
    }
    else if (inicio == fim) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        fim = NULL;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nodu* penultimo = NULL;
        Nodu* atual = inicio;
        while (atual->next != NULL) {
            penultimo = atual;
            atual = atual->next;
        }

        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;
        penultimo->next = NULL;
        fim = penultimo;
    }
}

// --- implementação dos metodos da Pilha
Pilha::Pilha(int tam) {
    this->ocupado = 0;
    this->tamanho = tam;
    this->inicio = NULL;
    this->topo = NULL;
}

void Pilha::popTopo() {
    if (inicio == NULL) {
        // A pilha está vazia, não há nada a ser removido.
        cout << "\nPilha Vazia" << endl;
    }
    else if (inicio == topo) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        topo = NULL;
        ocupado--;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nodu* penultimo = NULL;
        Nodu* atual = inicio;
        while (atual->next != NULL) {
            penultimo = atual;
            atual = atual->next;
            ocupado--;
        }

        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;
        penultimo->next = NULL;
        topo = penultimo;
        ocupado--;
    }
}

void Pilha::delPilha() {
    Nodu* atual = this->inicio;

    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->tamanho = 0;
    this->topo = NULL;
    cout << "Pilha deletada!\n" << endl;
}

void Pilha::insereTopo(int valor){
    Nodu* novo_no = new Nodu();
    novo_no->info = valor;
    novo_no->next = NULL;

    if (inicio == NULL) {
        novo_no->prev = NULL;
        inicio = novo_no;
        topo = novo_no;
        ocupado++;
    }
    else {
        if (ocupado != tamanho) {
            novo_no->prev = topo;
            topo->next = novo_no;
            topo = novo_no;
            ocupado++;
        }
        else {
            cout << "\nPilha Cheia" << endl;
        }
        
    }
}

void Pilha::showPilha() {
    Nodu* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "Base:[";
    while (atual != NULL) {
        cout << atual->info << ""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento

    }
    cout << "]:Topo";
}

int Pilha::getTamanho() { return this->tamanho; }

int Pilha::getInicio() const {
    if (inicio != NULL) {
        return this->inicio->info;
    }
    return 0;
}

int Pilha::getTopo() const {
    if (inicio != NULL) {
        return this->topo->info;
    }
    return 0;
}


// --- implementação dos metodos da pilha Dinamica

PilhaD::PilhaD() {
    this->ocupado = 0;
    this->inicio = NULL;
    this->topo = NULL;
}

void PilhaD::popTopo() {
    if (inicio == NULL) {
        // A pilha está vazia, não há nada a ser removido.
        cout << "\nPilha Vazia" << endl;
    }
    else if (inicio == topo) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        topo = NULL;
        ocupado--;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nodu* penultimo = NULL;
        Nodu* atual = inicio;
        while (atual->next != NULL) {
            penultimo = atual;
            atual = atual->next;
            ocupado--;
        }

        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;
        penultimo->next = NULL;
        topo = penultimo;
        ocupado--;
    }
}

void PilhaD::delPilhaD() {
    Nodu* atual = this->inicio;

    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->topo = NULL;
    cout << "Pilha deletada!\n" << endl;
}

void PilhaD::insereTopo(int valor) {
    Nodu* novo_no = new Nodu();
    novo_no->info = valor;
    novo_no->next = NULL;

    if (inicio == NULL) {
        novo_no->prev = NULL;
        inicio = novo_no;
        topo = novo_no;
        ocupado++;
    }
    else {
        novo_no->prev = topo;
        topo->next = novo_no;
        topo = novo_no;
        ocupado++;
    }
}

void PilhaD::showPilhaD() {
    Nodu* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "Base:[";
    while (atual != NULL) {
        cout << atual->info << ""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento

    }
    cout << "]:Topo";
}

int PilhaD::getInicio() const {
    if (inicio != NULL) {
        return this->inicio->info;
    }
    return 0;
}

int PilhaD::getTopo() const {
    if (inicio != NULL) {
        return this->topo->info;
    }
    return 0;
}


// --- implementação dos metodos da Fila

Fila::Fila(int tam) {
    this->ocupado = 0;
    this->tamanho = tam;
    this->inicio = NULL;
    this->fim = NULL;
}

int Fila::getInicio() {
    if (inicio != NULL) {
        return this->inicio->info;
    }
    return 0;
}

int Fila::getFim(){
    if (inicio != NULL) {
        return this->fim->info;
    }
    return 0;
}

int Fila::getOcupado() {
    if (inicio != NULL) {
        return this->ocupado;
    }
    return 0;
}

int Fila::getTamanho() {
    if (inicio != NULL) {
        return this->tamanho;
    }
    return 0;
}

void Fila::inserir(int valor){
    Nodu* novo_no = new Nodu();
    novo_no->info = valor;
    novo_no->next = NULL;

    if (inicio == NULL) {
        novo_no->prev = NULL;
        inicio = novo_no;
        fim = novo_no;
        ocupado++;
    }
    else {
        if (ocupado != tamanho) {
            novo_no->prev = fim;
            fim->next = novo_no;
            fim = novo_no;
            ocupado++;
        }
        else {
            cout << "\nfila Cheia" << endl;
        }
    }
}

void Fila::pop(){
    if (inicio == NULL) {
        // A fila está vazia, não há nada a ser removido.
        cout << "\nFila Vazia" << endl;
    }
    else if (inicio == fim) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        fim = NULL;
        ocupado--;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nodu* penultimo = NULL;
        Nodu* atual = inicio;
        
        inicio = atual->next;
        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;
        
        ocupado--;
        
    }
}

void Fila::delFila(){
    Nodu* atual = this->inicio;

    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->tamanho = 0;
    this->fim = NULL;
    cout << "Fila deletada!\n" << endl;
}

void Fila::showFila() {
    Nodu* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "[";
    while (atual != NULL) {
        cout << atual->info << ""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento

    }
    cout << "]";
}

// --- implementação dos metodos da Fila dinamica

FilaD::FilaD() {
    this->ocupado = 0;
    this->inicio = NULL;
    this->fim = NULL;
}

int FilaD::getInicio() { return this->inicio->info; }

int FilaD::getFim() { return this->fim->info; }

int FilaD::getOcupado() { return this->ocupado; }

void FilaD::inserir(int valor){
    Nodu* novo_no = new Nodu();
    novo_no->info = valor;
    novo_no->next = NULL;

    if (inicio == NULL) {
        novo_no->prev = NULL;
        inicio = novo_no;
        fim = novo_no;
        ocupado++;
    }
    else {
        novo_no->prev = fim;
        fim->next = novo_no;
        fim = novo_no;
        ocupado++;
    }
}

void FilaD::pop(){
    if (inicio == NULL) {
        // A fila está vazia, não há nada a ser removido.
        cout << "\nFila Vazia" << endl;
    }
    else if (inicio == fim) {
        // A lista contém apenas um elemento, que será removido.
        delete inicio;
        inicio = NULL;
        fim = NULL;
        ocupado--;
    }
    else {
        // A lista tem mais de um elemento, precisamos percorrê-la para encontrar o penúltimo elemento.
        Nodu* penultimo = NULL;
        Nodu* atual = inicio;

        inicio = atual->next;
        // Agora, penultimo aponta para o penúltimo elemento, e atual aponta para o último.
        // Vamos remover o último elemento.
        delete atual;

        ocupado--;

    }
}

void FilaD::delFilaD(){
    Nodu* atual = this->inicio;

    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    this->inicio = NULL;
    this->fim = NULL;
    cout << "Fila deletada!\n" << endl;
}

void FilaD::showFilaD(){
    
    Nodu* atual = this->inicio; // Começa a partir do primeiro elemento
    cout << "[";
    while (atual != NULL) {
        cout << atual->info << ""; // Imprime o valor do elemento
        if (atual->next != NULL) {
            cout << ", ";
        }
        atual = atual->next; // Move para o próximo elemento

    }
    cout << "]";
}
