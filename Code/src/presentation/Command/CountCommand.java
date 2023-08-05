package presentation.Command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;

import domain.BillService;

public class CountCommand implements Command {
    private String selectedMonth;
    private String selectedYear;
    private boolean selectedInvoiceType;
    private BillService billService;
    private JLabel resultLabel;

    public CountCommand(boolean selectedInvoiceType, String selectedMonth, String selectedYear,
            BillService billService, JLabel resultLabel) {
        this.billService = billService;
        this.selectedInvoiceType = selectedInvoiceType;
        this.selectedMonth = selectedMonth;
        this.selectedYear = selectedYear;
        this.resultLabel = resultLabel;
    };

    @Override
    public void executed() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        try {
            Date startDate = new Date(dateFormat.parse(selectedMonth + "-" + selectedYear).getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate = new Date(calendar.getTimeInMillis());

            int count = billService.totalByTypeOfBill(selectedInvoiceType, startDate, endDate);

            resultLabel.setText("Tổng cộng: " + count);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
