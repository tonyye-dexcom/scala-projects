package com.dexcom.avro

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.apache.avro.file.{DataFileStream, DataFileWriter}
import org.apache.avro.reflect.{ReflectData, ReflectDatumReader, ReflectDatumWriter}

/**
  * Created by ay0716 on 9/11/16.
  */
class AvroScalaSerializer[T](c: Class[T]) {

  val schema = ReflectData.get().getSchema(c)
  def marshal(person: T): Array[Byte] = {
    val outputStream = new ByteArrayOutputStream()
    val reflectDatumWriter = new ReflectDatumWriter[T](schema)
    val writer = new DataFileWriter[T](reflectDatumWriter).create(schema, outputStream)
    writer.append(person)
    writer.close()
    outputStream.toByteArray()
  }

  def unmarshal(bytes: Array[Byte]): T = {
    val inputStream = new ByteArrayInputStream(bytes)
    val reflectDatumReader = new ReflectDatumReader[T](schema)
    val reader = new DataFileStream[T](inputStream, reflectDatumReader)
    val obj = reader.next()
    reader.close()
    inputStream.close()
    obj
  }
}
