CREATE DATABASE  IF NOT EXISTS `eutenho` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `eutenho`;
-- MySQL dump 10.13  Distrib 5.5.16, for osx10.5 (i386)
--
-- Host: 192.168.25.26    Database: eutenho
-- ------------------------------------------------------
-- Server version	5.0.95

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL auto_increment,
  `titulo` varchar(45) default NULL,
  PRIMARY KEY  (`id_categoria`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'mulheres'),(2,'homens'),(3,'criancas'),(4,'casa'),(5,'info_eletro'),(6,'outros');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cadastro`
--

DROP TABLE IF EXISTS `cadastro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cadastro` (
  `id_cadastro` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) default NULL,
  `id_patrimonio` int(11) default NULL,
  `data_cadastro` datetime default NULL,
  PRIMARY KEY  (`id_cadastro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro`
--

LOCK TABLES `cadastro` WRITE;
/*!40000 ALTER TABLE `cadastro` DISABLE KEYS */;
/*!40000 ALTER TABLE `cadastro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL auto_increment,
  `nome` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  `senha` varchar(45) default NULL,
  `reserva` tinyint(4) default NULL,
  `ultimo_acesso` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `foto` varchar(45) default NULL,
  `validado` int(11) default '0',
  PRIMARY KEY  (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'joao santos','joaomarcelo.ms@gmail.com','djojo11',1,'2013-09-25 20:12:50',NULL,0),(2,'asd','asd','asd',0,'2013-09-26 18:16:47',NULL,0),(3,'qwe','qwe','qwe',0,'2013-09-26 18:27:05',NULL,0),(4,'fgdfg','dfgdfg','dfgdfg',0,'2013-09-26 20:12:24',NULL,0),(5,'asd','aa','asas',0,'2013-09-26 18:46:02',NULL,0),(6,'s','s','s',0,'2013-11-14 18:48:29',NULL,1),(7,'as','as','as',0,'2013-11-12 16:24:12',NULL,0),(8,'dodo','dodo@dodo.com','dodo',0,'2013-11-14 16:28:16',NULL,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrimonio`
--

DROP TABLE IF EXISTS `patrimonio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrimonio` (
  `id_patrimonio` int(11) NOT NULL auto_increment,
  `titulo` varchar(45) default NULL,
  `foto` varchar(45) default NULL,
  `estado_conservacao` int(11) default NULL,
  `observacoes` text,
  `reservado` tinyint(4) default NULL,
  `categoria` int(11) default NULL,
  `subcategoria` int(11) default NULL,
  PRIMARY KEY  (`id_patrimonio`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrimonio`
--

LOCK TABLES `patrimonio` WRITE;
/*!40000 ALTER TABLE `patrimonio` DISABLE KEYS */;
/*!40000 ALTER TABLE `patrimonio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resposta`
--

DROP TABLE IF EXISTS `resposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resposta` (
  `id_resposta` int(11) NOT NULL auto_increment,
  `id_mensagem` varchar(45) default NULL,
  `texto` mediumtext,
  `data_resposta` datetime default NULL,
  PRIMARY KEY  (`id_resposta`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resposta`
--

LOCK TABLES `resposta` WRITE;
/*!40000 ALTER TABLE `resposta` DISABLE KEYS */;
/*!40000 ALTER TABLE `resposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `idhistorico` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) default NULL,
  PRIMARY KEY  (`idhistorico`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_subcategoria`
--

DROP TABLE IF EXISTS `itens_subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens_subcategoria` (
  `id_itens_subcategoria` int(11) NOT NULL auto_increment,
  `id_categoria` int(11) default NULL,
  `id_sub_categoria` int(11) default NULL,
  `nome_itens` varchar(45) default NULL,
  PRIMARY KEY  (`id_itens_subcategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_subcategoria`
--

LOCK TABLES `itens_subcategoria` WRITE;
/*!40000 ALTER TABLE `itens_subcategoria` DISABLE KEYS */;
INSERT INTO `itens_subcategoria` VALUES (1,1,1,'ÓCULOS'),(2,1,1,'RELÓGIOS'),(3,1,1,'BIJOUTERIAS'),(4,1,1,'MEIA-CALÇAS'),(5,1,1,'CINTOS'),(6,1,1,'JÓIAS'),(7,1,1,'CARTEIRAS'),(8,1,1,'LENÇOS'),(9,1,1,'CHAPÉU'),(10,1,1,'CABELOS'),(11,1,2,'BLUSAS'),(12,1,2,'COLETES'),(13,1,2,'CALÇAS'),(14,1,2,'CAMISAS'),(15,1,2,'CAMISETAS'),(16,1,2,'CASAQUINHOS'),(17,1,2,'MACACÃO'),(18,1,2,'SAIAS'),(19,1,2,'SHORT'),(20,1,2,'VESTIDOS'),(21,1,2,'VESTIDOS DE FESTA'),(22,1,3,'PERFUME'),(23,1,3,'MAQUIAGEM'),(24,1,3,'COSMÉTICOS'),(25,1,3,'CABELO'),(26,1,3,'UNHA');
/*!40000 ALTER TABLE `itens_subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensagem` (
  `id_mensagem` int(11) NOT NULL auto_increment,
  `titulo` varchar(45) default NULL,
  `texto` mediumtext,
  `data_mensagem` datetime default NULL,
  `id_usuario` int(11) default NULL,
  PRIMARY KEY  (`id_mensagem`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `id_telefone` int(11) NOT NULL auto_increment,
  `celular` int(11) default NULL,
  `ddd` int(11) default NULL,
  `id_usuario` int(11) default NULL,
  PRIMARY KEY  (`id_telefone`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (1,88470290,31,1);
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategoria` (
  `id_subcategoria` int(11) NOT NULL auto_increment,
  `id_categoria` int(11) default NULL,
  `titulo_subcategoria` varchar(45) default NULL,
  PRIMARY KEY  (`id_subcategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,1,'ACESSÓRIOS'),(2,1,'ROUPAS'),(3,1,'BELEZA'),(4,1,'CALÇADOS'),(5,1,'BOLSAS'),(6,1,'PRAIA'),(7,1,'LINGERIE'),(8,1,'CASAMENTO'),(9,1,'ESPORTES'),(10,1,'OUTROS'),(11,2,NULL);
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `logradouro` varchar(45) default NULL,
  `numero` int(11) default NULL,
  `complemento` varchar(10) default NULL,
  `cep` varchar(45) default NULL,
  `uf` varchar(45) default NULL,
  `cidade` varchar(45) default NULL,
  `id_logradouro` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_logradouro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES ('R Aristoteles Caldeira ',368,'B','30411225','MG','Belo Horizonte','1');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id_usuario` int(11) NOT NULL,
  `id_patrimonio` int(11) default NULL,
  PRIMARY KEY  (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-17 18:33:48
