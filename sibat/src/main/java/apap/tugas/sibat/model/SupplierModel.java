package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "supplier")
public class SupplierModel implements Serializable{
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

    @NotNull
    @Size(max = 255)
    @Column(name="namaSupplier", nullable = false)
    private String namaSupplier;

    @NotNull
    @Size(max = 255)
    @Column(name="alamatSupplier", nullable = false)
    private String alamatSupplier;

    @NotNull
    @Column(name="nomorTeleponSupplier", nullable = false)
    private Long nomorTeleponSupplier;

    @ManyToMany(mappedBy = "listSupplier")
    List<ObatModel> listObat;

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    public Long getNomorTeleponSupplier() {
        return nomorTeleponSupplier;
    }

    public void setNomorTeleponSupplier(Long nomorTeleponSupplier) {
        this.nomorTeleponSupplier = nomorTeleponSupplier;
    }

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }
}
