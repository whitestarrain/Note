
不同的文件里面可能会有不同的方法重写。

比如C4和C4_Dynamic中都有Rel()方法，不过C4_Dynamic中每一条连线都会有标号，除此之外作用相同

```plantuml
' C4
!unquoted procedure Rel_U($from, $to, $label, $techn="", $descr="", $sprite="", $tags="", $link="")
$getRel($up("-","->>"), $from, $to, $label, $techn, $descr, $sprite, $tags, $link)
!endprocedure

' C4_Dynamic
!unquoted procedure Rel_U($from, $to, $label, $techn="", $descr="", $sprite="", $tags="", $link="")
$getRel($up("-","->>"), $from, $to, Index() + ": " + $label, $techn, $descr, $sprite, $tags, $link)
!endprocedure
```

# 参考资料

- [使用C4-PlantUML来快速的描述软件架构](https://gowa.club/%E8%BD%AF%E4%BB%B6%E6%9E%B6%E6%9E%84/%E4%BD%BF%E7%94%A8C4-PlantUML%E6%9D%A5%E5%BF%AB%E9%80%9F%E7%9A%84%E6%8F%8F%E8%BF%B0%E8%BD%AF%E4%BB%B6%E6%9E%B6%E6%9E%84.html)
- [github:C4-PlantUML](https://github.com/plantuml-stdlib/C4-PlantUML)
- [plantuml中文文档](https://plantuml.com/zh/guide)
- [plantuml_c4网页工具](https://kroki.io/)
- [plantuml图标](https://github.com/tupadr3/plantuml-icon-font-sprites/blob/master/devicons/index.md)
