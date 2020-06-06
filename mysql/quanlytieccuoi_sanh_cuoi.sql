-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlytieccuoi
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sanh_cuoi`
--

DROP TABLE IF EXISTS `sanh_cuoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanh_cuoi` (
  `MaSC` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenSC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ViTriSC` int NOT NULL,
  `SucChua` int NOT NULL,
  `GiaThue` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`MaSC`),
  UNIQUE KEY `MaSC_UNIQUE` (`MaSC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanh_cuoi`
--

LOCK TABLES `sanh_cuoi` WRITE;
/*!40000 ALTER TABLE `sanh_cuoi` DISABLE KEYS */;
INSERT INTO `sanh_cuoi` VALUES ('S001','Thien Duong',1,500,50000000),('S002','Mộng lý',1,500,50000000),('S003','Thien Duong',1,500,50000000),('S004','Thien Duong',1,500,50000000),('S005','Sảnh đường hoa',1,500,45000000),('S006','Sảnh thiên đường',1,400,50000000),('S007','Sảnh muôn màu',1,550,50000000),('S008','Sảnh hạnh phúc',2,600,45000000),('S009','Sảnh cầu vồng',2,700,40000000),('S010','Sảnh chia li',2,400,45000000);
/*!40000 ALTER TABLE `sanh_cuoi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-06 10:30:19
