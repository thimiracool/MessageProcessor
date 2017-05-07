package test.simulator;

import main.common.MessageProcessorConstants;
import main.exception.InvalidMessageFormatException;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.processor.MessageMessageProcessorImpl;

/**
 * Created by thimira on 07/05/17.
 */
public class Simulator {
    public static void main(String[] args) throws InvalidMessageFormatException {

        /** This is the sample simulation for message processing.
        This will generate final output file under logs folder as follows

        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 80.0
        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 180.0
        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 280.0
        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 380.0
        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 480.0
        Message Processing is pausing ....
        Sales Type : orange  Adjustment OperationADD  Adjustment Value3.0
        Message Processing resumes ....
        ProductName : orange  Total : 690.0
        ProductName : apple  Total : 580.0
         */

        // Create record message.
        Message message = new Message(MessageProcessorConstants.MessageType.RECORD, "apple", 10);
        // Create bulk message.
        BulkMessage bulkMessage = new BulkMessage(MessageProcessorConstants.MessageType.BULK, "orange", 20, 30);
        // Create Adjustment message.
        AdjustmentMessage adjustmentMessage1 = new AdjustmentMessage(MessageProcessorConstants.MessageType.ADJUSTMENT, "orange", 3.0, MessageProcessorConstants.AdjustmentType.ADD);
        MessageMessageProcessorImpl mp = new MessageMessageProcessorImpl();
        // Process bulk message.
        mp.process(bulkMessage);
        // Process adjustment message.
        mp.process(adjustmentMessage1);
        // Process record messages.
        for (int a = 0; a < 58; a++) {
            mp.process(message);
        }
    }
}

