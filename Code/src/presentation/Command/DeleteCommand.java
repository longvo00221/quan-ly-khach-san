package presentation.Command;

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

        billService.deleteBill(hoaDonId, phongId);
    }
}
