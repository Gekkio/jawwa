package fi.jawsy.jawwa.zk.highcharts;

public interface SeriesDeltaListener<P> {

    void onDelta(SeriesDelta<? extends P> delta);

}
