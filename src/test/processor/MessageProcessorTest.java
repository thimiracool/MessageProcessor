package test.processor;

import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.processor.MessageProcessor;
import org.junit.Test;

import static main.common.MessageProcessorConstants.AdjustmentType;
import static main.common.MessageProcessorConstants.MessageType;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageProcessorTest {
    @Test
    public void testProcessMessage()
    {
        Message message = new Message(MessageType.RECORD, "apple", 20);
        BulkMessage bulkMessage = new BulkMessage(MessageType.BULK, "orange", 15, 30);
        AdjustmentMessage adjustmentMessage = new AdjustmentMessage(MessageType.ADJUSTMENT, "apple", 3.0, AdjustmentType.ADD);
        MessageProcessor mp = new MessageProcessor();
        mp.process(bulkMessage);
        mp.process(adjustmentMessage);
        for (int a=0; a < 60; a++) {
            //mp.process(bulkMessage);
            mp.process(message);
        }



    }

}
