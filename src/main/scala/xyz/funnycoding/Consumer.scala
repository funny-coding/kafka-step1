package xyz.funnycoding

import java.util
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._
import java.util.regex.Pattern
import xyz.funnycoding.config.Settings._

object Consumer extends App {

  val props = new Properties()
  props.put("bootstrap.servers", kafkaServer)
  props.put("key.deserializer", keyDeserializer)
  props.put("value.deserializer", valueDeserializer)
  props.put("group.id", consumerGroupId)

  val consumer = new KafkaConsumer[String, String](props)
  consumer.subscribe(util.Collections.singletonList(topicName))
  val records = consumer.poll(1000000000).asScala.map{
    record=>{
      val value = Pattern.compile(" ").split(record.value())
      (value(0),
        value(1) match {
          case "[info]" => "info"
          case "[warn]" => "warn"
          case "[error]" => "error"
          case _ => "other"
        },
        record.value())
    }
  }.groupBy(record => (record._2, record._1)).mapValues ( _.size )
  records.foreach(record => println(record))
}
