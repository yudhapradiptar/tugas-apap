package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.repository.GudangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

public class GudangServiceImpl implements GudangService{
    @Autowired
    private GudangDB gudangDb;

    @Override
    public void addGudang(GudangModel gudang) { gudangDb.save(gudang);}

    @Override
    public List<GudangModel> getListGudang() { return gudangDb.findAll();}
    
}
