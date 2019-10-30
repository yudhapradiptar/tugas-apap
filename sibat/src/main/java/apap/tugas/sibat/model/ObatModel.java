package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "obat")
public class ObatModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObat;

    @NotNull
    @Size(max = 255)
    @Column(name="namaObat", nullable = false)
    private String namaObat;

    @NotNull
    @Size(max = 255)
    @Column(name="kodeObat", nullable = false, unique = true)
    private String kodeObat;

    @NotNull
    @Column(name="hargaObat", nullable = false)
    private Double hargaObat;

    @NotNull
    @Size(max = 255)
    @Column(name="nomorRegistrasiObat", nullable = false, unique = true)
    private String nomorRegistrasiObat;

    @NotNull
    @Size(max = 255)
    @Column(name="bentukObat", nullable = false)
    private String bentukObat;

    @NotNull
    @Column(name="tanggalTerbitObat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalTerbitObat;

    @OneToMany(mappedBy = "obat")
    List<GudangObatModel> listGudangObat;

    @OneToMany(mappedBy = "obat")
    List<ObatSupplierModel> listObatSupplier;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jenisId", referencedColumnName = "idJenis")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenis;

    public Long getIdObat() {
        return idObat;
    }

    public void setIdObat(Long idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getKodeObat() {
        return kodeObat;
    }

    public void setKodeObat(String kodeObat) {
        this.kodeObat = kodeObat;
    }

    public Double getHargaObat() {
        return hargaObat;
    }

    public void setHargaObat(Double hargaObat) {
        this.hargaObat = hargaObat;
    }

    public String getNomorRegistrasiObat() {
        return nomorRegistrasiObat;
    }

    public void setNomorRegistrasiObat(String nomorRegistrasiObat) {
        this.nomorRegistrasiObat = nomorRegistrasiObat;
    }

    public String getBentukObat() {
        return bentukObat;
    }

    public void setBentukObat(String bentukObat) {
        this.bentukObat = bentukObat;
    }

    public Date getTanggalTerbitObat() {
        return tanggalTerbitObat;
    }

    public void setTanggalTerbitObat(Date tanggalTerbitObat) {
        this.tanggalTerbitObat = tanggalTerbitObat;
    }

    public JenisModel getJenisObat() {
        return jenis;
    }

    public void setJenisObat(JenisModel jenisObat) {
        this.jenis = jenis;
    }

    public List<GudangObatModel> getListGudangObat() {
        return listGudangObat;
    }

    public void setListGudangObat(List<GudangObatModel> listGudangObat) {
        this.listGudangObat = listGudangObat;
    }

    public List<ObatSupplierModel> getListObatSupplier() {
        return listObatSupplier;
    }

    public void setListObatSupplier(List<ObatSupplierModel> listObatSupplier) {
        this.listObatSupplier = listObatSupplier;
    }

    public JenisModel getJenis() {
        return jenis;
    }

    public void setJenis(JenisModel jenis) {
        this.jenis = jenis;
    }
}
