package com.bjsxt.scala

import java.util.Date

object Lesson_Function {
  def main(args: Array[String]): Unit = {
    /**
      * 1.方法定义
      *   1).方法体中最后返回值可以使用return,如果使用了return,那么方法体的返回值类型一定要指定
      *   2).如果方法体中没有return，默认将方法体中最后一行计算的结果当做返回值返回。方法体的返回值类型可以省略，会自动推断返回值的类型
      *   3).定义方法传入的参数一定要指定类型
      *   4).方法的方法体如果可以一行搞定，那么方法体的“{...}”可以省略
      *   5).如果定义方法时，省略了方法名称和方法体之间的“=”，那么无论方法体最后一行计算的结果是什么，都会被丢弃，返回Unit
      *   6).def 定义方法
      */
    def max(a:Int,b:Int)={
      if(a>b){
        a
      }else{
        b
      }
    }
    def max(a:Int,b:Int) =  if(a>b)  a else b

    println(max(100,20))

    /**
    * 2.递归方法
    *   递归方法要显式的声明函数的返回值类型
    */
    def fun(num:Int):Int= {
      if(num==1){
        1
      }else{
        num*fun(num-1)
      }
    }
    println(fun(5))

    /**
    * 3.参数有默认值的方法
    */
    def fun(a:Int=10,b:Int=20) = {
      a+b
    }

    println(fun(b=200))

    /***
    * 4.可变长参数的方法
    */
    def fun(s:String*) = {
      s.foreach(elem =>{println(elem)})
    //      for(elem<-s){
    //        println(elem)
    //      }
    }
    def fun(s:String*) = {
      s.foreach(elem=>{
        println(elem)
      })
    }

    fun("hello","a","b","c")
    /**
    * 5.匿名函数
    *  “=>”就是匿名函数，多用于方法的参数是函数时，常用匿名函数
    *
    */
    def fun1(a:Int,b:Int) ={
      a+b
    }
    def fun:(Int,Int)=>Int = (a:Int,b:Int)=>{
        a+b
    }

    println(fun(100,200))

    var fun1: String => Unit = (s: String) => {
      println(s)
    }
    fun1("hello")

    /**
    * 6.嵌套方法
    */
    def fun(num:Int) = {

      def fun1(a:Int):Int = {
        if(a==1){
          1
        }else{
          a*fun1(a-1)
        }
      }
      fun1(num)
    }

    println(fun(5))

    /**
    * 7.偏应用函数
    *   某些情况下，方法中参数非常多，调用这个方法非常频繁，每次调用只有固定的某个参数变化，其他都不变，可以定义偏应用来实现
    */
    def showLog(date:Date,log:String) ={
      println(s"date is $date   ,  log is $log")
    }
    val date = new Date()
    showLog(date,"a")
    showLog(date,"b")
    showLog(date,"c")

    def fun = showLog(date,_:String)
    fun("aaa")
    fun("bbb")
    fun("ccc")

    /**
    *  8.高阶函数
    *    1).方法的参数是函数
    *    2).方法的返回是函数  <要显式的写出方法的返回值类型,加 _ 就可以不显示的声明方法的返回值类型>
    *    3).方法的参数和返回都是函数
    */
    //方法的参数是函数
    def fun(a:Int,b:Int):Int = {
      a+b
    }

    val function: (Int, Int) => Int = fun _

    def fun1(f:(Int,Int)=>Int,s:String):String = {
      val i: Int = f(100,200)
      i+"#"+s
    }

    val result = fun1((a:Int,b:Int)=>{a*b},"scala")
    println(result)

    //方法的返回是函数
    def fun(s:String):(String,String)=>String = {
      def fun1(s1:String,s2:String): String= {
          s1+"~"+s2+"#"+s
      }
      fun1
    }
    println(fun("a")("b","c"))

    //方法的参数和返回都是函数

    def fun(ddd:(Int,Int)=>Int):(String,String)=>String = {
      val i: Int = ddd(1,2)
      def fun1(s1:String,s2:String):String = {
        s1+"@"+s2+"*"+i
      }
      fun1
    }
    println(fun((a,b)=>{a+b})("hello","world"))

    /**
    * 9.柯里化函数
    */
    def fun(a:Int,b:Int)(c:Int,d:Int)={
      a+b+c+d
    }
    println(fun(1,2)(3,4))
  }
}
