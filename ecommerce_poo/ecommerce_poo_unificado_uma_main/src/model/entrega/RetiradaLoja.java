package model.entrega;

import model.pedido.Pedido;

/**
 * Retirada do pedido diretamente na loja.
 */
public class RetiradaLoja implements Entrega {

    private String unidadeLoja;

    public RetiradaLoja(String unidadeLoja) {
        this.unidadeLoja = unidadeLoja;
    }

    @Override
    public String getTipo() {
        return "Retirada na Loja";
    }

    @Override
    public double calcularFrete() {
        return 0.00;
    }

    @Override
    public int getPrazoDias() {
        return 1;
    }

    @Override
    public void realizarEntrega(Pedido pedido) {
        System.out.println("✔ " + getTipo() + " selecionada para o Pedido #" + pedido.getId());
        System.out.println("  Unidade: " + unidadeLoja);
        System.out.println("  Disponível em até " + getPrazoDias() + " dia útil | Frete: R$ " + String.format("%.2f", calcularFrete()));
    }

    public String getUnidadeLoja() { return unidadeLoja; }
    public void setUnidadeLoja(String unidadeLoja) { this.unidadeLoja = unidadeLoja; }
}
