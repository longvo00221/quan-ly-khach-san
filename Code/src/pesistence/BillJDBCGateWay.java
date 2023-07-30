package pesistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import domain.model.Bill;

public class BillJDBCGateWay implements BillGateWay {
    private Connection connection;

    public BillJDBCGateWay() {
        String dbUrl = "jdbc:mysql://localhost:3306/db_qlks";
        String username = "root";
        String password = "1234";

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database!");
        }
    }

    @Override
    public void addBill(Bill bill) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBill'");
    }

    @Override
    public void updateBill(Bill bill) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBill'");
    }

    @Override
    public void deleteBill(int billId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBill'");
    }

    @Override
    public Bill findBill(int billId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBill'");
    }

    @Override
    public void totalByTypeOfBill(boolean typeBill) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'totalByTypeOfBill'");
    }

    @Override
    public void averageMonthlyIncome(int month) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'averageMonthlyIncome'");
    }

    @Override
    public List<Bill> getAllBill() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBill'");
    }

}
