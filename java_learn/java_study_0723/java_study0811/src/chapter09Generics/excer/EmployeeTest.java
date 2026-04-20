package chapter09Generics.excer;

import com.sun.source.tree.Tree;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.TreeSet;

public class EmployeeTest {
 @Test
    public void test1(){
     TreeSet<Employee> treeSet = new TreeSet<>();

     Employee e1 = new Employee("Tom",19,new Mydate(2003,1,21));
     Employee e2 = new Employee("Pom",29,new Mydate(2006,12,21));
     Employee e3 = new Employee("Lucy",13,new Mydate(2004,1,9));
     Employee e4 = new Employee("Yera",21,new Mydate(2005,7,1));


     treeSet.add(e1);
     treeSet.add(e2);
     treeSet.add(e3);
     treeSet.add(e4);

     for(Employee e: treeSet){
      System.out.println(e);
     }
 }
}
