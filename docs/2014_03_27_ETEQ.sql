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
-- Table structure for table `config_servidor_email`
--

DROP TABLE IF EXISTS `config_servidor_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_servidor_email` (
  `id_config` int(11) NOT NULL auto_increment,
  `host` varchar(40) default NULL,
  `port` varchar(45) default NULL,
  `auth` varchar(45) default NULL,
  `starttls` varchar(45) default NULL,
  `user_email` varchar(45) default NULL,
  `from_name_email` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `charset` varchar(45) default NULL,
  `subject` varchar(45) default NULL,
  `corpo_mensagem` text,
  PRIMARY KEY  (`id_config`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_servidor_email`
--

LOCK TABLES `config_servidor_email` WRITE;
/*!40000 ALTER TABLE `config_servidor_email` DISABLE KEYS */;
INSERT INTO `config_servidor_email` VALUES (1,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Confirmação de e-mail para validar cadastro',NULL),(2,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Nova proposta de TROCA',NULL),(3,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Proposta de troca recusada',NULL),(4,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Um voluntario solicitou sua doacao',NULL),(5,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Inicio do processo de doacao',NULL),(6,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Um patrimônio seu expirou',NULL),(7,'smtp.gmail.com','587','true','true','eutenhoeuquero@gmail.com','EuTenhoEuQuero','3utenhoEQ2014!*pswd','UTF-8','Existe um novo patrimônio a ser aprovado',NULL);
/*!40000 ALTER TABLE `config_servidor_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum` (
  `id_forum` int(11) NOT NULL auto_increment,
  `assunto` varchar(45) default NULL,
  `mensagem` mediumtext,
  `data_mensagem` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `id_usuario_remetente` int(11) default NULL,
  `id_usuario_destinatario` int(11) default NULL,
  `lida` tinyint(4) default '0',
  `id_negociacao` int(11) default NULL,
  `ativo` tinyint(4) default '1',
  PRIMARY KEY  (`id_forum`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (21,'Joao Santos aceitou a TROCA','Ola Silvio , Joao Santos  aceitou TROCAR o(a) bola  dele(a),  PELO(a) SEU(ua)  baralho. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-19 19:55:41',38,6,1,13,0),(20,'ppp','pppp','2014-03-18 21:47:27',6,38,1,1,NULL),(10,'msg 10','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(11,'11','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(12,'12','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(13,'13','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(14,'14','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(15,'15','TResstando','2014-03-18 21:45:48',6,38,1,NULL,NULL),(16,'testeoi','TResstando','2014-03-18 21:44:11',6,38,0,NULL,NULL),(17,'Silvio deseja trocar com vc ','Gostaria de trocar minha barata pela bola','2014-03-18 21:44:11',6,38,0,5,NULL),(18,'OIOI','OLA Silvio , Joao Santos  aceitou TROCAR o(a) bola  dele(a),  PELO(a) SEU(ua)  baralhoInicie a negociacao enviando uma mensagem para Joao Santos','2014-03-18 21:44:11',38,6,0,1,NULL),(19,'Joao Santos aceitou a TROCA','Ola Silvio , Joao Santos  aceitou TROCAR o(a) bola  dele(a),  PELO(a) SEU(ua)  baralho. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-19 19:55:41',38,6,0,13,0),(22,'Joao Santos aceitou a TROCA','Ola Leda , Joao Santos  aceitou TROCAR o(a) Mesa jantar  dele(a),  PELO(a) SEU(ua)  Poltrona. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-20 17:36:48',38,6,1,15,0),(23,'Joao Santos aceitou a TROCA','Ola Leda , Joao Santos  aceitou TROCAR o(a) Mesa jantar  dele(a),  PELO(a) SEU(ua)  Poltrona. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-20 17:36:48',38,6,1,15,0),(24,'Joao Santos aceitou a TROCA','Ola Leda , Joao Santos  aceitou TROCAR o(a) Mesa jantar  dele(a),  PELO(a) SEU(ua)  Poltrona. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-21 16:28:55',38,6,1,18,1),(25,'Aceitar doacao','oi doador','2014-03-24 17:37:08',38,6,1,16,0),(26,'Joao Santos aceitou a TROCA','Ola Leda , Joao Santos  aceitou TROCAR o(a) Mesa jantar  dele(a),  PELO(a) SEU(ua)  Poltrona. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-25 19:47:34',38,6,1,35,0),(27,'Joao Santos aceitou a TROCA','Ola Leda , Joao Santos  aceitou TROCAR o(a) Mesa jantar  dele(a),  PELO(a) SEU(ua)  Poltrona. Inicie a negociacao enviando uma mensagem para Joao Santos','2014-03-25 19:50:55',38,6,1,36,0);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_expiracao`
--

DROP TABLE IF EXISTS `config_expiracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_expiracao` (
  `idconfig_expiracao` int(11) NOT NULL auto_increment,
  `tempo_expiracao` int(11) default NULL,
  `tempo_aviso` int(11) default NULL,
  PRIMARY KEY  (`idconfig_expiracao`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_expiracao`
--

LOCK TABLES `config_expiracao` WRITE;
/*!40000 ALTER TABLE `config_expiracao` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_expiracao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(45) default NULL,
  `nome` varchar(45) default NULL,
  `celular` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'joaomarcelo.ms@gmail.com','Joao Santos','88470290');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resposta`
--

DROP TABLE IF EXISTS `resposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resposta` (
  `id_resposta` int(11) NOT NULL auto_increment,
  `id_forum` varchar(45) default NULL,
  `texto_resposta` mediumtext,
  `data_resposta` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `id_usuario_remetente` int(11) default NULL,
  `id_usuario_destinatario` int(11) default NULL,
  PRIMARY KEY  (`id_resposta`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resposta`
--

LOCK TABLES `resposta` WRITE;
/*!40000 ALTER TABLE `resposta` DISABLE KEYS */;
INSERT INTO `resposta` VALUES (1,'19','Sim','2014-03-19 18:55:46',38,6),(2,'19','mas quanto vc volta?','2014-03-19 18:55:46',6,38),(3,'19','wqer','2014-03-19 18:55:46',NULL,NULL);
/*!40000 ALTER TABLE `resposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `negociacao`
--

DROP TABLE IF EXISTS `negociacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `negociacao` (
  `id_negociacao` int(11) NOT NULL auto_increment,
  `id_usuario_tem` int(11) default NULL,
  `id_usuario_quer` int(11) default NULL,
  `id_produto_tem` int(11) default NULL,
  `id_produto_quer` int(11) default NULL,
  `status` tinyint(4) default NULL,
  `data_inicio` varchar(19) default NULL,
  PRIMARY KEY  (`id_negociacao`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negociacao`
--

LOCK TABLES `negociacao` WRITE;
/*!40000 ALTER TABLE `negociacao` DISABLE KEYS */;
INSERT INTO `negociacao` VALUES (18,38,6,17,5,1,NULL),(17,38,6,17,5,1,NULL),(16,38,6,17,0,0,NULL),(15,38,6,17,5,0,NULL),(5,38,6,11,8,1,NULL),(6,38,6,11,8,1,NULL),(7,38,6,11,8,1,NULL),(8,38,6,11,8,1,NULL),(9,38,6,11,8,1,NULL),(10,38,6,11,8,1,NULL),(11,38,6,11,8,1,NULL),(12,38,6,11,8,1,NULL),(13,38,6,11,8,0,NULL),(19,38,6,17,5,1,NULL),(20,38,6,17,0,1,NULL),(21,8,12,2,0,1,NULL),(22,8,12,2,0,1,NULL),(23,8,12,2,0,1,NULL),(24,8,12,2,0,1,NULL),(25,8,12,2,0,1,'2014-03-25 16:09:13'),(26,38,6,17,0,0,'2014-03-25 16:15:07'),(27,38,6,17,0,0,'2014-03-25 16:22:23'),(28,38,6,17,0,0,'2014-03-25 16:24:39'),(29,38,6,17,0,0,'2014-03-25 16:28:34'),(30,38,6,17,0,1,'2014-03-25 16:33:05'),(31,38,6,17,0,1,'2014-03-25 16:35:30'),(32,38,6,17,0,1,'2014-03-25 16:36:29'),(33,38,6,17,0,0,'2014-03-25 16:38:55'),(34,38,6,17,5,1,NULL),(35,38,6,17,5,0,'2014-03-25 16:48:32'),(36,38,6,17,5,0,'2014-03-25 16:55:47');
/*!40000 ALTER TABLE `negociacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cadastro_patrimonio`
--

DROP TABLE IF EXISTS `cadastro_patrimonio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cadastro_patrimonio` (
  `id_cadastro` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) default NULL,
  `id_patrimonio` int(11) default NULL,
  `data_cadastro` datetime default NULL,
  PRIMARY KEY  (`id_cadastro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro_patrimonio`
--

LOCK TABLES `cadastro_patrimonio` WRITE;
/*!40000 ALTER TABLE `cadastro_patrimonio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cadastro_patrimonio` ENABLE KEYS */;
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
INSERT INTO `itens_subcategoria` VALUES (1,1,1,'ÓCULOS'),(2,1,1,'RELÓGIOS'),(3,1,1,'BIJOUTERIAS'),(4,1,1,'MEIA-CALÇAS'),(5,1,1,'CINTOS'),(6,1,1,'JÓIAS'),(7,1,1,'CARTEIRAS'),(8,1,1,'LENÇOS'),(9,1,1,'CHAPÉU'),(10,1,1,'CABELOS'),(11,1,2,'BLUSAS'),(12,1,2,'COLETES'),(13,1,2,'CALÇAS'),(14,1,2,'CAMISAS'),(15,1,2,'CAMISETAS'),(16,1,2,'CASAQUINHOS'),(17,1,2,'MACACÃO'),(18,1,2,'SAIAS'),(19,1,2,'SHORT'),(20,1,2,'VESTIDOS'),(21,1,2,'VESTIDOS DE FESTA'),(22,1,3,'PERFUME'),(23,1,3,'MAQUIAGEM'),(24,1,3,'COSMÉTICOS'),(25,1,3,'CABELO'),(26,1,3,'UNHA'),(27,1,4,'BOTAS'),(28,1,4,'SANDALIAS'),(29,1,4,'RASTEIRINHA'),(30,1,4,'SAPATILHA'),(31,1,4,'SAPATO'),(32,1,4,'TENIS'),(33,1,5,'CLUTCHES'),(34,1,5,'OMBRO'),(35,1,5,'MOCHILA'),(36,1,5,'DE MÃO'),(37,1,5,'NECESSARIE'),(38,1,6,'KANGAS'),(39,1,6,'BIQUINIS'),(40,1,6,'MAIÔS'),(41,1,7,'CALCINHAS'),(42,1,7,'SOUTIENS'),(43,1,7,'BODY'),(44,1,8,'VESTIDOS DE NOIVA'),(45,1,8,'VESTIDOS'),(46,1,9,'TENIS'),(47,1,9,'MOLETONS'),(48,1,9,'CAMISETAS'),(49,1,9,'JAQUETAS'),(50,1,9,'KIMONOS'),(51,1,9,'BICICLETAS'),(52,1,10,'CAIXAS'),(53,1,10,'ENFEITES'),(54,1,10,'ESPELHOS'),(55,1,10,'PORTA TRECOS'),(56,1,10,'TRAVESSEIROS'),(57,1,10,'CACHECOL'),(58,1,10,'ARCOS'),(59,1,10,'AGENDAS'),(60,2,1,'ÓCULOS'),(61,2,1,'RELÓGIOS'),(62,2,1,'CARTEIRAS'),(63,2,1,'BONÉS'),(64,2,1,'BOLSAS'),(65,2,1,'CHAPEU'),(66,2,1,'LENÇOS'),(67,2,2,'PERFUMES'),(68,2,2,'CREMES'),(69,2,3,'BOTAS'),(70,2,3,'TÊNIS'),(71,2,3,'SANDÁLIAS'),(72,2,3,'SAPATOS'),(73,2,4,'BERMUDAS'),(74,2,4,'BLAZER'),(75,2,4,'CASACOS'),(76,2,4,'CALÇAS'),(77,2,4,'CAMISAS'),(78,2,4,'CAMISETAS'),(79,2,4,'COLETES'),(80,2,5,'SUNGA'),(81,2,5,'MERGULHO'),(82,2,5,'PRANCHA SURF'),(83,2,5,'JOGOS'),(84,2,6,'BOTAS'),(85,2,7,'BICICLETAS'),(86,2,7,'PRANCHAS'),(87,2,7,'FUTEBOL'),(88,2,7,'SKATE'),(89,2,7,'JOELHEIRAS'),(90,2,7,'LUVAS BOX'),(91,3,1,'ANDADORES'),(92,3,1,'MOISÉS'),(93,3,1,'CANGURU'),(94,3,1,'SLING'),(95,3,1,'CADEIRINHAS'),(96,3,2,'MENINAS'),(97,3,2,'MENINOS'),(98,3,2,'BEBÊS'),(99,3,3,'MENINAS'),(100,3,3,'MENINOS'),(101,3,3,'BEBÊS'),(102,3,4,'PEQUENINOS'),(103,3,4,'CRIANÇAS'),(104,3,4,'ADULTOS'),(105,3,5,'BERÇOS'),(106,3,6,'CARRINHOS'),(107,3,7,'OUTROS'),(108,4,1,'CADEIRAS'),(109,4,1,'MESAS'),(110,4,1,'POLTRONAS'),(111,4,1,'RACKS'),(112,4,1,'SOFÁS'),(113,4,1,'OUTROS'),(114,4,2,'ANTIQUÁRIO'),(115,4,3,'DECORAÇÃO'),(116,4,4,'ELETRO'),(117,4,5,'ILUMINAÇÃO'),(118,4,6,'COZINHA'),(119,4,7,'OUTROS'),(120,5,1,'DESKTOPS'),(121,5,1,'NOTEBOOKS'),(122,5,1,'NETBOOKS'),(123,5,1,'PERIFÉRICOS'),(124,5,2,'IPADS'),(125,5,2,'OUTROS'),(126,5,3,'IPHONE'),(127,5,3,'GALAXY'),(128,5,3,'CELULARES'),(129,5,3,'OUTROS'),(130,5,4,'DIGITAIS'),(131,5,4,'ANALOGICOS'),(132,5,4,'LENTES/ACESSÓRIOS'),(133,5,4,'FILMADORAS'),(134,5,4,'POLAROIDS'),(135,5,4,'LOMO'),(136,5,5,'IPODS'),(137,5,5,'MP3'),(138,5,5,'APARELHOS SOM'),(139,5,5,'FONES DE OUVIDO'),(140,5,5,'TOCA DISCOS'),(141,5,5,'VINIL'),(142,5,5,'CD'),(143,5,5,'OUTROS'),(144,5,6,'CONSOLES'),(145,5,6,'JOGOS'),(146,5,6,'ACESSÓRIOS'),(147,5,7,'DVD PLAYER'),(148,5,7,'HOME THEATERS'),(149,5,7,'TUBO DE IMAGEM'),(150,5,7,'LCDS'),(151,5,7,'PLASMA'),(152,5,7,'MONITORES'),(153,5,7,'PLAYERS'),(154,5,7,'PORTÁTEIS'),(155,5,8,'RADIOS'),(156,5,8,'KINDLER'),(157,5,8,'FILMADORAS'),(158,5,8,'DIVERSOS'),(159,5,8,'INTERNET'),(160,6,1,'TABULEIRO'),(161,6,1,'COMPUTADOR'),(162,6,1,'DIDATICOS'),(163,6,1,'CARTAS'),(164,6,2,'ROUPINHAS'),(165,6,2,'CAMAS'),(166,6,2,'ACESSÓRIOS'),(167,6,3,'GUITARRAS'),(168,6,3,'VIOLÕES'),(169,6,3,'BATERIA'),(170,6,3,'MICROFONES'),(171,6,3,'PERCUSSÃO'),(172,6,3,'ELETRÔNICOS'),(173,6,3,'ACUSTICOS'),(174,6,3,'TECLADOS'),(175,6,3,'EQUIPAMENTOS'),(176,6,3,'ACESSÓRIOS'),(177,6,3,'PEDALEIRAS'),(178,6,3,'ESTOJO'),(179,6,3,'SOPRO'),(180,6,4,'INFANTIL'),(181,6,4,'ADULTO'),(182,6,4,'JOVEM'),(183,6,5,'CDS'),(184,6,5,'VINIL'),(185,6,5,'K7'),(186,6,6,'INFANTIL'),(187,6,6,'SERIES'),(188,6,6,'CLÁSSICOS'),(189,6,6,'OUTROS'),(190,6,7,'OMBRO'),(191,6,7,'RODA'),(192,6,7,'ALÇA'),(193,6,8,'CAPINHAS'),(194,6,8,'COPOS E CANECAS'),(195,6,8,'PENDRIVES'),(196,6,8,'ADORNOS'),(197,6,8,'ETC');
/*!40000 ALTER TABLE `itens_subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrimonio`
--

DROP TABLE IF EXISTS `patrimonio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrimonio` (
  `id_patrimonio` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) default NULL,
  `foto` varchar(200) default 'http://localhost:8084/img/foto_default.png',
  `titulo` varchar(45) default NULL,
  `descricao` text,
  `estado_conservacao` varchar(45) default NULL,
  `reservado` tinyint(4) default '0',
  `id_categoria` int(11) default NULL,
  `id_subcategoria` int(11) default NULL,
  `palavra_chave` varchar(45) default NULL,
  `aprovado` tinyint(4) default '0',
  `avisado` tinyint(4) default '0',
  `expirado` tinyint(4) default '0',
  PRIMARY KEY  (`id_patrimonio`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrimonio`
--

LOCK TABLES `patrimonio` WRITE;
/*!40000 ALTER TABLE `patrimonio` DISABLE KEYS */;
INSERT INTO `patrimonio` VALUES (2,6,'/img/foto_default_produto.png','oculos','TUdo na vida passa menos vc','0',NULL,1,2,'bigode',1,0,1),(3,6,'/img/foto_default_produto.png','banheira','TUdo na vida passa menos vc','vleho',NULL,1,2,'bigode',1,0,0),(4,6,'/img/foto_default_produto.png','O danubio Azul','Filme no qual o a tor dse asoifujsdofi soidhf','Muito novo, um pouco arranhado',NULL,1,2,'VHS',1,0,0),(5,6,'/img/foto_default_produto.png','Poltrona','Tudo ontem era menlhosa','Novinho',0,1,1,'fedor',1,0,1),(7,6,'/img/foto_default_produto.png','carro','Filme no qual o a tor dse asoifujsdofi soidhf','Muito novo, um pouco arranhado',NULL,1,2,'VHS',1,0,0),(8,6,'/img/foto_default_produto.png','baralho','fasdasfd','safdas',0,2,14,'bicicleta',1,0,1),(9,6,'/img/foto_default_produto.png','colcha','asdfasdfasdfas','sadfasdf',NULL,6,4,'carneiro',1,0,0),(10,6,'/img/foto_default_produto.png','vestido','asdfasdfad','sadfasdf',NULL,1,2,'carro',1,0,0),(11,38,'/img/foto_default_produto.png','bola','ooo','a',0,2,2,'computador',1,0,1),(12,6,'/img/foto_default_produto.png','martelo','sdf','dsf',NULL,2,3,'tablet',1,0,0),(13,12,'/img/foto_default_produto.png','sadf','asd','asd',NULL,1,2,'ipod',1,0,0),(14,38,'/img/foto_default_produto.png','sardinha','asdasd','asdasd',NULL,2,3,'ipad',1,0,0),(15,38,'/img/foto_default_produto.png','whisky','wer','wer',NULL,2,3,'terno',1,0,1),(16,38,'/img/foto_default_produto.png','Cadeira da vovo','sadf','sadf',NULL,3,3,'roupas',1,0,0),(17,38,'/img/foto_default_produto.png','Mesa jantar','Filme no qual o a tor dse asoifujsdofi soidhf','Muito novo, um pouco arranhado',0,1,2,'viagem',1,0,1),(18,38,'/img/foto_default_produto.png','bermuda jeans','asdfasdfa','sadf',NULL,2,5,'sadf',1,0,0),(19,38,'/img/foto_default_produto.png','bicicleta','asdfgasdfg','dsfgsdfg',NULL,1,3,'sdfgsdfg',1,0,0),(20,38,'/img/foto_default_produto.png','O danubio Azul','Filme no qual o a tor dse asoifujsdofi soidhf','Muito novo, um pouco arranhado',NULL,1,2,'VHS',1,0,0),(21,38,'/img/foto_default_produto.png','O danubio Amarelo','Filme no qual o a tor dse asoifujsdofi soidhf','Muito novo, um pouco arranhado',NULL,1,2,'VHS',1,0,0),(22,38,'/img/foto_default_produto.png','Mesa de centro da vovÃ³','Uma mesinha antiga que pode acrescentar charme ao seu ambiente','semi-novo',0,4,25,'mesa',1,0,0),(23,38,'/img/foto_default_produto.png','patinete','Uma mesa bem antiga','semi-novo',0,4,25,'mesa',1,0,0),(24,38,'/img/foto_default_produto.png','Baratinha de Brinquedo','Baratinha que funciona a pilha e pode ser um Ã³timo brinquedo para a crianÃ§ada','Novo',0,3,21,'barata',1,0,0),(25,38,'/img/foto_default_produto.png','Bolo','sadfasdfadf','asdfasdf',0,2,3,'sadfasdf',1,0,0),(26,38,'/img/foto_default_produto.png','sadf','sadf','sdf',0,2,4,'sdf',1,0,0),(27,38,'/ert/erterrt/poiusdfpoi.jpg','Cadeira de rodas para cachorro','Artefato para cachorro com displazia','Muito novo, um pouco arranhado',0,1,2,'cadeira rodas',0,0,0),(28,38,'/ert/erterrt/poiusdfpoi.jpg','Cadeira de rodas para cachorro','Artefato para cachorro com displazia','Muito novo, um pouco arranhado',0,1,2,'cadeira rodas',0,0,0),(29,38,'/ert/erterrt/poiusdfpoi.jpg','CD do Metalica','Cd novo em otima condição','Zerado',0,1,2,'Metalica',0,0,0),(30,38,'/ert/erterrt/poiusdfpoi.jpg','Mochila de couro','Mochila propria para viagens','Novissimo',0,1,2,'mochila',0,0,0);
/*!40000 ALTER TABLE `patrimonio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `id_historico` int(11) NOT NULL auto_increment,
  `id_usuario_tem` int(11) default NULL,
  `id_usuario_quis` int(11) default NULL,
  `id_patrimonio_tem` int(11) default NULL,
  `id_patrimonio_quis` int(11) default NULL,
  `data_desativacao` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` varchar(45) default NULL,
  `concluido` tinyint(4) default NULL,
  PRIMARY KEY  (`id_historico`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (1,38,6,11,8,'2014-03-19 19:55:41','finalizado',1),(2,38,6,11,8,'2014-03-19 19:56:26','finalizado',1),(3,38,6,17,5,'2014-03-20 17:36:48','finalizado',1),(4,38,6,17,0,'2014-03-24 17:37:08','Doacao Finalizada',1),(5,38,6,17,0,'2014-03-25 19:11:59','Doacao Finalizada',1),(6,38,6,17,0,'2014-03-25 19:16:52','Doacao Finalizada',1),(7,38,6,17,0,'2014-03-25 19:38:05','Doacao Cancelada',0),(8,38,6,17,5,'2014-03-25 19:44:34','Troca Finalizada',1),(9,38,6,17,5,'2014-03-25 19:44:46','Troca Finalizada',1),(10,38,6,17,5,'2014-03-25 19:46:10','Troca Finalizada',1),(11,38,6,17,5,'2014-03-25 19:47:34','Troca Finalizada',1),(12,38,6,17,5,'2014-03-25 19:50:55','Troca Cancelada',0),(13,38,0,15,0,'2014-03-27 00:51:52','Item expirado!',0),(14,-2147483648,-2147483648,-2147483648,-2147483648,'2014-03-27 19:26:27','Excluido pelo usuario',0),(15,6,0,2,0,'2014-03-27 19:35:43','Excluido pelo usuario',0),(16,38,0,15,0,'2014-03-27 19:39:54','Item expirado pelo sistema',0);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
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
INSERT INTO `subcategoria` VALUES (1,1,'ACESSÓRIOS'),(2,1,'ROUPAS'),(3,1,'BELEZA'),(4,1,'CALÇADOS'),(5,1,'BOLSAS'),(6,1,'PRAIA'),(7,1,'LINGERIE'),(8,1,'CASAMENTO'),(9,1,'ESPORTES'),(10,1,'OUTROS'),(11,2,'ACESSÓRIOS'),(12,2,'BELEZA'),(13,2,'CALÇADOS'),(14,2,'ROUPAS'),(15,2,'PRAIA'),(16,2,'ESPORTES'),(17,2,'OUTROS'),(18,3,'ACESSÓRIOS'),(19,3,'ROUPAS'),(20,3,'SAPATOS'),(21,3,'BRINQUEDOS'),(22,3,'BERÇOS'),(23,3,'CARRINHOS'),(24,3,'OUTROS'),(25,4,'MÓVEIS'),(26,4,'ANTIQUARIO'),(27,4,'DECORAÇÃO'),(28,4,'ELETRO'),(29,4,'ILUMINAÇÃO'),(30,4,'COZINHA'),(31,4,'OUTROS'),(32,5,'COMPUTADORES'),(33,5,'TABLETS'),(34,5,'TELEFONIA'),(35,5,'FOTOGRAFIA'),(36,5,'MÚSICA'),(37,5,'VIDEOGAME'),(38,5,'TV`S E DISPLAYS'),(39,5,'OUTROS'),(40,6,'JOGOS'),(41,6,'ANIMAIS'),(42,6,'INSTRUMENTOS MUSICAIS'),(43,6,'LIVROS'),(44,6,'MUSICA'),(45,6,'FILMES'),(46,6,'MALAS'),(47,6,'DIVERSOS');
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id_logradouro` varchar(45) NOT NULL,
  `logradouro` varchar(45) default NULL,
  `numero` int(11) default NULL,
  `complemento` varchar(10) default NULL,
  `bairro` varchar(45) default NULL,
  `cep` varchar(45) default NULL,
  `uf` varchar(45) default NULL,
  `cidade` varchar(45) default NULL,
  `id_usuario` int(11) default NULL,
  PRIMARY KEY  (`id_logradouro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES ('1','R Aristoteles Caldeira ',368,'B',NULL,'30411225','MG','Belo Horizonte',38);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
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
  `validado` tinyint(4) default '0',
  `autentico` tinyint(4) default NULL,
  PRIMARY KEY  (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jonas melo','jj','abc',1,'2014-03-20 17:21:25','img/foto_default.png',0,0),(2,'henrique','dsf','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(3,'Naldo','ss','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(4,'Pinheiro','ppp','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(5,'Marcela','mmm','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(6,'Leda','vovoledabh@gmail.com','abc',0,'2014-03-25 19:50:55','img/foto_default.png',0,0),(7,'Jonas','j','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(8,'Carla','c','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(9,'Paula','paula','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(10,'Pedro','pedro','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(11,'Miguel','miguel','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(12,'SOfia','sofia','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(13,'Vicentina','vicenti','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(14,'Claudia','abc123','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(15,'Carol','carrrro','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(16,'Tina','titi','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(17,'Bernardo','berma','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(18,'Madelania','made','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(19,'Maria','mama','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(20,'Jose','jose','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(21,'Josi','josi','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(22,'Michel','micha','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(23,'SOraia','soso','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(24,'Sabrina','sabrina','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(25,'Helena','helena','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(26,'Regina','regina','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(31,'buba','buba','abc',0,'2014-03-20 17:21:25','img/foto_default.png',1,0),(34,'pipi','pipi','abc',0,'2014-03-20 17:21:25','img/foto_default.png',1,0),(36,'Kiki','kkiki','abc',0,'2014-03-20 17:21:25','img/foto_default.png',1,0),(35,'Carolina','carol.marini@gmail.com','abc',0,'2014-03-20 17:21:25','img/foto_default.png',1,0),(37,'Ubaldo','uba','abc',0,'2014-03-20 17:21:25','img/foto_default.png',0,0),(38,'Joao Santos','joaomarcelo.ms@gmail.com','abc',0,'2014-03-25 19:50:55','img/foto_default.png',1,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-27 16:48:48
