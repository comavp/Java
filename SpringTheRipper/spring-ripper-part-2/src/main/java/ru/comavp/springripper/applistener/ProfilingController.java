package ru.comavp.springripper.applistener;

public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
