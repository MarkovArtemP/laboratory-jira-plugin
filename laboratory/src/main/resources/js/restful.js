window.onload = function() {
    new AJS.RestfulTable({
        autoFocus: true,
        el: jQuery("#laboratory-records-table"),
        allowReorder: true,
        resources: {
            all: AJS.contextPath()+"/rest/laboratory/1.0/records/",
            self: AJS.contextPath()+"/rest/laboratory/1.0/records/"
        },
        columns: [
            {
                id: "text",
                header: "text"
            },
            {
                id: "date",
                header: "date"
            }
        ]
    });
};