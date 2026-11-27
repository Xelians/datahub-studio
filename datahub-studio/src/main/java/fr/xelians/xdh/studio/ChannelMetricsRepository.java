package fr.xelians.xdh.studio;


/**
 * Repository abstraction for persisting channel metrics.
 *
 * <p>
 * Metrics are stored as key–value pairs and exposed through the DataHub
 * interface or the supervision API.
 * </p>
 *
 * <p>
 * Implementations must ensure thread-safety and atomicity when updating values.
 * </p>
 */
public interface ChannelMetricsRepository {

    /**
     * Increments the metric associated with the given key.
     *
     * <p>
     * If the metric does not exist, it must be created with the provided value.
     * If it already exists, its current value must be atomically incremented
     * by the provided amount.
     * </p>
     *
     * @param key   the metric key (must not be {@code null} or blank)
     * @param delta the value to add to the current metric value
     *
     * @throws IllegalArgumentException if {@code key} is null or blank
     */
    void increment(String key, long delta);
}
