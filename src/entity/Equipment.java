package entity;

public class Equipment {

    private boolean projector;
    private boolean whiteboard;
    private boolean tvScreen;
    private boolean videoConferenceSystem;
    private boolean airConditioning;
    private boolean soundSystem;

    public Equipment() {
        this.projector = true;
        this.whiteboard = true;
        this.tvScreen = true;
        this.videoConferenceSystem = true;
        this.airConditioning = true;
        this.soundSystem = true;
    }

    public boolean hasProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean hasWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }

    public boolean hasTvScreen() {
        return tvScreen;
    }

    public void setTvScreen(boolean tvScreen) {
        this.tvScreen = tvScreen;
    }

    public boolean hasairConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean hasSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(boolean soundSystem) {
        this.soundSystem = soundSystem;
    }

    public boolean hasVideoConferenceSystem() {
        return videoConferenceSystem;
    }

    public void setVideoConferenceSystem(boolean videoConferenceSystem) {
        this.videoConferenceSystem = videoConferenceSystem;
    }
}
