package fi.jawsy.jawwa.validation;

import java.io.Serializable;

import com.google.common.collect.ImmutableList.Builder;

public final class IntegerValidators {

    private IntegerValidators() {
    }

    public static <E> Validator<E, Integer> isPositive(final E error) {
        class PositiveValidator extends ValidatorBase<E, Integer> implements Serializable {
            private static final long serialVersionUID = -2920378288243780782L;

            @Override
            public void validate(Integer object, Builder<E> errors) {
                if (object < 0)
                    errors.add(error);
            }

        }
        return new PositiveValidator();
    }

}
