package com.dexcom.avro

/**
  * Created by ay0716 on 9/11/16.
  */
case class Company(name: String, address: String, employeeCount: Int) {
  def this() {
    this("N/A", "N/A", 0)
  }
}
