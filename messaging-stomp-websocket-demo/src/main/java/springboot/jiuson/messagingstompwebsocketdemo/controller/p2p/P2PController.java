package springboot.jiuson.messagingstompwebsocketdemo.controller.p2p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import springboot.jiuson.messagingstompwebsocketdemo.message.ReceiveMessage;

@Controller
public class P2PController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 接收消息，并推送到指定的用户
     * @param receiveMessage
     */
    @MessageMapping("/queue")
    public void p2p(ReceiveMessage receiveMessage){
        //通过convertAndSendToUser方法，把消息推送到指定的用户，
        simpMessagingTemplate.convertAndSendToUser("tom", "message", receiveMessage.getName());
    }
}
