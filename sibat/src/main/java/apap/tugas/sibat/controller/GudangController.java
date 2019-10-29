package apap.tugas.sibat.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;


import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.ObatService;
import apap.tugas.sibat.service.GudangObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.service.GudangService;

@Controller
public class GudangController {
    @Qualifier("gudangServiceImpl")
    @Autowired
    private GudangService gudangService;

    @Autowired
    private GudangObatService gudangObatService;

    @Autowired
    private ObatService obatService;

    @RequestMapping("/gudang")
    public String viewallGudang(Model model){

        // Mengambil semua objek ObatModel yang ada
        List<GudangModel> listGudang = gudangService.getListGudang();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("gudangList", listGudang);
        model.addAttribute("titleNavbar", "Melihat Daftar Gudang");
        // Return view template
        return "viewall-gudang";
    }

    @RequestMapping(path = "/gudang/view", method = RequestMethod.GET)
    public String viewGudang(
            @RequestParam(value = "idGudang") Long idGudang, Model model
    ) {
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();

        List<GudangObatModel> listGudangObat = gudangObatService.findAllGudangObatByIdGudang(idGudang);


        List<ObatModel> listObatDiGudang = new ArrayList<ObatModel>();

        for(GudangObatModel gudangObat: listGudangObat){
            listObatDiGudang.add(gudangObat.getObat());
        }

        int jumlahObat = listObatDiGudang.size();

        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("gudang", gudang);
        model.addAttribute("jumlahObat", jumlahObat);
        model.addAttribute("listObatDiGudang", listObatDiGudang);
        model.addAttribute("listObat", listObat);

        //Return view template
        return "view-gudang";
    }
    @RequestMapping(path = "/gudang/expired-obat", method = RequestMethod.GET)
    public String viewGudangExpiredObat(
            @RequestParam(value = "idGudang", required = false) Long idGudang, Model model
    ) {
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();

        List<GudangObatModel> listGudangObat = gudangObatService.findAllGudangObatByIdGudang(idGudang);

            List<ObatModel> listObatExpired = new ArrayList<ObatModel>();

            for(GudangObatModel gudangObat: listGudangObat){
                ObatModel obatDiCek = gudangObat.getObat();
                Date tanggalTerbitObatDiCek = obatDiCek.getTanggalTerbitObat();
                Date currentDate = new Date();
                Calendar calendar  = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -5);
                currentDate = calendar.getTime();
                if(tanggalTerbitObatDiCek.before(currentDate)){
                    listObatExpired.add(gudangObat.getObat());
                }
            }
            model.addAttribute("gudang", gudang);
            model.addAttribute("listObatExpired", listObatExpired);

            //Return view template
            return "gudang-expired-obat";
    }
    @RequestMapping(value = "/gudang/form-expired-obat", method = RequestMethod.GET)
    public String expiredObatSubmit(Model model){
        List<GudangModel> listGudang = gudangService.getListGudang();
        GudangModel gudangModel = new GudangModel();
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("gudang", gudangModel);
        return "form-gudang-expired-obat";
    }
//    @RequestMapping("/gudang/expired-obat")
//    public String formGudangExpiredObat(Model model){
//        List<GudangModel> listGudang = gudangService.getListGudang();
//
//        // Add model restoran ke "resto" untuk dirender
//        model.addAttribute("gudangList", listGudang);
//        // Return view template
//        return "form-gudang-expired-obat";
//    }





    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.GET)
    public String addGudangFormPage(Model model){
        GudangModel newGudang = new GudangModel();
        model.addAttribute("gudang", newGudang);
        return "form-add-gudang";
    }
    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.POST)
    public String addGudangSubmit(@ModelAttribute GudangModel gudang, Model model){
        gudangService.addGudang(gudang);
        model.addAttribute("namaGudang", gudang.getNamaGudang());
        return "add-gudang";
    }
    @RequestMapping(value="/gudang/hapus/{idGudang}", method = RequestMethod.GET)
    public String deleteGudang(@PathVariable Long idGudang,GudangModel gudang, Model model){
        String strIdGudang = String.valueOf(idGudang);
        GudangModel gudangDeleted = gudangService.getGudangByIdGudang(idGudang).get();
        boolean isDeleted = gudangService.deleteGudang(gudangDeleted);
        model.addAttribute("idGudang", strIdGudang);
        if(isDeleted==true){
            return "delete-gudang";
        } else{return "error-delete-restoran";}
    }
}
