package fi.jawsy.jawwa.validation;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList.Builder;

public final class ObjectValidators {

    private ObjectValidators() {
    }

    public static <E, T> Validator<E, T> verify(final Predicate<? super T> predicate, final E error) {
        class PredicateValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = 5115445636694446004L;

            @Override
            public void validate(T object, Builder<E> errors) {
                if (!predicate.apply(object))
                    errors.add(error);
            }

        }
        return new PredicateValidator();
    }

    public static <E, T> Validator<E, T> isEqualTo(final T other, final E error) {
        class IsEqualValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = 2800857602853469532L;

            @Override
            public void validate(T object, Builder<E> errors) {
                if (!Objects.equal(object, other))
                    errors.add(error);
            }

        }
        return new IsEqualValidator();
    }

    public static <E, T> Validator<E, T> isNotNull(final E error) {
        class NotNullValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = 7013368564713055028L;

            @Override
            public void validate(T object, Builder<E> errors) {
                if (object == null)
                    errors.add(error);
            }

        }
        return new NotNullValidator();
    }

}
