package chapter10IOStream.File类的实例;

import org.junit.Test;

import java.io.File;

public class FileTest {
    @Test
    public void test1(){
      File file1 =   new File("d:\\io\\hello.txt");
        // d:\io\hello.txt
    }

    @Test
    public void test2(){

        File file1 = new File("d:/io","abc.txt");
        File file2 = new File("abc","abn");

        File file3 = new File(file2,"ab.txt");

    }

    @Test
    public void test3(){
    File file1 = new File("hello.txt");
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.length());

        //public String[] list()
    File file2 = new File("E:/Wuthering Waves");
    String[] arr = file2.list();
    for(String s : arr){
        System.out.println(s);
    }

    //public File[] listFiles()
        

    }


}
