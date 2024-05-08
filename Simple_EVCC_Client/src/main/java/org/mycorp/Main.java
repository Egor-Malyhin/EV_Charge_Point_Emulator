package org.mycorp;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.mycorp.protocolfilter.V2GDecoder;
import org.mycorp.protocolfilter.V2GEncoder;

import java.net.InetSocketAddress;

//Simple EVCC tcp client
//Use this project for testing during the development of your CSMS server,
//or when expanding the Charge Point emulator.
//This project implements only the logic of the standard charging algorithm under normal conditions.
public class Main {
    public static void main(String[] args) {
        V2GEncoder v2GEncoder = new V2GEncoder();
        V2GDecoder v2GDecoder = new V2GDecoder();
        String hostname = "localhost";

        IoConnector connector = new NioSocketConnector();

        connector.setHandler(new ClientHandler(connector));

        connector.setFilterChainBuilder(chain -> chain.addLast("myFilter", new ProtocolCodecFilter(v2GEncoder, v2GDecoder)));

        connector.setConnectTimeoutMillis(30000);

        connector.connect(new InetSocketAddress(hostname, 8008));
    }
}