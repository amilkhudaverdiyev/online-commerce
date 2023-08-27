package com.onlinefoodcommercems.service;


import com.onlinefoodcommercems.dto.request.TransactionLogRequest;
import com.onlinefoodcommercems.dto.response.TransactionLogResponse;

import java.util.List;

public interface TransactionLogService {
    List<TransactionLogResponse> getAllTransactionLogs();

    TransactionLogResponse createTransactionLog(TransactionLogRequest transactionLogRequest);
}
