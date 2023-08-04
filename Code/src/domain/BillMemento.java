package domain;

import domain.model.Bill;

public class BillMemento {
    private Bill saveBill;

    public BillMemento(Bill saveBill) {
        this.saveBill = saveBill;
    }

    public Bill getSaveBill() {
        return saveBill;
    }

}
