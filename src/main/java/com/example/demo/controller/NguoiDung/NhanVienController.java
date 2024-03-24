package com.example.demo.controller.NguoiDung;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.NguoiDung;
import com.example.demo.info.DiaChiNVInfo;
import com.example.demo.info.NguoiDungNVInfo;
import com.example.demo.info.NhanVienInfo;
import com.example.demo.service.impl.DiaChiImpl;
import com.example.demo.service.impl.NguoiDungImpl1;
import com.example.demo.service.impl.NhanVienImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NhanVienController {
    @Autowired
    NhanVienImpl nhanVien;
    @Autowired
    DiaChiImpl diaChi;
    @Autowired
    NguoiDungImpl1 nguoiDung;

    @GetMapping("/listnv")
    public String listnv(Model model) {
        List<DiaChi> page = diaChi.getAll();
        model.addAttribute("list", page);
        return "admin/qlnhanvien";
    }
    @GetMapping("/admin/addnhanvien1")
    public String viewAdd(@ModelAttribute("nv1") NhanVienInfo nv,
                          @ModelAttribute("nv") NguoiDungNVInfo nd,
                          @ModelAttribute("dc") DiaChiNVInfo dc,
                          Model model, RedirectAttributes redirectAttributes) {

        return "admin/addnhanvien";
    }
    @PostMapping("/addnv")
    public String addSave(@ModelAttribute("nv1") NhanVienInfo nv,
                          @ModelAttribute("nv") NguoiDungNVInfo nd,
                          @ModelAttribute("dc") DiaChiNVInfo dc,
                          Model model, RedirectAttributes redirectAttributes) {
//        if (deGiay.getTen().equals("")) {
//            model.addAttribute("err", "Tên không được để trống");
//            return "admin/qldegiay";
//        }
        nguoiDung.add(nd);
        NguoiDung n = nguoiDung.search(nd.getEmail());
        nv.setIdnguoidung(n);
        nhanVien.add(nv);
        dc.setIdnguoidung(n);
        diaChi.add(dc);
        String to = n.getEmail();
        String subject = "Chúc mừng đã trở thành nhân viên của T&T shop";
        String mailType = "";
        String mailContent = "Mật khẩu của bạn là: " + n.getMatkhau();
        nguoiDung.sendEmail(to, subject, mailType, mailContent);
        return "redirect:/listnv";
    }

}
