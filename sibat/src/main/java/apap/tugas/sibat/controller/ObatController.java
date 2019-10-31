package apap.tugas.sibat.controller;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


import apap.tugas.sibat.model.*;
import apap.tugas.sibat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Autowired
    private JenisService jenisService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private ObatSupplierService obatSupplierService;

    @Autowired
    private GudangObatService gudangObatService;

    @RequestMapping("/")
    public String viewallObat(Model model){

        // Mengambil semua objek ObatModel yang ada
        List<ObatModel> listObat = obatService.getListObat();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("obatList", listObat);
        model.addAttribute("titleNavbar", "Home");
        // Return view template
        return "viewall-obat";
    }

    @RequestMapping(path = "/obat/view", method = RequestMethod.GET)
    public String viewObat(
            //Request Parameter untuk dipass
            @RequestParam(value = "noReg") String nomorRegistrasiObat, Model model
    ) {
        //Mengambil objek RestoranModel yang dituju
        ObatModel obat = obatService.getObatByNomorRegistrasiObat(nomorRegistrasiObat).get();

        List<GudangObatModel> listGudangObat = gudangObatService.getListGudangObat();

        List<GudangModel> listGudang = new ArrayList<GudangModel>();

        for(GudangObatModel gudangObat : listGudangObat){
            if(gudangObat.getObat().getNomorRegistrasiObat().equals(nomorRegistrasiObat)){
                listGudang.add(gudangObat.getGudang());
            }
        }

        List<ObatSupplierModel> listObatSupplier = obatSupplierService.getListObatSupplier();

        List<SupplierModel> listSupplier = new ArrayList<SupplierModel>();

        for(ObatSupplierModel supplierObat : listObatSupplier){
            if(supplierObat.getObat().getNomorRegistrasiObat().equals(nomorRegistrasiObat)){
                listSupplier.add(supplierObat.getSupplier());
            }
        }

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("obat", obat);
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listSupplier", listSupplier);

        //Return view template
        return "view-obat";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
    public String addObatFormPage(Model model){
        ObatModel newObat = new ObatModel();

        List<JenisModel> listJenis = jenisService.getListJenis();
        List<SupplierModel> listSupplier = supplierService.getListSupplier();

        ObatSupplierModel newObatSupplier = new ObatSupplierModel();
        List<ObatSupplierModel> listObatSupplier = new ArrayList<>();
        listObatSupplier.add(newObatSupplier);
        newObat.setListObatSupplier(listObatSupplier);

        model.addAttribute("listJenis", listJenis);
        model.addAttribute("obat", newObat);
        model.addAttribute("listSupplier", listSupplier);
        return "form-add-obat";
    }

    @RequestMapping(value = "/obat", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model, BindingResult result){
        try {
            for (ObatSupplierModel obatSupplier : obat.getListObatSupplier()) {
                obatSupplier.setObat(obat);
            }
            obatService.generateKodeObat(obat);
            obatService.addObat(obat);
            for (ObatSupplierModel obatSupplier2 : obat.getListObatSupplier()) {
                obatSupplierService.addObatSupplier(obatSupplier2);
            }
            String kodeObat = obat.getKodeObat();
            model.addAttribute("kodeObat", obat.getKodeObat());
            model.addAttribute("namaObat", obat.getNamaObat());
            return "add-obat";
        } catch (NullPointerException e){
            return "error-add-obat";
        }

    }

    @RequestMapping(value="obat/ubah", method = RequestMethod.GET)
    public String changeObatFormPage(@RequestParam(value = "id") Long idObat, Model model) {
        //Mengambuk existing data restoran
        ObatModel existingObat = obatService.getObatByIdObat(idObat).get();

        List<JenisModel> listJenis = jenisService.getListJenis();
        List<ObatSupplierModel> listObatSupplier = new ArrayList<>();
        ObatSupplierModel newObatSupplier = new ObatSupplierModel();
        listObatSupplier.add(newObatSupplier);
        model.addAttribute("obat", existingObat);
        model.addAttribute("listJenis", listJenis);
        return "form-change-obat";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value="obat/ubah/{idObat}", method = RequestMethod.POST)
    public String changeObatFormSubmit(@PathVariable Long idObat, @ModelAttribute ObatModel obat, Model model){
        ObatModel newObatData = obatService.changeObat(obat);
        model.addAttribute("obat", newObatData);
        return "change-obat";
    }

    @RequestMapping(value = "/obat", method=RequestMethod.POST, params= {"addRowSupplier"})
    private String addRowSupplier(@ModelAttribute ObatModel obat, BindingResult Br, Model model) {

//        if(obat.getListObatSupplier() == null){
//            obat.setListObatSupplier(new ArrayList<ObatSupplierModel>());
//        }
        obat.getListObatSupplier().add(new ObatSupplierModel());
        List<SupplierModel> listSupplier = supplierService.getListSupplier();
        List<JenisModel> listJenis = jenisService.getListJenis();

        model.addAttribute("obat", obat);
        model.addAttribute("listSupplier", listSupplier);
        model.addAttribute("listJenis", listJenis);
        return "form-add-obat";
    }

    @RequestMapping(path = "/obat/filter", method = RequestMethod.GET)
    public String filterObat(
            @RequestParam(required = false) Long idGudang,@RequestParam(required = false) Long idSupplier, @RequestParam(required = false) Long idJenis, Model model
    ) {
        List<GudangObatModel> listGudangObat = gudangObatService.findAllGudangObatByIdGudang(idGudang);
        List<ObatModel> listObatByGudang = new ArrayList<ObatModel>();
        for(GudangObatModel gudangObat : listGudangObat){
            listObatByGudang.add(gudangObat.getObat());
        }

        List<ObatSupplierModel> listObatSupplier = obatSupplierService.findAllObatSupplierByIdSupplier(idSupplier);
        List<ObatModel> listObatBySupplier = new ArrayList<ObatModel>();
        for(ObatSupplierModel obatSupplier : listObatSupplier){
            listObatBySupplier.add(obatSupplier.getObat());
        }

        List<ObatModel> listObatByJenis = obatService.findAllObatByIdJenis(idJenis);

        if(idGudang != null && idSupplier == null && idJenis == null){
            model.addAttribute("listObat", listObatByGudang);
        }

        else if(idGudang == null && idSupplier != null && idJenis == null){
            model.addAttribute("listObat", listObatBySupplier);
        }

        else if(idGudang == null && idSupplier == null && idJenis != null){
            model.addAttribute("listObat", listObatByJenis);
        }

        else if(idGudang != null && idSupplier != null && idJenis == null){
            Set<ObatModel> setObatByGudangAndSupplier = listObatByGudang.stream()
                    .distinct()
                    .filter(listObatBySupplier::contains)
                    .collect(Collectors.toSet());

            List<ObatModel> listObatByGudangAndSupplier = new ArrayList<>(setObatByGudangAndSupplier);

            model.addAttribute("listObat", listObatByGudangAndSupplier);
        }

        else if(idGudang != null && idSupplier == null && idJenis != null){
            Set<ObatModel> setObatByGudangAndJenis = listObatByGudang.stream()
                    .distinct()
                    .filter(listObatByJenis::contains)
                    .collect(Collectors.toSet());

            List<ObatModel> listObatByGudangAndJenis = new ArrayList<>(setObatByGudangAndJenis);

            model.addAttribute("listObat", listObatByGudangAndJenis);
        }

        else if(idGudang == null && idSupplier != null && idJenis != null){
            Set<ObatModel> setObatBySupplierAndJenis = listObatBySupplier.stream()
                    .distinct()
                    .filter(listObatByJenis::contains)
                    .collect(Collectors.toSet());

            List<ObatModel> listObatBySupplierAndJenis = new ArrayList<>(setObatBySupplierAndJenis);

            model.addAttribute("listObat", listObatBySupplierAndJenis);
        }

        else if(idGudang != null && idSupplier != null && idJenis != null){
            Set<ObatModel> setObatByGudangAndSupplier= listObatByGudang.stream()
                    .distinct()
                    .filter(listObatBySupplier::contains)
                    .collect(Collectors.toSet());

            List<ObatModel> listObatByGudangAndSupplier = new ArrayList<>(setObatByGudangAndSupplier);

            Set<ObatModel> setObatByGudangAndSupplierAndJenis= listObatByGudangAndSupplier.stream()
                    .distinct()
                    .filter(listObatByJenis::contains)
                    .collect(Collectors.toSet());

            List<ObatModel> listObatByGudangAndSupplierAndJenis = new ArrayList<>(setObatByGudangAndSupplierAndJenis);

            model.addAttribute("listObat", listObatByGudangAndSupplierAndJenis);
        }

        else{
            List<ObatModel> listObat = obatService.getListObat();

            model.addAttribute("listObat", listObat);
        }

        List<GudangModel> listGudang = gudangService.getListGudang();
        List<SupplierModel> listSupplier = supplierService.getListSupplier();
        List<JenisModel> listJenis = jenisService.getListJenis();

        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listSupplier", listSupplier);
        model.addAttribute("listJenis", listJenis);


        return "filter-obat";

    }

    @RequestMapping("/obat/bonus")
    public String bonus(Model model){

        // Mengambil semua objek ObatModel yang ada
        List<ObatModel> listObat = obatService.getListObat();
        List<Long> listOfIdObat = new ArrayList<>();

        for(ObatModel obat : listObat){
            listOfIdObat.add(obat.getIdObat());
        }

        List<String> listSizeSupplier = new ArrayList<>();

        for(Long idObat : listOfIdObat){
            listSizeSupplier.add(Integer.toString(obatSupplierService.findAllObatSupplierByIdObat(idObat).size()));
        }

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("obatList", listObat);
        // Return view template
        return "bonus";
    }

}
