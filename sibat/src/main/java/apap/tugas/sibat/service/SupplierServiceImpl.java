package apap.tugas.sibat.service;

import apap.tugas.sibat.model.SupplierModel;
import apap.tugas.sibat.repository.SupplierDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierDB supplierDb;

    @Override
    public void addSupplier(SupplierModel supplier) { supplierDb.save(supplier);}

    @Override
    public List<SupplierModel> getListSupplier() { return supplierDb.findAll();}
}
