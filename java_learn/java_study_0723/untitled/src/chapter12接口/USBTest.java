package chapter12接口;

public class USBTest {
    public static void main(String[] args) {
        Computer c = new Computer();

        //1.创建 接口实现类 的对象
        Printer p = new Printer();
        c.USBConnect(p);

        //2.创建 接口实现类 的匿名对象
        c.USBConnect(new Printer());

        //3.创建 接口匿名实现类 的对象
        USB usb1 = new USB() {
            @Override
            public void start() {
                System.out.println("万源市");
            }

            @Override
            public void end() {
                System.out.println("结束 ");
            }
        };
        c.USBConnect(usb1);

        //4.创建 接口匿名实现类 的匿名对象
        c.USBConnect(new USB() {
            @Override
            public void start() {
                System.out.println("123");
            }

            @Override
            public void end() {
                System.out.println("12334");
            }
        });
    }
}

interface USB{
    void start();
    void end();
}

class Computer{

    void USBConnect(USB usb){
        System.out.println("电脑启动");
        usb.start();

        usb.end();
        System.out.println("电脑关闭");
    }
}

class Printer implements USB {
    @Override
    public void start() {
        System.out.println("打印机开始工作");
    }

    @Override
    public void end() {
        System.out.println("打印机结束工作");
    }
}