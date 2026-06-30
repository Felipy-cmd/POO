package model.pagamento;

public interface FormaPagamento {

    String getTipo();

    double processar(double valorTotal);
}
