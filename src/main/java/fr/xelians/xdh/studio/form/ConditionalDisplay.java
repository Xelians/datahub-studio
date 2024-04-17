package fr.xelians.xdh.studio.form;

import java.util.Objects;

/**
 * A class representing conditional display settings for an element based on certain conditions.
 * It encapsulates information such as the name of the dependent field, the comparison operator,
 * the display behavior, and the dependent value.
 *
 * @param <T> The type of the dependent value.
 */
public class ConditionalDisplay<T> {
    /**
     * The name of the dependent field.
     */
    private String dependentFieldName;

    /**
     * The comparison operator used to evaluate the condition.
     */
    private ComparisonOperator comparisonOperator;

    /**
     * The behavior to be applied for display based on the condition.
     */
    private DisplayBehavior displayBehavior;

    /**
     * The value on which the condition is dependent.
     */
    private T comparedValue;

    private ConditionalDisplay(String dependentFieldName, ComparisonOperator comparisonOperator, DisplayBehavior displayBehavior, T comparedValue) {
        this.dependentFieldName = dependentFieldName;
        this.comparisonOperator = comparisonOperator;
        this.displayBehavior = displayBehavior;
        this.comparedValue = comparedValue;
    }

    public static <T> ConditionalDisplay<T> of(String dependentFieldName, DisplayBehavior displayBehavior, ComparisonOperator comparisonOperator, T comparedValue) {
        Objects.requireNonNull(dependentFieldName, "dependentFieldName must be set");
        Objects.requireNonNull(comparisonOperator, "comparisonOperator must be set");
        Objects.requireNonNull(displayBehavior, "displayBehavior must be set");
        Objects.requireNonNull(comparedValue, "comparedValue must be set");
        return new ConditionalDisplay<>(dependentFieldName, comparisonOperator, displayBehavior, comparedValue);
    }

    /**
     * Constructs a new ConditionalDisplay instance with the provided builder.
     *
     * @param builder The builder containing all necessary parameters to create a ConditionalDisplay.
     */

    /**
     * Enum representing different types of comparison operators.
     */
    public enum ComparisonOperator {
        EQUAL,
        LESS_THAN_OR_EQUAL,
        LESS_THAN,
        GREATER_THAN_OR_EQUAL,
        GREATER_THAN
    }

    /**
     * Enumeration representing possible display behaviors for an element.
     * Behaviors include disabling (DISABLE) and hiding (HIDDEN).
     */
    public enum DisplayBehavior {
        /**
         * Indicates that the element is disabled.
         */
        DISABLE,

        /**
         * Indicates that the element is hidden and will not be rendered visible.
         * However, it may still be active or functional within the system.
         */
        HIDDEN
    }

}
