-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 25 Avril 2017 à 09:08
-- Version du serveur: 5.5.54-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET NAMES 'utf8';

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bovoyagetest`
--
DROP DATABASE IF EXISTS `bovoyagetest`;
CREATE DATABASE IF NOT EXISTS `bovoyagetest` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bovoyage`;

-- --------------------------------------------------------

--
-- Structure de la table `destinations`
--

DROP TABLE IF EXISTS `destinations`;
CREATE TABLE IF NOT EXISTS `destinations` (
  `pk_destination` int(11) NOT NULL AUTO_INCREMENT,
  `continent` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `pays` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `region` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  CONSTRAINT pk_destination
    PRIMARY KEY (`pk_destination`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6;

--
-- Contenu de la table `destinations`
--

INSERT INTO `destinations` (`pk_destination`, `region`, `pays`, `description`) VALUES
(1, 'Guadeloupe', 'France', 'Dans un site exceptionnel,en bordure d''un petit lagon turquoise, tout est réuni pour un séjour paradisiaque à la découverte des merveilles de la terre et de la mer'),
(2, 'Saint-Barthélémy', 'France', 'Imaginez une île où il fait 26 à 28 °C toute l''année, et où la température de la mer est la même que celle de l''air'),
(3, 'Myanmar', 'Birmanie', 'La Birmanie est un pays passionnat pour tous ceux qui s''interessent à l''art, aux civilisations, à l''inddhouisme. Ce pays s''ouvre et a conservé toute la richesse de son patrimoine culturel'),
(4, 'Canada', 'Canada', 'Découvrez la nature généreuse et les grandes villes du Canada en toute saison, grâce aux nombreux circuits que nous avons élaborés.'),
(5, 'Alpes suisses', 'Suisse', 'Venez découvrir les paysages somptueux de la Suisse, aussi bien en été qu''en hiver.');

-- --------------------------------------------------------

--
-- Structure de la table `dates_voyages`
--

DROP TABLE IF EXISTS `dates_voyages`;
CREATE TABLE IF NOT EXISTS `dates_voyages` (
  `pk_date_voyage` int(11) NOT NULL AUTO_INCREMENT,
  `date_depart` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_retour` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nb_voyageurs` int(11) NOT NULL DEFAULT '0',
  `prixHT` double NOT NULL DEFAULT '0',
  `fk_destination` int(11) NOT NULL DEFAULT '0',
  CONSTRAINT pk_date_voyage 
    PRIMARY KEY (`pk_date_voyage`) 
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Contenu de la table `dates_voyages`
--

INSERT INTO `dates_voyages` (`pk_date_voyage`, `date_depart`, `date_retour`, `prixHT`, `fk_destination`) VALUES
(1, '2017-06-12 00:00:00', '2017-06-24 00:00:00', 359, 1),
(2, '2017-08-01 00:00:00', '2017-08-15 00:00:00', 1450, 1),
(3, '2017-09-03 00:00:00', '2017-09-15 00:00:00', 1420, 1),
(4, '2017-06-03 00:00:00', '2017-06-15 00:00:00', 1250, 2),
(5, '2017-07-08 00:00:00', '2017-07-18 00:00:00', 1300, 2),
(6, '2017-07-02 00:00:00', '2017-07-15 00:00:00', 1700, 3),
(7, '2017-11-12 00:00:00', '2017-11-25 00:00:00', 1890, 3),
(8, '2017-12-27 00:00:00', '2017-01-10 00:00:00', 2200, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
