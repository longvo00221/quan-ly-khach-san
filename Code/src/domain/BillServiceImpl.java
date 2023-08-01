package domain;

import java.util.List;

import domain.model.Bill;
import pesistence.BillDAO;

public class BillServiceImpl implements BillService {
    private BillDAO billDAO;

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
    public void deleteBill(int billId) {
        billDAO.deleteBill(billId);
    }

    @Override
    public Bill findBill(int billId) {
        return billDAO.findBill(billId);
    }

    @Override
    public void totalByTypeOfBill(boolean typeBill) {
        billDAO.totalByTypeOfBill(typeBill);
    }

    @Override
    public void averageMonthlyIncome(int month) {
        billDAO.averageMonthlyIncome(month);
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

}
