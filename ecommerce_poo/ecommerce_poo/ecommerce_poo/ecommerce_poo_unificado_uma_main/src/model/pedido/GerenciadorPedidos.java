package model.pedido;

import model.cliente.Cliente;
import model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPedidos {

    private List<Pedido> pedidos;
    private List<Produto> produtosJaVinculados;

    public GerenciadorPedidos() {
        this.pedidos = new ArrayList<>();
        this.produtosJaVinculados = new ArrayList<>();
    }

    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        pedidos.add(pedido);
        return pedido;
    }

    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto) {
        if (produtosJaVinculados.contains(produto)) {
            System.out.println("⚠ Produto '" + produto.getNome() + "' já pertence a outro pedido e não será adicionado novamente.");
            return;
        }

        pedido.adicionarProduto(produto);
        produtosJaVinculados.add(produto);
    }

    public void exibirPedidos() {
        System.out.println("\n===== PEDIDOS CADASTRADOS =====");
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getId() + " | Cliente: " + pedido.getCliente().getNome()
                    + " | Produtos: " + pedido.getProdutos().size()
                    + " | Status: " + pedido.getStatus());
        }
        System.out.println("================================\n");
    }

    public List<Pedido> getPedidos() { return pedidos; }
}
