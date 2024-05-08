package org.mycorp;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ServerCoreProfile core = new ServerCoreProfile(new ServerCoreProfileEventHandler());
        JSONServer server = new JSONServer(core);
        ServerEventsListener serverEventsListener = new ServerEventsListener();
        server.open("localhost", 8887, serverEventsListener);
    }
}