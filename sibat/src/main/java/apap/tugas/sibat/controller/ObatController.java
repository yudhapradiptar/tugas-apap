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
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;


    //URL mapping yang digunakan untuk mengakses halaman add restoran

}
