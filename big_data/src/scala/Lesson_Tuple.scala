package com.bjsxt.scala

/**
  * 元组最多支持22个元素
  * 元组可以new 也可以不new，甚至可以直接写（）中写元素
  */
object Lesson_Tuple {
  def main(args: Array[String]): Unit = {
    val tuple1: Tuple1[String] = new Tuple1("hello")
    val tuple2: (String, Int) = new Tuple2("a", 100)
    val tuple3: (Int, Boolean, Char) = new Tuple3(1,true,'C')
    val tuple4: (Int, Double, String, Boolean) = Tuple4(1,3.4,"abc",false)
    val tuple6: (Int, Int, Int, Int, Int, String) = (1,2,3,4,5,"abc")
    val tuple22 = (1,2,3,4,5,6,7,8,9,10,11,12,"abc",14,15,16,17,18,19,20,21,22)

    println(tuple2.swap)
//    val value: String = tuple22._13


//    println(tuple4)
//    val iter: Iterator[Any] = tuple6.productIterator
//    iter.foreach(println)


//    while(iter.hasNext){
//        println(iter.next())
//    }

//    for(elem <- tuple6){
//      println(elem)
//    }

//    println(value)

  }
}
