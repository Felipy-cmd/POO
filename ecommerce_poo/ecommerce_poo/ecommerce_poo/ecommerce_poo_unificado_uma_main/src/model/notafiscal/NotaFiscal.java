package model.notafiscal;

import model.cliente.Cliente;
import model.pedido.Pedido;
import model.produto.Produto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotaFiscal {

    private static int contadorNF = 1;

    private int numero;
    private Pedido pedido;          
    private Cliente cliente;       
    private LocalDateTime dataEmissao;

    public NotaFiscal(Pedido pedido) {
        this.numero = contadorNF++;
        this.pedido = pedido;
        this.cliente = pedido.getCliente();
        this.dataEmissao = LocalDateTime.now();
        System.out.println("✔ Nota Fiscal #" + numero + " emitida para o Pedido #" + pedido.getId());
    }

    public void exibirNotaFiscal() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n================================");
        System.out.println("NOTA FISCAL #" + String.format("%-14d", numero));
        System.out.println("Data de Emissão: " + String.format("%-20s", dataEmissao.format(fmt)));
        System.out.println("DADOS DO CLIENTE");
        System.out.println("Nome   : " + String.format("%-28s", cliente.getNome()));
        System.out.println("CPF    : " + String.format("%-28s", cliente.getCpf()));
        System.out.println("E-mail : " + String.format("%-28s", cliente.getEmail()));
        if (cliente.getEndereco() != null) {
            System.out.println("║  Endereço: " + String.format("%-27s", cliente.getEndereco().toString()) + "║");
        }
        System.out.println("\n================================");
        System.out.println("DADOS DO PEDIDO");
        System.out.println("Pedido #: " + String.format("%-27d", pedido.getId()));
        System.out.println("Status  : " + String.format("%-27s", pedido.getStatus()));
        System.out.println("\n================================");
        System.out.println("ITENS");
        for (Produto p : pedido.getProdutos()) {
            String linha = p.getNome() + " x" + p.getQuantidade()
                    + " = R$ " + String.format("%.2f", p.getPreco() * p.getQuantidade());
            System.out.println("- " + String.format("%-35s", linha));
        }
        System.out.println("\n================================");
        System.out.println("Valor Total  : R$ " + String.format("%-18s", String.format("%.2f", pedido.getValorTotal())));
        if (pedido.getFormaPagamento() != null) {
            System.out.println("Pagamento    : " + String.format("%-21s", pedido.getFormaPagamento().getTipo()));
            System.out.println("Valor Final  : R$ " + String.format("%-18s", String.format("%.2f", pedido.getValorFinal())));
        }
        System.out.println("\n================================");
    }

    public int getNumero() { return numero; }
    public Pedido getPedido() { return pedido; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
}
