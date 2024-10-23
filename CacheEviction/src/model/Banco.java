public class Banco {
    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    private ListaAutoAjustavel<ServiceOrder>[] tabela; // Usando ListaAutoAjustavel em vez de LinkedList

    @SuppressWarnings("unchecked")
    public Banco(int tamanho) {
        this.M = tamanho;
        this.n = 0;
        this.tabela = new ListaAutoAjustavel[this.M]; // Inicializa a tabela
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ListaAutoAjustavel<>(2); // Inicializa cada lista com capacidade inicial 2
        }
    }

    private int calcularHash(int chave) {
        int x = encontrarPrimoAnterior(this.M);
        return chave % x; // Cálculo do índice da tabela hash
    }

    public void inserir(ServiceOrder serviceOrder) {
        int indice = calcularHash(serviceOrder.codigoServico);
        ListaAutoAjustavel<ServiceOrder> lista = this.tabela[indice];

        // Evita duplicatas
        for (int i = 0; i < lista.tamanho(); i++) {
            if (lista.obter(i).codigoServico == serviceOrder.codigoServico) {
                return; // Se já existe, não insere
            }
        }

        lista.adicionar(serviceOrder); // Adiciona o novo ServiceOrder
        n++;
    }

    public ServiceOrder buscar(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel<ServiceOrder> lista = this.tabela[indice];

        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder serviceOrder = lista.obter(i);
            if (serviceOrder.codigoServico == chave) {
                return serviceOrder; // Retorna o ServiceOrder encontrado
            }
        }
        return null; // Não encontrado
    }

    public ServiceOrder remover(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel<ServiceOrder> lista = this.tabela[indice];

        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder serviceOrder = lista.obter(i);
            if (serviceOrder.codigoServico == chave) {
                lista.remover(i); // Remove o ServiceOrder da lista
                n--;
                return serviceOrder; // Retorna o ServiceOrder removido
            }
        }
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);
        ListaAutoAjustavel<ServiceOrder> lista = this.tabela[indice];

        for (int i = 0; i < lista.tamanho(); i++) {
            ServiceOrder serviceOrder = lista.obter(i);
            if (serviceOrder.codigoServico == codigoServico) {
                // Atualiza os atributos do ServiceOrder existente
                serviceOrder.nome = nome;                   // Atualiza o nome do serviço
                serviceOrder.descricao = descricao;         // Atualiza a descrição do serviço

                System.out.println("Service Order atualizado: " + codigoServico);
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }
        System.out.println("Service Order não encontrado para atualização.");
        return false; // Retorna false se o ServiceOrder não foi encontrado para atualização
    }

    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print(i + " -> ");
            ListaAutoAjustavel<ServiceOrder> lista = this.tabela[i];

            if (lista.tamanho() == 0) {
                System.out.println("null");
            } else {
                for (int j = 0; j < lista.tamanho(); j++) {
                    ServiceOrder serviceOrder = lista.obter(j);
                    System.out.print(serviceOrder.codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (ListaAutoAjustavel<ServiceOrder> lista : tabela) {
            for (int i = 0; i < lista.tamanho(); i++) {
                printarServiceOrder(lista.obter(i));
            }
        }
    }

    public double calcularFatorDeCarga() {
        double fatorDeCarga = (double) n / M;
        System.out.printf("Fator de carga atual: %.2f%n", fatorDeCarga);
        return fatorDeCarga;
    }

    public void redimensionarTabela(boolean aumentar) {
        ListaAutoAjustavel<ServiceOrder>[] tabelaAntiga = tabela;
        M = aumentar ? encontrarProximoPrimo(M * 2) : encontrarPrimoAnterior(M / 2);
        tabela = new ListaAutoAjustavel[M];
        inicializarTabela();

        for (ListaAutoAjustavel<ServiceOrder> lista : tabelaAntiga) {
            for (int i = 0; i < lista.tamanho(); i++) {
                inserir(lista.obter(i)); // Reinsere os elementos na nova tabela
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
        for (ListaAutoAjustavel<ServiceOrder> lista : tabela) {
            total += lista.tamanho();
        }
        return total;
    }

    private void printarServiceOrder(ServiceOrder serviceOrder) {
        System.out.println("Código: " + serviceOrder.codigoServico);
        System.out.println("Nome: " + serviceOrder.nome);
        System.out.println("Descrição: " + serviceOrder.descricao);
        System.out.println("Hora: " + serviceOrder.hora);
    }

    public int tamanho() {
        return this.n;
    }
}
