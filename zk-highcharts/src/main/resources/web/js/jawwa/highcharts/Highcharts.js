zk.$package('jawwa.highcharts');
jawwa.highcharts.Highcharts = zk.$extends(zul.wgt.Div, {
  options: null,
  series: [],
  _chart: null,

  _dirtyOptions: false,
  _dirtySeries: false,

  setOptions: function(value) {
    this.options = value;
    if (this.desktop) {
      this._dirtyOptions = true;
    }
  },
  setSeries: function(value) {
    this.series = value;
    if (this.desktop) {
      this._dirtySeries = true;
    }
  },
  bind_: function(desktop, skipper, after) {
    this.$supers('bind_', arguments);
    zWatch.listen({
      onResponse: this
    });
    this._redrawChart();
  },
  onResponse: function() {
    if (this.desktop) {
      if (this._dirtyOptions) {
        this._redrawChart();
      } else if (this._dirtySeries) {
        throw "Series dedrawing is not supported yet!";
      }

      this._dirtyOptions = false;
      this._dirtySeries = false;
    }
  },
  _redrawChart: function() {
    this._destroy();

    var options = jq.extend(true, {
      chart: {
        renderTo: this.$n()
      }
    }, this.options);

    if (!options.series)
      options.series = [];

    for ( var i = 0, len = this.series.length; i < len; i++) {
      if (!options.series[i])
        options.series[i] = {};
      options.series[i].data = this.series[i];
    }

    this._chart = new Highcharts.Chart(options);
  },
  _destroy: function() {
    if (this._chart) {
      this._chart.destroy();
      this._chart = null;
    }
  },
  unbind_: function(desktop, skipper) {
    zWatch.unlisten({
      onResponse: this
    });
    this._destroy();
    this.$supers('unbind_', arguments);
  }
});
