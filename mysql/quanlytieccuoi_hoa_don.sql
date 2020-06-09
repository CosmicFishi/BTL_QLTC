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
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `MaHoaDon` int NOT NULL,
  `ThoiDiem` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayThue` date NOT NULL,
  `TenBuoiTiec` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaSC` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TongTien` int DEFAULT '0',
  PRIMARY KEY (`MaHoaDon`),
  UNIQUE KEY `MaHoaDon_UNIQUE` (`MaHoaDon`),
  KEY `fk_hoadon_sanhCuoi` (`MaSC`),
  CONSTRAINT `fk_hoadon_sanhCuoi` FOREIGN KEY (`MaSC`) REFERENCES `sanh_cuoi` (`MaSC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES (1,'sang','2020-05-18','ABC','S001',1000000),(2,'sang','2020-05-18','ABC','S001',1000000),(4,'sang','2020-06-18','ABC','S001',1000000),(5,'sang','2020-07-18','ABC','S001',2000000),(6,'sang','2020-10-18','ABC','S001',3000000),(7,'sang','2020-01-18','ABC','S001',1000000),(8,'sang','2020-03-18','ABC','S001',2000000),(9,'sang','2020-05-18','ABC','S001',3000000);
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
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
