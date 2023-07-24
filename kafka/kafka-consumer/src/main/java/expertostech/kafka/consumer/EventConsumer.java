package expertostech.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

@Slf4j
public class EventConsumer {

    private final KafkaConsumer<String, String> consumer;

    public EventConsumer() {
        consumer = createConsumer();
    }

    private KafkaConsumer<String, String> createConsumer() {
        if (consumer != null) {
            return consumer;
        }

        Properties properties = new Properties();

        //É o servidor/broker de mensagens kafka
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        //Como vai ser deserializada a chave de identificação que o kafka usa para identificar as mensagens
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //Ao criar um consumer precisamos informar qual é o GROUP_ID_CONFIG
        //Indica quantos consumidores irão receber a mesma mensagem
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "default");

        return new KafkaConsumer<String, String>(properties);
    }

    public void messageConsumer() {

        List topicos = new ArrayList<>();
        topicos.add("registro-evento");

        //subscribe - consumer está ouvindo o tópico
        consumer.subscribe(topicos);

        log.info("Iniciando consumo da mensagem");

        boolean continues = true;

        while (continues) {
            var records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                log.info("Topico:{}, Partição:{}, Mensagem:{}, Offset:{}", record.topic(), record.partition(), record.value(),
                        record.offset());
                if (record.value().equals("FECHAR")) {
                    continues = false;
                }
            }
        }
        consumer.close();
    }
}