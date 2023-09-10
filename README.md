# Challenge_Hotel_Alura
Hotel Alura 
# Instrucciones de Instalación de la Base de Datos para Hotel Alura

Bienvenidos a la aplicación de Escritorio de un Hotel Alura.

En este documento voy a explicar la instalación, ejecución y cierre de la aplicación.
Primeramente, tendremos que tener el MYSQL de tu preferencia instalado y corriendo en tu ordenador.
Como requisito necesitas tener creada una base de datos con la siguiente instrucción:

-- Creando base de datos.
CREATE DATABASE hotel_alura; 

Copia y pega el codigo anterior para crear la base de datos.

La aplicación ya cuenta con un codigo para crear las tablas para su correspondiente uso, en este caso solo es necesario crear la base de datos.

## Importación del Proyecto Java

A continuación, le indicamos los pasos a seguir para importar el proyecto Java desde nuestro repositorio:

1. **Nota Importante:** Si en su sistema local utiliza un usuario y contraseña específicos para acceder a la base de datos, es esencial que reemplace esta información en el archivo de configuración ("Connection Factory"). Solo necesita modificar los campos de usuario y contraseña.

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/36496573-ddd4-43c4-9088-42c539fca8ef)


2. Una vez realizados los cambios en el "Connection Factory", estará listo para ejecutar el programa. Para ello, diríjase al paquete `com.latam.alura.main` y ejecute el método principal. La aplicación estará completamente configurada y lista para su uso.

   ![image](https://github.com/CLP7432/Challenge_Hotel_Alura/assets/121730557/b3fc7074-341f-415b-8380-a06d757696d6)



   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/1fe46040-3556-434c-9053-c37741d1bc2a)


4. No olvide iniciar sesión utilizando las siguientes credenciales:
   - **Usuario:** "admin"
   - **Contraseña:** "admin"
  
![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/4294e383-0248-420b-9b1d-d36b300d3105)


Una vez entrado al menu principal ya podemos empezar a hacer operaciones en la aplicacion. por ejemplo creemos una nueva reservación.
![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/d269c096-9484-432c-b813-2ffb40b92d2b)


(nota) hay condiciones a la hora de crear una reservacion. por ejemplo:
   -   La fecha de incio no puede ser un dia anterior a la fecha que usted se encuentra actualmente, lo mismo aplica para la fecha de salida
   -   La fecha de salida no puede ser igual a la fecha de inicio
   -   Ningun campo puede estar vacio

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/a91486e1-07cb-4680-a09c-cbc0251ac715)


Cuando creamos una reservación en hotel alura tambien es necesario crear un huesped que ocupe esa reservacion, asi que una vez llenado los datos de la reservación y darle al boton azul, nos 
enviara a un formaulario para llenar los datos del huesped

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/05b737f8-a79e-4726-ac69-4348e7699610)

si al concluir de llenar los datos y de enviar el formulario todo fue exitosos, nos saltara una ventada como la siguiente, que indicara que los datos se guardaron en la base de datos con exito

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/95e87156-a5bb-4c2d-a3aa-989c4fc84629)

ahora vamos a revisar si realemente se guardo la reservacion en la tabla
![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/4e63d4c5-4c5f-4125-9bc6-1509952840cc)

y como podemos ver, se guarda correctamente la reservacion con su respectivo huesped

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/66b49138-5591-4249-b5d1-1e585f954eb1)

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/4686ab7e-4754-47d5-b1f4-20627e485ee1)


¿No te llamas asi?, no hay problema, el sistema cuenta con una funcionalidad de poder modificar los siguientes atributos de cada registro:
   Reservacion:
      -  Fecha de Salida
      -   Forma de pago
   Huespedes:  
      -   Nombre
      -   Apellido
      -   Fecha de nacimiento
      -   Nacionalidad
      -   Telefono

   Basta con dar doble cick en el campo a modificar y cambiar el valor, despues precionar la tecla "Enter" y por ultimo dar click en el botn de "Modificar"

   Cambiemos el nombre de "Marco" por "Marco Uriel"

  ![image](https://github.com/CLP7432/Challenge_Hotel_Alura/assets/121730557/ddf0bbb6-bc76-4eff-b8a1-333053e18097) 


   Despues de precionar "Enter" y dar Click en "Modificar"
   
   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/63a63085-5563-43bf-a056-860d07830027)

   Y el campo ha sido modificado con exito. 
   
   ![image](https://github.com/CLP7432/Challenge_Hotel_Alura/assets/121730557/169f73fa-3854-4d2d-b99f-54098858cf3a) 

   
  
   Ya vimos como Crear y Actualizar informacion, ahora vamos a eliminar una reservación. Este programa cuenta con una eliminacion en cascada,
   es decir, al momento de eliminar una reservacion, en automatico tambien se elimina el huesped que estaba vinculado con esa reservacion. 

   -   Primero seleccione la reservacion a eliminar dando click.
   -   
      ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/8f2b0d47-8565-42db-a0bb-f675e6f7c0bc)
   
   -   El campo debe cambiar de color a un tono azulado, ahora precione el boton "Eliminar"
     
   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/ce64b169-1242-4a4b-b746-3796929e2689)

   Y ambos campos se eliminaran de la base de datos y de la tabla de la aplicación


     
      

   


Estas instrucciones asegurarán una instalación exitosa y una configuración óptima para la aplicación Hotel Alura. ¡Disfrute de todas sus funciones y características!
