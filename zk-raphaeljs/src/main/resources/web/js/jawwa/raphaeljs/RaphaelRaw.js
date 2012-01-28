zk.$package('jawwa.raphaeljs');

jawwa.raphaeljs.RaphaelRaw = zk.$extends(jawwa.raphaeljs.RaphaelWidget, {
  jsRenderer: null,
  renderOnSize: false,

  setJsRenderer: function(value) {
    this.jsRenderer = value;

    if (this.desktop) {
      this.renderPaper();
    }
  },
  setRenderOnSize: function(value) {
    this.renderOnSize = value;
  },
  renderPaper: function() {
    if (this.jsRenderer != null && typeof this.jsRenderer === 'function') {
      this.jsRenderer(this.paper_);
    }
  },
  bind_: function(desktop, skipper, after) {
    this.$supers('bind_', arguments);

    this.renderPaper();
  },
  getZclass: function() {
    return this._zclass == null ? "jw-raphael-raw" : this._zclass;
  },
  unbind_: function(desktop, skipper) {
    this.$supers('unbind_', arguments);
  },
  onSize: function() {
    this.$supers('onSize', arguments);
    if (this.renderOnSize) {
      this.renderPaper();
    }
  },
  redraw: function(out) {
    out.push('<div', this.domAttrs_(), '>');
    out.push('</div>');
  }
});
