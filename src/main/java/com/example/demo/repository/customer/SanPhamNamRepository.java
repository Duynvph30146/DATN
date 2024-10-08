package com.example.demo.repository.customer;

import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamNamRepository extends JpaRepository<SanPham, Integer> {
    // sản phẩm nam
    @Query(nativeQuery = true, value = """
                WITH AnhDaiDien AS (
                    SELECT spct.IdSanPham, anh.tenanh,
                           ROW_NUMBER() OVER (PARTITION BY spct.IdSanPham ORDER BY anh.tenanh DESC) AS row_num
                    FROM Anh anh
                    JOIN SanPhamChiTiet spct ON anh.IdSanPhamChiTiet = spct.id
                    WHERE spct.trangthai = 1
                ),
                SanPhamChiTietGrouped AS (
                    SELECT IdSanPham, SUM(soluong) AS tongSoLuong, MIN(giatien) AS giatien
                    FROM SanPhamChiTiet
                    WHERE gioitinh = 1 AND trangthai = 1
                    GROUP BY IdSanPham
                ),
                KichCoCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT kc.id) AS soLuongKichCo
                    FROM SanPhamChiTiet spct
                    JOIN KichCo kc ON spct.IdKichCo = kc.id
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                MauSacCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT ms.id) AS soLuongMauSac
                    FROM SanPhamChiTiet spct
                    JOIN MauSac ms ON spct.IdMauSac = ms.id
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                GiamGia AS (
                    SELECT spct.IdSanPham, MAX(dg.giatrigiam) AS giaGiam
                    FROM SanPhamChiTiet spct
                    JOIN SanPhamDotGiam spdg ON spct.Id = spdg.idchitietsanpham
                    JOIN DotGiamGia dg ON spdg.iddotgiam = dg.id
                    WHERE dg.trangthai = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                )
                SELECT sp.id, sp.tensanpham, sp.ngaytao, spctg.tongSoLuong, sp.trangthai, spctg.giatien, anhdd.tenanh, kc.soLuongKichCo, ms.soLuongMauSac, COALESCE(giamgia.giaGiam, 0) AS giaGiam
                FROM SanPham sp
                JOIN SanPhamChiTietGrouped spctg ON sp.id = spctg.IdSanPham
                JOIN AnhDaiDien anhdd ON sp.id = anhdd.IdSanPham AND anhdd.row_num = 1
                JOIN KichCoCount kc ON sp.id = kc.IdSanPham
                JOIN MauSacCount ms ON sp.id = ms.IdSanPham
                LEFT JOIN GiamGia giamgia ON sp.id = giamgia.IdSanPham
                ORDER BY sp.ngaytao DESC, spctg.tongSoLuong DESC
            """)
    List<Object[]> findProductsGioiTinh1();


    // sắp xếp tăng dần theo giá của sp nam
    @Query(nativeQuery = true, value = """
                WITH AnhDaiDien AS (
                    SELECT spct.IdSanPham, anh.tenanh,
                           ROW_NUMBER() OVER (PARTITION BY spct.IdSanPham ORDER BY anh.tenanh DESC) AS row_num
                    FROM Anh anh
                    JOIN SanPhamChiTiet spct ON anh.IdSanPhamChiTiet = spct.id
                    WHERE spct.trangthai = 1
                ),
                SanPhamChiTietGrouped AS (
                    SELECT IdSanPham, SUM(soluong) AS tongSoLuong, MIN(giatien) AS giatien
                    FROM SanPhamChiTiet
                    WHERE gioitinh = 1 AND trangthai = 1
                    GROUP BY IdSanPham
                ),
                KichCoCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT kc.id) AS soLuongKichCo
                    FROM SanPhamChiTiet spct
                    JOIN KichCo kc ON spct.IdKichCo = kc.id
                    WHERE spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                MauSacCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT ms.id) AS soLuongMauSac
                    FROM SanPhamChiTiet spct
                    JOIN MauSac ms ON spct.IdMauSac = ms.id
                    WHERE spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                GiamGia AS (
                    SELECT spct.IdSanPham, MAX(dg.giatrigiam) AS giaGiam
                    FROM SanPhamChiTiet spct
                    JOIN SanPhamDotGiam spdg ON spct.Id = spdg.idchitietsanpham
                    JOIN DotGiamGia dg ON spdg.iddotgiam = dg.id
                    WHERE dg.trangthai = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                )
                SELECT sp.id, sp.tensanpham, sp.ngaytao, spctg.tongSoLuong, sp.trangthai, spctg.giatien, anhdd.tenanh, kc.soLuongKichCo, ms.soLuongMauSac, COALESCE(giamgia.giaGiam, 0) AS giaGiam
                FROM SanPham sp
                JOIN SanPhamChiTietGrouped spctg ON sp.id = spctg.IdSanPham
                JOIN AnhDaiDien anhdd ON sp.id = anhdd.IdSanPham AND anhdd.row_num = 1
                JOIN KichCoCount kc ON sp.id = kc.IdSanPham
                JOIN MauSacCount ms ON sp.id = ms.IdSanPham
                LEFT JOIN GiamGia giamgia ON sp.id = giamgia.IdSanPham
                ORDER BY spctg.giatien ASC, spctg.tongSoLuong ASC
            """)
    List<Object[]> loctangdan();


    //sắp xếp giảm dần theo giá của sp nam
    @Query(nativeQuery = true, value = """
                WITH AnhDaiDien AS (
                    SELECT spct.IdSanPham, anh.tenanh,
                           ROW_NUMBER() OVER (PARTITION BY spct.IdSanPham ORDER BY anh.tenanh DESC) AS row_num
                    FROM Anh anh
                    JOIN SanPhamChiTiet spct ON anh.IdSanPhamChiTiet = spct.id
                    WHERE spct.trangthai = 1
                ),
                SanPhamChiTietGrouped AS (
                    SELECT IdSanPham, SUM(soluong) AS tongSoLuong, MIN(giatien) AS giatien
                    FROM SanPhamChiTiet
                    WHERE gioitinh = 1 AND trangthai = 1
                    GROUP BY IdSanPham
                ),
                KichCoCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT kc.id) AS soLuongKichCo
                    FROM SanPhamChiTiet spct
                    JOIN KichCo kc ON spct.IdKichCo = kc.id
                    WHERE spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                MauSacCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT ms.id) AS soLuongMauSac
                    FROM SanPhamChiTiet spct
                    JOIN MauSac ms ON spct.IdMauSac = ms.id
                    WHERE spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                GiamGia AS (
                    SELECT spct.IdSanPham, MAX(dg.giatrigiam) AS giaGiam
                    FROM SanPhamChiTiet spct
                    JOIN SanPhamDotGiam spdg ON spct.Id = spdg.idchitietsanpham
                    JOIN DotGiamGia dg ON spdg.iddotgiam = dg.id
                    WHERE dg.trangthai = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                )
                SELECT sp.id, sp.tensanpham, sp.ngaytao, spctg.tongSoLuong, sp.trangthai, spctg.giatien, anhdd.tenanh, kc.soLuongKichCo, ms.soLuongMauSac, COALESCE(giamgia.giaGiam, 0) AS giaGiam
                FROM SanPham sp
                JOIN SanPhamChiTietGrouped spctg ON sp.id = spctg.IdSanPham
                JOIN AnhDaiDien anhdd ON sp.id = anhdd.IdSanPham AND anhdd.row_num = 1
                JOIN KichCoCount kc ON sp.id = kc.IdSanPham
                JOIN MauSacCount ms ON sp.id = ms.IdSanPham
                LEFT JOIN GiamGia giamgia ON sp.id = giamgia.IdSanPham
                ORDER BY spctg.giatien DESC, spctg.tongSoLuong DESC
            """)
    List<Object[]> locgiamdan();


    // lọc sản phẩm là nam
    @Query(value = """
            SELECT sp.id, sp.tensanpham, sp.ngaytao, SUM(spct.soluong) AS tongSoLuong, sp.trangthai, 
                   MAX(spct.giatien) AS maxGiaTien, MAX(anh.tenanh) AS maxTenAnh,
                   COUNT(CASE WHEN spct.kichco IS NOT NULL THEN spct.kichco.id ELSE NULL END) AS countKichCo,
                   COUNT(CASE WHEN spct.mausac IS NOT NULL THEN spct.mausac.id ELSE NULL END) AS countMauSac, 
                   MAX(COALESCE(dgg.giatrigiam, 0)) AS maxGiaTriGiam
            FROM SanPham sp
            JOIN sp.spct spct
            LEFT JOIN spct.anh anh
            LEFT JOIN spct.sanphamdotgiam spdg
            LEFT JOIN spdg.dotgiamgia dgg
            WHERE (
                    (?1 = true AND spct.giatien BETWEEN 0 AND 1000000 AND spct.gioitinh = true) 
                    OR (?2 = true AND spct.giatien BETWEEN 1000000 AND 2000000 AND spct.gioitinh = true)
                    OR (?3 = true AND spct.giatien BETWEEN 2000000 AND 3000000 AND spct.gioitinh = true)
                    OR (?4 = true AND spct.giatien BETWEEN 3000000 AND 5000000 AND spct.gioitinh = true)
                    OR (?5 = true AND spct.giatien > 5000000 AND spct.gioitinh = true)
                  )
            OR (
                    (?6 IS NULL OR spct.thuonghieu.id IN (?6))
                    OR (?7 IS NULL OR spct.kichco.id IN (?7))
                    OR (?8 IS NULL OR spct.mausac.id IN (?8))
                  ) 
            AND spct.gioitinh = true AND spct.trangthai=true
            GROUP BY sp.id, sp.tensanpham, sp.ngaytao, sp.trangthai
            ORDER BY sp.ngaytao DESC, tongSoLuong DESC
            """)
    List<Object[]> loctheothkc(Boolean range1, Boolean range2, Boolean range3, Boolean range4, Boolean range5, List<Integer> idthuonghieu, List<Integer> idkichco, List<Integer> idmausac);


    // tìm kiếm theo mã và tên sản phẩm nam
    @Query(nativeQuery = true, value = """
                WITH SanPhamChiTietGrouped AS (
                    SELECT spct.IdSanPham, SUM(spct.soluong) AS tongSoLuong, MAX(spct.giatien) AS maxGiaTien
                    FROM SanPhamChiTiet spct
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                AnhDaiDien AS (
                    SELECT spct.IdSanPham, MAX(anh.tenanh) AS maxTenAnh
                    FROM Anh anh
                    JOIN SanPhamChiTiet spct ON anh.IdSanPhamChiTiet = spct.id
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                KichCoCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT kc.id) AS soLuongKichCo
                    FROM SanPhamChiTiet spct
                    JOIN KichCo kc ON spct.IdKichCo = kc.id
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                MauSacCount AS (
                    SELECT spct.IdSanPham, COUNT(DISTINCT ms.id) AS soLuongMauSac
                    FROM SanPhamChiTiet spct
                    JOIN MauSac ms ON spct.IdMauSac = ms.id
                    WHERE spct.gioitinh = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                ),
                GiamGia AS (
                    SELECT spct.IdSanPham, MAX(dg.giatrigiam) AS giaGiam
                    FROM SanPhamChiTiet spct
                    JOIN SanPhamDotGiam spdg ON spct.Id = spdg.idchitietsanpham
                    JOIN DotGiamGia dg ON spdg.iddotgiam = dg.id
                    WHERE dg.trangthai = 1 AND spct.trangthai = 1
                    GROUP BY spct.IdSanPham
                )
                SELECT sp.id, sp.tensanpham, sp.ngaytao, spctg.tongSoLuong, sp.trangthai, spctg.maxGiaTien, anhdd.maxTenAnh, kc.soLuongKichCo, ms.soLuongMauSac, COALESCE(giamgia.giaGiam, 0) AS giaGiam
                FROM SanPham sp
                JOIN SanPhamChiTietGrouped spctg ON sp.id = spctg.IdSanPham
                JOIN AnhDaiDien anhdd ON sp.id = anhdd.IdSanPham
                JOIN KichCoCount kc ON sp.id = kc.IdSanPham
                JOIN MauSacCount ms ON sp.id = ms.IdSanPham
                LEFT JOIN GiamGia giamgia ON sp.id = giamgia.IdSanPham
                JOIN SanPhamChiTiet spct ON sp.id = spct.IdSanPham
                WHERE (sp.masanpham LIKE %?1% OR sp.tensanpham LIKE %?2%) AND spct.gioitinh = 1 AND spct.trangthai = 1
                GROUP BY sp.id, sp.tensanpham, sp.ngaytao, sp.trangthai, spctg.tongSoLuong, spctg.maxGiaTien, anhdd.maxTenAnh, kc.soLuongKichCo, ms.soLuongMauSac, giamgia.giaGiam
                ORDER BY sp.ngaytao DESC, spctg.tongSoLuong DESC
            """)
    List<Object[]> searchByMaAnhTenSP(String masanpham, String tensanpham);


}
