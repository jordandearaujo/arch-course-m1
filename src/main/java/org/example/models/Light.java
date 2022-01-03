package org.example.models;

public class Light extends Thing {

    private boolean isLightOn = false;
    private OnLightChangeListener lightChangeListener;

    @Override
    public String getTypeName() {
        return "Light";
    }

    @Override
    public String getDescription() {
        return "Light is on =" + isLightOn;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setLightOn(boolean lightOn) {
        isLightOn = lightOn;

        if (lightChangeListener != null) {
            lightChangeListener.onLightChange(this);
        }

    }

    public interface OnLightChangeListener {
        void onLightChange(Light light);
    }

    public void setLightChangeListener(OnLightChangeListener lightChangeListener) {
        this.lightChangeListener = lightChangeListener;
    }
}
