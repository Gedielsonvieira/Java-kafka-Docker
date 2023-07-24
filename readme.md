# Introdução Conceitual

## Apache Kafka

### O que é?

> "É uma plataforma distribuida de streaming", a ideia do kafka é mover e armazenar dados.

### Caracteristicas

- Plataforma: ele tem um ecossistemas em torno dele
- Trabalha de forma distribuida
- BD: Ele também é um Banco de dados (Todas as mensagens que trafegam pelo Kafka, temos a possibilidade de armazená-las
  e consultá-las e processá-las posteriormente)
- Utiliza o disco ao invés da memória para processar os dados
- Consegui armazenar os dados de forma distribuida, replicada e com tolerância a falhas
- Processa mensagens e eventos em tempo real e
- Trabalha no formato produtor e consumidor

Obs: Kafka NÃO é apenas um sistema tradicional de filas como RabbitMQ.

### Conceito Básico

- Produtor: Produz a mensagem, envia a mensagem para um sistema
- Consumidor: Consome a mensagem

> Explicação:
> Ao invés de um sistema A enviar uma msg direto para o sistema B, o sistema A ele passa a enviar uma mensagem para o
> kafka e o kafka mantem essa msg no Tópico e o outro sistema lê desse tópico. (Basicamente o dado passa por dentro de
> um
> tópico)

### O que é um "Tópico"?

- O Tópico é um Stream de dados (é o processo de transmissão de um fluxo contínuo de dados) que atua como um BD.

  > Explicação: Ao mandar mensagens para esse tópico, esse tópico vai ficar o tempo inteiro encaminhando essas mensagens
  para quem esta querendo ler.

- Todos os dados ficam armazenados, ou seja, cada Tópico tem seu "local" para armazenar seus dados.

- Tópico possui diversas partições
    - Cada partição é definida por um número. Ex: 0,1,2
    - Ao criar um Tópico somos obrigado a definir a quantidade de partições

No kafka não conseguimos garantir a ordem de entrega baseada na partição, não da para garantir que a ordem que foi
enviada para o topico é exatamente a ordem que o consumidor vai ler

Ao armazenar uma msg em um topico para cada mensagem é gerada um offset (posição que a msg esta armazenada no kafka)

### Kafka Cluster

- Cluster é um conjunto de Brokers
- Cada Broker é um servidor
- Cada Broker é responsável por armazenar os dados de uma partição

> Como cada broker (servidor) é responsavel por armazenar os dados de uma partição então começamos a entender o porque o
> kafka é distribuido.
> Ex: Ao criar um topico uma partição fica no broker 1 outra no broker 2 outra no broker 3.
> As mensagens ficam nas partições e essas partições não ficam nas mesmas máquinas e isso minimiza o risco de uma
> maquina cair e perdermos todos as mensagens de um tópico

**Replication factor** - São quantas réplicas queremos que uma partição tenha, guarda uma replica da informação

#### Service Discovery

> O kafka utiliza um sistema de service discovery chamado zookeeper e é ele que sabe de toda a conexão das réplicas dos
> tópicos

## Mensageria

Mensageria tem um broker que recebe mensagem e a apartir dessa mensagem outros serviços fazem o que tem que ser feito
levando em consideração a mensagem recebida

captura analisa e responde em tempo real

comunicação entre sistemas

"Mensageria é um conceito que define que sistemas distribuidos possam se comunicar por meio de troca de mensagens (
evento) sendo estas mensagens 'gerenciadas' por um Message Broker (servidor/módulo de mensagens)."

### Resumo

> Kafka é uma plataforma de streaming de dados, uma espécie de Banco de dados e a ideia do kafka é mover e armazenar
> dados, ele segue uma arquitetura produtor-consumidor e é nos tópicos onde os dados são publicados pelos produtores e
> consumidos pelos consumidores. Os produtores publicam os dados nos tópicos e os consumidores se inscrevem nesses
> tópicos
> para consumir os dados, os tópicos são divididos em partições, permitindo alta escalabilidade e tolerância a falhas e
> também a replicação de partições do Kafka permite que os dados sejam redundantes em diferentes nós do cluster,
> aumentando a resiliência do sistema.<br>
> Os tópicos também podem ser usados para agrupar os dados relacionados e categorizá-los de acordo com o assunto ou o
> tipo
> de informação. Cada mensagem em um tópico Kafka é identificada por um offset, que é um número único atribuído a cada
> mensagem em uma partição.<br>
> Consumidores de dados em Kafka podem ler mensagens em tópicos específicos a partir de um determinado offset ou a
> partir
> do início de uma partição.<br>
> O Kafka pode ser configurado para garantir a ordem de entrega das mensagens se isso for essencial para um determinado
> caso de uso.


---

## Produtores e consumidores

### Criando Produtores em Java

> Produzir mensagens se resume a criar um produtor, criar a mensagem, enviar e colocar algum listener

### Criando Consumidores em Java

- Cada serviço vai ter uma tarefa, um serviço em epecifico, por isso não é comum um consumidor ficar escutando mais de
  um tópico, porque como ele tem um objetivo em específico vai ficar escutando somente um tópico.

- Ao criar um consumer precisamos informar qual é o GROUP_ID_CONFIG

---

## Curiosidades

- Algumas informações básicas, o Kafka tem que armazenar em algum lugar e o lugar onde o Kafka armazena isso, por
  padrão, se chama zookeeper, ou seja, precisamos rodar o zookeeper antes do kafka


- Programação orientada a eventos também é conhecido como mensageria


- Todo consumer sempre precisa informar pro kafka que a mensagem que ele recebeu foi processada, caso contrário o kafka
  vai continuar entregando a mesma mensagem até que um consumer faça esse registro.


- Ao incluir mais um grupo de consumo mudamos o conceito de fila para tópico


- O kafka chama tudo de tópico mas para ter efetivamente esse funcionamento é necessário que tenhamos mais de um grupo
  de consumo.