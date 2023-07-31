package pesistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        boolean roomStatus = getRoomStatus(bill.getPhongID());

        if (roomStatus) {
            System.out.println("Phòng đã có hóa đơn!");
            return;
        } else {
            String insertQuery = "INSERT INTO HoaDon (SoPhong, TenKhachHang, NgayNhanPhong, NgayTraPhong, LoaiHoaDon, Thang, DonGia, PhongId)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, bill.getSoPhong());
                statement.setString(2, bill.getTenKhachHang());
                statement.setDate(3, bill.getNgayNhanPhong());
                statement.setDate(4, bill.getNgayTraPhong());
                statement.setBoolean(5, bill.getLoaiHoaDon());
                statement.setInt(6, bill.getThang());
                statement.setInt(7, bill.getDonGia());
                statement.setInt(8, bill.getPhongID());

                statement.executeUpdate();
                System.out.println("Thêm bill thành công!");

                updateRoomStatus(bill.getPhongID(), true);

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Thêm bill thất bại!");
            }
        }

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
        List<Bill> allBills = new ArrayList<>();
        String selectQuery = "SELECT * FROM HoaDon";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setSoPhong(resultSet.getInt("SoPhong"));
                bill.setTenKhachHang(resultSet.getString("TenKhachHang"));
                bill.setNgayNhanPhong(resultSet.getDate("NgayNhanPhong"));
                bill.setNgayTraPhong(resultSet.getDate("NgayTraPhong"));
                bill.setLoaiHoaDon(resultSet.getBoolean("LoaiHoaDon"));
                bill.setThang(resultSet.getInt("Thang"));
                bill.setDonGia(resultSet.getInt("DonGia"));
                bill.setPhongID(resultSet.getInt("PhongId"));

                allBills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get all bills!");
        }

        return allBills;
    }

    @Override
    public void updateRoomStatus(int roomId, boolean status) {
        String updateQuery = "UPDATE Phong SET TrangThai = ? WHERE PhongId = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setBoolean(1, status);
            statement.setInt(2, roomId);
            statement.executeUpdate();
            System.out.println("update room thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update phòng thất bại!");
        }
    }

    @Override
    public boolean getRoomStatus(int roomId) {
        String selectQuery = "SELECT TrangThai FROM Phong WHERE PhongId = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, roomId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("TrangThai");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select không thành công!");
        }
        return false;
    }

}
