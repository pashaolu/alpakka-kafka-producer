# Alpakka Kafka Producer Example
An example of Akka streams integration with Apache Kafka using Alpakka. Alpakka is built on top of Akka Streams to provide a DSL to implement stream-aware and reactive integration pipelines, with built-in support for backpressure. <br />
This example shows a simple use case of an unbounded streaming of transactions to Kafka for downstream processes to consume. 
 
## Domain

```scala
case class Customer(customerId: String, customerName: String, phone: String, address: String)
case class Product(productId:String, productName:String, price: Double, color: String)
case class Transaction(transactionId: String, dateTime: String, creditCard: String, creditCardType:String, product:Product, customer: Customer)
```
## Producer

* Alpakka Kafka: producer settings specify the broker address and the data types for Kafka’s key and value.
* Source.tick: to request data every 2 seconds 
* JSON support: through Spray JSON to convert the transaction objects into a JSON structure which gets published to a Kafka topic.
* Producer.plainSink sends the ProducerRecords stream elements to the specified Kafka topic.

## Dependencies and Prerequisites

* Scala Version: 2.12.12
* Bootstrap servers of Kafka cluster
* Dependencies:
   * ```"com.typesafe.akka" %% "akka-stream-kafka" % "2.0.4"```
   * ```"com.github.javafaker" % "javafaker" % "1.0.2"```
   * ```"io.spray" %% "spray-json" % "1.3.5"```