package ng.com.data

import java.util.{Calendar, UUID}
import java.text.SimpleDateFormat

import com.github.javafaker.Faker
import ng.com.model.{Customer, Product, Transaction}


object TransactionGenerator {
  val faker = new Faker()

  val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

  def generate: Transaction = {

    val customer = Customer(
      faker.idNumber().valid(),
      faker.name().fullName(),
      faker.phoneNumber().cellPhone(),
      faker.address().fullAddress()
    )
    val product = Product(
      faker.idNumber().valid(),
      faker.commerce().productName(),
      faker.commerce().price(10, 200).toDouble,
      faker.commerce().color()
    )

    Transaction(
      UUID.randomUUID().toString,
      sdf.format(Calendar.getInstance.getTime),
      faker.business().creditCardNumber(),
      faker.business().creditCardType(),
      product,
      customer
    )
  }

}
