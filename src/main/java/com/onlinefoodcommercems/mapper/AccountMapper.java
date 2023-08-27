package com.onlinefoodcommercems.mapper;


import com.onlinefoodcommercems.dto.request.AccountRequest;
import com.onlinefoodcommercems.dto.response.AccountResponse;
import com.onlinefoodcommercems.dto.update.AccountUpdateRequest;
import com.onlinefoodcommercems.entity.Account;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {
    Account fromDTO(AccountRequest accountRequest);

    AccountResponse toDTO(Account account);

    List<AccountResponse> toDTOs(List<Account> accounts);

    Account toDtoUpdate(@MappingTarget Account account, AccountUpdateRequest accountUpdateRequest);

}
