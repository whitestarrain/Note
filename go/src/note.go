package main

import "fmt"

func main1() {
	// 一行声明一个变量
	// 使用 var会进行隐式初始化
	var name = "name"
	name1 := "name1"

	// 默认float64类型，造成内存浪费
	num1 := 0.89
	var num2 float32 = 0.89
	fmt.Print(name, name1, num1, num2)

	// 多个变量一起声明
	var (
		name2  string
		age    int
		gender string
	)
	fmt.Printf(name2, age, gender)

	// 编译器自动推导类型，但这种方法有个限制就是，只能用于函数内部
	name4 := "Go编程时光"
	// 等价于
	var name5 string = "Go编程时光"
	// 等价于
	name6 := "Go编程时光"
	fmt.Printf(name4, name5, name6)

	num3 := 19
	a := float64(num3) + num1
	fmt.Printf("%f", a)

	name1, name2 = "name1", "name2"

	var num4 = 0b1
	var num5 = 0x5
	print(num4, num5)
}

var var1 string = "var1"

func main2() {
  println("var1-1:",var1)
	// var1 = "change+var1"
  var1 := "change+var1"
  println("var1-2:",var1)

}
func main3(){
    var scores map[string]int
    scores = map[string]int{"english": 80, "chinese": 85}
    println(scores)
}

func main() {
	// main1()
	main2()
  println("var1-3:",var1)
  main3()
}
