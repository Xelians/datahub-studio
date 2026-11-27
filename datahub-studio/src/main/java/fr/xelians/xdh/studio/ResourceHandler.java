package fr.xelians.xdh.studio;

public interface ResourceHandler {

    /**
     * Closes any open resources.
     * This method is used to safely close any open resources, such as files, streams,
     * or connections, to ensure proper resource management and prevent resource leaks.
     * It is important to implement this method and call it when you are done using the resources
     * to release them properly.
     */
    default void closeResources(){
    }
}