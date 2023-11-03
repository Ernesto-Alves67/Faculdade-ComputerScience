#ifndef DSTRUCT_H_INLUDED
#define DSTRUCT_H_INLUDED
#define MAX 25
/*N� para armazenazem de dados.*/
typedef struct Nod {
    int info;
    struct Nod* next;
} Nod;
/* N� para o encadeamento duplo*/
typedef struct Nodu{
	int info;
	struct Nodu* prev;
	struct Nodu* next;
} Nodu;

/*********************************************
*---Defini��o da estrutura Lista encadeada---*
**********************************************/
typedef struct {
	int tamanho;
	Nod* inicio;
	Nod* fim;
} Lista;

/*---Fun�oes Lista---*/
void init_Lista(Lista* l);
void imprime(Lista* l);
void showLista(Lista* l);
void insereInicio(Lista* lista, int Elem);
void insereFim(Lista* lista, int Elem);
void inserePosicao(Lista* lista, int posicao, int elemento);
//void deletElem(Lista* L, int Elem);
void delLista(Lista* l);

/*********************************************
*---Defini��o da estrutura Lista Duplamente encadeada---*
**********************************************/
typedef struct {
	int tamanho;
	Nodu* inicio;
	Nodu* fim;
} Dlista;

/*---Fun�oes Lista---*/
void init_Dlista(Dlista* lista);
void showDlista(Dlista* lista);
void insDlista(Dlista* lista, int Elem);
void insFim(Dlista* lista, int Elem);
void ins_Id(Dlista* lista, int id, int Elem);
void del_Dlista(Dlista* lista);
void del_Delem(Dlista* lista, int id);


/*********************************************
*---Defini��o da estrutura Pilha dinamica---*
**********************************************/
/*N� para armazenazem de dados.*/
typedef struct{
	int topo;
	Nodu* info;
}Pilha_dim;

//fun��es pilha
void initPilhaDim(Pilha_dim* pilha);
void addElem(Pilha_dim* pilha, int Elem);
int popElem(Pilha_dim* pilha);
void delPilhaDim(Pilha_dim* pilha);
void showPD(Pilha_dim* pilha);

/*********************************************
*---Defini��o da estrutura Pilha estatica---*
**********************************************/
typedef struct{
	int topo;
	Nodu* info[MAX];
} Pilha_;

void initPilha(Pilha_* pilha);
void inserir(Pilha_* pilha, int Elem);
int pop(Pilha_* pilha);
void delPilha(Pilha_* pilha);
void showPilha(Pilha_* pilha);

/*********************************************
*---Defini��o da estrutura Fila Dinamica---*
**********************************************/
typedef struct{
	int tamanho;
	Nodu* fim;
	Nodu* inicio;
}Fila;

//funcoe fila
void initFila(Fila* f);
void filaAdd(Fila* f, int Elem);
int popFila(Fila* f);
int peekFila(Fila* f);
int sizeFila(Fila* f);
void delFila(Fila* f);
void showFila(Fila* f);


/*********************************************
*---Defini��o da estrutura Deque---*
**********************************************/
typedef struct {
	int tamanho;
	Nodu* inicio;
	Nodu* fim;
} Deque;

//fun��es Deque
void initDeque(Deque* d);
void showDeque(Deque* d);
void insBegin(Deque* d, int Elem);
void insEnd(Deque* d, int Elem);
int getBegin(Deque* d);
int getEnd(Deque* d);
int sizeDeque(Deque* d);
void del_Deque(Deque* d);
void delBegin(Deque* d);
void delEnd(Deque* d);


/*********************************************
*---Defini��o da estrutura Conjunto(Set)---*
**********************************************/
typedef struct {
	int tamanho;
	Nod* inicio;
	Nod* fim;
} set_;

//	fun��es set(conjuntos)
void initSet(set_* set);
void delSet(set_* set);
void insertSet(set_* set, int Elem);
void set_idel(set_* set, int Elem);
set_ set_union(set_* set1, set_* set2);
set_ set_intersec(set_* set1, set_* set2);
set_ set_diferenca(set_* set1, set_* set2);
int set_isMember(set_* set, int Elem);
int set_isSubset(set_* set1, set_* set2);
int set_isEqual(set_* set1, set_* set2);
int set_size(set_* set);
void set_cover(int conjunto[], int tamanho_conjunto, int subconjuntos[][5], int quantidade_subconjuntos);
//void set_cover(set_** sets, int numSets, set_* result);



/*********************************************
*---Defini��o da estrutura Tabela hash---*
**********************************************/
/*N� para armazenazem de dados.*/
typedef struct {
	int ra;
	char *nome;
} tHash;
//fun��es tHash
void initTH(tHash *t);
void iniciaTH(int ra_, char* nome_, tHash *t);
char* getNome(tHash *t);
int getRa(tHash *t);

/*********************************************
*---Defini��o da estrutura Tabela hash Encadeada ---*
**********************************************/
typedef struct {
	int ra;
	char *nome;
}thash_encadeada;

/*********************************************
*---Defini��o da estrutura Arvore binaria---*
**********************************************/
typedef struct{
} binaryArvore;

/*********************************************
*---Defini��o da estrutura Heap ---*
**********************************************/
typedef struct{
} heap_;
/*********************************************
*---Defini��o da estrutura Fila Prioritaria---*
**********************************************/
typedef struct{
} filaPriority;
/*********************************************
*---Defini��o da estrutura Grqfo---*
**********************************************/
typedef struct{
} grafo;


#endif







