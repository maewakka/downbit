package com.woo.downbit.entity.user;

import com.woo.downbit.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id", unique = true)
    private Long id;
    private String email;
    private String nickname;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role userRole;
    private Float budget;
    @Builder
    public User(Long id, String email, String nickname, String profileImage, Role userRole, Float budget) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.userRole = userRole;
        this.budget = budget;
    }
}
