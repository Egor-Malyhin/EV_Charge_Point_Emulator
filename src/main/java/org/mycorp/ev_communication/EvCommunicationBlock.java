package org.mycorp.ev_communication;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;

public class EvCommunicationBlock implements Runnable{
    private final EVCommunicationBlockHandler evCommunicationBlockHandler;

    @Autowired
    public EvCommunicationBlock(EVCommunicationBlockHandler evCommunicationBlockHandler) {
         this.evCommunicationBlockHandler = evCommunicationBlockHandler;
    }

    @Override
    public void run() {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setHandler(evCommunicationBlockHandler);
        try {
            acceptor.bind(new InetSocketAddress(8800));
            while(true){
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
