package main;

import model.atendimento.AtendenteVirtual;
import model.cliente.Cliente;
import model.cliente.ClienteEmpresa;
import model.cliente.Endereco;
import model.entrega.Entrega;
import model.entrega.EntregaPadrao;
import model.entrega.EntregaRapida;
import model.entrega.RetiradaLoja;
import model.pagamento.Boleto;
import model.pagamento.Credito;
import model.pagamento.Debito;
import model.pagamento.Pix;
import model.pedido.Carrinho;
import model.pedido.GerenciadorPedidos;
import model.pedido.Pedido;
import model.notafiscal.NotaFiscal;
import model.produto.Produto;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("SISTEMA DE E-COMMERCE — POO/UNIPAM\n");

        AtendenteVirtual atendenteVirtual = new AtendenteVirtual("Ana Bot");
        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

        System.out.println("Você é:");
        System.out.println("  1 - Pessoa Física");
        System.out.println("  2 - Empresa");
        int tipo = lerInteiro("Escolha: ");

        Cliente cliente = cadastrarCliente(tipo);
        cadastrarEndereco(cliente);
        cadastrarFormaPagamento(cliente);

        System.out.print("\nDeseja falar com o Atendente Virtual antes da compra? (s/n): ");
        String desejaAtendimento = sc.nextLine();
        if (desejaAtendimento.equalsIgnoreCase("s")) {
            System.out.print("Qual é o assunto da dúvida? ");
            String assunto = sc.nextLine();
            atendenteVirtual.atenderCliente(cliente, assunto);
        }

        String continuarPedido = "s";
        while (continuarPedido.equalsIgnoreCase("s")) {
            realizarPedido(cliente, gerenciadorPedidos);

            System.out.print("Deseja realizar outro pedido para este mesmo cliente? (s/n): ");
            continuarPedido = sc.nextLine();
        }

        cliente.exibirConta();
        gerenciadorPedidos.exibirPedidos();
        atendenteVirtual.exibirAtendimentos();

        System.out.println("Obrigado por comprar conosco!");
        sc.close();
    }

    static Cliente cadastrarCliente(int tipo) {
        System.out.println("\nCRIAR CONTA\n");
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

    static void cadastrarEndereco(Cliente cliente) {
        System.out.println("\nENDEREÇO\n");
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Estado: ");
        String estado = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        cliente.registrarEndereco(endereco);
    }

    static void cadastrarFormaPagamento(Cliente cliente) {
        System.out.println("\nFORMA DE PAGAMENTO\n");
        System.out.println("  1 - PIX (15% de desconto!)");
        System.out.println("  2 - Cartão de Crédito");
        System.out.println("  3 - Cartão de Débito");
        System.out.println("  4 - Boleto");
        int opcaoPag = lerInteiro("Escolha: ");

        switch (opcaoPag) {
            case 1 -> {
                System.out.print("Sua chave PIX: ");
                String chave = sc.nextLine();
                cliente.registrarFormaPagamento(new Pix(chave));
            }
            case 2 -> {
                String numCartao = lerCartao("Número do cartão: ");
                int parcelas = lerInteiro("Parcelas: ");
                cliente.registrarFormaPagamento(new Credito(numCartao, parcelas));
            }
            case 3 -> {
                String numCartao = lerCartao("Número do cartão: ");
                cliente.registrarFormaPagamento(new Debito(numCartao));
            }
            case 4 -> {
                System.out.print("Código de barras do boleto: ");
                String codigo = sc.nextLine();
                cliente.registrarFormaPagamento(new Boleto(codigo));
            }
            default -> {
                System.out.println("Opção inválida. PIX será cadastrado como padrão.");
                cliente.registrarFormaPagamento(new Pix("pix@padrao.com"));
            }
        }
    }

    static void realizarPedido(Cliente cliente, GerenciadorPedidos gerenciadorPedidos) {
        Carrinho carrinho = new Carrinho();

        System.out.println("\nADICIONAR PRODUTOS AO CARRINHO\n");
        String continuarProduto = "s";
        while (continuarProduto.equalsIgnoreCase("s")) {
            System.out.print("Nome do produto: ");
            String nomeProd = sc.nextLine();
            System.out.print("Descrição do produto: ");
            String descricao = sc.nextLine();
            double preco = lerDouble("Preço: R$ ");
            int qtd = lerInteiro("Quantidade: ");

            Produto produto = new Produto(nomeProd, descricao, preco, qtd);
            carrinho.adicionarProduto(produto);

            System.out.print("Adicionar mais um produto? (s/n): ");
            continuarProduto = sc.nextLine();
        }

        carrinho.exibirCarrinho();

        Pedido pedido = gerenciadorPedidos.criarPedido(cliente);
        for (Produto produto : carrinho.getItens()) {
            gerenciadorPedidos.adicionarProdutoAoPedido(pedido, produto);
        }

        Entrega entregaEscolhida = escolherEntrega();

        pedido.finalizar(cliente.getFormaPagamento());
        entregaEscolhida.realizarEntrega(pedido);
        pedido.exibirPedido();

        NotaFiscal nf = new NotaFiscal(pedido);
        nf.exibirNotaFiscal();

        carrinho.limpar();
    }

    static Entrega escolherEntrega() {
        System.out.println("\nFORMA DE ENTREGA\n");
        System.out.println("  1 - Entrega Padrão");
        System.out.println("  2 - Entrega Rápida");
        System.out.println("  3 - Retirada na Loja");
        int opcaoEntrega = lerInteiro("Escolha: ");

        return switch (opcaoEntrega) {
            case 1 -> new EntregaPadrao();
            case 2 -> new EntregaRapida();
            case 3 -> {
                System.out.print("Informe a unidade da loja para retirada: ");
                String unidade = sc.nextLine();
                yield new RetiradaLoja(unidade);
            }
            default -> {
                System.out.println("Opção inválida. Entrega padrão será selecionada.");
                yield new EntregaPadrao();
            }
        };
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(sc.nextLine().replace(",", ".").trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido.");
            }
        }
    }

    static String lerCartao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String numeroCartao = sc.nextLine().trim();

            if (numeroCartao.length() >= 4) {
                return numeroCartao;
            }

            System.out.println("O número do cartão precisa ter pelo menos 4 caracteres.");
        }
    }
}
