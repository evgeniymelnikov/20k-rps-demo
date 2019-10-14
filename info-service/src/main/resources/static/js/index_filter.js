let result = [];
$("div#filter_result").on("click", "div.result__item_container", (event) => {
    $.get(`/${event.target.getAttribute("id")}/gps-position`, (data) => {
        alert("Получены следующие данные: " + JSON.stringify(data));
    })
});
const form = $("#filter_form");
form.submit((event) => {
    const data = {};
    form.serializeArray().forEach(keyVal => data[keyVal.name] = keyVal.value);
    event.preventDefault();
    $.ajax({
        url: "/info",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        dataType: "json",
        success: (response) => {
            if (response) {
                result = response;
            }
            $("#filter_result").empty();
            result.forEach((elemInfo) => {
                $("#filter_result").append(
                    `<div class="result__item_container" id="${elemInfo.id}">
                        <div><span class="font-weight-bold" >VIN: </span><span>${elemInfo.vinCode}</span></div>
                        <div><span class="font-weight-bold">Номер автомобиля: </span><span>${elemInfo.number}</span></div>
                    </div>`);
            });
        }
    });
});