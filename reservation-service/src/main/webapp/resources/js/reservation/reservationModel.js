// var $ = require("../../node_modules/jquery/dist/jquery");
//
// var ReservationMdoel = (function() {
//     var url = '/api/reservations';
//
//     function getReservation() {
//         return $.ajax(url);
//     }
//
//     function updateReservationType(data) {
//         return $.ajax({
//             url: url + '/' + data.id,
//             type: "PUT",
//
//             dataType: "json",
//             data: data
//         });
//     }
//
//     return {
//         getReservation : getReservation,
//         updateReservationType : updateReservationType
//     }
//
// })();
//
//
// module.exports = ReservationMdoel;