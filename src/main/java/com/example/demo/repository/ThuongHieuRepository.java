package com.example.demo.repository;

import com.example.demo.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Integer> {
//    Page<ThuongHieu> getAll(Pageable pageable);

    List<ThuongHieu> getThuongHieuByTenOrTrangthai(String ten, Boolean trangthai);

    List<ThuongHieu> findAllByOrderByNgaytaoDesc();

    Boolean existsByTen(String ten);

}
