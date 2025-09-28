package _1_java_base.base_strengthen.annotation;

/*
* @Overrides :检测被该注解标注的方法是否重写父类方法(或实现接口接口)
* @Deprecated :将该注解标注的内容以过时，有更好的替代内容
* @SuppressWarnings :压制警告
*/

@SuppressWarnings("all")//该类所有警告就都没了
public class annotationDemo2 {
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Deprecated
    public void show1() {
        // 有缺陷
        // 不要删，否则会不兼容低版本
    }

    public void show2() {
        // 更优秀的show方法
    }

    public void demo() {
        show1();
    }
}