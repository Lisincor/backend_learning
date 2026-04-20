import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_test {
    public static void main(String[] args) {

        List<Employee> list = new ArrayList<>();
        list.add(new Employee("张三",23330,21));
        list.add(new Employee("李四",33330,10));


        System.out.println(list.stream().map(e -> e.getSalary()).reduce(Integer::sum));
        System.out.println(list.stream().map(Employee::getSalary).reduce(Integer::sum));

        List<Employee> collect = list.stream().filter(employee -> employee.getSalary() > 30000).collect(Collectors.toList());
        System.out.println(collect);

    }
}
