package com.dexcom.avro

/**
  * Created by ay0716 on 9/11/16.
  */
case class Person(firstName: String, lastName: String, age: Int = 0) {
  def this() {
    this("N/A", "N/A", 0)
  }
}
