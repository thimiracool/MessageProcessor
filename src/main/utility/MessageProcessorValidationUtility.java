package main.utility;

import main.exception.InvalidMessageFormatException;
import main.model.AdjustmentMessage;
import main.model.BulkMessage;
import main.model.Message;

/**
 * Created by thimira on 07/05/17.
 */
public class MessageProcessorValidationUtility {

    public static void isRecordMessageValid(Message message) throws InvalidMessageFormatException {
        if (null == message.getProductType() && "" == message.getProductType())
        {
            throw new InvalidMessageFormatException("Message Product Type can not be null or empty !");
        }
        else if (message.getValue() < 0)
        {
            throw new InvalidMessageFormatException("Message Product value can not be negative!");
        }

    }
    public static void isBulkMessageValid(BulkMessage message) throws InvalidMessageFormatException {
        if (null == message.getProductType() && "" == message.getProductType())
        {
            throw new InvalidMessageFormatException("Bulk Message Product Type can not be null or empty !");
        }
        else if (message.getValue() < 0)
        {
            throw new InvalidMessageFormatException("Bulk Message value can not be negative !");
        }
        else if (message.getNumberOfOccurence() < 0)
        {
            throw new InvalidMessageFormatException("Bulk Message Number Of occurrence can not be negative !");
        }
    }

    public static void isAdjustmentMessageValid(AdjustmentMessage message) throws InvalidMessageFormatException {
        if (null == message.getProductType() && "" == message.getProductType())
        {
            throw new InvalidMessageFormatException("Adjustment Message Product Type can not be null or empty !");
        }
        else if (message.getValue() < 0)
        {
            throw new InvalidMessageFormatException("Adjustment  Message value can not be negative !");
        }
    }

}
