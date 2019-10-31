package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.ObatSupplierModel;

import java.util.List;
import java.util.Optional;


public interface ObatSupplierService {
    //List<ObatModel> getListObatByIdSupplier(Long idSupplier);

    List<ObatSupplierModel> getListObatSupplier();

    List<ObatSupplierModel> findAllObatSupplierByIdSupplier(Long idSupplier);

    void addObatSupplier(ObatSupplierModel obatSupplier);

    List<ObatSupplierModel> findAllObatSupplierByIdObat(Long idObat);
}
