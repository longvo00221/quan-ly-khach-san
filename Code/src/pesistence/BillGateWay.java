package pesistence;

import java.util.List;

import domain.model.Bill;

public interface BillGateWay {
    void addBill(Bill bill);

    void updateBill(Bill bill);

    void deleteBill(int billId);

    Bill findBill(int billId);

    void totalByTypeOfBill(boolean typeBill);

    void averageMonthlyIncome(int month);

    boolean isBillExists(int billId);

    List<Bill> getAllBill();

    void updateRoomStatus(int roomId, boolean status);

    boolean getRoomStatus(int roomId);

}
