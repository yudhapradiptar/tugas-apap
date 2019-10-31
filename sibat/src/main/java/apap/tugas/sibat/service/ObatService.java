package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.ObatSupplierModel;

public interface ObatService {

    // method untuk menambah Obat
    void addObat(ObatModel obat);

    // method untuk mendapatkan list obat
    List<ObatModel> getListObat();

    //method untuk mendapatkan obat berdasarkan nomor registrasi
    Optional<ObatModel> getObatByNomorRegistrasiObat(String nomorRegistrasiObat);

    ObatModel changeObat(ObatModel obatModel);

    Optional<ObatModel> getObatByIdObat(Long idObat);

    List<ObatModel> findAllObatByIdJenis(Long idJenis);

    ObatModel generateKodeObat(ObatModel obat);



}
