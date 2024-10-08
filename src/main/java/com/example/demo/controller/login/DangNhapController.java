package com.example.demo.controller.login;

import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NguoiDung;
import com.example.demo.info.DangNhapNDInfo;
import com.example.demo.info.TaiKhoanTokenInfo;
import com.example.demo.info.token.UserManager;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.security.CustomerUserDetailService;
import com.example.demo.security.JWTGenerator;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.NguoiDungService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DangNhapController {
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    public UserManager userManager;

    public List<TaiKhoanTokenInfo> taiKhoanTokenInfos = new ArrayList<>();

    @PostMapping("/dangnhap")
    public String dangnhap(Model model,
                           HttpSession session,
                           @RequestParam("username") String taikhoan,
                           @RequestParam("password") String matkhau,
                           @ModelAttribute("dangnhap") DangNhapNDInfo dangnhap,
                           RedirectAttributes redirectAttributes) {

        if (taikhoan == "" && matkhau == "") {
            redirectAttributes.addFlashAttribute("error", "Tài khoản và mật khẩu đang trống");
            redirectAttributes.addFlashAttribute("opentab", true);
            return "redirect:/account";
        } else if (taikhoan == "") {
            redirectAttributes.addFlashAttribute("error", "Tài khoản đang trống");
            redirectAttributes.addFlashAttribute("opentab", true);
            return "redirect:/account";
        } else if (matkhau == "") {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu đang trống");
            redirectAttributes.addFlashAttribute("opentab", true);
            return "redirect:/account";
        } else {
            try {
                NguoiDung nd = khachHangService.findNguoiDungByTaikhoan(taikhoan);
                UserDetails userDetails = customerUserDetailService.loadUserByUsername(taikhoan);
                KhachHang kh = khachHangService.findKhachHangByIdNguoiDung(nd.getId());
                if (userDetails == null) {
                    redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
                    redirectAttributes.addFlashAttribute("opentab", true);
                    return "redirect:/account";
                }
                if (kh == null) {
                    redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
                    redirectAttributes.addFlashAttribute("opentab", true);
                    return "redirect:/account";
                }
                if (kh.getTrangthai() == false) {
                    redirectAttributes.addFlashAttribute("error", "Tài khoản đã bị khóa");
                    redirectAttributes.addFlashAttribute("opentab", true);
                    return "redirect:/account";
                }

                if (passwordEncoder.matches(matkhau, userDetails.getPassword())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = jwtGenerator.generateToken(authentication);
                    Integer userId = nd.getId();
                    TaiKhoanTokenInfo taiKhoanTokenInfo = new TaiKhoanTokenInfo(userId, token);
                    // Lấy danh sách token từ session và thêm mới
//                List<TaiKhoanTokenInfo> taiKhoanTokenInfos = (List<TaiKhoanTokenInfo>) session.getAttribute("taiKhoanTokenInfos");
//                if (taiKhoanTokenInfos == null) {
//                    taiKhoanTokenInfos = new ArrayList<>();
//                }
                    taiKhoanTokenInfos.add(taiKhoanTokenInfo);
                    session.setAttribute("taiKhoanTokenInfos", taiKhoanTokenInfos);
                    session.setAttribute("token", token);
                    userManager.addUser(nd.getTaikhoan(), token);
                    session.setAttribute("userDangnhap", nd.getTaikhoan());
//                    session.setAttribute("user", nd);
                    return "redirect:/customer/trangchu";
                } else {
                    redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
                    redirectAttributes.addFlashAttribute("opentab", true);
                    return "redirect:/account";
                }
            } catch (UsernameNotFoundException e) {
                redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
                redirectAttributes.addFlashAttribute("opentab", true);
                return "redirect:/account";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
                redirectAttributes.addFlashAttribute("opentab", true);
                return "redirect:/account";
            }
        }


    }

//    @PostMapping("/dangnhap")
//    public String dangnhap(Model model,
//                           HttpSession session,
//                           @RequestParam("taikhoan") String taikhoan,
//                           @RequestParam("matkhau") String matkhau,
//                           @ModelAttribute("dangnhap") DangNhapNDInfo dangnhap,
//                           HttpServletRequest request,
//                           RedirectAttributes redirectAttributes) {
//        try {
//            NguoiDung nd = khachHangService.findNguoiDungByTaikhoan(taikhoan);
//            Integer userId = nd.getId();
//            UserDetails userDetails = customerUserDetailService.loadUserByUsername(taikhoan);
//            if (userDetails == null) {
//                redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
//                return "redirect:/dangky";
//            }
//
//            if (passwordEncoder.matches(matkhau, userDetails.getPassword())) {
//                String userAgent = request.getHeader("User-Agent");
//                userManager.storeUserAgent(userId, userAgent);
//                if (userManager.isUserLoggedIn(userId, userAgent)) {
//                    // Đăng xuất người dùng khỏi thiết bị A
//                    userManager.remove(userId, userAgent);
//                    userManager.logoutUser(userId, userManager.getToken(userId));
//                    SecurityContextHolder.getContext().setAuthentication(null);
//                    // Đăng nhập người dùng vào thiết bị B
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities()
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    String token = jwtGenerator.generateToken(authentication);
//                    session.setAttribute("userDangnhap", nd.getTaikhoan());
//                    userManager.addUser(userId, token);
//                    System.out.println("Danh sách người dùng đang đăng nhập: " + userManager.getLoggedInUsers());
//                    return "redirect:/customer/trangchu";
//                } else {
//                    // Người dùng chưa đăng nhập ở thiết bị nào
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities()
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    String token = jwtGenerator.generateToken(authentication);
//                    session.setAttribute("userDangnhap", nd.getTaikhoan());
//                    userManager.addUser(userId, token);
//                    System.out.println("Danh sách người dùng đang đăng nhập: " + userManager.getLoggedInUsers());
//                    return "redirect:/customer/trangchu";
//                }
//            } else {
//                redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
//                System.out.println("Mật khẩu không đúng");
//                return "redirect:/dangky";
//            }
//        } catch (UsernameNotFoundException e) {
//            redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
//            System.out.println("Sai tài khoản hoặc mật khẩu");
//            return "redirect:/dangky";
//        } catch (Exception e) {
//            System.out.println(e);
//            redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
//            return "redirect:/dangky";
//        }
//    }

        @GetMapping("/dangxuat")
        public String logout (HttpServletRequest request){
            // Xóa session của người dùng để đăng xuất
            HttpSession session = request.getSession(false);
            String userName = (String) session.getAttribute("userDangnhap");
            NguoiDung nguoiDung = khachHangService.findNguoiDungByTaikhoan(userName);
            String token = userManager.getToken(nguoiDung.getTaikhoan());
            if (session != null) {
                session.removeAttribute("userDangnhap");    
                userManager.logoutUser(nguoiDung.getTaikhoan(), token);
                System.out.println("Danh sách người dùng đang đăng nhập: " + userManager.getLoggedInUsers());
            }
            return "redirect:/customer/trangchu";
        }


    }
