package main.model;

import main.common.MessageProcessorConstants.MessageType;

/**
 * Created by thimira on 06/05/17.
 */
public class Message {
    private int messageId;
    private MessageType messageType;
    private String ProductType;
    private double value;

    public Message() {
    }

    public Message(MessageType messageType, String productType, double value) {
        this.messageType = messageType;
        ProductType = productType;
        this.value = value;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageType=" + messageType +
                ", ProductType='" + ProductType + '\'' +
                ", value=" + value +
                '}';
    }
}
