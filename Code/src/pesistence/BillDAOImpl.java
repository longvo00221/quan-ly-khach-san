package pesistence;

import java.sql.Date;
import java.util.List;

import domain.model.Bill;
import presentation.HotelBillManageApp;

public class BillDAOImpl implements BillDAO {
    private BillGateWay billGateWay;

    public BillDAOImpl(BillGateWay billGateWay) {
        this.billGateWay = billGateWay;
    }

    @Override
    public void addBill(Bill bill) {
        this.billGateWay.addBill(bill);
    }

    @Override
    public void updateBill(Bill bill) {
        this.billGateWay.updateBill(bill);
    }

    @Override
    public void deleteBill(int billId, int phongId) {
        this.billGateWay.deleteBill(billId, phongId);

    }

    @Override
    public List<Bill> findBill(String name) {
        return this.billGateWay.findBill(name);

    }

    @Override
    public int totalByTypeOfBill(boolean loaiHoaDon, Date startDate, Date endDate) {
        return this.billGateWay.totalByTypeOfBill(loaiHoaDon, startDate, endDate);

    }

    @Override
    public List<Bill> averageMonthlyIncome(Date startDate, Date endDate) {
        return this.billGateWay.averageMonthlyIncome(startDate, endDate);

    }

    @Override
    public List<Bill> getAllBill() {
        return this.billGateWay.getAllBill();
    }

    @Override
    public void updateRoomStatus(int roomId, boolean status) {
        this.billGateWay.updateRoomStatus(roomId, status);
    }

    @Override
    public boolean getRoomStatus(int roomId) {
        return this.billGateWay.getRoomStatus(roomId);
    }

    @Override
    public boolean isBillExists(int billId) {
        return this.billGateWay.isBillExists(billId);
    }

    @Override
    public String normalizeString(String name) {
        return this.billGateWay.normalizeString(name);
    }

    @Override
    public void registerView(HotelBillManageApp view) {
        this.billGateWay.registerView(view);
    }

    // @Override
    // public void unregisterView(HotelBillManageApp view) {
    // this.billGateWay.unregisterView(view);

    // }

}
