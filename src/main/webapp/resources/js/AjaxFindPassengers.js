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
        url: "get-tickets",
        contentType: 'application/json',
        method: "POST",
        data: JSON.stringify(sendData)

    }).done(function (result) {

        $("#imgLoad").hide();
        $("#userContainer").show().empty();

        var userTableTemplate = document.querySelector('#userTable').cloneNode(true);

        for (var i = 0; i < result.length; i++) {

            var tableRow = document.querySelector('#tableRow').cloneNode(true);
            var target = userTableTemplate.content.querySelector('#tableBody');

            var userDto = result[i];

            var email = userDto.email;
            var firstName = userDto.firstName;
            var lastName = userDto.lastName;
            var birthdate = userDto.birthDate;

            tableRow.content.querySelector('#number').textContent = (i + 1);
            tableRow.content.querySelector('#email').textContent = email;
            tableRow.content.querySelector('#firstName').textContent = firstName;
            tableRow.content.querySelector('#lastName').textContent = lastName;
            tableRow.content.querySelector('#birthdate').textContent = birthdate;

            var cloneTableRow = document.importNode(tableRow.content, true);
            target.appendChild(cloneTableRow);
        }

        var cloneUserTable = document.importNode(userTableTemplate.content, true);
        document.querySelector('#userContainer').appendChild(cloneUserTable);

    }).fail(function (error) {

        console.log('error: ' + error);

    });
}