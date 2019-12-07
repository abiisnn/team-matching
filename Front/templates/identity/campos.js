var skillsList=["Teamwork","Adaptability","Problemsolving", "Creativity", "Presentation skills", "Interpersonal skills" , "Algorithms skills","Wireless Comunications","Artificial inteligence","Natural language processing", "Greedy Algorithm", "Dynamic Problems", "Leadership", "FTT", "ASM Cards", "I.T Governance"];

function crearCampos() {
	var n = document.getElementById("skills").value;
	console.log("Creo " + n + " campos");
	$('#putFields').html('');
	for(var i = 0; i < parseInt(n); i++) {
		var res = '<div class="field">';
	    res += '<input type="text" value="'+skillsList[i]+'" name="idSkill[]' + '" id="skill_' + i + '" placeholder="Skill" />'
        res += '</div>';
        $('#putFields').append(res);
	}
}
$( document ).ready(function() {
 $("#skills").val(skillsList.length);
});


function cargaFormulario(){
  var idG=$('#idGrupo').val();
	var skills = [];
	$("input[name='idSkill[]']").each(function() {
			skills.push($(this).val());
	});
	var url= "http://10.100.66.182:8000/saveOptions?"+idG+"-";
	for(var i=0; i<skills.length-1; i++)
		url+= (skills[i].replace(/ /g, "_")+',');
	url+= (skills[skills.length-1].replace(/ /g, "_"));
	console.log(url);
  $.ajax({
   url:url, //?id
   method:"GET",
   data: null,
   dataType: "text",
   success:function(data){
       alert(data);
			 location.assign("C:/xampp/htdocs/Git/team-matching/Front/templates/identity/manager.html");
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
