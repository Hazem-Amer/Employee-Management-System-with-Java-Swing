package Controller;

import Model.Employee;
import Model.Project;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {


    public EmployeeController() {
    }


    public void addEmployee(Employee employee) {
        if (employee != null) {
            employee.calculateSalary();
            DataManager.getInstance().getEmployees().add(employee);
        }

    }

    public void removeEmployee(int employeeId) {
        DataManager.getInstance().removeEmployee(employeeId);


    }

    public void addProject(Project project) {
        DataManager.getInstance().getProjects().add(project);
    }

    public void removeProject(int projectId) {
        DataManager.getInstance().removeProject(projectId);
    }


    public void assignProjectsToEmployee(int employeeId, List<Integer> projectIds) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            for (int projectId : projectIds) {
                Project project = findProjectById(projectId);
                if (project != null) {
                    employee.assignToProject(project);
                } else {
                    System.out.println("Project with ID " + projectId + " not found.");
                }
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }

    }

    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : DataManager.getInstance().getEmployees()) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public Project findProjectById(int projectId) {
        for (Project project : DataManager.getInstance().getProjects()) {
            if (project.getId() == projectId) {
                return project;
            }
        }
        return null;
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            Project project = findProjectById(projectId);
            if (project != null) {
                DataManager.getInstance().removeProject(project.getId());
                System.out.println("Project with ID " + projectId + " has been removed");
            } else {
                System.out.println("Project with ID " + projectId + " not found.");
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }
    }

    public boolean updateEmployee(int employeeId, Employee updatedEmployee) {
        return DataManager.getInstance().updateEmployee(employeeId,updatedEmployee);
    }

    public List<Project> getAllProjects(){
        return DataManager.getInstance().getProjects();
    }

}
