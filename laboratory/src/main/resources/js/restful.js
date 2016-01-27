window.onload = function() {
alert("JS started!")
alert(location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+AJS.contextPath()+'/rest/laboratory/1.0/records/')
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
alert("JS done!")
};