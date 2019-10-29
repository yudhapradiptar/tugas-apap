package apap.tugas.sibat.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import apap.tugas.sibat.model.*;
import apap.tugas.sibat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GudangObatController {
    @Qualifier("gudangObatServiceImpl")
    @Autowired
    private GudangObatService gudangObatService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private GudangService gudangService;

    @RequestMapping(value = "/gudang/tambah-obat", method = RequestMethod.POST)
    public String assignObatGudangSubmit(@ModelAttribute GudangObatModel gudangObat, Long idGudang,@RequestParam Long idObat,  Model model){
        GudangObatModel newGudangObat = new GudangObatModel();
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();
        ObatModel obat = obatService.getObatByIdObat(idObat).get();
        LocalDate dateNow = LocalDate.now();

        gudangObat.setGudang(gudang);
        gudangObat.setObat(obat);
        gudangObatService.addGudangObat(gudangObat);
        model.addAttribute("gudangObat", gudangObat);
        model.addAttribute("gudang", gudang);
        model.addAttribute("obat", obat);
        model.addAttribute("dateNow", dateNow);
        return "assign-gudang";
    }
}
