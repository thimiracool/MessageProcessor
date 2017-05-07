package main.processor;

import main.common.MessageProcessorConstants;
import main.common.MessageProcessorConstants.MessageType;
import main.model.Message;
import main.model.Product;
import main.recorder.MessageRecorder;
import main.recorder.Recorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProcessor implements Processor{

    Map<Integer, Message> messageStore = new HashMap<>();
    Map<String, ArrayList<Product>> productStore = new HashMap<>();
    List<Message> AdjustmentMessages = new ArrayList<>();

    Recorder recorder = new MessageRecorder();

    int messageId =0;

    @Override
    public void process(Message message) {
        if (null != message)
        {
            messageId++;
            message.setMessageId(messageId);
            recorder.record(message.toString());

            MessageType messageType = message.getMessageType();
            if(messageType.RECORD == messageType)
            {
               String productType =   message.getProductType().toLowerCase();
               Product product = new Product(productType, message.getValue());



            }

            if(messageId % 10 == 0)
            {
                
            }


        }



    }
}
