package com.etna.gpe.mycloseshop.ms_quote_api.entity;

import com.etna.gpe.mycloseshop.ms_quote_api.enums.PaimentStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.PresationStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @Column(columnDefinition = "BINARY(16)", name = "quote_id", nullable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID quoteId;

    @Column(columnDefinition = "BINARY(16)", name = "user_id", nullable = false, length = 36)
    UUID userId;

    @Column(columnDefinition = "BINARY(16)", name = "shop_id", nullable = false, length = 36)
    UUID shopId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", length = 500)
    String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "scheduled_hours", nullable = false)
    Integer scheduledHours;

    @Column(name = "taken_hours", nullable = false)
    Integer takenHours;

    @Column(name = "quote_status", nullable = false)
    @Enumerated(EnumType.STRING)
    QuoteStatus quoteStatus;

    @Column(name = "prestation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    PresationStatus prestationStatus;

    @Column(name = "paiment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    PaimentStatus paimentStatus;


    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
