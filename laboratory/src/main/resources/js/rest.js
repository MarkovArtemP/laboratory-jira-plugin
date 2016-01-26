window.onload = function() {
    getRequestString ("GET", "", null);
    document.forms.namedItem('new-record-form').onsubmit = function() {getRequestString ("GET", "", null);};
}
function getRequestString (type, id, content){
    var url =   location.protocol+
                '//'+location.hostname+
                (location.port ? ':'+location.port: '')+
                contextPath+
                '/rest/laboratory/1.0/records/'
                +id;

    var httpRequest;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        httpRequest = new XMLHttpRequest();
        if (httpRequest.overrideMimeType) {
            httpRequest.overrideMimeType('text/xml');
            // See note below about this line
        }
    }
    else if (window.ActiveXObject) { // IE
        try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            try {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e) {}
        }
    }
    if (!httpRequest) {
        alert('Cannot create an XMLHTTP instance');
        return false;
    }
    httpRequest.onreadystatechange = function() { alertContents(httpRequest, type, url); };
    httpRequest.open(type, url, true);
    httpRequest.send(content);
}

function alertContents(httpRequest, type, url) {
    try {
        if (httpRequest.readyState == 4) {
            if (httpRequest.status == 200) {
                if (type==="GET"){
                    tableDrow(JSON.parse(httpRequest.responseText).records);
                }
                else {
                    getRequestString ("GET", "", null);
                }
            } else {
                alert('There was a problem with the request.');
                alert(type);
                alert(url);
                alert(httpRequest.status);
            }
        }
    }
    catch( e ) {}
}

function tableDrow(records){
    var table = document.getElementById("records-table");
    for (i=0;i<records.length;i++){
        var newRow=table.insertRow(i+1);

        var newCell = newRow.insertCell(0);
        newCell.innerHTML=records[i].text;

        var newCell = newRow.insertCell(1);
        newCell.innerHTML=records[i].date;

        var newCell = newRow.insertCell(2);
        newCell.innerHTML='<input type="button" value="Change" id="change-'+i+'"> <input type="button" value="Delete" id="delete-'+i+'">';
        document.getElementById('change-'+i).onclick=function(){alert('Changed!');};
        document.getElementById('delete-'+i).onclick=function(){getRequestString ('DELETE', i, null);};
    }
}