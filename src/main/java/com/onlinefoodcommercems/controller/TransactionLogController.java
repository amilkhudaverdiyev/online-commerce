package com.onlinefoodcommercems.controller;


import com.onlinefoodcommercems.dto.request.TransactionLogRequest;
import com.onlinefoodcommercems.dto.response.TransactionLogResponse;
import com.onlinefoodcommercems.service.TransactionLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactionlog")
@RequiredArgsConstructor
public class TransactionLogController {
    private final TransactionLogService transactionLogService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TransactionLogResponse> getAllTransactionLogs() {
        return transactionLogService.getAllTransactionLogs();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public TransactionLogResponse createTransactionLog(@RequestBody @Valid TransactionLogRequest transactionLogRequest) {
        return transactionLogService.createTransactionLog(transactionLogRequest);
    }

}
