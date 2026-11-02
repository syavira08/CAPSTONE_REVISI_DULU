package view;

/**
 * Kontrak semua halaman yang ditampilkan di dalam jendela utama (PelangganView / AdminView).
 * Setiap halaman adalah JPanel hasil GUI Form Builder yang mengimplementasikan interface ini
 * (ABSTRACTION + POLYMORPHISM: jendela cukup memanggil muat() tanpa tahu jenis halamannya).
 */
public interface Halaman {

    /** Ambil data terbaru lalu tampilkan. Dipanggil setiap kali halaman dibuka. */
    void muat();

    /** Sembunyikan pesan sukses / galat yang masih tampil di halaman. */
    void bersihkanPesan();
}
