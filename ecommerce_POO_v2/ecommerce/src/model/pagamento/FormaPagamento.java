package model.pagamento;

/**
 * Interface para formas de pagamento.
 * Demonstra: Abstração / Interface (req).
 */
public interface FormaPagamento {

    /** Retorna o nome/tipo da forma de pagamento */
    String getTipo();

    /** Processa o pagamento para o valor informado e retorna o valor final cobrado */
    double processar(double valorTotal);
}
