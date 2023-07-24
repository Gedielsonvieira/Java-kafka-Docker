import expertostech.kafka.producer.EventProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AplicationProducer {
    public static void main(String[] args) {
        log.info("Iniciando a aplicação");
        EventProducer eventProducer = new EventProducer();
        eventProducer.sendMessage();
    }

}
