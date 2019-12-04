-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: chamadaalunos
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `disciplina_turma`
--

DROP TABLE IF EXISTS `disciplina_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `disciplina_turma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dia_da_semana` varchar(255) DEFAULT NULL,
  `unidade` varchar(255) DEFAULT NULL,
  `disciplina_id` bigint(20) DEFAULT NULL,
  `professor_id` bigint(20) DEFAULT NULL,
  `turma_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKporm9kw1iv6pyaai8yxfnjs64` (`disciplina_id`),
  KEY `FK6xjj8ipdhvb0r08w6m93ljs7r` (`professor_id`),
  KEY `FKhc1dcyi0qahbp5a417x0s04mx` (`turma_id`),
  CONSTRAINT `FK6xjj8ipdhvb0r08w6m93ljs7r` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`),
  CONSTRAINT `FKhc1dcyi0qahbp5a417x0s04mx` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`),
  CONSTRAINT `FKporm9kw1iv6pyaai8yxfnjs64` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina_turma`
--

LOCK TABLES `disciplina_turma` WRITE;
/*!40000 ALTER TABLE `disciplina_turma` DISABLE KEYS */;
INSERT INTO `disciplina_turma` VALUES (1,'Terça-feira, Quarta-feira','mooca',1,1,1),(2,'Quarta-feira','mooca',1,1,1),(3,'Sexta-feira','Butata',2,1,1),(4,'Segunda-feira','Paulista',3,1,1),(5,'Terça-feira','Paulista',4,1,1),(6,'Quinta-deira','Mooca',5,1,1),(7,'Segunda-feira','Butata',7,1,1);
/*!40000 ALTER TABLE `disciplina_turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-04  6:59:01
