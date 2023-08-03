package presentation.Command;

import domain.BillService;

public class DeleteCommand implements Command {
    private int hoaDonId;
    private BillService billService;

    public DeleteCommand(int hoaDonId, BillService billService) {
        this.hoaDonId = hoaDonId;
        this.billService = billService;
    };

    @Override
    public void executed() {
        if (hoaDonId < 0) {
            return;
        }

        billService.deleteBill(hoaDonId);
    }
}
