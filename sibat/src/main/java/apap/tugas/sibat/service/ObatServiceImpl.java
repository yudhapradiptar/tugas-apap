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

    @Override
    public Optional<ObatModel> getObatByNomorRegistrasiObat(String nomorRegistrasiObat) {return obatDb.findByNomorRegistrasiObat(nomorRegistrasiObat);}

    @Override
    public Optional<ObatModel> getObatByIdObat(Long idObat) {return obatDb.findByIdObat(idObat);}

    @Override
    public ObatModel changeObat(ObatModel obatModel){
        // mengambil object restoran yang ingin diubah
        ObatModel targetObat = obatDb.findById(obatModel.getIdObat()).get();

        try{
            targetObat.setNamaObat(obatModel.getNamaObat());
            targetObat.setTanggalTerbitObat(obatModel.getTanggalTerbitObat());
            targetObat.setHargaObat(obatModel.getHargaObat());
            targetObat.setBentukObat(obatModel.getBentukObat());
            obatDb.save(targetObat);
            return targetObat;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public List<ObatModel> findAllObatByIdJenis(Long idJenis){
        return obatDb.findByJenisIdJenis(idJenis);
    }

}
