package fi.jawsy.jawwa.frp;

import java.util.concurrent.atomic.AtomicReference;

import lombok.RequiredArgsConstructor;
import fi.jawsy.jawwa.lang.Effect;

public interface Signal<T> {

    EventStream<T> nowAndChange();

    T now();

    EventStream<T> change();

    @RequiredArgsConstructor
    public static class Val<T> implements Signal<T> {

        private final T value;

        @Override
        public T now() {
            return value;
        }

        @Override
        public EventStream<T> change() {
            return EventStreams.empty();
        }

        public static <T> Signal.Val<T> create(T value) {
            return new Signal.Val<T>(value);
        }

        @Override
        public EventStream<T> nowAndChange() {
            return EventStreams.instant(value);
        }

    }

    public static class Var<T> implements Signal<T>, EventSink<T> {
        private final AtomicReference<T> value;
        private final EventSource<T> eventSource = new EventSource<T>();

        public Var(T initial) {
            value = new AtomicReference<T>(initial);
        }

        @Override
        public T now() {
            return value.get();
        }

        @Override
        public EventStream<T> change() {
            return eventSource;
        }

        @Override
        public CleanupHandle pipeFrom(EventStream<? extends T> es) {
            return es.foreach(new Effect<T>() {
                @Override
                public void apply(T input) {
                    update(input);
                }
            });
        }

        @Override
        public void fire(T value) {
            update(value);
        }

        public void update(T value) {
            this.value.set(value);
            eventSource.fire(value);
        }

        public static <T> Signal.Var<T> create(T initial) {
            return new Signal.Var<T>(initial);
        }

        @Override
        public EventStream<T> nowAndChange() {
            return EventStreams.instant(value).union(eventSource);
        }

    }

}
