# Alpakka Kafka Producer Example

An example of Akka streams integration with Apache Kafka

## Data Generator: 
Generates fake data. This example shows a simple use case of an unbounded streaming of transactions to Kafka for downstream processes to consume
### Domain
```scala
case class Customer(customerId: String, customerName: String, phone: String, address: String)
case class Product(productId:String, productName:String, price: Double, color: String)
case class Transaction(transactionId: String, dateTime: String, creditCard: String, creditCardType:String, product:Product, customer: Customer)
```

## Dependencies and Prerequisites

* Scala Version: 2.12.12
* Bootstrap servers of Kafka cluster
* Dependencies:
   * ```"com.typesafe.akka" %% "akka-stream-kafka" % "2.0.4"```
   * ```"com.github.javafaker" % "javafaker" % "1.0.2"```
   * ```"io.spray" %% "spray-json" % "1.3.5"```