package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface SupplierDB extends JpaRepository<SupplierModel, Long>{
    SupplierModel findByIdSupplier(Long idSupplier);
}
