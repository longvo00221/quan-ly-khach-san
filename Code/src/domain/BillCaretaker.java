package domain;

import java.util.Stack;

public class BillCaretaker {
    private Stack<BillMemento> undoStack;
    private Stack<BillMemento> redoStack;

    public BillCaretaker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();

    }

    public void pushMemento(BillMemento memento) {
        undoStack.push(memento);
    }

    public void pushRedoStack(BillMemento memento) {
        redoStack.push(memento);
    }

    public BillMemento undo() {
        if (!isUndoAvailable()) {
            return null;
        }

        BillMemento currentBill = undoStack.pop();
        return currentBill;

    }

    public BillMemento redo() {
        if (!isRedoAvailable()) {
            return null;
        }

        BillMemento currentBill = redoStack.pop();
        return currentBill;

    }

    public boolean isUndoAvailable() {
        return !undoStack.isEmpty();
    }

    public boolean isRedoAvailable() {
        return !redoStack.empty();
    }

}
