package model.pedido;

import model.cliente.Cliente;
import model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar para criar pedidos e controlar o vínculo de produtos.
 * Foi criada sem alterar a classe Pedido original da colega.
 *
 * Req 4: um Cliente pode fazer vários Pedidos; um Pedido contém apenas um Cliente;
 * um Pedido pode conter vários Produtos; um Produto deve pertencer apenas a um Pedido.
 */
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
