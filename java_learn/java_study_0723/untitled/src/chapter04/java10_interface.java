package chapter04;

public class java10_interface {
    public static void main(String[] args) {
     //TODO 接口
     //基本语法： interface 接口名称 {规则属性，规则行为}
     //接口是抽象的
     //规则的属性必须为固定值，不能修改
     //属性和行为是公共的
     //属性是静态的
     //行为是抽象的
     //接口可以继承其他接口
     //类的对象需要遵循接口，在java中这个遵循成为实现，类需要实现接口，而且可以实现多个接口
     Computer c = new Computer();
     Light light1 = new Light();
     Light light2 = new Light();

     c.usb1 = light1;
     c.usb2 = light2;
     c.powerSupply();
    }
}

interface USBInterface{

}
interface USBSupply extends USBInterface{
    public void powerSupply();
}
interface USBReceive extends USBInterface{
    public void powerReceive();
}

class Computer implements USBSupply{

    public USBReceive usb1;
    public USBReceive usb2;

    public void powerSupply(){
        System.out.println("提供能源");
        usb1.powerReceive();
        usb2.powerReceive();
    }
}

class Light implements USBReceive{
    public void powerReceive(){
        System.out.println("电灯接收能源");
    }
}