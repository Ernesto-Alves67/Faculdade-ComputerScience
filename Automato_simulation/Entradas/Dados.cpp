#include "Dados.h"
#include <regex>
using namespace std;


/* === TIPS === */

/*
    Implementar as leituras para a defini��o do Automato.
    Implementar as leituras para processamento das entradas.
*/





Automato::Automato(): adjacencyList(){}

void Automato::addAresta(const string& u, string& v, string& peso){
    adjacencyList[u].push_back(std::make_pair(v, peso));
}

void Automato::addVertice(const string& vertice) {
    adjacencyList[vertice] = std::vector<std::pair<std::string, std::string>>();
}

void Automato::printAuto() {
    
    for (const auto& pair : adjacencyList) {
        std::cout << "Vertice " << pair.first << ": ";
        for (const auto& neighbor : pair.second) {
            std::cout << neighbor.first << " (Peso: " << neighbor.second << ") ";
        }
        std::cout << std::endl;
    }
}

bool Automato::vertexExists(const string& vertice) {
    for (const auto& pair : adjacencyList) {
        if (pair.first == vertice) {
            return true; // O v�rtice j� existe no grafo
        }
    }
    return false;
}



///////////// Configura��o e teste do automato
void configAutomato(string nomeArquivo, Automato* autoDetermi) {
    //nomeArquivo = "exemplo.txt"; // Nome do arquivo a ser lido

    // Crie um objeto ifstream para ler o arquivo
    std::ifstream inputFile(nomeArquivo);

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }

    regex patLinha1("\\|(.+)");
    smatch matches;

    std::string line;
    int linhasLidas = 0;
    string valorA1 = "";
    string valorA2 = "";
    vector<string> vetorDeVertices;

    // Use um loop para ler cada linha do arquivo
    while (std::getline(inputFile, line)) {
        if (regex_search(line, matches, patLinha1)) {
            //cout << "entrou0";
            if (matches.size() > 1 && linhasLidas==0) {
                cout << "Custo Arestas\t";
                valorA1 = matches[1].str()[1]; // Obt�m a parte ap�s os dois pontos como uma string
                valorA2 = matches[1].str()[4];
                std::cout << valorA1 << " "<< valorA2 << std::endl;
                
                linhasLidas++;
            }
            else {
                string valorE0 = line.substr(0, 2);
                
                vetorDeVertices.push_back(valorE0);
                string valorE1 = matches[1].str().substr(1, 2);// Obt�m a parte ap�s os dois pontos como uma string
                vetorDeVertices.push_back(valorE1);
                string valorE2 = matches[1].str().substr(4, 2);
                vetorDeVertices.push_back(valorE2);
                //std::cout << valorE0 << valorE1 << " " << valorE2 << std::endl;
                autoDetermi->addAresta(valorE0, valorE1, valorA1);
                autoDetermi->addAresta(valorE0, valorE2, valorA2);
                //########## Adicionando vertices(estados) no automato
                for (int i = 0; i < vetorDeVertices.size(); i++) {
                    if (!autoDetermi->vertexExists(vetorDeVertices[i])) {
                        autoDetermi->addVertice(vetorDeVertices[i]); // Adiciona o v�rtice somente se ele n�o existir
                    }
                    else {
                        //std::cout << "O vertice j� existe no grafo." << std::endl;
                        continue;
                    }
                }

                //########## Adicionando as conecoes ao automato
                
                
            }
        }
        // Fa�a algo com a linha lida, por exemplo, imprima-a
        //std::cout << line << std::endl;
    }
    cout << "Exibindo Arestas do automato \n";
    autoDetermi->printAuto();
    // Feche o arquivo quando terminar de ler
    inputFile.close();


}
///////////// Configura��o das entradas
void testAutomato(std::string nomeArquivo, Automato* autoDetermi){
    std::ifstream inputFile(nomeArquivo);

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }
    std::string line;


    while (std::getline(inputFile, line)) {
        cout << line << endl;
    }
}
