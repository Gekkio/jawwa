package fi.jawsy.jawwa.validation;

import java.io.Serializable;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public abstract class ValidatorBase<E, T> implements Validator<E, T> {

    @Override
    public Validation<E, T> validate(T object) {
        ImmutableList.Builder<E> errors = ImmutableList.builder();
        validate(object, errors);

        return Validation.create(object, errors.build());
    }

    @Override
    public Validator<E, T> andThen(final Validator<E, ? super T> other) {
        class AndThenValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = -2988850109506544511L;

            @Override
            public void validate(T object, Builder<E> errors) {
                Validation<E, T> result = ValidatorBase.this.validate(object);
                if (result.isValue())
                    other.validate(object, errors);
                else
                    errors.addAll(result.getErrors());
            }

        }
        return new AndThenValidator();
    }

    @Override
    public Validator<E, T> filter(final Predicate<? super T> predicate) {
        class FilterValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = 5663115009831067782L;

            @Override
            public void validate(T object, Builder<E> errors) {
                if (predicate.apply(object))
                    ValidatorBase.this.validate(object, errors);
            }

        }
        return new FilterValidator();
    }

    @Override
    public Validator<E, T> union(final Validator<E, ? super T> other) {
        class UnionValidator extends ValidatorBase<E, T> implements Serializable {
            private static final long serialVersionUID = 1305487742494758030L;

            @Override
            public void validate(T object, Builder<E> errors) {
                ValidatorBase.this.validate(object, errors);
                other.validate(object, errors);
            }

        }
        return new UnionValidator();
    }

}
