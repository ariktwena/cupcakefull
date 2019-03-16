CREATE DATABASE  IF NOT EXISTS `cupcake` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cupcake`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: cupcake
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `bottom`
--

DROP TABLE IF EXISTS `bottom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bottom` (
  `bottom_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`bottom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bottom`
--

LOCK TABLES `bottom` WRITE;
/*!40000 ALTER TABLE `bottom` DISABLE KEYS */;
INSERT INTO `bottom` VALUES (1,'Chocolate',5),(2,'Vanilla',5),(3,'Nutmeg',5),(4,'Pistacio',6),(5,'Almond',7);
/*!40000 ALTER TABLE `bottom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `customer_view`
--

DROP TABLE IF EXISTS `customer_view`;
/*!50001 DROP VIEW IF EXISTS `customer_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `customer_view` AS SELECT 
 1 AS `customer_id`,
 1 AS `email`,
 1 AS `password`,
 1 AS `usertype`,
 1 AS `balance`,
 1 AS `numberoforders`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `usertype` varchar(45) NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'jobe@cph.dk','123','admin',720),(2,'jon@dr.dk','123','customer',323),(3,'robin@somewhere.com','123','customer',45);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_lines`
--

DROP TABLE IF EXISTS `order_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_lines` (
  `order_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `top_id` int(11) NOT NULL,
  `bottom_id` int(11) NOT NULL,
  `top_price` int(11) NOT NULL,
  `bottom_price` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`order_line_id`),
  KEY `pk_orders_top_idx` (`top_id`),
  KEY `pk_orders_bottom_idx` (`bottom_id`),
  KEY `pk_orders_orderlines_idx` (`order_id`),
  CONSTRAINT `pk_orders_bottom` FOREIGN KEY (`bottom_id`) REFERENCES `bottom` (`bottom_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_orders_orderlines` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ordre_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_orders_top` FOREIGN KEY (`top_id`) REFERENCES `top` (`top_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_lines`
--

LOCK TABLES `order_lines` WRITE;
/*!40000 ALTER TABLE `order_lines` DISABLE KEYS */;
INSERT INTO `order_lines` VALUES (1,1,1,1,5,5,3),(2,1,2,4,5,6,2),(3,2,1,2,5,5,1),(4,2,5,2,6,5,2),(5,3,9,5,9,7,2),(6,4,1,3,5,5,4),(7,4,2,3,5,5,3),(8,5,1,1,5,5,1),(9,7,4,1,6,5,5),(10,8,7,2,8,5,1),(11,8,2,5,5,7,3),(12,8,2,3,5,5,6),(13,9,2,3,5,5,7),(14,9,2,2,5,5,8),(15,10,3,2,5,5,3),(16,10,6,1,7,5,4),(17,11,7,2,8,5,1),(18,11,5,5,6,7,3),(19,12,2,4,5,6,7),(20,12,1,3,5,5,7),(21,12,2,3,5,5,3);
/*!40000 ALTER TABLE `order_lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `order_lines_view`
--

DROP TABLE IF EXISTS `order_lines_view`;
/*!50001 DROP VIEW IF EXISTS `order_lines_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `order_lines_view` AS SELECT 
 1 AS `customer_id`,
 1 AS `email`,
 1 AS `order_line_id`,
 1 AS `order_id`,
 1 AS `top_id`,
 1 AS `topname`,
 1 AS `top_price`,
 1 AS `bottom_id`,
 1 AS `bottom_name`,
 1 AS `bottom_price`,
 1 AS `amount`,
 1 AS `totalprice`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `ordre_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `ordre_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ordre_id`),
  KEY `pk_customers_orders_idx` (`customer_id`),
  CONSTRAINT `pk_customers_orders` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2019-03-05 22:21:47'),(2,1,'2019-03-12 00:00:00'),(3,1,'2019-03-12 00:00:00'),(4,2,'2019-03-12 00:00:00'),(5,2,'2019-03-13 00:00:00'),(6,2,'2019-03-13 00:00:00'),(7,3,'2019-03-13 00:00:00'),(8,1,'2019-03-14 00:00:00'),(9,1,'2019-03-14 00:00:00'),(10,1,'2019-03-15 00:00:00'),(11,1,'2019-03-15 00:00:00'),(12,2,'2019-03-15 00:00:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `top`
--

DROP TABLE IF EXISTS `top`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `top` (
  `top_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`top_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `top`
--

LOCK TABLES `top` WRITE;
/*!40000 ALTER TABLE `top` DISABLE KEYS */;
INSERT INTO `top` VALUES (1,'Chocolate',5),(2,'Blueberry',5),(3,'Raspberry',5),(4,'Crispy',6),(5,'Strawberry',6),(6,'Rum/Raisin',7),(7,'Orange',8),(8,'Lemon',8),(9,'Blue Cheese',9);
/*!40000 ALTER TABLE `top` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `customer_view`
--

/*!50001 DROP VIEW IF EXISTS `customer_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jobe`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_view` AS select `c`.`customer_id` AS `customer_id`,`c`.`email` AS `email`,`c`.`password` AS `password`,`c`.`usertype` AS `usertype`,`c`.`balance` AS `balance`,(select count(`o`.`ordre_id`) from `orders` `o` where (`o`.`customer_id` = `c`.`customer_id`)) AS `numberoforders` from `customers` `c` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `order_lines_view`
--

/*!50001 DROP VIEW IF EXISTS `order_lines_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jobe`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `order_lines_view` AS select `c`.`customer_id` AS `customer_id`,`c`.`email` AS `email`,`ol`.`order_line_id` AS `order_line_id`,`ol`.`order_id` AS `order_id`,`ol`.`top_id` AS `top_id`,`top`.`name` AS `topname`,`ol`.`top_price` AS `top_price`,`ol`.`bottom_id` AS `bottom_id`,`b`.`name` AS `bottom_name`,`ol`.`bottom_price` AS `bottom_price`,`ol`.`amount` AS `amount`,((`ol`.`bottom_price` + `ol`.`top_price`) * `ol`.`amount`) AS `totalprice` from ((((`order_lines` `ol` join `top` on((`ol`.`top_id` = `top`.`top_id`))) join `bottom` `b` on((`ol`.`bottom_id` = `b`.`bottom_id`))) join `orders` `o` on((`ol`.`order_id` = `o`.`ordre_id`))) join `customers` `c` on((`o`.`customer_id` = `c`.`customer_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-16  1:18:34
