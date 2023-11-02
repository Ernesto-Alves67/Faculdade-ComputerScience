#include <iostream>
#include <vector>

#include "Dados.h"
using namespace std;

/* === TIPS === */
/* 
   Os estados serão os vertices
   As arestas serão os caminhos. Caminhos estes que são ponderados
   O Automato é definido pelo usuario. 
   As entradas a serem processadas são definidas pelo usuario.

   Converter DFA --> &-NFA
   Converter DFA --> NFA
   Converter NFA --> DFA
   Converter NFA --> &-NFA
   Converter &-NFA --> NFA
   Converter &-NFA --> DFA
*/



int main() {
    Automato autoDeterministico = Automato();


    cout << "Entre com a definicao do Automato" << endl;
    
    configAutomato("definicao.txt", autoDeterministico);
    cout << "Entre com as entradas a serem analisadas" << endl;
    //testAutomato();
    return 0;
}
