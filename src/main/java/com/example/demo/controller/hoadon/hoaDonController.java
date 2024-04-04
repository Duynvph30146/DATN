package com.example.demo.controller.hoadon;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import com.example.demo.info.HoaDonCustom;
import com.example.demo.repository.hoadon.HoaDonRepository;
import com.example.demo.service.HoaDonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.Optional;

@Controller
@RequestMapping("hoa-don")
public class hoaDonController {
    @Autowired
    HoaDonService dao;
    Integer idhd = null;
    HoaDonCustom hdSaveInfoSeachr = new HoaDonCustom();

    @GetMapping("hien-thi")
    public String hienThi(Model model, @RequestParam("page") Optional<Integer> pageParam,
                          @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.findAll(p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 7);
        //hiển thị tổng số hd theo tt
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("loc")
    public String Loc(@ModelAttribute("hdcustom") HoaDonCustom HDinfo) {
        if (HDinfo.getKey().isBlank()) HDinfo.setKey("null");
        try {
            Date.valueOf(HDinfo.getTu());
        } catch (Exception e) {
            HDinfo.setTu("null");
        }
        try {
            Date.valueOf(HDinfo.getDen());
        } catch (Exception e) {
            HDinfo.setDen("null");
        }
        hdSaveInfoSeachr = HDinfo;
        return "redirect:/hoa-don/show-loc";
    }

    @GetMapping("show-loc")
    public String Loc(Model model, @RequestParam("page") Optional<Integer> pageParam, @ModelAttribute("hdcustom") HoaDonCustom HDinfo) {
        Page<HoaDon> lst = null;
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        int trangThai = -1;
        if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                || hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                || hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                || hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
            //các case tìm kiếm 1 th null
            //case key null and all not null
            if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                System.out.println("key trong all not null");
                return "redirect:/hoa-don/hien-thi";
            }
            //case loaihd null and all not null
            if (hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                System.out.println("loaihd trong all not null");
                return "redirect:/hoa-don/hien-thi";
            }
            //case ngaytao(tu or den) null and all not null
            if (hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    || hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                if (!hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                        && !hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")) {
                    System.out.println("tu or den trong all not null");
                    return "redirect:/hoa-don/hien-thi";
                }

            }
            //end case tìm kiếm 1 th null
            //các case tìm kiếm 2 th null


            //case key and loaihd null
            if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                System.out.println("key and loai hd null");
                return "redirect:/hoa-don/hien-thi";
            }
            //case key and (tu or den null)
            if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                    && !hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                System.out.println("key end tu or den null");
                return "redirect:/hoa-don/hien-thi";
            }

            //case loaihd and (tu or den null)
            if (!hdSaveInfoSeachr.getKey().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getLoaiHD().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getTu().equalsIgnoreCase("null")
                    && hdSaveInfoSeachr.getDen().equalsIgnoreCase("null")) {
                System.out.println("loaihd end tu or den null");
                return "redirect:/hoa-don/hien-thi";
            }

            // end các case tìm kiếm 2 th null

        } else {
            if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("chờ xác nhận")) {
                trangThai = 0;
            } else {
                if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("đã xác nhận")) {
                    trangThai = 1;
                } else {
                    if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("chờ giao hàng")) {
                        trangThai = 2;
                    } else {
                        if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("đang giao hàng")) {
                            trangThai = 3;
                        } else {
                            if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("đã thanh toán")) {
                                trangThai = 4;
                            } else {
                                if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("đã hoàn thành")) {
                                    trangThai = 5;
                                } else {
                                    if (hdSaveInfoSeachr.getKey().equalsIgnoreCase("đã hủy")) {
                                        trangThai = 6;
                                    }
                                }
                            }
                        }

                    }
                }
            }
            lst = dao.Loc(trangThai,
                    Boolean.valueOf(hdSaveInfoSeachr.getLoaiHD()), Date.valueOf(hdSaveInfoSeachr.getTu()),
                    Date.valueOf(hdSaveInfoSeachr.getDen()), p);
        }
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        //hiển thị số hd theo tt
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }


//    @GetMapping("loc")
//    public String Loc(Model model, @RequestParam("page") Optional<Integer> pageParam,
//                      @Validated @ModelAttribute("hdcustom") HoaDonCustom HDinfo, Errors er) {
//        Page<HoaDon> lst = null;
//        hdSave = HDinfo;
//        int page = pageParam.orElse(0);
//        Pageable p = PageRequest.of(page, 5);
//        Integer trangThai = -1;
//
//        if (er.hasErrors()) {
//            //trường hợp trống 2 key
//
//
//            if (er.hasFieldErrors("key") && HDinfo.getLoaiHD().equalsIgnoreCase("null")) {
//
//                if (HDinfo.getTu().compareTo(HDinfo.getDen()) >= 0) {
//                    Page<HoaDon> fixErr = dao.findAll(p);
//                    model.addAttribute("lst", fixErr);
//                    model.addAttribute("pageNo", page);
//                    model.addAttribute("errdate", 1);
//                    model.addAttribute("tt0", dao.tinhTong(0));
//                    model.addAttribute("tt1", dao.tinhTong(1));
//                    model.addAttribute("tt2", dao.tinhTong(2));
//                    model.addAttribute("tt3", dao.tinhTong(3));
//                    model.addAttribute("tt4", dao.tinhTong(4));
//                    model.addAttribute("tt5", dao.tinhTong(5));
//                    model.addAttribute("tt6", dao.tinhTong(6));
//                    model.addAttribute("tt7", dao.findAll(p).getTotalElements());
//                    return "admin/qlhoadon";
//                }
//                lst = dao.LocTheoKhoangNgay(HDinfo.getTu(), HDinfo.getDen(), p);
//            }
//            //trường hợp trống 1 key
//            if (er.hasFieldErrors("key")) {
//                if (er.hasFieldErrors("tu") || er.hasFieldErrors("den")) {
//                    System.out.println("toi day");
//                    lst = dao.LocTheoLoaiDon(Boolean.valueOf(HDinfo.getLoaiHD()), p);
//                } else {
//                    if (HDinfo.getTu().compareTo(HDinfo.getDen()) >= 0) {
//                        Page<HoaDon> fixErr = dao.findAll(p);
//                        model.addAttribute("lst", fixErr);
//                        model.addAttribute("pageNo", page);
//                        model.addAttribute("errdate", 1);
//                        model.addAttribute("tt0", dao.tinhTong(0));
//                        model.addAttribute("tt1", dao.tinhTong(1));
//                        model.addAttribute("tt2", dao.tinhTong(2));
//                        model.addAttribute("tt3", dao.tinhTong(3));
//                        model.addAttribute("tt4", dao.tinhTong(4));
//                        model.addAttribute("tt5", dao.tinhTong(5));
//                        model.addAttribute("tt6", dao.tinhTong(6));
//                        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
//                        return "admin/qlhoadon";
//                    }
//                    lst = dao.LockTT(Boolean.valueOf(HDinfo.getLoaiHD()), HDinfo.getTu(), HDinfo.getDen(), p);
//                }
//            }
//
//            if (er.hasFieldErrors("tu") || er.hasFieldErrors("den")) {
//
//                if (HDinfo.getKey().equalsIgnoreCase("chờ xác nhận")) {
//                    trangThai = 0;
//                } else {
//                    if (HDinfo.getKey().equalsIgnoreCase("đã xác nhận")) {
//                        trangThai = 1;
//                    } else {
//                        if (HDinfo.getKey().equalsIgnoreCase("chờ giao hàng")) {
//                            trangThai = 2;
//                        } else {
//                            if (HDinfo.getKey().equalsIgnoreCase("đang giao hàng")) {
//                                trangThai = 3;
//                            } else {
//                                if (HDinfo.getKey().equalsIgnoreCase("đã thanh toán")) {
//                                    trangThai = 4;
//                                } else {
//                                    if (HDinfo.getKey().equalsIgnoreCase("đã hoàn thành")) {
//                                        trangThai = 5;
//                                    } else {
//                                        if (HDinfo.getKey().equalsIgnoreCase("đã hủy")) {
//                                            trangThai = 6;
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//                lst = dao.LocKngayTao(trangThai, Boolean.valueOf(HDinfo.getLoaiHD()), p);
//            }
//
//        } else {
//            if (HDinfo.getLoaiHD().equalsIgnoreCase("null")) {
//                if (HDinfo.getKey().equalsIgnoreCase("chờ xác nhận")) {
//                    trangThai = 0;
//                } else {
//                    if (HDinfo.getKey().equalsIgnoreCase("đã xác nhận")) {
//                        trangThai = 1;
//                    } else {
//                        if (HDinfo.getKey().equalsIgnoreCase("chờ giao hàng")) {
//                            trangThai = 2;
//                        } else {
//                            if (HDinfo.getKey().equalsIgnoreCase("đang giao hàng")) {
//                                trangThai = 3;
//                            } else {
//                                if (HDinfo.getKey().equalsIgnoreCase("đã thanh toán")) {
//                                    trangThai = 4;
//                                } else {
//                                    if (HDinfo.getKey().equalsIgnoreCase("đã hoàn thành")) {
//                                        trangThai = 5;
//                                    } else {
//                                        if (HDinfo.getKey().equalsIgnoreCase("đã hủy")) {
//                                            trangThai = 6;
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//                if (HDinfo.getTu().compareTo(HDinfo.getDen()) >= 0) {
//                    Page<HoaDon> fixErr = dao.findAll(p);
//                    model.addAttribute("lst", fixErr);
//                    model.addAttribute("pageNo", page);
//                    model.addAttribute("errdate", 1);
//                    model.addAttribute("tt0", dao.tinhTong(0));
//                    model.addAttribute("tt1", dao.tinhTong(1));
//                    model.addAttribute("tt2", dao.tinhTong(2));
//                    model.addAttribute("tt3", dao.tinhTong(3));
//                    model.addAttribute("tt4", dao.tinhTong(4));
//                    model.addAttribute("tt5", dao.tinhTong(5));
//                    model.addAttribute("tt6", dao.tinhTong(6));
//                    model.addAttribute("tt7", dao.findAll(p).getTotalElements());
//                    return "admin/qlhoadon";
//                }
//                lst = dao.LocKLHD(trangThai, HDinfo.getTu(), HDinfo.getDen(), p);
//
//            } else {
//                if (HDinfo.getKey().equalsIgnoreCase("chờ xác nhận")) {
//                    trangThai = 0;
//                } else {
//                    if (HDinfo.getKey().equalsIgnoreCase("đã xác nhận")) {
//                        trangThai = 1;
//                    } else {
//                        if (HDinfo.getKey().equalsIgnoreCase("chờ giao hàng")) {
//                            trangThai = 2;
//                        } else {
//                            if (HDinfo.getKey().equalsIgnoreCase("đang giao hàng")) {
//                                trangThai = 3;
//                            } else {
//                                if (HDinfo.getKey().equalsIgnoreCase("đã thanh toán")) {
//                                    trangThai = 4;
//                                } else {
//                                    if (HDinfo.getKey().equalsIgnoreCase("đã hoàn thành")) {
//                                        trangThai = 5;
//                                    } else {
//                                        if (HDinfo.getKey().equalsIgnoreCase("đã hủy")) {
//                                            trangThai = 6;
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//                if (HDinfo.getTu().compareTo(HDinfo.getDen()) >= 0) {
//                    Page<HoaDon> fixErr = dao.findAll(p);
//                    model.addAttribute("lst", fixErr);
//                    model.addAttribute("pageNo", page);
//                    model.addAttribute("errdate", 1);
//                    model.addAttribute("tt0", dao.tinhTong(0));
//                    model.addAttribute("tt1", dao.tinhTong(1));
//                    model.addAttribute("tt2", dao.tinhTong(2));
//                    model.addAttribute("tt3", dao.tinhTong(3));
//                    model.addAttribute("tt4", dao.tinhTong(4));
//                    model.addAttribute("tt5", dao.tinhTong(5));
//                    model.addAttribute("tt6", dao.tinhTong(6));
//                    model.addAttribute("tt7", dao.findAll(p).getTotalElements());
//                    return "admin/qlhoadon";
//                }
//                lst = dao.Loc(trangThai,
//                        Boolean.valueOf(HDinfo.getLoaiHD()), HDinfo.getTu(), HDinfo.getDen(), p);
//            }
//        }
//
//        model.addAttribute("lst", lst);
//        model.addAttribute("pageNo", page);
//        //hiển thị số hd theo tt
//        model.addAttribute("tt0", dao.tinhTong(0));
//        model.addAttribute("tt1", dao.tinhTong(1));
//        model.addAttribute("tt2", dao.tinhTong(2));
//        model.addAttribute("tt3", dao.tinhTong(3));
//        model.addAttribute("tt4", dao.tinhTong(4));
//        model.addAttribute("tt5", dao.tinhTong(5));
//        model.addAttribute("tt6", dao.tinhTong(6));
//        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
//        return "admin/qlhoadon";
//    }

    @GetMapping("cho-xac-nhan")
    public String choXacNhan(Model model, @RequestParam("page") Optional<Integer> pageParam,
                             @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(0, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 0);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("da-xac-nhan")
    public String daXacNhan(Model model, @RequestParam("page") Optional<Integer> pageParam,
                            @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(1, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 1);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("cho-giao-hang")
    public String choGiaoHang(Model model, @RequestParam("page") Optional<Integer> pageParam,
                              @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(2, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 2);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("dang-giao-hang")
    public String dangGiaoHang(Model model, @RequestParam("page") Optional<Integer> pageParam,
                               @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(3, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 3);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("da-thanh-toan")
    public String daThanhToan(Model model, @RequestParam("page") Optional<Integer> pageParam,
                              @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(4, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 4);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("da-hoan-thanh")
    public String daHoanThanh(Model model, @RequestParam("page") Optional<Integer> pageParam,
                              @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(5, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 5);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("da-huy")
    public String daHuy(Model model, @RequestParam("page") Optional<Integer> pageParam,
                        @ModelAttribute("hdcustom") HoaDonCustom info) {
        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        Page<HoaDon> lst = dao.timKiemTT(6, p);
        model.addAttribute("lst", lst);
        model.addAttribute("pageNo", page);
        model.addAttribute("checkTT", 6);
        model.addAttribute("tt0", dao.tinhTong(0));
        model.addAttribute("tt1", dao.tinhTong(1));
        model.addAttribute("tt2", dao.tinhTong(2));
        model.addAttribute("tt3", dao.tinhTong(3));
        model.addAttribute("tt4", dao.tinhTong(4));
        model.addAttribute("tt5", dao.tinhTong(5));
        model.addAttribute("tt6", dao.tinhTong(6));
        model.addAttribute("tt7", dao.findAll(p).getTotalElements());
        return "admin/qlhoadon";
    }

    @GetMapping("chi-tiet/{id}")
    public String chiTiet(Model model, @PathVariable("id") Integer id) {
        idhd = id;
        return "redirect:/hoa-don/showDetail";
    }

    @GetMapping("showDetail")
    public String show(Model model) {

        return "admin/qlchitiethoadon";
    }

    @GetMapping("ban-hang")
    public String taoMoiHoaDon(Model model) {

        return "admin/banhangtaiquay";
    }
}
