package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @NoArgsConstructor
public class Employee extends Person implements ContractDepartment {
    private int employeeId;
    private float salary;
    private Positions position;
    private int contractDuration;
    private String entryDate;
    private int noticePeriod;
    private List<Project> projects;


    public Employee(String name,  int age, int employeeId, Positions position, int contractDuration, String entryDate, int noticePeriod) {
        super(name, age);
        this.employeeId = employeeId;
        this.position = position;
        this.contractDuration = contractDuration;
        this.entryDate = entryDate;
        this.noticePeriod = noticePeriod;
        this.projects = new ArrayList<>();

    }

    public Employee(String name, int age, int employeeId, float salary, Positions position, int contractDuration, String entryDate, int noticePeriod) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
        this.position = position;
        this.contractDuration = contractDuration;
        this.entryDate = entryDate;
        this.noticePeriod = noticePeriod;
    }

    public Employee(String name, int age, int employeeId, Positions position, int contractDuration, String entryDate, int noticePeriod, List<Project> projects) {
        super(name,  age);
        this.employeeId = employeeId;
        this.position = position;
        this.contractDuration = contractDuration;
        this.entryDate = entryDate;
        this.noticePeriod = noticePeriod;
        this.projects = projects;
    }

    @Override
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public void setContractDuration(int duration) {
        this.contractDuration = duration;
    }

    @Override
    public void setNoticePeriod(int noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public void calculateSalary() {
        switch (this.position) {
            case SOFTWARE_ENGINEER:
                this.salary = 13000f;
                break;
            case DEVOPS_ENGINEER:
                this.salary = 10000f;
                break;
            case UI_UX_DESIGNER:
                this.salary = 9000f;
                break;
            default:
                this.salary = 0f;
                break;
        }
    }

    public void assignToProject(Project project) {
        projects.add(project);
    }

    public void removeFromProject(Project project) {
        projects.remove(project);
    }
    public void removeFromProjectById(int projectId) {
        for(Project project :projects )
        {
            if(project.getId() == projectId)
                projects.remove(project);
        }
    }

    @Override
    public String toString() {
        if (projects != null) {
            return "employeeId=" + employeeId + "\n" +
                    "name='" + name + "'\n" +
                    "age=" + age+ "\n" +
                    "salary=" + salary + "\n" +
                    "position=" + position.name() + "\n" +
                    "contract Duration in months =" + contractDuration + "\n" +
                    "entryDate='" + entryDate + "'\n" +
                    "notice Period in days =" + noticePeriod + "\n" +
                    "projects=" + projects.toString() + "\n"
                    ;
        }else{
            return  "employeeId=" + employeeId + "\n" +
                    "name='" + name + "'\n" +
                    "age=" + age+ "\n"+
                    "salary=" + salary + "\n" +
                    "position=" + position.name() + "\n" +
                    "contract Duration in months =" + contractDuration + "\n" +
                    "entryDate='" + entryDate + "'\n" +
                    "notice Period in days =" + noticePeriod + "\n"
                    ;
        }
    }

}
