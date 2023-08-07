package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import domain.model.Bill;
import persistence.BillDAO;
import persistence.BillDAOImpl;
import persistence.BillJDBCGateWay;
import presentation.HotelBillManageApp;

public class BillServiceImpl implements BillService {
    private BillDAO billDAO;
    List<HotelBillManageApp> views = new ArrayList<>();
    private boolean hasPerformedAction = false;

    public BillServiceImpl() {
        billDAO = new BillDAOImpl(new BillJDBCGateWay());
        views = new ArrayList<>();
    }

    public BillServiceImpl(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public void addBill(Bill bill) {
        billDAO.addBill(bill);
        hasPerformedAction = true;
        notifyViews();
    }

    @Override
    public void updateBill(Bill bill) {
        billDAO.updateBill(bill);
        hasPerformedAction = true;

        notifyViews();

    }

    @Override
    public void deleteBill(int billId, int phongId) {
        billDAO.deleteBill(billId, phongId);
        hasPerformedAction = true;

        notifyViews();

    }

    @Override
    public List<Bill> findBill(String name) {
        List<Bill> billList = billDAO.findBill(name);
        return billList;

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
        List<Bill> billList = billDAO.getAllBill();
        return billList;

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
        views.add(view);
    }

    public void notifyViews() {
        if (hasPerformedAction) {
            for (HotelBillManageApp view : views) {
                view.update();
            }
            hasPerformedAction = false;
        }

    }

}
