package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;

import lombok.val;
import lombok.experimental.Value;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONAware;

import fi.jawsy.jawwa.zk.highcharts.PairSeries.Pair;

public class PairSeries extends SeriesBase<Pair> {

    private static final long serialVersionUID = -5203374458170549183L;

    @Value
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

    public boolean add(double x, double y) {
        return add(new Pair(x, y));
    }

}
