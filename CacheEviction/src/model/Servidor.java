
public class Servidor {

    public Cache mc;
    public Banco hash;
    public Log log = new Log();

    FrequenciaResultado freq;
    Huffman huff;

    ServiceOrder so;

    public Servidor(Banco hash, Cache mc){
        this.hash = hash;
        this.mc = mc;
    }

    public void inserir (String str, Huffman huff) throws MyPickException{
        String des = huff.descomprimir(str);
        so = so.fromString(des);
        No no = new No(so);
        hash.inserir(no);
        log.log("Cadastro", no, mc, hash);
    }

    public void inserirInicial (ServiceOrder order) throws MyPickException{
        No no = new No(order);
        hash.inserir(no);
        log.log("Cadastro", no, mc, hash);
    }

    public void remover (int codigo) throws MyPickException{
        hash.remover(codigo);
        mc.remover(codigo);
        log.log("Remover", hash.buscar(codigo), mc, hash);
    }

    public EnviarCompressao buscar(int codigo) throws MyPickException{
        ServiceOrder sc = mc.buscar(codigo, hash).order;
        freq = sc.obterFrequencias();
        huff = new Huffman();
        huff.construirArvore(freq.caracteres.length, freq.caracteres, freq.frequencias);
        sc.inicializarCodigos(freq.caracteres, huff.gerarCodigos());
        log.log("Buscado e inserido na cache", hash.buscar(codigo), mc, hash);
        EnviarCompressao ev = new EnviarCompressao(huff, sc.comprimir());
        return ev;
    }

    public void listar(){
        mc.imprimirCache();
        System.out.println("");
        hash.imprimirTabelaHash();
    }

    public void listarCache(){
        mc.imprimirCache();
    }

    public void listarHash(){
        hash.imprimirTabelaHash();
    }

    public void qtdSO(){
        System.out.println(hash.tamanho());
    }

    public void update(Banco hash, int cod, String nome, String descricao) throws MyPickException{
        hash.atualizar(cod, nome, descricao);
        mc.atualizar(cod, nome, descricao);
        log.log("Atualizado na cache e no banco", hash.buscar(cod), mc, hash);
    }

    public void inserirNoCacheComLimite() throws MyPickException {
        for (int i = 30; i < 60; i++){
            buscar(i);
        }
    }

    

    public void inicializar() throws MyPickException{

        ServiceOrder order1 = new ServiceOrder("Afonso", "Erro: Internet instável");
        ServiceOrder order2 = new ServiceOrder("Eduardo", "Erro: Conexão perdida");
        ServiceOrder order3 = new ServiceOrder("Pedro", "Erro: Rede indisponível");
        ServiceOrder order4 = new ServiceOrder("Arthur", "Erro: Falha no DNS");
        ServiceOrder order5 = new ServiceOrder("Lucas", "Erro: Timeout de conexão");
        ServiceOrder order6 = new ServiceOrder("Brenno", "Erro: Wi-Fi desconectado");
        ServiceOrder order7 = new ServiceOrder("Bruno", "Erro: Falha na autenticação");
        ServiceOrder order8 = new ServiceOrder("Maria", "Erro: Serviço não encontrado");
        ServiceOrder order9 = new ServiceOrder("Clara", "Erro: IP conflitante");
        ServiceOrder order10 = new ServiceOrder("Joao", "Erro: Proxy não responde");
        ServiceOrder order11 = new ServiceOrder("Guilhereme", "Erro: Latência alta");
        ServiceOrder order12 = new ServiceOrder("Joana", "Erro: Banda limitada");
        ServiceOrder order13 = new ServiceOrder("Margot", "Erro: Conexão interrompida");
        ServiceOrder order14 = new ServiceOrder("Carlos", "Erro: Porta bloqueada");
        ServiceOrder order15 = new ServiceOrder("Renato", "Erro: VPN falhou");
        ServiceOrder order16 = new ServiceOrder("Alan", "Erro: Conexão lenta");
        ServiceOrder order17 = new ServiceOrder("Jose", "Erro: Falha no roteador");
        ServiceOrder order18 = new ServiceOrder("Henrique", "Erro: Gateway inacessível");
        ServiceOrder order19 = new ServiceOrder("Gustavo", "Erro: Servidor não encontrado");
        ServiceOrder order20 = new ServiceOrder("Douglas", "Erro: DNS temporário");
        ServiceOrder order21 = new ServiceOrder("Ana", "Erro: Conexão instável");
        ServiceOrder order22 = new ServiceOrder("Lara", "Erro: Falha na rede");
        ServiceOrder order23 = new ServiceOrder("Felipe", "Erro: Interrupção de serviço");
        ServiceOrder order24 = new ServiceOrder("Fernanda", "Erro: Falha na comunicação");
        ServiceOrder order25 = new ServiceOrder("Mariana", "Erro: Erro de rede");
        ServiceOrder order26 = new ServiceOrder("Thiago", "Erro: Falha na sincronização");
        ServiceOrder order27 = new ServiceOrder("Beatriz", "Erro: Pacotes perdidos");
        ServiceOrder order28 = new ServiceOrder("Leonardo", "Erro: Falha na resolução de nome");
        ServiceOrder order29 = new ServiceOrder("Raquel", "Erro: Falha no handshake");
        ServiceOrder order30 = new ServiceOrder("Sofia", "Erro: Endereço IP inválido");
        ServiceOrder order31 = new ServiceOrder("Igor", "Erro: Falha no TLS");
        ServiceOrder order32 = new ServiceOrder("Ricardo", "Erro: Falha na criptografia");
        ServiceOrder order33 = new ServiceOrder("Carolina", "Erro: Ping não respondido");
        ServiceOrder order34 = new ServiceOrder("Gabriel", "Erro: Sessão expirada");
        ServiceOrder order35 = new ServiceOrder("Isabela", "Erro: Limite de tempo atingido");
        ServiceOrder order36 = new ServiceOrder("Matheus", "Erro: Certificado expirado");
        ServiceOrder order37 = new ServiceOrder("Lucas", "Erro: Endereço MAC bloqueado");
        ServiceOrder order38 = new ServiceOrder("Amanda", "Erro: Proxy não encontrado");
        ServiceOrder order39 = new ServiceOrder("Rafael", "Erro: HTTP 404");
        ServiceOrder order40 = new ServiceOrder("Juliana", "Erro: Serviço temporariamente indisponível");
        ServiceOrder order41 = new ServiceOrder("Bruna", "Erro: Endereço IP não atribuído");
        ServiceOrder order42 = new ServiceOrder("Thiago", "Erro: Configuração de rede inválida");
        ServiceOrder order43 = new ServiceOrder("Julio", "Erro: Conexão segura falhou");
        ServiceOrder order44 = new ServiceOrder("Renata", "Erro: Erro de autenticação");
        ServiceOrder order45 = new ServiceOrder("Patricia", "Erro: Pacote de dados corrompido");
        ServiceOrder order46 = new ServiceOrder("Sergio", "Erro: Problema no modem");
        ServiceOrder order47 = new ServiceOrder("Leticia", "Erro: Falha no firewall");
        ServiceOrder order48 = new ServiceOrder("Diego", "Erro: Endereço IP duplicado");
        ServiceOrder order49 = new ServiceOrder("Marcelo", "Erro: Servidor sobrecarregado");
        ServiceOrder order50 = new ServiceOrder("Camila", "Erro: Falha na atualização de DNS");
        ServiceOrder order51 = new ServiceOrder("Fabio", "Erro: Erro de SSL");
        ServiceOrder order52 = new ServiceOrder("Eliana", "Erro: Conexão rejeitada");
        ServiceOrder order53 = new ServiceOrder("Otavio", "Erro: Falha no roteamento");
        ServiceOrder order54 = new ServiceOrder("Paula", "Erro: Protocolo não suportado");
        ServiceOrder order55 = new ServiceOrder("Helena", "Erro: Conexão redefinida");
        ServiceOrder order56 = new ServiceOrder("Vicente", "Erro: Erro de configuração do servidor");
        ServiceOrder order57 = new ServiceOrder("Simone", "Erro: Falha na transferência de dados");
        ServiceOrder order58 = new ServiceOrder("Anderson", "Erro: Erro de sincronização de tempo");
        ServiceOrder order59 = new ServiceOrder("Luiza", "Erro: Falha na autenticação de usuário");
        ServiceOrder order60 = new ServiceOrder("Miguel", "Erro: Serviço temporariamente inacessível");
        ServiceOrder order61 = new ServiceOrder("Camila", "Erro: Falha na atualização de DNS");
        ServiceOrder order62 = new ServiceOrder("Fabio", "Erro: Erro de SSL");
        ServiceOrder order63 = new ServiceOrder("Eliana", "Erro: Conexão rejeitada");
        ServiceOrder order64 = new ServiceOrder("Otavio", "Erro: Falha no roteamento");
        ServiceOrder order65 = new ServiceOrder("Paula", "Erro: Protocolo não suportado");
        ServiceOrder order66 = new ServiceOrder("Helena", "Erro: Conexão redefinida");
        ServiceOrder order67 = new ServiceOrder("Vicente", "Erro: Erro de configuração do servidor");
        ServiceOrder order68 = new ServiceOrder("Simone", "Erro: Falha na transferência de dados");
        ServiceOrder order69 = new ServiceOrder("Anderson", "Erro: Erro de sincronização de tempo");
        ServiceOrder order70 = new ServiceOrder("Luiza", "Erro: Falha na autenticação de usuário");
        ServiceOrder order71 = new ServiceOrder("Instalação de Software", "Erro: Instalação de software de gerenciamento.");
        ServiceOrder order72 = new ServiceOrder("Atualização de Sistema", "Erro: Atualização de sistema operacional para a versão mais recente.");
        ServiceOrder order73 = new ServiceOrder("Configuração de Rede", "Erro: Configuração de rede local para novos dispositivos.");
        ServiceOrder order74 = new ServiceOrder("Substituição de Hardware", "Erro: Substituição de disco rígido por um SSD.");
        ServiceOrder order75 = new ServiceOrder("Treinamento de Usuários", "Erro: Treinamento para uso de novo software.");
        ServiceOrder order76 = new ServiceOrder("Reparo de Laptop", "Erro: Reparo em laptop com tela quebrada.");
        ServiceOrder order77 = new ServiceOrder("Backup de Dados", "Erro: Execução de backup completo dos dados.");
        ServiceOrder order78 = new ServiceOrder("Instalação de Antivírus", "Erro: Instalação e configuração de software antivírus.");
        ServiceOrder order79 = new ServiceOrder("Limpeza de Software", "Erro: Limpeza de programas indesejados do sistema.");
        ServiceOrder order80 = new ServiceOrder("Verificação de Segurança", "Erro: Análise de segurança em sistemas e redes.");
        ServiceOrder order81 = new ServiceOrder("Desinstalação de Programa", "Erro: Remoção de software desnecessário do sistema.");
        ServiceOrder order82 = new ServiceOrder("Atualização de Firmware", "Erro: Atualização do firmware de dispositivos de rede.");
        ServiceOrder order83 = new ServiceOrder("Configuração de Impressora", "Erro: Configuração de impressora em rede.");
        ServiceOrder order84 = new ServiceOrder("Desenvolvimento de Software", "Erro: Desenvolvimento de um aplicativo para gerenciamento.");
        ServiceOrder order85 = new ServiceOrder("Auditoria de Sistemas", "Erro: Auditoria de segurança e eficiência do sistema.");
        ServiceOrder order86 = new ServiceOrder("Suporte Técnico Remoto", "Erro: Assistência técnica via acesso remoto.");
        ServiceOrder order87 = new ServiceOrder("Reparo de Teclado", "Erro: Reparo de teclado de laptop com teclas faltando.");
        ServiceOrder order88 = new ServiceOrder("Configuração de Servidor", "Erro: Configuração de um novo servidor de arquivos.");
        ServiceOrder order89 = new ServiceOrder("Instalação de Rede Wi-Fi", "Erro: Instalação de pontos de acesso Wi-Fi.");
        ServiceOrder order90 = new ServiceOrder("Recuperação de Dados", "Erro: Recuperação de dados de disco rígido danificado.");
        ServiceOrder order91 = new ServiceOrder("Reparo de Monitor", "Erro: Reparo de monitor com problemas de display.");
        ServiceOrder order92 = new ServiceOrder("Configuração de Novo PC", "Erro: Configuração de um novo computador pessoal.");
        ServiceOrder order93 = new ServiceOrder("Limpeza de Hardware", "Erro: Limpeza interna de componentes de hardware.");
        ServiceOrder order94 = new ServiceOrder("Teste de Sistema", "Erro: Teste de funcionalidade e desempenho do sistema.");
        ServiceOrder order95 = new ServiceOrder("Implementação de Backup", "Erro: Configuração de rotinas de backup.");
        ServiceOrder order96 = new ServiceOrder("Troca de Bateria", "Erro: Substituição da bateria de laptop.");
        ServiceOrder order97 = new ServiceOrder("Instalação de Equipamento", "Erro: Instalação de equipamento de áudio.");
        ServiceOrder order98 = new ServiceOrder("Auditoria de Rede", "Erro: Auditoria de segurança da rede.");
        ServiceOrder order99 = new ServiceOrder("Suporte de Emergência", "Erro: Suporte técnico em caso de emergência.");
        ServiceOrder order100 = new ServiceOrder("Formatação de Computador", "Erro: Formatação completa e instalação do sistema operacional.");


        inserirInicial(order1);
        inserirInicial(order2);
        inserirInicial(order3);
        inserirInicial(order4);
        inserirInicial(order5);
        inserirInicial(order6);
        inserirInicial(order7);
        inserirInicial(order8);
        inserirInicial(order9);
        inserirInicial(order10);
        inserirInicial(order11);
        inserirInicial(order12);
        inserirInicial(order13);
        inserirInicial(order14);
        inserirInicial(order15);
        inserirInicial(order16);
        inserirInicial(order17);
        inserirInicial(order18);
        inserirInicial(order19);
        inserirInicial(order20);
        inserirInicial(order21);
        inserirInicial(order22);
        inserirInicial(order23);
        inserirInicial(order24);
        inserirInicial(order25);
        inserirInicial(order26);
        inserirInicial(order27);
        inserirInicial(order28);
        inserirInicial(order29);
        inserirInicial(order30);
        inserirInicial(order31);
        inserirInicial(order32);
        inserirInicial(order33);
        inserirInicial(order34);
        inserirInicial(order35);
        inserirInicial(order36);
        inserirInicial(order37);
        inserirInicial(order38);
        inserirInicial(order39);
        inserirInicial(order40);
        inserirInicial(order41);
        inserirInicial(order42);
        inserirInicial(order43);
        inserirInicial(order44);
        inserirInicial(order45);
        inserirInicial(order46);
        inserirInicial(order47);
        inserirInicial(order48);
        inserirInicial(order49);
        inserirInicial(order50);
        inserirInicial(order51);
        inserirInicial(order52);
        inserirInicial(order53);
        inserirInicial(order54);
        inserirInicial(order55);
        inserirInicial(order56);
        inserirInicial(order57);
        inserirInicial(order58);
        inserirInicial(order59);
        inserirInicial(order60);
        inserirInicial(order61);
        inserirInicial(order62);
        inserirInicial(order63);
        inserirInicial(order64);
        inserirInicial(order65);
        inserirInicial(order66);
        inserirInicial(order67);
        inserirInicial(order68);
        inserirInicial(order69);
        inserirInicial(order70);
        inserirInicial(order71);
        inserirInicial(order72);
        inserirInicial(order73);
        inserirInicial(order74);
        inserirInicial(order75);
        inserirInicial(order76);
        inserirInicial(order77);
        inserirInicial(order78);
        inserirInicial(order79);
        inserirInicial(order80);
        inserirInicial(order81);
        inserirInicial(order82);
        inserirInicial(order83);
        inserirInicial(order84);
        inserirInicial(order85);
        inserirInicial(order86);
        inserirInicial(order87);
        inserirInicial(order88);
        inserirInicial(order89);
        inserirInicial(order90);
        inserirInicial(order91);
        inserirInicial(order92);
        inserirInicial(order93);
        inserirInicial(order94);
        inserirInicial(order95);
        inserirInicial(order96);
        inserirInicial(order97);
        inserirInicial(order98);
        inserirInicial(order99);
        inserirInicial(order100);


        inserirNoCacheComLimite();

    }

    @Override
    public String toString() {
        return super.toString();
    }

}
