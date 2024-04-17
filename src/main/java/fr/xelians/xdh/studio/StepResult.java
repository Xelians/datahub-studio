package fr.xelians.xdh.studio;

/**
 * Result of a Transformation or Transfer step, indicating success or success with an alert.
 */
public class StepResult {

    private String message;
    private boolean warning;

    /**
     * Constructs a StepResult with the provided success message and warning flag set to false.
     *
     * @param message The message indicating success.
     */
    public StepResult(String message){
        this(message, false);
    }

    /**
     * Constructs a StepResult with the provided message and warning flag.
     *
     * @param message The message indicating success or alert.
     * @param warning A boolean indicating whether the return is a success (false) or a success with an alert (true).
     */
    public StepResult(String message, boolean warning){
        this.message=message;
        this.warning=warning;
    }

    /**
     * Retrieves the message associated with this step result.
     *
     * @return The message indicating success or success with alert.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with this step result.
     *
     * @param message The message indicating success or success with alert.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Checks if this result indicates a success or a success with an alert.
     *
     * @return True if the result is a success with an alert, false if it's a success.
     */
    public boolean hasWarning() {
        return warning;
    }

    /**
     * Sets whether this result is a success or a success with an alert.
     *
     * @param warning A boolean indicating whether the return is a success (false) or a success with an alert (true).
     */
    public void setWarning(boolean warning) {
        this.warning = warning;
    }
}
