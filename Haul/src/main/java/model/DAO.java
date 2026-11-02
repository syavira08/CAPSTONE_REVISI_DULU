package model;

import java.util.List;

/**
 * DAO PATTERN (ABSTRACTION): kontrak CRUD yang sama untuk semua tabel.
 * Controller hanya memanggil method di sini, tanpa tahu isi query-nya.
 */
public interface DAO<T> {

    List<T> semua();          // Read semua

    T cariId(int id);         // Read satu

    void simpan(T data);      // Create

    void ubah(T data);        // Update

    void hapus(int id);       // Delete
}
