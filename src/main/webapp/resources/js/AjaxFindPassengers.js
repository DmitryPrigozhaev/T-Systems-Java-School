$(document).ready(function () {
    $("#imgLoad").hide();  // hide pre-loader
});

function getPassenger() {

    $("#imgLoad").show();
    $("#userContainer").hide();

    var sendData = {
        number: $("#trainNumber").val()
    };

    $.ajax({
        url: "/get-tickets",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        $("#imgLoad").hide();
        $("#foundTrains").show().empty();

        for (var i = 0; i < result.length; i++) {

            var ticketDto = result[i];

            var email = ticketDto.userEmail;

        }

    }).fail(function (error) {

        console.log('error: ' + error);

    });
}