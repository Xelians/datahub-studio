package fr.xelians.xdh.studio.form;

import java.util.List;
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
    private final List<String> dependentFieldNames;

    /**
     * The comparison operator used to evaluate the condition.
     */
    private final ComparisonOperator comparisonOperator;

    /**
     * The behavior to be applied for display based on the condition.
     */
    private final DisplayBehavior displayBehavior;

    /**
     * The value on which the condition is dependent.
     */
    private final T comparedValue;

    private ConditionalDisplay(List<String> dependentFieldNames, ComparisonOperator comparisonOperator, DisplayBehavior displayBehavior, T comparedValue) {
        this.dependentFieldNames = dependentFieldNames;
        this.comparisonOperator = comparisonOperator;
        this.displayBehavior = displayBehavior;
        this.comparedValue = comparedValue;
    }

    public static <T> ConditionalDisplay<T> of(List<String> dependentFieldNames, DisplayBehavior displayBehavior, ComparisonOperator comparisonOperator, T comparedValue) {
        Objects.requireNonNull(dependentFieldNames, "dependentFieldName must be set");
        Objects.requireNonNull(comparisonOperator, "comparisonOperator must be set");
        Objects.requireNonNull(displayBehavior, "displayBehavior must be set");
        Objects.requireNonNull(comparedValue, "comparedValue must be set");
        return new ConditionalDisplay<>(dependentFieldNames, comparisonOperator, displayBehavior, comparedValue);
    }

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

    /**
     * get the dependent field name
     * @return the dependent field name
     */
    public List<String> getDependentFieldNames() {
        return dependentFieldNames;
    }

    /**
     * get the comparison operator
     * @return the comparison operator
     */
    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    /**
     * get the display behavior
     * @return the display behavior
     */
    public DisplayBehavior getDisplayBehavior() {
        return displayBehavior;
    }

    /**
     * get the compared value
     * @return the compared value
     */
    public T getComparedValue() {
        return comparedValue;
    }

}
