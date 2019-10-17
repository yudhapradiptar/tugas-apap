package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ObatDB extends JpaRepository<ObatModel, Long>{
    ObatModel findByIdObat(Long idObat);
}
