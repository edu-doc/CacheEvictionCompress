import java.util.HashMap;
import java.util.LinkedList;

public class Banco {

    private int M; // Tamanho da tabela hash
    private int n; // Número de elementos na tabela
    public LinkedList<No>[] tabela;
    private final double LIMITE_FATOR_CARGA = 0.75;

    public Banco() {
        this.M = 11;
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

    public void adicionarOrdemComprimida(HashMap<String, String> ordemComprimida) throws MyPickException {
        // Extrai os dados da ordem comprimida
        String codigoServicoStr = ordemComprimida.get("Código de Serviço");
        String nome = ordemComprimida.get("Nome");
        String descricao = ordemComprimida.get("Descrição");
        String hora = ordemComprimida.get("Hora");

        // Verifica se os dados da ordem comprimida são válidos
        if (codigoServicoStr == null || nome == null || descricao == null || hora == null) {
            throw new MyPickException("Dados da ordem comprimida inválidos.");
        }

        // Cria uma nova ordem de serviço com os dados fornecidos
        int codigoServico = Integer.parseInt(codigoServicoStr);
        ServiceOrder novaOrdem = new ServiceOrder(codigoServico, nome, descricao, hora);
        
        // A frequência inicial é 1 ao adicionar a nova ordem
        inserir(new No(novaOrdem, 1));
    }

    public void inserir(No no) {
        if (calcularFatorDeCarga() > LIMITE_FATOR_CARGA) {
            redimensionarTabela(true); // Aumentar o tamanho da tabela se necessário
        }

        int indice = calcularHash(no.getServiceOrder().getCodigoServico());
        LinkedList<No> lista = this.tabela[indice];

        // Evita duplicatas e move elementos já existentes para o início (autoajustável)
        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == no.getServiceOrder().getCodigoServico()) {
                lista.remove(node);  // Remove o nó antigo
                node.freq++; // Incrementa a frequência do nó existente
                lista.addFirst(node);  // Adiciona o nó atualizado no início
                return; // Se já existe, atualiza e não insere um novo
            }
        }

        lista.addFirst(no); // Adiciona o novo nó no início
        n++;
    }

    public No buscar(int chave) throws MyPickException {
        int indice = calcularHash(chave);
        LinkedList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == chave) {
                // Incrementa a frequência ao acessar a ordem de serviço
                node.freq++;
                // Move o nó acessado para o início (autoajustável)
                lista.remove(node);
                lista.addFirst(node);
                return node; // Retorna o nó encontrado
            }
        }
        throw new MyPickException("Service Order não encontrada com o código: " + chave);
    }

    public No remover(int chave) throws MyPickException {
        int indice = calcularHash(chave);
        LinkedList<No> lista = this.tabela[indice];

        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == chave) {
                lista.remove(node); // Remove o nó da lista
                n--;
                return node; // Retorna o nó removido
            }
        }
        throw new MyPickException("Service Order não encontrada para remoção com o código: " + chave);
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) throws MyPickException {
        int indice = calcularHash(codigoServico);
        LinkedList<No> lista = this.tabela[indice];

        if (lista == null || lista.isEmpty()) {
            throw new MyPickException("Nenhuma Service Order encontrada para atualização com o código: " + codigoServico);
        }

        for (No node : lista) {
            if (node.getServiceOrder().getCodigoServico() == codigoServico) {
                // Atualiza os atributos do nó existente
                node.getServiceOrder().setNome(nome);
                node.getServiceOrder().setDescricao(descricao);
                // Incrementa a frequência
                node.freq++;

                // Move o nó atualizado para o início da lista (autoajustável)
                lista.remove(node);
                lista.addFirst(node);

                System.out.println("Service Order atualizado: " + codigoServico);
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }
        throw new MyPickException("Service Order não encontrada para atualização com o código: " + codigoServico);
    }

    public void imprimirTabelaHash() {
        for (int i = 0; i < this.M; i++) {
            System.out.print(i + " -> ");
            LinkedList<No> lista = this.tabela[i];

            if (lista.isEmpty()) {
                System.out.println("null");
            } else {
                for (No node : lista) {
                    System.out.print(node.getServiceOrder().getCodigoServico() + " ");
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

    public void listarFrequencias() {
        for (LinkedList<No> lista : tabela) {
            for (No no : lista) {
                System.out.println("Código: " + no.getServiceOrder().getCodigoServico() + ", Frequência: " + no.freq);
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
        System.out.println("Código: " + no.getServiceOrder().getCodigoServico());
        System.out.println("Nome: " + no.getServiceOrder().getNome());
        System.out.println("Descrição: " + no.getServiceOrder().getDescricao());
        System.out.println("Hora: " + no.getServiceOrder().getHora());
        System.out.println("Frequência: " + no.freq); // Exibe a frequência
    }

    public int tamanho() {
        return this.n;
    }
}