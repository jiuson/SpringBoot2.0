package com.yipinketang.app.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/websocket/{roomId}/{userFlag}")//roomId用于区分指定连接
public class WebSocketService {

    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketService对象
    private static CopyOnWriteArraySet<WebSocketService> webSocketServicesSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketServicesSet.add(this);
        addOnlineCount();
        System.out.println("有新的连接加入，当前在线人数: " + getOnlineCount());
        try {
            sendMessage("hello");
        } catch (IOException e) {
            System.out.println("IO异常");
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketServicesSet.remove(this);
        subOnlineCount();
        System.out.println("有一个连接关闭，当前在线人数: " + getOnlineCount());
    }

    /**
     * 收到客户端的消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(@PathParam("roomId") String roomId, String message, Session session){
        System.out.println("message from client: " + message);
        Map<String, String> currrentPathParameters = this.session.getPathParameters();

        for(WebSocketService webSocketService : webSocketServicesSet){
            Map<String, String> webSocketServicePathParameters = webSocketService.session.getPathParameters();
            try {
                //判断是否属于同一个连接
                if (currrentPathParameters.get("roomId").equalsIgnoreCase(webSocketServicePathParameters.get("roomId"))){
                    webSocketService.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("occur error");
        error.printStackTrace();
    }

    /**
     * 发消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * @param message
     */
    public static void sendInfo(String message){
        for(WebSocketService webSocketService : webSocketServicesSet){
            try {
                webSocketService.sendMessage(message);
            }catch (IOException e){
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        WebSocketService.onlineCount--;
    }
}
