package se.alex.lexicon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.alex.lexicon.service.WalletService;
import se.alex.lexicon.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

@RestController  // Marks this as a REST controller in Spring
@RequestMapping("/api/wallets")  // Base URL for all wallet-related endpoints
public class WalletController {

    @Autowired  // Injects the WalletService into the controller
    private WalletService walletService;

    // Create a new wallet
    @PostMapping("/create")
    public Wallet createWallet() {
        return walletService.createWallet();
    }

    // Get wallet balance by wallet ID
    @GetMapping("/{walletId}/balance")
    public BigDecimal getBalance(@PathVariable Long walletId) {
        return walletService.getWalletBalance(walletId);
    }

    // Deposit money into a wallet
    @PostMapping("/{walletId}/deposit")
    public Wallet deposit(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        return walletService.deposit(walletId, amount);
    }

    // Get all wallets
    @GetMapping("/all")
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    // Delete a wallet by its ID
    @DeleteMapping("/{walletId}")
    public void deleteWallet(@PathVariable Long walletId) {
        walletService.deleteWallet(walletId);
    }
}
