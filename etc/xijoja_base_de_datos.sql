CREATE TABLE `almacen` (  `idProducto` int(50) NOT NULL,  `Cantidad` int(50) NOT NULL,  `Nombre` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,  `IteracionesSemana` int(50) NOT NULL);
CREATE TABLE `pacientes` (  `idPaciente` int(11) NOT NULL,  `Nombre` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,  `Apellido1` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,  `Apellido2` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,  `NIFNIE` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,  `FechaBaja` date NOT NULL,  `Habitacion` int(50) NOT NULL,  `Enfermedad` varchar(50) DEFAULT NULL,  `fk_idProducto` int(50) DEFAULT NULL,  `fk_idMedico` int(50) NOT NULL,  `UMedicamento` int(11) DEFAULT '0',  `fk_idEnfermero` int(50) NOT NULL) ;
CREATE TABLE `personal` (  `idTrabajador` int(11) NOT NULL,  `Nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `Apellido1` varchar(75) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `Apellido2` varchar(75) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `NIFNIE` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `FechaAlta` date DEFAULT NULL,  `CuentaBancaria` varchar(30) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `Puesto` varchar(15) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,  `contrasenia` varchar(50) CHARACTER SET utf32 COLLATE utf32_spanish_ci NOT NULL,  `usuario` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,  `Email` varchar(40) NOT NULL);
CREATE TABLE `eliminados` (`eliminado` int(200) NOT NULL DEFAULT '0',`anhadido` int(200)  NOT NULL DEFAULT '0',`medicinas` int(200)  NOT NULL DEFAULT '0') ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;
INSERT INTO `almacen` (`idProducto`, `Cantidad`, `Nombre`, `IteracionesSemana`) VALUES(0, 20, 'Paracetamol', 0),(1, 20, 'Aspirina', 0),(2, 20, 'Betadine', 0),(3, 20, 'Morfina', 0);
INSERT INTO `pacientes` (`idPaciente`, `Nombre`, `Apellido1`, `Apellido2`, `NIFNIE`, `FechaBaja`, `Habitacion`, `Enfermedad`, `fk_idProducto`, `fk_idMedico`, `UMedicamento`, `fk_idEnfermero`) VALUES(0, 'Nombre', 'Apellido1', 'Apellido2', '123456789A', '2020-01-01', 1, 'Gripe', 0, 1, 1, 2);
INSERT INTO `personal` (`idTrabajador`, `Nombre`, `Apellido1`, `Apellido2`, `NIFNIE`, `FechaAlta`, `CuentaBancaria`, `Puesto`, `contrasenia`, `usuario`, `Email`) VALUES(0, 'NombreRoot', 'ApellidoRoot1', 'ApellidoRoot2', '123456789A', '2019-10-25', '0000-0000-0000-0000', 'Administrador', 'root', 'root', 'hospitalxijoja@gmail.com'),(1, 'NombreMedico', 'ApellidoMedico1', 'ApellidoMedico2', '123456789B', '2019-11-13', '0000-0000-0000-0000', 'Medico', 'medico', 'medico', 'hospitalxijoja@gmail.com'),(2, 'NombreEnfermero', 'ApellidoEnfermero1', 'ApellidoEnfermero2', '123456789C', '2019-11-13', '0000-0000-0000-0000', 'Enfermero', 'enfermero', 'enfermero', 'hospitalxijoja@gmail.com'),(3, 'NombreSecretario', 'Apellido1Secretario', 'ApellidoSecretario2', '123456789D', '2019-11-13', '0000-0000-0000-0000', 'Secretario', 'secretario', 'secretario', 'hospitalxijoja@gmail.com'),(4, 'xian', 'garcia', 'nogueira', '35578885R', '2019-11-13', '0000-0000-0000-0000', 'Medico', '12345', 'xgarcn00', 'hospitalxijoja@gmail.com');
INSERT INTO `eliminados` (`eliminado`, `anhadido`, `medicinas`) VALUES(0, 0, 0);
ALTER TABLE `almacen`  ADD PRIMARY KEY (`idProducto`),  ADD UNIQUE KEY `idProducto` (`idProducto`);
ALTER TABLE `pacientes`  ADD PRIMARY KEY (`idPaciente`),  ADD UNIQUE KEY `idPaciente` (`idPaciente`),  ADD UNIQUE KEY `Habitacion` (`Habitacion`),  ADD KEY `fk_almacen` (`fk_idProducto`),  ADD KEY `fk_trabajador` (`fk_idMedico`),  ADD KEY `fk_trabajadorE` (`fk_idEnfermero`);
ALTER TABLE `personal`  ADD PRIMARY KEY (`idTrabajador`),  ADD UNIQUE KEY `idTrabajador` (`idTrabajador`);
ALTER TABLE `pacientes`  MODIFY `idPaciente` int(11) NOT NULL;
ALTER TABLE `personal`  MODIFY `idTrabajador` int(11) NOT NULL;
ALTER TABLE `pacientes`  ADD CONSTRAINT `fk_almacen` FOREIGN KEY (`fk_idProducto`) REFERENCES `almacen` (`idProducto`),  ADD CONSTRAINT `fk_trabajador` FOREIGN KEY (`fk_idMedico`) REFERENCES `personal` (`idTrabajador`),  ADD CONSTRAINT `fk_trabajadorE` FOREIGN KEY (`fk_idEnfermero`) REFERENCES `personal` (`idTrabajador`);
COMMIT;
