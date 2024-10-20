import java.util.HashMap;

public class ClienteHuffman {
    private Huffman arvoreHuffman;

    public ClienteHuffman() {
        this.arvoreHuffman = new Huffman();
    }

    // Método para comprimir uma única ordem de serviço
    public HashMap<String, String> comprimir(ServiceOrder ordem, int frequencia) throws MyPickException {
        // Verifica se a ordem de serviço é válida
        if (ordem == null) {
            throw new MyPickException("Ordem de serviço não pode ser nula.");
        }

        // Cria um array com a ordem de serviço
        ServiceOrder[] ordens = { ordem };
        int[] frequencias = { frequencia };

        // Construir a árvore de Huffman
        try {
            arvoreHuffman.construirArvore(ordens.length, ordens, frequencias);
        } catch (Exception e) {
            throw new MyPickException("Erro ao construir a árvore de Huffman: " + e.getMessage());
        }
        
        // Mapa para armazenar os códigos de Huffman
        HashMap<String, String> codigosHuffman = new HashMap<>();
        
        // Gerar os códigos de Huffman
        gerarCodigosHuffman(arvoreHuffman.raiz, "", codigosHuffman);
        
        // Criar um mapa para retornar os códigos
        HashMap<String, String> ordensComprimidas = new HashMap<>();
        
        // Obter os códigos de Huffman correspondentes à ordem de serviço
        String codigoServico = codigosHuffman.get(String.valueOf(ordem.getCodigoServico())); // Código do serviço
        String codigoNome = codigosHuffman.get(ordem.getNome()); // Nome
        String codigoDescricao = codigosHuffman.get(ordem.getDescricao()); // Descrição
        String codigoHora = codigosHuffman.get(ordem.getHora()); // Hora

        // Adiciona os códigos de Huffman ao mapa
        ordensComprimidas.put("Código de Serviço", codigoServico != null ? codigoServico : "N/A");
        ordensComprimidas.put("Nome", codigoNome != null ? codigoNome : "N/A");
        ordensComprimidas.put("Descrição", codigoDescricao != null ? codigoDescricao : "N/A");
        ordensComprimidas.put("Hora", codigoHora != null ? codigoHora : "N/A");

        return ordensComprimidas; // Retorna os códigos de Huffman
    }

    // Método recursivo para gerar códigos de Huffman
    private void gerarCodigosHuffman(No no, String codigoAtual, HashMap<String, String> codigosHuffman) {
        if (no != null) {
            // Se é uma folha, armazena o código
            if (no.serviceOrder != null) {
                codigosHuffman.put(String.valueOf(no.serviceOrder.getCodigoServico()), codigoAtual); // Comprime o código de serviço
                codigosHuffman.put(no.serviceOrder.getNome(), codigoAtual); // Comprime o nome
                codigosHuffman.put(no.serviceOrder.getDescricao(), codigoAtual); // Comprime a descrição
                codigosHuffman.put(no.serviceOrder.getHora(), codigoAtual); // Comprime a hora
            }
            // Chamadas recursivas para os filhos esquerdo e direito
            gerarCodigosHuffman(no.esquerda, codigoAtual + "0", codigosHuffman);
            gerarCodigosHuffman(no.direita, codigoAtual + "1", codigosHuffman);
        }
    }

    // Método para descomprimir os códigos de Huffman
    public ServiceOrder descomprimir(HashMap<String, String> ordensComprimidas) throws MyPickException {
        // Inicializa um ServiceOrder
        ServiceOrder ordemDescomprimida = new ServiceOrder();
        
        // Verifica se a árvore de Huffman foi construída
        if (arvoreHuffman.raiz == null) {
            throw new MyPickException("A árvore de Huffman não foi construída corretamente.");
        }

        // Para cada entrada no mapa de ordens comprimidas
        for (String chave : ordensComprimidas.keySet()) {
            String codigo = ordensComprimidas.get(chave);
            if (codigo != null && !codigo.equals("N/A")) {
                // Percorre a árvore de Huffman para encontrar a ordem de serviço correspondente
                No noAtual = arvoreHuffman.raiz;
                boolean encontrado = false;

                for (char bit : codigo.toCharArray()) {
                    if (bit == '0') {
                        noAtual = noAtual.esquerda; // Vai para a esquerda
                    } else {
                        noAtual = noAtual.direita; // Vai para a direita
                    }

                    // Verifica se chegou a uma folha
                    if (noAtual != null && noAtual.serviceOrder != null) {
                        encontrado = true; // Encontrou um serviço correspondente

                        // Preenche os atributos do ServiceOrder
                        if (chave.equals("Código de Serviço")) {
                            ordemDescomprimida.setCodigoServico(noAtual.serviceOrder.getCodigoServico());
                        } else if (chave.equals("Nome")) {
                            ordemDescomprimida.setNome(noAtual.serviceOrder.getNome());
                        } else if (chave.equals("Descrição")) {
                            ordemDescomprimida.setDescricao(noAtual.serviceOrder.getDescricao());
                        } else if (chave.equals("Hora")) {
                            ordemDescomprimida.setHora(noAtual.serviceOrder.getHora());
                        }
                        // Reinicia a busca para a próxima chave
                        noAtual = arvoreHuffman.raiz;
                    }

                    // Lança uma exceção se a navegação na árvore falhar
                    if (noAtual == null) {
                        throw new MyPickException("Código inválido para a chave: " + chave);
                    }
                }

                // Se não encontrou um serviço correspondente
                if (!encontrado) {
                    throw new MyPickException("Não foi possível encontrar uma ordem de serviço para a chave: " + chave);
                }
            }
        }

        return ordemDescomprimida; // Retorna a ordem de serviço descomprimida
    }
}