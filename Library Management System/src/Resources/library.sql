-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `categoryId` varchar(15) DEFAULT NULL,
  `bookId` varchar(15) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publishYear` date DEFAULT NULL,
  `available` varchar(3) NOT NULL,
  PRIMARY KEY (`bookId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`),
  CONSTRAINT `books_chk_1` CHECK ((`available` in (_cp850'yes',_cp850'no')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('BC-06','B-01','Clean Code','Robert C. Martin','2012-06-19','yes'),('BC-06','B-02','The Pragmatic Programmer','Andrew Hunt ','1999-10-21','yes'),('BC-06','B-03','Code Complete','Steve McConnell','1993-03-10','yes'),('BC-15','B-04','Effective Java','Joshua Bloch','2008-10-23','yes'),('BC-15','B-05','Head First Java','Bert Bates','2003-10-23','yes'),('BC-16','B-06','Effective Python:','Brett Slatkin','2015-02-09','yes'),('BC-16','B-07','Python for Data Analysis','Wes McKinney','2012-05-22','yes'),('BC-17','B-08','Learning SQL','Alan Beaulieu','2009-04-21','yes');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryId` varchar(10) NOT NULL,
  `categoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('BC-01','Fantasy'),('BC-02','Science fiction'),('BC-03','Horror'),('BC-04','History'),('BC-05','Biography'),('BC-06','Programmig'),('BC-07','Historical Fiction'),('BC-08','Thriller'),('BC-09','Adventure fiction'),('BC-10','Romance'),('BC-11','Memoir'),('BC-12','Classics'),('BC-13','Poetry'),('BC-14','Political thriller'),('BC-15','Java'),('BC-16','Python'),('BC-17','Databases');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuebooks`
--

DROP TABLE IF EXISTS `issuebooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issuebooks` (
  `issueId` varchar(15) NOT NULL,
  `bookId` varchar(15) DEFAULT NULL,
  `bookDetails` varchar(100) DEFAULT NULL,
  `memberId` varchar(15) DEFAULT NULL,
  `memberDetails` varchar(100) DEFAULT NULL,
  `issueDate` date DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  PRIMARY KEY (`issueId`),
  KEY `bookId` (`bookId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `issuebooks_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  CONSTRAINT `issuebooks_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `members` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuebooks`
--

LOCK TABLES `issuebooks` WRITE;
/*!40000 ALTER TABLE `issuebooks` DISABLE KEYS */;
INSERT INTO `issuebooks` VALUES ('IB-01','B-08','BC-17 | Learning SQL','M-02','Dilshan','2024-07-11','2024-07-18'),('IB-02','B-06','BC-16 | Effective Python:','M-01','Tharindu','2024-07-10','2024-07-17'),('IB-03','B-01','BC-06 | Clean Code','M-04','Sithumi','2024-07-04','2024-07-11'),('IB-04','B-02','BC-06 | The Pragmatic Programmer','M-05','tharindu','2024-07-15','2024-07-22'),('IB-05','B-01','BC-06 | Clean Code','M-01','Tharindu','2024-07-01','2024-07-08'),('IB-06','B-02','BC-06 | The Pragmatic Programmer','M-02','Dilshan','2024-07-24','2024-07-31'),('IB-07','B-03','BC-06 | Code Complete','M-04','Sithumi','2024-07-01','2024-07-08'),('IB-08','B-04','BC-15 | Effective Java','M-04','Sithumi','2024-07-01','2024-07-08');
/*!40000 ALTER TABLE `issuebooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `memberId` varchar(10) NOT NULL,
  `memberName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `mobileNumber` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('M-01','Tharindu','Galle','0762791821','tharindu@gmail.com',22,'2002-04-19','Male'),('M-02','Dilshan','Boosa','0772152946','dilshan@gmail.com',21,'2003-11-14','Male'),('M-03','Madushanka','Dewata','0722576359','madus@gmail.com',22,'2002-07-10','Male'),('M-04','Sithumi','Galle','0787635467','sithumi@gmail.com',19,'2005-12-18','Female'),('M-05','tharindu','deniyaya','0772345627','td@gmail.com',12,'2024-07-11','Male'),('M-06','Banumath','Jafna','0785421256','b@gamil.com',23,'2024-07-16','Female');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnbooks`
--

DROP TABLE IF EXISTS `returnbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returnbooks` (
  `returnId` varchar(15) NOT NULL,
  `issueId` varchar(15) DEFAULT NULL,
  `bookId` varchar(15) DEFAULT NULL,
  `bookDetails` varchar(100) DEFAULT NULL,
  `memberId` varchar(15) DEFAULT NULL,
  `memberDetails` varchar(100) DEFAULT NULL,
  `issueDate` date DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `fine` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`returnId`),
  KEY `issueId` (`issueId`),
  KEY `bookId` (`bookId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `returnbooks_ibfk_1` FOREIGN KEY (`issueId`) REFERENCES `issuebooks` (`issueId`),
  CONSTRAINT `returnbooks_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  CONSTRAINT `returnbooks_ibfk_3` FOREIGN KEY (`memberId`) REFERENCES `members` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnbooks`
--

LOCK TABLES `returnbooks` WRITE;
/*!40000 ALTER TABLE `returnbooks` DISABLE KEYS */;
INSERT INTO `returnbooks` VALUES ('1','IB-01','B-08','BC-17 | Learning SQL','M-02','Dilshan','2024-07-11','2024-07-18','2024-07-19','10.0 $'),('2','IB-02','B-06','BC-16 | Effective Python:','M-01','Tharindu','2024-07-10','2024-07-17','2024-07-19','20.0 $'),('3','IB-03','B-01','BC-06 | Clean Code','M-04','Sithumi','2024-07-04','2024-07-11','2024-07-14','30.0 $'),('4','IB-04','B-02','BC-06 | The Pragmatic Programmer','M-05','tharindu','2024-07-15','2024-07-22','2024-07-27','50.0 $'),('5','IB-05','B-01','BC-06 | Clean Code','M-01','Tharindu','2024-07-01','2024-07-08','2024-07-08','-'),('6','IB-06','B-02','BC-06 | The Pragmatic Programmer','M-02','Dilshan','2024-07-24','2024-07-31','2024-08-01','10.0 $'),('7','IB-07','B-03','BC-06 | Code Complete','M-04','Sithumi','2024-07-01','2024-07-08','2024-07-09','10.0 $'),('8','IB-08','B-04','BC-15 | Effective Java','M-04','Sithumi','2024-07-01','2024-07-08','2024-07-08','-');
/*!40000 ALTER TABLE `returnbooks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-31 14:27:02
