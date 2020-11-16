package com.sixfor.betanalysis;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketClientInst extends WebSocketClient {
    public WebSocketClientInst(URI serverUri ) {
        super( serverUri );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Connected" );
        send("2probe");

    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "got: " + message );

    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "Disconnected" );
        System.out.println( "reason:"  + reason);

    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();

    }
}
