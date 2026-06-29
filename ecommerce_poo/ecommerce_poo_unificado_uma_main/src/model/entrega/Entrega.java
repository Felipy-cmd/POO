package model.entrega;

import model.pedido.Pedido;

/**
 * Interface para as formas de entrega do pedido.
 * Req 6: entrega padrão, entrega rápida ou retirada na loja.
 *
 * Demonstra: abstração/interface e polimorfismo.
 */
public interface Entrega {

    String getTipo();

    double calcularFrete();

    int getPrazoDias();

    void realizarEntrega(Pedido pedido);
}
