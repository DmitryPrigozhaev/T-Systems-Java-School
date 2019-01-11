$(document).ready(function () {
    $("#imgLoad").hide();  // hide pre-loader
});

function addRoutePointButtonClick() {

    $("#imgLoad").show(); // show loading

    var sendData = {
        routeName: $("#routeName").val(),
        stationName: $("#stationName").val(),
        dateArrival: $("#dateArrival").val(),
        dateDeparture: $("#dateDeparture").val()
    };

    $.ajax({
        url: "new-route",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)
    }).done(function (result) {

        $("#imgLoad").hide(); // hide loading

        // alert(JSON.stringify(result));

        var container = $("#tableContainer").empty();

        var table = $("<table class='shopping-cart'></table>")
        table.append(
            "<tr>" +
            "<td>id</td>" +
            "<td>route name</td>" +
            "<td>station name</td>" +
            "<td>date arrival</td>" +
            "<td>date departure</td>" +
            "</tr>");

        for (var i = 0; i < result.length; i++) {

            var routePointDto = result[i];

            var tr = $("<tr></tr>");
            tr.append("<td>" + routePointDto.id + "</td>");
            tr.append("<td>" + routePointDto.routeName + "</td>");
            tr.append("<td>" + routePointDto.stationName + "</td>");
            tr.append("<td>" + routePointDto.dateArrival + "</td>");
            tr.append("<td>" + routePointDto.dateDeparture + "</td>");
            table.append(tr);
        }

        container.append(table);

    }).fail(function (error) {

        console.log("Error\n" + JSON.stringify(error));

    });

}

function editRoutePointButtonClick() {

    var sendData = {
        routeName: $("#routeName").val(),
        stationName: $("#stationName").val(),
        dateArrival: $("#dateArrival").val(),
        dateDeparture: $("#dateDeparture").val()
    };

    $.ajax({
        url: "edit-" + $("#routeName").val() + "-route",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        console.log(JSON.stringify(result));

    }).fail(function (error) {

        console.log("Error\n" + JSON.stringify(error));

    });
}