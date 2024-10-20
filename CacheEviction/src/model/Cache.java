public class Cache {
    private FilaPrioridade<No> filaPrioridade;
    private static final int CAPACIDADE_CACHE = 30; // Definindo capacidade para a cache

    public Cache() {
        this.filaPrioridade = new FilaPrioridade<>(CAPACIDADE_CACHE);
    }

    public No buscarOrdemServico(int codigoServico, Banco banco) throws MyPickException {
        // Verifica se já está na fila de prioridade
        for (int i = 0; i < filaPrioridade.size(); i++) {
            No noAtual = filaPrioridade.peek();
            if (noAtual != null && noAtual.serviceOrder.getCodigoServico() == codigoServico) {
                System.out.println("Ordem de Serviço encontrada na cache.");
                return noAtual; // Retorna o que já está na fila
            }
            // Reordena a fila temporariamente
            filaPrioridade.enqueue(filaPrioridade.dequeue());
        }

        // Busca no banco se não está na cache
        No noBanco = banco.buscar(codigoServico);
        if (noBanco != null) {
            // Adiciona o nó encontrado na fila de prioridade
            if (filaPrioridade.size() >= CAPACIDADE_CACHE) {
                filaPrioridade.dequeue(); // Remove o menor (ou o de menor prioridade) para manter a capacidade
            }
            filaPrioridade.enqueue(noBanco);
            System.out.println("Ordem de Serviço adicionada à cache.");
            return noBanco;
        }

        throw new MyPickException("Ordem de Serviço não encontrada no banco para o código: " + codigoServico);
    }

    public boolean atualizarOrdemServico(int codigo, String nome, String descricao) throws MyPickException {
        for (int i = 0; i < filaPrioridade.size(); i++) {
            No noAtual = filaPrioridade.peek();
            if (noAtual != null && noAtual.serviceOrder.getCodigoServico() == codigo) {
                // Atualiza os atributos da ordem de serviço existente
                noAtual.serviceOrder.setNome(nome); // Supondo que exista um método setNome
                noAtual.serviceOrder.setDescricao(descricao); // Supondo que exista um método setDescricao
    
                // Reorganiza a fila de prioridade, pois a prioridade pode ter mudado
                filaPrioridade.dequeue(); // Remove o nó antigo
                filaPrioridade.enqueue(noAtual); // Adiciona o nó atualizado
                System.out.println("Ordem de Serviço atualizada na cache.");
                return true; // Atualização bem-sucedida
            }
            // Reordena a fila temporariamente
            filaPrioridade.enqueue(filaPrioridade.dequeue());
        }
    
        throw new MyPickException("Ordem de Serviço não encontrada na cache para atualização com o código: " + codigo);
    }

    public boolean removerOrdemServico(int codigoServico) throws MyPickException {
        for (int i = 0; i < filaPrioridade.size(); i++) {
            No noAtual = filaPrioridade.peek();
            if (noAtual != null && noAtual.serviceOrder.getCodigoServico() == codigoServico) {
                // O nó correspondente foi encontrado, removendo da fila
                filaPrioridade.dequeue(); // Remove o nó com a ordem de serviço
                System.out.println("Ordem de Serviço removida da cache.");
                return true; // Remoção bem-sucedida
            }
            // Reordena a fila temporariamente
            filaPrioridade.enqueue(filaPrioridade.dequeue());
        }

        throw new MyPickException("Ordem de Serviço não encontrada na cache para remoção com o código: " + codigoServico);
    }

    public FilaPrioridade<No> getFilaPrioridade() {
        return filaPrioridade;
    }

    public void imprimirFila() {
        filaPrioridade.printQueue(); // Imprime a fila de prioridade
    }

    public int tamanhoCache() {
        return filaPrioridade.size();
    }
}