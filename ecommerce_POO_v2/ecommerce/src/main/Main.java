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

        System.out.println("SISTEMA DE E-COMMERCE — POO/UNIPAM\n");

        System.out.println("Você é:");
        System.out.println("  1 - Pessoa Física");
        System.out.println("  2 - Empresa");
        System.out.print("Escolha: \n");
        int tipo = Integer.parseInt(sc.nextLine().trim());

        Cliente cliente = cadastrarCliente(tipo);

        System.out.println("\nENDEREÇO");
        System.out.print("Rua: \n");
        String rua = sc.nextLine();
        System.out.print("Número: \n");
        String numero = sc.nextLine();
        System.out.print("Bairro: \n");
        String bairro = sc.nextLine();
        System.out.print("Cidade: \n");
        String cidade = sc.nextLine();
        System.out.print("Estado: \n");
        String estado = sc.nextLine();
        System.out.print("CEP: \n");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        cliente.registrarEndereco(endereco);

        System.out.println("\nFORMA DE PAGAMENTO");
        System.out.println("  1 - PIX (15% de desconto!)");
        System.out.println("  2 - Cartão de Crédito");
        System.out.println("  3 - Cartão de Débito");
        System.out.println("  4 - Boleto");
        System.out.print("Escolha: \n");
        int opcaoPag = Integer.parseInt(sc.nextLine().trim());

        switch (opcaoPag) {
            case 1 -> {
                System.out.print("Sua chave PIX: \n");
                String chave = sc.nextLine();
                cliente.registrarFormaPagamento(new Pix(chave));
            }
            case 2 -> {
                System.out.print("Número do cartão: \n");
                String numCartao = sc.nextLine();
                System.out.print("Parcelas: \n");
                int parcelas = Integer.parseInt(sc.nextLine().trim());
                cliente.registrarFormaPagamento(new Credito(numCartao, parcelas));
            }
            case 3 -> {
                System.out.print("Número do cartão: \n");
                String numCartao = sc.nextLine();
                cliente.registrarFormaPagamento(new Debito(numCartao));
            }
            case 4 -> {
                System.out.print("Código de barras do boleto: \n");
                String codigo = sc.nextLine();
                cliente.registrarFormaPagamento(new Boleto(codigo));
            }
            default -> System.out.println("Opção inválida.");
        }

        Carrinho carrinho = new Carrinho();

        System.out.println("\nADICIONAR PRODUTOS AO CARRINHO\n");
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Nome do produto: \n");
            String nomeProd = sc.nextLine();
            System.out.print("Preço: R$ ");
            double preco = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Quantidade: \n");
            int qtd = Integer.parseInt(sc.nextLine().trim());

            carrinho.adicionarProduto(new Produto(nomeProd, nomeProd, preco, qtd));

            System.out.print("Adicionar mais um produto? (s/n): \n");
            continuar = sc.nextLine();
        }

        carrinho.exibirCarrinho();

        Pedido pedido = new Pedido(cliente);
        carrinho.getItens().forEach(pedido::adicionarProduto);
        pedido.finalizar(cliente.getFormaPagamento());
        pedido.exibirPedido();

        cliente.exibirConta();

        System.out.println("Obrigado por comprar conosco!");
        sc.close();
    }

    static Cliente cadastrarCliente(int tipo) {
        System.out.println("\nCRIAR CONTA\n");
        System.out.print("Nome: \n");
        String nome = sc.nextLine();
        System.out.print("E-mail: \n");
        String email = sc.nextLine();
        System.out.print("CPF: \n");
        String cpf = sc.nextLine();
        System.out.print("Senha: \n");
        String senha = sc.nextLine();

        if (tipo == 2) {
            System.out.print("Razão Social: \n");
            String razao = sc.nextLine();
            System.out.print("CNPJ: \n");
            String cnpj = sc.nextLine();
            return new ClienteEmpresa(nome, email, cpf, senha, cnpj, razao);
        }

        return new Cliente(nome, email, cpf, senha);
    }
}