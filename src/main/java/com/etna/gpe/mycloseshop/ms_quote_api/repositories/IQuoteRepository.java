package com.etna.gpe.mycloseshop.ms_quote_api.repositories;

import com.etna.gpe.mycloseshop.ms_quote_api.entity.Quote;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IQuoteRepository extends JpaRepository<Quote, UUID> {
    List<Quote> findByShopId(UUID shopId);

    List<Quote> findByUserId(UUID userId);

    List<Quote> findByShopIdAndUserId(UUID shopId, UUID userId);

    List<Quote> findByUserIdAndQuoteStatus(UUID userId, QuoteStatus quoteStatus);

    List<Quote> findByShopIdAndQuoteStatus(UUID shopId, QuoteStatus quoteStatus);

    List<Quote> findByQuoteStatus(QuoteStatus quoteStatus);
}