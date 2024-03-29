package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phieugiamgia")
public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String macode;
    String tenphieu;
    BigDecimal giatrigiamtoida;//tiền giảm tối đa
    Integer giatrigiam;// phần trăm giảm
    BigDecimal dontoithieu;
    Integer soluong;
    Boolean loaiphieu;
    Timestamp ngaybatdau;
    Timestamp ngayketthuc;
    Timestamp ngaytao;
    String nguoitao;
    Timestamp lancapnhatcuoi;
    String nguoicapnhat;
    Boolean trangthai;
}
