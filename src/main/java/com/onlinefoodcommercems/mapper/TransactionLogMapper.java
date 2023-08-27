package com.onlinefoodcommercems.mapper;


import com.onlinefoodcommercems.dto.request.TransactionLogRequest;
import com.onlinefoodcommercems.dto.response.TransactionLogResponse;
import com.onlinefoodcommercems.entity.Payment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransactionLogMapper {
    Payment fromDTO(TransactionLogRequest transactionLogRequest);

    TransactionLogResponse toDTO(Payment payment);

    List<TransactionLogResponse> toDTOs(List<Payment> payments);

}
