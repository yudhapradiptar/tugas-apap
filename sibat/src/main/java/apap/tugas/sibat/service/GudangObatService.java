package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;

import java.util.List;
import java.util.Optional;


public interface GudangObatService {
    List<GudangObatModel> getListGudangObat();

    List<GudangObatModel> findAllGudangObatByIdGudang(Long idGudang);

    List<GudangObatModel> findAllGudangObatByIdObat(Long idObat);

    void addGudangObat(GudangObatModel gudangObat);

    //List<ObatModel> getListObatByIdGudang(Long idGudang);
}
