package presentation.Command;

import javax.swing.JOptionPane;

import domain.BillCaretaker;
import domain.BillMemento;
import domain.BillService;
import domain.model.Bill;

public class RedoCommand implements Command {
    private Boolean trangThai;
    private BillCaretaker billCaretaker;
    private BillService billService;
    private Bill bill;

    public RedoCommand() {
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void setBillCaretaker(BillCaretaker billCaretaker) {
        this.billCaretaker = billCaretaker;
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void executed() {
        System.out.println(trangThai);
        if (trangThai == null) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        BillMemento billMemento = billCaretaker.redo();
        bill = billMemento.getSaveBill();

        if (trangThai == true) {

            EditCommand editCommand = new EditCommand();
            editCommand.setCurentBill(bill, billService);
            trangThai = null;
        }

    }

}
