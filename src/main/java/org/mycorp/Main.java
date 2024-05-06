package org.mycorp;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Main {
    public static void main(String[] args) {
        // The core profile is mandatory
        ServerCoreProfile core = new ServerCoreProfile(new ServerCoreProfileEventHandler());
        JSONServer server = new JSONServer(core);
        server.open("localhost", 8887, new ServerEventsListener());
    }
}