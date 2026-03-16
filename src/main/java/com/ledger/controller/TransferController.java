
package com.ledger.controller;

import com.ledger.dto.TransferRequest;
import com.ledger.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest req){

        service.transfer(
                req.getFromId(),
                req.getToId(),
                req.getAmount());
    }
}