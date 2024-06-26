package com.example.demo.controller.sanpham;

import com.example.demo.entity.*;
import com.example.demo.info.SanPhamInfo;
import com.example.demo.info.ThuocTinhInfo;
import com.example.demo.repository.*;
import com.example.demo.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Controller
public class SanPhamController {
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Autowired
    DeGiayRepository deGiayRepository;
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


    @GetMapping("/listsanpham")
    public String hienthi(@RequestParam(defaultValue = "0") int p, @ModelAttribute("tim") SanPhamInfo info, Model model) {
        Pageable pageable = PageRequest.of(p, 20);
        Page<Object[]> page = null;
        if (info.getKey() != null) {
            page = sanPhamRepositoty.findByMasanphamAndTenSanPhamAndTrangThai("%" + info.getKey() + "%", "%" + info.getKey() + "%", info.getTrangthai(), pageable);
        } else {
            page = sanPhamRepositoty.findProductsWithTotalQuantityOrderByDateDesc(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("fillSearch", info.getKey());
        model.addAttribute("fillTrangThai", info.getTrangthai());
        return "admin/qlsanpham";
    }

    @RequestMapping(value = {"/viewaddSPGET", "/viewaddSPPOST"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String viewaddSP(Model model, @RequestParam(defaultValue = "0") int p,
                            @ModelAttribute("thuonghieu") ThuongHieu thuongHieu,
                            @ModelAttribute("chatlieu") ChatLieu chatLieu,
                            @ModelAttribute("kichco") KichCo kichCo,
                            @ModelAttribute("degiay") DeGiay deGiay,
                            @ModelAttribute("mausac") MauSac mauSac,
                            @ModelAttribute("tim") ThuocTinhInfo info
    ) {
        List<DeGiay> listDG = null;
        if (info.getKey() != null) {
            listDG = deGiayImp.getDeGiayByTenOrTrangthai(info.getKey(), info.getTrangthai());
        } else {
            listDG = deGiayRepository.findAllByOrderByNgaytaoDesc();
        }
        model.addAttribute("listDG", listDG);
        List<ThuongHieu> listTH = null;
        if (info.getKey() != null) {
            listTH = thuongHieuImp.getThuongHieuByTenOrTrangthai(info.getKey(), info.getTrangthai());
        } else {
            listTH = thuongHieuRepository.findAllByOrderByNgaytaoDesc();
        }
        model.addAttribute("listTH", listTH);

        List<ChatLieu> listCL = null;
        if (info.getKey() != null) {
            listCL = chatLieuRepository.getChatLieuByTenOrTrangthai(info.getKey(), info.getTrangthai());
        } else {
            listCL = chatLieuRepository.findAllByOrderByNgaytaoDesc();
        }
        model.addAttribute("listCL", listCL);
        List<MauSac> listMS = null;
        if (info.getKey() != null) {
            listMS = mauSacRepository.getDeGiayByTenOrTrangthai(info.getKey(), info.getTrangthai());
        } else {
            listMS = mauSacRepository.findAllByOrderByNgaytaoDesc();
        }
        model.addAttribute("listMS", listMS);


        List<SanPham> listSanPham = sanPhamImp.findAll();
        List<SanPhamChiTiet> listSPCT = sanPhamChiTietImp.findAll();
        List<ThuongHieu> listThuongHieu = thuongHieuImp.findAll();
        List<MauSac> listMauSac = mauSacImp.findAll();
        List<KichCo> listKichCo = kichCoImp.findAll();
        List<DeGiay> listDeGiay = deGiayImp.findAll();
        List<ChatLieu> listChatLieu = chatLieuImp.findAll();
        List<Anh> listAnh = anhImp.findAll();
        model.addAttribute("sp", listSanPham);
        model.addAttribute("spct", listSPCT);
        model.addAttribute("th", listThuongHieu);
        model.addAttribute("ms", listMauSac);
        model.addAttribute("kc", listKichCo);
        model.addAttribute("dg", listDeGiay);
        model.addAttribute("cl", listChatLieu);
        model.addAttribute("a", listAnh);
        return "admin/addsanpham";
    }

    List<SanPham> sanPhamList = new ArrayList<>();
    List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(defaultValue = "0") int p, Model model,
                             @RequestParam String tensp,
                             @RequestParam String mota,
                             @RequestParam ThuongHieu idThuongHieu,
                             @RequestParam ChatLieu idChatLieu,
                             @RequestParam Boolean gioitinh,
                             @RequestParam(name = "kichCoId") List<String> kichCoNames,
                             @RequestParam DeGiay idDeGiay,
                             @RequestParam List<MauSac> idMauSac
    ) {
        model.addAttribute("selectedTensp", tensp);
        model.addAttribute("motas", mota);
        model.addAttribute("gioitinh", gioitinh);
        Integer nextId = sanPhamRepositoty.findMaxIdSP();
        Integer nextId2 = sanPhamChiTietRepository.findMaxIdSPCT();
        if (nextId == null || nextId2 == null) {
            return "redirect:/error";
        }
        nextId++;
        SanPham sanPham = new SanPham();
        int doDaiChuoi = 10;
        // Chuỗi chứa tất cả các ký tự có thể có trong chuỗi ngẫu nhiên
        String kiTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // Tạo đối tượng Random
        Random random = new Random();
        // StringBuilder để xây dựng chuỗi ngẫu nhiên
        StringBuilder chuoiNgauNhien = new StringBuilder(doDaiChuoi);
        // Lặp để thêm ký tự ngẫu nhiên vào chuỗi
        for (int i = 0; i < doDaiChuoi; i++) {
            // Lấy một ký tự ngẫu nhiên từ chuỗi kiTu và thêm vào chuỗi ngẫu nhiên
            chuoiNgauNhien.append(kiTu.charAt(random.nextInt(kiTu.length())));
        }
        sanPham.setId(nextId);
        sanPham.setMasanpham(chuoiNgauNhien.toString());
        sanPham.setTensanpham(tensp);
        sanPham.setTrangthai(true);
        LocalDateTime currentTime = LocalDateTime.now();
        sanPham.setNgaytao(currentTime);
        sanPhamList.add(sanPham);
        for (SanPham sp : sanPhamList) {
            System.out.println("idsp:" + sp.getId());
            System.out.println("tensp:" + sp.getTensanpham());
        }
        if (sanPhamChiTietList == null || sanPhamChiTietList.isEmpty()) {
            for (MauSac colorId : idMauSac) {
                for (String sizeName : kichCoNames) {
                    KichCo kichCo = kichCoRepository.findByTen(sizeName);
                    if (kichCo != null) {
                        int doDaiChuoi2 = 10;
                        // Chuỗi chứa tất cả các ký tự có thể có trong chuỗi ngẫu nhiên
                        String kiTu2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                        // Tạo đối tượng Random
                        Random random2 = new Random();
                        // StringBuilder để xây dựng chuỗi ngẫu nhiên
                        StringBuilder chuoiNgauNhien2 = new StringBuilder(doDaiChuoi2);
                        // Lặp để thêm ký tự ngẫu nhiên vào chuỗi
                        for (int i = 0; i < doDaiChuoi2; i++) {
                            // Lấy một ký tự ngẫu nhiên từ chuỗi kiTu và thêm vào chuỗi ngẫu nhiên
                            chuoiNgauNhien2.append(kiTu2.charAt(random2.nextInt(kiTu2.length())));
                        }
                        nextId2++;
                        SanPhamChiTiet spct = new SanPhamChiTiet();
                        spct.setId(nextId2);
                        spct.setMasanphamchitiet(chuoiNgauNhien2.toString());
                        spct.setSanpham(sanPham);
                        spct.setSoluong(1);
                        spct.setGiatien(BigDecimal.valueOf(100000.000));
                        spct.setMota(mota);
                        spct.setThuonghieu(idThuongHieu);
                        spct.setChatlieu(idChatLieu);
                        spct.setGioitinh(gioitinh);
                        spct.setTrangthai(true);
                        spct.setKichco(kichCo);
                        spct.setDegiay(idDeGiay);
                        spct.setMausac(colorId);
                        sanPhamChiTietList.add(spct);
                        for (SanPhamChiTiet spcts : sanPhamChiTietList) {
                            System.out.println("idspct:" + spcts.getId());
                            System.out.println("mausac:" + spcts.getKichco().getTen());
                            System.out.println("kichco:" + spcts.getMausac().getTen());
                        }
                    }
                }
            }
        } else {
            for (MauSac colorId : idMauSac) {
                for (String sizeName : kichCoNames) {
                    KichCo kichCo = kichCoRepository.findByTen(sizeName);
                    if (kichCo != null) {
                        boolean found = false;
                        if(sanPhamChiTietList!=null){
                            for (SanPhamChiTiet spct2 : sanPhamChiTietList) {
                                if (spct2.getMausac().getId() == colorId.getId() && spct2.getKichco().getTen().equals(sizeName)) {
                                    spct2.setSoluong(spct2.getSoluong() + 1);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if(!found){
                            int doDaiChuoi2 = 10;
                            // Chuỗi chứa tất cả các ký tự có thể có trong chuỗi ngẫu nhiên
                            String kiTu2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                            // Tạo đối tượng Random
                            Random random2 = new Random();
                            // StringBuilder để xây dựng chuỗi ngẫu nhiên
                            StringBuilder chuoiNgauNhien2 = new StringBuilder(doDaiChuoi2);
                            // Lặp để thêm ký tự ngẫu nhiên vào chuỗi
                            for (int i = 0; i < doDaiChuoi2; i++) {
                                // Lấy một ký tự ngẫu nhiên từ chuỗi kiTu và thêm vào chuỗi ngẫu nhiên
                                chuoiNgauNhien2.append(kiTu2.charAt(random2.nextInt(kiTu2.length())));
                            }
                            int lastIndex = sanPhamChiTietList.size() - 1;
                            SanPhamChiTiet lastItem = sanPhamChiTietList.get(lastIndex);
                            int count = lastItem.getId();
                            count++;
                            SanPhamChiTiet spct = new SanPhamChiTiet();
                            spct.setId(count);
                            spct.setSanpham(sanPham);
                            spct.setMasanphamchitiet(chuoiNgauNhien2.toString());
                            spct.setSoluong(1);
                            spct.setGiatien(BigDecimal.valueOf(100000.000));
                            spct.setMota(mota);
                            spct.setThuonghieu(idThuongHieu);
                            spct.setChatlieu(idChatLieu);
                            spct.setGioitinh(gioitinh);
                            spct.setTrangthai(true);
                            spct.setKichco(kichCo);
                            spct.setDegiay(idDeGiay);
                            spct.setMausac(colorId);
                            sanPhamChiTietList.add(spct);
                        }

                    }
                }
            }
        }
        model.addAttribute("sanphamchitiet", sanPhamChiTietList);
        return "forward:/viewaddSPPOST";
    }


    @GetMapping("/deleteCTSP/{id}")
    public String deleteCTSP(@PathVariable Integer id, Model model) {
        for (Iterator<SanPhamChiTiet> iterator = sanPhamChiTietList.iterator(); iterator.hasNext(); ) {
            SanPhamChiTiet spct = iterator.next();
            if (spct.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
        model.addAttribute("sanphamchitiet", sanPhamChiTietList);
        return "forward:/viewaddSPPOST";
    }


    @PostMapping("/addImage")
    public String addImage(
            Model model,
            @RequestParam(name = "anh1") List<MultipartFile> anhFiles1,
            @RequestParam(name = "anh2") List<MultipartFile> anhFiles2,
            @RequestParam(name = "anh3") List<MultipartFile> anhFiles3,
            @RequestParam(name = "spctId") List<Integer> spctIds
    ) {
        for (SanPham sanPham : sanPhamList) {
            SanPham savedSanPham = sanPhamRepositoty.save(sanPham);
            for (SanPhamChiTiet spct : sanPhamChiTietList) {
                if (spct.getSanpham().equals(sanPham)) {
                    spct.setSanpham(savedSanPham);
                    sanPhamChiTietRepository.save(spct);
                }
            }
        }
        sanPhamList.clear();
        sanPhamChiTietList.clear();
        if (anhFiles1.size() != anhFiles2.size() || anhFiles1.size() != anhFiles3.size() || anhFiles1.size() != spctIds.size()) {
            System.out.println("Số lượng phần tử của các danh sách không khớp");
            return "redirect:/error";
        }
        for (int i = 0; i < spctIds.size(); i++) {
            Integer spctId = spctIds.get(i);
            SanPhamChiTiet spct = sanPhamChiTietRepository.findById(spctId).orElse(null);
            if (spct != null) {
                MultipartFile anhFile1 = anhFiles1.get(i);
                addAnh(spct, anhFile1);
                MultipartFile anhFile2 = anhFiles2.get(i);
                addAnh(spct, anhFile2);
                MultipartFile anhFile3 = anhFiles3.get(i);
                addAnh(spct, anhFile3);
            }
        }
        return "redirect:/listsanpham";
    }

    private void addAnh(SanPhamChiTiet spct, MultipartFile anhFile) {
        if (!anhFile.isEmpty()) {
            String anhUrl = saveImage(anhFile);
            if (anhUrl != null) {
                Anh anh = new Anh();
                LocalDateTime currentTime = LocalDateTime.now();
                anh.setTenanh(anhUrl);
                anh.setNgaytao(currentTime);
                anh.setSanphamchitiet(spct);
                anhRepository.save(anh);
            }
        }
    }

    private String saveImage(MultipartFile file) {
        String uploadDir = "G:\\Ki7\\DATN\\DATN\\src\\main\\resources\\static\\upload";
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String filePath = uploadDir + File.separator + originalFileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/detailsanpham/{id}")
    public String detailsanpham(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamRepositoty.findById(id).orElse(null);
        model.addAttribute("sanpham", sanPham);
        model.addAttribute("sanphamchitiet", sanPham.getSpct());
        return "admin/qlchitietsanpham";
    }

    @PostMapping("/updateGiaAndSoLuong")
    public String updateGiaAndSoLuong(
            Model model,
            @RequestParam("soluong") Integer soluong,
            @RequestParam("giatien") BigDecimal giatien,
            @RequestParam("choncheckbox") String[] choncheckbox
    ) {
        List<String> listString = Arrays.asList(choncheckbox);
        List<Integer> listInt = new ArrayList<>();
        for (String s : listString) {
            Integer i = Integer.parseInt(s);
            listInt.add(i);
        }
        listInt.remove(Integer.valueOf(-1));
        for (Integer id : listInt) {
            for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList) {
                if (sanPhamChiTiet.getId().equals(id)) {
                    sanPhamChiTiet.setGiatien(giatien);
                    sanPhamChiTiet.setSoluong(soluong);
                }
            }
        }
        model.addAttribute("sanphamchitiet", sanPhamChiTietList);
        return "forward:/viewaddSPPOST";
    }
}
