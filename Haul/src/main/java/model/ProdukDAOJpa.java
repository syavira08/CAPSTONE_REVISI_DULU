package model;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/** DAO Produk versi JPA/Hibernate (ORM: tanpa menulis SQL). */
public class ProdukDAOJpa implements DAO<Produk> {

    @Override
    public List<Produk> semua() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produk p ORDER BY p.id", Produk.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Produk cariId(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Produk.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void simpan(Produk p) {
        tulis(em -> em.persist(p));
    }

    @Override
    public void ubah(Produk p) {
        tulis(em -> em.merge(p));
    }

    @Override
    public void hapus(int id) {
        tulis(em -> em.remove(em.find(Produk.class, id)));
    }

    /** Jalankan perubahan data di dalam transaksi. */
    private void tulis(java.util.function.Consumer<EntityManager> aksi) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            aksi.accept(em);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
