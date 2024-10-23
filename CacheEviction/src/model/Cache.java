import java.util.Random;

public class Cache {

    ListaAutoAjustavel<No> tabela; // Usando a lista autoajustável
    private Random random;

    public Cache() {
        this.tabela = new ListaAutoAjustavel<>(30); // Capacidade inicial de 30
        this.random = new Random();
    }

    private void inserirCache(No no) {
        // Verifica se o elemento já existe e remove se necessário
        for (int i = 0; i < tabela.size(); i++) {
            if (tabela.get(i).order.codigoServico == no.order.codigoServico) {
                tabela.remove(i); // Remove o nó existente
                break; // Sai do loop após remover
            }
        }

        // Se a tabela estiver cheia, remove um nó aleatoriamente
        if (tabela.size() >= 30) {
            System.out.println("Tabela cheia. Removendo um Service Order aleatoriamente.");
            removerAleatorio(); // Remove um nó aleatoriamente
        }

        // Insere o novo nó na tabela
        tabela.add(no);
        System.out.println("Service Order inserido na cache: " + no.order.codigoServico);
    }

    private int removerAleatorio() {
        int indiceTabela;
        do {
            indiceTabela = random.nextInt(tabela.size()); // Sorteia um índice da tabela
        } while (tabela.get(indiceTabela) == null); // Garante que não seja um índice vazio

        No noRemovido = tabela.get(indiceTabela); // Guarda o nó a ser removido
        tabela.remove(indiceTabela); // Remove o nó
        System.out.println("Service Order removido: " + noRemovido.order.codigoServico);
        
        return indiceTabela; // Retorna o índice do nó removido
    }

    public No buscar(int codigoServico, Banco banco) {
        // Primeiro, verifica se o elemento está na cache
        for (int i = 0; i < tabela.size(); i++) {
            if (tabela.get(i) != null && tabela.get(i).order.codigoServico == codigoServico) {
                return tabela.get(i); // Retorna o nó encontrado na cache
            }
        }

        // Se não encontrado na cache, busca no banco
        No noDoBanco = banco.buscar(codigoServico);
        if (noDoBanco != null) {
            // Adiciona à cache somente se encontrado no banco
            inserirCache(noDoBanco);
            System.out.println("Service Order encontrado no banco e adicionado à cache, " + noDoBanco.order);
            return noDoBanco; // Retorna o nó encontrado no banco
        }

        System.out.println("Service Order não encontrado nem na cache nem no banco: " + codigoServico);
        return null; // Não encontrado
    }

    public No remover(int codigoServico) {
        for (int i = 0; i < tabela.size(); i++) {
            if (tabela.get(i) != null && tabela.get(i).order.codigoServico == codigoServico) {
                No noRemovido = tabela.get(i); // Guarda o nó a ser removido
                tabela.remove(i); // Remove o nó
                System.out.println("Service Order removido: " + noRemovido.order.codigoServico);
                return noRemovido; // Retorna o nó removido
            }
        }

        System.out.println("Service Order não encontrado para remoção.");
        return null; // Não encontrado
    }

    public boolean atualizar(int codigoServico, String nome, String descricao) {
        for (int i = 0; i < tabela.size(); i++) {
            if (tabela.get(i) != null && tabela.get(i).order.codigoServico == codigoServico) {
                // Atualiza os atributos do nó existente
                tabela.get(i).order.nome = nome; // Atualiza o nome do serviço
                tabela.get(i).order.descricao = descricao; // Atualiza a descrição do serviço
                return true; // Retorna true se a atualização foi bem-sucedida
            }
        }

        System.out.println("Service Order não encontrado.");
        return false; // Retorna false se o nó não foi encontrado para atualização
    }

    public void imprimirCache() {
        for (int i = 0; i < tabela.size(); i++) {
            System.out.print(i + " -> ");
            if (tabela.get(i) == null) {
                System.out.println("null");
            } else {
                System.out.println(tabela.get(i).order.codigoServico);
            }
        }
    }

    public int tamanho() {
        return tabela.size(); // Retorna o total de elementos na cache
    }

    public boolean estaCheia() {
        return tamanho() >= 30; // Mantém a lógica de verificação de cheia
    }

    public int capacidadeMaxima() {
        return 30; // Retorna a capacidade máxima definida
    }
}
