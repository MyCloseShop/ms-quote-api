package com.etna.gpe.mycloseshop.ms_quote_api.mappers;

import com.etna.gpe.mycloseshop.ms_quote_api.dto.QuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.RequestCreateQuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.entity.Quote;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IQuoteMapper {
    QuoteDto toDto(Quote quote);

    Quote toEntity(QuoteDto quoteDto);

    Quote toEntity(RequestCreateQuoteDto requestCreateQuoteDto);
}
