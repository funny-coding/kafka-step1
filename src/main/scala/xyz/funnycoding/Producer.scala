package xyz.funnycoding

import java.util.Properties
import java.util.concurrent.Future

import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Callback
import scala.io.Source
import Settings._

object Producer extends App {
  val  props = new Properties()
  props.put("bootstrap.servers", kafkaServer)
  props.put("key.serializer", keySerializer)
  props.put("value.serializer", valueSerializer)
  val producer = new KafkaProducer[String, String](props)

  val filename = "host.log"
  var i = 0
  for (line <- Source.fromResource(filename).getLines) {
    i += 1
    val record = new ProducerRecord[String, String]("logs","line-"+i , line)
    val metaF: Future[RecordMetadata] = producer.send(record, new Callback{
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        if (exception != null) {
          println(exception.toString)
        }
      }
    })
  }
  producer.close()
}
