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
@Table(name="gudang_obat")
public class GudangObatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGudangObat;

    @ManyToOne
    @JoinColumn(name="idObat")
    ObatModel obat;

    @ManyToOne
    @JoinColumn(name="idGudang")
    GudangModel gudang;

    public Long getIdGudangObat() {
        return idGudangObat;
    }

    public void setIdGudangObat(Long idGudangObat) {
        this.idGudangObat = idGudangObat;
    }

    public ObatModel getObat() {
        return obat;
    }

    public void setObat(ObatModel obat) {
        this.obat = obat;
    }

    public GudangModel getGudang() {
        return gudang;
    }

    public void setGudang(GudangModel gudang) {
        this.gudang = gudang;
    }
}
