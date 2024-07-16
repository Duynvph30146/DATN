package com.example.demo.controller.chatlieu;

import com.example.demo.entity.ChatLieu;
import com.example.demo.info.ThuocTinhInfo;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatLieuController {
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping("/chatlieu")
    public String display(@ModelAttribute("search") ThuocTinhInfo info, @ModelAttribute("chatlieu") ChatLieu chatLieu, Model model) {
        List<ChatLieu> list;

        boolean isKeyEmpty = (info.getKey() == null || info.getKey().trim().isEmpty());
        boolean isTrangthaiNull = (info.getTrangthai() == null);

        if (isKeyEmpty && isTrangthaiNull) {
            list = chatLieuRepository.getAll();
        } else {
            list = chatLieuRepository.findChatLieuByTenAndTrangThaiFalse(info.getKey());
        }

        model.addAttribute("fillSearch", info.getKey());
        model.addAttribute("fillTrangThai", info.getTrangthai());
        model.addAttribute("lstChatLieu", list);
        return "admin/qlchatlieu";
    }

    @PostMapping("/updateChatLieu/{id}")
    public String updateChatLieu(@PathVariable Integer id) {
        chatLieuRepository.updateTrangThaiToFalseById(id);
        return "redirect:/chatlieu";
    }
    @PostMapping("/add")
    public String add(Model model, @ModelAttribute("chatlieu") ChatLieu chatLieu) {
        String trimmedTenChatLieu = (chatLieu.getTen() != null)
                ? chatLieu.getTen().trim().replaceAll("\\s+", " ")
                : null;
        LocalDateTime currentTime = LocalDateTime.now();
        chatLieu.setTen(trimmedTenChatLieu);
        chatLieu.setTrangthai(true);
        chatLieu.setNgaytao(currentTime);
        chatLieu.setLancapnhatcuoi(currentTime);
        chatLieuService.add(chatLieu);
        return "redirect:/chatlieu";
    }

    @PostMapping("/addChatLieuModal")
    public String addChatLieuModal(Model model, @ModelAttribute("chatlieu") ChatLieu chatLieu) {
        String trimmedTenChatLieu = (chatLieu.getTen() != null)
                ? chatLieu.getTen().trim().replaceAll("\\s+", " ")
                : null;
        LocalDateTime currentTime = LocalDateTime.now();
        chatLieu.setTen(trimmedTenChatLieu);
        chatLieu.setTrangthai(true);
        chatLieu.setNgaytao(currentTime);
        chatLieu.setLancapnhatcuoi(currentTime);
        chatLieuService.add(chatLieu);
        return "redirect:/viewaddSPGET";
    }

    @PostMapping("/addChatLieuSua")
    public String addChatLieuSua(Model model, @ModelAttribute("chatlieu") ChatLieu chatLieu, @RequestParam("spctId") Integer spctId) {
        String trimmedTenChatLieu = (chatLieu.getTen() != null)
                ? chatLieu.getTen().trim().replaceAll("\\s+", " ")
                : null;
        LocalDateTime currentTime = LocalDateTime.now();
        chatLieu.setTen(trimmedTenChatLieu);
        chatLieu.setTrangthai(true);
        chatLieu.setNgaytao(currentTime);
        chatLieu.setLancapnhatcuoi(currentTime);
        chatLieuService.add(chatLieu);
        return "redirect:/updateCTSP/" + spctId;
    }

    @GetMapping("/chatlieu/delete/{id}")
    public String delete(@PathVariable int id) {
        chatLieuService.deleteById(id);
        return "redirect:/chatlieu";
    }
}
