package Practice03.team.domain;

import Practice03.team.service.Status;

public class Programmer extends Employee{
    private int memberId; //开发人员中的TID
    private Status status = Status.FREE;
    private Equipment equipment;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetails() + "\t程序员\t" + status + "\t\t\t\t\t" +equipment.getDescription();
    }

    protected String getBasicDetailsForTeam(){
        return memberId + "/" + getId() + "\t" + getName() + "\t" +
                getAge() + "\t" + getSalary() + "\t";
    }

    public String getDetailsForTeam(){
        return getBasicDetailsForTeam() + "程序员";
    }
}
