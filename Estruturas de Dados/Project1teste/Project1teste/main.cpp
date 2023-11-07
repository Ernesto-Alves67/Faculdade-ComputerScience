#include <iostream>
#include "dstruct.h"
#include <cstring>
#include <cctype>
#include <cstdlib>
#include <regex>
#include <cstdlib>
using namespace std;

bool isStringNotANumber(const std::string& str) {
    for (char c : str) {
        if (!std::isdigit(c)) {
            return true; // Encontrou um caractere que não é um dígito
        }
    }
    return false; // Todos os caracteres são dígitos
}


int main(int argc, char** argv) {
    
    Menus::menuEstruturas();

    string op; // Usando um array de caracteres para armazenar a operação
    checkMenu:
    cin >> op;
    if (isStringNotANumber(op) || stoi(op) > 12) {
        if (op == "cls" || op == "menu") { goto operacoesdinamicas; }
        std::cout << "Digite uma opcao valida!" << std::endl;
        goto checkMenu;
    }
    else {
        while (op != "0") {

            if (op == "1") {                                         // Lista Estatica
                Lista* novaLista = nullptr;


                cout << "{Lista Estatica}\n" << endl;
                string opt;
                Menus::listaSimplesOperations();

                cin >> opt;
            verificacoes:
                if (opt == "crt") {
                    int tamanho;
                    cout << "Qual o tamanho da lista?" << endl;
                    cin >> tamanho;
                    novaLista = new Lista(tamanho);
                    cout << "Lista criada com sucesso\t";
                    novaLista->showLista();
                    cout << "\n" << endl;
                    cin >> opt;
                }

                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (novaLista) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            novaLista->insereFim(valor);
                            novaLista->showLista();
                            cout << "\n";
                            cin >> opt;
                            goto verificacoes;
                        }
                    }
                    else { cout << "Lista ainda nao foi criada!.\n"; }
                }



                if (opt == "pop") {
                    novaLista->popItem();
                    novaLista->showLista();
                    cin >> opt;
                    goto verificacoes;
                }
                if (opt == "del") {
                    novaLista->delLista();
                    cin >> opt;
                }

                if (opt == "sair") {
                    if (novaLista) { novaLista->delLista(); }

                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }
            }

            if (op == "2") {
                ListaD* listadinamica = nullptr;

                cout << "{Lista Dinamica}\n" << endl;
                string opt;
                Menus::listaDinamicaOperations();
                cin >> opt;
            veri2:
                if (opt == "crt") {
                    listadinamica = new ListaD();
                    cout << "Lista criada com sucesso\t";
                    listadinamica->showListaD();
                    cout << "\n" << endl;
                    cin >> opt;
                    goto veri2;
                }

                // definiçao da adição de elementos.
                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (listadinamica) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            listadinamica->insereFim(valor);
                            listadinamica->showListaD();
                            cout << "\n";
                            cin >> opt;
                            goto veri2;
                        }
                    }
                    else { cout << "Lista ainda nao foi criada!.\n"; cin >> opt; goto veri2; }
                }
              

                if (opt == "pop") {
                    listadinamica->popItem();
                    listadinamica->showListaD();
                    cout << "\n";
                    cin >> opt;
                    goto veri2;
                }
                if (opt == "del") {
                    listadinamica->delListaD();
                    listadinamica->showListaD();
                    cout << "\n";
                    cin >> opt;
                    goto veri2;
                }
                if (opt == "sair") {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "3") {
                Pilha* novaPilha = nullptr;
                cout << "{Pilha Estatica}\n" << endl;
                string opt;
                Menus::pilhaOperations();
            pilhavr:
                cin >> opt;

                if (opt == "pop") {
                    novaPilha->popTopo();
                    novaPilha->showPilha();
                    cout << "\n";

                    goto pilhavr;
                }
                if (opt == "crt") {
                    int tam;
                    cout << "Qual o tamanho da pilha?" << endl;
                    cin >> tam;
                    novaPilha = new Pilha(tam);
                    cout << "Pilha criada com sucesso\t";
                    novaPilha->showPilha();
                    cout << "\n" << endl;
                    
                    goto pilhavr;
                }

                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (novaPilha) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            novaPilha->insereTopo(valor);
                            novaPilha->showPilha();
                            cout << "\n";
                            
                            goto pilhavr;
                        }
                    }
                    else { cout << "Pilha ainda nao foi criada!.\n";  goto pilhavr; }
                }
                
                

                if (opt == "sair") {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }
                // se digitar alguma coisa invalida
                cout << "Digite uma opcao valida!" << endl;  goto pilhavr;
            }

            if (op == "4") {
                cout << "{Pilha Dinamica}\n" << endl;
                
                PilhaD *pd = nullptr;
                string opt;
                cin >> opt;
            // --Quebra de fluxo/ inicio loope
            pdvr:
                if(opt == "crt"){
                    if (pd) {
                        pd = new PilhaD();
                        cout << "Pilha Criada!" << endl;
                        pd->showPilhaD();
                        cin >> opt;
                        goto pdvr;
                    }
                    else { cout << "Pilha ainda nao foi criada" << endl; cin >> opt; goto pdvr; }
                }

                if (opt == "del") {
                    if (pd) {
                        pd->delPilhaD();
                        cout << "Pilha Deletada!" << endl;
                        pd->showPilhaD();
                        cin >> opt;
                        goto pdvr;
                    }
                    else { cout << "Pilha ainda nao foi criada" << endl; cin >> opt; goto pdvr; }
                }

                if (opt == "pop"){
                    if (pd) {
                        pd->popTopo();
                        pd->showPilhaD();
                        cin >> opt;
                        goto pdvr;
                    }
                    else { cout << "Pilha ainda nao foi criada" << endl; cin >> opt; goto pdvr; }
                }

                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (pd) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            pd->insereTopo(valor);
                            pd->showPilhaD();
                            cout << "\n";
                            cin >> opt;
                            goto pdvr;
                        }
                    }
                    else { cout << "Pilha ainda nao foi criada!.\n";  goto pdvr; }
                }
                
                if (opt == "sair") {
                    pd->delPilhaD();
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "5") {
                Fila* mFila = nullptr;
                cout << "{Fila Estatica}\n" << endl;
                
                Menus::filaOperations();
                string opt;
                cin >> opt;

            filavr:
               
                if(opt == "crt"){
                    int tam;
                    cout << "Qual o tamanho da fila?" << endl;
                    cin >> tam;
                    mFila = new Fila(tam);
                    cout << "Fila criada com sucesso\t";
                    mFila->showFila();
                    cout << "\n" << endl;
                    cin >> opt;
                    goto filavr;
                }

                // remoção de item da fila
                
                if (opt == "pop") {
                    if (mFila) {
                    mFila->pop();
                    mFila->showFila();
                    cout << "\n";
                    cin >> opt;
                    goto filavr;
                    }
                    else { cout << "Fila ainda não foi criada!" << endl; cin >> opt; goto filavr; }
                }
                
                // adição de items na fila
                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (mFila) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            
                            mFila->inserir(valor );
                            mFila->showFila();
                            cout << "\n";
                            cin >> opt;
                            goto filavr;
                        }
                    }
                    else { cout << "Fila ainda nao foi criada!.\n"; cin >> opt; goto filavr; }
                }

                if (opt == "sair") {
                    mFila->delFila();
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }
                if (opt == "show") {
                    mFila->showFila();
                    cout << "\n";
                    cin >> opt;
                    goto filavr;
                }

                if (opt == "del") {
                    if (mFila) {
                        mFila->delFila();
                        mFila->showFila();
                        cout << "\n";
                        cin >> opt;
                        goto filavr;
                    }
                    else { cout << "Fila ainda não foi criada!" << endl; cin >> opt; goto filavr; }
                }

                if(opt == "cls"){
                #ifdef _WIN32
                    system("cls"); // No Windows
                    Menus::filaOperations();
                    mFila->showFila();
                    cin >> opt;
                    goto filavr;
                #endif
                }
                // se digitar alguma coisa invalida
                cout << "Digite uma opcao valida!" << endl; cin >> opt; goto filavr;
            }

            if (op == "6") {
                
                FilaD* fila_dinamica = nullptr;
                cout << "{Fila Dinamica}\n" << endl;
                string opt;
                Menus::filaOperations();
                cin >> opt;

            fdvr:
                if (opt == "crt") {
                    fila_dinamica = new FilaD();
                    cout << "Fila criada com sucesso\t";
                    fila_dinamica->showFilaD();
                    cout << "\n" << endl;
                    cin >> opt;
                    goto fdvr;
                }

                // remoção de item da fila

                if (opt == "pop") {
                    if (fila_dinamica) {
                        fila_dinamica->pop();
                        fila_dinamica->showFilaD();
                        cout << "\n";
                        cin >> opt;
                        goto fdvr;
                    }
                    else { cout << "Fila ainda não foi criada!" << endl; cin >> opt; goto fdvr; }
                }



                // adição de items na fila
                regex pattern("add:(\\d+)");
                smatch matches;
                if (regex_search(opt, matches, pattern)) {
                    if (fila_dinamica) {
                        if (matches.size() > 1) {
                            std::string valorStr = matches[1].str(); // Obtém a parte após os dois pontos como uma string

                            // Converte o valor para um inteiro (ou outro tipo desejado)
                            int valor;
                            try {
                                valor = std::stoi(valorStr);
                            }
                            catch (const std::invalid_argument& e) {
                                std::cerr << "Erro ao converter valor para inteiro: " << e.what() << std::endl;
                                return 1; // Saia com código de erro
                            }
                            for (int i = 0; i <= 100000; i++) { fila_dinamica->inserir(valor+i); }
                            
                            fila_dinamica->showFilaD();
                            cout << "\n";
                            cin >> opt;
                            goto fdvr;
                        }
                    }
                    else { cout << "Fila ainda nao foi criada!.\n"; cin >> opt; goto fdvr; }
                }

                if (opt == "sair") {
                    fila_dinamica->delFilaD();
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }
                if (opt == "show") {
                    fila_dinamica->showFilaD();
                    cout << "\n";
                    cin >> opt;
                    goto fdvr;
                }

                if (opt == "del") {
                    if (fila_dinamica) {
                        fila_dinamica->delFilaD();
                        fila_dinamica->showFilaD();
                        cout << "\n";
                        cin >> opt;
                        goto fdvr;
                    }
                    else { cout << "Fila ainda não foi criada!" << endl; cin >> opt; goto fdvr; }
                }

                if (opt == "cls") {
                #ifdef _WIN32
                    system("cls"); // No Windows
                    Menus::filaOperations();
                    fila_dinamica->showFilaD();
                    cin >> opt;
                    goto fdvr;
                #endif
                }
                // se digitar alguma coisa invalida
                cout << "Digite uma opcao valida!" << endl; cin >> opt; goto fdvr;

            }

            if (op == "7") {
                cout << "{Grafo(Dinamico)}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "8") {
                cout << "{Arvore Binaria}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "9") {
                cout << "{Arvore AVL}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "10") {
                cout << "{HashTable}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }

            if (op == "11") {
                cout << "{Conjunto}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }
            
            if (op == "12") {
                cout << "{Arvore-RubroNegra}\n" << endl;
                char opt[5];
                cout << "Ainda em implementacao!\n Digite sair" << endl;
                cin >> opt;

                if (strcmp(opt, "sair") == 0) {
                    cout << "Escolha uma estrutura.\n";
                    goto checkMenu;

                }

            }
            
            operacoesdinamicas:
            if (op == "menu") {
                Menus::menuEstruturas();
                goto checkMenu;
            }
            if (op == "cls") {
             #ifdef _WIN32
                system("cls"); // No Windows
                Menus::menuEstruturas();
                goto checkMenu;
            #endif
            }
        }
    }

    
    

    return 0;
}


