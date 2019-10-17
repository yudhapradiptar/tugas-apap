package apap.tugas.sibat.service;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.repository.JenisDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JenisServiceImpl implements JenisService{
    @Autowired
    private JenisDB jenisDb;

    @Override
    public void addJenis(JenisModel jenis) { jenisDb.save(jenis);}

    @Override
    public List<JenisModel> getListJenis() { return jenisDb.findAll();}
    
}
