package presentation.Command;

import javax.swing.JOptionPane;

import domain.BillService;

public class DeleteCommand implements Command {
    private int hoaDonId;
    private int phongId;

    private BillService billService;

    public DeleteCommand(int hoaDonId, int phongId, BillService billService) {
        this.hoaDonId = hoaDonId;
        this.billService = billService;
        this.phongId = phongId;
    };

    @Override
    public void executed() {
        if (hoaDonId < 0) {
            return;
        }
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Bạn chắc chắn muốn xóa?",
                "Xác nhận",
                JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.OK_OPTION) {
            billService.deleteBill(hoaDonId, phongId);
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            return;
        }

    }
}
