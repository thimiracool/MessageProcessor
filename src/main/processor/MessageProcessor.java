package main.processor;

import main.common.MessageProcessorConstants.MessageType;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.model.Product;
import main.recorder.MessageRecorder;
import main.recorder.Recorder;

import java.util.*;

import static main.common.MessageProcessorConstants.*;

public class MessageProcessor implements Processor {

    Map<Integer, Message> messageStore = new HashMap<>();
    Map<String, ArrayList<Product>> productStore = new HashMap<>();
    List<AdjustmentMessage> AdjustmentMessages = new ArrayList<>();

    Recorder recorder = new MessageRecorder();

    int messageId = 0;

    @Override
    public void process(Message message) {
        if (null != message) {
            messageId++;
            message.setMessageId(messageId);
            recorder.record(message.toString());

            MessageType messageType = message.getMessageType();
            if (messageType.RECORD == messageType) {
                String productType = message.getProductType().toLowerCase();
                Product product = new Product(productType, message.getValue());
                ArrayList<Product> values;
                if (productStore.containsKey(productType)) {
                    values = productStore.get(productType);

                } else {
                    values = new ArrayList<>();

                }
                values.add(product);
                productStore.put(productType, values);
            } else if (messageType.BULK == messageType) {
                BulkMessage bulkMessage = (BulkMessage) message;
                String productType = bulkMessage.getProductType().toLowerCase();
                double productValue = bulkMessage.getValue();
                int numberOfProducts = bulkMessage.getNumberOfOccurence();
                Product product = new Product(productType, productValue);
                ArrayList<Product> values;
                if (productStore.containsKey(productType)) {
                    values = productStore.get(productType);

                } else {
                    values = new ArrayList<>();

                }
                for (int i = 0; i < numberOfProducts; i++) {
                    values.add(product);
                }
                values.add(product);
                productStore.put(productType, values);

            } else if (messageType.ADJUSTMENT == messageType) {
                AdjustmentMessage adjustmentMessage = (AdjustmentMessage) message;
                AdjustmentMessages.add(adjustmentMessage);
            }

            if (messageId % 50 == 0) {
                for (AdjustmentMessage adjustmentMessage : AdjustmentMessages) {
                    String productType = adjustmentMessage.getProductType().toLowerCase();
                    double productValue = adjustmentMessage.getValue();
                    AdjustmentType adjustmentType = adjustmentMessage.getAdjustment();
                    double adjustedValue;

                    if (productStore.containsKey(productType)) {
                        ArrayList<Product> values = productStore.get(productType);
                        for (Product p : values) {

                            switch (adjustmentType) {
                                case ADD:
                                    adjustedValue = p.getValue() + productValue;
                                    p.setValue(adjustedValue);
                                    break;
                                case SUBTRACT:
                                    adjustedValue = p.getValue() - productValue;
                                    p.setValue(adjustedValue);
                                    break;
                                case MULTIPLY:
                                    adjustedValue = p.getValue() * productValue;
                                    p.setValue(adjustedValue);
                                    break;
                            }

                            values.add(p);
                            productStore.put(productType, values);
                        }

                    }

                }

                AdjustmentMessages.clear();

            }
            if (messageId % 10 == 0) {
                final Set<String> productSet = productStore.keySet();
                for (String productName : productSet) {
                    double totalValue = 0.0;
                    ArrayList<Product> productList = productStore.get(productName);
                    for (Product p : productList) {
                        totalValue += p.getValue();
                    }
                    recorder.record("ProductName : " + productName + "  Total : " + totalValue);
                }
            }
        }

    }
}
