package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.SupplierModel;
import apap.tugas.sibat.model.ObatSupplierModel;
import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ObatSupplierDB extends JpaRepository<ObatSupplierModel, Long>{
    List<ObatSupplierModel> findBySupplierIdSupplier(Long idSupplier);
}
