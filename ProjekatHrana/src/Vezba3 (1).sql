-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 14, 2018 at 02:25 PM
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
-- Table structure for table `Klijenti`
--

CREATE TABLE `Klijenti` (
  `id_klijenta` int(11) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  `BrojTelefona` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Klijenti`
--

INSERT INTO `Klijenti` (`id_klijenta`, `Ime`, `Prezime`, `BrojTelefona`, `Email`) VALUES
(1, 'Pera', 'Peric', '0656985123', 'peraperic@gmail.com'),
(2, 'Mika', 'Mikic', '0639856789', 'mikamikic@gmail.com'),
(3, 'Toma', 'Tomic', '0645823147', 'tomatomic@gmail.com'),
(4, 'Zika', 'Zikic', '0698745632', 'zikazikic@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `Narudzbina`
--

CREATE TABLE `Narudzbina` (
  `id_narudzbine` int(11) NOT NULL,
  `id_klijenta` int(11) NOT NULL,
  `id_glj` int(11) NOT NULL,
  `id_sal` int(11) NOT NULL,
  `id_slat` int(11) NOT NULL,
  `KolicinaGlavnogJela` int(11) NOT NULL,
  `KolicinaSalate` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `datumPorudzbine` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Narudzbina`
--

INSERT INTO `Narudzbina` (`id_narudzbine`, `id_klijenta`, `id_glj`, `id_sal`, `id_slat`, `KolicinaGlavnogJela`, `KolicinaSalate`, `Email`, `datumPorudzbine`) VALUES
(1, 1, 1, 1, 1, 321, 265, 'aaohfdhasl', '2018-06-13'),
(2, 2, 1, 1, 1, 222, 333, 'qwesafs', '2018-06-13'),
(3, 2, 1, 1, 1, 145, 100, 'sdfweewr', '2018-06-13'),
(4, 2, 1, 1, 1, 121, 123, 'dadwaq', '2018-06-13'),
(5, 4, 4, 3, 3, 143, 50, 'fdsfdfs', '2018-06-13'),
(6, 4, 1, 1, 1, 131, 123, 'wqwe', '2018-06-14'),
(7, 2, 2, 2, 2, 112, 100, 'dfhgegd', '2018-06-14'),
(8, 1, 1, 2, 2, 150, 120, 'dfhgegd', '2018-06-14'),
(9, 4, 5, 4, 3, 230, 100, 'ksbvhbsdh', '2018-06-15'),
(10, 1, 1, 4, 3, 250, 100, 'ksbvhbsdh', '2018-06-15'),
(11, 3, 1, 4, 3, 230, 145, 'crazynorth203@gmail.com', '2018-06-15'),
(12, 3, 4, 2, 5, 250, 100, 'crazynorth203@gmail.com', '2018-06-14'),
(13, 2, 4, 2, 5, 22, 22, 'vxzfeww', '2018-06-14'),
(14, 1, 1, 1, 1, 123, 80, 'fsfsf', '2018-06-15'),
(15, 2, 1, 1, 1, 123, 231, 'sadda', '2018-06-13'),
(16, 3, 1, 1, 1, 112, 211, 'asdadad@da.ada', '2018-06-13'),
(17, 2, 1, 1, 1, 131, 213, 'crazynorth@gmail.com', '2018-06-20'),
(18, 2, 1, 1, 1, 131, 213, 'crazynorth@gmail.com', '2018-06-20'),
(19, 2, 1, 1, 1, 213, 3131, 'crazynorth@gmail.com', '2018-06-20'),
(20, 2, 1, 1, 1, 12, 23, 'fbfb@akaj.fh', '2018-06-20'),
(21, 2, 1, 1, 1, 21, 21, 'asdad@saa.', '2018-06-20'),
(22, 2, 1, 1, 1, 12, 213, 'isfgsklg@pasj.', '2018-06-20'),
(23, 2, 1, 1, 1, 121, 121, 'adsa@.', '2018-06-20'),
(24, 2, 1, 1, 1, 211, 211, 'adaabdb@.', '2018-06-13'),
(25, 2, 1, 1, 1, 211, 211, 'adas@.', '2018-06-13'),
(26, 2, 1, 1, 1, 211, 211, 'adnnnnns@.', '2018-06-13'),
(27, 1, 1, 1, 1, 213, 311, 'd@.', '2018-06-18'),
(28, 1, 1, 1, 1, 213, 311, 'd@.', '2018-06-18'),
(29, 1, 1, 1, 1, 212, 213, 'dddd@.', '2018-06-19'),
(30, 1, 1, 1, 1, 212, 213, 'dddd@.', '2018-06-19'),
(31, 2, 1, 1, 1, 123, 211, 'agdaru@/.', '2018-06-13'),
(32, 2, 1, 1, 1, 213, 21, 'admmmm@.', '2018-06-13'),
(33, 2, 1, 1, 1, 12, 311, 'dad@.', '2018-06-13'),
(34, 2, 1, 1, 1, 122, 213, 'ff@.', '2018-06-13'),
(35, 2, 1, 1, 1, 113, 2321, 'aa@.a', '2018-06-13'),
(36, 2, 1, 1, 1, 123, 12, 'w@.', '2018-06-13'),
(37, 2, 1, 1, 1, 232, 2123, 'da@.', '2018-06-13'),
(38, 2, 1, 1, 1, 123, 321, 'eqeeq@.', '2018-06-19'),
(39, 4, 5, 4, 4, 123, 321, 'ragnarlotbruk203@gmail.com', '2018-06-16'),
(40, 4, 1, 1, 1, 123, 121, 'ragnarlotbruk203@gmail.com', '2018-06-22'),
(41, 3, 4, 3, 2, 145, 122, 'ragnarlotbruk203@gmail.com', '2018-06-16'),
(42, 3, 3, 3, 3, 321, 243, 'acaaca@.', '2018-06-11'),
(43, 3, 1, 1, 1, 135, 345, 'malo@.', '2018-06-10'),
(44, 4, 1, 1, 1, 135, 345, 'malo@.', '2018-06-10');

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
-- Indexes for table `Klijenti`
--
ALTER TABLE `Klijenti`
  ADD PRIMARY KEY (`id_klijenta`);

--
-- Indexes for table `Narudzbina`
--
ALTER TABLE `Narudzbina`
  ADD PRIMARY KEY (`id_narudzbine`),
  ADD KEY `id_klijenta` (`id_klijenta`),
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
-- AUTO_INCREMENT for table `Klijenti`
--
ALTER TABLE `Klijenti`
  MODIFY `id_klijenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Narudzbina`
--
ALTER TABLE `Narudzbina`
  MODIFY `id_narudzbine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

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
  ADD CONSTRAINT `Narudzbina_ibfk_1` FOREIGN KEY (`id_klijenta`) REFERENCES `Klijenti` (`id_klijenta`),
  ADD CONSTRAINT `Narudzbina_ibfk_2` FOREIGN KEY (`id_glj`) REFERENCES `Glavno_jelo` (`id_glj`),
  ADD CONSTRAINT `Narudzbina_ibfk_3` FOREIGN KEY (`id_sal`) REFERENCES `Salata` (`id_sal`),
  ADD CONSTRAINT `Narudzbina_ibfk_4` FOREIGN KEY (`id_slat`) REFERENCES `Slatkis` (`Id_slat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
