zk.$package('jawwa.raphaeljs');

jawwa.raphaeljs.Base = zk.$extends(zk.Widget, {
  paper_: null,
  _rootNode: null,

  bind_: function(desktop, skipper, after) {
    this.$supers('bind_', arguments);

    zWatch.listen({
      onSize: this
    });

    this.paper_ = Raphael(this.$n());
    this._rootNode = jq(this.$n());
  },
  unbind_: function(desktop, skipper) {
    this.paper_.remove();
    this.paper_ = null;
    this._node = null;

    zWatch.unlisten({
      onSize: this
    });

    this.$supers('unbind_', arguments);
  },
  onSize: function() {
    if (this._rootNode.innerWidth() !== this.paper_.width ||
        this._rootNode.innerHeight() !== this.paper_.height) {
      this.paper_.setSize(this._rootNode.innerWidth(), this._rootNode.innerHeight());
    }
  },
  redraw: function(out) {
    out.push('<div', this.domAttrs_(), '>');
    out.push('</div>');
  }
});
