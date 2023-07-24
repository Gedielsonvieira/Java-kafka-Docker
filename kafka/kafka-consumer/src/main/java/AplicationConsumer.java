import expertostech.kafka.consumer.EventConsumer;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AplicationConsumer {
    public static void main(String[] args) {
        log.info("Iniciando a aplicação");
        EventConsumer consumer = new EventConsumer();
        consumer.messageConsumer();
    }

}
