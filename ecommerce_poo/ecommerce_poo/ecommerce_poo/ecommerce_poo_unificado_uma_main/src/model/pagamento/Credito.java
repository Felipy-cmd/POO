package model.pagamento;

public class Credito implements FormaPagamento {

    private String numeroCartao;
    private int parcelas;

    public Credito(String numeroCartao, int parcelas) {
        this.numeroCartao = numeroCartao;
        this.parcelas = parcelas;
    }

    @Override
    public String getTipo() {
        return "Cartão de Crédito";
    }

    @Override
    public double processar(double valorTotal) {
        double parcela = valorTotal / parcelas;
        System.out.println("✔ Crédito aprovado | Cartão: " + mascararCartao() +
                " | " + parcelas + "x de R$ " + String.format("%.2f", parcela));
        return valorTotal;
    }

    private String mascararCartao() {
        return "**** **** **** " + numeroCartao.substring(numeroCartao.length() - 4);
    }

    public String getNumeroCartao() { return numeroCartao; }
    public int getParcelas() { return parcelas; }
}
