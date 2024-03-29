package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.StepResult;

/**
 * Represents the result of a transformation step.
 * Contain the file name produced by the transformation and a message of success or success with alert
 */
public class TransformResult extends StepResult{
    private String fileName;

    /**
     * Constructs a TransformationResult with the provided file name, and warning flag set to false.
     *
     * @param fileName The name of the file produced in the transformation step.
     */
    public TransformResult(String fileName) {
        super(null);
        this.fileName=fileName;
    }

    /**
     * Constructs a TransformationResult with the provided file name, message, and warning flag set to false.
     *
     * @param fileName The name of the file produced in the transformation step.
     * @param message The message indicating success or success with alert.
     */
    public TransformResult(String fileName, String message) {
        super(message);
        this.fileName=fileName;
    }

    /**
     * Constructs a TransformationResult with the provided file name , message, and warning flag.
     *
     * @param fileName The name of the file produced in the transformation step.
     * @param message The message indicating success or success with alert.
     * @param warning A boolean indicating whether the return is a success (false) or a success with an alert (true).
     */
    public TransformResult(String fileName, String message, boolean warning) {
        super(message, warning);
        this.fileName=fileName;
    }

    /**
     * Retrieves the file name associated with this transformation result.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name associated with this transformation result.
     *
     * @param fileName The name of the file.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
