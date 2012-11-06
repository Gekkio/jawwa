package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONAware;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.SeriesDelta.Clear;

abstract class SeriesBase<P> extends ForwardingList<P> implements HighchartsSeries<P>, JSONAware, Serializable {

    private static final long serialVersionUID = -5416147993199253622L;

    private final List<SeriesDeltaListener<? super P>> listeners = Lists.newArrayList();

    protected final List<P> data = Lists.newArrayList();

    private final Value<Animation> animation = new Value<Animation>();

    public Value<Animation> animation() {
        return animation;
    }

    SeriesBase() {
    }

    @Override
    protected List<P> delegate() {
        return data;
    }

    @Override
    public Iterator<P> iterator() {
        class Iter implements Iterator<P> {
            private final Iterator<P> inner = data.iterator();
            private int currentIndex = -1;
            private int indexOffset = 0;

            @Override
            public boolean hasNext() {
                return inner.hasNext();
            }

            @Override
            public P next() {
                P value = inner.next();

                currentIndex += (1 + indexOffset);
                indexOffset = 0;

                return value;
            }

            @Override
            public void remove() {
                inner.remove();
                indexOffset -= 1;
                fire(SeriesDelta.Remove.<P> create(currentIndex, animation.get()));
            }
        }
        return new Iter();
    }

    @Override
    public ListIterator<P> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<P> listIterator(final int index) {
        class ListIter implements ListIterator<P> {
            private final ListIterator<P> inner = data.listIterator(index);
            private int currentIndex = inner.nextIndex();

            @Override
            public boolean hasNext() {
                return inner.hasNext();
            }

            @Override
            public P next() {
                int nextIndex = inner.nextIndex();
                P value = inner.next();
                currentIndex = nextIndex;
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return inner.hasPrevious();
            }

            @Override
            public P previous() {
                int prevIndex = inner.previousIndex();
                P value = inner.previous();
                currentIndex = prevIndex;
                return value;
            }

            @Override
            public int nextIndex() {
                return inner.nextIndex();
            }

            @Override
            public int previousIndex() {
                return inner.previousIndex();
            }

            @Override
            public void remove() {
                inner.remove();
                fire(SeriesDelta.Remove.<P> create(currentIndex, animation.get()));
            }

            @Override
            public void set(P e) {
                inner.set(e);
                fire(SeriesDelta.Replace.create(currentIndex, e, animation.get()));
            }

            @Override
            public void add(P e) {
                inner.add(e);
                fire(SeriesDelta.Add.create(currentIndex, e, animation.get()));
            }
        }
        return new ListIter();
    }

    @Override
    public String toJSONString() {
        return JSONArray.toJSONString(data);
    }

    @Override
    public String toString() {
        return Iterables.toString(data);
    }

    @Override
    public boolean add(P element) {
        data.add(element);
        fire(SeriesDelta.Append.create(element, animation.get()));
        return true;
    }

    @Override
    public void add(int index, P element) {
        data.add(index, element);
        fire(SeriesDelta.Add.create(index, element, animation.get()));
    }

    @Override
    public boolean addAll(Collection<? extends P> elements) {
        boolean result = data.addAll(elements);
        for (P point : elements) {
            fire(SeriesDelta.Append.create(point, animation.get()));
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends P> elements) {
        boolean result = data.addAll(index, elements);
        int currentIndex = index;
        for (P point : elements) {
            fire(SeriesDelta.Add.create(currentIndex, point, animation.get()));
            currentIndex += 1;
        }
        return result;
    }

    @Override
    public void clear() {
        data.clear();
        fire(Clear.<P> create(animation.get()));
    }

    @Override
    public boolean remove(Object object) {
        int index = data.indexOf(object);
        if (index == -1)
            return false;
        data.remove(index);
        fire(SeriesDelta.Remove.<P> create(index, animation.get()));
        return true;
    }

    @Override
    public P remove(int index) {
        P result = data.remove(index);
        fire(SeriesDelta.Remove.<P> create(index, animation.get()));
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return batchRemove(collection, false);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return batchRemove(collection, true);
    }

    private boolean batchRemove(Collection<?> collection, boolean complement) {
        List<Integer> indices = Lists.newArrayList();
        boolean result = false;

        Iterator<P> iterator = data.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            P point = iterator.next();
            if (collection.contains(point) != complement) {
                result = true;
                iterator.remove();
                indices.add(i);
            } else {
                i += 1;
            }
        }

        for (int index : indices) {
            fire(SeriesDelta.Remove.<P> create(index, animation.get()));
        }

        return result;
    }

    @Override
    public P set(int index, P element) {
        P result = data.set(index, element);
        fire(SeriesDelta.Replace.create(index, element, animation.get()));
        return result;
    }

    @Override
    public void addSeriesDeltaListener(SeriesDeltaListener<? super P> l) {
        listeners.add(Preconditions.checkNotNull(l, "listener cannot be null"));
    }

    @Override
    public void removeSeriesDeltaListener(SeriesDeltaListener<? super P> l) {
        listeners.remove(l);
    }

    protected void fire(SeriesDelta<P> delta) {
        for (SeriesDeltaListener<? super P> l : listeners) {
            l.onDelta(delta);
        }
    }

}
