package com.example.demo.repository;

import com.example.demo.entity.NguoiDung;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    @Query("SELECT n FROM NguoiDung n WHERE n.taikhoan = ?1")
    NguoiDung findNguoiDungByTaikhoan(String taikhoan);
    @Query("SELECT n FROM NguoiDung n WHERE n.hovaten LIKE %?1% OR n.sodienthoai LIKE %?2%")
    List<NguoiDung> findByTenOrSdt(@Param("hovaten") String ten,
                                   @Param("sodienthoai") String sdt);
    @Query("SELECT n FROM NguoiDung n WHERE n.cccd LIKE %?1%")
    List<NguoiDung> findNguoiDungByCccd(String cccd);
    @Query("SELECT n FROM NguoiDung n WHERE n.ngaysinh BETWEEN :startDate AND :endDate")
    List<NguoiDung> findNguoiDungByNgaysinhBetween(@Param("startDate")Date startDate,
                                                   @Param("endDate") Date endDate);
    @Query("SELECT n FROM NguoiDung n WHERE n.email = ?1")
    NguoiDung findNguoiDungByEmail(@Param("emailResetPassword") String emailResetPassword);

    NguoiDung findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE NguoiDung n SET n.matkhau = :newPassword WHERE n.taikhoan = :username")
    void updatePassword(String username, String newPassword);
    boolean existsBySodienthoai(String sdt);
    boolean existsByEmail(String email);

}
