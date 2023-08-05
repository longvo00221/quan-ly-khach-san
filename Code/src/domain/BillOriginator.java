package domain;

import domain.model.Bill;

public class BillOriginator {
    private Bill bill;
    private int hoaDonId;
    private int phongId;

    public BillOriginator() {

    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BillMemento saveStateMemento() {
        BillMemento memento = new BillMemento(bill);
        return memento;
    }

}
