package fr.xelians.xdh.studio.sender;

import fr.xelians.xdh.studio.StepResult;

/**
 * Result of a sending process
 */
public class SenderResult extends StepResult {

    public SenderResult(String message) {
        super(message);
    }
    public SenderResult(String message, boolean warning) {
        super(message, warning);
    }
}
