-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2023 at 11:48 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode` int(10) NOT NULL,
  `Nama` varchar(500) NOT NULL,
  `Harga` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode`, `Nama`, `Harga`) VALUES
(1, 'Pulpen', 5000),
(2, 'Pensil', 3000),
(3, 'Spidol', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(500) NOT NULL,
  `password` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(1, 'admin', '1');

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `kode` int(10) NOT NULL,
  `Nama` varchar(500) NOT NULL,
  `Harga` float NOT NULL,
  `Kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`kode`, `Nama`, `Harga`, `Kadaluarsa`) VALUES
(4, 'Chitato', 10000, '2023-10-24'),
(5, 'Potabee', 8000, '2024-06-13'),
(6, 'Oreo', 12000, '2025-03-11');

-- --------------------------------------------------------

--
-- Table structure for table `pulsa`
--

CREATE TABLE `pulsa` (
  `kode` int(10) NOT NULL,
  `Nama` varchar(500) NOT NULL,
  `Harga` float NOT NULL,
  `operator` varchar(500) NOT NULL,
  `nominal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pulsa`
--

INSERT INTO `pulsa` (`kode`, `Nama`, `Harga`, `operator`, `nominal`) VALUES
(7, 'TELKOM10', 12000, 'TELKOMSEL', 10000),
(8, 'TELKOM20', 22000, 'TELKOMSEL', 20000),
(9, 'TELKOM50', 52000, 'TELKOMSEL', 50000),
(10, 'TRI10', 12000, 'TRI', 10000),
(11, 'TRI20', 22000, 'TRI', 20000),
(12, 'TRI50', 52000, 'TRI', 50000),
(13, 'INDOSAT10', 12000, 'INDOSAT', 10000),
(14, 'INDOSAT20', 22000, 'INDOSAT', 20000),
(15, 'INDOSAT50', 52000, 'INDOSAT', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `kode` int(10) NOT NULL,
  `Nama` varchar(500) NOT NULL,
  `Harga` float NOT NULL,
  `nominal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`kode`, `Nama`, `Harga`, `nominal`) VALUES
(16, 'TOKEN50', 55000, 50000),
(17, 'TOKEN100', 105000, 100000),
(18, 'TOKEN200', 205000, 200000);

-- --------------------------------------------------------

--
-- Stand-in structure for view `totalitem`
-- (See below for the actual view)
--
CREATE TABLE `totalitem` (
`kode` int(11)
,`Nama` varchar(500)
,`Harga` float
);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(12) NOT NULL,
  `daftar_belanja` text NOT NULL,
  `total_belanja` float NOT NULL,
  `jumlah_bayar` float NOT NULL,
  `kembalian` float NOT NULL,
  `jenis_pembayaran` text NOT NULL,
  `invoice_id` text NOT NULL,
  `waktu_transaksi` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `daftar_belanja`, `total_belanja`, `jumlah_bayar`, `kembalian`, `jenis_pembayaran`, `invoice_id`, `waktu_transaksi`) VALUES
(1, 'Pulpen  X1, TELKOM10 12312321312 X1, ', 17000, 20000, 3000, 'Kas', '8507519673', '2023-06-08 08:57:39'),
(2, 'Pensil  X1, TOKEN50 12321321421421 X1, ', 58000, 58000, 0, 'Debit BCA 23523523523253', '9169316886', '2023-06-08 08:58:09'),
(3, 'Spidol  X1, TOKEN50 12523523243253 X1, ', 65000, 65000, 0, 'QRIS', '7180889194', '2023-06-08 08:59:04'),
(4, 'Pulpen  X1, TELKOM10 2112421214 X1, ', 17000, 20000, 3000, 'Kas', '7467384482', '2023-06-08 09:05:15'),
(5, 'Pensil  X1, TOKEN100 1531525234253 X1, ', 108000, 108000, 0, 'Debit BNI 43634643463', '2303489574', '2023-06-08 09:05:35'),
(6, 'Spidol  X1, TOKEN200 1213523523 X1, ', 215000, 215000, 0, 'QRIS', '3259377001', '2023-06-08 09:06:09'),
(7, 'Chitato  X1, INDOSAT50 12412412421 X1, ', 62000, 62000, 0, 'QRIS', '1661592480', '2023-06-08 09:07:31'),
(8, 'Pulpen  X1, ', 5000, 5000, 0, 'QRIS', '9649951247', '2023-06-08 09:19:44'),
(9, 'Pulpen  X1, ', 5000, 5000, 0, 'QRIS', '7377767348', '2023-06-08 09:24:04'),
(10, 'Pulpen  X1, ', 5000, 5000, 0, 'QRIS', '3587112246', '2023-06-08 09:31:19'),
(11, 'TOKEN100 12412421 X1, ', 105000, 105000, 0, 'QRIS', '4933682048', '2023-06-08 09:31:57'),
(12, 'Pulpen  X1, ', 5000, 10000, 5000, 'Kas', '1327336692', '2023-06-08 09:35:37'),
(13, 'Pulpen  X1, ', 5000, 15000, 10000, 'Kas', '6048349945', '2023-06-08 09:35:55'),
(14, 'Chitato  X1, TELKOM10 082121212121 X1, ', 22000, 22000, 0, 'QRIS', '4383946506', '2023-06-08 09:38:00'),
(15, 'TOKEN100 12321321321312 X1, INDOSAT50 08212121212121 X1, ', 157000, 200000, 43000, 'Kas', '4841669645', '2023-06-08 14:40:32'),
(16, 'Pulpen  X1, ', 5000, 5000, 0, 'Debit BSI 412421421412214', '5286312716', '2023-06-08 14:47:56'),
(17, 'Pulpen  X1, ', 5000, 5000, 0, 'Debit Mandiri 111821321321321', '8923024762', '2023-06-09 10:20:43'),
(18, 'Potabee  X1, ', 8000, 100000, 92000, 'Kas', '3522083907', '2023-06-10 15:28:34'),
(19, 'Pulpen  X1, ', 5000, 100000, 95000, 'Kas', '7093382288', '2023-06-10 15:33:15'),
(20, 'Pulpen  X1, Potabee  X1, ', 13000, 13000, 0, 'Debit BRI 8888999922221111', '2517636357', '2023-06-10 15:34:00'),
(21, 'TOKEN50 888888222221111 X1, ', 55000, 55000, 0, 'QRIS', '1078882159', '2023-06-10 15:34:32');

-- --------------------------------------------------------

--
-- Structure for view `totalitem`
--
DROP TABLE IF EXISTS `totalitem`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalitem`  AS SELECT `barang`.`kode` AS `kode`, `barang`.`Nama` AS `Nama`, `barang`.`Harga` AS `Harga` FROM `barang` union all select `makanan`.`kode` AS `kode`,`makanan`.`Nama` AS `Nama`,`makanan`.`Harga` AS `Harga` from `makanan` union all select `pulsa`.`kode` AS `kode`,`pulsa`.`Nama` AS `Nama`,`pulsa`.`Harga` AS `Harga` from `pulsa` union all select `token`.`kode` AS `kode`,`token`.`Nama` AS `Nama`,`token`.`Harga` AS `Harga` from `token`  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `pulsa`
--
ALTER TABLE `pulsa`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
