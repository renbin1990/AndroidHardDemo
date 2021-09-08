# 注解 
本质就是一个接口

使用 @interface 定义注解时，意味着它实现了 java.lang.annotation.Annotation 接口，即该注解就是一个Annotation。

###Target 对应参数说明

    package java.lang.annotation;
    public enum ElementType {
        TYPE,               /* 类、接口（包括注释类型）或枚举声明  */
        FIELD,              /* 字段声明（包括枚举常量）  */
        METHOD,             /* 方法声明  */
        PARAMETER,          /* 参数声明  */
        CONSTRUCTOR,        /* 构造方法声明  */
        LOCAL_VARIABLE,     /* 局部变量声明  */
        ANNOTATION_TYPE,    /* 注释类型声明  */
        PACKAGE             /* 包声明  */
    }

###Retention 对应参数说明（注解执行作用域）

    package java.lang.annotation;
    public enum RetentionPolicy {
        SOURCE,            /* Annotation信息仅存在于编译器处理期间，编译器处理完之后就没有该Annotation信息了  */
        CLASS,             /* 编译器将Annotation存储于类对应的.class文件中。默认行为  */
        RUNTIME            /* 编译器将Annotation存储于class文件中，并且可由JVM读入 */
    }

### APT技术自动查找控件，创建找控件类

    1.创建查找控件注解类BindView
    2.导入代码生成相关包
        dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])
            annotationProcessor 'com.google.auto.service:auto-service:1.0-rc4'
            implementation 'com.google.auto.service:auto-service:1.0-rc4'
            implementation project(path: ':annotationDemo')
        }
    3.创建绑定Activity类IBinder
    4.创建类AnnotationsCompiler实现AbstractProcessor并添加自动生成代码注解AutoService，编写代码生成逻辑
    5.创建页面绑定类XBButterknife，通过传入acticity，反射查找类名+_ViewBinding找到对应生成代码类，去绑定Activity
    6.页面使用

###动态设置点击事件
    参考annotation_click 
