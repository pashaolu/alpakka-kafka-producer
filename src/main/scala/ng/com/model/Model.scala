package ng.com.model

import spray.json._
import DefaultJsonProtocol._

case class Customer(customerId: String, customerName: String, phone: String, address: String)
case class Product(productId:String, productName:String, price: Double, color: String)
case class Transaction(transactionId: String, dateTime: String, creditCard: String, creditCardType:String, product:Product, customer: Customer)

trait JsonSupport extends DefaultJsonProtocol {
  implicit val customerFormat = jsonFormat4(Customer)
  implicit val productFormat = jsonFormat4(Product)
  implicit val transactionFormat = jsonFormat6(Transaction)
}
