package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.val;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONAware;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fi.jawsy.jawwa.zk.highcharts.PairSeries.Pair;

public class PairSeries extends ForwardingList<Pair> implements HighchartsSeries<Pair>, Serializable {

    private static final long serialVersionUID = -5203374458170549183L;

    private final List<Pair> data = Lists.newArrayList();

    @Data
    public static final class Pair implements JSONAware, Serializable {
        private static final long serialVersionUID = -6681020912415581259L;

        public final double x;
        public final double y;

        @Override
        public String toJSONString() {
            return JSONArray.toJSONString(new double[] { x, y });
        }

        @Override
        public String toString() {
            val sb = new StringBuilder();
            sb.append('(');
            sb.append(x);
            sb.append(',');
            sb.append(y);
            sb.append(')');
            return sb.toString();
        }

    }

    @Override
    protected List<Pair> delegate() {
        return data;
    }

    public boolean add(double x, double y) {
        return add(new Pair(x, y));
    }

    @Override
    public String toString() {
        return Iterables.toString(data);
    }

}
