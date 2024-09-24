import java.util.Random;

public class Cache {

    private static final int CAPACIDADE_MAXIMA = 20; // Capacidade máxima da tabela
    No[] tabela; // Array para armazenar os elementos
    private Random random;

    public Cache() {
        this.tabela = new No[CAPACIDADE_MAXIMA]; // Criação de um array de tamanho fixo
        this.random = new Random();
    }

    private int calcularHash(int chave) {
        return chave % CAPACIDADE_MAXIMA;
    }

    public void inserir(No no) {
        int indice = calcularHash(no.order.codigoServico);

        // Se já existe um nó no índice, removemos o existente antes de inserir o novo
        if (tabela[indice] != null) {
            System.out.println("Service Order já existe na tabela. Removendo existente: " + tabela[indice].order.codigoServico);
            tabela[indice] = null; // Remove o nó existente
        }

        // Se a tabela estiver cheia, remove um nó aleatoriamente e guarda a posição
        if (estaCheia()) {
            System.out.println("Tabela cheia. Removendo um Service Order aleatoriamente.");
            int indiceRemovido = removerAleatorio(); // Remove um nó aleatoriamente e retorna o índice
            // Insere o novo nó na posição do nó removido
            tabela[indiceRemovido] = no;
            System.out.println("Service Order inserido na cache na posição " + indiceRemovido + ": " + no.order.codigoServico);
        } else {
            // Insere o novo nó no índice calculado
            tabela[indice] = no;
            System.out.println("Service Order inserido na cache: " + no.order.codigoServico);
        }
    }

    private int removerAleatorio() {
        int indiceTabela;
        do {
            indiceTabela = random.nextInt(CAPACIDADE_MAXIMA); // Sorteia um índice da tabela
        } while (tabela[indiceTabela] == null); // Garante que não seja um índice vazio

        No noRemovido = tabela[indiceTabela]; // Guarda o nó a ser removido
        tabela[indiceTabela] = null; // Remove o nó
        System.out.println("Service Order removido: " + noRemovido.order.codigoServico);
        
        return indiceTabela; // Retorna o índice do nó removido
    }

    public No buscar(int chave, Banco banco) {
        int indice = calcularHash(chave);

        // Verifica se o elemento está na cache
        if (tabela[indice] != null && tabela[indice].order.codigoServico == chave) {
            System.out.println("Service Order encontrado na cache: " + chave);
            return tabela[indice]; // Retorna o nó encontrado na cache
        }

        // Se não encontrado na cache, busca no banco
        No noDoBanco = banco.buscar(chave);
        if (noDoBanco != null) {
            // Adiciona à cache
            inserir(noDoBanco);
            System.out.println("Service Order encontrado no banco e adicionado à cache: " + chave);
            return noDoBanco; // Retorna o nó encontrado no banco
        }

        System.out.println("Service Order não encontrado nem no banco nem na cache: " + chave);
        return null; // Não encontrado
    }

    public No remover(int chave) {
        int indice = calcularHash(chave);

        if (tabela[indice] != null && tabela[indice].order.codigoServico == chave) {
            No noRemovido = tabela[indice]; // Guarda o nó a ser removido
            tabela[indice] = null; // Remove o nó
            System.out.println("Service Order removido: " + noRemovido.order.codigoServico);
            return noRemovido; // Retorna o nó removido
        }

        System.out.println("Service Order não encontrado para remoção.");
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        int indice = calcularHash(codigoServico);

        if (tabela[indice] == null) {
            System.out.println("Nenhuma Service Order nesse índice.");
            return false;
        }

        No node = tabela[indice];
        if (node.order.codigoServico == codigoServico) {
            // Atualiza os atributos do nó existente
            node.order.nome = nome; // Atualiza o nome do serviço
            node.order.descricao = descricao; // Atualiza a descrição do serviço
            System.out.println("Service Order atualizado: " + codigoServico);
            return true; // Retorna true se a atualização foi bem-sucedida
        }

        System.out.println("Service Order não encontrado.");
        return false; // Retorna false se o nó não foi encontrado para atualização
    }

    public void imprimirCache() {
        for (int i = 0; i < CAPACIDADE_MAXIMA; i++) {
            System.out.print(i + " -> ");
            if (tabela[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(tabela[i].order.codigoServico);
            }
        }
    }

    public int tamanho() {
        int count = 0;
        for (No no : tabela) {
            if (no != null) count++;
        }
        return count; // Retorna o total de elementos na cache
    }

    public boolean estaCheia() {
        return tamanho() >= CAPACIDADE_MAXIMA;
    }

    public int capacidadeMaxima() {
        return CAPACIDADE_MAXIMA;
    }
}
