package com.example.demo.controller.login;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NguoiDung;
import com.example.demo.info.DangKyDCInfo;
import com.example.demo.info.DangKyKHInfo;
import com.example.demo.info.DangKyNDInfo;
import com.example.demo.info.DangNhapNDInfo;
import com.example.demo.security.CustomerUserDetailService;
import com.example.demo.service.DiaChiService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.NguoiDungService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DangKyController {
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    DiaChiService diaChiService;
    @Autowired
    CustomerUserDetailService userDetailService;

    @GetMapping("/dangky")
    public String view(@ModelAttribute("nguoidung") DangKyNDInfo nguoidung,
                       @ModelAttribute("khachhang") DangKyKHInfo khachhang,
                       @ModelAttribute("dangnhap") DangNhapNDInfo dangnhap
                       ) {
        return "customer/login";
    }

    @PostMapping("/dangky")
    public String dangky(@Valid @ModelAttribute("nguoidung") DangKyNDInfo nguoidung,
                         BindingResult ndBindingResult,
                         @Valid @ModelAttribute("khachhang") DangKyKHInfo khachhang,
                         BindingResult khBindingResult,
                         @Valid @ModelAttribute("diachi") DangKyDCInfo diachi,
                         @Valid @ModelAttribute("dangnhap") DangNhapNDInfo dangnhap,
                         BindingResult dcBindingResult,
                         Model model
    ) {
        System.out.println("BBB");
        System.out.println(nguoidung.getHovaten());
        if (ndBindingResult.hasErrors()) {
            List<ObjectError> ndError = ndBindingResult.getAllErrors();
            System.out.println(ndError);
            return "customer/login";
        } else if (khBindingResult.hasErrors()) {
            List<ObjectError> khError = khBindingResult.getAllErrors();
            System.out.println(khError);
            return "customer/login";
        } else if (dcBindingResult.hasErrors()) {
            List<ObjectError> dcError = dcBindingResult.getAllErrors();
            System.out.println(dcError);
            return "customer/login";
        }

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = bCryptPasswordEncoder.encode(nguoidung.getMatkhau());


        //nguoi dung
        LocalDateTime currentDate = LocalDateTime.now();

        System.out.println("AAA");
        System.out.println(nguoidung.getTaikhoan());
        NguoiDung nd = new NguoiDung();
        nd.setHovaten(nguoidung.getHovaten());
        nd.setNgaysinh(nguoidung.getNgaysinh());
        nd.setGioitinh(nguoidung.getGioitinh());
        nd.setEmail(nguoidung.getEmail());
        nd.setSodienthoai(nguoidung.getSodienthoai());
        nd.setNgaytao(Timestamp.valueOf(currentDate));
        nd.setLancapnhatcuoi(Timestamp.valueOf(currentDate));
        nd.setTaikhoan(nguoidung.getTaikhoan());
        nd.setMatkhau(nguoidung.getMatkhau());
        nd.setTrangthai(true);
        nd.setNguoitao("CUSTOMER");
        nd.setNguoicapnhat("CUSTOMER");
        khachHangService.addNguoiDung(nd);

        //Khach hang
        KhachHang kh = new KhachHang();
        List<KhachHang> lstKhachHang = khachHangService.findAllKhachHang();
        int total = lstKhachHang.size();
        String maKH = "KH" + (total + 1);
        kh.setMakhachhang(maKH);
        kh.setNguoitao(nd.getNguoitao());
        kh.setNguoicapnhat(nd.getNguoicapnhat());
        kh.setTrangthai(nd.getTrangthai());
        kh.setNguoidung(nd);
        kh.setNgaytao(nd.getNgaytao());
        kh.setLancapnhatcuoi(nd.getLancapnhatcuoi());
        khachHangService.addKhachHang(kh);

        DiaChi dc = new DiaChi();
        dc.setTenduong("");
        dc.setTinhthanhpho("");
        dc.setHotennguoinhan(nd.getHovaten());
        dc.setQuanhuyen("");
        dc.setXaphuong("");
        dc.setNguoidung(nd);
        dc.setLancapnhatcuoi(nd.getLancapnhatcuoi());
        dc.setNgaytao(nd.getNgaytao());
        dc.setNguoitao(nd.getNguoitao());
        dc.setNguoicapnhat(nd.getNguoicapnhat());
        dc.setSdtnguoinhan(nd.getSodienthoai());
        dc.setTrangthai(nd.getTrangthai());
        khachHangService.addDiaChi(dc);

//        khachHangService.sendEmail(nd.getEmail(), nd.getTaikhoan(), nd.getMatkhau(), hoten);

        return "customer/login";
    }

    @PostMapping("/quenmatkhau")
    public String quenmatkhau(@RequestParam("email") String email, Model model) {
        NguoiDung nd = khachHangService.findByEmail(email);
        SecureRandom random = new SecureRandom();
        int CODE_LENGTH = 6;
        if (nd != null) {
            int code = random.nextInt((int) Math.pow(10, CODE_LENGTH));
            khachHangService.sendEmailQuenMatKhau(email, nd.getHovaten(), String.valueOf(code));
            model.addAttribute("dangnhap", true);
            return "same/quenmatkhau";
        } else {
            model.addAttribute("message", "Email không tồn tại trong hệ thống.");
            model.addAttribute("dangnhap", false);
            return "same/quenmatkhau";
        }
    }


}
