var i=0;
var data;

function retrieveNoteDetail(url) {
    $("#noteDetail").load(url);
}

function retrieveAllNotes(username) {
    var url = "/api/users/" + username + "/notes";
    $.getJSON(url, function (datap) {
        data = datap;
        console.log(datap);
        writeNoteDetail(i);
        writeNoteListTitle();
    });
    console.log("notes"); //see that this is printed before data
}

function writeNoteDetail(i) {
    var noteDetail = document.getElementById("oneNoteDetail");
    noteDetail.innerHTML = "<h2>" + data[i].title + "</h2>" +
        "<div> Date Creation: " + data[i].dateCreation + "</div>" +
        "<div> Date Edit: " + data[i].dateEdit + "</div>" +
        "<div> Content: " + data[i].content + "</div>";
}
function previous() {
    if (i > 0) {
        i--;
        writeNoteDetail(i);
    }
}
function next() {
    if (i < data.length-1) {  //thymeleaf has problems with "<". It interpreates it is a malformed markup. Don't use it whithin *.js file
        i++;
        writeNoteDetail(i);
    }
}

function writeNoteListTitle() {
    var noteList = document.getElementById("titles")
    var content = "<ul>";
    for (var i=0; i < data.length; i++) {
        content = content + "<li><a onclick=\"writeNoteDetail(" + i + ")\">" + data[i].title + "</a></li>";
    }
    noteList.innerHTML = content + "</ul>";
}
