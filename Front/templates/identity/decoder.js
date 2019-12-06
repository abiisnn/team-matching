

function cargaGrupos(){
  var idG=$('#idGrupo').val();
  $.ajax({
   url:"http://10.100.66.182:8000/getMatchings?"+idG, //?id
   method:"GET",
   data: null,
   dataType: "json",
   success:function(data){
       console.log(data);
       $('#idGroup').html('');

       $('#idGroup').append('<ul class="list-group">');
       for (var i = 0; i < data['groups'].length; i++) {

         var res='';
         res+='<li class="list-group-item">';
         res+=data['groups'][i][0];
         for (var j = 1; j < Object.keys(data['groups'][i]).length; j++) {
           res+=', ';
           res+=data['groups'][i][j];
         }
         res+='</li>';
         $('#idGroup').append(res);
       }
       // $('#idGroup').append(data['groups'][0]);
       $('#idGroup').append('</ul>');


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
