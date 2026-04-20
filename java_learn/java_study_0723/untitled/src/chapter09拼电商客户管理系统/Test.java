package chapter09拼电商客户管理系统;

public class Test {
    public static void main(String[] args) {
        CustomerList culist = new CustomerList(5);

        Customer cu1 = new Customer();
        cu1.setName("战后高三");
        cu1.setAge(19);
        cu1.setEmail("1029@126.com");
        cu1.setGender('男');
        cu1.setPhone("191239881238");

        Customer cu2 = new Customer();
       cu2.setName("战后高4");
       cu2.setAge(19);
       cu2.setEmail("1029@126.com");
       cu2.setGender('男');
       cu2.setPhone("191239881238");

        culist.addCustomer(cu1);
        culist.addCustomer(cu2);

        System.out.println(culist.getTotal());

        System.out.println(culist.getCustomer(1).getName());

        Customer cu3 = new Customer();
        cu3.setName("战后高5");
        cu3.setAge(19);
        cu3.setEmail("1029@126.com");
        cu3.setGender('男');
        cu3.setPhone("191239881238");

        culist.replaceCustomer(0,cu3);

        System.out.println(culist.getCustomer(0).getName());

    }
}
