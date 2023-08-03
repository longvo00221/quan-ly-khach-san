package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domain.BillService;
import domain.BillServiceImpl;
import domain.model.Bill;
import presentation.Command.AddCommand;
import presentation.Command.AverageCommand;
import presentation.Command.CountCommand;
import presentation.Command.DeleteCommand;
import presentation.Command.EditCommand;
import presentation.Command.FindCommand;
import presentation.Command.SelectCommand;
import presentation.Invoker.Invoker;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import java.awt.event.*;

public class HotelBillManageApp extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton clearButton;
    private JTextField sophongTextField;
    private JTextField tenkhachhangTextField;
    private JTextField thoigiannhanphongTextField;
    private JTextField thoigiantraphongTextField;
    private JTextField loaihoadonTextField;
    private JTextField dongiaTextField;
    private JTextField sodienthoaiTextField;
    private JComboBox loaihoadonComboBox;

    private BillService billService;

    private Invoker invoker;
    private int hoaDonId;
    private int phongId;

    public HotelBillManageApp() {

        billService = new BillServiceImpl();
        invoker = new Invoker();
        billService.registerView(this);
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
        tableModel.addColumn("Thời gian nhận phòng ");
        tableModel.addColumn("Thời gian trả phòng");
        tableModel.addColumn("Loại hóa đơn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Thành tiền");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        hoaDonId = (int) table.getValueAt(selectedRow, 0);
                        phongId = (int) table.getValueAt(selectedRow, 5);
                    }
                    SelectCommand selectCommand = new SelectCommand(table, sophongTextField, sodienthoaiTextField,
                            tenkhachhangTextField, thoigiannhanphongTextField, thoigiantraphongTextField,
                            dongiaTextField, loaihoadonComboBox);
                    invoker.addToQueue(selectCommand);
                    invoker.executeCommands();

                }
            }
        });

        // Create the first menu - Main
        JMenu mainMenu = new JMenu("Main");
        JMenuItem countMenuItem = new JMenuItem("Count");
        JMenuItem averageMenuItem = new JMenuItem("Average");
        JMenuItem billListItem = new JMenuItem("BillList");
        menuBar.add(mainMenu);
        mainMenu.add(billListItem);
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
        dongiaTextField = new JTextField();
        sodienthoaiTextField = new JTextField();
        String[] invoiceTypes = { "Giờ", "Ngày" };
        loaihoadonComboBox = new JComboBox<>(invoiceTypes);

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");
        clearButton = new JButton("Clear");

        inputPanel.add(new JLabel("Tên khách hàng:"));
        inputPanel.add(tenkhachhangTextField);
        inputPanel.add(new JLabel("Số phòng:"));
        inputPanel.add(sophongTextField);
        inputPanel.add(new JLabel("Số điên thoại:"));
        inputPanel.add(sodienthoaiTextField);
        inputPanel.add(new JLabel("Loại hóa đơn:"));
        inputPanel.add(loaihoadonComboBox);

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
        buttonsPanel.add(clearButton);
        JPanel mainInputPanel = new JPanel(new BorderLayout());
        mainInputPanel.add(inputPanel, BorderLayout.CENTER);
        mainInputPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainInputPanel, BorderLayout.SOUTH);

        billListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableData();
            }
        });

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

                AddCommand addCommand = new AddCommand(sophongTextField, tenkhachhangTextField,
                        thoigiannhanphongTextField, thoigiantraphongTextField, loaihoadonComboBox, dongiaTextField,
                        sodienthoaiTextField, billService);
                invoker.addToQueue(addCommand);
                invoker.executeCommands();
                if (addCommand.isValidPhoneNumber()) {
                    clearFields();
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditCommand editCommand = new EditCommand(hoaDonId, phongId, sophongTextField, tenkhachhangTextField,
                        thoigiannhanphongTextField, thoigiantraphongTextField, loaihoadonComboBox,
                        dongiaTextField, sodienthoaiTextField, billService);
                invoker.addToQueue(editCommand);
                invoker.executeCommands();

                if (editCommand.isValidPhoneNumber()) {
                    clearFields();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCommand deleteCommand = new DeleteCommand(hoaDonId, phongId, billService);
                invoker.addToQueue(deleteCommand);
                invoker.executeCommands();
                clearFields();
            }
        });
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFindDialog();
            }
        });

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }

        });

        update();

    }

    private void showFindDialog() {
        // Tạo dialog
        JDialog findDialog = new JDialog(this, "Tìm kiếm hóa đơn");
        findDialog.setSize(300, 70);
        findDialog.setLayout(new GridLayout(1, 2));

        // Tạo components cho dialog
        JLabel nameLabel = new JLabel("Tên khách hàng:");
        JTextField searchTextField = new JTextField();

        findDialog.add(nameLabel, BorderLayout.SOUTH);
        findDialog.add(searchTextField, BorderLayout.SOUTH);

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchTextField.getText();
                FindCommand findCommand = new FindCommand(searchName, billService, tableModel);
                invoker.addToQueue(findCommand);
                invoker.executeCommands();
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
        String[] invoiceTypes = { "Theo giờ", "Theo ngày" };
        String[] months = { "1", "2", "3", "4", "5", "6", "7",
                "8",
                "9", "10", "11", "12" };
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

                int selectedIndex = invoiceTypeComboBox.getSelectedIndex();
                boolean selectedInvoiceType = selectedIndex == 1;

                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String selectedYear = (String) yearComboBox.getSelectedItem();

                CountCommand countCommand = new CountCommand(selectedInvoiceType, selectedMonth, selectedYear,
                        billService, resultLabel);
                invoker.addToQueue(countCommand);
                invoker.executeCommands();

            }
        });

        countDialog.setVisible(true);
    }

    private void showCountDialogAverage() {
        // Create the count dialog
        JDialog countDialog = new JDialog(this, "Tính thành tiền trung bình");
        countDialog.setSize(300, 150);
        countDialog.setLayout(new GridLayout(4, 2));
        String[] months = { "1", "2", "3", "4", "5", "6", "7",
                "8",
                "9", "10", "11", "12" };
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
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String selectedYear = (String) yearComboBox.getSelectedItem();
                AverageCommand averageCommand = new AverageCommand(selectedMonth, selectedYear,
                        billService, resultLabel);
                invoker.addToQueue(averageCommand);
                invoker.executeCommands();
            }
        });

        countDialog.setVisible(true);
    }

    private void updateTableData() {

        List<Bill> billList = billService.getAllBill();

        // Xóa toàn bộ dữ liệu cũ trên bảng
        tableModel.setRowCount(0);

        // Đổ dữ liệu mới vào bảng
        for (Bill bill : billList) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String donGia = decimalFormat.format(bill.getDonGia());
            String loaiHoaDon = "";
            if (bill.getLoaiHoaDon()) {
                loaiHoaDon = "Ngày";
            } else {
                loaiHoaDon = "Giờ";
            }
            Object[] rowData = {
                    bill.getHoaDonId(),
                    bill.getSoPhong(),
                    bill.getLoaiPhong(),
                    bill.getTenKhachHang(),
                    bill.getSoDienThoai(),
                    bill.getSoPhong(),
                    bill.getNgayNhanPhong(),
                    bill.getNgayTraPhong(),
                    loaiHoaDon,
                    donGia,
                    bill.unitCost(),

            };
            tableModel.addRow(rowData);
        }
    }

    private void clearFields() {

        sophongTextField.setText("");
        tenkhachhangTextField.setText("");
        thoigiannhanphongTextField.setText("");
        thoigiantraphongTextField.setText("");
        loaihoadonTextField.setText("");
        dongiaTextField.setText("");
        sodienthoaiTextField.setText("");
    }

    public void update() {
        updateTableData();
    }

}
