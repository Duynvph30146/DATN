package com.example.demo.repository;

import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia,Integer> {
    PhieuGiamGia findFirstByOrderByNgaytaoDesc();
}
