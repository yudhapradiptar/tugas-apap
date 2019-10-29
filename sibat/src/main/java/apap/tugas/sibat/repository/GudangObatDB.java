package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface GudangObatDB extends JpaRepository<GudangObatModel, Long>{
    List<GudangObatModel> findByGudangIdGudang(Long idGudang);

    List<GudangObatModel> findByObatIdObat(Long idObat);



}
