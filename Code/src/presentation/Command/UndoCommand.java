package presentation.Command;

import javax.swing.JOptionPane;

import domain.BillCaretaker;
import domain.BillMemento;
import domain.BillService;
import domain.model.Bill;

public class UndoCommand implements Command {
    private Boolean trangThai;
    private BillCaretaker billCaretaker;
    private BillService billService;
    private Bill bill;
    private int phongId;

    public UndoCommand() {

    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void setPhongId(int phongId) {
        this.phongId = phongId;
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

        BillMemento billMemento = billCaretaker.undo();
        bill = billMemento.getSaveBill();

        if (trangThai == true) {

            EditCommand editCommand = new EditCommand();
            editCommand.setCurentBill(bill, billService, phongId);
            trangThai = null;
        } else {
            AddCommand addCommand = new AddCommand();
            addCommand.addBillPrevious(bill, billService);
            trangThai = null;
        }

    }

}
