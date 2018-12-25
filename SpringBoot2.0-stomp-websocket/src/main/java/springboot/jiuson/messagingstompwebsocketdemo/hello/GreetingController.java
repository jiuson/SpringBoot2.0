package springboot.jiuson.messagingstompwebsocketdemo.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

    /**
     * 接收客户端发送到服务器的消息，并广播出去
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")//广播地址
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        logger.info("收到客户端发送过来的消息：" + message.toString());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @SubscribeMapping("/subscription")
    public Object subscription(){
        return "订阅成功！";
    }
}
