package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.JenisModel;

public interface JenisService {

    void addJenis(JenisModel gudang);

    // method untuk mendapatkan list obat
    List<JenisModel> getListJenis();
}