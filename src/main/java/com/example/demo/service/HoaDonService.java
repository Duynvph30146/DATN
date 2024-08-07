package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import com.example.demo.info.MauHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.context.Context;

import java.sql.Date;
import java.util.List;

public interface HoaDonService {
    Page<HoaDon> findAll(Pageable p);

    Page<HoaDon> Loc(Integer trangThai, Boolean loaihd, Date tu, Date den, Pageable p);

    Page<HoaDon> timKiemTT(Integer trangThai, Pageable p);

    Long tinhTong(Integer tt);

    List<HoaDon> timall();

    Page<HoaDon> LockTT(Boolean loaihd, Date tu, Date den, Pageable p);

    Page<HoaDon> LocKLHD(Integer trangThai, Date tu, Date den, Pageable p);

    Page<HoaDon> LocKngayTao(Integer trangThai, Boolean loaihd, Pageable p);

    Page<HoaDon> LocTheoKhoangNgay(Date tu, Date den, Pageable p);
    Page<HoaDon> LocTheoLoaiDon( Boolean loaihd, Pageable p);
    List<HoaDon> timTheoID(Integer id);
    void capNhatHD(HoaDon hd);
    public  String htmlToPdf(String htmlfile,String pdfname);
    public Context setData(MauHoaDon userList);
    List<HoaDon> timTheoTrangThaiVaLoai(Integer id,Boolean loaihd);
    HoaDon timBanGhiDuocTaoGanNhat();
    HoaDon timHDTheoMaHD(String mahd);
    public  String htmlToPdfTaiQuay(String htmlfile,String pdfname);
    Boolean delete(HoaDon idhd);
}
