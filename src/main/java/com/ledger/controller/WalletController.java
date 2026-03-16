
package com.ledger.controller;

import com.ledger.dto.WalletRequest;
import com.ledger.entity.Wallet;
import com.ledger.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletRepository repo;

    public WalletController(WalletRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public void create(@RequestBody WalletRequest req){

    	Wallet w = new Wallet();
    	w.setUserId(req.getUserId());
    	w.setBalance(req.getInitialBalance());

        repo.save(w);
    }

    @GetMapping("/{id}")
    public Wallet get(@PathVariable String id){
        return repo.findById(id).orElseThrow();
    }
}