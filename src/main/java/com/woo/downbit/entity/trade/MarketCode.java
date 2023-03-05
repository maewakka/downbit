package com.woo.downbit.entity.trade;

import com.woo.downbit.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MarketCode extends BaseTimeEntity {

    @Id
    @Column(name = "market_code", unique = true)
    private String marketCode;
    @Column(name = "korean_name")
    private String koreanName;
    @Column(name = "english_name")
    private String englishName;
    @Column(name = "market_warning")
    @Enumerated(EnumType.STRING)
    private MarketWarning MarketWarning;

    @Builder
    public MarketCode(String marketCode, String koreanName, String englishName, com.woo.downbit.entity.trade.MarketWarning marketWarning) {
        this.marketCode = marketCode;
        this.koreanName = koreanName;
        this.englishName = englishName;
        MarketWarning = marketWarning;
    }
}
