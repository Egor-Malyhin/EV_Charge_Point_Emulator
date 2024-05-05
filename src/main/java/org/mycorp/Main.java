package org.mycorp;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.mycorp.protocolfilter.V2GDecoder;
import org.mycorp.protocolfilter.V2GEncoder;
import org.mycorp.protocolfilter.XMLConverter;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        XMLConverter xmlConverter = new XMLConverter();
        V2GEncoder v2GEncoder = new V2GEncoder(xmlConverter);
        V2GDecoder v2GDecoder = new V2GDecoder(xmlConverter);
        IoConnector connector = new NioSocketConnector();

        connector.addListener(new IoServiceListenerAdapter() {
            @Override
            public void sessionClosed(IoSession session) {
                connector.dispose();
            }
        });

        connector.setHandler(new ClientHandler());

        connector.setFilterChainBuilder(chain -> chain.addLast("myFilter", new ProtocolCodecFilter(v2GEncoder, v2GDecoder)));

        connector.setConnectTimeoutMillis(30000);

        connector.connect(new InetSocketAddress("localhost", 8008));

    }
}