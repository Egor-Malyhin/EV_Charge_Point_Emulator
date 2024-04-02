package org.mycorp.models.station_messages;

public abstract class StationMessage {
    private final StationMessageDescription description;
    private final StationMessageTypes type;

    public StationMessage(StationMessageTypes type, StationMessageDescription description) {
        this.type = type;
        this.description = description;
    }

    public StationMessageDescription getDescription() {
        return description;
    }

    public StationMessageTypes getType() {
        return type;
    }
}
