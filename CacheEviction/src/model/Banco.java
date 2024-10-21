import java.util.LinkedList;
import java.util.Random;

public class Banco {

    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    public LinkedList<ServiceOrder>[] tabela;

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

    public void inserir(ServiceOrder sc) {
        int indice = calcularHash(sc.getCodigoServico());
        LinkedList<ServiceOrder> lista = this.tabela[indice];

        // Evita duplicatas
        for (ServiceOrder order : lista) {
            if (sc.getCodigoServico() == order.getCodigoServico()) {
                return; // Se já existe, não insere
            }
        }

        lista.add(sc); // Adiciona o novo nó
        n++;
    }

    public ServiceOrder buscar(int chave) {
        int indice = calcularHash(chave);
        LinkedList<ServiceOrder> lista = this.tabela[indice];

        for (ServiceOrder order : lista) {
            if (order.getCodigoServico() == chave) {
                return order; // Retorna o nó encontrado
            }
        }
        return null; // Não encontrado
    }

    public ServiceOrder remover(int chave) {
        int indice = calcularHash(chave);
        LinkedList<ServiceOrder> lista = this.tabela[indice];

        for (ServiceOrder order : lista) {
            if (order.getCodigoServico() == chave) {
                lista.remove(order); // Remove o nó da lista
                n--;
                return order; // Retorna o nó removido
            }
        }
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);
        LinkedList<ServiceOrder> lista = this.tabela[indice];
    
        if (lista == null) {
            System.out.println("Nenhum Service Order encontrado");
            return false;
        }
    
        for (ServiceOrder order : lista) {
            if (order.getCodigoServico() == codigoServico) {
                // Atualiza os atributos do nó existente
                order.codigoServico = codigoServico; // Atualiza o código do serviço
                order.nome = nome;                   // Atualiza o nome do serviço
                order.descricao = descricao;         // Atualiza a descrição do serviço
    
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
            LinkedList<ServiceOrder> lista = this.tabela[i];

            if (lista.isEmpty()) {
                System.out.println("null");
            } else {
                for (ServiceOrder order : lista) {
                    System.out.print(order.codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (LinkedList<ServiceOrder> lista : tabela) {
            for (ServiceOrder order : lista) {
                printarOrder(order);
            }
        }
    }

    public double calcularFatorDeCarga() {
        double fatorDeCarga = (double) n / M;
        System.out.printf("Fator de carga atual: %.2f%n", fatorDeCarga);
        return fatorDeCarga;
    }

    public void redimensionarTabela(boolean aumentar) {
        LinkedList<ServiceOrder>[] tabelaAntiga = tabela;
        M = aumentar ? encontrarProximoPrimo(M * 2) : encontrarPrimoAnterior(M / 2);
        tabela = new LinkedList[M];
        inicializarTabela();

        for (LinkedList<ServiceOrder> lista : tabelaAntiga) {
            for (ServiceOrder order : lista) {
                inserir(order); // Reinsere os elementos na nova tabela
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
        for (LinkedList<ServiceOrder> lista : tabela) {
            total += lista.size();
        }
        return total;
    }

    private void printarOrder(ServiceOrder sc) {
        System.out.println("Código: " + sc.codigoServico);
        System.out.println("Nome: " + sc.nome);
        System.out.println("Descrição: " + sc.descricao);
        System.out.println("Hora: " + sc.hora);
    }

    public ServiceOrder sortearElemento() {
        Random random = new Random();
        ServiceOrder scSorteado = null;

        while (scSorteado == null) {
            int indiceTabela = random.nextInt(M);
            LinkedList<ServiceOrder> lista = tabela[indiceTabela];

            if (!lista.isEmpty()) {
                int indiceLista = random.nextInt(lista.size());
                scSorteado = lista.get(indiceLista);
            }
        }

        System.out.println("Service Order sorteado: ");
        printarOrder(scSorteado);

        return scSorteado;
    }

    public int tamanho() {
        return this.n;
    }
}