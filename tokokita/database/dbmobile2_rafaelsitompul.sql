-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2025 at 04:54 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbmobile2_rafaelsitompul`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_barang`
--

CREATE TABLE `tb_barang` (
  `kdbarang` varchar(10) NOT NULL,
  `nmbarang` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_barang`
--

INSERT INTO `tb_barang` (`kdbarang`, `nmbarang`, `harga`, `stok`, `deskripsi`, `foto`) VALUES
('BR001', 'Laptop Gaming ASUS ROG', 19500000, 10, 'Laptop gaming berperforma tinggi dengan processor Intel Core i9, RAM 32GB dan VGA NVIDIA GeForce RTX 3080', 'https://images.unsplash.com/photo-1603302576837-37561b2e2302?w=400'),
('BR002', 'Monitor Curved Samsung', 3500000, 15, 'Monitor gaming curved 27 inch dengan refresh rate 144Hz dan resolusi 2K', 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=400'),
('BR003', 'Keyboard Mekanik HyperX', 1200000, 25, 'Keyboard gaming mekanik dengan switch Cherry MX Red dan RGB lighting', 'https://images.unsplash.com/photo-1541140532154-b024d705b90a?w=400'),
('BR004', 'SSD NVME Samsung 1TB', 2100000, 20, 'SSD NVMe dengan kecepatan baca hingga 7000MB/s dan kapasitas 1TB', 'https://images.unsplash.com/photo-1597872200969-2b65d56bd16b?w=400'),
('BR005', 'Mouse Gaming Logitech G502', 850000, 30, 'Mouse gaming dengan sensor HERO 25K dan 11 tombol yang dapat diprogram', 'https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`kdbarang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
