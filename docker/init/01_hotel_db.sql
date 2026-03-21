/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.14-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: hotel_db
-- ------------------------------------------------------
-- Server version	10.11.14-MariaDB-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `dni` varchar(20) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `saldo_puntos` decimal(8,2) DEFAULT 0.00,
  `num_estancias` int(11) DEFAULT 0,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES
(1,'María','García López','maria@email.com','612345678','12345678A','1990-05-15',150.50,3,1),
(2,'Carlos','Martínez Ruiz','carlos@email.com','698765432','87654321B','1985-11-20',0.00,1,1),
(3,'Ana','Pérez Sánchez','ana@email.com','677112233','11223344C','1995-03-08',75.00,2,0);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitaciones`
--

DROP TABLE IF EXISTS `habitaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio_noche` decimal(8,2) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `fecha_ultima_limpieza` date DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitaciones`
--

LOCK TABLES `habitaciones` WRITE;
/*!40000 ALTER TABLE `habitaciones` DISABLE KEYS */;
INSERT INTO `habitaciones` VALUES
(1,101,'Simple','Habitación individual con vistas al jardín',65.00,1,'2024-01-10',1),
(2,202,'Doble','Habitación doble con cama king size',95.00,2,'2024-01-10',1),
(3,303,'Suite','Suite de lujo con jacuzzi y terraza',250.00,4,'2024-01-09',0);
/*!40000 ALTER TABLE `habitaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `precio_total` decimal(10,2) NOT NULL,
  `num_personas` int(11) NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `confirmada` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_habitacion` (`id_habitacion`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_habitacion`) REFERENCES `habitaciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES
(1,1,2,'2024-02-01','2024-02-05',380.00,2,'Solicita cuna para bebé',1),
(2,2,1,'2024-02-10','2024-02-12',130.00,1,NULL,0),
(3,3,3,'2024-03-15','2024-03-20',1250.00,3,'Aniversario de boda',1);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_extra`
--

DROP TABLE IF EXISTS `servicios_extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_extra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_reserva` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` decimal(8,2) NOT NULL,
  `cantidad` int(11) DEFAULT 1,
  `fecha_servicio` date NOT NULL,
  `incluido` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_reserva` (`id_reserva`),
  CONSTRAINT `servicios_extra_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_extra`
--

LOCK TABLES `servicios_extra` WRITE;
/*!40000 ALTER TABLE `servicios_extra` DISABLE KEYS */;
INSERT INTO `servicios_extra` VALUES
(1,1,'Desayuno','Desayuno buffet para 2 personas',15.00,4,'2024-02-01',1),
(2,1,'Parking','Plaza de garaje cubierta',10.00,4,'2024-02-01',0),
(3,3,'Spa','Acceso ilimitado al spa',50.00,2,'2024-03-15',0);
/*!40000 ALTER TABLE `servicios_extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-11 19:59:36
