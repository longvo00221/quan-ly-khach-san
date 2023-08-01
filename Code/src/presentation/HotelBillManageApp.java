package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import presentation.Command.AddCommand;
import presentation.Command.AverageCommand;
import presentation.Command.CountCommand;
import presentation.Command.DeleteCommand;
import presentation.Command.EditCommand;
import presentation.Command.FindCommand;
import presentation.Invoker.Invoker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

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

    private Invoker invoker;
   
    

    public HotelBillManageApp() {
        setTitle("Hotel Bill Management");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        

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
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create the first menu - Main
        JMenu mainMenu = new JMenu("Main");
        JMenuItem countMenuItem = new JMenuItem("Count");
        JMenuItem averageMenuItem = new JMenuItem("Average");
        menuBar.add(mainMenu);
        mainMenu.add(countMenuItem);
        mainMenu.add(averageMenuItem);
        add(scrollPane, BorderLayout.CENTER);

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
        JPanel mainInputPanel = new JPanel(new BorderLayout());
        mainInputPanel.add(inputPanel, BorderLayout.CENTER);
        mainInputPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainInputPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        countMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCountDialogCount();
            }
        });
        averageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCountDialogAverage();
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddCommand addCommand = new AddCommand();
                clearFields();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditCommand editCommand = new EditCommand();
                clearFields();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCommand deleteCommand = new DeleteCommand();
                clearFields();
            }
        });
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFindDialog();
            }
        });

    }
    private void showFindDialog() {
        // Tạo dialog
        JDialog findDialog = new JDialog(this, "Tìm kiếm khách hàng");
        findDialog.setSize(300, 70);
        findDialog.setLayout(new GridLayout(1, 2));
        
        // Tạo components cho dialog
        JLabel nameLabel = new JLabel("Tên khách hàng:");
        JTextField searchTextField = new JTextField();

    
        findDialog.add(nameLabel,BorderLayout.SOUTH);
        findDialog.add(searchTextField,BorderLayout.SOUTH);


        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchTextField.getText();
                FindCommand findCommand = new FindCommand();
            }
        });
    
        findDialog.setVisible(true);
    }
    
    private void showCountDialogCount() {
        // Create the count dialog
        JDialog countDialog = new JDialog(this, "Tính số lượng hóa đơn");
        countDialog.setSize(300, 150);
        countDialog.setLayout(new GridLayout(4, 2));
        // Create components for the dialog
        String[] invoiceTypes = { "Theo giờ", "Theo Ngày" };
        String[] months = { "tháng 1", "tháng 2", "tháng 3", "tháng 4", "tháng 5", "tháng 6", "tháng 7",
                "tháng 8",
                "tháng 9", "tháng 10", "tháng 11", "tháng 12" };
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String[] years = new String[currentYear - 1999];
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(2000 + i);
        }
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        JComboBox<String> invoiceTypeComboBox = new JComboBox<>(invoiceTypes);
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        JButton countButton = new JButton("Tính toán");
        JLabel resultLabel = new JLabel("Tổng cộng:");

        countDialog.add(new JLabel("Loại hóa đơn:"));
        countDialog.add(invoiceTypeComboBox);
        countDialog.add(new JLabel("Tháng :"));
        countDialog.add(monthComboBox);
        countDialog.add(new JLabel("Năm :"));
        countDialog.add(yearComboBox);
        countDialog.add(countButton, BorderLayout.SOUTH);
        countDialog.add(resultLabel, BorderLayout.SOUTH);

        // Add action listener for the count button
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountCommand countCommand = new CountCommand();
                String selectedInvoiceType = (String) invoiceTypeComboBox.getSelectedItem();
                String selectedMonth = (String) monthComboBox.getSelectedItem();
        
                resultLabel.setText("10");
            }
        });

        countDialog.setVisible(true);
    }
    private void showCountDialogAverage() {
        // Create the count dialog
        JDialog countDialog = new JDialog(this, "Tính thành tiền trung bình");
        countDialog.setSize(300, 150);
        countDialog.setLayout(new GridLayout(4, 2));
        String[] months = { "tháng 1", "tháng 2", "tháng 3", "tháng 4", "tháng 5", "tháng 6", "tháng 7",
                "tháng 8",
                "tháng 9", "tháng 10", "tháng 11", "tháng 12" };
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String[] years = new String[currentYear - 1999];
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(2000 + i);
        }
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        JButton averageButton = new JButton("Tính toán");
        JLabel resultLabel = new JLabel("Tổng cộng:");
        countDialog.add(new JLabel("Tháng :"));
        countDialog.add(monthComboBox);
        countDialog.add(new JLabel("Năm :"));
        countDialog.add(yearComboBox);
        countDialog.add(averageButton, BorderLayout.SOUTH);
        countDialog.add(resultLabel, BorderLayout.SOUTH);

        // Add action listener for the count button
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AverageCommand averageCommand = new AverageCommand();
                 resultLabel.setText("100.000d");
            }
        });

        countDialog.setVisible(true);
    }

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
