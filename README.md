# Challenge_Hotel_Alura
Hotel Alura 
# Instrucciones de Instalación de la Base de Datos para Hotel Alura


-- Crear la base de datos
CREATE DATABASE `hotelalura`;

-- Usar la base de datos
USE `hotelalura`;

-- Crear la tabla `usuario`
CREATE TABLE `usuario` (
  `nombre` VARCHAR(50) DEFAULT NULL,
  `contraseña` VARCHAR(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Insertar un usuario de ejemplo
INSERT INTO `usuario` (`nombre`, `contraseña`) VALUES
('admin', 'admin');

-- Crear la tabla `huesped`
CREATE TABLE `huesped` (
  `id` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `fecha_nacimiento` DATE DEFAULT NULL,
  `nacionalidad` VARCHAR(50) DEFAULT NULL,
  `telefono` VARCHAR(12) DEFAULT NULL,
  `id_reserva` INT(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;



-- Crear la tabla `reservas`
CREATE TABLE `reservas` (
  `id_reservas` INT(11) NOT NULL,
  `fecha_entrada` DATE DEFAULT NULL,
  `fecha_salida` DATE DEFAULT NULL,
  `valor` FLOAT DEFAULT NULL,
  `forma_pago` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Crear índices
-- Índices de la tabla `huesped`
ALTER TABLE `huesped`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_reserva` (`id_reserva`);

-- Índices de la tabla `reservas`
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reservas`);

-- Configurar AUTO_INCREMENT
-- AUTO_INCREMENT de la tabla `huesped`
ALTER TABLE `huesped` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

-- AUTO_INCREMENT de la tabla `reservas`
ALTER TABLE `reservas` MODIFY `id_reservas` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

-- Restricciones
-- Filtros para la tabla `huesped`
ALTER TABLE `huesped`
  ADD CONSTRAINT `fk_id_reserva` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reservas`) ON DELETE CASCADE,
  ADD CONSTRAINT `huesped_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reservas`),
  ADD CONSTRAINT `id_reserva` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reservas`) ON DELETE CASCADE;

-- Fin del script


Antes de ejecutar la aplicación Hotel Alura, es fundamental crear la base de datos correspondiente. Para facilitar este proceso, hemos proporcionado la siguiente consulta SQL que puede ejecutar en su administrador de bases de datos favorito, ya sea Workbench o mediante phpMyAdmin. Simplemente copie y pegue el siguiente código en su administrador y proceda a ejecutarlo:

Recuerda activar mysql y apache en caso de usar Xampp
![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/ca0e8380-fdf8-4419-8b0e-382442b102d3)


Despues recuerda pegar en SQL la query proporcionada anteriormente

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/751d3ca0-9097-43b0-a753-f83e1ff9e80a)



La base de datos se entrega preconfigurada con un usuario de acceso predeterminado: "usuario" con la contraseña "admin".

## Importación del Proyecto Java

A continuación, le indicamos los pasos a seguir para importar el proyecto Java desde nuestro repositorio:

1. **Nota Importante:** Si en su sistema local utiliza un usuario y contraseña específicos para acceder a la base de datos, es esencial que reemplace esta información en el archivo de configuración ("Connection Factory"). Solo necesita modificar los campos de usuario y contraseña.

![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/36496573-ddd4-43c4-9088-42c539fca8ef)


2. Una vez realizados los cambios en el "Connection Factory", estará listo para ejecutar el programa. Para ello, diríjase al paquete `com.latam.alura.main` y ejecute el método principal. La aplicación estará completamente configurada y lista para su uso.

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

   Cambiemos el nombre de "Marco" por "Adrian"

   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/e585daa8-d91b-44c9-8bd7-2ee735bc0e4c)

   Despues de precionar "Enter" y dar Click en "Modificar"
   
   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/63a63085-5563-43bf-a056-860d07830027)

   Y el campo ha sido modificado con exito.
   
   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/55d61cce-24c7-4beb-a08e-f120f3922306)

   Veamos si realmente se ah cambiado en la base de datos

   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/60e3081d-7841-4b68-8b1d-8758d0976965)

   


   Ya vimos como Crear y Actualizar informacion, ahora vamos a eliminar una reservación. Este programa cuenta con una eliminacion en cascada,
   es decir, al momento de eliminar una reservacion, en automatico tambien se elimina el huesped que estaba vinculado con esa reservacion. 

   -   Primero seleccione la reservacion a eliminar dando click.
   -   
      ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/8f2b0d47-8565-42db-a0bb-f675e6f7c0bc)
   
   -   El campo debe cambiar de color a un tono azulado, ahora precione el boton "Eliminar"
     
   ![image](https://github.com/CLP7432/Challenge_Adrian_hotelalura/assets/121730557/ce64b169-1242-4a4b-b746-3796929e2689)

   Y ambos campos se eliminaran de la base de datos y de la tabla de la aplicación


     
      

   


Estas instrucciones asegurarán una instalación exitosa y una configuración óptima para la aplicación Hotel Alura. ¡Disfrute de todas sus funciones y características!
