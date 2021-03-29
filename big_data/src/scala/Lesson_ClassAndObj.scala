
/**
  * Scala ：
  * 1.Scala object相当于java中的单例，object中定义的全是静态的,相当于java中的工具类，Object不可以传参，对象要传参，使用apply方法。
  * 2.Scala中定义变量使用var，定义常量使用val ,变量可变，常量不可变.变量和常量类型可以省略不写，会自动推断。
  * 3.Scala中每行后面都会有分号自动推断机制，不用显式写出“;”
  * 4.建议在Scala中命名使用驼峰命名法
  * 5.Scala类中可以传参，传参一定要指定类型，有了参数就有了默认了构造。类中的属性默认有getter和setter方法
  * 6.类中重写构造时，构造中第一行必须先调用默认的构造 。def this(....){....}
  * 7.Scala中当new class 时，类中除了方法不执行【除了构造方法】，其他都执行。
  * 8.在同一个scala文件中，class名称和Object名称一样时，这个类叫做个对象的伴生类，这个对象叫做这个类的伴生对象，他们之间可以互相访问私有变量。
  */

class Person(xname :String,xage:Int){
  private val name = xname
  var age = xage
  var gender = 'M'

//  println("******* Person Class *********")

  def this(yname:String,yage:Int,ygender:Char){
    this(yname,yage)
    this.gender = ygender
  }

  def  sayName() = {
//    println("hello world..."+Lesson_ClassAndObj.name)
  }
//  println("====== Person Class =======")
}

object Lesson_ClassAndObj {
//  println("####### Lesson_ClassAndObj Object #####")
//  val name = "wangwu"

//  def apply(i:Int) = {
//      println("Score is "+i)
//  }
//  def apply(i:Int,s:String) = {
//      println("name is "+s+",score is "+i)
//  }

  def main(args: Array[String]): Unit = {

    /**
      * while
      * do...while...
      */
//    var i = 0
//    do{
//      println(s" 第 $i 次求婚。。。")
//      i += 1
//    }while(i<100)

//    while(i<100){
//      println(s" 第 $i 次求婚。。。")
////      i = i+1
//      i += 1
//    }








    /**
      * for
      */
//    val r = 1.to(10,2)
//    val r1 = 1 until (10,3)
//    println(r)
//    println(r1)
//    for(i <- 1 to 10){
//      println(i)
//    }

    //小九九
//    for( i<- 1 until  10){
//      for(j <- 1 until 10){
//        if(i>=j){
//          print(i+"*"+j+"="+i*j+"\t")
//        }
//        if(i==j){
//          println()
//        }
//      }
//    }
//    for( i<- 1 until  10;j <- 1 until 10){
//        if(i>=j){
//          print(i+"*"+j+"="+i*j+"\t")
//        }
//        if(i==j){
//          println()
//        }
//    }

//    for(i <- 1 to 1000  if(i>500) if(i%2==0)){
//      println(i)
//    }
//    val result = for(i <- 1 to 100  if(i>50) if(i%2==0)) yield  i
//    println(result)
    /**
      * if...else...
      */
//    val age = 20
//    if(age<=20){
//      println("age <=20")
//    }else if(age>20&&age<=30){
//      println("20<20<=30")
//    }else{
//      println("age>30")
//    }
    /**
      * 类和对象
      *
      */
    //    Lesson_ClassAndObj(1000,"zhaoliu")


//    val p = new Person("zhangsan",20)
//    println(p.name)

//    println(p.gender)
//    val p1=new Person("diaochan",18,'F')
//    println(p1.gender)
//    p.age = 200
//    println(p.name)
//    println(p.age)
//    p.sayName()


//    val a = 100
//    var b = 200
//    b = 300
//    println(b)
  }
}
