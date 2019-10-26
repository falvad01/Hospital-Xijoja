-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-10-2019 a las 18:36:23
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `xijoja base de datos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacen`
--

CREATE TABLE `almacen` (
  `idProducto` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `idPaciente` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `idTrabajador` int(11) NOT NULL,
  `Nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Apellido1` varchar(75) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Apellido2` varchar(75) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `NIFNIE` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `FechaAlta` date DEFAULT NULL,
  `CuentaBancaria` varchar(30) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Puesto` varchar(15) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `contrasenia` varchar(50) CHARACTER SET utf32 COLLATE utf32_spanish_ci NOT NULL,
  `usuario` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,
  `Email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`idTrabajador`, `Nombre`, `Apellido1`, `Apellido2`, `NIFNIE`, `FechaAlta`, `CuentaBancaria`, `Puesto`, `contrasenia`, `usuario`, `Email`) VALUES
(0, 'Javier', 'Alvarez', 'De Celis', '21345675C', '2019-10-25', 'ewrftyr657uyi', 'Administrador', 'root', 'root', ''),
(1, 'Xian', 'Garcia', 'Nogueira', '745741489E', '2019-10-25', '666546116465465646', 'Medico', '12345', 'xgarnog', 'iopaiasdop@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`idTrabajador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `idTrabajador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
