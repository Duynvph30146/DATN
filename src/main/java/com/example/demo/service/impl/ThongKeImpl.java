package com.example.demo.service.impl;

import com.example.demo.repository.ThongKeRepository;
import com.example.demo.repository.hoadon.HoaDonRepository;
import com.example.demo.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ThongKeImpl implements ThongKeService {
    @Autowired
    ThongKeRepository dao;

    @Override
    public int thongKeTheoThang() {
        return dao.thongKeTheoThang();
    }
    @Override
    public int thongKeTienTheoThang() {
        return dao.thongKeTienTheoThang();
    }
    @Override
    public int thongKeTheoNgay() {
        return dao.thongKeTheoNgay();
    }
    @Override
    public int thongKeTienTheoNgay() {
        return dao.thongKeTienTheoNgay();
    }

    @Override
    public List<Object[]> bdTron() {
        return dao.bdTron();
    }

    @Override
    public BigDecimal ttdsn() {
        return dao.ttdsn();
    }

    @Override
    public BigDecimal ttdst() {
        return dao.ttdst();
    }

    @Override
    public int ttspn() {
        return dao.ttspn();
    }

    @Override
    public int ttspt() {
        return dao.ttspt();
    }

    @Override
    public int tthdn() {
        return dao.tthdn();
    }

    @Override
    public int tthdt() {
        return dao.tthdt();
    }

    @Override
    public int ptdsn() {
        return dao.ptdtn();
    }

    @Override
    public int ptdst() {
        return dao.ptdtt();
    }

    @Override
    public int ptspn() {
        return dao.ptspn();
    }

    @Override
    public int ptspt() {
        return dao.ptspt();
    }

    @Override
    public int pthdn() {
        return dao.pthdn();
    }

    @Override
    public int pthdt() {
        return dao.pthdt();
    }
    @Override
    public int soLuongsp(){
        return dao.soLuongsp();
    }

    @Override
    public List<Object[]> soLuongDaBan() {
        return dao.soLuongDaBan();
    }
    @Override
    public List<Object[]> soLuongTon() {
        return dao.soLuongTon();
    }

    @Override
    public List<Object[]> dayData() {
        return dao.dayData();
    }

    @Override
    public List<Object[]> thangData() {
        return dao.thangData();
    }

    @Override
    public List<Object[]> namData() {
        return dao.namData();
    }

    //ok
    @Override
    public List<Object[]> dayex() {
        return dao.dayex();
    }
    //ok
    @Override
    public List<Object[]> thangex() {
        return dao.thangex();
    }
    //ok
    @Override
    public List<Object[]> namex() {
        return dao.namex();
    }

    @Override
    public Map<String, Object> khoangngayData() {
        // Trả về dữ liệu tùy chỉnh
        Map<String, Object> data = new HashMap<>();
        data.put("labels", new String[]{"Custom1", "Custom2", "Custom3", "Custom4"});
        data.put("datasets", new int[][]{
                {50, 60, 70, 80},  // Dữ liệu sản phẩm
                {30, 40, 50, 60}   // Dữ liệu hóa đơn
        });
        return data;
    }

    @Override
    public List<Object[]> khoangngay(Date startDate, Date endDate) {
        return dao.khoangNgay(startDate,endDate);
    }
}
