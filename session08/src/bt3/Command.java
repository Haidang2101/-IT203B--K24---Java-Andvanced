package bt3;

public interface Command {
    void execute();
    void undo();
}