package fr.xelians.xdh.plugin.sender;

import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * @author aurelien on 10/01/2022
 */
public interface Sender {

	String send(String fileName, Path fromDirectory, Logger logger) throws Exception;

}
