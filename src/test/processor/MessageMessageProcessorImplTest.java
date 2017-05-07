package test.processor;

import main.exception.InvalidMessageFormatException;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.processor.MessageMessageProcessorImpl;
import org.junit.Test;

import static main.common.MessageProcessorConstants.AdjustmentType;
import static main.common.MessageProcessorConstants.MessageType;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageMessageProcessorImplTest {
    @Test
    public void testProcessMessage() throws InvalidMessageFormatException {
        Message message = new Message(MessageType.RECORD, "apple", 20);
        BulkMessage bulkMessage = new BulkMessage(MessageType.BULK, "orange", 15, 30);
        AdjustmentMessage adjustmentMessage = new AdjustmentMessage(MessageType.ADJUSTMENT, "apple", 3.0, AdjustmentType.ADD);
        MessageMessageProcessorImpl mp = new MessageMessageProcessorImpl();
        mp.process(bulkMessage);

        mp.process(bulkMessage);
        for (int a=0; a < 20; a++) {

            mp.process(message);
        }
        mp.process(adjustmentMessage);
        for (int a=0; a < 60; a++) {

            mp.process(message);
        }



    }

}
