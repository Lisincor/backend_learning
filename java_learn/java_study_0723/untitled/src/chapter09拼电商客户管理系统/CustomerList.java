package chapter09拼电商客户管理系统;

public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];//引用变量赋值
    }

    public boolean addCustomer(Customer customer){

        if(total < customers.length){
            customers[total] = customer;
            total++;
            return true;
        }else{
            return false;
        }
    }

    public boolean replaceCustomer(int index,Customer cust) {
        if (index >= 0 && index < total) {
            customers[index] = cust;
            return true;
        } else {
            return false;
        }
    }

    public boolean delCustomer(int index){
        if(index < 0  || index >= total){
            return false;
        }

        for(int i = index; i < total - 1; i ++){
            customers[i] = customers[i+1];
        }
        customers[total - 1] = null;
        total--;
        return true;
    }

    public Customer[] getAllCustomers(){
        Customer[] cu = new Customer[total];

        for(int i = 0; i < total;i ++){
            cu[i] = customers[i];
        }

        return cu;
    }

    public  Customer getCustomer(int index){
        if(index < 0 || index >= total){
            return null;
        }
        return customers[index];
    }

    public int getTotal(){
        return total;
    }
}
