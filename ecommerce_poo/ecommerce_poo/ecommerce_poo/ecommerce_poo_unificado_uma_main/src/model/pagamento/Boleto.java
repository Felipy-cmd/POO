package model.pagamento;

public class Boleto implements FormaPagamento {

    private String codigoBarras;

    public Boleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public String getTipo() {
        return "Boleto Bancário";
    }

    @Override
    public double processar(double valorTotal) {
        System.out.println("✔ Boleto gerado | Código: " + codigoBarras + " | Valor: R$ " + String.format("%.2f", valorTotal));
        return valorTotal;
    }

    public String getCodigoBarras() { return codigoBarras; }
}
