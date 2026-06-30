package model.cliente;

import model.pagamento.FormaPagamento;

public class ClienteEmpresa extends Cliente {

    private String cnpj;
    private String razaoSocial;

    public ClienteEmpresa(String nome, String email, String cpf, String senha, String cnpj, String razaoSocial) {
        super(nome, email, cpf, senha); // chama construtor da superclasse
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        System.out.println("✔ Conta de empresa criada. Razão Social: " + razaoSocial + " | CNPJ: " + cnpj);
    }

    public ClienteEmpresa(String nome, String email, String cpf, String senha,
                          String cnpj, String razaoSocial, Endereco endereco) {
        super(nome, email, cpf, senha, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        System.out.println("✔ Empresa: " + razaoSocial + " | CNPJ: " + cnpj);
    }

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

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
}
