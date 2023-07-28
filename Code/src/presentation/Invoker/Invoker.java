package presentation.Invoker;
import java.util.Queue;

import presentation.Command.Command;

import java.util.LinkedList;

public class Invoker {
    private Command command;
    private Queue<Command> commandQueue;

    public Invoker() {
        commandQueue = new LinkedList<>();
    }
    public void addToQueue(Command command){
        commandQueue.add(command);
    }
    public void executeCommands() {
        while(!commandQueue.isEmpty()){
            Command executedCommand = commandQueue.poll();
            executedCommand.executed();
        }
    }
}
