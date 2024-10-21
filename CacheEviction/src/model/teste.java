public class teste {

    public static void main(String[] args) {
        

        Huffman arv = new Huffman();
        ServiceOrder sc = new ServiceOrder("eduardo", "caiu a luz");
        FrequenciaResultado fr = sc.obterFrequencias();
        
        arv.construirArvore(fr.caracteres.length, fr.caracteres, fr.frequencias);
        
        arv.imprimirCodigos(arv.gerarCodigos());

        sc.inicializarCodigos(fr.caracteres, arv.gerarCodigos());

        System.out.println(sc.comprimir());

        System.out.println(arv.descomprimir(sc.comprimir()));

        


    }

}
