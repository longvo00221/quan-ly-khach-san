package pesistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            String insertQuery = "INSERT INTO HoaDon (SoPhong, TenKhachHang, SoDienThoai, NgayNhanPhong, NgayTraPhong, LoaiHoaDon, Thang, DonGia, PhongId)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, bill.getSoPhong());
                statement.setString(2, bill.getTenKhachHang());
                statement.setString(3, bill.getSoDienThoai());
                statement.setTimestamp(4, bill.getNgayNhanPhong());
                statement.setTimestamp(5, bill.getNgayTraPhong());
                statement.setBoolean(6, bill.getLoaiHoaDon());
                statement.setInt(7, bill.getThang());
                statement.setDouble(8, bill.getDonGia());
                statement.setInt(9, bill.getPhongID());

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
        String updateQuery = "UPDATE HoaDon SET SoPhong = ?, TenKhachHang = ?, NgayNhanPhong = ?, NgayTraPhong = ?, LoaiHoaDon = ?, Thang = ?, DonGia = ?, SoDienThoai = ? WHERE HoaDonId = ?";
        if (!isBillExists(bill.getHoaDonId())) {
            System.out.println("Hóa đơn không tồn tại!");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, bill.getSoPhong());
            statement.setString(2, bill.getTenKhachHang());
            statement.setTimestamp(3, bill.getNgayNhanPhong());
            statement.setTimestamp(4, bill.getNgayTraPhong());
            statement.setBoolean(5, bill.getLoaiHoaDon());
            statement.setInt(6, bill.getThang());
            statement.setDouble(7, bill.getDonGia());
            statement.setString(8, bill.getSoDienThoai());
            statement.setInt(9, bill.getHoaDonId());

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
    public List<Bill> findBill(String name) {
        List<Bill> allBills = new ArrayList<>();
        String findQuery = "SELECT hd.*, p.LoaiPhong FROM HoaDon hd " +
                "JOIN Phong p ON hd.PhongId = p.PhongId " + "WHERE LOWER(TenKhachHang) LIKE ?";
        String normalizedSearchName = normalizeString(name);

        try (PreparedStatement statement = connection.prepareStatement(findQuery)) {

            statement.setString(1, "%" + normalizedSearchName + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    boolean loaiHoaDon = resultSet.getBoolean("LoaiHoaDon");

                    if (loaiHoaDon) {
                        DayBill dayBill = new DayBill();
                        dayBill.setNgayNhanPhong(resultSet.getTimestamp("NgayNhanPhong"));
                        dayBill.setNgayTraPhong(resultSet.getTimestamp("NgayTraPhong"));
                        dayBill.setLoaiPhong(resultSet.getString("LoaiPhong"));

                        dayBill.setSoNgay(dayBill.calculateDuration());
                        dayBill.setHoaDonId(resultSet.getInt("HoaDonId"));
                        dayBill.setSoPhong(resultSet.getInt("SoPhong"));
                        dayBill.setTenKhachHang(resultSet.getString("TenKhachHang"));
                        dayBill.setLoaiHoaDon(loaiHoaDon);
                        dayBill.setThang(resultSet.getInt("Thang"));
                        dayBill.setDonGia(resultSet.getInt("DonGia"));
                        dayBill.setPhongID(resultSet.getInt("PhongId"));
                        dayBill.setSoDienThoai(resultSet.getString("SoDienThoai"));
                        allBills.add(dayBill);
                    } else {
                        HourBill hourBill = new HourBill();
                        hourBill.setNgayNhanPhong(resultSet.getTimestamp("NgayNhanPhong"));
                        hourBill.setNgayTraPhong(resultSet.getTimestamp("NgayTraPhong"));
                        hourBill.setSoGio(hourBill.calculateDuration());
                        hourBill.setHoaDonId(resultSet.getInt("HoaDonId"));
                        hourBill.setSoPhong(resultSet.getInt("SoPhong"));
                        hourBill.setTenKhachHang(resultSet.getString("TenKhachHang"));
                        hourBill.setLoaiPhong(resultSet.getString("LoaiPhong"));
                        hourBill.setLoaiHoaDon(loaiHoaDon);
                        hourBill.setThang(resultSet.getInt("Thang"));
                        hourBill.setDonGia(resultSet.getInt("DonGia"));
                        hourBill.setPhongID(resultSet.getInt("PhongId"));
                        hourBill.setSoDienThoai(resultSet.getString("SoDienThoai"));

                        allBills.add(hourBill);
                    }

                }

            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm");

        }
        return allBills;
    }

    @Override
    public int totalByTypeOfBill(boolean loaiHoaDon, Date startDate, Date endDate) {
        String countQuery = "SELECT COUNT(*) FROM HoaDon WHERE LoaiHoaDon = ? AND NgayTraPhong BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(countQuery)) {
            statement.setBoolean(1, loaiHoaDon);
            statement.setTimestamp(2, new Timestamp(startDate.getTime()));
            statement.setTimestamp(3, new Timestamp(endDate.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    @Override
    public List<Bill> averageMonthlyIncome(Date startDate, Date endDate) {
        List<Bill> allBills = new ArrayList<>();
        String query = "SELECT * FROM HoaDon WHERE NgayTraPhong BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, new Timestamp(startDate.getTime()));
            statement.setTimestamp(2, new Timestamp(endDate.getTime()));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int hoaDonId = resultSet.getInt("HoaDonId");
                    int soPhong = resultSet.getInt("SoPhong");
                    String tenKhachHang = resultSet.getString("TenKhachHang");
                    Timestamp ngayNhanPhong = resultSet.getTimestamp("NgayNhanPhong");
                    Timestamp ngayTraPhong = resultSet.getTimestamp("NgayTraPhong");
                    boolean loaiHoaDon = resultSet.getBoolean("LoaiHoaDon");
                    int thang = resultSet.getInt("Thang");
                    int donGia = resultSet.getInt("DonGia");
                    int phongID = resultSet.getInt("PhongId");
                    String soDienThoai = resultSet.getString("SoDienThoai");

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
                    bill.setSoDienThoai(soDienThoai);

                    allBills.add(bill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBills;
    }

    @Override
    public List<Bill> getAllBill() {
        List<Bill> allBills = new ArrayList<>();
        String selectQuery = "SELECT hd.*, p.LoaiPhong FROM HoaDon hd " +
                "JOIN Phong p ON hd.PhongId = p.PhongId";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int hoaDonId = resultSet.getInt("HoaDonId");
                int soPhong = resultSet.getInt("SoPhong");
                String loaiPhong = resultSet.getString("LoaiPhong");
                String tenKhachHang = resultSet.getString("TenKhachHang");
                Timestamp ngayNhanPhong = resultSet.getTimestamp("NgayNhanPhong");
                Timestamp ngayTraPhong = resultSet.getTimestamp("NgayTraPhong");
                boolean loaiHoaDon = resultSet.getBoolean("LoaiHoaDon");
                int thang = resultSet.getInt("Thang");
                int donGia = resultSet.getInt("DonGia");
                int phongID = resultSet.getInt("PhongId");
                String soDienThoai = resultSet.getString("SoDienThoai");

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
                bill.setLoaiPhong(loaiPhong);
                bill.setSoDienThoai(soDienThoai);

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

    @Override
    public String normalizeString(String name) {
        String normalized = name.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
        return normalized;
    }

}
