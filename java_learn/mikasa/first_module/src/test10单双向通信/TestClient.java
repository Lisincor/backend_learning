package test10单双向通信;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
    public static void main(String[] args) throws IOException, IOException {
        System.out.println("客户端启动");

        //套接字;指定服务器的IP和端口
        Socket s = new Socket("192.168.1.8",6666);
        //对于程序来说，感受利用输出流在传送数据
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        //传送数据
        dos.writeUTF("类好啊，符木器");
          
        //对服务器返回的数据做处理：
        InputStream is =  s.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readUTF();
        System.out.println("服务器对我说" + str);

        //流以及网络资源关闭
        dos.close();
        os.close();
        s.close();
    }
}

