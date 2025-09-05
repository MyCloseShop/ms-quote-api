package com.etna.gpe.mycloseshop.ms_quote_api.controllers;

import com.etna.gpe.mycloseshop.common_api.ms_login.dto.error.ResponseError;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.QuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.RequestCreateQuoteDto;
import com.etna.gpe.mycloseshop.ms_quote_api.dto.TakenHoursDto;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "Quote Managment", description = "API for managing quotes")
@RequestMapping(path = "/quote")
public interface IQuoteController {
    @GetMapping(path = "")
    @Operation(summary = "Get available quotes", description = "Get all quotes available in the system")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quote found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getAllQuotes();

    @GetMapping(path = "/{quoteId}")
    @Operation(summary = "Get a quote by ID", description = "Get a specific quote by its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quote found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuoteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quote not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<QuoteDto> getQuoteById(@Valid @PathVariable UUID quoteId);

    @GetMapping(path = "/shop/{shopId}")
    @Operation(summary = "Get quotes from a shop", description = "Get all quotes available in a specific shop")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quotes found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getQuotesByShopId(@Valid @PathVariable UUID shopId);


    @GetMapping(path = "/user/{userId}")
    @Operation(summary = "Get quotes from a user", description = "Get all quotes available for a specific user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quotes found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getQuotesByUser(@Valid @PathVariable UUID userId);

    @GetMapping(path = "/user/{userId}/status/{status}")
    @Operation(summary = "Get quotes from a user by status", description = "Get all quotes available for a specific user filtered by status")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quotes found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getQuotesByUserAndStatus(@Valid @PathVariable UUID userId, @Valid @PathVariable String status);

    @GetMapping(path = "/shop/{shopId}/status/{status}")
    @Operation(summary = "Get quotes from a shop by status", description = "Get all quotes available for a specific shop filtered by status")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quotes found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getQuotesByShopAndStatus(@Valid @PathVariable UUID shopId, @Valid @PathVariable String status);

    @PostMapping(path = "")
    @Operation(summary = "Create a new quote", description = "Create a new quote in the system")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Quote created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuoteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<QuoteDto> createQuote(@Valid @RequestBody RequestCreateQuoteDto quoteDto);

    @PatchMapping(path = "/{quoteId}/status")
    @Operation(summary = "Update quote status", description = "Update the status of a specific quote")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quote status updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuoteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quote not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<QuoteDto> updateQuoteStatus(
            @Valid @PathVariable UUID quoteId,
            @Valid @RequestBody QuoteStatus status
    );

    @GetMapping(path = "/shop/{shopId}/user/{userId}")
    @Operation(summary = "Get quotes by shop and user", description = "Get all quotes available for a specific shop and user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quotes found",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = QuoteDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<List<QuoteDto>> getQuotesByShopAndUser(
            @Valid @PathVariable UUID shopId,
            @Valid @PathVariable UUID userId
    );

    @PatchMapping(path = "/{quoteId}/taken-hours")
    @Operation(summary = "Update taken hours of a quote", description = "Update the taken hours of a specific quote")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quote taken hours updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuoteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quote not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<QuoteDto> updateQuoteTakenHours(
            @Valid @PathVariable UUID quoteId,
            @Valid @RequestBody TakenHoursDto takenHours
    );
}
