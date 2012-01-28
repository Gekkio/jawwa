package fi.jawsy.jawwa.lang;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;

/**
 * Represents a value of one of two possible types (left or right).
 * 
 * @param <L>
 *            type of left value
 * @param <R>
 *            type of right value
 */
public abstract class Either<L, R> implements Serializable {

    private static final long serialVersionUID = -3948035962462400570L;

    /**
     * Private constructor that prevents unintended subclassing
     */
    private Either() {
    }

    /**
     * Returns the left value or throws an exception.
     * 
     * @throws UnsupportedOperationException
     *             if the right value is defined
     * @return the value
     */
    public L getLeftValue() {
        throw new UnsupportedOperationException("Attempted to get Left value from a Right");
    }

    /**
     * Returns the right value or throws an exception.
     * 
     * @throws UnsupportedOperationException
     *             if the left value is defined
     * @return the value
     */
    public R getRightValue() {
        throw new UnsupportedOperationException("Attempted to get Right value from a Left");
    }

    /**
     * Checks whether the left value is defined.
     * 
     * @return true if the left value is defined
     */
    public boolean isLeft() {
        return false;
    }

    /**
     * Checks whether the right value is defined.
     * 
     * @return true if the right value is defined
     */
    public boolean isRight() {
        return false;
    }

    /**
     * Returns an {@link Option} representing the left value.
     * 
     * @return possible left value
     */
    public Option<L> getLeft() {
        return Option.none();
    }

    /**
     * Returns an {@link Option} representing the right value.
     * 
     * @return possible right value
     */
    public Option<R> getRight() {
        return Option.none();
    }

    /**
     * Returns an {@link Either} that contains the given value in the left side.
     * 
     * @param <L>
     *            type of left value
     * @param <R>
     *            type of right value
     * @param value
     *            underlying value
     * @return an {@link Either} with a left value
     */
    public static <L, R> Either<L, R> left(L value) {
        return new Left<L, R>(value);
    }

    /**
     * Returns an {@link Either} that contains the given value in the left side. Class of the right value is used for
     * convenience purposes only.
     * 
     * @param <L>
     *            type of left value
     * @param <R>
     *            type of right value
     * @param value
     *            underlying value
     * @param clazzR
     *            class of the right value
     * @return an {@link Either} with a left value
     */
    public static <L, R> Either<L, R> left(L value, Class<R> clazzR) {
        return new Left<L, R>(value);
    }

    /**
     * Returns an {@link Either} that contains the given value in the right side.
     * 
     * @param <L>
     *            type of left value
     * @param <R>
     *            type of right value
     * @param value
     *            underlying value
     * @return an {@link Either} with a right value
     */
    public static <L, R> Either<L, R> right(R value) {
        return new Right<L, R>(value);
    }

    /**
     * Returns an {@link Either} that contains the given value in the right side. Class of the left value is used for
     * convenience purposes only.
     * 
     * @param <L>
     *            type of left value
     * @param <R>
     *            type of right value
     * @param value
     *            underlying value
     * @param clazzL
     *            class of the left value
     * @return an {@link Either} with a right value
     */
    public static <L, R> Either<L, R> right(R value, Class<L> clazzL) {
        return new Right<L, R>(value);
    }

    public abstract Object getValue();

    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    private final static class Left<L, R> extends Either<L, R> {
        private static final long serialVersionUID = -1700690199070133845L;

        private final L value;

        @Override
        public L getLeftValue() {
            return value;
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public Option<L> getLeft() {
            return Option.some(value);
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            val sb = new StringBuilder();
            sb.append("Left(");
            sb.append(value);
            sb.append(')');
            return sb.toString();
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedForm(value);
        }

        @AllArgsConstructor
        @NoArgsConstructor
        private static class SerializedForm implements Externalizable {
            private Object value;

            @Override
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
                value = in.readObject();
            }

            private Object readResolve() throws ObjectStreamException {
                return Either.left(value);
            }

            @Override
            public void writeExternal(ObjectOutput out) throws IOException {
                out.writeObject(value);
            }

        }

    }

    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    private final static class Right<L, R> extends Either<L, R> {
        private static final long serialVersionUID = -7139072909962934671L;

        private final R value;

        @Override
        public R getRightValue() {
            return value;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public Option<R> getRight() {
            return Option.some(value);
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            val sb = new StringBuilder();
            sb.append("Right(");
            sb.append(value);
            sb.append(')');
            return sb.toString();
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedForm(value);
        }

        @AllArgsConstructor
        @NoArgsConstructor
        private static class SerializedForm implements Externalizable {
            private Object value;

            @Override
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
                value = in.readObject();
            }

            private Object readResolve() throws ObjectStreamException {
                return Either.right(value);
            }

            @Override
            public void writeExternal(ObjectOutput out) throws IOException {
                out.writeObject(value);
            }

        }

    }

}