package fr.xelians.xdh.studio;

/**
 * A storage interface for persisting key-value data specific to a connector
 * during the execution of a channel process.
 * <p>
 * This store allows connectors (Collectors, Transformers, Senders, etc.) to
 * persist and retrieve contextual data while the connector remains active.
 * Data stays available until explicitly removed or the store is cleared or the channel is deleted.
 * </p>
 */
public interface ChannelStore {

    /**
     * Stores a key-value pair in the store.
     * <p>
     * If the key already exists, its value will be overwritten.
     * </p>
     *
     * @param key   the key under which the value will be stored (must not be null)
     * @param value the value to store (can be null)
     */
    void put(String key, String value);

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key the key whose associated value is to be returned (must not be null)
     * @return the value corresponding to the key, or {@code null} if the key does not exist
     */
    String get(String key);

    /**
     * Removes the value associated with the given key.
     *
     * @param key the key whose mapping is to be removed (must not be null)
     * @return the previously stored value for the key, or {@code null} if there was no mapping
     */
    String remove(String key);

    /**
     * Clears all key-value pairs from the store.
     * <p>
     * After calling this method, the store will be empty.
     * </p>
     */
    void clear();
}
