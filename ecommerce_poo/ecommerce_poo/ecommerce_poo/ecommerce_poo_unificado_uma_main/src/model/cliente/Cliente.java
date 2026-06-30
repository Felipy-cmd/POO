package model.cliente;

import model.pagamento.FormaPagamento;
import model.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String email;
    private String cpf;
    private String senha;

    private Endereco endereco;

    private FormaPagamento formaPagamento;

    private List<Pedido> pedidos;

    public Cliente(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.pedidos = new ArrayList<>();
        System.out.println("✔ Conta criada para o cliente: " + nome);
    }

    public Cliente(String nome, String email, String cpf, String senha, Endereco endereco) {
        this(nome, email, cpf, senha);
        this.endereco = endereco;
        System.out.println("✔ Endereço registrado: " + endereco);
    }

    public void registrarEndereco(Endereco endereco) {
        this.endereco = endereco;
        System.out.println("✔ Endereço registrado para " + nome + ": " + endereco);
    }

    public void registrarFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        System.out.println("✔ Forma de pagamento registrada para " + nome + ": " + formaPagamento.getTipo());
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        System.out.println("✔ Pedido #" + pedido.getId() + " adicionado ao histórico de " + nome);
    }

    public void exibirConta() {
        System.out.println("\n===== CONTA DO CLIENTE =====");
        System.out.println("Nome    : " + nome);
        System.out.println("E-mail  : " + email);
        System.out.println("CPF     : " + cpf);
        System.out.println("Endereço: " + (endereco != null ? endereco : "não cadastrado"));
        System.out.println("Pagamento: " + (formaPagamento != null ? formaPagamento.getTipo() : "não cadastrado"));
        System.out.println("Pedidos : " + pedidos.size());
        System.out.println("============================\n");
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Endereco getEndereco() { return endereco; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public List<Pedido> getPedidos() { return pedidos; }

    @Override
    public String toString() {
        return "Cliente{nome='" + nome + "', email='" + email + "', cpf='" + cpf + "'}";
    }
}
