package com.dexcom.avro

import org.scalatest.FunSpec

/**
  * Created by ay0716 on 9/11/16.
  */
class AvroScalaSerializerSpec extends FunSpec {
  describe("AvroScalaSerializer") {
    it("should serialize Person object") {
    val serializer = new AvroScalaSerializer[Person](classOf[Person])
    val person = Person("John", "Smith", 16)
    val bytesFromMarshal = serializer.marshal(person)
    assert(serializer.unmarshal(bytesFromMarshal).age == 16)
    }

    it("should serialize Organization object") {
      val companyName = "Dexcom"
      val companySerializer = new AvroScalaSerializer[Company](classOf[Company])
      val company = Company(companyName, "6310 Sequence Drive, San Diego, CA 92121", 2000)
      val companyAvro = companySerializer.marshal(company)
      assert(companySerializer.unmarshal(companyAvro).name == companyName)
    }
  }
}
