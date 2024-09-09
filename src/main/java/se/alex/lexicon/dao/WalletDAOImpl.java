package se.alex.lexicon.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.alex.lexicon.dao.WalletDAO;
import se.alex.lexicon.model.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository  // Marks this class as a Spring-managed DAO
public class WalletDAOImpl implements WalletDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Wallet wallet) {
        if (wallet.getId() == null) {
            entityManager.persist(wallet);  // Save new wallet
        } else {
            entityManager.merge(wallet);  // Update existing wallet
        }
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Wallet.class, id));
    }

    @Override
    public List<Wallet> findAll() {
        return entityManager.createQuery("FROM Wallet", Wallet.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Wallet wallet = entityManager.find(Wallet.class, id);
        if (wallet != null) {
            entityManager.remove(wallet);
        }
    }

    @Override
    public void updateBalance(Long walletId, BigDecimal newBalance) {
        Wallet wallet = entityManager.find(Wallet.class, walletId);
        if (wallet != null) {
            wallet.setBalance(newBalance);
            entityManager.merge(wallet);  // Save updated balance
        }
    }
}
