package com.renbin.annotation_compiler;

import com.google.auto.service.AutoService;
import com.renbin.annotationdemo.BindView;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * 注解处理器，用来生成代码的
 * 使用前需要注册
 */
@AutoService(Processor.class)
public class AnnotationsCompiler extends AbstractProcessor {

    //1.支持的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //2.能用来处理哪些注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindView.class.getCanonicalName());
//        types.add(Override.class.getCanonicalName());
        return types;
    }

    //3.定义一个用来生成APT目录下面的文件的对象
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }
    /**
     * 自动生成找控件代码
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,"111111 ---------------"+set);
        //获取APP中所有用到了BindView注解的对象
        Set<? extends Element> elementsAnnotatedWith =  roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //开始对elementsAnnotatedWith进行分类
        //elementsAnnotatedWith包含
//        TypeElement//类
//        ExecutableElement//方法
//        VariableElement//属性
        Map<String, List<VariableElement>> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            VariableElement variableElement = (VariableElement) element;
            String activityName =  variableElement.getEnclosingElement().getSimpleName().toString();
            Class activityClazz =  variableElement.getEnclosingElement().getClass();

            List<VariableElement> variableElements = map.get(activityName);
            if (variableElements == null){
                variableElements = new ArrayList<>();
                map.put(activityName,variableElements);
            }

            variableElements.add(variableElement);
        }

        //开始动态生成找控件文件代码类
        if(map.size()>0){
            Writer writer = null;
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String activityName = iterator.next();
                List<VariableElement> variableElements = map.get(activityName);
                //得到包名
                TypeElement enclosingElement = (TypeElement) variableElements.get(0).getEnclosingElement();
                String packageName = processingEnv.getElementUtils().getPackageOf(enclosingElement).toString();
                try {
                    //创建_ViewBinding文件类
                    JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + activityName + "_ViewBinding");
                    //创建写入流
                    writer = sourceFile.openWriter();
                    writer.write("package "+packageName+";\n");
                    writer.write("import " + packageName + ".APT.IBinder;\n");
                    writer.write("public class " + activityName + "_ViewBinding implements IBinder<" +
                            packageName + "." + activityName + ">{\n");
                    writer.write(" @Override\n" +
                            " public void bind(" + packageName + "." + activityName + " target){\n");
                    for (VariableElement variableElement : variableElements) {
                        //得到名字
                        String variableName = variableElement.getSimpleName().toString();
                        //得到ID
                        int id = variableElement.getAnnotation(BindView.class).value();
                        //得到类型
                        TypeMirror typeMirror = variableElement.asType();
                        writer.write("target." + variableName + "=(" + typeMirror + ")target.findViewById(" + id + ");\n");
                    }
                    writer.write("\n}}");
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }
}