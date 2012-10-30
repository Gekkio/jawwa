package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;

class CreditsImpl extends OptionBase implements Highcharts.Options.Credits {

    private Value<Boolean> enabled;

    @Override
    public Value<Boolean> enabled() {
        if (enabled == null)
            enabled = new Value<Boolean>();
        return enabled;
    }

    @Override
    public void writeJsonOutput(JSONObject json) {
        writeValue(json, "enabled", enabled);
    }

}
