# Voting_2b

Voting System

[![Build Status](https://travis-ci.org/Arquisoft/Voting_2b.svg?branch=master)](https://travis-ci.org/Arquisoft/Voting_2b)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/cfba81ae28014e3893bc519f83b6d272)](https://www.codacy.com/app/jelabra/Voting_2b)
[![codecov.io](https://codecov.io/github/Arquisoft/Voting_2b/coverage.svg?branch=master)](https://codecov.io/github/Arquisoft/Voting_2b?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57231ce9ba37ce00464dfe03/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57231ce9ba37ce00464dfe03)
[![Gitter](https://badges.gitter.im/Arquisoft/Voting_2b.svg)](https://gitter.im/Arquisoft/Voting_2b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)


# Instrucciones de funcionamento

## [Accede a nuestra wiki](https://github.com/Arquisoft/Voting_2b/wiki)

### IMPORTANTE: tener abierto xampp (o un servidor de mysql escuchando en el puerto 3306)

# Módulo censuses 

Desde la carpeta específica de censuses (Voting_2b/censuses_2b) ejecutamos **mvn spring-boot:run** se accederá a un menú en el cual disponemos de diferentes opciones:

### localizacionDeArchivos    [-p, -w , -t ]

* -help para ayuda
* -p  (.pdf)
* -w (.docx
* -t (.txt) 
* quit para salir
 
Las cartas personales estarán disponibles en el directorio letters.


# Módulo Voters

Desde la carpeta específica de censuses (Voting_2b/voters_2b) ejecutamos **mvn spring-boot:run**


* Consulta de la información del votante, pudiendo realizar la petición en formato: XML, HTML o JSON.

* Cambio de contraseña del votante realizando la petición en JSON.

* Aviso de errores en formato JSON, excepto el caso en el que la petición haya sido en formulada en HTML.

### Consulta de la información en JSON
Se deben realizar peticiones de votantes con esta estructura `{"email": email, "password": password}` a traves de la dirección:
`<URIServicioWeb>/user`
 *  Ej. Empleando una extensión web (como puede ser RESTClient en Firefox o Advanced Rest Client en Chrome).

  La respuesta será un mensaje JSON con la siguiente estructura:   `{\"email\":\"ej@mail.com\",\"name\":\"Alfonso\",\"nif\":\"455846\",\"poolingState\":1}`.

### Consulta de la información en HTML
Al enviar una petición GET a la página `<URIServicioWeb>` se recibirá un archivo html con un formulario.

Después de rellenar los campos de formulario y hacer clic en el botón enviar, el servicio web devolverá los datos solicitados, o si ha habido un error devolverá un html con la causa del mismo.

# Módulo VotingSystem

Desde la carpeta específica de censuses (Voting_2b/VotingSystem_2b) ejecutamos **mvn spring-boot:run** accedemos mediante nuestro navegador favorito a la url **localhost:9090/** , en ella accederemos a la aplicación web que controla el sistema de configuración de las elecciones y sistema de voto.

### Acceso al portal de administración

Para acceder al portal del administrador al inciar sesión nos logueamos con **admin**  **admin** y accederemos al portal de administración

En el portal podremos realizar las siguientes tareas:


* Podemos crear tres tipos de elecciones

  * Listas Abiertas
  * Referéndum
  * Listas Cerradas

* Cargar votos físicos
* Control de voto físico y marcado de voto presencial
* Configuración colegios electorales

# Módulo VoteCounting

Desde la carpeta específica de censuses (Voting_2b/VoteCounting_2b) ejecutamos **mvn spring-boot:run** accedemos mediente nuestro navegador favorito a la url **localhost:8080/** , en ella accederemos a la aplicación web que controlar el sistema de comprobación y recuento de voto.

### Acceso al portal de la junta electoral

Para acceder iniciar el recuento accedemos a la zona de login de la junta electoral, con el usuario y contraseña 
**junta** **junta** una vez nos encontremos en el portal de recuento podremos darle comienzo, y tras ello comprobar los resultados.

Una vez realicemos el recuento podremos ya ver los resultados desde cualquier lado

# Aplicaciones desplegadas online (carecen de integración pero permiten prueba)

### Vote Couting
http://vote-counting-2b.herokuapp.com

### Voting System
https://boiling-sea-17649.herokuapp.com/


# Authors
* Ignacio Fernández Álvarez
* Naucé López González
* Jorge Riopedre Vega
* Francisco De Borja Fernández González
* Sergio Moradas Quintana 
* Marcelo Torrejón Manso
* Jose Labra
