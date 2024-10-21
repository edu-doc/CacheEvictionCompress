import java.util.LinkedList;
import java.util.Random;

public class Banco {

    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    public LinkedList<No>[] tabela;

    public Banco(int tamanho) {
        this.M = tamanho;
        this.n = 0;
        this.tabela = new LinkedList[this.M];
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new LinkedList<>();
        }
    }

    private int calcularHash(int chave) {
        int x = encontrarPrimoAnterior(this.M);
        return chave % x;
    }

    public void inserir(No no) {
        int indice = calcularHash(no.getServiceOrder().getCodigoServico());
        LinkedList<No> lista = this.tabela[indice];

        // Evita duplicatas
        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == no.getServiceOrder().getCodigoServico()) {
                return; // Se já existe, não insere
            }
        }

        lista.add(no); // Adiciona o novo nó
        n++;
    }

    public No buscar(int chave) {
        int indice = calcularHash(chave);
        LinkedList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == chave) {
                return node; // Retorna o nó encontrado
            }
        }
        return null; // Não encontrado
    }

    public No remover(int chave) {
        int indice = calcularHash(chave);
        LinkedList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == chave) {
                lista.remove(node); // Remove o nó da lista
                n--;
                return node; // Retorna o nó removido
            }
        }
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);
        LinkedList<No> lista = this.tabela[indice];
    
        if (lista == null) {
            System.out.println("Nenhum Service Order encontrado");
            return false;
        }
    
        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == codigoServico) {
                // Atualiza os atributos do nó existente
                node.getServiceOrder().codigoServico = codigoServico; // Atualiza o código do serviço
                node.getServiceOrder().nome = nome;                   // Atualiza o nome do serviço
                node.getServiceOrder().descricao = descricao;         // Atualiza a descrição do serviço
    
                System.out.println("Service Order atualizado: " + codigoServico);
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }
        System.out.println("Service Order não encontrado para atualização.");
        return false; // Retorna false se o nó não foi encontrado para atualização
    }
    
    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print(i + " -> ");
            LinkedList<No> lista = this.tabela[i];

            if (lista.isEmpty()) {
                System.out.println("null");
            } else {
                for (No node : lista) {
                    System.out.print(node.getServiceOrder().codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (LinkedList<No> lista : tabela) {
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
        LinkedList<No>[] tabelaAntiga = tabela;
        M = aumentar ? encontrarProximoPrimo(M * 2) : encontrarPrimoAnterior(M / 2);
        tabela = new LinkedList[M];
        inicializarTabela();

        for (LinkedList<No> lista : tabelaAntiga) {
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
        for (LinkedList<No> lista : tabela) {
            total += lista.size();
        }
        return total;
    }

    private void printarNo(No no) {
        System.out.println("Código: " + no.getServiceOrder().codigoServico);
        System.out.println("Nome: " + no.getServiceOrder().nome);
        System.out.println("Descrição: " + no.getServiceOrder().descricao);
        System.out.println("Hora: " + no.getServiceOrder().hora);
    }

    public No sortearElemento() {
        Random random = new Random();
        No noSorteado = null;

        while (noSorteado == null) {
            int indiceTabela = random.nextInt(M);
            LinkedList<No> lista = tabela[indiceTabela];

            if (!lista.isEmpty()) {
                int indiceLista = random.nextInt(lista.size());
                noSorteado = lista.get(indiceLista);
            }
        }

        System.out.println("Service Order sorteado: ");
        printarNo(noSorteado);

        return noSorteado;
    }

    public int tamanho() {
        return this.n;
    }
}