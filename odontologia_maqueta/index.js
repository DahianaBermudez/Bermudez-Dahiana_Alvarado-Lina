fetch('http://localhost:8081/turnos/listar').then(response => response.json()).then(json => {console.log(json)
    
var str = '<div>'

json.forEach(function(v,k) {
  str += `<div class="card">
  <h2>Turno ${k+1}</h2>
  <p class="date"><i class="fas fa-calendar-alt"></i>${v.fechaHora}</p>
  <p class="dentist"><strong>Odontólogo:</strong> Dr. ${v.odontologo}</p>
  <p class="patient"></p><strong>Paciente:</strong> ${v.paciente}</p>
</div>`
}); 

str += '</div>';
document.getElementById("slideContainer").innerHTML = str;
})

fetch('http://localhost:8081/odontologos/listar').then(response => response.json()).then(json => {console.log(json)
    
    var str = '<div>'

    json.forEach(function(v,k) {
      str += `<div class="card">
         <p class="dentist">Odontólogo Dr. ${v.nombre + " " + v.apellido}</p>
    </div>`
    }); 
    
    str += '</div>';
    document.getElementById("slideContainerOdontologo").innerHTML = str;
    })

    function openModal() {
        document.getElementById('modal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('modal').style.display = 'none';
    }