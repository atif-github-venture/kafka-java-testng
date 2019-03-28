package kafka;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaTopicQuery {
    public List<Serializable> simpleConsumerQuery(String topic, String[] searchFor, String matchkey) {
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer(topic);
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
            // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
            if (consumerRecords.count() == 0) {
                noMessageFound++;
                // If no message found count is reached to threshold exit loop.
                if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT) break;
                else continue;
            }
            if (matchkey.equalsIgnoreCase("value")) {
                for (ConsumerRecord<Long, String> record : consumerRecords) {
                    if (Arrays.stream(searchFor).allMatch(record.value()::contains)) {
                        return Arrays.asList(record.key(), record.value(), record.partition(), record.offset());
                    }
                }
            }
            if (matchkey.equalsIgnoreCase("key")) {
                for (ConsumerRecord<Long, String> record : consumerRecords) {
                    if (record.key().toString().equalsIgnoreCase(searchFor[0])) {
                        return Arrays.asList(record.key(), record.value(), record.partition(), record.offset());
                    }
                }
            }
            if (matchkey.equalsIgnoreCase("keyandvalue")) {
                for (ConsumerRecord<Long, String> record : consumerRecords) {
                    if (Objects.equals(record.key(), searchFor[0])) {
                        if (Arrays.stream(searchFor).allMatch(record.value()::contains)) {
                            return Arrays.asList(record.key(), record.value(), record.partition(), record.offset());
                        }
                    }
                }
            }

        }
        consumer.close();
        return Arrays.asList(new String[]{StringUtils.EMPTY});
    }

    public void runProducer() {
        Producer<Long, String> producer = ProducerCreator.createProducer();
        for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("test",
                    "This is record " + index);
            try {
                RecordMetadata metadata = null;
                try {
                    metadata = producer.send(record).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assert metadata != null;
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            }
            catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e.getMessage());
            }
        }
    }
}
