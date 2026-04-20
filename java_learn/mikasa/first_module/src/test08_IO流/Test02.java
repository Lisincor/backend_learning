package test08_IO流;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test02 {
    public static void main(String[] args) throws IOException {
        //程序中的字符串
        String str = "abc你好";
        //文件:
        File f = new File("D:\\demo.txt");
        //字符输出流
        FileWriter fw = new FileWriter(f);
        //动作
        fw.write(str);
        //关闭输出流
        fw.close();
    }
}
