package fi.jawsy.jawwa.zk.highcharts;

import java.util.List;

import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;

public interface HighchartsSeries<P> extends List<P> {

    Value<Animation> animation();

    void addSeriesDeltaListener(SeriesDeltaListener<? super P> l);

    void removeSeriesDeltaListener(SeriesDeltaListener<? super P> l);

}
