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
@Table(name="gudang")
public class GudangModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGudang;

    @NotNull
    @Size(max = 255)
    @Column(name="namaGudang", nullable = false)
    private String namaGudang;

    @NotNull
    @Size(max = 255)
    @Column(name="alamatGudang", nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang")
    List<GudangObatModel> listGudangObat;

    public Long getIdGudang() {
        return idGudang;
    }

    public void setIdGudang(Long idGudang) {
        this.idGudang = idGudang;
    }

    public String getNamaGudang() {
        return namaGudang;
    }

    public void setNamaGudang(String namaGudang) {
        this.namaGudang = namaGudang;
    }

    public String getAlamatGudang() {
        return alamatGudang;
    }

    public void setAlamatGudang(String alamatGudang) {
        this.alamatGudang = alamatGudang;
    }

    public List<GudangObatModel> getListGudangObat() {
        return listGudangObat;
    }

    public void setListGudangObat(List<GudangObatModel> listGudangObat) {
        this.listGudangObat = listGudangObat;
    }

}
