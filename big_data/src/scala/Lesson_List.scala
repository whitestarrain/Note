package com.bjsxt.scala

import scala.collection.mutable.ListBuffer

object Lesson_List {
  def main(args: Array[String]): Unit = {
    val list = ListBuffer[Int](1,2,3)
    list.append(4,5,6)
    list.+=:(100)
    list.foreach(println)

//    val list = List[String]("hello scala","hello java","hello spark","a","abc")

//    val i: Int = list.count(s => {
//      s.length < 4
//    })
//    println(i)
//    val result: List[String] = list.filter(s => {
//      "hello scala".equals(s)
//    })
//    result.foreach(println)

//    val result: List[Array[String]] = list.map(s => {
//      s.split(" ")
//    })

//    val result: List[String] = list.flatMap(s=>{s.split(" ")})
//    result.foreach(println)


//    result.foreach(arr=>{
//      println("新的数组")
//      arr.foreach(println)
//    })



//    val list = List[Int](1,2,3)
//    for(elem<-list){
//      println(elem)
//    }
//    list.foreach(println)
  }
}
