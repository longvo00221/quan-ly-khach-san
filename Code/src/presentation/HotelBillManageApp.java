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
    private JTextField sophongTextField;
    private JTextField loaiphongTextField;
    private JTextField tenkhachhangTextField;
    private JTextField thoigiannhanphongTextField;
    private JTextField thoigiantraphongTextField;
    private JTextField loaihoadonTextField;
    private JTextField thangTextField;
    private JTextField dongiaTextField;
    private JTextField phongTextField;
    private JTextField sodienthoaiTextField;

    public HotelBillManageApp() {
        setTitle("Hotel Bill Management");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JTable to display student list
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Phòng");
        tableModel.addColumn("Loại Phòng");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Số phòng");
        tableModel.addColumn("Tháng");
        tableModel.addColumn("Thời gian nhận phòng ");
        tableModel.addColumn("Thời gian trả phòng");
        tableModel.addColumn("Loại hóa đơn");
        tableModel.addColumn("Đơn giá");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create JPanel for student details input and buttons
        JPanel inputPanel = new JPanel(new GridLayout(9, 3));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        sophongTextField = new JTextField();
        tenkhachhangTextField = new JTextField();
        thoigiannhanphongTextField = new JTextField();
        thoigiantraphongTextField = new JTextField();
        loaihoadonTextField = new JTextField();
        thangTextField = new JTextField();
        dongiaTextField = new JTextField();
        phongTextField = new JTextField();
        loaiphongTextField = new JTextField();
        sodienthoaiTextField = new JTextField();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");
        saveButton = new JButton("Save");

        //new GridLayout(7, 2)

        

        inputPanel.add(new JLabel("Tên khách hàng:"));
        inputPanel.add(tenkhachhangTextField);
        inputPanel.add(new JLabel("Số phòng:"));
        inputPanel.add(sophongTextField);
        inputPanel.add(new JLabel("Loại Phòng:"));
        inputPanel.add(loaiphongTextField);
        inputPanel.add(new JLabel("Số điên thoại:"));
        inputPanel.add(sodienthoaiTextField);
        inputPanel.add(new JLabel("Loại hóa đơn:"));
        inputPanel.add(loaihoadonTextField);
        inputPanel.add(new JLabel("Tháng:"));
        inputPanel.add(thangTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(dongiaTextField);
        inputPanel.add(new JLabel("Thời gian nhận phòng:"));
        inputPanel.add(thoigiannhanphongTextField);
        inputPanel.add(new JLabel("Thời gian trả phòng:"));
        inputPanel.add(thoigiantraphongTextField);
       
       
      
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(findButton);
        buttonsPanel.add(saveButton);
        JPanel mainInputPanel = new JPanel(new BorderLayout());
        mainInputPanel.add(inputPanel, BorderLayout.CENTER);
        mainInputPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainInputPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });

        // Load initial student list
         
    }

    // Method to add a student
 

    // Method to clear input fields
    private void clearFields() {

        sophongTextField.setText("");
        loaiphongTextField.setText("");
        tenkhachhangTextField.setText("");
        thoigiannhanphongTextField.setText("");
        thoigiantraphongTextField.setText("");
        loaihoadonTextField.setText("");
        thangTextField.setText("");
        dongiaTextField.setText("");
        phongTextField.setText("");
        sodienthoaiTextField.setText("");
    }

}
