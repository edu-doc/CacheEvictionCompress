public class teste {

    public static void main(String[] args) {
        

        Huffman arv = new Huffman();
        Huffman arv2 = new Huffman();
        ServiceOrder sc = new ServiceOrder("paulo", "faltou água");
        ServiceOrder sc2 = new ServiceOrder("eduardo", "faltou energia");
        FrequenciaResultado fr = sc.obterFrequencias();
        FrequenciaResultado fr2 = sc2.obterFrequencias();
        
        arv.construirArvore(fr.caracteres.length, fr.caracteres, fr.frequencias);
        
        arv.imprimirCodigos(arv.gerarCodigos());

        sc.inicializarCodigos(fr.caracteres, arv.gerarCodigos());

        System.out.println(sc.comprimir());

        System.out.println(arv.descomprimir(sc.comprimir()));

        ServiceOrder order2 = ServiceOrder.fromString(arv.descomprimir(sc.comprimir()));

        System.out.println(order2);

        arv2.construirArvore(fr2.caracteres.length, fr2.caracteres, fr2.frequencias);
        
        arv2.imprimirCodigos(arv2.gerarCodigos());

        sc2.inicializarCodigos(fr2.caracteres, arv2.gerarCodigos());

        System.out.println(sc2.comprimir());

        System.out.println(arv2.descomprimir(sc2.comprimir()));

        ServiceOrder order3 = ServiceOrder.fromString(arv2.descomprimir(sc2.comprimir()));

        System.out.println(order3);

        


    }

}
