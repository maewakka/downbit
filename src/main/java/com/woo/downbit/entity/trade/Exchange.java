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
public class Exchange extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "exchange_amount")
    private Long exchangeAmount;
    @Column(name = "exchange_date")
    private LocalDateTime exchangeDate;

    @Builder
    public Exchange(User user, Long exchangeAmount, LocalDateTime exchangeDate) {
        this.user = user;
        this.exchangeAmount = exchangeAmount;
        this.exchangeDate = exchangeDate;
    }
}
