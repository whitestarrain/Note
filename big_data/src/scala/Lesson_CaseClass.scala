package com.bjsxt.scala

case class Person1(var name:String,age:Int)

object Lesson_CaseClass {
  def main(args: Array[String]): Unit = {
    val p1 = new Person1("zhangsan",18)
    val p2 = new Person1("zhangsan",18)
    println(p1.equals(p2))
    println(p1)
  }
}
