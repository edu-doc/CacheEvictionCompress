import java.util.Scanner;

import Exception.MyPickException;
import model.Banco;
import model.Cache;
import model.No;
import model.ServiceOrder;
import model.Servidor;

public class Cliente {

    private static Banco hash = new Banco(11);
    private static Cache mc = new Cache(20);
    private static Servidor server = new Servidor(hash, mc);

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        server.inicializar();

        do {
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            processarOpcao(opcao, sc);
            
        } while (opcao != 7);

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Cadastrar OS");
        System.out.println("2. Listar todas as OS");
        System.out.println("3. Alterar OS");
        System.out.println("4. Remover OS");
        System.out.println("5. Buscar OS");
        System.out.println("6. Acessar quantidade de OS");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
        System.out.println("");
    }

    private static void processarOpcao(int opcao, Scanner sc) {
        switch (opcao) {
            case 1:
                cadastrarOS(sc);
                System.out.println("");
                server.listarCache();
                break;
            case 2:
                server.listarHash();
                System.out.println("");
                server.listarCache();
                break;
            case 3:
                alterarOS(sc);
                System.out.println("");
                server.listarCache();
                break;
            case 4:
                removerOS(sc);
                System.out.println("");
                server.listarCache();
                break;
            case 5:
                buscarOS(sc);
                System.out.println("");
                server.listarCache();
                break;
            case 6:
                server.qtdSO();
                System.out.println("");
                server.listarCache();
                break;
            case 7:
                System.out.println("Saindo");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
        System.out.println();
    }

    private static void cadastrarOS(Scanner sc) {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite a descrição do problema: ");
        String desc = sc.nextLine();

        ServiceOrder so = new ServiceOrder(nome, desc);
        No no = new No(so);

        server.inserirCliente(no);
    }

    private static void alterarOS(Scanner sc)  {
        System.out.print("Digite o id do Service Order que você vai alterar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o novo nome da pessoa: ");
        String nome = sc.nextLine();

        System.out.print("Digite a nova descrição: ");
        String descricao = sc.nextLine();

        try {
            server.update(hash, codigo, nome, descricao);
        } catch (MyPickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void removerOS(Scanner sc) {
        System.out.print("Digite o id do Service Order que você vai remover: ");
        int codigo = sc.nextInt();

        try {
            server.remover(codigo);
        } catch (MyPickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void buscarOS(Scanner sc) {
        System.out.print("Digite o id do Service Order que você vai buscar: ");
        int codigo = sc.nextInt();

        try {
            server.buscar(codigo);
        } catch (MyPickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
    
