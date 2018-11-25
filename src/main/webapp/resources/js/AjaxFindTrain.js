$(document).ready(function () {
    $("#imgLoad").hide();  // hide pre-loader
});

function findScheduleButtonClick() {

    $("#imgLoad").show(); // show loading

    var sendData = {
        stationFromName: $("#stationFromName").val(),
        stationToName: $("#stationToName").val(),
        date: $("#dateStr").val()
    };

    $.ajax({
        url: "/find-schedule",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {



        // $("#imgLoad").hide(); // hide loading
        //
        // // alert(JSON.stringify(result));
        //
        // var container = $("#tableContainer").empty();
        //
        // var table = $("<table class='shopping-cart'></table>");
        // table.append(
        //     "<tr>" +
        //     "<td>Train number</td>" +
        //     "<td>Route</td>" +
        //     "<td>Number of carriages</td>" +
        //     "</tr>");
        //
        // for (var i = 0; i < result.length; i++) {
        //
        //     var trainDto = result[i];
        //
        //     var tr = $("<tr></tr>");
        //     tr.append("<td>" + trainDto.number + "</td>");
        //     tr.append("<td>" + trainDto.routeName + "</td>");
        //     tr.append("<td>" + trainDto.numberOfCarriages + "</td>");
        //     table.append(tr);
        // }
        //
        // container.append(table);

    }).fail(function (error) {

        alert("Error\n" + JSON.stringify(error));

    });

}