#include "Leitura_Dados.h"

using namespace std;


/* === TIPS === */

/*
    Implementar as leituras para a definição do Automato.
    Implementar as leituras para processamento das entradas.
*/


void configAutomato(string nomeArquivo) {
    //nomeArquivo = "exemplo.txt"; // Nome do arquivo a ser lido

    // Crie um objeto ifstream para ler o arquivo
    std::ifstream inputFile(nomeArquivo);

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }

    std::string line;

    // Use um loop para ler cada linha do arquivo
    while (std::getline(inputFile, line)) {
        // Faça algo com a linha lida, por exemplo, imprima-a
        std::cout << line << std::endl;
    }

    // Feche o arquivo quando terminar de ler
    inputFile.close();


}
