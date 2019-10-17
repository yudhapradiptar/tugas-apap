package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.repository.ObatDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDB obatDb;

    @Override
    public void addObat(ObatModel obat) { obatDb.save(obat);}

    @Override
    public List<ObatModel> getListObat() { return obatDb.findAll();}
}
