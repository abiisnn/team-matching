function cargaGrupos() {
    var idG=$('#idGrupo').val();
    var sizeG=$('#size').val();
    $.ajax ({
        url:"http://10.100.66.182:8000/getMatchings?"+idG+"-"+sizeG, //?id
        method:"GET",
        data: null,
        dataType: "json",
        success:function(data) {
        console.log(data);
        $('#idGroup').html('');

        for (var i = 0; i < data['groups'].length; i++) {
            // Creando tabla individual
            var c = i + 1;
            $('#idGroup').append('<p> <b> ----- GRUPO '+ c +' </b></p>');
            var names = '';
            for(var j = 0; j < Object.keys(data['groups'][i]).length; j++) {
                names += '<p>';
                names += data['groups'][i][j];
                names += '</p>';
            }
            $('#idGroup').append('<ul class="collection"><li class="collection-item avatar"><img src="images/start.png" alt="" class="circle">'+ names +'</li></ul>');
        }
    }

    }).fail( function( jqXHR, textStatus, errorThrown ) {
        if (jqXHR.status === 0) {
            alert('Not connect: Verify Network.');
        } else if (jqXHR.status == 404) {
            alert('Requested page not found [404]');
        } else if (jqXHR.status == 500) {
            alert('Internal Server Error [500].');
        } else if (textStatus === 'parsererror') {
            alert('Requested JSON parse failed.');
        } else if (textStatus === 'timeout') {
            alert('Time out error.');
        } else if (textStatus === 'abort') {
            alert('Ajax request aborted.');
        } else {
            alert('Uncaught Error: ' + jqXHR.responseText);
        }
    });
}