package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * Data produk (tabel produk).
 * Anotasi @Entity dipakai oleh ProdukDAOJpa (Hibernate); ProdukDAOJdbc memakai kelas yang sama.
 */
@Entity
@Table(name = "produk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    private Integer id;

    @Column(name = "nama_produk")
    private String nama;

    @Column(name = "kategori")
    private String kategori;

    @Column(name = "harga")
    private BigDecimal harga;

    @Column(name = "stok")
    private int stok;

    public Produk() { }   // dibutuhkan JPA

    public Produk(Integer id, String nama, String kategori, BigDecimal harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    /** Kode tampilan, mis. PRD-001. */
    public String getKode() { return String.format("PRD-%03d", id); }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public BigDecimal getHarga() { return harga; }
    public void setHarga(BigDecimal harga) { this.harga = harga; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}
