package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.GudangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface GudangDB extends JpaRepository<GudangModel, Long>{
    GudangModel findByIdGudang(Long idGudang);
}
