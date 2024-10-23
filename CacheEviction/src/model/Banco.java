public class Banco {

    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    public ListaAutoAjustavel[] tabela;

    public Banco(int tamanho) {
        this.M = tamanho;
        this.n = 0;
        this.tabela = new ListaAutoAjustavel[this.M];
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ListaAutoAjustavel(this.M); // Usando a lista autoajustável
        }
    }

    private int calcularHash(int chave) {
        int x = encontrarPrimoAnterior(this.M);
        return chave % x;
    }

    public void inserir(ServiceOrder order) {
        int indice = calcularHash(order.codigoServico);
        ListaAutoAjustavel lista = this.tabela[indice];

        // Evita duplicatas
        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder order2 = (ServiceOrder) lista.obter(i);
            if (order2.codigoServico == order.codigoServico) {
                return; // Se já existe, não insere
            }
        }

        lista.adicionar(order); // Adiciona o novo nó
        n++;
    }

    public ServiceOrder buscar(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel lista = this.tabela[indice];

        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder order = (ServiceOrder) lista.obter(i);
            if (order.codigoServico == chave) {
                return order; // Retorna o nó encontrado
            }
        }
        return null; // Não encontrado
    }

    public ServiceOrder remover(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel lista = this.tabela[indice];

        // Remover o elemento mais antigo
        ServiceOrder orderRemovido = (ServiceOrder) lista.obter(lista.tamanho() - 1);
        if (orderRemovido != null && orderRemovido.codigoServico == chave) {
            lista.remover(); // Remove o nó da lista
            n--;
            return orderRemovido; // Retorna o nó removido
        }
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);
        ListaAutoAjustavel lista = this.tabela[indice];

        if (lista == null) {
            System.out.println("Nenhum Service Order encontrado");
            return false;
        }

        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder order = (ServiceOrder) lista.obter(i);
            if (order.codigoServico == codigoServico) {
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
            ListaAutoAjustavel lista = this.tabela[i];

            if (lista.tamanho() == 0) {
                System.out.println("null");
            } else {
                for (int j = 0; j < lista.tamanho(); j++) {
                    ServiceOrder order = (ServiceOrder) lista.obter(j);
                    System.out.print(order.codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (ListaAutoAjustavel lista : tabela) {
            for (int j = 0; j < lista.tamanho(); j++) {
                ServiceOrder order = (ServiceOrder) lista.obter(j);
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
        ListaAutoAjustavel[] tabelaAntiga = tabela;
        M = aumentar ? encontrarProximoPrimo(M * 2) : encontrarPrimoAnterior(M / 2);
        tabela = new ListaAutoAjustavel[M];
        inicializarTabela();

        for (ListaAutoAjustavel lista : tabelaAntiga) {
            for (int j = 0; j < lista.tamanho(); j++) {
                ServiceOrder order = (ServiceOrder) lista.obter(j);
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
        for (ListaAutoAjustavel lista : tabela) {
            total += lista.tamanho();
        }
        return total;
    }

    private void printarOrder(ServiceOrder order) {
        System.out.println("Código: " + order.codigoServico);
        System.out.println("Nome: " + order.nome);
        System.out.println("Descrição: " + order.descricao);
        System.out.println("Hora: " + order.hora);
    }

    public int tamanho() {
        return this.n;
    }
}