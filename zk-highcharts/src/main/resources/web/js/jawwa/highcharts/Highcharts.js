zk.$package('jawwa.highcharts');
jawwa.highcharts.Highcharts = zk.$extends(zul.wgt.Div, {
  options: {},
  series: [],
  _chart: null,
  _delta: [],

  _dirtyOptions: false,
  _dirtySeries: false,
  _dirtyDelta: false,

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
  setDelta: function(value) {
    var seriesDelta = this._delta[value.series];
    if (typeof seriesDelta === 'undefined') {
      seriesDelta = [];
      this._delta[value.series] = seriesDelta;
    }
    seriesDelta.push(value.delta);
    if (this.desktop) {
      this._dirtyDelta = true;
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
        this._redrawChart();
      } else if (this._dirtyDelta) {
        this._applyDelta();
      }

      this._dirtyOptions = false;
      this._dirtySeries = false;
      this._dirtyDelta = false;
      this._delta = [];
    }
  },
  _redrawChart: function() {
    this._destroy();

    var options = jq.extend(true, {
      chart: {
        renderTo: this.$n()
      }
    }, this.options);

    if (typeof options.series === 'undefined')
      options.series = [];

    for ( var i = 0, len = this.series.length; i < len; i++) {
      if (typeof options.series[i] === 'undefined')
        options.series[i] = {};
      options.series[i].data = this.series[i];
    }

    this._chart = new Highcharts.Chart(options);
  },
  _applyDelta: function() {
    for ( var i = 0, len = this._delta.length; i < len; i++) {
      var seriesDelta = this._delta[i];

      if (typeof seriesDelta !== 'undefined') {
        var series = this._chart.series[i];

        for ( var j = 0, len2 = seriesDelta.length; j < len2; j++) {
          var delta = seriesDelta[j];

          if (delta.type === 'clear') {
            series.setData([], false, delta.animation);
          } else if (delta.type === 'append') {
            series.addPoint(delta.point, false, delta.animation);
          } else if (delta.type === 'add') {
            var index = delta.index;
            if (index === series.data.length) {
              series.addPoint(delta.point, false, delta.animation);
            } else {
              series.addPoint(series.data[series.data.length - 1], false, delta.animation);
              for ( var k = (series.data.length - 1); k >= index; k--) {
                series.data[k + 1].update(series.data[k], false, delta.animation);
              }
              series.data[index].update(delta.point, delta.animation);
            }
          } else if (delta.type === 'replace') {
            series.data[delta.index].update(delta.point, false, delta.animation);
          } else if (delta.type === 'remove') {
            series.data[delta.index].remove(false, delta.animation);
          }
        }
      }
    }
    this._chart.redraw();
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
