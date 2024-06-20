fetch('http://localhost:8081/turnos/listar').then(response => response.json()).then(json => {
  console.log(json)

  var str = '<div>'

  json.forEach(function (v, k) {
    str += `<div class="card">
  <h2>Turno ${k + 1}</h2>
  <p class="date"><i class="fas fa-calendar-alt"></i>${v.fechaHora}</p>
  <p class="dentist"><strong>Odontólogo:</strong> Dr. ${v.odontologo}</p>
  <p class="patient"></p><strong>Paciente:</strong> ${v.paciente}</p>
</div>`
  });

  str += '</div>';
  document.getElementById("slideContainer").innerHTML = str;
})

fetch('http://localhost:8081/odontologos/listar').then(response => response.json()).then(json => {
  console.log(json)

  var str = '<div>'

  json.forEach(function (v, k) {
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

document.getElementById('addDentistForm').addEventListener('submit', function (event) {
  event.preventDefault();
  const name = document.getElementById('name-dentist').value;
  const lastName = document.getElementById('lastName-dentist').value;
  const licenseNumber = document.getElementById('nummber-license').value;

  var dentistData = {
    nombre: name,
    apellido: lastName,
    numeroDeMatricula: licenseNumber
  };

  fetch('http://localhost:8081/odontologos/registrar', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dentistData)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    }).then(data => {
      console.log('Success:', data);
      closeModal();
      fetch('http://localhost:8081/odontologos/listar')
        .then(response => response.json())
        .then(json => {
          console.log(json);
          var str = '<div>';
          json.forEach(function (v, k) {
            str += `<div class="card">
                              <p class="dentist">Odontólogo Dr. ${v.nombre + " " + v.apellido}</p>
                          </div>`;
          });
          str += '</div>';
          document.getElementById("slideContainerOdontologo").innerHTML = str;
        });
    })
    .catch((error) => {
      console.error('Error:', error);
    });

})