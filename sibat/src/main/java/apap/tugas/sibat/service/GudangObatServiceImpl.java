package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.repository.GudangObatDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GudangObatServiceImpl implements GudangObatService{
    @Autowired
    private GudangObatDB gudangObatDb;

    @Override
    public List<GudangObatModel> getListGudangObat() {return gudangObatDb.findAll();}

    @Override
    public List<GudangObatModel> findAllGudangObatByIdGudang(Long idGudang){
        return gudangObatDb.findByGudangIdGudang(idGudang);
    }

    @Override
    public List<GudangObatModel> findAllGudangObatByIdObat(Long idObat){
        return gudangObatDb.findByObatIdObat(idObat);
    }

    @Override
    public void addGudangObat(GudangObatModel gudangObat) { gudangObatDb.save(gudangObat);}

    //@Override
    //public List<ObatModel> getListObatByIdGudang(Long idGudang) { return gudangObatDb.findListObatByIdGudang(idGudang);}
}
