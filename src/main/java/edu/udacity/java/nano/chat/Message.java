package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private  int onlineCount;
    private String type;
    private String msg;


    public Message() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * WebSocket Server
     *
     * @see ServerEndpoint WebSocket Client
     * @see Session   WebSocket Session
     */

    @Component
    @ServerEndpoint("/chat/{username}")
    public static class WebSocketChatServer {

        /**
         * All chat sessions.
         */

        private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

        private static void sendMessageToAll(String msg) {
            //TODO: add send message method.
            onlineSessions.entrySet().forEach(entry -> {
                        try {
                            Session session = entry.getValue();

                            if(session.isOpen()) {

                                session.getBasicRemote()
                                        .sendText(msg);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        /**
         * Open connection, 1) add session, 2) add user.
         */

    //    public void onOpen(Session session) {
    //        //TODO: add on open connection.
    //    }
        @OnOpen
        public void onOpen(Session session,@PathParam("username") String username)throws IOException {
            onlineSessions.put(username, session);
            Message message = new Message();
            message.setType("ENTER");
        }

        /**
         * Send message, 1) get username and session, 2) send message to all.
         */
        @OnMessage
        public void onMessage(Session session, String jsonStr) throws IOException{
            //TODO: add send message.

            Message message = JSON.parseObject(jsonStr, Message.class);
            Message messageObj = new Message();
            messageObj.setMsg(message.getMsg());
            messageObj.setUsername(message.getUsername());
            messageObj.setOnlineCount(onlineSessions.size());
            messageObj.setType("SPEAK");

            String msgOutput= JSON.toJSONString(messageObj);
            sendMessageToAll(msgOutput);
        }

        /**
         * Close connection, 1) remove session, 2) update user.
         */
        @OnClose
        public void onClose(Session session,@PathParam("username") String username) {
            //TODO: add close connection.
            Message message = new Message();
            onlineSessions.remove(username);
            message.setType("CLOSE");


        }

        /**
         * Print exception.
         */
        @OnError
        public void onError(Session session, Throwable error) {
            error.printStackTrace();
        }



    }
}
