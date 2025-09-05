package com.etna.gpe.mycloseshop.ms_quote_api.controllers;


import com.etna.gpe.mycloseshop.ms_quote_api.dto.QuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.RequestCreateQuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.TakenHoursDto;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.services.IQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
class QuoteControllerImpl implements IQuoteController {

    private final IQuoteService quoteService;

    public QuoteControllerImpl(IQuoteService quoteService) {
        this.quoteService = quoteService;
    }


    @Override
    public ResponseEntity<List<QuoteDto>> getAllQuotes() {
        return ResponseEntity.ok(quoteService.findAll());
    }

    @Override
    public ResponseEntity<QuoteDto> getQuoteById(UUID quoteId) {
        return ResponseEntity.ok(quoteService.findById(quoteId));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getQuotesByShopId(UUID shopId) {
        return ResponseEntity.ok(quoteService.findByShopId(shopId));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getQuotesByUser(UUID userId) {
        return ResponseEntity.ok(quoteService.findByUserId(userId));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getQuotesByUserAndStatus(UUID userId, String status) {
        return ResponseEntity.ok(quoteService.findByUserIdAndQuoteStatus(userId, status));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getQuotesByShopAndStatus(UUID shopId, String status) {
        return ResponseEntity.ok(quoteService.findByShopIdAndQuoteStatus(shopId, status));
    }

    @Override
    public ResponseEntity<QuoteDto> createQuote(RequestCreateQuoteDto quoteDto) {
        return ResponseEntity
                .status(201)
                .body(quoteService.createQuote(quoteDto));
    }

    @Override
    public ResponseEntity<QuoteDto> updateQuoteStatus(UUID quoteId, QuoteStatus status) {
        return ResponseEntity.ok(quoteService.updateQuoteStatus(quoteId, status));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getQuotesByShopAndUser(UUID shopId, UUID userId) {
        return ResponseEntity.ok(quoteService.findByShopIdAndUserId(shopId, userId));
    }

    @Override
    public ResponseEntity<QuoteDto> updateQuoteTakenHours(UUID quoteId, TakenHoursDto takenHours) {
        return ResponseEntity.ok(quoteService.updateQuoteTakenHours(quoteId, takenHours.getTakenHours()));
    }
}
