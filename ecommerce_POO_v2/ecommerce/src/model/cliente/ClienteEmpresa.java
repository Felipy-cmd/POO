package model.cliente;

import model.pagamento.FormaPagamento;

/**
 * Representa um Cliente do tipo Empresa.
 * Demonstra: Herança (extends Cliente) — req 3.
 * Uma empresa possui CNPJ além dos dados do cliente comum.
 */
public class ClienteEmpresa extends Cliente {

    private String cnpj;
    private String razaoSocial;

    // ── Construtores (Sobrecarga) ──────────────────────────────────────────────

    /** Construtor com dados básicos da empresa */
    public ClienteEmpresa(String nome, String email, String cpf, String senha, String cnpj, String razaoSocial) {
        super(nome, email, cpf, senha); // chama construtor da superclasse
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        System.out.println("✔ Conta de empresa criada. Razão Social: " + razaoSocial + " | CNPJ: " + cnpj);
    }

    /** Construtor com endereço já registrado */
    public ClienteEmpresa(String nome, String email, String cpf, String senha,
                          String cnpj, String razaoSocial, Endereco endereco) {
        super(nome, email, cpf, senha, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        System.out.println("✔ Empresa: " + razaoSocial + " | CNPJ: " + cnpj);
    }

    // ── Sobrescrita (Override) ────────────────────────────────────────────────

    /**
     * Sobrescreve exibirConta para incluir os dados de empresa (CNPJ e Razão Social).
     * Demonstra: Sobrescrita (req).
     */
    @Override
    public void exibirConta() {
        System.out.println("\n===== CONTA DE EMPRESA =====");
        System.out.println("Razão Social: " + razaoSocial);
        System.out.println("CNPJ        : " + cnpj);
        System.out.println("Nome        : " + getNome());
        System.out.println("E-mail      : " + getEmail());
        System.out.println("CPF resp.   : " + getCpf());
        System.out.println("Endereço    : " + (getEndereco() != null ? getEndereco() : "não cadastrado"));
        System.out.println("Pagamento   : " + (getFormaPagamento() != null ? getFormaPagamento().getTipo() : "não cadastrado"));
        System.out.println("Pedidos     : " + getPedidos().size());
        System.out.println("============================\n");
    }

    @Override
    public String toString() {
        return "ClienteEmpresa{razaoSocial='" + razaoSocial + "', cnpj='" + cnpj + "', " + super.toString() + "}";
    }

    // ── Getters e Setters ─────────────────────────────────────────────────────

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
}
