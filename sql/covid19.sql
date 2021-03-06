-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- 생성 시간: 22-02-27 09:55
-- 서버 버전: 10.4.21-MariaDB
-- PHP 버전: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `covid19`
--

CREATE TABLE `daum_news` (
  `id` varchar(50) NOT NULL COMMENT '글번호',
  `reg_date` datetime NOT NULL,
  `category` varchar(50) default NULL,
  `thumbnail` varchar(250) DEFAULT NULL,
  `title_thumbnail` varchar(250) default null,
  `summary` varchar(500) NOT NULL,
  `title_name` varchar(200) NOT NULL,
  `title_contents` text NOT NULL,
  `reporter` varchar(100) NOT NULL COMMENT '기자',
  `newspaper` varchar(100) NOT NULL,
  `open_yn` char(1) NOT NULL DEFAULT 'Y',
  `view_count` int(11) NOT NULL,
  `link` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='daum.news';


-- --------------------------------------------------------

--
-- 테이블 구조 `board`
--

CREATE TABLE `board` (
  `board_no` bigint(20) NOT NULL,
  `contents` varchar(2000) NOT NULL,
  `regdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `title` varchar(200) NOT NULL,
  `writer` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 테이블 구조 `markers`
--

CREATE TABLE `markers` (
  `marker_no` bigint(20) NOT NULL,
  `address` varchar(500) NOT NULL,
  `address_second` varchar(500) NOT NULL,
  `confirm_date` varchar(50) DEFAULT NULL,
  `disinfect` varchar(500) NOT NULL,
  `location_name` varchar(500) NOT NULL,
  `position_x` varchar(20) DEFAULT NULL,
  `position_y` varchar(20) DEFAULT NULL,
  `regdate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 테이블 구조 `member`
--

CREATE TABLE `member` (
  `user_no` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `position_x` varchar(20) DEFAULT NULL,
  `position_y` varchar(20) DEFAULT NULL,
  `refresh_token` varchar(4096) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`board_no`);

--
-- 테이블의 인덱스 `markers`
--
ALTER TABLE `markers`
  ADD PRIMARY KEY (`marker_no`);

--
-- 테이블의 인덱스 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`user_no`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `board`
--
ALTER TABLE `board`
  MODIFY `board_no` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 테이블의 AUTO_INCREMENT `markers`
--
ALTER TABLE `markers`
  MODIFY `marker_no` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 테이블의 AUTO_INCREMENT `member`
--
ALTER TABLE `member`
  MODIFY `user_no` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
