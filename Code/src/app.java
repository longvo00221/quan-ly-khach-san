
import javax.swing.SwingUtilities;

import domain.BillService;
import domain.BillServiceImpl;
import pesistence.BillJDBCGateWay;
import presentation.HotelBillManageApp;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HotelBillManageApp view = new HotelBillManageApp();
                BillService billService = new BillServiceImpl();
                billService.registerView(view);
                view.setVisible(true);

            }
        });

    }
}
