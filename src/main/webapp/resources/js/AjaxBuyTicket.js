$(document).ready(function () {
    $("#imgLoad").hide();
});

function buyTicket() {

    $("#imgLoad").show();
    $('#ticketBody').hide();

    var sendData = {
        userEmail: $("#email").val(),
        trainNumber: $("#trainNumber").val(),
        carriageNumber: $("#carriageNumber").val(),
        seatNumber: $("#seatNumber").val(),
        routeName: $("#routeName").val(),
        stationFromName: $("#stationFromName").val(),
        stationToName: $("#stationToName").val()
    };

    $.ajax({
        url: "get-tickets",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {


    });
}