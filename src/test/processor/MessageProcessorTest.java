package test.processor;

import main.common.MessageProcessorConstants;
import main.model.BulkMessage;
import main.model.Message;
import main.processor.MessageProcessor;
import org.junit.Test;

import static main.common.MessageProcessorConstants.*;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageProcessorTest {
    @Test
    public void testProcessMessage()
    {
        Message message = new Message(MessageType.RECORD, "apple", 20);
        BulkMessage bulkMessage = new BulkMessage(MessageType.BULK, "orange", 15, 30);
        MessageProcessor mp = new MessageProcessor();
        mp.process(message);
        for (int a=0; a < 20; a++) {

            mp.process(bulkMessage);
            mp.process(message);
        }


    }

}
