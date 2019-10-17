package apap.tugas.sibat.controller;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import apap.tugas.sibat.service.JenisService;
import apap.tugas.sibat.model.JenisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.ObatService;

@Controller
public class JenisController {
    @Qualifier("jenisServiceImpl")
    @Autowired
    private JenisService jenisService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("titleNavbar", "Home");
        return "home";
    }

    @RequestMapping(value = "/jenis/add", method = RequestMethod.GET)
    public String addJenisFormPage(Model model){
        JenisModel newJenis = new JenisModel();
        model.addAttribute("titleNavbar", "Add Jenis");
        model.addAttribute("jenis", newJenis);
        return "form-add-jenis";
    }

    @RequestMapping(value = "/jenis/add", method = RequestMethod.POST)
    public String addJenisSubmit(@ModelAttribute JenisModel jenis, Model model){
        jenisService.addJenis(jenis);
        model.addAttribute("titleNavbar", "Add Jenis");
        model.addAttribute("namaJenis", jenis.getNamaJenis());
        return "add-jenis";
    }
}
