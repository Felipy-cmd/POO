package model.pedido;

import model.cliente.Cliente;
import model.pagamento.FormaPagamento;
import model.produto.Produto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Pedido realizado por um Cliente.
 * Req 4: um Pedido pode ter vários Produtos, mas pertence a apenas um Cliente.
 * Demonstra: composição (Produtos), associação (Cliente, FormaPagamento).
 */
public class Pedido {

    private static int contadorId = 1;

    private int id;
    private Cliente cliente;          // associação: um Pedido pertence a um Cliente
    private List<Produto> produtos;   // composição: Produtos existem dentro do Pedido
    private FormaPagamento formaPagamento;
    private double valorTotal;
    private double valorFinal;
    private LocalDateTime dataHora;
    private String status;

    public Pedido(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
        this.status = "ABERTO";
        System.out.println("✔ Pedido #" + id + " criado para " + cliente.getNome());
    }

    /** Adiciona produto ao pedido */
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("  + Produto: " + produto.getNome() + " (R$ " + String.format("%.2f", produto.getPreco()) + ")");
    }

    /** Finaliza o pedido processando o pagamento */
    public void finalizar(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.valorTotal = produtos.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
        this.valorFinal = formaPagamento.processar(valorTotal);
        this.status = "FINALIZADO";
        cliente.adicionarPedido(this);
        System.out.println("✔ Pedido #" + id + " finalizado com sucesso!");
    }

    public void exibirPedido() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("\n======= PEDIDO #" + id + " =======");
        System.out.println("Cliente : " + cliente.getNome());
        System.out.println("Data    : " + dataHora.format(fmt));
        System.out.println("Status  : " + status);
        System.out.println("Produtos:");
        produtos.forEach(p -> System.out.println("  - " + p.getNome() + " x" + p.getQuantidade() + " = R$ " + String.format("%.2f", p.getPreco() * p.getQuantidade())));
        System.out.println("Valor Total : R$ " + String.format("%.2f", valorTotal));
        if (formaPagamento != null) {
            System.out.println("Pagamento   : " + formaPagamento.getTipo());
            System.out.println("Valor Final : R$ " + String.format("%.2f", valorFinal));
        }
        System.out.println("================================\n");
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Produto> getProdutos() { return produtos; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public double getValorTotal() { return valorTotal; }
    public double getValorFinal() { return valorFinal; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getStatus() { return status; }
}
