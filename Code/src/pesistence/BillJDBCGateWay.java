package pesistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.Bill;
import domain.model.DayBill;
import domain.model.HourBill;

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
        String updateQuery = "UPDATE HoaDon SET SoPhong = ?, TenKhachHang = ?, NgayNhanPhong = ?, NgayTraPhong = ?, LoaiHoaDon = ?, Thang = ?, DonGia = ? WHERE HoaDonId = ?";
        if (!isBillExists(bill.getHoaDonId())) {
            System.out.println("Hóa đơn không tồn tại!");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, bill.getSoPhong());
            statement.setString(2, bill.getTenKhachHang());
            statement.setDate(3, bill.getNgayNhanPhong());
            statement.setDate(4, bill.getNgayTraPhong());
            statement.setBoolean(5, bill.getLoaiHoaDon());
            statement.setInt(6, bill.getThang());
            statement.setInt(7, bill.getDonGia());
            statement.setInt(8, bill.getHoaDonId());
            statement.executeUpdate();
            System.out.println("Cập nhật hóa đơn thành công!");
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @Override
    public void deleteBill(int billId) {
        String deleteQuery = "DELETE FROM HoaDon WHERE HoaDonId= ?";
        if (!isBillExists(billId)) {
            System.out.println("Hóa đơn không tồn tại!");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, billId);
            statement.executeUpdate();
            updateRoomStatus(billId, false);
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa ");
        }
    }

    @Override
    public Bill findBill(int billId) {
        String findQuery = "Select * from HoaDon WHERE HoaDonId= ?";
        try (PreparedStatement statement = connection.prepareStatement(findQuery)) {
            statement.setInt(1, billId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boolean loaiHoaDon = resultSet.getBoolean("LoaiHoaDon");
                    System.out.println("hi");
                    if (loaiHoaDon) {
                        DayBill dayBill = new DayBill();
                        dayBill.setNgayNhanPhong(resultSet.getDate("NgayNhanPhong"));
                        dayBill.setNgayTraPhong(resultSet.getDate("NgayTraPhong"));

                        dayBill.setSoNgay(dayBill.calculateDuration());
                        dayBill.setHoaDonId(billId);
                        dayBill.setSoPhong(resultSet.getInt("SoPhong"));
                        dayBill.setTenKhachHang(resultSet.getString("TenKhachHang"));
                        dayBill.setLoaiHoaDon(loaiHoaDon);
                        dayBill.setThang(resultSet.getInt("Thang"));
                        dayBill.setDonGia(resultSet.getInt("DonGia"));
                        dayBill.setPhongID(resultSet.getInt("PhongId"));
                        return dayBill;
                    } else {
                        HourBill hourBill = new HourBill();
                        hourBill.setNgayNhanPhong(resultSet.getDate("NgayNhanPhong"));
                        hourBill.setNgayTraPhong(resultSet.getDate("NgayTraPhong"));
                        hourBill.setSoGio(hourBill.calculateDuration());
                        hourBill.setHoaDonId(billId);
                        hourBill.setSoPhong(resultSet.getInt("SoPhong"));
                        hourBill.setTenKhachHang(resultSet.getString("TenKhachHang"));

                        hourBill.setLoaiHoaDon(loaiHoaDon);
                        hourBill.setThang(resultSet.getInt("Thang"));
                        hourBill.setDonGia(resultSet.getInt("DonGia"));
                        hourBill.setPhongID(resultSet.getInt("PhongId"));
                        return hourBill;
                    }

                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm");

        }
        return null;
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
                int hoaDonId = resultSet.getInt("HoaDonId");
                int soPhong = resultSet.getInt("SoPhong");
                String tenKhachHang = resultSet.getString("TenKhachHang");
                Date ngayNhanPhong = resultSet.getDate("NgayNhanPhong");
                Date ngayTraPhong = resultSet.getDate("NgayTraPhong");
                boolean loaiHoaDon = resultSet.getBoolean("LoaiHoaDon");
                int thang = resultSet.getInt("Thang");
                int donGia = resultSet.getInt("DonGia");
                int phongID = resultSet.getInt("PhongId");

                Bill bill;

                if (loaiHoaDon) {
                    // Nếu là DayBill thì lấy số ngày
                    bill = new DayBill();
                    ((DayBill) bill).setNgayNhanPhong(ngayNhanPhong);
                    ((DayBill) bill).setNgayTraPhong(ngayTraPhong);
                    int soNgay = ((DayBill) bill).calculateDuration();
                    ((DayBill) bill).setSoNgay(soNgay);

                } else {
                    // Nếu là HourBill thì lấy số giờ
                    bill = new HourBill();
                    ((HourBill) bill).setNgayNhanPhong(ngayNhanPhong);
                    ((HourBill) bill).setNgayTraPhong(ngayTraPhong);
                    int soGio = ((HourBill) bill).calculateDuration();
                    ((HourBill) bill).setSoGio(soGio);
                }

                bill.setHoaDonId(hoaDonId);
                bill.setSoPhong(soPhong);
                bill.setTenKhachHang(tenKhachHang);
                bill.setLoaiHoaDon(loaiHoaDon);
                bill.setThang(thang);
                bill.setDonGia(donGia);
                bill.setPhongID(phongID);

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

    @Override
    public boolean isBillExists(int billId) {
        String selectQuery = "SELECT COUNT(*) AS count FROM HoaDon WHERE HoaDonId = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, billId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi kiểm tra tồn tại hóa đơn!");
        }
        return false;
    }

}
