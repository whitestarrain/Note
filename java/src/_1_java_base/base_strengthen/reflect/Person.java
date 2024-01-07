package _1_java_base.base_strengthen.reflect;

class Person {
    private String name;
    private int age;
    public String a;

    public Person(String name) {
        this.name = name;
    }
    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
      return name+"   "+age;
    }
    public void eat(){
        System.out.println(name+":欧力给");
    }
    public  void eat(String what){
        System.out.println(name+":欧力给++"+what);
    }
    private void privatemethod(){
        System.out.println("private方法");
    }
}
