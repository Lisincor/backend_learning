package ServiceTesr;

import Practice03.team.domain.Employee;
import Practice03.team.service.NameListService;
import Practice03.team.service.TeamException;
import org.junit.Test;

public class Tesst {
    public static void main(String[] args) {
        NameListService n = new NameListService();

        Employee employee[] = n.getAllEmployees();

        try{
            Employee employee1 = n.getEmployee(3);
            System.out.println(employee1);
        }catch(TeamException t){
            System.out.println(t.getMessage());
        }

    }
}
