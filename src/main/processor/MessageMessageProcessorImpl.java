package main.processor;

import main.common.MessageProcessorConstants.MessageType;
import main.exception.InvalidMessageFormatException;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;
import main.model.Product;
import main.report.ReportGenerator;
import main.report.ReportGeneratorImpl;
import main.utility.MessageProcessorValidationUtility;

import java.util.*;

import static main.common.MessageProcessorConstants.*;

public class MessageMessageProcessorImpl implements MessageProcessor {

    Map<Integer, Message> messageStore = new HashMap<>();
    Map<String, ArrayList<Product>> productStore = new HashMap<>();
    List<AdjustmentMessage> AdjustmentMessages = new ArrayList<>();

    private static int messageId = 0;
    private static boolean isPaused = false;

    ReportGenerator reportGenerator = new ReportGeneratorImpl();

    @Override
    public int process(Message message) throws InvalidMessageFormatException {
        // If message is not null and is not paused start processing the message.
        if (null != message && !isPaused) {
            // Increment the message id count.
            messageId++;
            message.setMessageId(messageId);
            // Record the incoming message in message store.
            messageStore.put(messageId, message);

            // Based on message type select processing path.
            MessageType messageType = message.getMessageType();
            if (messageType.RECORD == messageType) {
                recordMessageProcess(message);
            } else if (messageType.BULK == messageType) {
                bulkMessageProcess((BulkMessage) message);
            } else if (messageType.ADJUSTMENT == messageType) {
                adjustmentMessageProcess((AdjustmentMessage) message);
            }

            // Start message post processing to generate reports.
            messagePostProcessing();
        }
        return messageId;

    }

    private void recordMessageProcess(Message message) throws InvalidMessageFormatException {
        // Validate record message.
        MessageProcessorValidationUtility.isRecordMessageValid(message);
        // Retrieve product type.
        String productType = message.getProductType().toLowerCase();
        // Create product using product type and value.
        Product product = new Product(productType, message.getValue());
        ArrayList<Product> values;
        // If product type exists retrieve value set.
        if (productStore.containsKey(productType)) {
            values = productStore.get(productType);

        } else {
            // New Product type generate new value list.
            values = new ArrayList<>();

        }
        // Add product into value set.
        values.add(product);
        // Update product store.
        productStore.put(productType, values);
    }

    private void bulkMessageProcess(BulkMessage bulkMessage) throws InvalidMessageFormatException {
        // Validate bulk message.
        MessageProcessorValidationUtility.isBulkMessageValid(bulkMessage);
        // Retrieve product type.
        String productType = bulkMessage.getProductType().toLowerCase();
        // Retrieve product value.
        double productValue = bulkMessage.getValue();
        // Retrieve occurrences of the bulk message.
        int numberOfProducts = bulkMessage.getNumberOfOccurence();
        // Create single product using retrieved information.
        Product product = new Product(productType, productValue);
        ArrayList<Product> values;
        if (productStore.containsKey(productType)) {
            values = productStore.get(productType);

        } else {
            values = new ArrayList<>();

        }
        // Adding products to reach number of occurrences count.
        for (int i = 0; i < numberOfProducts; i++) {
            // Add product into value set.
            values.add(product);
        }
        // Update product store.
        productStore.put(productType, values);
    }

    private void adjustmentMessageProcess(AdjustmentMessage adjustmentMessage) throws InvalidMessageFormatException {
        // Validate adjustment message.
        MessageProcessorValidationUtility.isAdjustmentMessageValid(adjustmentMessage);
        // Add messages to adjustment list.
        AdjustmentMessages.add(adjustmentMessage);
        // Retrieve product type.
        String productType = adjustmentMessage.getProductType().toLowerCase();
        // Retrieve product value.
        double productValue = adjustmentMessage.getValue();
        // Retrieve adjustment type.
        AdjustmentType adjustmentType = adjustmentMessage.getAdjustment();
        ArrayList<Product> adjustedProducts = new ArrayList<>();
        double adjustedValue = 0.0;
        // Check product type exists in the product store.
        if (productStore.containsKey(productType)) {
            ArrayList<Product> values = productStore.get(productType);
            for (Product product : values) {

                // Adjust product value based on the adjustment type.
                switch (adjustmentType) {
                    case ADD:
                        adjustedValue = product.getValue() + productValue;
                        break;
                    case SUBTRACT:
                        adjustedValue = product.getValue() - productValue;
                        break;
                    case MULTIPLY:
                        adjustedValue = product.getValue() * productValue;
                        break;
                }
                // Create new product based on adjusted value.
                Product adjustedProduct = new Product(productType, adjustedValue);
                // Add to the adjusted products.
                adjustedProducts.add(adjustedProduct);
            }
            // Update product store.
            productStore.put(productType, adjustedProducts);
        }
    }

    private void messagePostProcessing() {
        // Message Post processing after 10 messages.
        if (messageId % 10 == 0) {
            messagePostProcessingAfter10();

        }
        // Message Post processing after 50 messages.
        if (messageId % 50 == 0) {
            messagePostprocessingAfter50();
        }
    }

    private void messagePostProcessingAfter10() {

        // Retrieve product value set.
        final Set<String> productSet = productStore.keySet();
        for (String productName : productSet) {
            double totalValue = 0.0;
            ArrayList<Product> productList = productStore.get(productName);
            for (Product p : productList) {
                // Calculate total value adding product values.
                totalValue += p.getValue();
            }
            // Generate log with the total value and product type.
            reportGenerator.record("ProductName : " + productName + "  Total : " + totalValue);
        }

    }

    private void messagePostprocessingAfter50() {
        // If message count to reach 50 pause the system.
        isPaused = true;
        reportGenerator.record("Message Processing is pausing ....");
        for (AdjustmentMessage adjustmentMessage : AdjustmentMessages) {
            String productType = adjustmentMessage.getProductType().toLowerCase();
            double productValue = adjustmentMessage.getValue();
            AdjustmentType adjustmentType = adjustmentMessage.getAdjustment();
            // Generate log with product type, operation type and value.
            reportGenerator.record("Sales Type : " + productType + "  Adjustment Operation" +
                    adjustmentType + "  Adjustment Value" + productValue);
        }

        // Clear adjustment message list.
        AdjustmentMessages.clear();
        reportGenerator.record("Message Processing resumes ....");
        // Resume system operation.
        isPaused = false;

    }
}
