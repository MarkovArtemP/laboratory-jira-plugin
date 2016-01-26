function getRequestString (type, id, content){
    var url =   location.protocol+
                "//"+location.hostname+
                (location.port ? ":"+location.port: "")+
                contextPath+
                "/rest/laboratory/1.0/records/"
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
        alert('Giving up :( Cannot create an XMLHTTP instance');
        return false;
    }
    httpRequest.onreadystatechange = function() { alertContents(httpRequest); };
    httpRequest.open(type, url, true);
    httpRequest.send(content);
    document.write("lalalala");
    document.write(httpRequest.responseText);
    }
    function alertContents(httpRequest) {
    try {
        if (httpRequest.readyState == 4) {
            if (httpRequest.status == 200) {
                }
            } else {
                alert('There was a problem with the request.');
            }
        }
        else return;
    }
    catch( e ) {
        alert('Caught Exception: ' + e.description);
    }
}
    getRequestString ("GET", "", null);