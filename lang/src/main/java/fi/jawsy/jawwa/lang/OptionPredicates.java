package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import com.google.common.base.Predicate;

/**
 * Predicates for Option objects.
 */
public final class OptionPredicates {

    private OptionPredicates() {
    }

    static class IsDefinedPredicate implements Predicate<Option<? extends Object>>, Serializable {

        private static final long serialVersionUID = -4799134802946996403L;

        public static final IsDefinedPredicate INSTANCE = new IsDefinedPredicate();

        @Override
        public boolean apply(Option<? extends Object> input) {
            return input.isDefined();
        }

    }

    /**
     * Returns a predicate that calls isDefined() on input values.
     * 
     * @return predicate
     */
    public static final Predicate<? super Option<?>> isDefined() {
        return IsDefinedPredicate.INSTANCE;
    }

}
