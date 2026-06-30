package model.entrega;

import model.pedido.Pedido;

public interface Entrega {

    String getTipo();

    double calcularFrete();

    int getPrazoDias();

    void realizarEntrega(Pedido pedido);
}
