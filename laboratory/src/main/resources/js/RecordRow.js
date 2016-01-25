jQuery.namespace("JIRA.Laboratory.Record.RecordRow");

 JIRA.Laboratory.Record.RecordRow = JIRA.RestfulTable.Row.extend({
     initialize: function() {
         JIRA.RestfulTable.Row.prototype.initialize.apply(this, arguments);
     },
     render: function() {

         var data = this.model.toJSON(),
                 id = this.model.get("id"),
                 $el = this.$el;

         $el.attr("id", "record-" + id + "-row").attr("data-id", id);

         $el.html(JIRA.Templates.Record.RecordRow({
             record: data
         }));

         return this;
     }

 });