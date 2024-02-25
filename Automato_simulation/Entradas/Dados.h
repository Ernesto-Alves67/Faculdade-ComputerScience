#ifndef DADOS_H
#define DADOS_H
#pragma once
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <set>
using std::string;

//========================================== Automato Determministico
class Automato {
private:
    std::map<std::string, std::vector<std::pair<string, string>>> adjacencyList;  // Lista de adjacencia que implementa o grafo
    
public:
    std::string estado_inicial = "q0";
    std::vector<std::string> estado_final = {};
    std::string estado_atual = " ";
    std::string entrada_avaliada = " ";
    std::string processados = " ";
    Automato();
    void addVertice(const string& vertice);
    void addAresta(const string& u, string& v, string& peso);
    void printAuto();
    bool vertexExists(const std::string& vertice);
    string transicaoAutomato(string& entrada);
};

void configAutomato(std::string nomeArquivo, Automato* autoDetermi);
void testAutomato(std::string nomeArquivo, Automato* autoDetermi);


//========================================== Automato Não determminitico
class AutoND {
private:
    std::map<std::string, std::vector<std::pair<string, string>>> adjacencyList;

public:
    std::string estado_inicial = " ";
    std::vector<std::string> estado_final = {};
    std::string estado_atual = " ";
    std::string entrada_avaliada = " ";
    std::string processados = " ";
    AutoND();
    void addVertice(const string& vertice);
    void addAresta(const string& u, string& v, string& peso);
    void printAutoND();
    bool vertexExists(const std::string& vertice);
    bool DFSUtil(const std::string& atual, const std::string& entradaRestante, std::set<std::string>& visitados);
    void DFS(string& entrada);
    string testAutoND(string& entrada);

};

void configAutoND(std::string nomeArquivo, AutoND* nonDetermi);

//========================================== Automato Não Determinisco Transição c/ Vazia
class Epsilon_NFA {

};

//========================================== Automato de Pilha
class AutoPilha {

};
#endif // DADOS_H
