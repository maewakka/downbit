package com.woo.downbit.entity.trade;

import com.woo.downbit.entity.BaseTimeEntity;
import com.woo.downbit.entity.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Trade extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "market_code")
    private MarketCode marketCode;
    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;
    private Float price;
    private Float amount;
    @Column(name = "classification_name")
    private Classification classificationName;

    @Builder
    public Trade(User user, MarketCode marketCode, LocalDateTime conclusionDate, Float price, Float amount, Classification classificationName) {
        this.user = user;
        this.marketCode = marketCode;
        this.conclusionDate = conclusionDate;
        this.price = price;
        this.amount = amount;
        this.classificationName = classificationName;
    }
}
