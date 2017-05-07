package main.model;

import main.common.MessageProcessorConstants;

/**
 * Created by thimira on 06/05/17.
 */
public class BulkMessage extends Message{
    private int numberOfOccurrence;

    public BulkMessage(MessageProcessorConstants.MessageType messageType, String productType, double value, int numberOfOccurrence) {
        super(messageType, productType, value);
        this.numberOfOccurrence = numberOfOccurrence;
    }

    public int getNumberOfOccurence() {
        return numberOfOccurrence;
    }

    public void setNumberOfOccurrence(int numberOfOccurrence) {
        this.numberOfOccurrence = numberOfOccurrence;
    }

    @Override
    public String toString() {
        return "BulkMessage{" +
                "numberOfOccurrence=" + numberOfOccurrence +
                "} " + super.toString();
    }
}
