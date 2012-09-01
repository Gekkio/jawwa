package fi.jawsy.jawwa.validation;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

public interface Validator<E, T> {

    Validation<E, T> validate(T object);

    void validate(T object, ImmutableList.Builder<E> errors);

    Validator<E, T> andThen(Validator<E, ? super T> other);

    Validator<E, T> filter(Predicate<? super T> predicate);

    Validator<E, T> union(Validator<E, ? super T> other);

}
