package xyz.funnycoding.config

import com.typesafe.config.{Config, ConfigFactory}

object Settings {

  lazy val config: Config = ConfigFactory.load()
  val kafkaServer: String = config.getString("bootstrap.servers")
  val keyDeserializer: String = config.getString("key.deserializer")
  val valueDeserializer: String = config.getString("value.deserializer")
  val keySerializer: String = config.getString("key.serializer")
  val valueSerializer: String = config.getString("value.serializer")
}
