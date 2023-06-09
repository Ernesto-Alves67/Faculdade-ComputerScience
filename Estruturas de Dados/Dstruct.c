#include "Dstruct.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//definição do tamanho MAX do vetor estatico
#define MAX 25

/* ### Funções Lista Sequencial Dinamica ### */
//	Inicializa Lista Sequencial Dinamica
void init_Lista(Lista* l){
	l->tamanho = 0;
	l->inicio = NULL;
	l->fim = NULL;
}
//	Exibe valores lista
void showLista(Lista* l) {
    if (l->inicio == NULL) {
        printf("Lista vazia\n");
        return;
    }
    
    Nod* atual = l->inicio;
    printf("Lista: [");
    while (atual != NULL) {
        printf("%d", atual->info);
        
        atual = atual->next;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}

void imprime(Lista* l){
    if (l->inicio == NULL) {
        printf("Lista vazia\n");
        return;
    }

    Nod* atual = l->inicio;
    printf("Elementos da lista:");

    while (atual != NULL) {
        printf(" %d", atual->info);
        atual = atual->next;
    }
    printf(" %d", atual->info);

    printf("\n");
}

//	Insere elemento
void insereInicio(Lista* lista, int Elem) {
    if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
	
	Nod* novoNo = (Nod*)malloc(sizeof(Nod));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    if (lista->inicio == NULL) {
    	novoNo->next = NULL;
        // Se a lista estiver vazia, o novo nó será o primeiro e o último
        lista->inicio = novoNo;
        lista->fim = novoNo;
        //lista->inicio->next = NULL;
	}else{
		novoNo->next = lista->inicio;
    	lista->inicio = novoNo;
	}
        lista->tamanho++;
    
}

void insereFim(Lista* lista, int Elem) {
    if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
    
    Nod* novoNo = (Nod*)malloc(sizeof(Nod));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }
    novoNo->info = Elem;
    novoNo->next = NULL;  // Define o próximo como NULL, já que será o último nó
    
    if (lista->inicio == NULL) {
        // Se a lista estiver vazia, o novo nó será o primeiro e o último
        lista->inicio = novoNo;
        lista->fim = novoNo;
    } else {
        // Se a lista não estiver vazia, adiciona o novo nó no final
        lista->fim->next = novoNo;
        lista->fim = novoNo;
    }
    
    lista->tamanho++;
}

void inserePosicao(Lista* lista, int posicao, int elemento) {
    if (posicao < 0 || posicao > lista->tamanho) {
        printf("Erro: Posição inválida\n");
        return;
    }

    if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }

    Nod* novoNo = (Nod*)malloc(sizeof(Nod));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }
    novoNo->info = elemento;

    if (posicao == 0) {
        // Inserção no início da lista
        novoNo->next = lista->inicio;
        lista->inicio = novoNo;
        if (lista->fim == NULL) {
            // A lista estava vazia, atualiza o ponteiro fim
            lista->fim = novoNo;
        }
    } else if (posicao == lista->tamanho) {
        // Inserção no fim da lista
        novoNo->next = NULL;
        lista->fim->next = novoNo;
        lista->fim = novoNo;
    } else {
        // Inserção em uma posição intermediária
        Nod* anterior = lista->inicio;
        int i;
        for (i = 0; i < posicao - 1; i++) {
            anterior = anterior->next;
        }
        novoNo->next = anterior->next;
        anterior->next = novoNo;
    }

    lista->tamanho++;
}

//	Deletar elementos
void delLista(Lista* l){
	Nod* atual = l->inicio;
    while (atual != NULL) {
        Nod* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    l->tamanho = 0;
    l->inicio = NULL;
    l->fim = NULL;
}



/* ### Funções Lista duplamente encadeada ### */
void init_Dlista(Dlista* lista){
	lista->tamanho = 0;
	lista->inicio = NULL;
	lista->fim = NULL;
}

void showDlista(Dlista* lista){
	if (lista->inicio == NULL) {
        printf("Lista vazia\n");
        return;
    }
    
    Nodu* atual = lista->inicio;
    printf("Lista: [");
    while (atual != NULL) {
        printf("%d", atual->info);
        
        atual = atual->next;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}

void insDlista(Dlista* lista, int Elem){
	if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
	
	Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    if (lista->inicio == NULL) {
    	novoNo->next = NULL;
    	novoNo->prev = NULL;
        // Se a lista estiver vazia, o novo nó será o primeiro e o último
        lista->inicio = novoNo;
        lista->fim = novoNo;
        //lista->inicio->next = NULL;
	}else{
		novoNo->next = lista->inicio;
		novoNo->prev = NULL;
    	lista->inicio = novoNo;
	}
        lista->tamanho++;
}

void insFim(Dlista* lista, int Elem){
	if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
    
    Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }
    novoNo->info = Elem;
    novoNo->next = NULL;
	novoNo->prev = lista->fim;  // Define o próximo como NULL, já que será o último nó
    
    if (lista->inicio == NULL) {
        // Se a lista estiver vazia, o novo nó será o primeiro e o último
        lista->inicio = novoNo;
        lista->fim = novoNo;
        novoNo->prev = NULL;
    } else {
        // Se a lista não estiver vazia, adiciona o novo nó no final
        lista->fim->next = novoNo;
        lista->fim = novoNo;
    }
    
    lista->tamanho++;
}

void ins_Id(Dlista* lista, int id, int Elem){
	    if (id < 0 || id > lista->tamanho) {
        printf("Erro: Posição inválida\n");
        return;
    }

    if (lista->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }

    Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }
    novoNo->info = Elem;

    if (id == 0) {
        // Inserção no início da lista
        novoNo->next = lista->inicio;
        lista->inicio = novoNo;
        if (lista->fim == NULL) {
            // A lista estava vazia, atualiza o ponteiro fim
            lista->fim = novoNo;
        }
    } else if (id == lista->tamanho) {
        // Inserção no fim da lista
        novoNo->next = NULL;
        lista->fim->next = novoNo;
        lista->fim = novoNo;
    } else {
        // Inserção em uma posição intermediária
        Nodu* anterior = lista->inicio;
        int i;
        for (i = 0; i < id - 1; i++) {
            anterior = anterior->next;
        }
        novoNo->next = anterior->next;
        anterior->next = novoNo;
    }

    lista->tamanho++;
}

void del_Dlista(Dlista* lista){
	Nodu* atual = lista->inicio;
    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    lista->tamanho = 0;
    lista->inicio = NULL;
    lista->fim = NULL;
}

/* ### Funções Pilha estatica ### */
void initPilha(Pilha_* pilha){
	pilha->topo = 0;
}
void inserir(Pilha_* pilha, int Elem) {
    if (pilha->topo == MAX) {
        printf("Erro: Pilha cheia\n");
        return;
    }

    Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    novoNo->prev = NULL;
    novoNo->next = NULL;

    pilha->info[pilha->topo] = novoNo;

    if (pilha->topo > 0) {
        pilha->info[pilha->topo]->prev = pilha->info[pilha->topo - 1];
        pilha->info[pilha->topo - 1]->next = pilha->info[pilha->topo];
    }

    pilha->topo++;
}

int pop(Pilha_* pilha){
    if (pilha->topo == 0) {
        printf("Erro: Pilha vazia\n");
        return 0; // Ou algum valor de erro adequado
    }

    pilha->topo--;
    int valor = pilha->info[pilha->topo]->info;

    // Remover referências ao nó desempilhado
    pilha->info[pilha->topo]->prev = NULL;
    pilha->info[pilha->topo]->next = NULL;

    return valor;
}

void delPilha(Pilha_* pilha){
	if (pilha->topo == 0) {
        printf("Erro: Pilha vazia\n");
        return;
    }

    pilha->topo--;
    free(pilha->info[pilha->topo]);
    pilha->info[pilha->topo] = NULL;

}

void showPilha(Pilha_* pilha) {
    if (pilha->topo == 0) {
        printf("Pilha vazia\n");
        return;
    }

    Nodu* atual = pilha->info[pilha->topo - 1];
    printf("Pilha: [");
    while (atual != NULL) {
        printf("%d", atual->info);

        atual = atual->prev;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}


/* ### Funções Pilha Dinamica ### */
void initPilhaDim(Pilha_dim* pilha){
	pilha->topo = 0;
	pilha->info = NULL;
}

void addElem(Pilha_dim* pilha, int Elem){
    Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    novoNo->prev = NULL;
    novoNo->next = NULL;
    
    if (pilha->topo > 0) {
      novoNo->prev = pilha->info;
      pilha->info = novoNo;
    }
	pilha->info = novoNo;
    pilha->topo++;
}

int popElem(Pilha_dim* pilha){
	if (pilha->topo == 0) {
        printf("Erro: Pilha vazia\n");
        return 0; // Ou algum valor de erro adequado
    }
    
    Nodu* no_desempilhado = pilha->info;
    pilha->info = no_desempilhado->prev; // Atualiza o ponteiro para o próximo nó
    int valor = no_desempilhado->info;

    free(no_desempilhado); // Libera a memória do nó desempilhado
    
    pilha->topo--;
    
    return valor;
}

void delPilhaDim(Pilha_dim* pilha){
	if (pilha->topo == 0) {
        printf("Erro: Pilha vazia\n");
    	return; // Ou algum valor de erro adequado
    }
    
    pilha->topo--;
    free(pilha->info);
    pilha->info = NULL;
    
}

void showPD(Pilha_dim* pilha){
	if (pilha->topo == 0) {
        printf("Pilha vazia\n");
        return;
    }

    Nodu* atual = pilha->info;
    printf("Pilha: [");
    while (atual != NULL) {
        printf("%d", atual->info);

        atual = atual->prev;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}


/* ### Funções Fila ### */
void initFila(Fila* f){
	f->inicio = NULL;
	f->fim = NULL;
	f->tamanho = 0;

}

void filaAdd(Fila* f, int Elem){
    if (f->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
	
	Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    if (f->inicio == NULL) {
    	novoNo->next = NULL;
        // Se a fila estiver vazia, o novo nó será o primeiro e o último
        f->inicio = novoNo;
        f->fim = novoNo;
        //lista->inicio->next = NULL;
	}else{
		novoNo->prev = f->fim;
		f->fim->next = novoNo;
		f->fim = novoNo;
	}
        f->tamanho++;
}

int popFila(Fila* f){
	if (f->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
    
    Nodu* retirado = f->inicio;
    int valor = retirado->info;

    if (f->tamanho == 1) {
        // Se há apenas um elemento na fila, atualiza os ponteiros de início e fim para NULL
        f->inicio = NULL;
        f->fim = NULL;
    } else {
        // Atualiza o ponteiro de início para o próximo nó da fila
        f->inicio = retirado->next;
        f->inicio->prev = NULL;
    }

    free(retirado);
    f->tamanho--;

    return valor;
}

int peekFila(Fila* f) {
    if (f->inicio == NULL) {
        printf("Erro: Fila vazia\n");
        return -1; // Ou algum valor de erro adequado
    }

    return f->inicio->info;
}

int sizeFila(Fila* f){
	
	if (f->tamanho == 0) {
        printf("Erro: Fila vazia\n");
        return 0;
    }
    //int tam = f->tamanho;
    return f->tamanho;
}

void delFila(Fila* f){
	Nodu* atual = f->inicio;
    while (atual != NULL) {
        Nodu* proximo = atual->next;
        free(atual);
        atual = proximo;
    }
    f->tamanho = 0;
    f->inicio = NULL;
    f->fim = NULL;
}

void showFila(Fila* f){
	if (f->inicio == NULL) {
        printf("Fila vazia\n");
        return;
    }
    
    Nodu* atual = f->inicio;
    printf("Fila: [");
    while (atual != NULL) {
        printf("%d", atual->info);
        
        atual = atual->next;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}


/* ### Funçõe Deque ### */
void initDeque(Deque* d){
	d->tamanho =0
	d->inicio = NULL;
	d->fim = NULL;
}

void showDeque(Deque* d){
	if (d->inicio == NULL) {
        printf("Lista vazia\n");
        return;
    }
    
    Nodu* atual = d->inicio;
    printf("Deque: [");
    while (atual != NULL) {
        printf("%d", atual->info);
        
        atual = atual->next;
        if (atual != NULL) {
            printf(", ");
        }
    }
    printf("]\n");
}

void insBegin(Deque* d, int Elem){
	if (d->tamanho >= MAX) {
        printf("Erro: Lista cheia\n");
        return;
    }
	
	Nodu* novoNo = (Nodu*)malloc(sizeof(Nodu));
    if (novoNo == NULL) {
        printf("Erro: Falha na alocação de memória\n");
        return;
    }

    novoNo->info = Elem;
    if (d->inicio == NULL) {
    	novoNo->next = NULL;
    	novoNo->prev = NULL;
        // Se a lista estiver vazia, o novo nó será o primeiro e o último
        d->inicio = novoNo;
        d->fim = novoNo;
        //lista->inicio->next = NULL;
	}else{
		novoNo->next = d->inicio;
		novoNo->prev = NULL;
    	d->inicio = novoNo;
	}
        d->tamanho++;
}

void insEnd(Deque* d, int Elem);
int getBegin(Deque* d);
int getEnd(Deque* d);
int sizeDeque(Deque* d);
void del_Deque(Deque* d);
void delBegin(Deque* d);
void delEnd(Deque* d);








/* ### Funções Tabela Hash ### */
void initTH(tHash *t){
	t->ra = -1;
	t->nome = (char*) malloc(2 * sizeof(char));  // Alocando memória para 2 caracteres (incluindo o caractere nulo)
    strcpy(t->nome, " ");
}

void iniciaTH(int ra_, char* nome_, tHash *t){
	t->ra = ra_;
    strcpy(t->nome, nome_);
}
int getRa(tHash *t){
	return t->ra;
}
char* getNome(tHash *t){
	return t->nome;
}


