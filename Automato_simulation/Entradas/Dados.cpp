#include "Dados.h"
#include <regex>
using namespace std;


/* === TIPS === */

/*
    Implementar as leituras para a definição do Automato.
    Implementar as leituras para processamento das entradas.
*/

Graph::Graph(int vertices) : V(vertices) {
    // Inicialize a matriz de adjacência com todos os valores como -1 (sem aresta)
    adjacencyMatrix.resize(V, vector<int>(V, -1));
}

// Adicione uma aresta ponderada entre os vértices u e v
void Graph::addEdge(int u, int v, int weight) {
    adjacencyMatrix[u][v] = weight;
    adjacencyMatrix[v][u] = weight; // Para grafos não direcionados
}

// Exiba a matriz de adjacência
void Graph::printMatrix() {
    for (int i = 0; i < V; i++) {
        cout << "[v" << i << "]";
        for (int j = 0; j < V; j++) {
            if (adjacencyMatrix[i][j] == -1) {
                cout << "\t0"; // 0 indica nenhuma aresta
            }
            else {

                cout << "\t" << adjacencyMatrix[i][j];
            }
        }
        cout << endl;
    }
}

Automato::Automato(){}

void Automato::addVertice(int u, int v, int peso){
    if (u >= 0 && u < adjacencyList.size() && v >= 0 && v < adjacencyList.size()) {
        adjacencyList[u].push_back(std::make_pair(v, peso)); // Aresta direcionada de u para v com peso
    }
    else {
        std::cerr << "Vértices inválidos." << std::endl;
    }
}

void Automato::printAuto() {
    for (int i = 0; i < adjacencyList.size(); i++) {
        std::cout << "Vértice " << i << ": ";
        for (const auto& edge : adjacencyList[i]) {
            std::cout << edge.first << " (Peso: " << edge.second << ") ";
        }
        std::cout << std::endl;
    }
}
void configAutomato(string nomeArquivo) {
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
    // Use um loop para ler cada linha do arquivo
    while (std::getline(inputFile, line)) {
        if (regex_search(line, matches, patLinha1)) {
            //cout << "entrou0";
            if (matches.size() > 1 && linhasLidas==0) {
                cout << "Custo Arestas\t";
                char valorA1 = matches[1].str()[1]; // Obtém a parte após os dois pontos como uma string
                char valorA2 = matches[1].str()[4];
                std::cout << valorA1 << " "<< valorA2 << std::endl;
                
                linhasLidas++;
            }
            else {
                string valorE0 = line.substr(0, 2);
                cout << "Estados\t";
                cout << valorE0 << "\t"<< endl;
                string valorE1 = matches[1].str().substr(1, 2);; // Obtém a parte após os dois pontos como uma string
                string valorE2 = matches[1].str().substr(4, 2);
                std::cout << valorE1 << " " << valorE2 << std::endl;
            }
        }
        // Faça algo com a linha lida, por exemplo, imprima-a
        //std::cout << line << std::endl;
    }

    // Feche o arquivo quando terminar de ler
    inputFile.close();


}


