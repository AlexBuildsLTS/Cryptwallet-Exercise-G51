package se.alex.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.alex.lexicon.dao.WalletDAO;
import se.alex.lexicon.model.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service  // Marks this class as a Spring-managed service
public class WalletService {

    @Autowired
    private WalletDAO walletDAO;

    // Create a new wallet with a default balance of 0
    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        walletDAO.save(wallet);
        return wallet;
    }

    // Get the balance of a wallet by its ID
    public BigDecimal getWalletBalance(Long walletId) {
        Optional<Wallet> wallet = walletDAO.findById(walletId);
        if (wallet.isPresent()) {
            return wallet.get().getBalance();
        } else {
            throw new RuntimeException("Wallet not found with ID: " + walletId);
        }
    }

    // Deposit an amount into the wallet
    public Wallet deposit(Long walletId, BigDecimal amount) {
        Optional<Wallet> optionalWallet = walletDAO.findById(walletId);
        if (optionalWallet.isPresent()) {
            Wallet wallet = optionalWallet.get();
            BigDecimal newBalance = wallet.getBalance().add(amount);
            wallet.setBalance(newBalance);
            walletDAO.save(wallet);  // Update the wallet balance
            return wallet;
        } else {
            throw new RuntimeException("Wallet not found with ID: " + walletId);
        }
    }

    // Get all wallets
    public List<Wallet> getAllWallets() {
        return walletDAO.findAll();
    }

    // Delete a wallet by its ID
    public void deleteWallet(Long walletId) {
        walletDAO.deleteById(walletId);
    }
}
