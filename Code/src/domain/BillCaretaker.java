package domain;

import java.util.Stack;

public class BillCaretaker {
    private Stack<BillMemento> undoStack;

    public BillCaretaker() {
        undoStack = new Stack<>();
    }

    public void pushMemento(BillMemento memento) {
        undoStack.push(memento);
    }

    public BillMemento undo() {
        if (!isUndoAvailable()) {
            return null;
        }

        BillMemento currentBill = undoStack.pop();
        return currentBill;

    }

    public boolean isUndoAvailable() {
        return !undoStack.isEmpty();
    }

}
