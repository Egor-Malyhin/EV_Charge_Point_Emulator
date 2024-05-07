package org.mycorp.models;

//Actions through which the station's state can be changed.
public enum StationStateAction {
    START_CHARGING,
    PREPARE_CHARGING,
    FINISH_CHARGING,
    GET_AVAILABLE,
    GET_UNAVAILABLE
}
