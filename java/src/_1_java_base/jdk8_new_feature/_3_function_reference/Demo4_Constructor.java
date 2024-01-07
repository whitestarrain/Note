package _1_java_base.jdk8_new_feature._3_function_reference;

class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
    定义一个创建Person对象的函数式接口
 */
@FunctionalInterface
interface PersonBuilder {
    //定义一个方法,根据传递的姓名,创建Person对象返回
    Person builderPerson(String name);
}

class Demo4_ClassConstructor {
    //定义一个方法,参数传递姓名和PersonBuilder接口,方法中通过姓名创建Person对象
    public static void printName(String name,PersonBuilder pb){
        Person person = pb.builderPerson(name);
        System.out.println(person.getName());
    }
    public static void main(String[] args) {
        //调用printName方法,方法的参数PersonBuilder接口是一个函数式接口,可以传递Lambda
        printName("艹",(String name)->{
            return new Person(name);
        });
        /*
            使用方法引用优化Lambda表达式
            构造方法new Person(String name) 已知
            创建对象已知 new
            就可以使用Person引用new创建对象
         */
        printName("草",Person::new);//使用Person类的带参构造方法,通过传递的姓名创建对象
    }
}

@FunctionalInterface
interface ArrayBuilder{
    int[] arrayBuilder(int l);
}
class Demo4_ArrayConstructor {
    public static int[] getArray(int l,ArrayBuilder ab) {
        return ab.arrayBuilder(l);
    }
    public static void main(String[] args) {
        //lambfa表达式
        int[] a=getArray(4,l->new int[l]);
        System.out.println(a.length);

        //数组new方法引用
        int[] b=getArray(5, int[]::new);
        System.out.println(b.length);
    }
}