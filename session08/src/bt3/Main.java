package bt3;

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command on = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(on);

        remote.press();
        remote.undo();
    }
}