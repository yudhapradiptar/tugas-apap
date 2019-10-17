package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface JenisDB extends JpaRepository<JenisModel, Long>{
    JenisModel findByIdJenis(Long idJenis);

}
