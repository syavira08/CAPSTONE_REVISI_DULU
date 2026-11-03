package config;

import java.math.BigDecimal;

/** Semua pengaturan aplikasi di satu tempat. Sesuaikan USER/PASSWORD dengan MySQL kamu. */
public class Konfigurasi {

    // Database
    public static final String URL = "jdbc:mysql://localhost:3306/haul?serverTimezone=Asia/Makassar&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    // Connection pool: jumlah koneksi maksimal yang disimpan & dipakai ulang
    public static final int UKURAN_POOL = 5;

    // DAO produk: true = JPA/Hibernate (ProdukDAOJpa), false = JDBC (ProdukDAOJdbc)
    public static final boolean PRODUK_PAKAI_JPA = false;

    // Kategori produk (admin hanya bisa memilih, tidak mengetik)
    public static final String[] KATEGORI = {"Elektronik", "Aksesoris", "Penyimpanan", "Komponen Komputer"};

    // Aturan toko
    public static final BigDecimal ONGKOS_KIRIM = new BigDecimal("20000");
    public static final int BATAS_BAYAR_JAM = 24;
    public static final int ESTIMASI_KIRIM_HARI = 3;            // estimasi tiba setelah dikirim
    public static final int UKURAN_GAMBAR_MAKS = 2 * 1024 * 1024;  // 2 MB (kolom MEDIUMBLOB)
}
