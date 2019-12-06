

function cargaGrupos(){
  var form_data = new FormData();
  form_data.append("idGroup", "J5");
  $.ajax({
   url:"http://localhost:8000/getMatchings?123", //?id
   method:"GET",
   data: null,
   contentType: false,
   cache: false,
   processData: false,
   success:function(data){
       console.log(data);
       var datos= JSON.parse(data);
       $('#idGroup').html('');

       $('#idGroup').append('<ul class="list-group">');
       for (var i = 0; i < datos['groups'].length; i++) {

         var res='';
         res+='<li class="list-group-item">';
         res+=datos['groups'][i][0];
         for (var j = 1; j < Object.keys(datos['groups'][i]).length; j++) {
           res+=', ';
           res+=datos['groups'][i][j];
         }
         res+='</li>';
         $('#idGroup').append(res);
         alert(res);
       }
       // $('#idGroup').append(datos['groups'][0]);
       $('#idGroup').append('</ul>');


   }
  });
}
