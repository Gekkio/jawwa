package fi.jawsy.jawwa.validation;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public abstract class Validation<E, T> implements Serializable {

    private static final long serialVersionUID = -5573828311815317365L;

    public static <E, T> Validation<E, T> success(T object) {
        return new Success<E, T>(object);
    }

    public static <E, T> Validation<E, T> failure(ImmutableList<E> errors) {
        Preconditions.checkArgument(!errors.isEmpty(), "errors cannot be empty");
        return new Failure<E, T>(errors);
    }

    public static <E, T> Validation<E, T> create(T object, ImmutableList<E> errors) {
        if (errors.isEmpty())
            return success(object);
        return failure(errors);
    }

    public boolean isValue() {
        return false;
    }

    public T getValue() {
        throw new IllegalStateException("Validation has no value");
    }

    public ImmutableList<E> getErrors() {
        throw new IllegalStateException("Validation has no errors");
    }

    public boolean isErrors() {
        return false;
    }

    public abstract <O> Validation<E, O> map(Function<? super T, O> f);

    public String printErrors() {
        return Joiner.on('\n').join(getErrors());
    }

    Validation() {
    }

    @RequiredArgsConstructor
    static class Success<E, T> extends Validation<E, T> {

        private static final long serialVersionUID = -7242935179686595621L;

        @Getter
        private final T value;

        @Override
        public boolean isValue() {
            return true;
        }

        @Override
        public <O> Validation<E, O> map(Function<? super T, O> f) {
            return new Success<E, O>(f.apply(value));
        }

    }

    @RequiredArgsConstructor
    static class Failure<E, T> extends Validation<E, T> {

        private static final long serialVersionUID = -1174711172350634743L;

        @Getter
        private final ImmutableList<E> errors;

        @Override
        public boolean isErrors() {
            return true;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public <O> Validation<E, O> map(Function<? super T, O> f) {
            return (Validation) this;
        }

    }

}
