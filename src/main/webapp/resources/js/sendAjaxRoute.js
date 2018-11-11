function sendRoutePointButtonClick() {

    var sendData = {
        routeName: $("#routeName").val(),
        station: $("#station").val(),
        dateArrival: $("#dateArrival").val(),
        dateDeparture: $("#dateDeparture").val()
    };

    $.ajax({
        url: "/new-route",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        alert(JSON.stringify(result));

    }).fail(function (error) {

        alert("Some error" + JSON.stringify(error));

    });

}