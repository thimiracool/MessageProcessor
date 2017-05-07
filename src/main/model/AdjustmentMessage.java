package main.model;

import main.common.MessageProcessorConstants;
import main.common.MessageProcessorConstants.AdjustmentType;

/**
 * Created by thimira on 06/05/17.
 */
public class AdjustmentMessage extends Message {

    private AdjustmentType adjustment;

    public AdjustmentMessage(MessageProcessorConstants.MessageType messageType, String productType, double value, AdjustmentType adjustment) {
        super(messageType, productType, value);
        this.adjustment = adjustment;
    }

    public AdjustmentType getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(AdjustmentType adjustment) {
        this.adjustment = adjustment;
    }

    @Override
    public String toString() {
        return "AdjustmentMessage{" +
                "adjustment=" + adjustment +
                "} " + super.toString();
    }
}