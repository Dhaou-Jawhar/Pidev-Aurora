package pidev.tn.aurora.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pidev.tn.aurora.model.Message;
import pidev.tn.aurora.service.SocketService;
import pidev.tn.aurora.services.Forum.IMessageService;
import pidev.tn.aurora.services.Users.IServiceUsers;

@Slf4j
@Component
public class SocketModule {


    private final SocketIOServer server;
    private final SocketService socketService;

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private IServiceUsers iServiceUsers;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
    }


    private DataListener<Message> onChatReceived() {
        log.info("SALEEEEEM");
        return (senderClient, data, ackSender) -> {
            log.info("SALEEEEEM2");
            log.info(data.toString());
            //iMessageService.addMessage(new pidev.tn.aurora.entities.Forum.Message(data.getMessage(),iServiceUsers.GetUser(data.getUserId())));
            socketService.sendMessage(data.getRoom(), "get_message", senderClient, data.getMessage());
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}
