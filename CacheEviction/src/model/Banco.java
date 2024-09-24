package model;

import java.util.ArrayList;
import java.util.Random;

public class Banco {

    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    public ArrayList<No>[] tabela;

    public Banco(int tamanho) {
        this.M = tamanho;
        this.n = 0;
        this.tabela = new ArrayList[this.M];
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ArrayList<>();
        }
    }

    private int calcularHash(int chave) {
        return chave % this.M;
    }

    public void inserir(No no) {
        int indice = calcularHash(no.order.codigoServico);
        ArrayList<No> lista = this.tabela[indice];

        // Evita duplicatas
        for (No node : lista) {
            if (node.order.codigoServico == no.order.codigoServico) {
                return; // Se já existe, não insere
            }
        }

        lista.add(no); // Adiciona o novo nó
        n++;
    }

    public No buscar(int chave) {
        int indice = calcularHash(chave);
        ArrayList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.order.codigoServico == chave) {
                return node; // Retorna o nó encontrado
            }
        }
        return null; // Não encontrado
    }

    public No remover(int chave) {
        int indice = calcularHash(chave);
        ArrayList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.order.codigoServico == chave) {
                lista.remove(node); // Remove o nó da lista
                n--;
                return node; // Retorna o nó removido
            }
        }
        return null; // Não encontrado
    }

    public boolean atualizar(No novoNo) {
        int indice = calcularHash(novoNo.order.codigoServico);
        ArrayList<No> lista = this.tabela[indice];
    
        for (int i = 0; i < lista.size(); i++) {
            No node = lista.get(i);
            if (node.order.codigoServico == novoNo.order.codigoServico) {
                // Atualiza os dados do nó existente
                lista.set(i, novoNo);
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }
        return false; // Retorna false se o nó não foi encontrado para atualização
    }
    

    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print("[ " + i + " ] --> ");
            ArrayList<No> lista = this.tabela[i];

            if (lista.isEmpty()) {
                System.out.println("null");
            } else {
                for (No node : lista) {
                    System.out.print(node.order.codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (ArrayList<No> lista : tabela) {
            for (No no : lista) {
                printarNo(no);
            }
        }
    }

    public double calcularFatorDeCarga() {
        double fatorDeCarga = (double) n / M;
        System.out.printf("Fator de carga atual: %.2f%n", fatorDeCarga);
        return fatorDeCarga;
    }

    public void redimensionarTabela(boolean aumentar) {
        System.out.println("Redimensionando tabela...");

        ArrayList<No>[] tabelaAntiga = tabela;
        M = aumentar ? encontrarProximoPrimo(M * 2) : encontrarPrimoAnterior(M / 2);
        tabela = new ArrayList[M];
        inicializarTabela();

        for (ArrayList<No> lista : tabelaAntiga) {
            for (No no : lista) {
                inserir(no); // Reinsere os elementos na nova tabela
            }
        }
        n = contarElementos();
    }

    private int encontrarProximoPrimo(int numero) {
        while (!ehPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    private int encontrarPrimoAnterior(int numero) {
        while (numero > 2) {
            if (ehPrimo(numero)) {
                return numero;
            }
            numero--;
        }
        return 2;
    }

    private boolean ehPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    private int contarElementos() {
        int total = 0;
        for (ArrayList<No> lista : tabela) {
            total += lista.size();
        }
        return total;
    }

    private void printarNo(No no) {
        System.out.println("=================");
        System.out.println("Código: " + no.order.codigoServico);
        System.out.println("Nome: " + no.order.nome);
        System.out.println("Descrição: " + no.order.descricao);
        System.out.println("Hora: " + no.order.hora);
    }

    public No sortearElemento() {
        Random random = new Random();
        No noSorteado = null;

        while (noSorteado == null) {
            int indiceTabela = random.nextInt(M);
            ArrayList<No> lista = tabela[indiceTabela];

            if (!lista.isEmpty()) {
                int indiceLista = random.nextInt(lista.size());
                noSorteado = lista.get(indiceLista);
            }
        }

        System.out.println("Elemento sorteado: ");
        printarNo(noSorteado);

        return noSorteado;
    }

    public int tamanho() {
        return this.n;
    }
}