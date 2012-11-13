package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import com.google.common.base.Predicate;

/**
 * Predicates for String objects.
 */
public final class StringPredicates {

    private StringPredicates() {
    }

    static class IsEmptyPredicate implements Predicate<String>, Serializable {
        private static final long serialVersionUID = -1705328419251983539L;

        public static final IsEmptyPredicate INSTANCE = new IsEmptyPredicate();

        @Override
        public boolean apply(String input) {
            return input.isEmpty();
        }
    }

    public static Predicate<? super String> isEmpty() {
        return IsEmptyPredicate.INSTANCE;
    }

}
