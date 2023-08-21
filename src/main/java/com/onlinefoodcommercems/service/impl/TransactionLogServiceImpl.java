package com.onlinefoodcommercems.service.impl;


import com.onlinefoodcommercems.dto.request.TransactionLogRequest;
import com.onlinefoodcommercems.dto.response.TransactionLogResponse;
import com.onlinefoodcommercems.mapper.TransactionLogMapper;
import com.onlinefoodcommercems.repository.PaymentRepository;
import com.onlinefoodcommercems.service.TransactionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionLogServiceImpl implements TransactionLogService {
    private final PaymentRepository paymentRepository;
    private final TransactionLogMapper transactionLogMapper;
    @Override
    public List<TransactionLogResponse> getAllTransactionLogs() {
        var transactionLogEntity = paymentRepository.findAll();
        return transactionLogMapper.toDTOs(transactionLogEntity);
    }

    @Transactional(propagation =Propagation.REQUIRES_NEW)
    @Override
    public TransactionLogResponse createTransactionLog(TransactionLogRequest transactionLogRequest) {
        var transactionLogEntity = transactionLogMapper.fromDTO(transactionLogRequest);
        transactionLogEntity=paymentRepository.save(transactionLogEntity);
        return transactionLogMapper.toDTO(transactionLogEntity);
    }
}
