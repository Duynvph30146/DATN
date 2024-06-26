package com.example.demo.repository.giohang;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.SanPhamChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

    GioHangChiTiet findByGiohangAndSanphamchitiet(GioHang gioHang, SanPhamChiTiet sanPhamChiTiet);

    @Transactional
    @Modifying
    @Query("UPDATE GioHangChiTiet ghct SET ghct.soluong = :soLuong WHERE ghct.id = :id")
    void updateSoLuongById(Integer soLuong, Integer id);

    @Query(value = """
            SELECT g FROM GioHangChiTiet g where g.giohang.id=:id
            """)
    List<GioHangChiTiet> findGioHangChiTietByGiohang(Integer id);


}
