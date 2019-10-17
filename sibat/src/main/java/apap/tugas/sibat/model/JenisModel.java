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
@Table(name= "jenis")
public class JenisModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenis;

    @NotNull
    @Size(max = 255)
    @Column(name="namaJenis", nullable = false)
    private String namaJenis;

    @NotNull
    @Size(max = 255)
    @Column(name="deskripsiJenis", nullable = false)
    private String deskripsiJenis;

    @OneToMany(mappedBy = "jenis", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatModel> listObat;

    public Long getIdJenis() {
        return idJenis;
    }

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }

    public void setIdJenis(Long idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public String getDeskripsiJenis() {
        return deskripsiJenis;
    }

    public void setDeskripsiJenis(String deskripsiJenis) {
        this.deskripsiJenis = deskripsiJenis;
    }
}
