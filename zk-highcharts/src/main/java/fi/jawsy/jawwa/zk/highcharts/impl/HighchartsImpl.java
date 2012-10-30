package fi.jawsy.jawwa.zk.highcharts.impl;

import lombok.val;

import org.zkoss.json.JSONValue;

import fi.jawsy.jawwa.zk.highcharts.Highcharts;

public final class HighchartsImpl {

    private HighchartsImpl() {
    }

    public static Highcharts.Options createOptions() {
        return new OptionsImpl();
    }

    public static void main(String[] args) {
        val options = createOptions();

        System.out.println(JSONValue.toJSONString(options));
    }

}
