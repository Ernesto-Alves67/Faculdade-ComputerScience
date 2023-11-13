#include "Dados.h"
#include <regex>
using namespace std;


/* === TIPS === */

/*
    Implementar as leituras para a definição do Automato.
    Implementar as leituras para processamento das entradas.
*/





Automato::Automato(): adjacencyList(){}

void Automato::addAresta(const string& u, string& v, string& peso){
    adjacencyList[u].push_back(std::make_pair(v, peso));
}

void Automato::addVertice(const string& vertice) {
    adjacencyList[vertice] = std::vector<std::pair<std::string, std::string>>();
}

string Automato::transicaoAutomato(string& entrada) {
    
    auto it = adjacencyList.find(estado_atual);
    if (it != adjacencyList.end() && !it->second.empty()) {
        const std::string& estadoAutomato = it->first;
        const std::vector<std::pair<string, string>>& transicoes = it->second;
        string estado1 = transicoes[0].first;
        string estado2 = transicoes[1].first;
        //std::cout << "\nEstado: " << estadoAutomato << " entry: " << entrada<<"\n";
        //std::cout << "  ir para: " << transicoes[0].first << " lendo: " << transicoes[0].second << std::endl;
        //std::cout << "  ir para: " << transicoes[1].first << " lendo: " << transicoes[1].second << std::endl;
        if (entrada == transicoes[0].second) {
            processados = processados + transicoes[0].second;
            return estado1;
        }
        if (entrada == transicoes[1].second) {
            processados = processados + transicoes[1].second;
            return estado2;
        }
    }
    else {
        return "Entrada n pode ser processada.";
    }
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
            return true; // O vértice já existe no grafo
        }
    }
    return false;
}



///////////// Configuração e teste do automato
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
                valorA1 = matches[1].str()[1]; // Obtém a parte após os dois pontos como uma string
                valorA2 = matches[1].str()[4];
                std::cout << valorA1 << " "<< valorA2 << std::endl;
                
                linhasLidas++;
            }
            else {
                string valorE0 = line.substr(0, 2);
                
                vetorDeVertices.push_back(valorE0);
                string valorE1 = matches[1].str().substr(1, 2);// Obtém a parte após os dois pontos como uma string
                vetorDeVertices.push_back(valorE1);
                string valorE2 = matches[1].str().substr(4, 2);
                vetorDeVertices.push_back(valorE2);
                //std::cout << valorE0 << valorE1 << " " << valorE2 << std::endl;
                autoDetermi->addAresta(valorE0, valorE1, valorA1);
                autoDetermi->addAresta(valorE0, valorE2, valorA2);
                //########## Adicionando vertices(estados) no automato
                for (int i = 0; i < vetorDeVertices.size(); i++) {
                    if (!autoDetermi->vertexExists(vetorDeVertices[i])) {
                        autoDetermi->addVertice(vetorDeVertices[i]); // Adiciona o vértice somente se ele não existir
                    }
                    else {
                        //std::cout << "O vertice já existe no grafo." << std::endl;
                        continue;
                    }
                }

                //########## Adicionando as conecoes ao automato
                
                
            }
        }
        // Faça algo com a linha lida, por exemplo, imprima-a
        //std::cout << line << std::endl;
    }
    
    //autoDetermi->printAuto();
    // Feche o arquivo quando terminar de ler
    inputFile.close();


}

///////////// Configuração das entradas
int contarLinhas(const std::string& nomeArquivo) {
    std::ifstream arquivo(nomeArquivo);

    if (!arquivo.is_open()) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return -1; // Retorna um valor negativo para indicar erro
    }

    int numeroLinhas = 0;
    std::string linha;

    while (std::getline(arquivo, linha)) {
        numeroLinhas++;
    }

    arquivo.close();
    return numeroLinhas;
}


void testAutomato(std::string nomeArquivo, Automato* autoDetermi){
    std::ifstream inputFile(nomeArquivo);

    int numeroLinhas = 0;
    std::vector<std::string> visitados;
    numeroLinhas = contarLinhas(nomeArquivo);

    

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }
    std::string line;
    std::string estado;

    
    while (std::getline(inputFile, line)) {
        visitados.clear();
        autoDetermi->entrada_avaliada = line + "\n";
        autoDetermi->estado_atual = autoDetermi->estado_inicial;

        cout << "Processando entrada => " << line;
        for (int i = 0; i < autoDetermi->entrada_avaliada.length(); ++i) {
            
            char caractere =autoDetermi->entrada_avaliada[i];
            visitados.push_back("q0");
            
            if (to_string(caractere) != "10" && visitados.back() != "qf") {
                string mys{ caractere };
                //cout << i << " ASCii: " << to_string(caractere) << " " + mys << endl;
                estado = autoDetermi->transicaoAutomato(mys);
                visitados.push_back(estado);
                autoDetermi->estado_atual = estado;
            }
            else {
                if (autoDetermi->estado_atual == "qf") {
                    cout << " >Aceita" << endl;
                }else{ cout << " >Recusada" << endl; }
                
                
            }
    
            /*if (autoDetermi->estado_atual != autoDetermi->estado_final && to_string(caractere) != "\n") {
                char proximo = autoDetermi->entrada_avaliada[i + 1];
                cout << "\nentry " << i << endl;
                
                if (to_string(proximo) == "\n") {
                    cout << "o barra n :" << proximo << endl;
                }
            }*/
            
            
        }
        autoDetermi->entrada_avaliada = " ";
        autoDetermi->processados = " ";
    }
    cout << "Linhas processadas: " << numeroLinhas << endl;
    inputFile.close();
}
