package com.onlinefoodcommercems.service;

import java.math.BigDecimal;

public interface TransferService {
    String transfer(String senderAccount, String recieverAccount, BigDecimal payment);
}
