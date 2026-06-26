package main;

import model.cliente.Cliente;
import model.cliente.ClienteEmpresa;
import model.cliente.Endereco;
import model.pagamento.Boleto;
import model.pagamento.Credito;
import model.pagamento.Debito;
import model.pagamento.Pix;
import model.pedido.Carrinho;
import model.pedido.Pedido;
import model.produto.Produto;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("     SISTEMA DE E-COMMERCE — POO/UNIPAM    ");
        System.out.println("============================================\n");

        // ── PASSO 1: Tipo de cliente ──────────────────────────────────────────
        System.out.println("Você é:");
        System.out.println("  1 - Pessoa Física");
        System.out.println("  2 - Empresa");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(sc.nextLine().trim());

        Cliente cliente = cadastrarCliente(tipo);

        // ── PASSO 2: Registrar endereço ───────────────────────────────────────
        System.out.println("\n--- ENDEREÇO ---");
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Estado (ex: MG): ");
        String estado = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        cliente.registrarEndereco(endereco);

        // ── PASSO 3: Forma de pagamento ───────────────────────────────────────
        System.out.println("\n--- FORMA DE PAGAMENTO ---");
        System.out.println("  1 - PIX (15% de desconto!)");
        System.out.println("  2 - Cartão de Crédito");
        System.out.println("  3 - Cartão de Débito");
        System.out.println("  4 - Boleto");
        System.out.print("Escolha: ");
        int opcaoPag = Integer.parseInt(sc.nextLine().trim());

        switch (opcaoPag) {
            case 1 -> {
                System.out.print("Sua chave PIX: ");
                String chave = sc.nextLine();
                cliente.registrarFormaPagamento(new Pix(chave));
            }
            case 2 -> {
                System.out.print("Número do cartão: ");
                String numCartao = sc.nextLine();
                System.out.print("Parcelas: ");
                int parcelas = Integer.parseInt(sc.nextLine().trim());
                cliente.registrarFormaPagamento(new Credito(numCartao, parcelas));
            }
            case 3 -> {
                System.out.print("Número do cartão: ");
                String numCartao = sc.nextLine();
                cliente.registrarFormaPagamento(new Debito(numCartao));
            }
            case 4 -> {
                System.out.print("Código de barras do boleto: ");
                String codigo = sc.nextLine();
                cliente.registrarFormaPagamento(new Boleto(codigo));
            }
            default -> System.out.println("Opção inválida, pagamento não registrado.");
        }

        // ── PASSO 4: Montar carrinho e fazer pedido ───────────────────────────
        Carrinho carrinho = new Carrinho();

        System.out.println("\n--- ADICIONAR PRODUTOS AO CARRINHO ---");
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Nome do produto: ");
            String nomeProd = sc.nextLine();
            System.out.print("Preço: R$ ");
            double preco = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine().trim());

            carrinho.adicionarProduto(new Produto(nomeProd, preco > 0 ? nomeProd : "", preco, qtd));

            System.out.print("Adicionar mais um produto? (s/n): ");
            continuar = sc.nextLine();
        }

        carrinho.exibirCarrinho();

        // Finaliza o pedido
        System.out.println("Finalizando pedido...");
        Pedido pedido = new Pedido(cliente);
        carrinho.getItens().forEach(pedido::adicionarProduto);
        pedido.finalizar(cliente.getFormaPagamento());
        pedido.exibirPedido();

        // Exibe resumo da conta
        cliente.exibirConta();

        System.out.println("Obrigado por comprar conosco!");
        sc.close();
    }

    // ── Método auxiliar: cadastro de cliente ──────────────────────────────────

    static Cliente cadastrarCliente(int tipo) {
        System.out.println("\n--- CRIAR CONTA ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (tipo == 2) {
            System.out.print("Razão Social: ");
            String razao = sc.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();
            return new ClienteEmpresa(nome, email, cpf, senha, cnpj, razao);
        }

        return new Cliente(nome, email, cpf, senha);
    }
}
