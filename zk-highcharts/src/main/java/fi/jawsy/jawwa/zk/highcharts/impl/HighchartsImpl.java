package fi.jawsy.jawwa.zk.highcharts.impl;

import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.PointSeries.Point;

public final class HighchartsImpl {

    private HighchartsImpl() {
    }

    public static Highcharts.Options createOptions() {
        return new OptionsImpl();
    }

    public static Point createPoint() {
        return new PointImpl();
    }

}
