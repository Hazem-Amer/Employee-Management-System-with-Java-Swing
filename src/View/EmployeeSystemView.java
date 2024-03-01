package View;

import Controller.EmployeeController;
import Model.Employee;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeSystemView extends JFrame {
    private JTextArea employeeInfoTextArea;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addEmployeeButton;
    private JButton addProjectButton;
    private JButton editProjectsButton;
    private JPanel mainPanel;

    private EmployeeController controller;
    private Employee employee;
    private JButton refreshButton;
    private boolean isEmployeeUpdate;

    public EmployeeSystemView() {
        controller = new EmployeeController();
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));

        initComponents();
        addListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        employeeInfoTextArea = new JTextArea();
        employeeInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(employeeInfoTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        addEmployeeButton = new JButton("Add Employee");
        addProjectButton = new JButton("Add/Remove Project");
        refreshButton = new JButton("Refresh");


        controlPanel.add(new JLabel("Search by ID:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(addEmployeeButton);
        controlPanel.add(addProjectButton);
        controlPanel.add(refreshButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFrame();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployeeById();
            }
        });

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmployeeUpdate)
                    openUpdateEmployeeDialog();
                else
                    openAddEmployeeDialog();
            }
        });
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddProjectDialog();
            }
        });
        searchFieldClearListener();

    }

    public void searchFieldClearListener() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkText();
            }

            private void checkText() {
                if (isEmployeeUpdate) {
                    if (searchField.getText().isEmpty()) {
                        System.out.println("search field is cleared.");
                        employeeInfoTextArea.setText("");
                        isEmployeeUpdate = false;
                        addEmployeeButton.setText("Add Employee");
                        employee = null;
                    }
                }
            }
        });

    }

    private void refreshFrame() {
        dispose();
        new EmployeeSystemView();
    }

    private void openAddProjectDialog() {
        AddProjectDialog addProjectDialog = new AddProjectDialog(this, employee);
    }

    private void openAddEmployeeDialog() {
        AddEmployeeDialog addEmployeeDialog = new AddEmployeeDialog(this, null);
    }

    private void displayEmployeeInfo(Employee employee) {
        if (employee != null) {
            StringBuilder info = new StringBuilder();
            info.append("Employee found:\n");
            info.append("Name: ").append(employee.getName()).append("\n");
            info.append("Age: ").append(employee.getAge()).append("\n");
            info.append("Salary ").append(employee.getSalary()).append("\n");
            info.append("Position: ").append(employee.getPosition()).append("\n");
            info.append("entryDate: ").append(employee.getEntryDate()).append("\n");
            info.append("Contract date: ").append(String.valueOf(employee.getContractDuration())).append("\n");
            info.append("Notice period: ").append(String.valueOf(employee.getNoticePeriod())).append("\n");
            info.append("Projects").append(employee.getProjects().toString().toString()).append("\n");

            employeeInfoTextArea.setText(info.toString());
            addEmployeeButton.setText("Update");
            isEmployeeUpdate = true;

        } else {
            employeeInfoTextArea.setText("Employee not found.");
        }
    }

    private void openUpdateEmployeeDialog() {
        AddEmployeeDialog addEmployeeDialog = new AddEmployeeDialog(new JFrame(), employee);
    }

    private void searchEmployeeById() {
        int id = Integer.parseInt(searchField.getText());
        employee = controller.findEmployeeById(id);
        displayEmployeeInfo(employee);
    }


}