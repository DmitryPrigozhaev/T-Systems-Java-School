$(document).ready(function () {
    $("#imgLoad").hide();
});

function findScheduleButtonClick() {

    $("#imgLoad").show();
    $("#foundTrains").hide();

    var sendData = {
        stationFromName: $("#stationFromName").val(),
        stationToName: $("#stationToName").val(),
        date: $("#dateStr").val()
    };

    $.ajax({
        url: "find_train",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        $("#imgLoad").hide();
        $("#foundTrains").show().empty();

        window.trainDtoList = result;

        for (var i = 0; i < result.length; i++) {

            var trainDto = result[i];

            var departureTime = new Date(Date.parse(trainDto.datetimeDeparture));
            // do not display departed trains
            if (departureTime < (new Date())) break;
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
            timeDeparture.textContent = ('0' + departureTime.getHours()).slice(-2) + ":" + ('0' + departureTime.getMinutes()).slice(-2);
            dateDeparture.textContent = departureTime.getFullYear() + "." + (departureTime.getMonth() + 1) + "." + ('0' + departureTime.getDate()).slice(-2);
            stationDeparture.textContent = trainDto.stationDeparture;
            travelTimeH.textContent = travelTime.getHours();
            travelTimeM.textContent = travelTime.getMinutes();
            timeArrival.textContent = ('0' + arrivalTime.getHours()).slice(-2) + ":" + ('0' + arrivalTime.getMinutes()).slice(-2);
            dateArrival.textContent = arrivalTime.getFullYear() + "." + (arrivalTime.getMonth() + 1) + "." + ('0' + arrivalTime.getDate()).slice(-2);
            stationArrival.textContent = trainDto.stationArrival;

            // slider carriages
            var button = template.content.querySelector('#economClassButton');
            button.setAttribute('data-target', ('#' + trainDto.number + "_" + trainDto.routeName.replace(/\s/g, '')));

            var target = template.content.querySelector('#target');
            target.setAttribute('id', trainDto.number + "_" + trainDto.routeName.replace(/\s/g, ''));

            // carriages
            for (var j = 0; j < trainDto.numberOfCarriages; j++) {

                var carriage = document.querySelector('#carriage').cloneNode(true);

                // carriage ID
                carriage.content.querySelector('#carriage_id')
                    .setAttribute('id', ('train' + trainDto.number + "_" + (j + 1)));

                // carriage number on button
                carriage.content.querySelector('#carrNumber').textContent = j + 1;

                // slider carriage
                var carriageButton = carriage.content.querySelector('#carriageButton');
                carriageButton.setAttribute('data-target', ('#train' + trainDto.number + "_" + (j + 1)));
                carriageButton.setAttribute('id', trainDto.number + "-" + (j + 1));

                // selected place form
                var selectedPlace = carriage.content.querySelector('#selectedPlace');
                selectedPlace.setAttribute('id', 'selectedPlace_' + trainDto.number + "_" + (j + 1));

                // hidden form for send data to controller
                var hiddenTrainNumber = carriage.content.querySelector('#hiddenTrainNumber');
                hiddenTrainNumber.setAttribute('value', trainDto.number);
                var hiddenCarriageNumber = carriage.content.querySelector('#hiddenCarriageNumber');
                hiddenCarriageNumber.setAttribute('value', j + 1);
                var hiddenSeatNumber = carriage.content.querySelector('#hiddenSeatNumber');
                hiddenSeatNumber.setAttribute('id', 'hiddenSeatNumber_' + trainDto.number + '_' + (j + 1));
                var hiddenRouteName = carriage.content.querySelector('#hiddenRouteName');
                hiddenRouteName.setAttribute('value', trainDto.routeName);
                var hiddenStationFromName = carriage.content.querySelector('#hiddenStationFromName');
                hiddenStationFromName.setAttribute('value', trainDto.stationDeparture);
                var hiddenStationToName = carriage.content.querySelector('#hiddenStationToName');
                hiddenStationToName.setAttribute('value', trainDto.stationArrival);
                var hiddenDatetimeDeparture = carriage.content.querySelector('#hiddenDatetimeDeparture');
                hiddenDatetimeDeparture.setAttribute('value', departureTime.getFullYear() + "." + (departureTime.getMonth() + 1) + "." + ('0' + departureTime.getDate()).slice(-2) +
                    ' ' + ('0' + departureTime.getHours()).slice(-2) + ":" + ('0' + departureTime.getMinutes()).slice(-2));
                var hiddenDatetimeArrival = carriage.content.querySelector('#hiddenDatetimeArrival');
                hiddenDatetimeArrival.setAttribute('value', arrivalTime.getFullYear() + "." + (arrivalTime.getMonth() + 1) + "." + ('0' + arrivalTime.getDate()).slice(-2) +
                    ' ' + ('0' + arrivalTime.getHours()).slice(-2) + ":" + ('0' + arrivalTime.getMinutes()).slice(-2));
                var count = 1;
                carriage.content.querySelectorAll('.place').forEach(
                    function (value) {
                        if (count === 41) count = 1;
                        value.setAttribute('onclick', 'addSeatNumberToPanel(' + hiddenSeatNumber.id + ', ' + selectedPlace.id + ', ' + count + ')');
                        count++;
                    }
                );

                // carriage button "buy ticket"
                var buyTicket = carriage.content.querySelector('#buyTicket');
                buyTicket.setAttribute('id', 'buyTicket_' + trainDto.number + "-" + (j + 1));
                buyTicket.setAttribute('onclick', 'buyTicket(' + i + ', ' + (j + 1) + ', ' + selectedPlace.id + ');');

                var cloneCarriage = document.importNode(carriage.content, true);

                target.appendChild(cloneCarriage);
            }

            var cloneTrain = document.importNode(template.content, true);
            document.querySelector('#foundTrains').appendChild(cloneTrain);
        }

    }).fail(function (error) {

        console.log("error:" + error);

    });

}

// add seat number to panel and to hidden form
function addSeatNumberToPanel(hiddenSeatNumber, selectedPlace, number) {
    selectedPlace.style.fontSize = 'large';
    selectedPlace.style.removeProperty('color');
    selectedPlace.textContent = number;
    hiddenSeatNumber.setAttribute('value', number);
}

// find tickets by pressing button "Carriage [id]"
function findTicketsByCarriage(trainAndCarriageNumber) {

    var data = trainAndCarriageNumber.toString().split('-');

    var sendData = {
        stationFromName: $("#stationFromName").val(),
        stationToName: $("#stationToName").val(),
        trainNumber: data[0],
        carriageNumber: data[1]
    };

    $.ajax({
        url: "find-ticket",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        var block = document.querySelector('#train' + data[0] + "_" + data[1]);

        var purchasedPlace = [];
        for (var i = 0; i < result.length; i++) {
            var ticketDto = result[i];
            purchasedPlace.push(ticketDto.seatNumber);
        }

        for (var i = 0; i < purchasedPlace.length; i++) {
            var place = block.querySelector('#place_' + purchasedPlace[i]);
            place.setAttribute('fill', 'ff0000');
            place.removeAttribute('onclick');
        }

    }).fail(function (error) {

        console.log("error:" + error);

    });
}

function buyTicket(trainDtoId, carriageNumber, seatNumber) {

    if (seatNumber.textContent === '' || seatNumber.textContent === 'NO PLACE SELECTED') {
        event.preventDefault();
        seatNumber.style.color = 'red';
        seatNumber.style.fontSize = 'large';
        seatNumber.textContent = 'NO PLACE SELECTED';
    }

}