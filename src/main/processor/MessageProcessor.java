package main.processor;

import main.exception.InvalidMessageFormatException;
import main.model.Message;

/**
 * Created by thimira on 06/05/17.
 */
public interface MessageProcessor {

    // Process message.
    int process(Message message) throws InvalidMessageFormatException;

}
