package model.atendimento;

import model.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Atendente Virtual do e-commerce.
 * Req 5: Um Cliente pode entrar em contato com um Atendente Virtual.
 * Um Atendente Virtual pode atender vários Clientes.
 *
 * Demonstra: associação com Cliente, encapsulamento, ArrayList e sobrecarga.
 */
public class AtendenteVirtual {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private List<Cliente> clientesAtendidos;

    /** Construtor padrão */
    public AtendenteVirtual() {
        this("Assistente Virtual");
    }

    /** Construtor com nome do atendente */
    public AtendenteVirtual(String nome) {
        this.id = contadorId++;
        this.nome = nome;
        this.clientesAtendidos = new ArrayList<>();
    }

    /** Atende um cliente com uma mensagem padrão */
    public void atenderCliente(Cliente cliente) {
        clientesAtendidos.add(cliente);
        System.out.println("✔ " + nome + " está atendendo o cliente: " + cliente.getNome());
        System.out.println("  Olá, " + cliente.getNome() + "! Como posso ajudar no seu pedido?");
    }

    /**
     * Sobrecarga: atende um cliente recebendo também o assunto da dúvida.
     */
    public void atenderCliente(Cliente cliente, String assunto) {
        clientesAtendidos.add(cliente);
        System.out.println("✔ " + nome + " está atendendo o cliente: " + cliente.getNome());
        System.out.println("  Assunto do atendimento: " + assunto);
        responderDuvida(assunto);
    }

    public void responderDuvida(String assunto) {
        System.out.println("  Resposta automática: sua solicitação sobre '" + assunto + "' foi registrada.");
    }

    public void exibirAtendimentos() {
        System.out.println("\n===== ATENDIMENTOS DO " + nome.toUpperCase() + " =====");
        System.out.println("ID do atendente: " + id);
        System.out.println("Total de clientes atendidos: " + clientesAtendidos.size());

        for (Cliente cliente : clientesAtendidos) {
            System.out.println("  - " + cliente.getNome() + " | " + cliente.getEmail());
        }
        System.out.println("=====================================\n");
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Cliente> getClientesAtendidos() { return clientesAtendidos; }
}
