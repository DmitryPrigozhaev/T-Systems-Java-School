$(document).ready(function () {
    $("#imgLoad").hide();  // hide pre-loader
});

function findStationSchedule() {

    $("#imgLoad").show();
    $("#scheduleContainer").hide();

    var sendData = {
        stationFromName: $("#stationFromName").val(),
        date: $("#date").val()
    };

    $.ajax({
        url: "station_schedule",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        $("#imgLoad").hide();
        $("#scheduleContainer").show().empty();

        var scheduleTableTemplate = document.querySelector('#schedule-head').cloneNode(true);

        for (var i = 0; i < result.length; i++) {

            var scheduleBody = document.querySelector('#schedule-body').cloneNode(true);
            var target = scheduleTableTemplate.content.querySelector('#target');

            var RoutePointDto = result[i];
            var dateArrival = RoutePointDto.dateArrival === "" ? "" : new Date(Date.parse(RoutePointDto.dateArrival));
            var dateDeparture = RoutePointDto.dateDeparture === "" ? "" : new Date(Date.parse(RoutePointDto.dateDeparture));
            var stayTime = (dateArrival !== "" && dateDeparture !== "") ? new Date(dateDeparture - dateArrival - 3 * 60 * 60 * 1000) : "";

            var trainNumber = RoutePointDto.trainNumber;
            var route = RoutePointDto.routeName;
            var arrival = dateArrival === "" ? "-" : dateArrival.getFullYear() + "." + (dateArrival.getMonth() + 1) + "." + ('0' + dateArrival.getDate()).slice(-2) + " " +
                ('0' + dateArrival.getHours()).slice(-2) + ":" + ('0' + dateArrival.getMinutes()).slice(-2);
            var departure = dateDeparture === "" ? "-" : dateDeparture.getFullYear() + "." + (dateDeparture.getMonth() + 1) + "." + ('0' + dateDeparture.getDate()).slice(-2) + " " +
                ('0' + dateDeparture.getHours()).slice(-2) + ":" + ('0' + dateDeparture.getMinutes()).slice(-2);
            var stay = stayTime === "" ? "-" : (stayTime.getHours() === 0 ? "" : stayTime.getHours() + " h ") + stayTime.getMinutes() + " min";

            scheduleBody.content.querySelector('#trainNumber').textContent = trainNumber;
            scheduleBody.content.querySelector('#route').textContent = route;
            scheduleBody.content.querySelector('#arrival').textContent = arrival;
            scheduleBody.content.querySelector('#departure').textContent = departure;
            scheduleBody.content.querySelector('#stay').textContent = stay;

            var cloneTableRow = document.importNode(scheduleBody.content, true);
            target.appendChild(cloneTableRow);
        }

        var cloneScheduleTable = document.importNode(scheduleTableTemplate.content, true);
        document.querySelector('#scheduleContainer').appendChild(cloneScheduleTable);

    }).fail(function (error) {

        console.log('error: ' + error);

    });
}