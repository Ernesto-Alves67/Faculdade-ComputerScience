#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include "Dstruct.h"

int main() {
    Deque d1;
    initDeque(&d1);
    
    int num = 0;
    int id_ = 0;
    char op[4];
    
    printf("Vamos manipular o Deque \n");
    printf("Digite 's' para finalizar o programa.\n\t'add' adicionar inicio \n\t'adf' para adicionar fim \n\t");
	printf("'pop2' retira fim \n\t'tam' tamanho do Deque \n\t'pop1' retira inicio \n\t'del' para apagar os itens do Deque\n");
    scanf("%4s", op);  // Ler até 3 caracteres para a opção
    
    while (strcmp(op, "s") != 0) {
        if (strcmp(op, "add") == 0) {
            printf("Digite um numero: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            insBegin(&d1, num);
            showDeque(&d1);
        } else if (strcmp(op, "adf") == 0) {
            insEnd(&d1);
            showDeque(&d1); 
		else if (strcmp(op, "del") == 0) {
            del_Deque(&d1);
            printf("Fila deletada\n");
        } else if(strcmp(op, "pop1") == 0){
        	delBegin(&d1);
        	showFila(&d1);
		} else if(strcmp(op, "pop2") == 0){
			delBegin(&d1);
        	showFila(&d1);
		} else if(strcmp(op, "tam") == 0){
			int item = sizeDeque(&d1);
			printf("\nTamanho Deque: %d",item);
		}
		  else {
            printf("Digite uma opcao valida!\n");

           }
        
        printf("\nProxima acao? ");
        scanf("%3s", op);  // Ler até 3 caracteres para a opção
    }
    
    //showFila(&f1);
    return 0;
}
