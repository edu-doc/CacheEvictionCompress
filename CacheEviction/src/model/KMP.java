public class KMP {

    // Método principal que realiza a busca
    public static int[] search(String texto, String padrao) {
        int[] lps = calcularLPS(padrao);
        int i = 0; // índice para o texto
        int j = 0; // índice para o padrão
        int[] ocorrencias = new int[texto.length()]; // Array para armazenar as ocorrências
        int count = 0; // Contador de ocorrências

        while (i < texto.length()) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;
            }

            if (j == padrao.length()) {
                // Encontrou uma ocorrência
                ocorrencias[count++] = i - j; // Armazena o índice de início da ocorrência
                j = lps[j - 1]; // Reseta j com base no valor do LPS
            } else if (i < texto.length() && padrao.charAt(j) != texto.charAt(i)) {
                // Não coincide
                if (j != 0) {
                    j = lps[j - 1]; // Usa o valor do LPS
                } else {
                    i++;
                }
            }
        }

        // Retorna as ocorrências encontradas
        return java.util.Arrays.copyOf(ocorrencias, count);
    }

    // Método para calcular o vetor LPS
    private static int[] calcularLPS(String padrao) {
        int[] lps = new int[padrao.length()];
        int len = 0; // Comprimento do prefixo anterior
        int i = 1; // Índice para o padrão

        while (i < padrao.length()) {
            if (padrao.charAt(i) == padrao.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Usa o LPS anterior
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}

