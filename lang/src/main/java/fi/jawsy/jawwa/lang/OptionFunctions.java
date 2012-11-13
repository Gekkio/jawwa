package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import com.google.common.base.Function;

/**
 * Functions for Option objects.
 */
public final class OptionFunctions {

    private OptionFunctions() {
    }

    static class GetValueFunction<T> implements Function<Option<? extends T>, T>, Serializable {
        private static final long serialVersionUID = 5753367640741973339L;

        @SuppressWarnings("rawtypes")
        public static final GetValueFunction INSTANCE = new GetValueFunction();

        @Override
        public T apply(Option<? extends T> input) {
            return input.getValue();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<Option<? extends T>, T> getValue() {
        return GetValueFunction.INSTANCE;
    }

    static class OptionFunction<T> implements Function<T, Option<T>>, Serializable {
        private static final long serialVersionUID = -7512216163114972210L;

        @SuppressWarnings("rawtypes")
        public static final OptionFunction INSTANCE = new OptionFunction();

        @Override
        public Option<T> apply(T input) {
            return Option.option(input);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Option<T>> option() {
        return OptionFunction.INSTANCE;
    }

}
