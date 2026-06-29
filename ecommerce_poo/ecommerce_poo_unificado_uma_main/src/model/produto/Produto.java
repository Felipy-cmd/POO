package model.produto;

/**
 * Representa um Produto disponível no e-commerce.
 * Demonstra: encapsulamento (atributos privados + getters/setters).
 */
public class Produto {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    // ── Construtores (Sobrecarga) ─────────────────────────────────────────────

    public Produto(String nome, double preco) {
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = 1;
    }

    public Produto(String nome, String descricao, double preco, int quantidade) {
        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // ── Getters e Setters ─────────────────────────────────────────────────────

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', preco=R$" + String.format("%.2f", preco) + ", qtd=" + quantidade + "}";
    }
}
