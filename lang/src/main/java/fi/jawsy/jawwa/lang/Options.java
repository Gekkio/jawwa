package fi.jawsy.jawwa.lang;

import java.io.Serializable;
import java.util.Iterator;

import com.google.common.collect.AbstractIterator;

/**
 * Utilities for Option objects.
 */
public final class Options {

    private Options() {
    }

    /**
     * Returns an iterator that extracts all existing values from the parent iterator. The iterator is serializable as
     * long as the given iterator is too.
     * 
     * @param iterator
     *            parent iterator with n values
     * @return iterator with 0..n values
     */
    public static <T> Iterator<T> flatten(final Iterator<Option<T>> iterator) {
        final class FlatteningIterator extends AbstractIterator<T> implements Serializable {
            private static final long serialVersionUID = -7316062488298537910L;

            @Override
            protected T computeNext() {
                while (iterator.hasNext()) {
                    for (T next : iterator.next()) {
                        return next;
                    }
                }
                return endOfData();
            }
        }
        return new FlatteningIterator();
    }

    /**
     * Returns a view of an Iterable that retains and unwraps all Some values from the parent iterable. The view is
     * serializable as long as the given iterable is too.
     * 
     * @param iterable
     *            parent iterable with n values
     * @return iterable with 0..n values
     */
    public static <T> Iterable<T> flatten(final Iterable<Option<T>> iterable) {
        final class FlatteningIterable implements Iterable<T>, Serializable {
            private static final long serialVersionUID = 4441232518997394186L;

            @Override
            public Iterator<T> iterator() {
                return flatten(iterable.iterator());
            }
        }
        return new FlatteningIterable();
    }

}
