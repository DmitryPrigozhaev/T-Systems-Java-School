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

        $("#imgLoad").hide(); // hide loading

        $("#foundTrains").empty();

        for (var i = 0; i < result.length; i++) {

            var trainDto = result[i];

            var departureTime = new Date(Date.parse(trainDto.datetimeDeparture));
            var arrivalTime = new Date(Date.parse(trainDto.datetimeArrival));
            var travelTime = new Date(arrivalTime - departureTime);

            var template = document.querySelector('#train').cloneNode(true);

            var number = template.content.querySelector('#number');
            var route = template.content.querySelector('#route');
            var timeDeparture = template.content.querySelector('#timeDeparture');
            var dateDeparture = template.content.querySelector('#dateDeparture');
            var stationDeparture = template.content.querySelector('#stationDeparture');
            var travelTimeH = template.content.querySelector('#travelTimeH');
            var travelTimeM = template.content.querySelector('#travelTimeM');
            var timeArrival = template.content.querySelector('#timeArrival');
            var dateArrival = template.content.querySelector('#dateArrival');
            var stationArrival = template.content.querySelector('#stationArrival');

            number.textContent = trainDto.number;
            route.textContent = trainDto.routeName;
            timeDeparture.textContent = departureTime.getHours() + ":" + ('0' + departureTime.getMinutes()).slice(-2);
            dateDeparture.textContent = departureTime.getFullYear() + "." + (departureTime.getMonth() + 1) + "." + departureTime.getDate();
            stationDeparture.textContent = trainDto.stationDeparture;
            travelTimeH.textContent = travelTime.getHours();
            travelTimeM.textContent = travelTime.getMinutes();
            timeArrival.textContent = arrivalTime.getHours() + ":" + arrivalTime.getMinutes();
            dateArrival.textContent = arrivalTime.getFullYear() + "." + (arrivalTime.getMonth() + 1) + "." + arrivalTime.getDate();
            stationArrival.textContent = trainDto.stationArrival;

            // slider carriages
            var button = template.content.querySelector('#carButton');
            button.setAttribute('data-target', ('#' + trainDto.number));

            var target = template.content.querySelector('#target');
            target.setAttribute('id', trainDto.number);

            // carriages
            for (var j = 0; j < trainDto.numberOfCarriages; j++) {

                var carriage = document.querySelector('#carriage').cloneNode(true);
                carriage.content.querySelector('#carrNumber').textContent = j + 1;

                var cloneCarriage = document.importNode(carriage.content, true);

                target.appendChild(cloneCarriage);
            }


            var cloneTrain = document.importNode(template.content, true);
            document.querySelector('#foundTrains').appendChild(cloneTrain);

        }

    }).fail(function (error) {

        alert("Error\n" + JSON.stringify(error));

    });

}