package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import com.google.common.base.Function;

public final class Effects {

    private Effects() {
    }

    public static <T> Function<T, Void> toFunction(final Effect<T> e) {
        class EffectFunction implements Function<T, Void>, Serializable {
            private static final long serialVersionUID = -4601876026894597505L;

            @Override
            public Void apply(T input) {
                e.apply(input);
                return null;
            }
        }
        return new EffectFunction();
    }

    public static <T> Effect<T> fromFunction(final Function<? super T, ?> f) {
        class FromFunctionEffect implements Effect<T>, Serializable {
            private static final long serialVersionUID = -8381630600993099590L;

            @Override
            public void apply(T input) {
                f.apply(input);
            }
        }
        return new FromFunctionEffect();
    }

}
