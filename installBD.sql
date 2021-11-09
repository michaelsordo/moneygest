-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 13-11-2020 a las 12:00:36
-- Versión del servidor: 10.3.23-MariaDB-0+deb10u1
-- Versión de PHP: 7.3.19-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `MONEYGEST_DB`
--
CREATE DATABASE IF NOT EXISTS MONEYGEST_DB DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE MONEYGEST_DB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla USUARIO
--

DROP TABLE IF EXISTS USUARIO;
CREATE TABLE IF NOT EXISTS USUARIO (
  login_usuario VARCHAR(15) COLLATE utf8_spanish_ci PRIMARY KEY,
  pass_usuario VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL,
  email_usuario VARCHAR(50) COLLATE utf8_spanish_ci NOT NULL UNIQUE
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla GASTO
--

DROP TABLE IF EXISTS GASTO;
CREATE TABLE IF NOT EXISTS GASTO (
  id_gasto INT PRIMARY KEY AUTO_INCREMENT,
  concepto_gasto VARCHAR(100) COLLATE utf8_spanish_ci NOT NULL,
  cantidad_gasto DOUBLE(7,2) NOT NULL,
  fecha_gasto DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  login_autor VARCHAR(15) COLLATE utf8_spanish_ci NOT NULL,
  FOREIGN KEY (login_autor) REFERENCES USUARIO(login_usuario) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla INGRESO
--

DROP TABLE IF EXISTS INGRESO;
CREATE TABLE IF NOT EXISTS INGRESO (
  id_ingreso INT PRIMARY KEY AUTO_INCREMENT,
  concepto_ingreso VARCHAR(100) COLLATE utf8_spanish_ci NOT NULL,
  cantidad_ingreso DOUBLE(7,2) NOT NULL,
  fecha_ingreso DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  login_autor VARCHAR(15) COLLATE utf8_spanish_ci NOT NULL,
  FOREIGN KEY (login_autor) REFERENCES USUARIO(login_usuario) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_spanish_ci;

-- --------------------------------------------------------

COMMIT;


