package com.example.demo.controller.customer;

import com.example.demo.entity.Anh;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.repository.AnhRepository;
import com.example.demo.repository.KichCoRepository;
import com.example.demo.repository.SanPhamChiTietRepository;
import com.example.demo.repository.SanPhamRepositoty;
import com.example.demo.repository.customer.TrangChuRepository;
import com.example.demo.repository.giohang.GioHangChiTietRepository;
import com.example.demo.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.*;

@Controller
public class TrangChuCustomerController {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    TrangChuRepository trangChuRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    SanPhamRepositoty sanPhamRepositoty;

    @Autowired
    SanPhamImp sanPhamImp;

    @Autowired
    SanPhamChiTietImp sanPhamChiTietImp;

    @Autowired
    ThuongHieuImp thuongHieuImp;

    @Autowired
    MauSacImp mauSacImp;

    @Autowired
    KichCoImp kichCoImp;

    @Autowired
    DeGiayImp deGiayImp;

    @Autowired
    ChatLieuImp chatLieuImp;

    @Autowired
    AnhImp anhImp;
    @Autowired
    AnhRepository anhRepository;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/customer/trangchu")
    public String hienthiTrangChu(Model model) {
        List<GioHangChiTiet> cartItems = gioHangChiTietRepository.findAll(); // Giả sử bạn có phương thức này để lấy các mục trong giỏ hàng
        int totalQuantity = 0;
        // Tính tổng số lượng sản phẩm trong giỏ hàng
        for (GioHangChiTiet item : cartItems) {
            totalQuantity += item.getSoluong();
        }
        model.addAttribute("totalQuantity", totalQuantity);
        List<Object[]> topspmoinhattrangchu = trangChuRepository.topspmoinhattrangchu();
        model.addAttribute("topspmoinhattrangchu", topspmoinhattrangchu);
        List<Object[]> topspbanchaynhattrangchu = trangChuRepository.topspbanchaynhat();
        model.addAttribute("topspbanchaynhattrangchu", topspbanchaynhattrangchu);
        System.out.println();
        return "customer/trangchu";
    }

    @GetMapping("/detailsanphamCustomer/{id}")
    public String detailsanphamCustomer(@PathVariable Integer id, @RequestParam(required = false) String color, @RequestParam(required = false) String size, Model model) {
        List<GioHangChiTiet> cartItems = gioHangChiTietRepository.findAll(); // Giả sử bạn có phương thức này để lấy các mục trong giỏ hàng
//        int totalQuantity = cartItems.size(); // Đếm số lượng các mục trong giỏ hàng
        int totalQuantity = 0;
        // Tính tổng số lượng sản phẩm trong giỏ hàng
        for (GioHangChiTiet item : cartItems) {
            totalQuantity += item.getSoluong();
        }
        model.addAttribute("totalQuantity", totalQuantity);
        SanPham sanPham = trangChuRepository.findById(id).orElse(null);
        model.addAttribute("sanpham", sanPham);
        SanPhamChiTiet sanPhamChiTiet=sanPhamChiTietRepository.findById(id).orElse(null);
        model.addAttribute("sanphamchitiet", sanPhamChiTiet);
        List<String> danhSachAnh = new ArrayList<>();
        for (SanPhamChiTiet spct : sanPham.getSpct()) {
            for (Anh anh : spct.getAnh()) {
                if (color == null || color.equals(spct.getMausac().getTen())) {
                    danhSachAnh.add(anh.getTenanh());
                }
            }
        }
        model.addAttribute("danhSachAnh", danhSachAnh);
        List<String> sizes = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        BigDecimal selectedPrice = null;
        Integer selectedQuantity = null;
        String selectedThuongHieu=null;
        String selectedChatLieu=null;
        String selectedDeGiay=null;
        String selectedMaSPCT=null;
        String defaultColor = null;
        String defaultSize = null;
        for (SanPhamChiTiet spct : sanPham.getSpct()) {
            if (!sizes.contains(spct.getKichco().getTen())) {
                sizes.add(spct.getKichco().getTen());
            }
            if (!colors.contains(spct.getMausac().getTen())) {
                colors.add(spct.getMausac().getTen());
            }
            if ((color == null || color.equals(spct.getMausac().getTen())) && (size == null || size.equals(spct.getKichco().getTen()))) {
                selectedPrice = spct.getGiatien();
                selectedQuantity = spct.getSoluong();
                selectedThuongHieu=spct.getThuonghieu().getTen();
                selectedChatLieu=spct.getChatlieu().getTen();
                selectedDeGiay=spct.getDegiay().getTen();
                selectedMaSPCT=spct.getMasanphamchitiet();
            }
        }
        if (colors.size() > 0) {
            defaultColor = colors.get(0);
        }
        if (sizes.size() > 0) {
            defaultSize = sizes.get(0);
        }
        model.addAttribute("sizes", sizes);
        model.addAttribute("colors", colors);
        model.addAttribute("selectedPrice", selectedPrice);
        model.addAttribute("selectedQuantity", selectedQuantity);
        model.addAttribute("selectedThuongHieu", selectedThuongHieu);
        model.addAttribute("selectedChatLieu", selectedChatLieu);
        model.addAttribute("selectedDeGiay", selectedDeGiay);
        model.addAttribute("selectedMaSPCT", selectedMaSPCT);
        model.addAttribute("defaultColor", defaultColor);
        model.addAttribute("defaultSize", defaultSize);
        List<Object[]> page = trangChuRepository.topspmoinhatdetail();
        model.addAttribute("page", page);
        List<Object[]> page2 = trangChuRepository.topspnoibatdetail();
        model.addAttribute("page2", page2);
        return "customer/product-details";
    }
}