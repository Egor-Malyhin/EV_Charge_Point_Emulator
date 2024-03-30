package org.mycorp.models.station_messages;

public abstract class StationMessage {
    private StationMessageClassification description;

    public StationMessage(StationMessageClassification description) {
        this.description = description;
    }

    public StationMessageClassification getDescription() {
        return description;
    }

    public void setDescription(StationMessageClassification description) {
        this.description = description;
    }
}
