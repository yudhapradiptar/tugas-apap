package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.ObatSupplierModel;
import apap.tugas.sibat.repository.ObatDB;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    @Override
    public ObatModel generateKodeObat(ObatModel obat){
        String kodeGenerated = "";
        kodeGenerated+=obat.getJenis().getIdJenis();
        switch(obat.getBentukObat()){
            case "Cairan":
                kodeGenerated+="01";
                break;
            case "Kapsul":
                kodeGenerated+="02";
                break;
            case "Tablet":
                kodeGenerated+="03";
                break;
        }
        LocalDate date = LocalDate.now();
        String kodeTahunDimasukan = Integer.toString(date.getYear());
        kodeGenerated+=kodeTahunDimasukan;
        String kodeTahunTerbit = Integer.toString(obat.getTanggalTerbitObat().getYear()+5);
        kodeGenerated+=kodeTahunTerbit;
        String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        String kodeRandom = "";
        for(int i=0;i<2;i++){
            kodeRandom+=alfabet.charAt(r.nextInt(alfabet.length()));
        }
        kodeGenerated+=kodeRandom;
        for(ObatModel obatLainya : getListObat()){
            if(obatLainya.getKodeObat().equals(kodeGenerated)){
                generateKodeObat(obat);
            }
        }
        obat.setKodeObat(kodeGenerated);
        return obat;
    }

}
