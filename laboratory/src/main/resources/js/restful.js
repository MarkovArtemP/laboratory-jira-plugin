window.onload = function() {
    var DateInputEditView = AJS.RestfulTable.CustomEditView.extend({
            render: function (self) {
                console.log(self);
                var $select = $('<input type="date" name="date" placeholder="yyyy-MM-dd"/>');
                return $select;
            }
        });
    var DateInputCreateView = AJS.RestfulTable.CustomCreateView.extend({
                render: function (self) {
                    console.log(self);
                    var $select = $('<input type="date" name="date" placeholder="yyyy-MM-dd"/>');
                    return $select;
                }
            });
    new AJS.RestfulTable({
        autoFocus: true,
        el: jQuery("#laboratory-records-table"),
        allowReorder: true,
        resources: {
            all: AJS.contextPath()+"/rest/laboratory/1.0/records/",
            self: AJS.contextPath()+"/rest/laboratory/1.0/records/"
        },
        noEntriesMsg: "There are currently no records.",
        columns: [
            {
                id: "text",
                header: "text",
                emptyText: "No text for this record"
            },
            {
                id: "date",
                header: "date",
                editView: DateInputEditView,
                createView: DateInputCreateView,
                emptyText: "No date for this record"
            }
        ]
    });
};