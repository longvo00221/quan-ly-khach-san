package presentation.Command;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import domain.BillService;
import domain.model.Bill;

public class AverageCommand implements Command {
    private String selectedMonth;
    private String selectedYear;
    private BillService billService;
    private JLabel resultLabel;

    public AverageCommand(String selectedMonth, String selectedYear,
            BillService billService, JLabel resultLabel) {
        this.billService = billService;
        this.selectedMonth = selectedMonth;
        this.selectedYear = selectedYear;
        this.resultLabel = resultLabel;
    }

    @Override
    public void executed() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        try {
            Date startDate = new Date(dateFormat.parse(selectedMonth + "-" + selectedYear).getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate = new Date(calendar.getTimeInMillis());

            // Gọi hàm đếm từ cơ sở dữ liệu
            List<Bill> bills = billService.averageMonthlyIncome(startDate, endDate);
            double total = 0.0;
            for (Bill bill : bills) {
                double unitCost = Double.parseDouble(bill.unitCost().replace(",", ""));
                total += unitCost;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTotal = decimalFormat.format(total);
            resultLabel.setText("Tổng cộng: " + formattedTotal);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
