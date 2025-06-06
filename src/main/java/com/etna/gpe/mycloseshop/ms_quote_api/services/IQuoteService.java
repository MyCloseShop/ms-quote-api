package com.etna.gpe.mycloseshop.ms_quote_api.services;


import com.etna.gpe.mycloseshop.ms_quote_api.dto.QuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.RequestCreateQuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface IQuoteService {
    List<QuoteDto> findAll();

    QuoteDto findById(UUID quoteId) throws NoSuchElementException;

    Boolean deleteById(UUID quoteId);

    List<QuoteDto> findByShopId(UUID shopId);

    List<QuoteDto> findByUserId(UUID userId);

    List<QuoteDto> findByUserIdAndQuoteStatus(UUID userId, String quoteStatus);

    List<QuoteDto> findByShopIdAndQuoteStatus(UUID shopId, String quoteStatus);

    QuoteDto createQuote(RequestCreateQuoteDto quoteDto);

    QuoteDto updateQuoteStatus(UUID quoteId, QuoteStatus status) throws NoSuchElementException;

    List<QuoteDto> findByQuoteStatus(String quoteStatus);

    List<QuoteDto> findByShopIdAndUserId(UUID shopId, UUID userId);
}
