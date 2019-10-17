package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.ObatModel;

public interface ObatService {

    // method untuk menambah Obat
    void addObat(ObatModel obat);

    // method untuk mendapatkan list obat
    List<ObatModel> getListObat();
}
