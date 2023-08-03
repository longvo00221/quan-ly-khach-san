package presentation.Command;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.BillService;
import domain.model.Bill;

public class FindCommand implements Command {
    private String searchName;
    private BillService billService;
    private DefaultTableModel tableModel;

    public FindCommand(String searchName, BillService billService, DefaultTableModel tableModel) {
        this.searchName = searchName;
        this.billService = billService;
        this.tableModel = tableModel;
    }

    @Override
    public void executed() {
        List<Bill> billList = new ArrayList<>();

        billList = billService.findBill(searchName);

        tableModel.setRowCount(0);

        for (Bill bill : billList) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String donGia = decimalFormat.format(bill.getDonGia());
            Object[] rowData = {
                    bill.getHoaDonId(),
                    bill.getSoPhong(),
                    bill.getLoaiPhong(),
                    bill.getTenKhachHang(),
                    bill.getSoDienThoai(),
                    bill.getSoPhong(),
                    bill.getThang(),
                    bill.getNgayNhanPhong(),
                    bill.getNgayTraPhong(),
                    bill.getLoaiHoaDon(),
                    donGia,
                    bill.unitCost(),

            };
            tableModel.addRow(rowData);
        }
    }
}
