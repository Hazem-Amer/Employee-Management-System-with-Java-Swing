package View;
import Controller.EmployeeController;
import Model.Employee;
import Model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProjectDialog extends JDialog {

    private JTextField idField;
    private JTextField titleField;
    private JTextField descriptionField;
    private EmployeeController employeeController;
    private Employee employee;

    public AddProjectDialog(JFrame parent, Employee employee) {
        super(parent, "Add Project", true);
        setSize(300, 200);
        setLayout(new GridLayout(0, 2));
        this.employee = employee;
        employeeController = new EmployeeController();

        idField = new JTextField();
        titleField = new JTextField();
        descriptionField = new JTextField();

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Description:"));
        add(descriptionField);

        JButton addButton = new JButton("Add ");
        add(addButton);
        JButton removeButton = new JButton("Remove");
        add(removeButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject();
                dispose();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProject();
                dispose();
            }
        });

        setVisible(true);
    }


    private void addProject() {
        int projectId = Integer.parseInt(idField.getText());
        String projectTitle = titleField.getText();
        String projectDesc = descriptionField.getText();
        Project project = new Project(projectId, projectTitle, projectDesc);

        employeeController.addProject(project);

        System.out.println(project.toString());
    }

    private void removeProject() {
            int projectId = Integer.parseInt(idField.getText());
            employeeController.removeProject(projectId);
            if(employee!= null)
                employeeController.removeEmployeeFromProject(employee.getEmployeeId(),projectId);

    }
}
