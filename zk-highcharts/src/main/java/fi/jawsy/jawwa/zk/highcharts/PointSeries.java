package fi.jawsy.jawwa.zk.highcharts;

import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.PointSeries.Point;
import fi.jawsy.jawwa.zk.highcharts.impl.HighchartsImpl;

public class PointSeries extends SeriesBase<Point> {

    private static final long serialVersionUID = -1667960121067951516L;

    public static interface Point extends RawJsonSupport {
        Value<String> name();

        Value<Double> x();

        Value<Double> y();
    }

    public static Point createPoint() {
        return HighchartsImpl.createPoint();
    }

}
