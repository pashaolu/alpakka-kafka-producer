package ng.com

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import akka.Done
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.Future
import scala.concurrent.duration._
import model._
import ng.com.data.TransactionGenerator
import spray.json._

object AlpakkaKafkaProducer extends App with JsonSupport {

  implicit val system = ActorSystem("data-generator")
  implicit val materializer = ActorMaterializer()

  final val bootstrapServers: String = ConfigFactory.load().getString("akka.kafka.bootstrap-servers")
  final val topic: String = ConfigFactory.load().getString("akka.kafka.topic")
  final val delay: FiniteDuration = ConfigFactory.load().getDuration("akka.tick-delay-duration", TimeUnit.MILLISECONDS).milli
  final val duration: FiniteDuration = ConfigFactory.load().getDuration("akka.tick-interval-duration", TimeUnit.MILLISECONDS).milli
  final val producerConfig: Config = ConfigFactory.load.getConfig("akka.kafka.producer")

  val producerSettings =
    ProducerSettings(producerConfig, new StringSerializer, new StringSerializer)
      .withBootstrapServers(bootstrapServers)

  val producerSink: Future[Done] =
    Source.tick(delay, duration, Done)
      .map(_ => TransactionGenerator.generate) //Transaction
      .map(_.toJson) //: JsValue
      .map(_.compactPrint) //: String (JSON formatted)
      .map{value =>
        new ProducerRecord[String, String](topic, value) //: Kafka ProducerRecord
      }
      .runWith(Producer.plainSink(producerSettings))
  //.runWith(Sink.foreach(println))


}
