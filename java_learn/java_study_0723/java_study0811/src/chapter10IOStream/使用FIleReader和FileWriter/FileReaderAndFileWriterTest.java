package chapter10IOStream.使用FIleReader和FileWriter;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;

public class FileReaderAndFileWriterTest {
    @Test
    public void test1() throws IOException {
        File file1 = new File("hello.txt");

        FileReader fr = new FileReader(file1);

        System.out.println((char)fr.read());
    }


    //输入流的书写规范
    @Test
    public void test2()  {
        FileReader fr = null;

        try {
            File file = new File("hello.txt");

            fr = new FileReader(file);

            char[] cbuffer = new char[5];
            int len;
            while((len = fr.read(cbuffer)) != -1){
                for (int i = 0;i < len;i ++){
                    System.out.print(cbuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fr != null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

   @Test
    public void test3() throws IOException {
       FileWriter fw = null;

       File file = new File("info.txt");

       //创造或者覆盖原有文件
       fw = new FileWriter(file,false);
       //在现有文件基础上追加
       fw = new FileWriter(file,true);

       fw.write("asduhuaf");
   }


   @Test
    public void test4() throws IOException {
        FileReader fr = null;
        FileWriter fw = null;

       try {
           File srcFile = new File("hello.txt");
           File destFile = new File("hello_copy.txt");

           fr = new FileReader(srcFile);
           fw = new FileWriter(destFile);

           char[] cbuffer = new char[5];
           int len;
           while((len = fr.read(cbuffer)) != -1){
               fw.write(cbuffer,0,len);
           }
       } catch (IOException e) {
          e.printStackTrace();
       } finally {
           try {
               fw.close();
           } catch (IOException e) {
               e.printStackTrace();
           }

           try {
               fr.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
       }


   }


}
