package apap.tugas.sibat.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sibat.model.SupplierModel;

public interface SupplierService {

    void addSupplier(SupplierModel gudang);

    // method untuk mendapatkan list obat
    List<SupplierModel> getListSupplier();
}
