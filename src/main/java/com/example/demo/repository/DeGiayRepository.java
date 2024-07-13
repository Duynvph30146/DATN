package com.example.demo.repository;

import com.example.demo.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay, Integer> {
    List<DeGiay> findAllByOrderByNgaytaoDesc();

    @Query("SELECT dg FROM DeGiay dg WHERE dg.ten = :ten AND dg.trangthai = true ")
    List<DeGiay> findDeGiayByTenAndTrangThaiFalse(@Param("ten") String ten);

    @Query(nativeQuery = true, value = """
            SELECT * FROM DeGiay Where TrangThai=1
            ORDER BY NgayTao DESC
             """)
    List<DeGiay> getAll();

    @Modifying
    @Transactional
    @Query("UPDATE DeGiay d SET d.trangthai = false WHERE d.id = :id")
    void updateTrangThaiToFalseById(Integer id);
}
