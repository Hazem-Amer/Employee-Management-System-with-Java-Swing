import Controller.DataManager;
import Model.Employee;
import Model.Positions;
import Model.Project;
import Model.ProjectStatus;
import View.EmployeeSystemView;

import javax.swing.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeSystemView();
            }
        });
        DataManager.getInstance().intializeMockData();
    }
}



