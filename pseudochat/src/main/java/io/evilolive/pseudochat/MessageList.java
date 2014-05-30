package io.evilolive.pseudochat;

import java.util.LinkedList;

/**
 * Created by Jonathan Almeida on 30/05/14.
 */
public class MessageList extends LinkedList<Message> {
    private static MessageList ourInstance = new MessageList();

    private MessageList() {}

    public static MessageList getInstance() {
        return ourInstance;
    }

    void addToList(int pos, Message message) {
        super.add(pos, message);
    }

    void addMessage(Message message) {
        super.add(message);
    }
}
