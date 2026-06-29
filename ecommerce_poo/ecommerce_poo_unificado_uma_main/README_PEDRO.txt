PROJETO E-COMMERCE POO — VERSÃO UNIFICADA COM UMA MAIN

Esta versão junta a parte da colega com a parte do Pedro em um único fluxo de execução.

Arquivo principal para executar:
  src/main/Main.java

Classe principal:
  main.Main

O arquivo MainCompleto foi removido para evitar duas entradas principais.
Agora tudo roda em uma única Main:

1. Cria cliente pessoa física ou empresa.
2. Registra endereço.
3. Registra forma de pagamento.
4. Permite atendimento pelo Atendente Virtual.
5. Permite adicionar produtos ao carrinho.
6. Cria pedido usando GerenciadorPedidos.
7. Transfere produtos do carrinho para o pedido.
8. Escolhe uma das três formas de entrega:
   - Entrega Padrão
   - Entrega Rápida
   - Retirada na Loja
9. Finaliza o pedido com a forma de pagamento escolhida.
10. Exibe pedido, conta do cliente, lista de pedidos e atendimentos.
11. Permite fazer mais de um pedido para o mesmo cliente.

Arquivos originais de modelo foram mantidos:
  model/cliente/Cliente.java
  model/cliente/ClienteEmpresa.java
  model/cliente/Endereco.java
  model/pagamento/FormaPagamento.java
  model/pagamento/Pix.java
  model/pagamento/Boleto.java
  model/pagamento/Credito.java
  model/pagamento/Debito.java
  model/pedido/Carrinho.java
  model/pedido/Pedido.java
  model/produto/Produto.java

Arquivos adicionados para a parte do Pedro:
  model/atendimento/AtendenteVirtual.java
  model/entrega/Entrega.java
  model/entrega/EntregaPadrao.java
  model/entrega/EntregaRapida.java
  model/entrega/RetiradaLoja.java
  model/pedido/GerenciadorPedidos.java

Como compilar pelo terminal:
  javac -encoding UTF-8 -d out $(find src -name "*.java")

Como executar:
  java -cp out main.Main
