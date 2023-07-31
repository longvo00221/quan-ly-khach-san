package pesistence;

import java.util.List;

import domain.model.Bill;

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
    public void deleteBill(int billId) {
        this.billGateWay.deleteBill(billId);

    }

    @Override
    public Bill findBill(int billId) {
        return this.billGateWay.findBill(billId);

    }

    @Override
    public void totalByTypeOfBill(boolean typeBill) {
        this.billGateWay.totalByTypeOfBill(typeBill);

    }

    @Override
    public void averageMonthlyIncome(int month) {
        this.billGateWay.averageMonthlyIncome(month);

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

}
