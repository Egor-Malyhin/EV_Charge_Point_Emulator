package org.mycorp.commev;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.mycorp.commev.protocolfilter.V2GDecoder;
import org.mycorp.commev.protocolfilter.V2GEncoder;
import org.mycorp.logging.EVMessagesLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;

//Core Class of EV Communication Module.
//Session Communication Logic described in EVCommunicationBlockSessionHandler.
@Component
public class EVCommunicationBlock implements Runnable {
    private final EVCommunicationBlockSessionHandler evCommunicationBlockSessionHandler;
    private final V2GDecoder v2gDecoder;
    private final V2GEncoder v2gEncoder;
    private final EVMessagesLogger evMessagesLogger;


    @Autowired
    public EVCommunicationBlock(EVCommunicationBlockSessionHandler evCommunicationBlockSessionHandler, V2GDecoder v2gDecoder, V2GEncoder v2gEncoder, EVMessagesLogger evMessagesLogger) {
        this.evCommunicationBlockSessionHandler = evCommunicationBlockSessionHandler;
        this.v2gDecoder = v2gDecoder;
        this.v2gEncoder = v2gEncoder;
        this.evMessagesLogger = evMessagesLogger;
    }

    @Override
    public void run() {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setHandler(evCommunicationBlockSessionHandler);
        try {
            DefaultIoFilterChainBuilder filterChainBuilder = acceptor.getFilterChain();
            filterChainBuilder.addLast("V2GFilter", new ProtocolCodecFilter(v2gEncoder, v2gDecoder));
            filterChainBuilder.addLast("LoggingFilter", evMessagesLogger);
            acceptor.bind(new InetSocketAddress(8008));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
