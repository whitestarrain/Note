package com.bjsxt.scala

/**
  * Match 模式匹配
  * 1.case _ 什么都匹配不上匹配，放在最后
  * 2.match 可以匹配值还可以匹配类型
  * 3.匹配过程中会有数值的转换
  * 4.从上往下匹配，匹配上之后会自动终止
  * 5.模式匹配外部的“{..}”可以省略
  *
  */
object Lesson_Match {

  def main(args: Array[String]): Unit = {
    val tp = (1,1.2,"abc",'a',true)
    val iter: Iterator[Any] = tp.productIterator
    iter.foreach(MatchTest)
  }

  def MatchTest(o:Any) =
    o match {
      case i:Int=>println(s"type is Int ,value = $i")
      case 1=>println("value is 1")
      case d:Double=>println(s"type is Double ,value = $d")
      case s:String=>println(s"type is String ,value = $s")
      case 'a'=>println("value is c")
      case _=>{println("no match...")}
    }



}
