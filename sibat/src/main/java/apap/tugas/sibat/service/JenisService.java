package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.model.ObatModel;

public interface JenisService {
    // method untuk mendapatkan list obat
    List<JenisModel> getListJenis();

    List<Optional<ObatModel>> getListObatByIdJenis(Long idJenis);
}