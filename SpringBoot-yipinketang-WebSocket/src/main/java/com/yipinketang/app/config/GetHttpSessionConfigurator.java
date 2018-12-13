package com.yipinketang.app.config;


import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        StandardSessionFacade standardSessionFacade = (StandardSessionFacade) request.getHttpSession();
        if (standardSessionFacade != null){
            HttpSession session = (HttpSession) request.getHttpSession();
            sec.getUserProperties().put("sessionId", session);
        }
        sec.getUserProperties().put("test", "hello");
        super.modifyHandshake(sec, request, response);
    }
}
