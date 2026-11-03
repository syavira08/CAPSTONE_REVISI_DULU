package view;

/**
 * Kontrak halaman yang ditampilkan di dalam PelangganView / AdminView (logika layout controller).
 * Setiap halaman memuat ulang datanya dari controller ketika dibuka.
 */
public interface Halaman {

    void muat();
}
