public class Cache {
    private ListaAutoAjustavel listaCache;
    private static final int CAPACIDADE_CACHE = 30; // Definindo capacidade para a cache

    public Cache() {
        this.listaCache = new ListaAutoAjustavel(CAPACIDADE_CACHE); // Usar o construtor padrão
    }

    public ServiceOrder buscarOrdemServico(int codigoServico, Banco banco) throws MyPickException {
        // Verifica se já está na cache
        for (int i = 0; i < listaCache.tamanho(); i++) {
            ServiceOrder orderAtual = (ServiceOrder) listaCache.obter(i);
            if (orderAtual != null && orderAtual.codigoServico == codigoServico) {
                // Remove o elemento encontrado da posição atual
                listaCache.remover(i);
                // Adiciona novamente na primeira posição
                listaCache.adicionar(orderAtual);
                System.out.println("Ordem de Serviço encontrada na cache e movida para o topo.");
                return orderAtual; // Retorna o que já estava na cache
            }
        }
    
        // Busca no banco se não está na cache
        ServiceOrder orderBanco = banco.buscar(codigoServico);
        if (orderBanco != null) {
            // Adiciona o nó encontrado na cache
            if (listaCache.tamanho() >= CAPACIDADE_CACHE) {
                // Remove o primeiro elemento (ou o que você definir como menos prioritário)
                listaCache.remover(0); // Remove o elemento mais antigo
            }
            listaCache.adicionar(orderBanco); // Adiciona o novo elemento
            System.out.println("Ordem de Serviço adicionada à cache.");
            return orderBanco;
        }
    
        throw new MyPickException("Ordem de Serviço não encontrada no banco para o código: " + codigoServico);
    }

    public boolean atualizarOrdemServico(int codigo, String nome, String descricao) throws MyPickException {
        for (int i = 0; i < listaCache.tamanho(); i++) {
            ServiceOrder orderAtual = (ServiceOrder) listaCache.obter(i);
            if (orderAtual != null && orderAtual.codigoServico == codigo) {
                // Atualiza os atributos da ordem de serviço existente
                orderAtual.codigoServico = codigo; // Supondo que existe um método setCodigoServico
                orderAtual.nome = nome;             // Supondo que existe um método setNome
                orderAtual.descricao = descricao;   // Supondo que existe um método setDescricao

                // Remove o nó antigo
                listaCache.remover(i);
                // Adiciona o nó atualizado
                listaCache.adicionar(orderAtual);
                System.out.println("Ordem de Serviço atualizada na cache.");
                return true; // Atualização bem-sucedida
            }
        }

        throw new MyPickException("Ordem de Serviço não encontrada na cache para atualização com o código: " + codigo);
    }

    public boolean removerOrdemServico(int codigoServico) throws MyPickException {
        for (int i = 0; i < listaCache.tamanho(); i++) {
            ServiceOrder orderAtual = (ServiceOrder) listaCache.obter(i);
            if (orderAtual != null && orderAtual.codigoServico == codigoServico) {
                // O nó correspondente foi encontrado, removendo da cache
                listaCache.remover(i); // Remove o nó com a ordem de serviço
                System.out.println("Ordem de Serviço removida da cache.");
                return true; // Remoção bem-sucedida
            }
        }

        throw new MyPickException("Ordem de Serviço não encontrada na cache para remoção com o código: " + codigoServico);
    }

    public ListaAutoAjustavel getListaCache() {
        return listaCache;
    }

    public void imprimirCache() {
        for (int i = 0; i < listaCache.tamanho(); i++) {
            ServiceOrder order = (ServiceOrder) listaCache.obter(i);
            System.out.println("Ordem de Serviço - Código: " + order.codigoServico);
        }
    }

    public int tamanhoCache() {
        return listaCache.tamanho();
    }
}
