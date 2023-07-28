package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;

public class HotelBillManageApp extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton saveButton;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField majorTextField;
    private JTextField javaTextField;
    private JTextField htmlTextField;
    private JTextField cssTextField;

    public HotelBillManageApp() {
        setTitle("Student Management");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JTable to display student list
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Major");
        tableModel.addColumn("Java Mark");
        tableModel.addColumn("HTML Mark");
        tableModel.addColumn("CSS Mark");
        tableModel.addColumn("Average");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create JPanel for student details input and buttons
        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idTextField = new JTextField();
        nameTextField = new JTextField();
        majorTextField = new JTextField();
        javaTextField = new JTextField();
        htmlTextField = new JTextField();
        cssTextField = new JTextField();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");
        saveButton = new JButton("Save");

        //new GridLayout(7, 2)

        

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Major:"));
        inputPanel.add(majorTextField);
        inputPanel.add(new JLabel("Java Mark:"));
        inputPanel.add(javaTextField);
        inputPanel.add(new JLabel("HTML Mark:"));
        inputPanel.add(htmlTextField);
        inputPanel.add(new JLabel("CSS Mark:"));
        inputPanel.add(cssTextField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);
        inputPanel.add(saveButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findStudent();
            }
        });

        // Load initial student list
         loadStudentList();
    }

    // Method to add a student
    private void addStudent() {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String major = majorTextField.getText();
        int javaMark = Integer.parseInt(javaTextField.getText());
        int htmlMark = Integer.parseInt(htmlTextField.getText());
        int cssMark = Integer.parseInt(cssTextField.getText());

        // Calculate the average mark using the formula provided
        double averageMark = (javaMark * 2.0 + htmlMark + cssMark) / 4.0;

        clearFields();
        loadStudentList();
    }

    // Method to edit a student
    private void editStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
            return;
        }

        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String major = majorTextField.getText();
        int javaMark = Integer.parseInt(javaTextField.getText());
        int htmlMark = Integer.parseInt(htmlTextField.getText());
        int cssMark = Integer.parseInt(cssTextField.getText());

        // Calculate the average mark using the formula provided
        double averageMark = (javaMark * 2.0 + htmlMark + cssMark) / 4.0;

        clearFields();
        loadStudentList();
    }

    // Method to delete a student
    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }

       

        clearFields();
        loadStudentList();
    }

    // Method to find a student
    private void findStudent() {
        int studentId = Integer.parseInt(idTextField.getText());
        // Student student = studentService.getStudentById(studentId);

        // if (student != null) {
        //     nameTextField.setText(student.getName());
        //     majorTextField.setText(student.getMajor());
        //     javaTextField.setText(String.valueOf(student.getJavaMark()));
        //     htmlTextField.setText(String.valueOf(student.getHtmlMark()));
        //     cssTextField.setText(String.valueOf(student.getCssMark()));
        // } else {
        //     JOptionPane.showMessageDialog(this, "Student not found.");
        //     clearFields();
        // }
    }

    // Method to load the student list into the JTable
    private void loadStudentList() {
        // List<Student> students = studentService.getAllStudents();
        // List<Student> students = new ArrayList<>();
        // students.add(new Student(111, "Le Van Teo", "IT", 5, 5, 5, 5));
        // students.add(new Student(222, "Le Van Ty", "IT", 7,
        //         7, 5, 7));
        // students.add(new Student(333, "Le Van Tung", "IT", 5,
        //         5, 5, 5));
        // tableModel.setRowCount(0); // Clear previous data
        // for (Student student : students) {
        //     Object[] rowData = { student.getId(), student.getName(), student.getMajor(),
        //             student.getJavaMark(), student.getHtmlMark(), student.getCssMark(), student.calAverage() };
        //     tableModel.addRow(rowData);
        // }
    }

    // Method to clear input fields
    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        majorTextField.setText("");
        javaTextField.setText("");
        htmlTextField.setText("");
        cssTextField.setText("");
    }

}
