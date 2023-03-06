package com.woo.downbit.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
public class UserInformResponseDto {
    private String nickname;
    private String profileImage;

    @Builder
    public UserInformResponseDto(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
