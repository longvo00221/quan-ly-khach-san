
import javax.swing.SwingUtilities;

import presentation.HotelBillManageApp;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HotelBillManageApp view = new HotelBillManageApp();
                view.setVisible(true);

            }
        });

    }
}
