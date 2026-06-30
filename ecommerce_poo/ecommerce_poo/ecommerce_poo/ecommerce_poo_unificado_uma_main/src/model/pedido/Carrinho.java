package model.pedido;

import model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        itens.add(produto);
        System.out.println("✔ Produto adicionado ao carrinho: " + produto.getNome() +
                " | R$ " + String.format("%.2f", produto.getPreco()));
    }

    public void removerProduto(String nomeProduto) {
        itens.removeIf(p -> p.getNome().equalsIgnoreCase(nomeProduto));
        System.out.println("✔ Produto removido do carrinho: " + nomeProduto);
    }

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
        System.out.println("================================\n");
    }

    public List<Produto> getItens() { return itens; }

    public void limpar() { itens.clear(); }
}
