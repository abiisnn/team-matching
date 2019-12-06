function crearCampos() {
	var n = document.getElementById("skills").value;
	console.log("Creo " + n + " campos");
	$('#putFields').html('');
	for(var i = 0; i < parseInt(n); i++) {
		var res = '<div class="field">';
	    res += '<input type="text" name="idSkill_' + i + '" id="skill_' + i + '" placeholder="Skill" />'
        res += '</div>';
        $('#putFields').append(res);
	}	
}



