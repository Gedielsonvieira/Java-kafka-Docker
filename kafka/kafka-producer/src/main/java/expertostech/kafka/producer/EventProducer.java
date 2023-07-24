package expertostech.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

@Slf4j
public class EventProducer {

    private final KafkaProducer<String, String> producer;

    public EventProducer() {
        producer = createProducer();
    }

    private KafkaProducer<String, String> createProducer() {
        if (producer != null) {
            return producer;
        }

        Properties properties = new Properties();

        //É o servidor/broker de mensagens kafka
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        //Como vai ser serializada a chave de identificação que o kafka usa para identificar as mensagens
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<String, String>(properties);

    }

    public void sendMessage() {
        String key = UUID.randomUUID().toString();

        String menssage = " | Nova Mensagem | " + key;

        log.info("Iniciando envio da mensagem");
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("registro-evento", key, menssage);
        producer.send(record);
        // A chamada flush() garante que todas as mensagens enviadas tenham sido realmente concluídas
        producer.flush();
        producer.close();
        log.info("Mensagem enviada com sucesso[{}]", menssage);
    }
}