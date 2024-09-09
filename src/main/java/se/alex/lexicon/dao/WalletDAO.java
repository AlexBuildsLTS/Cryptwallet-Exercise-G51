package se.alex.lexicon.dao;

import se.alex.lexicon.model.Wallet;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WalletDAO {
    void save(Wallet wallet);
    Optional<Wallet> findById(Long id);
    List<Wallet> findAll();
    void deleteById(Long id);
    void updateBalance(Long walletId, BigDecimal newBalance);
}
