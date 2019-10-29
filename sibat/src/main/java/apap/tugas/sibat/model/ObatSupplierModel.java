package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="obat_supplier")
public class ObatSupplierModel {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObatSupplier;

    @ManyToOne
    @JoinColumn(name="idObat")
    ObatModel obat;

    @ManyToOne
    @JoinColumn(name="idSupplier")
    SupplierModel supplier;

    public Long getIdObatSupplier() {
        return idObatSupplier;
    }

    public void setIdObatSupplier(Long idObatSupplier) {
        this.idObatSupplier = idObatSupplier;
    }

    public ObatModel getObat() {
        return obat;
    }

    public void setObat(ObatModel obat) {
        this.obat = obat;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }
}
