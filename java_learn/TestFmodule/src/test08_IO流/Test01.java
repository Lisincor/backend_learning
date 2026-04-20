package test08_IO流;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
 * 功能： 读取文件的内容：
 */

public class Test01 {
    public static void main(String[] args) throws IOException {
        //对文件进行操作，必须将文件封装为具体的File类的对象
        File f = new File("D:\\TEST.txt");
        //"管子" -- 流 == 输入字符流
        FileReader fr = new FileReader(f);

        //开始动作
        // int n1 =  fr.read();
        // System.out.println(n1);

        int n = fr.read();
        while(n != -1){
            System.out.println(n);
            n = fr.read();
        }

        fr.close();
    }
}
