package controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Dilempar controller ketika input/aksi tidak valid.
 * Bisa membawa pesan per-field (mis. "telepon" -> "Masukkan 10-13 digit angka.")
 * supaya View dapat menampilkannya tepat di bawah input yang salah.
 */
public class ValidasiException extends RuntimeException {

    private final Map<String, String> pesanField;

    public ValidasiException(String pesan) {
        this(pesan, new HashMap<>());
    }

    public ValidasiException(String pesan, Map<String, String> pesanField) {
        super(pesan);
        this.pesanField = pesanField;
    }

    /** Pesan error untuk satu field, atau null jika field itu benar. */
    public String getPesan(String field) {
        return pesanField.get(field);
    }

    /** Lempar jika ada pesan error yang terkumpul. */
    public static void cek(Map<String, String> pesanField) {
        if (!pesanField.isEmpty()) {
            throw new ValidasiException("Terdapat Kesalahan Input", pesanField);
        }
    }
}
