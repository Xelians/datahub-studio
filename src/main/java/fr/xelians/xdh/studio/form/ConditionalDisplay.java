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

    /**
     * Constructs a new ConditionalDisplay instance with the provided builder.
     *
     * @param builder The builder containing all necessary parameters to create a ConditionalDisplay.
     */
    private ConditionalDisplay(Builder<T> builder) {
        this.dependentFieldName = builder.dependentFieldName;
        this.comparisonOperator = builder.comparisonOperator;
        this.displayBehavior = builder.displayBehavior;
        this.comparedValue = builder.comparedValue;
    }

    /**
     * Builder class for constructing ConditionalDisplay instances.
     *
     * @param <T> The type of the dependent value.
     */
    public static class Builder<T> {
        private String dependentFieldName;
        private DisplayBehavior displayBehavior;
        private ComparisonOperator comparisonOperator;
        private T comparedValue;

        private Builder(String dependentFieldName, DisplayBehavior displayBehavior, ComparisonOperator comparisonOperator, T comparedValue) {
            this.dependentFieldName = dependentFieldName;
            this.comparisonOperator = comparisonOperator;
            this.displayBehavior = displayBehavior;
            this.comparedValue = comparedValue;
        }

        public Builder() {
        }

        /**
         * Constructs a new ConditionalDisplay instance with the configured parameters.
         *
         * @return A new ConditionalDisplay instance.
         */
        public ConditionalDisplay<T> build() {
            Objects.requireNonNull(dependentFieldName, "dependentFieldName must be set");
            Objects.requireNonNull(comparisonOperator, "comparisonOperator must be set");
            Objects.requireNonNull(displayBehavior, "displayBehavior must be set");
            Objects.requireNonNull(comparedValue, "comparedValue must be set");
            return new ConditionalDisplay<>(this);
        }

        /**
         * Sets the name of the dependent field.
         *
         * @param dependentFieldName The name of the dependent field.
         * @return This builder instance.
         */
        public BuilderDependentFieldName<T> dependentField(String dependentFieldName) {
            return new BuilderDependentFieldName<>(dependentFieldName);
        }

        public static class BuilderDependentFieldName<T> {
            private final String dependentFieldName;

            private BuilderDependentFieldName(String dependentFieldName) {
                this.dependentFieldName = dependentFieldName;
            }

            /**
             * Sets the display behavior based on the condition.
             *
             * @param displayBehavior The display behavior to be applied.
             * @return This builder instance.
             */
            private BuilderDisplayBehavior<T> displayBehavior(DisplayBehavior displayBehavior) {
                return new BuilderDisplayBehavior<>(dependentFieldName, displayBehavior);
            }

            /**
             * Sets the display Disable behavior based on the condition.
             *
             * @return This builder instance.
             */
            public BuilderDisplayBehavior<T> isDisabled() {
                return displayBehavior(DisplayBehavior.DISABLE);
            }

            /**
             * Sets the display Hidden behavior based on the condition.
             *
             * @return This builder instance.
             */
            public BuilderDisplayBehavior<T> isHidden() {
                return displayBehavior(DisplayBehavior.HIDDEN);
            }
        }

        public static class BuilderDisplayBehavior<T> {
            private final String dependentFieldName;
            private final DisplayBehavior displayBehavior;

            /**
             * Constructs a new BuilderDisplayBehavior instance.
             *
             * @param dependentFieldName The name of the field on which the display behavior is dependent.
             * @param displayBehavior    The display behavior to be applied based on conditions.
             */
            private BuilderDisplayBehavior(String dependentFieldName, DisplayBehavior displayBehavior) {
                this.dependentFieldName = dependentFieldName;
                this.displayBehavior = displayBehavior;
            }

            /**
             * Specifies the "equal to" condition for display behavior.
             *
             * @param comparedValue The value against which the condition is evaluated.
             *                      Display behavior will apply when the field value is equal to this compared value.
             * @return A Builder instance configured with the specified comparison condition.
             */
            public Builder<T> whenEqualTo(T comparedValue) {
                return new Builder<>(dependentFieldName, displayBehavior, ComparisonOperator.EQUAL, comparedValue);
            }

            /**
             * Specifies the "greater than" condition for display behavior.
             *
             * @param comparedValue The value against which the condition is evaluated.
             *                      Display behavior will apply when the field value is greater than this compared value.
             * @return A Builder instance configured with the specified comparison condition.
             */
            public Builder<T> whenGreaterThan(T comparedValue) {
                return new Builder<>(dependentFieldName, displayBehavior, ComparisonOperator.GREATER_THAN, comparedValue);
            }

            /**
             * Specifies the "less than" condition for display behavior.
             *
             * @param comparedValue The value against which the condition is evaluated.
             *                      Display behavior will apply when the field value is less than this compared value.
             * @return A Builder instance configured with the specified comparison condition.
             */
            public Builder<T> whenLessThan(T comparedValue) {
                return new Builder<>(dependentFieldName, displayBehavior, ComparisonOperator.LESS_THAN, comparedValue);
            }

            /**
             * Specifies the "greater than or equal to" condition for display behavior.
             *
             * @param comparedValue The value against which the condition is evaluated.
             *                      Display behavior will apply when the field value is greater than or equal to this compared value.
             * @return A Builder instance configured with the specified comparison condition.
             */
            public Builder<T> whenGreaterThanOrEqualTo(T comparedValue) {
                return new Builder<>(dependentFieldName, displayBehavior, ComparisonOperator.GREATER_THAN_OR_EQUAL, comparedValue);
            }

            /**
             * Specifies the "less than or equal to" condition for display behavior.
             *
             * @param comparedValue The value against which the condition is evaluated.
             *                      Display behavior will apply when the field value is less than or equal to this compared value.
             * @return A Builder instance configured with the specified comparison condition.
             */
            public Builder<T> whenLessThanOrEqualTo(T comparedValue) {
                return new Builder<>(dependentFieldName, displayBehavior, ComparisonOperator.LESS_THAN_OR_EQUAL, comparedValue);
            }
        }


    }

    /**
     * Enum representing different types of comparison operators.
     */
    private enum ComparisonOperator {
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
    private enum DisplayBehavior {
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
