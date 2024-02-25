#include "Dados.h"
#include <regex>
#include <algorithm>
using namespace std;
/* === TIPS === */

/*
    Implementar as leituras para a definição do Automato.
    Implementar as leituras para processamento das entradas.
*/

/*  Nos arquivos teste do professor
*   o simbolo '>' marca o estado INICIAL
*   simbolo '*' marca o estado FINAL.

*/
//========================================== Automato Determministico
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
        else{
            if (entrada == transicoes[1].second) {
                processados = processados + transicoes[1].second;
                return estado2;
            }
        }
        
    }
    else {
        return "Entrada n pode ser processada.";
    }
    return "";
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

///////////// Configuração e teste do automato Deterministico
/*
void configAutomato(string nomeArquivo, Automato* autoDetermi) {
    //nomeArquivo = "exemplo.txt"; // Nome do arquivo a ser lido

    // Crie um objeto ifstream para ler o arquivo
    std::ifstream inputFile(nomeArquivo);

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }

    //regex patLinha1("\\|(.+)");
    //smatch matches;

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
                cout << "Alfabeto da Linguagem\t";
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
    
    autoDetermi->printAuto();
    // Feche o arquivo quando terminar de ler
    inputFile.close();


}

*/

void configAutomato(string nomeArquivo, Automato* autoDetermi) {
    // Crie um objeto ifstream para ler o arquivo
    std::ifstream inputFile(nomeArquivo);

    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }

   

    std::string line;
    int linhasLidas = 0;
    string valorAresta1 = "";
    string valorAresta2 = "";
    vector<string> vetorDeVertices;

    while (std::getline(inputFile, line)) {
        if (line != " ") {
            //cout << "entrou0";
            if (linhasLidas == 0 && line.size() >= 2) {
                cout << "Alfabeto da Linguagem\t";
                valorAresta1 = line[2]; // Obtém a parte após os dois pontos como uma string
                valorAresta2 = line[5];
                std::cout << valorAresta1 << "\t" << valorAresta2 << std::endl;

                linhasLidas++;
            }
            else {
                //cout << line << endl;
                size_t pos = line.find(">*"); //inicial-final
                size_t inicial = line.find(">"); // inicial
                size_t final = line.find("*");
                if (pos != std::string::npos) {
                    std::cout << "A sequência '>*' foi encontrada na posição: " << pos << std::endl;
                    /*cout << "" << line.substr(5, 2) << endl;
                    cout << "" << line.substr(9, 2) << endl;
                    cout << "" << line.substr(2, 2) << endl;*/
                    //break;
                    string valorE0 = line.substr(2, 2);

                    vetorDeVertices.push_back(valorE0);
                    string valorE1 = line.substr(5, 2);// Obtém a parte após os dois pontos como uma string
                    vetorDeVertices.push_back(valorE1);
                    string valorE2 = line.substr(9, 2);
                    vetorDeVertices.push_back(valorE2);
                    //std::cout << valorE0 << valorE1 << " " << valorE2 << std::endl;
                    autoDetermi->addAresta(valorE0, valorE1, valorAresta1);
                    autoDetermi->addAresta(valorE0, valorE2, valorAresta2);
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

                }
                else {
                    //caso " >q0 ";
                    if (inicial != std::string::npos) {
                        cout << "Caso  >q0" << endl;
                        string valorE0 = line.substr(1, 2);

                        vetorDeVertices.push_back(valorE0);
                        string valorE1 = line.substr(5, 2);// Obtém a parte após os dois pontos como uma string
                        vetorDeVertices.push_back(valorE1);
                        string valorE2 = line.substr(9, 2);
                        vetorDeVertices.push_back(valorE2);
                        std::cout << valorE0 << " " << valorE1 << " " << valorE2 << std::endl;
                        
                        autoDetermi->addAresta(valorE0, valorE1, valorAresta1);
                        autoDetermi->addAresta(valorE0, valorE2, valorAresta2);
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
                        continue;
                    }
                    
                    if (final != std::string::npos) {
                        cout << "Caso  *qn" << endl;
                        string valorE0 = line.substr(1, 2);

                        vetorDeVertices.push_back(valorE0);
                        string valorE1 = line.substr(5, 2);// Obtém a parte após os dois pontos como uma string
                        vetorDeVertices.push_back(valorE1);
                        string valorE2 = line.substr(9, 2);
                        vetorDeVertices.push_back(valorE2);
                        std::cout << valorE0 << " " << valorE1 << " " << valorE2 << std::endl;
                        
                        autoDetermi->addAresta(valorE0, valorE1, valorAresta1);
                        autoDetermi->addAresta(valorE0, valorE2, valorAresta2);
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
                    }
                    else {
                        // caso Linha normal
                        cout << "caso normal" << endl;
                        string valorE0 = line.substr(0, 2);

                        vetorDeVertices.push_back(valorE0);
                        string valorE1 = line.substr(4, 2);// Obtém a parte após os dois pontos como uma string
                        vetorDeVertices.push_back(valorE1);
                        string valorE2 = line.substr(8, 2);
                        vetorDeVertices.push_back(valorE2);
                        std::cout << valorE0 << " " << valorE1 << " " << valorE2 << std::endl;
                        
                        autoDetermi->addAresta(valorE0, valorE1, valorAresta1);
                        autoDetermi->addAresta(valorE0, valorE2, valorAresta2);
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
                    }

                    
                }
                linhasLidas++;

            }
        }
        // Faça algo com a linha lida, por exemplo, imprima-a
        //std::cout << line << std::endl;
    }
    inputFile.close();
}

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
        }
        autoDetermi->entrada_avaliada = " ";
        autoDetermi->processados = " ";
    }
    cout << "Linhas processadas: " << numeroLinhas << endl;
    inputFile.close();
}

//========================================== Automato Determministico
// ===================== Configuração e teste do AUTOMATO NÃO DETERMINISTICO
AutoND::AutoND() : adjacencyList() {}

bool AutoND::DFSUtil(const std::string& atual, const std::string& entradaRestante, std::set<std::string>& visitados) {
    // Verifica se chegou a um estado final e se toda a entrada foi processada
    /*if (atual == estado_final && entradaRestante.empty()) {
        std::cout << "Chegou a um estado final." << std::endl;
        return true;
    }*/

    // Processar o estado atual, se necessário
    std::cout << "Visitando estado: " << atual << " | Entrada restante: " << entradaRestante << std::endl;
    visitados.insert(atual);

    for (const auto& transicao : adjacencyList[atual]) {
        std::string proximoEstado = transicao.first;
        std::string simboloTransicao = transicao.second;

        // Verifica se a transição é possível
        if (!entradaRestante.empty() && entradaRestante.substr(0, simboloTransicao.size()) == simboloTransicao) {
            std::string novaEntrada = entradaRestante.substr(simboloTransicao.size());

            std::pair<std::string, std::string> novoEstadoEntrada = { proximoEstado, novaEntrada };

            if (visitados.find(novoEstadoEntrada.first) == visitados.end()) {
                if (DFSUtil(novoEstadoEntrada.first, novoEstadoEntrada.second, visitados)) {
                    return true;
                }
            }
        }
    }

    return false;
}

// Função de chamada para DFS com verificações adicionais
void AutoND::DFS(string& entrada) {
    std::set<std::string> visitados;
    bool resultado = DFSUtil(estado_inicial, entrada_avaliada, visitados);

    if (!resultado) {
        std::cout << "Não chegou a um estado final." << std::endl;
        
    }
    
}

void AutoND::addVertice(const string& vertice) {
    adjacencyList[vertice] = std::vector<std::pair<std::string, std::string>>();
}
void AutoND::addAresta(const string& u, string& v, string& peso){
    adjacencyList[u].push_back(std::make_pair(v, peso));
}
void AutoND::printAutoND() {
    cout << "entrou" << endl;
    for (const auto& pair : adjacencyList) {
        std::cout << "Vertice " << pair.first << ": ";
        for (const auto& neighbor : pair.second) {
            std::cout << neighbor.first << " (Peso: " << neighbor.second << ") ";
        }
        std::cout << std::endl;
    }
}
void configAutoND(std::string nomeArquivo, AutoND* nonDetermi) {
    std::ifstream inputFile(nomeArquivo);
    if (!inputFile) {
        std::cerr << "Erro ao abrir o arquivo." << std::endl;
        return;
    }

    std::string line;
    int linhasLidas = 0;
    int state_lidos = 0;
    string valorAresta1 = "";
    string valorAresta2 = "";
    string valorE0 = "";
    string valorE1 = "";
    string valorE2 = "";
    string valorE3 = "";
    vector<string> vetorDeVertices;
    size_t pos = 184; //inicial-final
    size_t inicial = 184;  // inicial
    size_t final = 184;

    while (std::getline(inputFile, line)) {
        pos = (line.find(">*") != 0) ? 9 : 1;
        inicial = (line.find(">") != 0) ? 9 : 1;
        final = (line.find("*") != 0) ? 9 : 1;

        if (line != " ") {
            if (linhasLidas == 0 && line.size() >= 2) {
                cout << "Alfabeto da Linguagem\t";
                valorAresta1 = line[2]; // Obtém a parte após os dois pontos como uma string
                valorAresta2 = line[6];
                std::cout << valorAresta1 << "\t" << valorAresta2 << std::endl;
                linhasLidas++;
            }
            else {
                if (pos == 1) {
                    cout << "ini-fim" << endl;
                    continue;
                }
                if (final == 1) {
                    cout << "fim" << endl;
                    continue;
                }
                if (inicial == 1) {
                    cout << "inicial" << endl;
                    std::regex pattern("q[0-9]"); // Expressão regular para encontrar padrões "q" seguido de um dígito
                    std::smatch match;
                    state_lidos = 0;
                    // Procura pelo padrão na linha
                loop_1:
                    while (std::regex_search(line, match, pattern)) {
                        // Obtém a posição da ocorrência atual

                        size_t match_pos = match.position();

                        if (match_pos == 1 && (state_lidos == 0)) {
                            state_lidos++;
                            valorE0 = line.substr(match_pos, 2);
                            //vetorDeVertices.push_back(valorE0);
                            line = match.suffix();
                            goto loop_1;
                        }

                        if ((match_pos == 1 || match_pos == 3) && state_lidos <2) {
                            state_lidos++;
                            valorE1 = line.substr(match_pos, 2);

                            line = match.suffix();
                            goto loop_1;

                        }
                        else {
                            cout << match_pos << "\t " << line.substr(match_pos, 2) << endl;
                            switch (state_lidos) {
                            case 1:
                                valorE2 = line.substr(match_pos, 2);
                                break;
                            case 2:
                                if(match_pos == 1){ 
                                    valorE2 = line.substr(match_pos, 2); 
                                }
                                else {
                                    valorE3 = line.substr(match_pos, 2);
                                }
                                
                                break;
                            default:
                                std::cout << "Opção inválida." << std::endl;
                                break;
                            }
                            line = match.suffix();
                            
                        }
                    }
                
                    nonDetermi->addAresta(valorE0, valorE1, valorAresta1);
                    nonDetermi->addAresta(valorE0, valorE2, valorAresta1);
                    nonDetermi->addAresta(valorE0, valorE3, valorAresta2);
                    //########## Adicionando vertices(estados) no automato
                    for (int i = 0; i < vetorDeVertices.size(); i++) {
                        if (!nonDetermi->vertexExists(vetorDeVertices[i])) {
                            nonDetermi->addVertice(vetorDeVertices[i]); // Adiciona o vértice somente se ele não existir
                        }
                        else {
                            //std::cout << "O vertice já existe no grafo." << std::endl;
                            continue;
                        }
                    }
                    continue;
                }
                else {
                    cout << "Normal" << endl;
                    std::regex pattern("q[0-9]"); // Expressão regular para encontrar padrões "q" seguido de um dígito
                    std::smatch match;
                    size_t pos = 0;
                    state_lidos = 0;
                    // Procura pelo padrão na linha
                loop:
                    while (std::regex_search(line, match, pattern)) {
                        // Obtém a posição da ocorrência atual

                        size_t match_pos = match.position();

                        if (match_pos == 0) {
                            state_lidos++;
                            valorE0 = line.substr(match_pos, 2);

                            line = match.suffix();
                            goto loop;
                        }

                        if (match_pos == 1) {
                            state_lidos++;
                            valorE1 = line.substr(match_pos, 2);

                            line = match.suffix();
                            goto loop;

                        }
                        else {
                            cout << match_pos << "\t " << line.substr(match_pos, 2) << endl;
                            switch (state_lidos) {
                            case 1:
                                valorE2 = line.substr(match_pos, 2);
                                break;
                            case 2:
                                valorE3 = line.substr(match_pos, 2);
                                break;
                            default:
                                std::cout << "Opção inválida." << std::endl;
                                break;
                            }
                            line = match.suffix();
                            goto loop;
                        }
                    }

                    nonDetermi->addAresta(valorE0, valorE1, valorAresta1);
                    nonDetermi->addAresta(valorE0, valorE2, valorAresta1);
                    nonDetermi->addAresta(valorE0, valorE3, valorAresta2);




                    std::cout << valorE0 << "\t " << valorE1 << " " << valorE2 << std::endl;


                    //########## Adicionando vertices(estados) no automato
                    for (int i = 0; i < vetorDeVertices.size(); i++) {
                        if (!nonDetermi->vertexExists(vetorDeVertices[i])) {
                            nonDetermi->addVertice(vetorDeVertices[i]); // Adiciona o vértice somente se ele não existir
                        }
                        else {
                            //std::cout << "O vertice já existe no grafo." << std::endl;
                            continue;
                        }
                    }
                    continue;
                }
            }
            linhasLidas++;
        }
    } // Fecha configAutoND
}

bool AutoND::vertexExists(const std::string& vertice) { return false; }
void testAutoND(std::string nomeArquivo, AutoND* autoDetermi) {
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

            char caractere = autoDetermi->entrada_avaliada[i];
            visitados.push_back("q0");

            if (to_string(caractere) != "10" && visitados.back() != "qf") {
                string mys{ caractere };
                //cout << i << " ASCii: " << to_string(caractere) << " " + mys << endl;

                visitados.push_back(estado);
                autoDetermi->estado_atual = estado;
            }
            else {
                if (autoDetermi->estado_atual == "qf") {
                    cout << " >Aceita" << endl;
                }
                else { cout << " >Recusada" << endl; }


            }
        }
        autoDetermi->entrada_avaliada = " ";
        autoDetermi->processados = " ";
    }
}


//========================================== Automato Não Determministico c/ transição vazia 