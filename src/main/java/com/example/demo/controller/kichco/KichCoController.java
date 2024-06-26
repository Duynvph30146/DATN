package com.example.demo.controller.kichco;

import com.example.demo.entity.DeGiay;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.info.ThuocTinhInfo;
import com.example.demo.repository.KichCoRepository;
import com.example.demo.service.impl.KichCoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class KichCoController {
    @Autowired
    KichCoImp kichCoImp;

    @Autowired
    KichCoRepository kichCoRepository;

    @GetMapping("/listKichCo")
    public String listKichCo(Model model, @ModelAttribute("kichco") KichCo kichCo, @ModelAttribute("tim") ThuocTinhInfo info) {
        List<KichCo> page;
        boolean isKeyEmpty = (info.getKey() == null || info.getKey().trim().isEmpty());
        boolean isTrangthaiNull = (info.getTrangthai() == null);

        if (isKeyEmpty && isTrangthaiNull) {
            page = kichCoRepository.findAllByOrderByNgaytaoDesc();
        } else {
            page = kichCoRepository.getDeGiayByTenOrTrangthai(info.getKey(), info.getTrangthai());
        }
        model.addAttribute("fillSearch", info.getKey());
        model.addAttribute("fillTrangThai", info.getTrangthai());
        model.addAttribute("list", page);
        return "admin/qlkichco";
    }

    @PostMapping("/addSaveKichCo")
    @CacheEvict(value = "kichcoCache", allEntries = true)
    public String addSave(@ModelAttribute("kichco") KichCo kichCo, @ModelAttribute("kc") ThuocTinhInfo info, Model model) {
        LocalDateTime currentTime = LocalDateTime.now();
        kichCo.setTrangthai(true);
        kichCo.setNgaytao(currentTime);
        kichCo.setLancapnhatcuoi(currentTime);
        kichCoRepository.save(kichCo);
        return "redirect:/listKichCo";
    }
    @PostMapping("/addKichCoModal")
    public String addKichCoModal(@ModelAttribute("kichco") KichCo kichCo) {
        LocalDateTime currentTime = LocalDateTime.now();
        kichCo.setTrangthai(true);
        kichCo.setNgaytao(currentTime);
        kichCo.setLancapnhatcuoi(currentTime);
        kichCoImp.addKichCo(kichCo);
        return "redirect:/viewaddSPGET";
    }
}
