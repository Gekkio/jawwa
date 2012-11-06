package fi.jawsy.jawwa.zk.highcharts.impl;

import fi.jawsy.jawwa.zk.highcharts.Highcharts;

public final class HighchartsImpl {

    private HighchartsImpl() {
    }

    public static Highcharts.Options createOptions() {
        return new OptionsImpl();
    }

}
