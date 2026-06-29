package model.pagamento;

/**
 * Pagamento via PIX.
 * Req 8: aplica desconto de 15% no valor total.
 */
public class Pix implements FormaPagamento {

    private static final double DESCONTO = 0.15;
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public String getTipo() {
        return "PIX";
    }

    @Override
    public double processar(double valorTotal) {
        double desconto = valorTotal * DESCONTO;
        double valorFinal = valorTotal - desconto;
        System.out.println("✔ PIX processado | Chave: " + chavePix);
        System.out.println("  Valor original : R$ " + String.format("%.2f", valorTotal));
        System.out.println("  Desconto (15%) : R$ " + String.format("%.2f", desconto));
        System.out.println("  Valor final    : R$ " + String.format("%.2f", valorFinal));
        return valorFinal;
    }

    public String getChavePix() { return chavePix; }
}
