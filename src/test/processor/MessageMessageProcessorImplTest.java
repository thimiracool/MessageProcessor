package test.processor;

import main.exception.InvalidMessageFormatException;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.processor.MessageMessageProcessorImpl;
import org.junit.Assert;
import org.junit.Test;

import static main.common.MessageProcessorConstants.AdjustmentType;
import static main.common.MessageProcessorConstants.MessageType;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageMessageProcessorImplTest {

    @Test
    public void testProcessRecordMessage() throws InvalidMessageFormatException {
        Message message = new Message(MessageType.RECORD, "apple", 20);
        MessageMessageProcessorImpl mp = new MessageMessageProcessorImpl();
        int messageId = mp.process(message);
        Assert.assertNotNull(messageId) ;
    }

    @Test
    public void testProcessBulkMessage() throws InvalidMessageFormatException {
        BulkMessage bulkMessage = new BulkMessage(MessageType.BULK, "orange", 15, 30);
        MessageMessageProcessorImpl mp = new MessageMessageProcessorImpl();
        int messageId = mp.process(bulkMessage);
        Assert.assertNotNull(messageId) ;
    }

    @Test
    public void testProcessAdjustmentMessage() throws InvalidMessageFormatException {
        AdjustmentMessage adjustmentMessage = new AdjustmentMessage(MessageType.ADJUSTMENT, "apple", 3.0, AdjustmentType.ADD);
        MessageMessageProcessorImpl mp = new MessageMessageProcessorImpl();
        int messageId = mp.process(adjustmentMessage);
        Assert.assertNotNull(messageId) ;
    }




}
