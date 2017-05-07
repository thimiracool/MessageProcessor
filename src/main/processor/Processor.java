package main.processor;

import main.model.Message;

/**
 * Created by thimira on 06/05/17.
 */
public interface Processor {

    void process(Message message);

}
