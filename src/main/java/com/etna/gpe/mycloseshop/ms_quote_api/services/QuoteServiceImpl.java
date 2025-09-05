package com.etna.gpe.mycloseshop.ms_quote_api.services;

import com.etna.gpe.mycloseshop.ms_quote_api.dto.QuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.RequestCreateQuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.entity.Quote;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.PaimentStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.PresationStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.mappers.IQuoteMapper;
import com.etna.gpe.mycloseshop.ms_quote_api.repositories.IQuoteRepository;
import com.etna.gpe.mycloseshop.security_api.utils.security.ISecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class QuoteServiceImpl implements IQuoteService {
    private final IQuoteRepository quoteRepository;
    private final IQuoteMapper quoteMapper;
    private final ISecurityUtils securityUtils;

    public QuoteServiceImpl(IQuoteRepository quoteRepository, IQuoteMapper quoteMapper, ISecurityUtils securityUtils) {
        this.quoteRepository = quoteRepository;
        this.quoteMapper = quoteMapper;
        this.securityUtils = securityUtils;
    }

    @Override
    public List<QuoteDto> findAll() {
        return quoteRepository.findAll()
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public QuoteDto findById(UUID quoteId) {
        return quoteRepository.findById(quoteId)
                .map(quoteMapper::toDto)
                .orElseThrow(
                        () -> new NoSuchElementException("Quote not found with id: " + quoteId)
                );
    }

    @Override
    public Boolean deleteById(UUID quoteId) {
        return quoteRepository.findById(quoteId)
                .map(quote -> {
                    quoteRepository.delete(quote);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<QuoteDto> findByShopId(UUID shopId) {
        return quoteRepository.findByShopId(shopId)
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public List<QuoteDto> findByUserId(UUID userId) {
        return quoteRepository.findByUserId(userId)
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public List<QuoteDto> findByUserIdAndQuoteStatus(UUID userId, String quoteStatus) {
        return quoteRepository.findByUserIdAndQuoteStatus(userId, QuoteStatus.fromString(quoteStatus))
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public List<QuoteDto> findByShopIdAndQuoteStatus(UUID shopId, String quoteStatus) {
        return quoteRepository.findByShopIdAndQuoteStatus(shopId, QuoteStatus.fromString(quoteStatus))
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public QuoteDto createQuote(RequestCreateQuoteDto quoteDto) {
        Quote quote = quoteMapper.toEntity(quoteDto);

        // Set default status to PENDING
        quote.setQuoteStatus(QuoteStatus.PENDING);
        quote.setPaimentStatus(PaimentStatus.PENDING);
        quote.setPrestationStatus(PresationStatus.PENDING);

        Quote savedQuote = quoteRepository.save(quote);
        return quoteMapper.toDto(savedQuote);
    }

    @Override
    public QuoteDto updateQuoteStatus(UUID quoteId, QuoteStatus status) throws NoSuchElementException {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NoSuchElementException("Quote not found with id: " + quoteId));

        UUID userId = quote.getUserId();

        securityUtils.checkUserIsOwner(userId);

        quote.setQuoteStatus(status);
        Quote updatedQuote = quoteRepository.save(quote);
        return quoteMapper.toDto(updatedQuote);
    }

    @Override
    public List<QuoteDto> findByQuoteStatus(String quoteStatus) {
        QuoteStatus status = QuoteStatus.fromString(quoteStatus);
        return quoteRepository.findByQuoteStatus(status)
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public List<QuoteDto> findByShopIdAndUserId(UUID shopId, UUID userId) {
        return quoteRepository.findByShopIdAndUserId(shopId, userId)
                .stream().map(quoteMapper::toDto)
                .toList();
    }

    @Override
    public QuoteDto updateQuoteTakenHours(UUID quoteId, Integer takenHours) throws NoSuchElementException {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NoSuchElementException("Quote not found with id: " + quoteId));

        // add to existing taken hours
        takenHours += quote.getTakenHours();

        quote.setTakenHours(takenHours);

        Quote updatedQuote = quoteRepository.save(quote);
        return quoteMapper.toDto(updatedQuote);
    }
}
