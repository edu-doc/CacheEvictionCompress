import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public Cache mc;
    public Banco hash;
    public Log log = new Log();

    public Servidor(Banco hash, Cache mc){
        this.hash = hash;
        this.mc = mc;
    }

    public void inserir (No no){
        hash.inserir(no);
        log.log("Inserido no banco", no, no.order.codigoServico, mc.tabela);
    }

    public void inserirCliente (No no){
        hash.inserir(no);
        log.log("Inserido no banco", no, no.order.codigoServico, mc.tabela);
    }

    public void remover (int codigo) throws MyPickException{
        hash.remover(codigo);
        mc.remover(codigo);
    }

    public void buscar(int codigo) throws MyPickException{
        mc.buscar(codigo,hash);
        log.log("Buscado e inserido na cache", hash.buscar(codigo), hash.buscar(codigo).order.codigoServico, mc.tabela);
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
        log.log("Atualizado na cache e no banco", hash.buscar(cod), cod, mc.tabela);
    }

    public void inserirNoCacheComLimite(No no) {
        if (mc.tamanho() < 20) {  // Verifica se a cache tem espaço disponível
            mc.inserir(no);  // Insere o elemento na cache
            log.log("Inserido na cache", no, no.order.codigoServico, mc.tabela);  // Log da inserção
        } else {
            System.out.println("Cache já está cheia! Não é possível inserir mais de " + 20 + " elementos.");
        }
    }

    public void inicializar(){

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

        No n1 = new No(order1);
        No n2 = new No(order2);
        No n3 = new No(order3);
        No n4 = new No(order4);
        No n5 = new No(order5);
        No n6 = new No(order6);
        No n7 = new No(order7);
        No n8 = new No(order8);
        No n9 = new No(order9);
        No n10 = new No(order10);
        No n11 = new No(order11);
        No n12 = new No(order12);
        No n13 = new No(order13);
        No n14 = new No(order14);
        No n15 = new No(order15);
        No n16 = new No(order16);
        No n17 = new No(order17);
        No n18 = new No(order18);
        No n19 = new No(order19);
        No n20 = new No(order20);
        No n21 = new No(order21);
        No n22 = new No(order22);
        No n23 = new No(order23);
        No n24 = new No(order24);
        No n25 = new No(order25);
        No n26 = new No(order26);
        No n27 = new No(order27);
        No n28 = new No(order28);
        No n29 = new No(order29);
        No n30 = new No(order30);
        No n31 = new No(order31);
        No n32 = new No(order32);
        No n33 = new No(order33);
        No n34 = new No(order34);
        No n35 = new No(order35);
        No n36 = new No(order36);
        No n37 = new No(order37);
        No n38 = new No(order38);
        No n39 = new No(order39);
        No n40 = new No(order40);
        No n41 = new No(order41);
        No n42 = new No(order42);
        No n43 = new No(order43);
        No n44 = new No(order44);
        No n45 = new No(order45);
        No n46 = new No(order46);
        No n47 = new No(order47);
        No n48 = new No(order48);
        No n49 = new No(order49);
        No n50 = new No(order50);
        No n51 = new No(order51);
        No n52 = new No(order52);
        No n53 = new No(order53);
        No n54 = new No(order54);
        No n55 = new No(order55);
        No n56 = new No(order56);
        No n57 = new No(order57);
        No n58 = new No(order58);
        No n59 = new No(order59);
        No n60 = new No(order60);
        No n61 = new No(order61);
        No n62 = new No(order62);
        No n63 = new No(order63);
        No n64 = new No(order64);
        No n65 = new No(order65);
        No n66 = new No(order66);
        No n67 = new No(order67);
        No n68 = new No(order68);
        No n69 = new No(order69);
        No n70 = new No(order70);

        List<No> nos = new ArrayList<>();

        nos.add(n1);
        nos.add(n2);
        nos.add(n3);
        nos.add(n4);
        nos.add(n5);
        nos.add(n6);
        nos.add(n7);
        nos.add(n8);
        nos.add(n9);
        nos.add(n10);
        nos.add(n11);
        nos.add(n12);
        nos.add(n13);
        nos.add(n14);
        nos.add(n15);
        nos.add(n16);
        nos.add(n17);
        nos.add(n18);
        nos.add(n19);
        nos.add(n20);
        nos.add(n21);
        nos.add(n22);
        nos.add(n23);
        nos.add(n24);
        nos.add(n25);
        nos.add(n26);
        nos.add(n27);
        nos.add(n28);
        nos.add(n29);
        nos.add(n30);
        nos.add(n31);
        nos.add(n32);
        nos.add(n33);
        nos.add(n34);
        nos.add(n35);
        nos.add(n36);
        nos.add(n37);
        nos.add(n38);
        nos.add(n39);
        nos.add(n40);
        nos.add(n41);
        nos.add(n42);
        nos.add(n43);
        nos.add(n44);
        nos.add(n45);
        nos.add(n46);
        nos.add(n47);
        nos.add(n48);
        nos.add(n49);
        nos.add(n50);
        nos.add(n51);
        nos.add(n52);
        nos.add(n53);
        nos.add(n54);
        nos.add(n55);
        nos.add(n56);
        nos.add(n57);
        nos.add(n58);
        nos.add(n59);
        nos.add(n60);
        nos.add(n61);
        nos.add(n62);
        nos.add(n63);
        nos.add(n64);
        nos.add(n65);
        nos.add(n66);
        nos.add(n67);
        nos.add(n68);
        nos.add(n69);
        nos.add(n70);

        for (No no : nos) {
            inserir(no);
        }

        
        inserirNoCacheComLimite(n50);
        inserirNoCacheComLimite(n51);
        inserirNoCacheComLimite(n52);
        inserirNoCacheComLimite(n53);
        inserirNoCacheComLimite(n54);
        inserirNoCacheComLimite(n55);
        inserirNoCacheComLimite(n56);
        inserirNoCacheComLimite(n57);
        inserirNoCacheComLimite(n58);
        inserirNoCacheComLimite(n59);
        inserirNoCacheComLimite(n60);
        inserirNoCacheComLimite(n61);
        inserirNoCacheComLimite(n62);
        inserirNoCacheComLimite(n63);
        inserirNoCacheComLimite(n64);
        inserirNoCacheComLimite(n65);
        inserirNoCacheComLimite(n66);
        inserirNoCacheComLimite(n67);
        inserirNoCacheComLimite(n68);
        inserirNoCacheComLimite(n69);
        inserirNoCacheComLimite(n70);

        
        
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
