package com.bjsxt.scala

/**
  * 一个类继承多个trait时，第一个关键字使用 extends，之后使用with
  * trait 不可以传参
  */
trait Read {
  def read(name:String) ={
    println(s"$name is reading...")
  }
}
trait Listen {
  def listen(name:String) ={
    println(s"$name is listening...")
  }
}

class Human() extends Read with Listen{

}
object Lesson_Trait1 {
  def main(args: Array[String]): Unit = {
    val h = new Human()
    h.read("zhangsan")
    h.listen("lisi")

  }
}
