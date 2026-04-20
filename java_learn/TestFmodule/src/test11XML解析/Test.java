package test11XML解析;

import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class Test {
    public static void main(String[] args) throws DocumentException {
        // 读取XML：
        // 1.创建一个xml解析器对象：（就是一个流）
        SAXReader sr = new SAXReader();

        // 2.读取xml文件，返回Document对象出来；封装成dom对象
        Document dom = sr.read(new File("TestFmodule/src/students.xml"));

        System.out.println(dom); // 这里就相当于将整个文档封装为Document对象了啊！

        // 3.获取根节点：（根节点只有一个啊！）
        Element studentsEle = dom.getRootElement();

        // 4.获取根节点下的多个子节点：
        Iterator<Element> it1 = studentsEle.elementIterator();
        while (it1.hasNext()) {
            // 4.1获取到子节点：
            Element studentEle = it1.next();//next()方法获取元素

            // 4.2获取子节点的属性：
            List<Attribute> atts = studentEle.attributes();
            for (Attribute a : atts) {
                System.out.println("该子节点的属性：" + a.getName() + "---" + a.getText());
            }

            // 4.3获取到子节点的子节点啊：
            Iterator<Element> it2 = studentEle.elementIterator();
            while (it2.hasNext()) {
                Element eles = it2.next();
                System.out.println("节点：" + eles.getName() + "---" + eles.getText());
            }

            // 5.每组输出后加一个换行：
            System.out.println();
        }
    }
}