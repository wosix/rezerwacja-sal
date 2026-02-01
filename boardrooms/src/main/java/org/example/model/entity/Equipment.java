package org.example.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Setter
@Getter
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

    public boolean hasWhiteboard() {
        return whiteboard;
    }

    public boolean hasTvScreen() {
        return tvScreen;
    }

    public boolean hasAirConditioning() {
        return airConditioning;
    }

    public boolean hasSoundSystem() {
        return soundSystem;
    }

    public boolean hasVideoConferenceSystem() {
        return videoConferenceSystem;
    }

}
