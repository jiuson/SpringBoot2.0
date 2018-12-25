package springboot.jiuson.messagingstompwebsocketdemo.controller.sub;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import springboot.jiuson.messagingstompwebsocketdemo.message.ReceiveMessage;

@Controller
public class SubController {

    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 接收消息并广播
     * @param receiveMessage
     */
    @MessageMapping("/subscrible")
    public void subscrible(ReceiveMessage receiveMessage){
        //广播使用convertAndSend方法，第一个参数为广播地址，和js中订阅的地址要一致
        simpMessagingTemplate.convertAndSend("/topic/sub", receiveMessage.getName());
    }
}
