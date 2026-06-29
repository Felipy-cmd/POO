package model.pagamento;

/**
 * Pagamento via Cartão de Débito.
 */
public class Debito implements FormaPagamento {

    private String numeroCartao;

    public Debito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String getTipo() {
        return "Cartão de Débito";
    }

    @Override
    public double processar(double valorTotal) {
        System.out.println("✔ Débito aprovado | Cartão: **** " +
                numeroCartao.substring(numeroCartao.length() - 4) +
                " | Valor: R$ " + String.format("%.2f", valorTotal));
        return valorTotal;
    }

    public String getNumeroCartao() { return numeroCartao; }
}
