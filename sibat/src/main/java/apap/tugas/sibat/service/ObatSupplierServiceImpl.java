package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.ObatSupplierModel;
import apap.tugas.sibat.repository.GudangObatDB;
import apap.tugas.sibat.repository.ObatSupplierDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatSupplierServiceImpl implements ObatSupplierService{
    @Autowired
    private ObatSupplierDB obatSupplierDb;

    @Override
    public List<ObatSupplierModel> getListObatSupplier() {return obatSupplierDb.findAll();}

    @Override
    public List<ObatSupplierModel> findAllObatSupplierByIdSupplier(Long idSupplier){
        return obatSupplierDb.findBySupplierIdSupplier(idSupplier);
    }

    //@Override
    //public List<ObatModel> getListObatByIdSupplier(Long idSupplier) { return obatSupplierDb.findListObatByIdSupplier(idSupplier);}
}
