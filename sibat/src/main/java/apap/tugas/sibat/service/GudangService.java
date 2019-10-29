package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.GudangModel;

public interface GudangService {

    void addGudang(GudangModel gudang);

    // method untuk mendapatkan list obat
    List<GudangModel> getListGudang();

    Optional<GudangModel> getGudangByIdGudang(Long idGudang);

    boolean deleteGudang(GudangModel gudang);
}
