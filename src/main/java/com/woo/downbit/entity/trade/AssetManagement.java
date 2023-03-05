package com.woo.downbit.entity.trade;

import com.woo.downbit.entity.BaseTimeEntity;
import com.woo.downbit.entity.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AssetManagement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_management_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "market_code")
    private MarketCode marketCode;
    private Float amount;
    @Column(name = "unit_price")
    private Float unitPrice;

    @Builder
    public AssetManagement(User user, MarketCode marketCode, Float amount, Float unitPrice) {
        this.user = user;
        this.marketCode = marketCode;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }
}
