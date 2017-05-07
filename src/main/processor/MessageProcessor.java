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
    boolean isPaused = false;

    @Override
    public void process(Message message) {
        if (null != message && !isPaused) {
            messageId++;
            message.setMessageId(messageId);
            messageStore.put(messageId, message);

            MessageType messageType = message.getMessageType();
            if (messageType.RECORD == messageType) {
                recordMessageProcess(message);
            } else if (messageType.BULK == messageType) {
                bulkMessageProcess((BulkMessage) message);
            } else if (messageType.ADJUSTMENT == messageType) {
                adjustmentMessageProcess((AdjustmentMessage) message);
            }

            messagePostProcessing();
        }

    }

    private void messagePostProcessing() {
        if (messageId % 10 == 0) {
            messagePostProcessingAfter10();

        }

        if (messageId % 50 == 0) {
            messagePostprocessingAfter50();
        }
    }

    private void messagePostProcessingAfter10() {

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

    private void messagePostprocessingAfter50() {
            isPaused = true;
            recorder.record("Message Processing is pausing ....");
            for (AdjustmentMessage adjustmentMessage : AdjustmentMessages) {
                String productType = adjustmentMessage.getProductType().toLowerCase();
                double productValue = adjustmentMessage.getValue();
                AdjustmentType adjustmentType = adjustmentMessage.getAdjustment();
                recorder.record("Sales Type : " + productType + "  Adjustment Operation" +
                        adjustmentType + "  Adjustment Value" + productValue);
            }

            AdjustmentMessages.clear();
            recorder.record("Message Processing resumes ....");
            isPaused = false;

    }

    private void adjustmentMessageProcess(AdjustmentMessage message) {
        AdjustmentMessage adjustmentMessage = message;
        AdjustmentMessages.add(adjustmentMessage);

        String productType = adjustmentMessage.getProductType().toLowerCase();
        double productValue = adjustmentMessage.getValue();
        AdjustmentType adjustmentType = adjustmentMessage.getAdjustment();
        ArrayList<Product> adjustedProducts = new ArrayList<>();
        double adjustedValue = 0.0;

        if (productStore.containsKey(productType)) {
            ArrayList<Product> values = productStore.get(productType);
            for (Product p : values) {

                switch (adjustmentType) {
                    case ADD:
                        adjustedValue = p.getValue() + productValue;
                        break;
                    case SUBTRACT:
                        adjustedValue = p.getValue() - productValue;
                        break;
                    case MULTIPLY:
                        adjustedValue = p.getValue() * productValue;
                        break;
                }

                Product adjustedProduct = new Product(productType, adjustedValue);
                adjustedProducts.add(adjustedProduct);
            }
            productStore.put(productType, adjustedProducts);
        }
    }

    private void bulkMessageProcess(BulkMessage message) {
        BulkMessage bulkMessage = message;
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
    }

    private void recordMessageProcess(Message message) {
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
    }
}
