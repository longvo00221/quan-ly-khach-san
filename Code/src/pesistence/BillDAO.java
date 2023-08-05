package pesistence;

import java.sql.Date;
import java.util.List;

import domain.model.Bill;
import presentation.HotelBillManageApp;

public interface BillDAO {

    void addBill(Bill bill);

    void updateBill(Bill bill);

    void deleteBill(int billId, int phongId);

    List<Bill> findBill(String name);

    int totalByTypeOfBill(boolean loaiHoaDon, Date startDate, Date endDate);

    List<Bill> averageMonthlyIncome(Date startDate, Date endDate);

    boolean isBillExists(int billId);

    String normalizeString(String name);

    List<Bill> getAllBill();

    void updateRoomStatus(int roomId, boolean status);

    boolean getRoomStatus(int roomId);

    // void registerView(HotelBillManageApp view);

    // void unregisterView(HotelBillManageApp view);

}
