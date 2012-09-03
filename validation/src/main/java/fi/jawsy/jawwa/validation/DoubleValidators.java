package fi.jawsy.jawwa.validation;

import java.io.Serializable;

import com.google.common.collect.ImmutableList.Builder;

public final class DoubleValidators {

    private DoubleValidators() {
    }

    public static <E> Validator<E, Double> isPositive(final E error) {
        class PositiveValidator extends ValidatorBase<E, Double> implements Serializable {
            private static final long serialVersionUID = -2699511581231169209L;

            @Override
            public void validate(Double object, Builder<E> errors) {
                if (object < 0.0)
                    errors.add(error);
            }

        }
        return new PositiveValidator();
    }

}
