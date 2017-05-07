# Message Processor

## Prerequisites
    Java 8
    Junit 1.4 or above

## Authors

* **Thimira Darshana Upatissa** -  - [Thimira Upatissa](https://github.com/thimiracool)

## Project Structure

    Main Source : Main source is avaible under src/main location.
    Logs        : Reports will be generated under /src/main/logs location.
    Unit Tests  : Unit tests are under test/processor location.
    Simulator   : Test simulator available under src/test/simulator location.

## Developer notes
### Message Types
        Record     - sales request.
        Bulk       - bulk sales requests.
        Adjustment - adjustment for sales requests.

### Adjustment Operations
        ADD, SUBTRACT, MULTIPLY

### Sample code to generate messages

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
