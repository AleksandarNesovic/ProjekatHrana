-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 05, 2018 at 02:12 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Vezba2`
--

-- --------------------------------------------------------

--
-- Table structure for table `Glavno_jelo`
--

CREATE TABLE `Glavno_jelo` (
  `id_glj` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `Cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Glavno_jelo`
--

INSERT INTO `Glavno_jelo` (`id_glj`, `Naziv`, `Kolicina`, `Cena`) VALUES
(1, 'Karadjordjeva', 250, 400),
(2, 'Pasulj', 300, 110),
(3, 'Punjeni batak', 200, 330),
(4, 'Pileci paketici', 350, 290),
(5, 'Cevapi', 250, 180);

-- --------------------------------------------------------

--
-- Table structure for table `Salata`
--

CREATE TABLE `Salata` (
  `id_sal` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Porcija` int(11) NOT NULL,
  `Cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Salata`
--

INSERT INTO `Salata` (`id_sal`, `Naziv`, `Porcija`, `Cena`) VALUES
(1, 'Sopska salata', 150, 100),
(2, 'Ruska salata', 150, 110),
(3, 'Kupus salata', 150, 80);

-- --------------------------------------------------------

--
-- Table structure for table `Slatkis`
--

CREATE TABLE `Slatkis` (
  `Id_slat` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `Cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Slatkis`
--

INSERT INTO `Slatkis` (`Id_slat`, `Naziv`, `Kolicina`, `Cena`) VALUES
(1, 'Sam rolna', 120, 150),
(2, 'Jaffa', 80, 105),
(3, 'Cokolada', 300, 350),
(4, 'Sladoled', 90, 170);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Glavno_jelo`
--
ALTER TABLE `Glavno_jelo`
  ADD PRIMARY KEY (`id_glj`);

--
-- Indexes for table `Salata`
--
ALTER TABLE `Salata`
  ADD PRIMARY KEY (`id_sal`);

--
-- Indexes for table `Slatkis`
--
ALTER TABLE `Slatkis`
  ADD PRIMARY KEY (`Id_slat`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Glavno_jelo`
--
ALTER TABLE `Glavno_jelo`
  MODIFY `id_glj` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Salata`
--
ALTER TABLE `Salata`
  MODIFY `id_sal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Slatkis`
--
ALTER TABLE `Slatkis`
  MODIFY `Id_slat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
