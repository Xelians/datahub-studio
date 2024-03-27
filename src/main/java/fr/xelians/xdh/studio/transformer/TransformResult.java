package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.StepResult;

/**
 * Result of a transformation process
 */
public class TransformResult extends StepResult{
    private String fileName;

    public TransformResult(String fileName, String message) {
        super(message);
        this.fileName=fileName;
    }

    public TransformResult(String fileName, String message, boolean warning) {
        super(message, warning);
        this.fileName=fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
