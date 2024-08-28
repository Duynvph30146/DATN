GO
USE master
GO
DROP DATABASE DATN
GO
CREATE DATABASE DATN
GO
USE DATN
GO
CREATE TABLE DiaChi (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdNguoiDung INT NOT NULL,
TenDuong NVARCHAR(100) NOT NULL,
XaPhuong NVARCHAR(100) NOT NULL,
QuanHuyen NVARCHAR(100) NOT NULL,
TinhThanhPho NVARCHAR(100) NOT NULL,
SdtNguoiNhan VARCHAR(30) NULL,
HoTenNguoiNhan NVARCHAR(100) NULL,
NguoiTao NVARCHAR(100) NULL,
NguoiCapNhat NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE NguoiDung (
Id INT IDENTITY(1,1) PRIMARY KEY,
TaiKhoan VARCHAR(50)  NULL,
MatKhau VARCHAR(300)  NULL,
Email VARCHAR(50)  NULL,
HoVaTen NVARCHAR(100) NOT NULL,
NgaySinh DATE NULL,
Cccd VARCHAR(20) NULL,
SoDienThoai VARCHAR(20)  NULL,
GioiTinh BIT DEFAULT 0,
Anh VARCHAR(300) NULL,
NgayTao DATETIME DEFAULT GETDATE()  NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE()  NULL,
NguoiTao NVARCHAR(100)  NULL,
NguoiCapNhat NVARCHAR(100)  NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE KhachHangPhieuGiam (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdKhachHang INT NOT NULL,
IdPhieuGiam INT NOT NULL,
)
GO
CREATE TABLE KhachHang (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdNguoiDung INT UNIQUE NOT NULL,
MaKhachHang VARCHAR(50),
NgayTao DATETIME DEFAULT GETDATE()  NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE()  NULL,
NguoiTao NVARCHAR(100)  NULL,
NguoiCapNhat NVARCHAR(100)  NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE GioHangChiTiet (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdSanPhamChiTiet INT NOT NULL,
IdGioHang INT NOT NULL,
SoLuong SMALLINT  NULL,
NgayTao DATETIME DEFAULT GETDATE()  NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE GioHang (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdKhachHang INT UNIQUE NOT NULL,
NgayTao DATETIME DEFAULT GETDATE()  NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE PhuongThucThanhToan (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdHoaDon INT NOT NULL,
TenPhuongThuc NVARCHAR(50) NOT NULL,
MoTa NVARCHAR(MAX) NULL,
TongTien MONEY NOT NULL,
MaGiaoDichVnPay VARCHAR(200)  NULL,
NgayTao DATETIME DEFAULT GETDATE()  NULL,
NguoiTao NVARCHAR(100)  NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE()  NULL,
NguoiCapNhat NVARCHAR(100)  NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE HoaDon (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdNhanVien INT  NOT NULL,
IdKhachHang INT NULL,
HoaToc BIT NULL,
Sdt VARCHAR(20) NULL,
Mahoadon NVARCHAR(100) UNIQUE NULL,
DiaChi NVARCHAR(100) NULL,
Email VARCHAR(100) NULL,
TongTien MONEY  NULL,
NgayXacNhan DATETIME NULL,
NgayVanChuyen DATETIME NULL,
NgayNhanHang DATETIME NULL,
NgayGiaoDuKien DATETIME NULL,
NgayHoanThanh DATETIME NULL,
LoaiHoaDon BIT DEFAULT 0,
PhiVanChuyen MONEY NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
GhiChu NVARCHAR(MAX) NULL,
TenNguoiNhan NVARCHAR(50) NULL,
TrangThai INT
)
GO
CREATE TABLE LichSuHoaDon (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdHoaDon INT  NOT NULL,
IdNhanVien INT  NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
GhiChu NVARCHAR(MAX) NULL,
TrangThai INT
)
GO
CREATE TABLE Anh (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdSanPhamChiTiet INT NOT NULL,
TenAnh VARCHAR(300) NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE SanPhamChiTiet (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdSanPham INT NOT NULL,
IdKichCo INT NOT NULL,
IdThuongHieu INT NOT NULL,
IdMauSac INT NOT NULL,
IdChatLieu INT NOT NULL,
IdDeGiay INT NOT NULL,
QRCode NVARCHAR(MAX) NULL,
MaSanPhamChiTiet NVARCHAR(MAX) NULL,
SoLuong INT NULL,
MoTa NVARCHAR(MAX) NULL,
GioiTinh BIT NULL,
GiaTien MONEY NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE HoaDonChiTiet (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdHoaDon INT NOT NULL,
IdSanPhamChiTiet INT NOT NULL,
GiaSanPham MONEY NULL,
SoLuong SMALLINT NULL,
GhiChu NVARCHAR(MAX) NULL,

TrangThai BIT DEFAULT 0,
)
GO
CREATE TABLE PhieuGiamGiaChiTiet (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdHoaDon INT  NOT NULL,
IdMaGiamGia INT  NOT NULL,
GiaBanDau MONEY  NULL,
GiaSauApDung MONEY  NULL,
TienGiam MONEY  NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
)
GO
CREATE TABLE PhieuGiamGia (
Id INT IDENTITY(1,1) PRIMARY KEY,
MaCode NVARCHAR(MAX) NULL,
TenPhieu NVARCHAR(300) NOT NULL,
GiaTriGiamToiDa MONEY NULL,--giảm tối đa là 40k
GiaTriGiam INT NOT NULL,--giảm 50%
DonToiThieu MONEY NULL,--tổng đơn là 100k
SoLuong SMALLINT NULL,
LoaiPhieu BIT DEFAULT 0,
KieuPhieu BIT DEFAULT 0,
NgayBatDau DATETIME NOT NULL,
NgayKetThuc DATETIME NOT NULL,
NgayTao DATETIME DEFAULT GETDATE(),
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE(),
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai INT NOT NULL
)
GO
CREATE TABLE SanPham (
Id INT IDENTITY(1,1) PRIMARY KEY,
QRCode NVARCHAR(MAX) NULL,
MaSanPham NVARCHAR(MAX) NULL,
TenSanPham NVARCHAR(300) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE KichCo (
Id INT IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(50) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE ThuongHieu (
Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
Ten NVARCHAR(100) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE MauSac (
Id INT IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(100) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE ChatLieu (
Id INT IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(100) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE DeGiay (
Id INT IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(100) NOT NULL,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 1
)
GO
CREATE TABLE SanPhamTraHang (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdSanPhamChiTiet INT,
SoLuong SMALLINT,
GhiChu NVARCHAR (MAX),
ThoiGianToiThieu DATETIME,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE NhanVien(
Id INT IDENTITY(1,1) PRIMARY KEY,
IdNguoiDung INT,
MaNhanVien VARCHAR(50),
VaiTro INT DEFAULT 0,
NgayTao DATETIME DEFAULT GETDATE() NULL,
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE() NULL,
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE DotGiamGia(
Id INT IDENTITY(1,1) PRIMARY KEY,
TenDot NVARCHAR(300) NOT NULL,
GiaTriGiam INT NOT NULL,--giảm 50%
NgayBatDau DATETIME NOT NULL,
NgayKetThuc DATETIME NOT NULL,
NgayTao DATETIME DEFAULT GETDATE(),
NguoiTao NVARCHAR(100) NULL,
LanCapNhatCuoi DATETIME DEFAULT GETDATE(),
NguoiCapNhat NVARCHAR(100) NULL,
TrangThai INT NOT NULL
)
GO
CREATE TABLE SanPhamDotGiam (
Id INT IDENTITY(1,1) PRIMARY KEY,
IdChiTietSanPham INT NOT NULL,
IdDotGiam INT NOT NULL,
)

--Dia chi
ALTER TABLE DiaChi ADD CONSTRAINT FK_DiaChi_NguoiDung_IdNguoiDung
FOREIGN KEY (IdNguoiDung) REFERENCES NguoiDung(Id)
-- Khach hang phieu giam
ALTER TABLE KhachHangPhieuGiam ADD CONSTRAINT FK_KhachHangPhieuGiam_KhachHang_IdKhachHang
FOREIGN KEY (IdKhachHang) REFERENCES KhachHang(Id)
ALTER TABLE KhachHangPhieuGiam ADD CONSTRAINT FK_KhachHangPhieuGiam_PhieuGiamGia_IdPhieuGiam
FOREIGN KEY (IdPhieuGiam) REFERENCES PhieuGiamGia(Id)
-- San Pham dơt giam
ALTER TABLE SanPhamDotGiam ADD CONSTRAINT FK_SanPhamDotGiam_SanPhamChiTiet_IdChiTietSanPham
FOREIGN KEY (IdChiTietSanPham) REFERENCES SanPhamChiTiet(Id)
ALTER TABLE SanPhamDotGiam ADD CONSTRAINT FK_SanPhamDotGiam_DotGiamGia_IdDotGiam
FOREIGN KEY (IdDotGiam) REFERENCES DotGiamGia(Id)
--Khach hang
ALTER TABLE KhachHang ADD CONSTRAINT FK_KhachHang_NguoiDung_IdNguoiDung
FOREIGN KEY (IdNguoiDung) REFERENCES NguoiDung(Id)
--LS hoa don
ALTER TABLE LichSuHoaDon ADD CONSTRAINT FK_LSHoaDon_HoaDon_IdHoaDon
FOREIGN KEY (IdHoaDon) REFERENCES HoaDon(Id)
ALTER TABLE LichSuHoaDon ADD CONSTRAINT FK_LSHoaDon_NhanVien_IdNhanVien
FOREIGN KEY (IdNhanVien) REFERENCES NhanVien(Id)
--Gio hang chi tiet
ALTER TABLE GioHangChiTiet ADD CONSTRAINT FK_GioHangChiTiet_SPCT_IdSPCT
FOREIGN KEY (IdSanPhamChiTiet) REFERENCES SanPhamChiTiet(Id)
ALTER TABLE GioHangChiTiet ADD CONSTRAINT FK_GioHangChiTiet_GioHang_IdGioHang
FOREIGN KEY (IdGioHang) REFERENCES GioHang(Id)
--Gio hang
ALTER TABLE GioHang ADD CONSTRAINT FK_GioHang_KhachHang_IdKhachHang
FOREIGN KEY (IdKhachHang) REFERENCES KhachHang(Id)
--Phuong thuc thanh toan
ALTER TABLE PhuongThucThanhToan ADD CONSTRAINT FK_PhuongThucThanhToan_HoaDon_IdHoaDon
FOREIGN KEY (IdHoaDon) REFERENCES HoaDon(Id)
--Hoa don
ALTER TABLE HoaDon ADD CONSTRAINT FK_HoaDon_NhanVien_IdNHanVien
FOREIGN KEY (IdNHanVien) REFERENCES NhanVien(Id)
ALTER TABLE HoaDon ADD CONSTRAINT FK_HoaDon_KhachHang_IdKhachHang
FOREIGN KEY (IdKhachHang) REFERENCES KhachHang(Id)
--Anh
ALTER TABLE Anh ADD CONSTRAINT FK_Anh_SPCT_IdSPCT
FOREIGN KEY (IdSanPhamChiTiet) REFERENCES SanPhamChiTiet(Id)
--SPCT
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_SanPham_IdSanPham
FOREIGN KEY (IdSanPham) REFERENCES SanPham(Id)
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_KichCo_IdKichCo
FOREIGN KEY (IdKichCo) REFERENCES KichCo(Id)
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_HangGiay_IdHang
FOREIGN KEY (IdThuongHieu) REFERENCES ThuongHieu(Id)
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_MauSac_IdMauSac
FOREIGN KEY (IdMauSac) REFERENCES MauSac(Id)
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_ChatLieu_IdChatLieu
FOREIGN KEY (IdChatLieu) REFERENCES ChatLieu(Id)
ALTER TABLE SanPhamChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_DeGiay_IdDeGiay
FOREIGN KEY (IdDeGiay) REFERENCES DeGiay(Id)
--Hoa don chi tiet
ALTER TABLE HoaDonChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_HoaDon_IdHoaDon
FOREIGN KEY (IdHoaDon) REFERENCES HoaDon(Id)
ALTER TABLE HoaDonChiTiet ADD CONSTRAINT FK_SanPhamChiTiet_SPCT_IdSPCT
FOREIGN KEY (IdSanPhamChiTiet) REFERENCES SanPhamChiTiet(Id)
--Phieu giam gia chi tiet
ALTER TABLE PhieuGiamGiaChiTiet ADD CONSTRAINT FK_PhieuGiamGiaChiTiet_HoaDon_IdHoaDon
FOREIGN KEY (IdHoaDon) REFERENCES HoaDon(Id)
ALTER TABLE PhieuGiamGiaChiTiet ADD CONSTRAINT FK_PhieuGiamGiaChiTiet_PhieuGiamGia_IdMaGiamGia
FOREIGN KEY (IdMaGiamGia) REFERENCES PhieuGiamGia(Id)
--San pham tra hang
ALTER TABLE SanPhamTraHang ADD CONSTRAINT FK_SPTH_SPCT_IdSPCT
FOREIGN KEY (IdSanPhamChiTiet) REFERENCES SanPhamChiTiet(Id)
--Nhan Vien
ALTER TABLE NhanVien ADD CONSTRAINT FK_NhanVien_NguoiDung_IdNguoiDung
FOREIGN KEY (IdNguoiDung) REFERENCES NguoiDung(Id)
GO
INSERT INTO NguoiDung (TaiKhoan, MatKhau, Email, HoVaTen, NgaySinh, Cccd, SoDienThoai, GioiTinh, Anh, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
('anhnv1', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'anhnv1@gmail.com', N'Nguyễn Văn Anh', '2000-01-01', '024303011234', '0395874692', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('quanth2', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'quanth2@gmail.com', N'Trịnh Hoàng Quân', '2001-02-02', '024303011256', '0395874147', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('hoangnv3', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'hoangnv3@gmail.com', N'Nguyễn Việt Hoàng', '2002-03-03', '024303011278', '0395874258', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('hadt4', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'hadt4@gmail.com', N'Đỗ Thu Hà', '2003-04-04', '024303011289', '0395874369', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('anhht5', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'anhht5@gmail.com', N'Hoàng Thị Ánh', '2004-05-05', '024303011286', '0395874159', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('huongdt6', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'huongdt6@gmail.com', N'Đồng Thu Hương', '2000-06-06', '024303011257', '0395874496', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('quynhnn7', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'quynhnn7@gmail.com', N'Nguyễn Như Quỳnh', '2001-07-07', '024303011249', '0395874756', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('hoatt8', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'hoatt8@gmail.com', N'Trương Thị Hoa', '2002-08-08', '024303011205', '0395874798', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('dungnt9', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'dungnt9@gmail.com', N'Nguyễn Tiến Dũng', '2003-09-09', '024303011295', '0395874459', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('ngadt10', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'ngadt10@gmail.com', N'Đặng Thúy Nga', '2001-10-10', '024303011284', '0395874436', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user11', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'anhnt@gmail.com', N'Nguyễn Tuấn Anh', '2001-04-07', '024303011235', '0395874102', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user12', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'anhhn@gmail.com', N'Hoàng Ngọc Anh', '2002-07-05', '024303011495', '0395874983', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user13', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'anhpt@gmail.com', N'Phùng Thị Ánh', '2004-08-07', '024303011486', '0395874106', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user14', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'hoangdv@gmail.com', N'Đỗ Việt Hoàng', '2000-10-05', '024303011499', '0395874258', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user15', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'chauna@gmail.com', N'Nguyễn Anh Châu', '2001-12-01', '024303011497', '0395874159', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user16', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'tungdd@gmail.com', N'Đỗ Duy Tùng', '2003-09-07', '024303011456', '0395874148', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user17', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'thuydt@gmail.com', N'Đồng Thanh Thúy', '2004-04-03', '024303011455', '0395874329', 0, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user18', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'dunght@gmail.com', N'Hoàng Tuấn Dũng', '2002-09-05', '024303011425', '0395874628', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user19', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'lamtv@gmail.com', N'Trịnh Văn Lâm', '2000-11-01', '024303011400', '0395870159', 1, '/upload\a1.jpg', N'Admin', N'Admin',1),
('user20', '$2a$10$XzdDIIsUVAib6fAxFhwiMeJlyL2B1gFhkWsQwogvo6fZnRbynMpHS', 'tamnt@gmail.com', N'Nguyễn Thúy Tâm', '2001-07-01', '024303011185', '0395873257', 0, '/upload\a1.jpg', N'Admin', N'Admin',1);
GO
INSERT INTO KhachHang (IdNguoiDung, MaKhachHang, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
(11,'KH2U1QSXE', N'Admin', N'Admin',1),
(12,'KH3U2QSXE', N'Admin', N'Admin',1),
(13,'KH3U4QSXE', N'Admin', N'Admin',1),
(14,'KH4U5QSXE', N'Admin', N'Admin',1),
(15,'KH5U6QSXE', N'Admin', N'Admin',1),
(16,'KH6U7QSXE', N'Admin', N'Admin',1),
(17,'KH7U8QSXE', N'Admin', N'Admin',1),
(18,'KH8U0QSXE', N'Admin', N'Admin',1),
(19,'KH9U1QSXE', N'Admin', N'Admin',1),
(20,'KH10UQSXE', N'Admin', N'Admin',1);
GO
INSERT INTO NhanVien (IdNguoiDung, VaiTro,MaNhanVien, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
(1, 1,'NV5S6FIRM',N'Admin', N'Admin',1),
(2, 1,'NVT5TDVXZ', N'Admin', N'Admin',1),
(3, 0,'NVHUSKF56', N'Admin', N'Admin',1),
(4, 1,'NVTQGH3GT', N'Admin', N'Admin',1),
(5, 1,'NVNDUCTB4', N'Admin', N'Admin',1),
(6, 0,'NV3MNFTHS', N'Admin', N'Admin',1),
(7, 0,'NV9OHSNTE', N'Admin', N'Admin',1),
(8, 1,'NVM3UJYRH', N'Admin', N'Admin',1),
(9, 1,'NVHQU3X6F', N'Admin', N'Admin',1),
(10, 0,'NVSKE9YRF', N'Admin', N'Admin',1);
GO
INSERT INTO DiaChi (IdNguoiDung, TenDuong, XaPhuong, QuanHuyen, TinhThanhPho, SdtNguoiNhan, HoTenNguoiNhan, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
(1, N'Trần Hưng Đạo1', N'Xã Hùng Lô', N'Thành phố Việt Trì', N'Tỉnh Phú Thọ', '0123456789', N'Nguyễn Văn A', N'Admin', N'Admin',1),
(2, N'Nguyễn Huệ1', N'Phường Tân Long', N'Thành phố Thái Nguyên', N'Tỉnh Thái Nguyên', '0987654321', N'Trần Thị B', N'Admin', N'Admin',1),
(3, N'Lê Lợi1', N'Xã Nậm Vì', N'Huyện Mường Nhé', N'Tỉnh Điện Biên', '0123456789', N'Lê Văn C', N'Admin', N'Admin',1),
(4, N'Tôn Đức Thắng1', N'Phường Vạn An', N'Thành phố Bắc Ninh', N'Tỉnh Bắc Ninh', '0987654321', N'Phạm Thị D', N'Admin', N'Admin',1),
(5, N'Nguyễn Thị Minh Khai1', N'Phường Nam Viêm', N'Thành phố Phúc Yên', N'Tỉnh Vĩnh Phúc', '0123456789', N'Lê Thị E', N'Admin', N'Admin',1),
(6, N'Lê Lợi1', N'Xã Đồng Việt', N'Huyện Yên Dũng', N'Tỉnh Bắc Giang', '0987654321', N'Nguyễn Văn F', N'Admin', N'Admin',1),
(7, N'Lê Lợi1', N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0123456789', N'Lê Thị G', N'Admin', N'Admin',1),
(8, N'Nguyễn Huệ1',N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0987654321', N'Trần Văn H', N'Admin', N'Admin',1),
(9, N'Nguyễn Thị Minh Khai1', N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0123456789', N'Phạm Thị I', N'Admin', N'Admin',1),
(10, N'Tôn Đức Thắng1',N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0987654321', N'Nguyễn Văn K', N'Admin', N'Admin',1),
(11, N'Trần Hưng Đạo', N'Xã Hùng Lô', N'Thành phố Việt Trì', N'Tỉnh Phú Thọ', '0123456789', N'Nguyễn Văn A', N'Admin', N'Admin',1),
(12, N'Nguyễn Huệ', N'Phường Tân Long', N'Thành phố Thái Nguyên', N'Tỉnh Thái Nguyên', '0987654321', N'Trần Thị B', N'Admin', N'Admin',1),
(13, N'Lê Lợi', N'Xã Nậm Vì', N'Huyện Mường Nhé', N'Tỉnh Điện Biên', '0123456789', N'Lê Văn C', N'Admin', N'Admin',1),
(14, N'Tôn Đức Thắng', N'Phường Vạn An', N'Thành phố Bắc Ninh', N'Tỉnh Bắc Ninh', '0987654321', N'Phạm Thị D', N'Admin', N'Admin',1),
(15, N'Nguyễn Thị Minh Khai', N'Phường Nam Viêm', N'Thành phố Phúc Yên', N'Tỉnh Vĩnh Phúc', '0123456789', N'Lê Thị E', N'Admin', N'Admin',1),
(16, N'Lê Lợi', N'Xã Đồng Việt', N'Huyện Yên Dũng', N'Tỉnh Bắc Giang', '0987654321', N'Nguyễn Văn F', N'Admin', N'Admin',1),
(17, N'Lê Lợi', N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0123456789', N'Lê Thị G', N'Admin', N'Admin',1),
(18, N'Nguyễn Huệ', N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0987654321', N'Trần Văn H', N'Admin', N'Admin',1),
(19, N'Nguyễn Thị Minh Khai', N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0123456789', N'Phạm Thị I', N'Admin', N'Admin',1),
(20, N'Tôn Đức Thắng',N'Xã Thường Thắng', N'Huyện Hiệp Hòa', N'Tỉnh Bắc Giang', '0987654321', N'Nguyễn Văn K', N'Admin', N'Admin',1);
GO
INSERT INTO SanPham (QRCode,MaSanPham, TenSanPham, NguoiTao, NguoiCapNhat)
VALUES
('SP001',N'SPURJSJP5', N'Giày thể thao Nike Air Max', N'Admin', N'Admin'),
('SP002',N'SP5S6FIRM', N'Giày cao cổ Adidas Superstar', N'Admin', N'Admin'),
('SP003',N'SPU3S6FGW', N'Giày chạy bộ Asics Gel-Kayano', N'Admin', N'Admin'),
('SP004',N'SPCD85FAS', N'Giày công sở Clarks Originals', N'Admin', N'Admin'),
('SP005',N'SPE77RDGD', N'Giày đế vuông Gucci Horsebit', N'Admin', N'Admin'),
('SP006',N'SPR1D4PMN', N'Giày thể thao Puma Suede Classic', N'Admin', N'Admin'),
('SP007',N'SPT5TDVXZ', N'Giày sandal Birkenstock Arizona', N'Admin', N'Admin'),
('SP008',N'SPHIY5DNV', N'Giày lười Salvatore Ferragamo', N'Admin', N'Admin'),
('SP009',N'SPLN6FG5S',N'Giày boot Timberland 6-Inch Premium', N'Admin', N'Admin'),
('SP010',N'SPO5DFPN6',N'Giày thể thao New Balance 574', N'Admin', N'Admin');
GO
INSERT INTO KichCo (Ten, NguoiTao, NguoiCapNhat)
VALUES
(N'35', N'Admin', N'Admin'),
(N'36', N'Admin', N'Admin'),
(N'37', N'Admin', N'Admin'),
(N'38', N'Admin', N'Admin'),
(N'39', N'Admin', N'Admin'),
(N'40', N'Admin', N'Admin'),
(N'41', N'Admin', N'Admin'),
(N'42', N'Admin', N'Admin'),
(N'43', N'Admin', N'Admin'),
(N'44', N'Admin', N'Admin');
GO
INSERT INTO ThuongHieu(Ten, NguoiTao, NguoiCapNhat)
VALUES
(N'Nike', N'Admin', N'Admin'),
(N'Adidas', N'Admin', N'Admin'),
(N'Asics', N'Admin', N'Admin'),
(N'Clarks', N'Admin', N'Admin'),
(N'Gucci', N'Admin', N'Admin'),
(N'Puma', N'Admin', N'Admin'),
(N'Birkenstock', N'Admin', N'Admin'),
(N'Salvatore Ferragamo', N'Admin', N'Admin'),
(N'Timberland', N'Admin', N'Admin'),
(N'New Balance', N'Admin', N'Admin');
GO
INSERT INTO MauSac (Ten, NguoiTao, NguoiCapNhat)
VALUES
(N'#2e53c2', N'Admin', N'Admin'),
(N'#f76808', N'Admin', N'Admin'),
(N'#f50505', N'Admin', N'Admin'),
(N'#030303', N'Admin', N'Admin'),
(N'#77e92b', N'Admin', N'Admin'),
(N'#288a6a', N'Admin', N'Admin'),
(N'#0eccfb', N'Admin', N'Admin'),
(N'#654206', N'Admin', N'Admin'),
(N'#d1236f', N'Admin', N'Admin'),
(N'#003b99', N'Admin', N'Admin');
GO
INSERT INTO ChatLieu (Ten, NguoiTao, NguoiCapNhat)
VALUES
(N'Da tổng hợp', N'Admin', N'Admin'),
(N'Da bò', N'Admin', N'Admin'),
(N'Lụa', N'Admin', N'Admin'),
(N'Canvas', N'Admin', N'Admin'),
(N'Suede', N'Admin', N'Admin'),
(N'Vải', N'Admin', N'Admin'),
(N'Nhựa', N'Admin', N'Admin'),
(N'Vải dù', N'Admin', N'Admin'),
(N'Nỉ', N'Admin', N'Admin'),
(N'Nylon', N'Admin', N'Admin');
GO
INSERT INTO DeGiay (Ten, NguoiTao, NguoiCapNhat)
VALUES
(N'Super Soft Ground', N'Admin', N'Admin'),
(N'Soft Ground', N'Admin', N'Admin'),
(N'Artificial Ground', N'Admin', N'Admin'),
(N'Turf', N'Admin', N'Admin'),
(N'Hard Ground', N'Admin', N'Admin'),
(N'Indoor Court', N'Admin', N'Admin'),
(N'Multi Ground', N'Admin', N'Admin'),
(N'Soft Ground Pro', N'Admin', N'Admin'),
(N'Firm Ground', N'Admin', N'Admin'),
(N'Hard Ground', N'Admin', N'Admin');
GO
INSERT INTO SanPhamChiTiet (IdSanPham, IdKichCo, IdThuongHieu, IdMauSac, IdChatLieu, IdDeGiay, QRCode,MaSanPhamChiTiet,SoLuong, MoTa, GioiTinh, GiaTien, NguoiTao, NguoiCapNhat)
VALUES
(1, 1,1, 1, 1, 1, 'ABC124','ABC124',100, N'Mô tả sản phẩm 1', 1, 500000, N'Admin', N'Admin'),
(2, 2 ,2, 2, 2, 2, 'SPCTDEF456H','SPCTDEF456H',100, N'Mô tả sản phẩm 2', 0, 600000, N'Admin', N'Admin'),
(3, 3,3, 3, 3, 3, 'SPCTGHI789F','SPCTGHI789F',100, N'Mô tả sản phẩm 3', 1, 700000, N'Admin', N'Admin'),
(4, 4,4, 4, 4, 4, 'SPCTJKL012F','SPCTJKL012F',100, N'Mô tả sản phẩm 4', 0, 800000, N'Admin', N'Admin'),
(5, 5, 5, 5, 5, 5, 'SPCTMNO345F','SPCTMNO345F',100, N'Mô tả sản phẩm 5', 1, 900000, N'Admin', N'Admin'),
(6, 6, 6, 6, 6, 6, 'SPCTPQR678J','SPCTPQR678J',100, N'Mô tả sản phẩm 6', 0, 1000000, N'Admin', N'Admin'),
(7, 7, 7, 7, 7, 7, 'SPCTSTU901U','SPCTSTU901U',10, N'Mô tả sản phẩm 7', 1, 1100000, N'Admin', N'Admin'),
(8, 8, 8, 8, 8, 8, 'SPCTVWX234L','SPCTVWX234L',100, N'Mô tả sản phẩm 8', 0, 1200000, N'Admin', N'Admin'),
(9, 9, 9, 9, 9, 9, 'SPCTYZA567M','SPCTYZA567M',10, N'Mô tả sản phẩm 9', 1, 1300000, N'Admin', N'Admin'),
(10, 10, 10, 10, 10, 10, 'SPCTBCD890K','SPCTBCD890K',100, N'Mô tả sản phẩm 10', 0, 1400000, N'Admin', N'Admin');
GO
INSERT INTO Anh (IdSanPhamChiTiet, TenAnh, NguoiTao, NguoiCapNhat)
VALUES
(1, '/upload/sanpham1.1_adidas.jpg', N'Admin', N'Admin'),
(2, '/upload/sanpham2.1_adidas.jpg', N'Admin', N'Admin'),
(3, '/upload/sanpham3.1_adidas.jpg', N'Admin', N'Admin'),
(4, '/upload/sanpham4.1_adidas.jpg', N'Admin', N'Admin'),
(5, '/upload/sanpham5.1_adidas.jpg', N'Admin', N'Admin'),
(6, '/upload/sanpham1.1_jogarbola.jpg', N'Admin', N'Admin'),
(7, '/upload/sanpham2.1_jogarbola.jpg', N'Admin', N'Admin'),
(8, '/upload/sanpham3.1_nikes.jpg', N'Admin', N'Admin'),
(9, '/upload/sanpham4.1_nikes.jpg', N'Admin', N'Admin'),
(10,'/upload/sanpham5.1_nikes.jpg', N'Admin', N'Admin');
GO
INSERT INTO PhieuGiamGia (MaCode, TenPhieu, GiaTriGiamToiDa, GiaTriGiam, DonToiThieu, SoLuong, LoaiPhieu,KieuPhieu, NgayBatDau, NgayKetThuc, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
('CODE001', N'Phiếu giảm giá 1', 200000, 10, 200000, 100, 1, 1, '2024-03-01', '2024-03-31', N'Admin', N'Admin',2),
('CODE002', N'Phiếu giảm giá 2', null, 100000, 300000, 200, 0,0, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE003', N'Phiếu giảm giá 3', null, 150000, 400000, 300, 0,0, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE004', N'Phiếu giảm giá 4', 300000, 10, 1000000, 400, 1,0, '2024-03-01', '2026-04-30', N'Admin', N'Admin',1),
('CODE005', N'Phiếu giảm giá 5', null, 250000, 600000, 500, 0,0, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE006', N'Phiếu giảm giá 6', null, 300000, 700000, 600, 0,0, '2024-03-01', '2025-04-30', N'Admin', N'Admin',1),
('CODE007', N'Phiếu giảm giá 7', null, 350000, 800000, 700, 0,0, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE008', N'Phiếu giảm giá 8', null, 400000, 900000, 800, 0,0, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE009', N'Phiếu giảm giá 9', null, 450000, 1000000, 900, 0,1, '2024-03-01', '2024-04-30', N'Admin', N'Admin',2),
('CODE010', N'Phiếu giảm giá 10', null, 500000, 1100000, 1000, 0,0, '2024-04-15', '2025-04-30', N'Admin', N'Admin',1);
GO
INSERT INTO DotGiamGia(TenDot, GiaTriGiam, NgayBatDau, NgayKetThuc, NguoiTao, NguoiCapNhat,TrangThai)
VALUES
(N'Đợt giảm giá 1', 10, '2024-03-01', '2024-03-31', N'Admin', N'Admin',2),
(N'Đợt giảm giá 2', 15, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 3', 17, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 4', 18, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 5', 13, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 6', 20, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 7', 21, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 8', 22, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 9', 23, '2024-03-01', '2024-05-30', N'Admin', N'Admin',1),
(N'Đợt giảm giá 10', 25, '2024-04-18', '2024-05-30', N'Admin', N'Admin',0);
GO
INSERT INTO HoaDon (IdNhanVien, IdKhachHang, HoaToc, Sdt, Mahoadon, DiaChi, Email, TongTien, NgayXacNhan, NgayVanChuyen, LoaiHoaDon, PhiVanChuyen, NguoiTao, NguoiCapNhat, GhiChu,TrangThai,TenNguoiNhan)
VALUES
(1, 1, 0, '0123456789', N'HDFSPORT55676', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email1@example.com', 1000000, '2024-03-19 08:00:00', '2024-03-19 10:00:00', 1, 50000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 1',0,N'Dương Thanh Tùng'),
(2, 2, 0, '0987654321', N'HDFSPORT55677', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email2@example.com', 1500000, '2024-03-19 09:00:00', '2024-03-19 11:00:00', 1, 60000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 2',0,N'Dương Thanh Tùng'),
(3, 3, 0, '0123456789', N'HDFSPORT55678', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email3@example.com', 2000000, '2024-03-19 10:00:00', '2024-03-19 12:00:00', 1, 70000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 3',0,N'Dương Thanh Tùng'),
(4, 4, 0, '0987654321', N'HDFSPORT55679', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email4@example.com', 2500000, '2024-03-19 11:00:00', '2024-03-19 13:00:00', 1, 80000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 4',0,N'Dương Thanh Tùng'),
(5, 5, 0, '0123456789', N'HDFSPORT55680', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email5@example.com', 3000000, '2024-03-19 12:00:00', '2024-03-19 14:00:00', 1, 90000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 5',0,N'Dương Thanh Tùng'),
(6, 6, 0, '0987654321', N'HDFSPORT55681', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email6@example.com', 3500000, '2024-03-19 13:00:00', '2024-03-19 15:00:00', 1, 100000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 6',0,N'Dương Thanh Tùng'),
(7, 7, 0, '0123456789', N'HDFSPORT55682', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email7@example.com', 4000000, '2024-03-19 14:00:00', '2024-03-19 16:00:00', 1, 110000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 7',0,N'Dương Thanh Tùng'),
(8, 8, 0, '0987654321', N'HDFSPORT55683', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email8@example.com', 4500000, '2024-03-19 15:00:00', '2024-03-19 17:00:00', 1, 120000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 8',0,N'Dương Thanh Tùng'),
(9, 9, 0, '0123456789', N'HDFSPORT55684', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email9@example.com', 5000000, '2024-03-19 16:00:00', '2024-03-19 18:00:00', 1, 130000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 9',0,N'Dương Thanh Tùng'),
(10,10, 0, '0987654321', N'HDFSPORT55685', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email10@example.com', 5500000, '2024-03-19 17:00:00', '2024-03-19 19:00:00', 1, 140000, N'Admin', N'Admin', N'Ghi chú cho hóa đơn 10',0,N'Dương Thanh Tùng')
GO
INSERT INTO PhuongThucThanhToan (IdHoaDon, TenPhuongThuc, MoTa, TongTien, MaGiaoDichVnPay, NguoiTao, NguoiCapNhat)
VALUES
(1, N'Trả trước', N'chuyển khoản', 500000, 'ABC123', N'Admin', N'Admin'),
(2, N'Trả sau', N'chuyển khoản', 600000, 'DEF456', N'Admin', N'Admin'),
(3, N'Trả sau', N'chuyển khoản', 700000, 'GHI789', N'Admin', N'Admin'),
(4, N'Trả trước', N'chuyển khoản', 800000, 'JKL012', N'Admin', N'Admin'),
(5, N'Trả sau', N'tiền mặt', 900000, 'MNO345', N'Admin', N'Admin'),
(6, N'Trả trước', N'tiền mặt', 1000000, 'PQR678', N'Admin', N'Admin'),
(7, N'Trả sau', N'tiền mặt', 1100000, 'STU901', N'Admin', N'Admin'),
(8, N'Trả trước', N'tiền mặt', 1200000, 'VWX234', N'Admin', N'Admin'),
(9, N'Trả sau', N'tiền mặt', 1300000, 'YZA567', N'Admin', N'Admin'),
(10, N'Trả trước', N'tiền mặt', 1400000, 'BCD890', N'Admin', N'Admin');
GO
INSERT INTO HoaDonChiTiet (IdHoaDon, IdSanPhamChiTiet, GiaSanPham, SoLuong, GhiChu,TrangThai)
VALUES
(1, 1, 500000, 1, NULL,1),
(2, 2, 600000, 2, NULL,1),
(3, 3, 700000, 3, NULL,1),
(4, 4, 800000, 4, NULL,1),
(5, 5, 900000, 5, NULL,1),
(6, 6, 1000000, 6, NULL,1),
(7, 7, 1100000, 7, NULL,1),
(8, 8, 1200000, 8, NULL,1),
(9, 9, 1300000, 9, NULL,1),
(10, 10, 1400000, 10, NULL,1);
GO
INSERT INTO KhachHangPhieuGiam (IdKhachHang, IdPhieuGiam)
VALUES
(1, 1),
(2, 9);
GO
INSERT INTO PhieuGiamGiaChiTiet (IdHoaDon, IdMaGiamGia, GiaBanDau, GiaSauApDung, TienGiam, NguoiTao, NguoiCapNhat)
VALUES
(1, 1, 1000000, 900000, 100000, N'Admin', N'Admin'),
(2, 2, 1200000, 1100000, 100000, N'Admin', N'Admin'),
(3, 3, 1500000, 1300000, 200000, N'Admin', N'Admin'),
(4, 4, 800000, 750000, 50000, N'Admin', N'Admin'),
(5, 5, 2000000, 1900000, 100000, N'Admin', N'Admin'),
(6, 6, 600000, 550000, 50000, N'Admin', N'Admin'),
(7, 7, 750000, 700000, 50000, N'Admin', N'Admin'),
(8, 8, 900000, 850000, 50000, N'Admin', N'Admin'),
(9, 9, 1100000, 1000000, 100000, N'Admin', N'Admin'),
(10, 10, 1400000, 1300000, 100000, N'Admin', N'Admin');
GO
INSERT INTO LichSuHoaDon (IdHoaDon, IdNhanVien, NguoiTao, NguoiCapNhat,GhiChu,TrangThai)
VALUES
(1, 1, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(1, 1, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',1),
(1, 1, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',4),
(1, 1, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',5),
(2, 2, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(3, 3, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(4, 4, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(5, 5, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(6, 6, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(7, 7, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(8, 8, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(9, 9, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(10, 10, N'Admin', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0);
GO
INSERT INTO HoaDon (IdNhanVien, IdKhachHang, HoaToc, Sdt, Mahoadon, DiaChi, Email, TongTien, NgayXacNhan, NgayVanChuyen, NgayNhanHang, NgayGiaoDuKien, NgayHoanThanh, LoaiHoaDon, PhiVanChuyen, NgayTao, NguoiTao, LanCapNhatCuoi, NguoiCapNhat, GhiChu,TrangThai,TenNguoiNhan)
VALUES
(1, 1, 0, '0324587635', N'HDFSPORT55686', N'Địa chỉ 1, Xã Nậm Vì, Huyện Mường Nhé, Tỉnh Điện Biên', 'email01@example.com', 3200000, '2024-07-24 20:11:14.563', '2024-07-24 20:11:38.357', '2024-07-24 20:11:41.743', '2024-07-28 06:59:59.000', '2024-07-24 20:11:44.880', 0, 0, '2024-07-24 20:10:27.747', N'Admin', '2024-07-24 20:11:44.880', N'Admin', N'Ghi chú cho hóa đơn 11',5,N'Dương Thanh Tùng')
INSERT INTO HoaDonChiTiet (IdHoaDon, IdSanPhamChiTiet, GiaSanPham, SoLuong, GhiChu,TrangThai)
VALUES
(11, 3, 700000, 1, NULL,1),
(11, 4, 800000, 2, NULL,1),
(11, 5, 900000, 1, NULL,1)
INSERT INTO PhuongThucThanhToan (IdHoaDon, TenPhuongThuc, MoTa, TongTien, MaGiaoDichVnPay, NgayTao, NguoiTao, LanCapNhatCuoi, NguoiCapNhat, TrangThai)
VALUES
(11, N'Trả trước', N'Tiền mặt', 3200000, 'N/A', '2024-07-24 20:11:09.047', N'Admin', '2024-07-24 20:11:09.047', N'Admin', 1)
INSERT INTO LichSuHoaDon (IdHoaDon, IdNhanVien, NgayTao, NguoiTao, LanCapNhatCuoi, NguoiCapNhat,GhiChu,TrangThai)
VALUES
(11, 1, '2024-07-24 20:11:14.577', N'Admin', '2024-07-24 20:11:14.577', N'Admin',N'Đơn đã được đặt và đang chờ xác nhận',0),
(11, 1, '2024-07-24 20:11:14.577', N'Admin', '2024-07-24 20:11:14.577', N'Admin',N'Đơn đã được xác nhận',1),
(11, 1, '2024-07-24 20:11:33.000', N'Admin', '2024-07-24 20:11:33.000', N'Admin',N'Đơn đang chờ giao hàng',2),
(11, 1, '2024-07-24 20:11:38.000', N'Admin', '2024-07-24 20:11:38.000', N'Admin',N'Đơn đang giao hàng',3),
(11, 1, '2024-07-24 20:11:41.000', N'Admin', '2024-07-24 20:11:41.000', N'Admin',N'Đơn đã thanh toán',4),
(11, 1, '2024-07-24 20:11:44.000', N'Admin', '2024-07-24 20:11:44.000', N'Admin',N'Đơn đã được hoàn thành',5)
INSERT INTO GioHang (IdKhachHang)
VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10);
GO
INSERT INTO GioHangChiTiet (IdSanPhamChiTiet, IdGioHang, SoLuong)
VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 3),
(4, 4, 1),
(5, 5, 2),
(6, 6, 1),
(7, 7, 2),
(8, 8, 3),
(9, 9, 1),
(10, 10, 2);
GO
INSERT INTO SanPhamTraHang (IdSanPhamChiTiet, SoLuong, NguoiTao, NgayTao, GhiChu, ThoiGianToiThieu)
VALUES
(1, 2, N'Admin', GETDATE(), NULL, NULL),
(2, 1, N'Admin', GETDATE(), NULL, NULL),
(3, 3, N'Admin', GETDATE(), NULL, NULL),
(4, 1, N'Admin', GETDATE(), NULL, NULL),
(5, 2, N'Admin', GETDATE(), NULL, NULL),
(6, 1, N'Admin', GETDATE(), NULL, NULL),
(7, 2, N'Admin', GETDATE(), NULL, NULL),
(8, 3, N'Admin', GETDATE(), NULL, NULL),
(9, 1, N'Admin', GETDATE(), NULL, NULL),
(10, 2, N'Admin', GETDATE(), NULL, NULL);


