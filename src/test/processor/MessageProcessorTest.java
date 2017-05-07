package test.processor;

import main.common.MessageProcessorConstants;
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
        MessageProcessor mp = new MessageProcessor();
        mp.process(message);

    }

}
