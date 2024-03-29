package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diachi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer soduong;
    String tenduong;
    String xaphuong;
    String quanhuyen;
    String tinhthanhpho;
    String sdtnguoinhan;
    String hotennguoinhan;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ngaytao;
    String nguoitao;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date lancapnhatcuoi;
    String nguoicapnhat;
    Boolean trangthai;
    @ManyToOne
    @JoinColumn(name = "idnguoidung")
    NguoiDung nguoidung;
}
