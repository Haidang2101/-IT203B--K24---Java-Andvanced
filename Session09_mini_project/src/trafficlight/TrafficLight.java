package trafficlight;

import trafficlight.state.LightState;
import trafficlight.state.RedState;

public class TrafficLight implements Runnable {

    private volatile LightState state = new RedState();

    public boolean isRed() {
        return state instanceof RedState;
    }

    public void setState(LightState state) {
        this.state = state;
    }

    public String getStateName() {
        return state.getName();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                state.next(this);
                util.Logger.log("🚦 Đèn: " + state.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}