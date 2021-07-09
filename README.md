# ISSLocation
Tareas realizadas:
-Creación de la google maps key para poder acceder a la api de Google Maps.
-Creación del proyecto en Android Studio con Kotlin.
-Crear un archivo resource en values para agregar ahí la key que creamos anteriormente en la consola de google.
-Agregar en el build.gradle las dependencias que se van a utilizar (MapUtils, Dagger, ViewModel, LiveData, Lifecycles, Retrofit, Parse de JSON,
    Implementacion loginInterceptor, RxJava).
-Agregar en el AndroidManifest dentro de 'application' un meta-data para mandar a llamar la api de Google maps.
-Creación de un xml que contendra nuestro mapa (agregar al layout un id y agregar tambien un atributo name el cual va a llamar a la ruta en
    donde se encuentra el mapa original la cual es esta "com.google.android.gms.maps.SupportMapFragment").
-Crear un nuevo activity llamada MapsActivity para poder mostrar el mapa y sea visible para el usuario, comenzamos creando una variable de
    tipo GoogleMap.
    -Crear un metodo para poder crear el mapa, empezamos creando una variable de tipo SupportMapFragment para pasarle en id antes colocado 
    en el layout.
    -Implementar a la clase el metodo OnMapReadyCallback
    -En el metodo sobreescrito onMapReady este metodo se manda llamar cuando el mapa sea cargado, entonces vamos a asignarla a nuestra 
    variable map (En este momento si corremos nuestra aplicación ya tendriamos cargado el mapa).
-Ahora comenzaremos a estructurar los paquetes del proyecto el cual se va a dividir en:
    -api: Interfaz de la comunicación, en donde se estructuran las peticiones en este caso solo es una y es un GET.
    -di: Donde ira todo lo relación con la inyección de dependencias (Dagger).
        -modules: Todos los modulos que vamos a estar ocupando para la inyección de dependencias.
    -entity: En este caso solo tenemos una entidad la cual es ISS, a nivel raiz tendremos la entidad.
        -repository: 
            ISSRepository: Clase donde se consumen los servicios relacionados con esta entidad en este caso solo sera un metodo con el cual
            vamos a recibir la latitud y longitud.
            IISSRepository: Interfaz donde se crea la funcion que se implementa en ISSRepository.
        -apiResponse: Clase donde esta el pojo de la estructura de la respuesta del servicio.
    -ui: Donde iran todas nuestra clases que son vistas.
        -activity
            -maps: (Para tener un mejor orden vamos a clasificar por carpetas las diferentes clases por ejemplo en este caso Maps).
                MapsActivity: Donde esta todo el codigo relacionado con el xml de maps.
                MapsViewModel: Donde ira toda la logica en este caso donde vamos a hacer la consultar para el servicio.
-Vamos a comenzar a conectar todo para la inyección de dependencias en la cual vamos a utilizar Dagger, primero vamos a crear la clase 
    -AppModule la cual estara anotada con @Module que recibira por consstructor la instancia de aplicación y devolvera mediante un metodo 
        anotado con @Provides
    -ViewModelFactory para crear una instancia de un objeto ViewModel con parametros de constructor
    -NetworkModule en donde agrupamos el OkHttpClient, la url que se estaria utilizando para los servicios y Retrofit esto por medio de 
    metodos.
    -RepositoryModule modulo en donde tendremos la función que retorna retrofit al repositorio
    -ViewModelKey esta clase es para generar una llave de acuerdo a la clase 
    -ViewModelModule donde se agrega los @binds que proporciona interfaces, @IntoMap el cual se usa como comando para las claves del mapa
        donde la clave es proporcionada por @ViewModelKey a la cual le pasamos el ViewModel de nuestra clase en este caso MapsViewModel
    -ApplicationCommponent sera una interfaz en donde incluiremos todos los modulos al grafo con tan solo colocar la anotación
        @Component, tambien vamos a agregar las clases a las cuales vamos a inyectar en este caso seria MapsActivity
    -ISSLocationApplication se crea una instación del repositorio y tambien es donde se va a inicializar dagger (Para hacer esto primero
        debemos compilar el codigo así dagger podraa generar el DaggerApplicationComponent)
-Vamos a continuar creando las clases faltantes para consumir nuestros servicio por medio de retrofit
    -ISSLocationApi donde haremos la petición para el servicio 
    -ISSLocationResponse el pojo de la respuesta del servicio
    -ISS la clase de nuestra entidad con sus atributos
    -IISSRepository la interfaz que tiene la función que se implementara en el repository
    -ISSRepository es donde vamos a consumir el servicio que devuelve la latitud y longitud.
-Nos iremos a nuestra clase MapsViewModel y vamos a crear la variable _latlng = MutableLiveData y latlng LiveData para poder conectar 
    ambas seran de tipo Latlng porque es variable se la vamos a pasar al mapa
-Creamos una varibale de tipo CompositeDisposable que utilizaremos para conectar el servicio
-Creamos la funcion getCoordinates() para poder tener acceso a la latitud y longitud del ISS
-Creamos tambien la función updateLocation para que el metodo getCoordinates() se ejecute cada 10 segundos por medio de un Handler, dentro del metodo
    postDelayed mandamos llamar el metodo getCoodinates.
-Ahora vamos a pasarnos al MapsActivity en donde vamos a colocar la anotación @Inject y crear una variable de tipo ViewModelProvider
     y tambien vamos a instanciar el MapsViewModel para poder tener acceso a el.
-Crearemos una funcion llamada setUpObservables en la cual vamos a agregar todas las llamadas al viewModel, en este caso vamos a mandar 
    llamar la variable latlng para poder pasarle la posición al mapa y que se muestre el pin
-En el onCreate mandaremos llamar el metodo updateLocation() y tambien la función setUpObservables para poder mostrar el pin con las 
    coordenadas que arroja el servicio y se actualice cada 10 segundos.

Para probar la aplicación simplemente es necesario correrla y observar en el mapa como se actualiza el pin cada 10 segundos en donde tambien
se muestra un toast con las coordenadas del ISS.