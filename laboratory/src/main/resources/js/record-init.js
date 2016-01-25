jQuery(function() {

     var $table = AJS.$("#laboratory-records-table");

     function getResourceURL() {
         return contextPath + "rest/laboratory/1.0/records";
     }

     function getRecord(callback) {
         JIRA.SmartAjax.makeRequest({
             url: getResourceURL(),
             complete: function(xhr, status, response) {
                 if (response.successful) {
                     callback(response.data.records);
                 } else {
                     $table.trigger("serverError",
                             [JIRA.SmartAjax.buildSimpleErrorContent(response)]);
                 }
             }
         });
     }

     getRecord(function(records) {

         JIRA.Laboratory.RecordTable = new JIRA.RestfulTable({
             el: $table,
             resources: {
                     all: getResourceURL(),
                     self: getResourceURL()
                 },
             entries: records,
             noEntriesMsg: 'There are currently no records.',
             views: {
                 row: JIRA.Laboratory.Record.RecordRow
             }

         });

         jQuery(".jira-restfultable-init").remove();

         JIRA.userhover($table);
     });
 });
