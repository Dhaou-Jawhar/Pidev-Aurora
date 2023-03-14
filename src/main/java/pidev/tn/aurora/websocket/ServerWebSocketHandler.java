package pidev.tn.aurora.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        //logger.info("Server received: {}", request);
        System.out.print(request);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        //logger.info("Server sends: {}", response);
        System.out.print(response);
        session.sendMessage(new TextMessage(response));
    }
}
