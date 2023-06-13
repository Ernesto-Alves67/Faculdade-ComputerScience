#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include "Dstruct.h"

#define Max_conj 50
int main() {
	set_ d1;
	set_ s1;
	initSet(&s1);
	initSet(&d1);
	set_ conjuntos[50];
	conjuntos[0] = d1;
	conjuntos [1] = s1;
    int num, num1 = 0;
    char op[5];
    int i;
    for(i=2; i<Max_conj;i++){
        conjuntos[i].tamanho = 0;
        conjuntos[i].inicio = NULL;
        conjuntos[i].fim = NULL;    
    }
    printf("Vamos operacoes entre Conjuntos(Set)\n");
    printf("Digite 's' para finalizar o programa.\n'aset' adiciona novo set.\n'uni' uniao de set.\n");
    printf("'add' adiciona item no set1 .\n'add2' adiciona item no set2 .\n'del1' deleta item set1.\n'del2' deleta item set2.\n'delset' deleta set especifico.\n");
    printf("'mbset' verifica se um set contem o item.\n");
	printf("'show' Lista todos os Conjuntos.\n");
    printf("'del' deleta todos os Conjuntos.\n");
    
    while (1) {
        printf("\nDigite uma opcao: ");
        scanf("%6s", op);
        
        if (strcmp(op, "s") == 0) {
            // Opção 's' para finalizar o programa
            break;
        }else if(strcmp(op, "aset") == 0){
            set_ novoSet;
            initSet(&novoSet);
            int k;
			for(k=2; k<Max_conj;k++){
                if(conjuntos[k].inicio == NULL){
                    conjuntos[k] = novoSet;
                    break;
                }else{
                    continue;
                }
            }
        } else if(strcmp(op, "add") == 0){
            printf("Digite um numero: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            insertSet(&conjuntos[0], num);
            showSet(&conjuntos[0]);            
        } else if(strcmp(op, "add2") == 0){
            printf("Digite um numero: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            insertSet(&conjuntos[1], num);
            showSet(&conjuntos[1]);
        }  else if(strcmp(op, "show") == 0){
            for (i = 0; i < Max_conj; i++) {
                if(conjuntos[0].inicio == NULL && conjuntos[1].inicio == NULL){
                	printf("Erro: Nao ha nenhum conjunto criado.");
                	break;
            	}else{
            		if(conjuntos[i].inicio == NULL){
            			continue;
					}
					printf("C%d: ", i);
                	showSet(&conjuntos[i]);
				}
				
    		}
        } else if(strcmp(op, "del1") == 0){
        	printf("Digite a posicao do item: ");
            scanf("%d", &num);
            set_idel(&conjuntos[0], num);
            showSet(&conjuntos[0]);            
        } else if(strcmp(op, "del2") == 0){
        	printf("Digite a posicao do item: ");
            scanf("%d", &num);
            set_idel(&conjuntos[1], num);
            showSet(&conjuntos[1]);            
        } else if(strcmp(op, "delset") == 0){
        	printf("Digite a posicao do set: ");
            scanf("%d", &num);
            delSet(&conjuntos[num]);
            printf("Set apagado!");            
        } else if(strcmp(op, "del") == 0){
            int l;
			for(l=0; l<=Max_conj; l++){
                if(conjuntos[l].inicio != NULL){
                    delSet(&conjuntos[l]);
                }
            }
            printf("Todos os Conjuntos foram apagados");
        } else if(strcmp(op, "uni") == 0){
            printf("posicao set 1: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            printf("posicao set 2: ");
            scanf("%d", &num1);
            getchar();
			set_ uniresultado = set_union(&conjuntos[num], &conjuntos[num1]);
			showSet(&uniresultado);          
        } else if(strcmp(op, "inter") == 0){
            printf("posicao set 1: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            printf("posicao set 2: ");
            scanf("%d", &num1);
            getchar();
			set_ interresultado = set_intersec(&conjuntos[num], &conjuntos[num1]);
			if(interresultado.inicio != NULL){
				showSet(&interresultado);
			}else{
				printf("Intersecao vazia {}");
			} 
			          
        } else if(strcmp(op, "diff") == 0){
            printf("posicao set 1: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            printf("posicao set 2: ");
            scanf("%d", &num1);
            getchar();
			set_ valor = set_diferenca(&conjuntos[num], &conjuntos[num1]);
			//valor->inicio = set_differenca(&conjuntos[num], &conjuntos[num1]);
	
			if(valor.inicio != NULL){
				showSet(&valor);
			}else{
				printf("Diferenca vazia {}");
			} 	          
        } else if(strcmp(op, "mbset") == 0){
            printf("posicao set 1: ");
            scanf("%d", &num);
            getchar();  // Consumir o caractere de nova linha pendente
            printf("item verificado: ");
            scanf("%d", &num1);
            getchar();
			int val = set_isMember(&conjuntos[num], num1);
			if(val){
				printf("Valor esta no set");
			}else{
				printf("Valor nao esta no set");
			}          
        }
		else{
            printf("Opção inválida! Digite novamente.\n");            
        }
	}
	printf("Programa Finalizado.\n");
    return 0;
}
