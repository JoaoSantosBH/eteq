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
) ENGINE=MyISAM AUTO_INCREMENT=198 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_subcategoria`
--

LOCK TABLES `itens_subcategoria` WRITE;
/*!40000 ALTER TABLE `itens_subcategoria` DISABLE KEYS */;
INSERT INTO `itens_subcategoria` VALUES (1,1,1,'ÓCULOS'),(2,1,1,'RELÓGIOS'),(3,1,1,'BIJOUTERIAS'),(4,1,1,'MEIA-CALÇAS'),(5,1,1,'CINTOS'),(6,1,1,'JÓIAS'),(7,1,1,'CARTEIRAS'),(8,1,1,'LENÇOS'),(9,1,1,'CHAPÉU'),(10,1,1,'CABELOS'),(11,1,2,'BLUSAS'),(12,1,2,'COLETES'),(13,1,2,'CALÇAS'),(14,1,2,'CAMISAS'),(15,1,2,'CAMISETAS'),(16,1,2,'CASAQUINHOS'),(17,1,2,'MACACÃO'),(18,1,2,'SAIAS'),(19,1,2,'SHORT'),(20,1,2,'VESTIDOS'),(21,1,2,'VESTIDOS DE FESTA'),(22,1,3,'PERFUME'),(23,1,3,'MAQUIAGEM'),(24,1,3,'COSMÉTICOS'),(25,1,3,'CABELO'),(26,1,3,'UNHA'),(27,1,4,'BOTAS'),(28,1,4,'SANDALIAS'),(29,1,4,'RASTEIRINHA'),(30,1,4,'SAPATILHA'),(31,1,4,'SAPATO'),(32,1,4,'TENIS'),(33,1,5,'CLUTCHES'),(34,1,5,'OMBRO'),(35,1,5,'MOCHILA'),(36,1,5,'DE MÃO'),(37,1,5,'NECESSARIE'),(38,1,6,'KANGAS'),(39,1,6,'BIQUINIS'),(40,1,6,'MAIÔS'),(41,1,7,'CALCINHAS'),(42,1,7,'SOUTIENS'),(43,1,7,'BODY'),(44,1,8,'VESTIDOS DE NOIVA'),(45,1,8,'VESTIDOS'),(46,1,9,'TENIS'),(47,1,9,'MOLETONS'),(48,1,9,'CAMISETAS'),(49,1,9,'JAQUETAS'),(50,1,9,'KIMONOS'),(51,1,9,'BICICLETAS'),(52,1,10,'CAIXAS'),(53,1,10,'ENFEITES'),(54,1,10,'ESPELHOS'),(55,1,10,'PORTA TRECOS'),(56,1,10,'TRAVESSEIROS'),(57,1,10,'CACHECOL'),(58,1,10,'ARCOS'),(59,1,10,'AGENDAS'),(60,2,1,'ÓCULOS'),(61,2,1,'RELÓGIOS'),(62,2,1,'CARTEIRAS'),(63,2,1,'BONÉS'),(64,2,1,'BOLSAS'),(65,2,1,'CHAPEU'),(66,2,1,'LENÇOS'),(67,2,2,'PERFUMES'),(68,2,2,'CREMES'),(69,2,3,'BOTAS'),(70,2,3,'TÊNIS'),(71,2,3,'SANDÁLIAS'),(72,2,3,'SAPATOS'),(73,2,4,'BERMUDAS'),(74,2,4,'BLAZER'),(75,2,4,'CASACOS'),(76,2,4,'CALÇAS'),(77,2,4,'CAMISAS'),(78,2,4,'CAMISETAS'),(79,2,4,'COLETES'),(80,2,5,'SUNGA'),(81,2,5,'MERGULHO'),(82,2,5,'PRANCHA SURF'),(83,2,5,'JOGOS'),(84,2,6,'BOTAS'),(85,2,7,'BICICLETAS'),(86,2,7,'PRANCHAS'),(87,2,7,'FUTEBOL'),(88,2,7,'SKATE'),(89,2,7,'JOELHEIRAS'),(90,2,7,'LUVAS BOX'),(91,3,1,'ANDADORES'),(92,3,1,'MOISÉS'),(93,3,1,'CANGURU'),(94,3,1,'SLING'),(95,3,1,'CADEIRINHAS'),(96,3,2,'MENINAS'),(97,3,2,'MENINOS'),(98,3,2,'BEBÊS'),(99,3,3,'MENINAS'),(100,3,3,'MENINOS'),(101,3,3,'BEBÊS'),(102,3,4,'PEQUENINOS'),(103,3,4,'CRIANÇAS'),(104,3,4,'ADULTOS'),(105,3,5,'BERÇOS'),(106,3,6,'CARRINHOS'),(107,3,7,'OUTROS'),(108,4,1,'CADEIRAS'),(109,4,1,'MESAS'),(110,4,1,'POLTRONAS'),(111,4,1,'RACKS'),(112,4,1,'SOFÁS'),(113,4,1,'OUTROS'),(114,4,2,'ANTIQUÁRIO'),(115,4,3,'DECORAÇÃO'),(116,4,4,'ELETRODOMÉSTICOS'),(117,4,5,'ILUMINAÇÃO'),(118,4,6,'COZINHA'),(119,4,7,'OUTROS'),(120,5,1,'DESKTOPS'),(121,5,1,'NOTEBOOKS'),(122,5,1,'NETBOOKS'),(123,5,1,'PERIFÉRICOS'),(124,5,2,'IPADS'),(125,5,2,'OUTROS'),(126,5,3,'IPHONE'),(127,5,3,'GALAXY'),(128,5,3,'CELULARES'),(129,5,3,'OUTROS'),(130,5,4,'DIGITAIS'),(131,5,4,'ANALOGICOS'),(132,5,4,'LENTES/ACESSÓRIOS'),(133,5,4,'FILMADORAS'),(134,5,4,'POLAROIDS'),(135,5,4,'LOMO'),(136,5,5,'IPODS'),(137,5,5,'MP3'),(138,5,5,'APARELHOS SOM'),(139,5,5,'FONES DE OUVIDO'),(140,5,5,'TOCA DISCOS'),(141,5,5,'VINIL'),(142,5,5,'CD'),(143,5,5,'OUTROS'),(144,5,6,'CONSOLES'),(145,5,6,'JOGOS'),(146,5,6,'ACESSÓRIOS'),(147,5,7,'DVD PLAYER'),(148,5,7,'HOME THEATERS'),(149,5,7,'TUBO DE IMAGEM'),(150,5,7,'LCDS'),(151,5,7,'PLASMA'),(152,5,7,'MONITORES'),(153,5,7,'PLAYERS'),(154,5,7,'PORTÁTEIS'),(155,5,8,'RADIOS'),(156,5,8,'KINDLER'),(157,5,8,'FILMADORAS'),(158,5,8,'DIVERSOS'),(159,5,8,'INTERNET'),(160,6,1,'TABULEIRO'),(161,6,1,'COMPUTADOR'),(162,6,1,'DIDATICOS'),(163,6,1,'CARTAS'),(164,6,2,'ROUPINHAS'),(165,6,2,'CAMAS'),(166,6,2,'ACESSÓRIOS'),(167,6,3,'GUITARRAS'),(168,6,3,'VIOLÕES'),(169,6,3,'BATERIA'),(170,6,3,'MICROFONES'),(171,6,3,'PERCUSSÃO'),(172,6,3,'ELETRÔNICOS'),(173,6,3,'ACUSTICOS'),(174,6,3,'TECLADOS'),(175,6,3,'EQUIPAMENTOS'),(176,6,3,'ACESSÓRIOS'),(177,6,3,'PEDALEIRAS'),(178,6,3,'ESTOJO'),(179,6,3,'SOPRO'),(180,6,4,'INFANTIL'),(181,6,4,'ADULTO'),(182,6,4,'JOVEM'),(183,6,5,'CDS'),(184,6,5,'VINIL'),(185,6,5,'K7'),(186,6,6,'INFANTIL'),(187,6,6,'SERIES'),(188,6,6,'CLÁSSICOS'),(189,6,6,'OUTROS'),(190,6,7,'OMBRO'),(191,6,7,'RODA'),(192,6,7,'ALÇA'),(193,6,8,'CAPINHAS'),(194,6,8,'COPOS E CANECAS'),(195,6,8,'PENDRIVES'),(196,6,8,'ADORNOS'),(197,6,8,'ETC');
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
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,1,'ACESSÓRIOS'),(2,1,'ROUPAS'),(3,1,'BELEZA'),(4,1,'CALÇADOS'),(5,1,'BOLSAS'),(6,1,'PRAIA'),(7,1,'LINGERIE'),(8,1,'CASAMENTO'),(9,1,'ESPORTES'),(10,1,'OUTROS'),(11,2,'ACESSÓRIOS'),(12,2,'BELEZA'),(13,2,'CALÇADOS'),(14,2,'ROUPAS'),(15,2,'PRAIA'),(16,2,'ESPORTES'),(17,2,'OUTROS'),(18,3,'ACESSÓRIOS'),(19,3,'ROUPAS'),(20,3,'SAPATOS'),(21,3,'BRINQUEDOS'),(22,3,'BERÇOS'),(23,3,'CARRINHOS'),(24,3,'OUTROS'),(25,4,'MÓVEIS'),(26,4,'ANTIQUARIO'),(27,4,'DECORAÇÃO'),(28,4,'ELETRODOMÉSTICOS'),(29,4,'ILUMINAÇÃO'),(30,4,'COZINHA'),(31,4,'OUTROS'),(32,5,'COMPUTADORES'),(33,5,'TABLETS'),(34,5,'TELEFONIA'),(35,5,'FOTOGRAFIA'),(36,5,'MÚSICA'),(37,5,'VIDEOGAME'),(38,5,'TV`S E DISPLAYS'),(39,5,'OUTROS'),(40,6,'JOGOS'),(41,6,'ANIMAIS'),(42,6,'INSTRUMENTOS MUSICAIS'),(43,6,'LIVROS'),(44,6,'MUSICA'),(45,6,'FILMES'),(46,6,'MALAS'),(47,6,'DIVERSOS');
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

-- Dump completed on 2013-11-27 15:32:13
