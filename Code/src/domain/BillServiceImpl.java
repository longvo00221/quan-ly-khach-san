package domain;

import java.sql.Date;
import java.util.List;

import domain.model.Bill;
import pesistence.BillDAO;
import pesistence.BillDAOImpl;
import pesistence.BillJDBCGateWay;
import presentation.HotelBillManageApp;

public class BillServiceImpl implements BillService {
    private BillDAO billDAO;

    public BillServiceImpl() {
        billDAO = new BillDAOImpl(new BillJDBCGateWay());
    }

    public BillServiceImpl(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public void addBill(Bill bill) {
        billDAO.addBill(bill);
    }

    @Override
    public void updateBill(Bill bill) {
        billDAO.updateBill(bill);
    }

    @Override
    public void deleteBill(int billId, int phongId) {
        billDAO.deleteBill(billId, phongId);
    }

    @Override
    public List<Bill> findBill(String name) {
        return billDAO.findBill(name);
    }

    @Override
    public int totalByTypeOfBill(boolean loaiHoaDon, Date startDate, Date endDate) {
        return billDAO.totalByTypeOfBill(loaiHoaDon, startDate, endDate);
    }

    @Override
    public List<Bill> averageMonthlyIncome(Date startDate, Date endDate) {
        return billDAO.averageMonthlyIncome(startDate, endDate);
    }

    @Override
    public boolean isBillExists(int billId) {
        return billDAO.isBillExists(billId);
    }

    @Override
    public List<Bill> getAllBill() {
        return billDAO.getAllBill();
    }

    @Override
    public void updateRoomStatus(int roomId, boolean status) {
        billDAO.updateRoomStatus(roomId, status);
    }

    @Override
    public boolean getRoomStatus(int roomId) {
        return billDAO.getRoomStatus(roomId);
    }

    @Override
    public String normalizeString(String name) {
        return billDAO.normalizeString(name);
    }

    @Override
    public void registerView(HotelBillManageApp view) {
        billDAO.registerView(view);
    }

    // @Override
    // public void unregisterView(HotelBillManageApp view) {
    // billDAO.unregisterView(view);
    // }

    @Override
    public void notifyViews() {
        billDAO.notifyViews();

    }

}
