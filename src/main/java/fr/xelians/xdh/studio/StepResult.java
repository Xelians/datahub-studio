package fr.xelians.xdh.studio;

/**
 * Result of a step execution
 */
public class StepResult {

    private String message;
    private boolean warning;

    public StepResult(String message){
        this(message, false);
    }
    public StepResult(String message, boolean warning){
        this.message=message;
        this.warning=warning;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }
}
