package model.pedido;

import model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Carrinho de compras do cliente.
 * Req 1.4: adicionar produto ao carrinho.
 * Demonstra: composição (lista de Produtos).
 */
public class Carrinho {

    private List<Produto> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    /** Req 1.4 — Adicionar produto ao carrinho */
    public void adicionarProduto(Produto produto) {
        itens.add(produto);
        System.out.println("✔ Produto adicionado ao carrinho: " + produto.getNome() +
                " | R$ " + String.format("%.2f", produto.getPreco()));
    }

    /** Remove produto pelo nome */
    public void removerProduto(String nomeProduto) {
        itens.removeIf(p -> p.getNome().equalsIgnoreCase(nomeProduto));
        System.out.println("✔ Produto removido do carrinho: " + nomeProduto);
    }

    /** Calcula o total dos itens no carrinho */
    public double calcularTotal() {
        return itens.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
    }

    public void exibirCarrinho() {
        System.out.println("\n--- CARRINHO ---");
        if (itens.isEmpty()) {
            System.out.println("  Carrinho vazio.");
        } else {
            itens.forEach(System.out::println);
            System.out.println("Total: R$ " + String.format("%.2f", calcularTotal()));
        }
        System.out.println("----------------\n");
    }

    public List<Produto> getItens() { return itens; }

    public void limpar() { itens.clear(); }
}
