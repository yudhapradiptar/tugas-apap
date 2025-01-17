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
    @Size(max = 20)
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

    @ManyToMany(mappedBy = "listGudang")
    List<ObatModel> listObat;

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

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }
}
