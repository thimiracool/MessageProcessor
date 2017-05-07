package main.model;

import main.common.MessageProcessorConstants;

/**
 * Created by thimira on 06/05/17.
 */
public class BulkMessage extends Message{
    private int numberOfOccurence;

    public BulkMessage(MessageProcessorConstants.MessageType messageType, String productType, double value, int numberOfOccurence) {
        super(messageType, productType, value);
        this.numberOfOccurence = numberOfOccurence;
    }

    public int getNumberOfOccurence() {
        return numberOfOccurence;
    }

    public void setNumberOfOccurence(int numberOfOccurence) {
        this.numberOfOccurence = numberOfOccurence;
    }

    @Override
    public String toString() {
        return "BulkMessage{" +
                "numberOfOccurence=" + numberOfOccurence +
                "} " + super.toString();
    }
}
