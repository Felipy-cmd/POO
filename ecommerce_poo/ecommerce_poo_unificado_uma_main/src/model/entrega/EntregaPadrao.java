package model.entrega;

import model.pedido.Pedido;

/**
 * Entrega padrão do e-commerce.
 */
public class EntregaPadrao implements Entrega {

    private static final double FRETE = 15.00;
    private static final int PRAZO_DIAS = 7;

    @Override
    public String getTipo() {
        return "Entrega Padrão";
    }

    @Override
    public double calcularFrete() {
        return FRETE;
    }

    @Override
    public int getPrazoDias() {
        return PRAZO_DIAS;
    }

    @Override
    public void realizarEntrega(Pedido pedido) {
        System.out.println("✔ " + getTipo() + " selecionada para o Pedido #" + pedido.getId());
        System.out.println("  Prazo: " + PRAZO_DIAS + " dias úteis | Frete: R$ " + String.format("%.2f", FRETE));
    }
}
