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

    // Aturan toko
    public static final BigDecimal ONGKOS_KIRIM = new BigDecimal("20000");
    public static final int BATAS_BAYAR_JAM = 24;
}
