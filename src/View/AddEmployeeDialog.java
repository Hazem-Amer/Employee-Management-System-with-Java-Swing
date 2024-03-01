package View;

import Controller.DataManager;
import Controller.EmployeeController;
import Model.Employee;
import Model.Positions;
import Model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class AddEmployeeDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<Positions> positionComboBox;
    private JList<Integer> projectList;
    private JList<Integer> myProjectsList;
    private DefaultListModel<Integer> projectListModel;
    private DefaultListModel<Integer> myProjectsListModel;
    private JTextField contractDurationField;
    private JTextField entryDateField;
    private JTextField noticePeriodField;
    private JButton addButton;
    private EmployeeController employeeController;
    private HashSet<Integer> doubleSelectedProjectIds;
    private HashSet<Integer> doubleSelectedMyProjectIds;
    private Employee employee;

    public AddEmployeeDialog(JFrame parent, Employee employee) {
        super(parent, "Add Employee", true);
        employeeController = new EmployeeController();
        setSize(400, 400);
        setLayout(new GridLayout(0, 2));
        this.employee = employee;
        if (employee != null) {
            intializeComponenetsForUpdate();
            handleListenersForUpdate();
        } else {
            initComponentsForAdd();
            handleListeners();
        }


    }

    private void handleListenersForUpdate() {
        doubleSelectedMyProjectIds = new HashSet<>();
        doubleSelectedProjectIds = new HashSet<>();
        int empId = Integer.valueOf(idField.getText());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = projectList.locationToIndex(e.getPoint());
                    int selectedItem = projectListModel.getElementAt(index);
                    //get the entered employee id
                    doubleSelectedProjectIds.add(selectedItem);
                    System.out.println("Project with id: " + selectedItem + " stored to be added");
                }
            }

        });
        setVisible(true);
        myProjectsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked on myProjectsList");
                if (e.getClickCount() == 2) {
                    int index = myProjectsList.locationToIndex(e.getPoint());
                    int selectedItem = myProjectsListModel.getElementAt(index);
                    doubleSelectedMyProjectIds.add(selectedItem);
                    System.out.println("Project with id: " + selectedItem + " stored to be removed");
                }
            }
        });
    }


    private void intializeComponenetsForUpdate() {
        idField = new JTextField();
        idField.setText(String.valueOf(employee.getEmployeeId()));
        nameField = new JTextField();
        nameField.setText(employee.getName());
        ageField = new JTextField();
        ageField.setText(String.valueOf(employee.getAge()));
        positionComboBox = new JComboBox<>(employee.getPosition().values());

        projectListModel = new DefaultListModel<>();
        projectList = new JList<Integer>(projectListModel);
        projectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane projectScrollPane = new JScrollPane(projectList);
        setProjectsList((ArrayList<Project>) employeeController.getAllProjects());


        myProjectsListModel = new DefaultListModel<>();
        myProjectsList = new JList<Integer>(myProjectsListModel);
        myProjectsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane myProjectsScrollPane = new JScrollPane(myProjectsList);
        setMyProjectsList((ArrayList<Project>) employee.getProjects());

        contractDurationField = new JTextField();
        contractDurationField.setText(String.valueOf(employee.getContractDuration()));
        entryDateField = new JTextField();
        entryDateField.setText(employee.getEntryDate());
        noticePeriodField = new JTextField();
        noticePeriodField.setText(String.valueOf(employee.getNoticePeriod()));

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Position"));
        add(positionComboBox);
        add(new JLabel("Projects (double click to select)"));
        add(projectScrollPane);
        add(new JLabel("MyProjects (double click to select for remove)"));
        add(myProjectsScrollPane);

        add(new JLabel("Contract Duration"));
        add(contractDurationField);
        add(new JLabel("Entry Date"));
        add(entryDateField);
        add(new JLabel("Notice Period"));
        add(noticePeriodField);

        addButton = new JButton("update");
        add(addButton);

    }

    private void initComponentsForAdd() {
        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        positionComboBox = new JComboBox<>(Positions.values());

        projectListModel = new DefaultListModel<>();
        projectList = new JList<Integer>(projectListModel);
        projectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane projectScrollPane = new JScrollPane(projectList);
        setProjectsList((ArrayList<Project>) DataManager.getInstance().getProjects());

        contractDurationField = new JTextField();
        entryDateField = new JTextField();
        noticePeriodField = new JTextField();

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Position"));
        add(positionComboBox);
        add(new JLabel("Projects (double click to select)"));
        add(projectScrollPane);
        add(new JLabel("Contract Duration"));
        add(contractDurationField);
        add(new JLabel("Entry Date"));
        add(entryDateField);
        add(new JLabel("Notice Period"));
        add(noticePeriodField);

        addButton = new JButton("Add");
        add(addButton);

    }

    private void handleListeners() {
        doubleSelectedProjectIds = new HashSet<>();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
                dispose();
            }
        });
        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = projectList.locationToIndex(e.getPoint());
                    int selectedItem = projectListModel.getElementAt(index);
                    doubleSelectedProjectIds.add(selectedItem);
                    System.out.println("Project with id: " + selectedItem + "added");
                }
            }

        });
        setVisible(true);
    }

    public void setProjectsList(ArrayList<Project> projects) {
        projectListModel.clear();
        if (projects != null) {
            for (Project project : projects) {
                projectListModel.addElement(project.getId());
            }
        }
    }

    public void setMyProjectsList(ArrayList<Project> projects) {
        myProjectsListModel.clear();
        if (projects != null) {
            for (Project project : projects) {
                myProjectsListModel.addElement(project.getId());
            }
        }
    }

    private void addEmployee() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        Positions position = (Positions) positionComboBox.getSelectedItem();
        int contractDuration = Integer.parseInt(contractDurationField.getText());
        String entryDate = entryDateField.getText();
        int noticePeriod = Integer.parseInt(noticePeriodField.getText());
        Employee tempEmployee = new Employee(name, age, id, position, contractDuration, entryDate, noticePeriod);
        tempEmployee.calculateSalary();
        if (doubleSelectedProjectIds != null) {
            for (int projectId : doubleSelectedProjectIds) {
                Project project = employeeController.findProjectById(projectId);
                if (project != null)
                    tempEmployee.assignToProject(project);
            }
        }
        if (doubleSelectedMyProjectIds != null) {
            for (int projectIdToRemove : doubleSelectedMyProjectIds) {
                Project project = employeeController.findProjectById(projectIdToRemove);
                tempEmployee.removeFromProject(project);
            }
        }
        if(employee !=null)
            employeeController.updateEmployee(employee.getEmployeeId(), tempEmployee);
        else
            employeeController.addEmployee(tempEmployee);

        System.out.println(tempEmployee.toString());
    }


}