package Controller;

import Model.Employee;
import Model.Positions;
import Model.Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private List<Employee> employees;
    private List<Project> projects;

    private DataManager() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();


    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addProject(Project project) {
        projects.add(project);
    }
    public void removeProject(int projectId) {
        Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            Project project = iterator.next();
            if (project.getId() == projectId) {
                iterator.remove();
            }
        }
    }

    public void removeEmployee(int employeeId) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmployeeId() == employeeId) {
                iterator.remove();
            }
        }
    }
    public boolean updateEmployee(int employeeId, Employee updatedEmployee) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employee.setName(updatedEmployee.getName());
                employee.setAge(updatedEmployee.getAge());
                employee.setEmployeeId(updatedEmployee.getEmployeeId());
                employee.setPosition(updatedEmployee.getPosition());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setContractDuration(updatedEmployee.getContractDuration());
                employee.setEntryDate(updatedEmployee.getEntryDate());
                employee.setProjects(updatedEmployee.getProjects());
                return true;
            }
        }
        return false;
    }
    public void intializeMockData() {
        DataManager.getInstance().getEmployees().add(new Employee("Hazem",22,1, Positions.SOFTWARE_ENGINEER,
                24,"5-3-2024",7));
        DataManager.getInstance().getEmployees().add(new Employee("Alice", 25, 2, Positions.DEVOPS_ENGINEER,
                18, "2-6-2023", 6));
        DataManager.getInstance().getEmployees().add(new Employee("Bob", 30, 3, Positions.UI_UX_DESIGNER,
                12, "10-10-2022", 3));
        DataManager.getInstance().getEmployees().add(new Employee("Charlie", 28, 4, Positions.PROJECT_MANAGER,
                36, "15-9-2025", 9));
        DataManager.getInstance().getEmployees().add(new Employee("Diana", 27, 5, Positions.SOFTWARE_ENGINEER,
                24, "20-11-2023", 4));
        DataManager.getInstance().getEmployees().add(new Employee("Dian", 27, 6, Positions.SOFTWARE_ENGINEER,
                24, "20-11-2023", 4,projects));
        DataManager.getInstance().getProjects().add(new Project(1,"Project A","implementing project A in 4 months"));
        DataManager.getInstance().getProjects().add(new Project(2, "Project B", "Testing project B functionality"));
        DataManager.getInstance().getProjects().add(new Project(3, "Project C", "Designing user interface for project C"));
        DataManager.getInstance().getProjects().add(new Project(4, "Project D", "Analyzing requirements for project D"));
        DataManager.getInstance().getProjects().add(new Project(5, "Project E", "Developing backend for project E"));
    }
}
