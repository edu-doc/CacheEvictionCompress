package model;

import java.util.ArrayList;
import java.util.Random;

public class Cache {

    private static final int CAPACIDADE_MAXIMA = 20; // Capacidade máxima da tabela
    private int M; // Tamanho da tabela (fixo em 20)
    private int n; // Número de elementos na tabela
    public ArrayList<No>[] tabela;
    private Random random;

    public Cache(int tamanho) {
        if (tamanho > CAPACIDADE_MAXIMA) {
            throw new IllegalArgumentException("O tamanho da tabela não pode exceder " + CAPACIDADE_MAXIMA);
        }
        this.M = tamanho;
        this.n = 0;
        this.tabela = new ArrayList[this.M];
        this.random = new Random();
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
        if (n >= CAPACIDADE_MAXIMA) {
            removerAleatorio(); // Remove um elemento aleatório se a tabela estiver cheia
        }

        int indice = calcularHash(no.order.codigoServico);
        ArrayList<No> lista = this.tabela[indice];

        // Verifica duplicatas
        for (No node : lista) {
            if (node.order.codigoServico == no.order.codigoServico) {
                System.out.println("Elemento já existe na tabela.");
                return; // Não insere duplicata
            }
        }

        lista.add(no); // Adiciona o novo nó
        n++;
    }

    private void removerAleatorio() {
        int indiceTabela = random.nextInt(M); // Sorteia um índice da tabela
        while (tabela[indiceTabela].isEmpty()) {
            indiceTabela = random.nextInt(M); // Garante que não seja uma lista vazia
        }

        ArrayList<No> lista = tabela[indiceTabela];
        int indiceLista = random.nextInt(lista.size()); // Sorteia um índice na lista

        No noRemovido = lista.remove(indiceLista); // Remove o nó
        n--;
        System.out.println("Elemento removido: " + noRemovido.order.codigoServico);
    }

    public No buscar(int chave, Banco banco) {
        // Primeiro, verifica na cache
        int indice = calcularHash(chave);
        ArrayList<No> lista = this.tabela[indice];
    
        for (No node : lista) {
            if (node.order.codigoServico == chave) {
                System.out.println("Elemento encontrado na cache: " + chave);
                return node; // Retorna o nó encontrado na cache
            }
        }
    
        // Se não encontrado na cache, busca no banco
        No noDoBanco = banco.buscar(chave);
        if (noDoBanco != null) {
            // Adiciona à cache
            inserir(noDoBanco);
            System.out.println("Elemento encontrado no banco e adicionado à cache: " + chave);
            return noDoBanco; // Retorna o nó encontrado no banco
        }
    
        System.out.println("Elemento não encontrado em nenhum lugar: " + chave);
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
                System.out.println("Elemento atualizado: " + novoNo.order.codigoServico);
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }
        System.out.println("Elemento não encontrado para atualização.");
        return false; // Retorna false se o nó não foi encontrado para atualização
    }
    

    public void imprimirCache() {
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

    private void printarNo(No no) {
        System.out.println("=================");
        System.out.println("Código: " + no.order.codigoServico);
        System.out.println("Nome: " + no.order.nome);
        System.out.println("Descrição: " + no.order.descricao);
        System.out.println("Hora: " + no.order.hora);
    }

    public No sortearElemento() {
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

    public boolean estaCheia() {
        return n >= CAPACIDADE_MAXIMA;
    }

    public int capacidadeMaxima() {
        return CAPACIDADE_MAXIMA;
    }
}
