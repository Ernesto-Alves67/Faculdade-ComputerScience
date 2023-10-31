#ifndef DADOS_H
#define DADOS_H
#pragma once
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
void configAutomato(std::string nomeArquivo);
void testAutomato(std::string nomeArquivo);

class Graph {
private:
    int V; // Número de vértices
    std::vector<std::vector<int>> adjacencyMatrix; // Matriz de adjacência
public:
    Graph(int vertice);
    void addEdge(int u, int v, int weight);
    void printMatrix();
};

class Automato {
private:
    std::vector<std::vector<std::pair<int, int>>> adjacencyList;

public:
    Automato();
    void addVertice(int u, int v, int peso);
    void printAuto();
};
#endif // DADOS_H
