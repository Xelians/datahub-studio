package fr.xelians.xdh.studio.sender;

import fr.xelians.xdh.studio.StepResult;

/**
 * Result of a send step, indicating success or success with an alert.
 */
public class SenderResult extends StepResult {

    /**
     * Constructs a SenderResult with the provided success message and warning flag set to false.
     *
     * @param message The message indicating success.
     */
    public SenderResult(String message) {
        super(message);
    }

    /**
     * Constructs a SenderResult with the provided message and warning flag.
     *
     * @param message The message indicating success or success with alert.
     * @param warning A boolean indicating whether the return is a success (false) or a success with an alert (true).
     */
    public SenderResult(String message, boolean warning) {
        super(message, warning);
    }
}
