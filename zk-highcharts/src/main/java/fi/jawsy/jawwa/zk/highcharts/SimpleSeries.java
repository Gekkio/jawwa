package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;
import java.util.List;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONAware;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class SimpleSeries extends ForwardingList<Double> implements HighchartsSeries<Double>, JSONAware, Serializable {

    private static final long serialVersionUID = 5402282418562024234L;

    private final List<Double> data = Lists.newArrayList();

    @Override
    protected List<Double> delegate() {
        return data;
    }

    @Override
    public String toJSONString() {
        return JSONArray.toJSONString(data);
    }

    @Override
    public String toString() {
        return Iterables.toString(data);
    }

}
