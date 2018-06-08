-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2018 at 03:19 PM
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
-- Database: `Vezba3`
--

-- --------------------------------------------------------

--
-- Table structure for table `Glavno_jelo`
--

CREATE TABLE `Glavno_jelo` (
  `id_glj` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Glavno_jelo`
--

INSERT INTO `Glavno_jelo` (`id_glj`, `Naziv`, `Cena`) VALUES
(1, 'Karadjordjeva', 1600),
(2, 'Pasulj', 400),
(3, 'Punjeni batak', 1400),
(4, 'Pileci paketici', 1300),
(5, 'Cevapi', 800),
(6, 'Gulas', 900);

-- --------------------------------------------------------

--
-- Table structure for table `Narudzbina`
--

CREATE TABLE `Narudzbina` (
  `id_glj` int(11) NOT NULL,
  `id_sal` int(11) NOT NULL,
  `id_slat` int(11) NOT NULL,
  `KolicinaGlavnogJela` int(11) NOT NULL,
  `KolicinaSalate` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Narudzbina`
--

INSERT INTO `Narudzbina` (`id_glj`, `id_sal`, `id_slat`, `KolicinaGlavnogJela`, `KolicinaSalate`, `Email`) VALUES
(6, 4, 5, 120, 150, 'ragnarlotbruk203@gmail.com'),
(6, 4, 5, 150, 360, 'ragnarlotbruk203@gmail.com'),
(6, 4, 5, 520, 200, ''),
(6, 4, 5, 100, 100, ''),
(6, 4, 5, 150, 350, 'ragnarlotbruk203@gmail.com'),
(6, 4, 5, 120, 200, ''),
(6, 4, 5, 123, 250, 'adada'),
(6, 4, 5, 1312, 324, '');

-- --------------------------------------------------------

--
-- Table structure for table `Salata`
--

CREATE TABLE `Salata` (
  `id_sal` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Salata`
--

INSERT INTO `Salata` (`id_sal`, `Naziv`, `Cena`) VALUES
(1, 'Sopska salata', 300),
(2, 'Ruska salata', 350),
(3, 'Kupus salata', 200),
(4, 'Cezar salata', 700);

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
(2, 'Jaffa', 110, 105),
(3, 'Cokolada', 300, 350),
(4, 'Sladoled', 240, 170),
(5, 'Rum kasato', 80, 75);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Glavno_jelo`
--
ALTER TABLE `Glavno_jelo`
  ADD PRIMARY KEY (`id_glj`);

--
-- Indexes for table `Narudzbina`
--
ALTER TABLE `Narudzbina`
  ADD KEY `id_glj` (`id_glj`),
  ADD KEY `id_sal` (`id_sal`),
  ADD KEY `id_slat` (`id_slat`);

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
  MODIFY `id_glj` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Salata`
--
ALTER TABLE `Salata`
  MODIFY `id_sal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Slatkis`
--
ALTER TABLE `Slatkis`
  MODIFY `Id_slat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Narudzbina`
--
ALTER TABLE `Narudzbina`
  ADD CONSTRAINT `Narudzbina_ibfk_1` FOREIGN KEY (`id_glj`) REFERENCES `Glavno_jelo` (`id_glj`),
  ADD CONSTRAINT `Narudzbina_ibfk_2` FOREIGN KEY (`id_slat`) REFERENCES `Slatkis` (`Id_slat`),
  ADD CONSTRAINT `Narudzbina_ibfk_3` FOREIGN KEY (`id_sal`) REFERENCES `Salata` (`id_sal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
