public class Banco {

    private int M; // Tamanho da tabela
    private int n; // Número de elementos na tabela
    private ListaAutoAjustavel<No>[] tabela; // Array de listas autoajustáveis

    public Banco(int tamanho) {
        this.M = tamanho;
        this.n = 0;
        this.tabela = new ListaAutoAjustavel[this.M];
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < this.M; i++) {
            this.tabela[i] = new ListaAutoAjustavel<>(10); // Capacidade inicial de 10 para cada lista
        }
    }

    private int calcularHash(int chave) {
        return chave % M; // Usar o tamanho M diretamente
    }

    public void inserir(No no) {
        int indice = calcularHash(no.order.codigoServico);
        ListaAutoAjustavel<No> lista = this.tabela[indice];

        // Evita duplicatas
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).order.codigoServico == no.order.codigoServico) {
                return; // Se já existe, não insere
            }
        }

        lista.add(no); // Adiciona o novo nó
        n++;
    }

    public No buscar(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel<No> lista = this.tabela[indice];

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).order.codigoServico == chave) {
                return lista.get(i); // Retorna o nó encontrado
            }
        }
        return null; // Não encontrado
    }

    public No remover(int chave) {
        int indice = calcularHash(chave);
        ListaAutoAjustavel<No> lista = this.tabela[indice];

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).order.codigoServico == chave) {
                No noRemovido = lista.remove(i); // Remove o nó da lista
                n--;
                return noRemovido; // Retorna o nó removido
            }
        }
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);
        ListaAutoAjustavel<No> lista = this.tabela[indice];
    
        for (int i = 0; i < lista.size(); i++) {
            No node = lista.get(i);
            if (node.order.codigoServico == codigoServico) {
                // Atualiza os atributos do nó existente
                node.order.nome = nome; // Atualiza o nome do serviço
                node.order.descricao = descricao; // Atualiza a descrição do serviço

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
            ListaAutoAjustavel<No> lista = this.tabela[i];

            if (lista.isEmpty()) {
                System.out.println("null");
            } else {
                for (int j = 0; j < lista.size(); j++) {
                    System.out.print(lista.get(j).order.codigoServico + " ");
                }
                System.out.println();
            }
        }
    }

    public void listarElementos() {
        for (ListaAutoAjustavel<No> lista : tabela) {
            for (int j = 0; j < lista.size(); j++) {
                printarNo(lista.get(j));
            }
        }
    }

    public double calcularFatorDeCarga() {
        double fatorDeCarga = (double) n / M;
        System.out.printf("Fator de carga atual: %.2f%n", fatorDeCarga);
        return fatorDeCarga;
    }

    public int tamanho() {
        return this.n;
    }

    private void printarNo(No no) {
        System.out.println("Código: " + no.order.codigoServico);
        System.out.println("Nome: " + no.order.nome);
        System.out.println("Descrição: " + no.order.descricao);
        System.out.println("Hora: " + no.order.hora);
    }
}
