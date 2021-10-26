package com.yapp.project.account.domain.dto;

import com.yapp.project.account.domain.Account;
import com.yapp.project.account.domain.Authority;
import com.yapp.project.account.domain.SocialType;
import com.yapp.project.aux.Message;
import com.yapp.project.aux.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.yapp.project.aux.common.DateUtil.KST_LOCAL_DATETIME_NOW;

public class AccountDto {
    private AccountDto(){
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserRequest {
        @ApiModelProperty(value = "이메일",example = "yapp@example.com")
        private String email;
        @ApiModelProperty(value = "닉네임", example = "미닝")
        private String nickname;
        @ApiModelProperty(value = "비밀번호", example = "비밀번호")
        private String password;
        @ApiModelProperty(value = "소셜타입", example = "KAKAO/APPLE/NORMAL")
        private SocialType socialType;
        public Account toAccount(PasswordEncoder passwordEncoder){
            return Account.builder()
                    .email(email)
                    .nickname(nickname)
                    .password(passwordEncoder.encode(password))
                    .authority(Authority.ROLE_USER)
                    .socialType(socialType)
                    .createdAt(KST_LOCAL_DATETIME_NOW())
                    .lastLogin(KST_LOCAL_DATETIME_NOW())
                    .build();
        }
        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email,password);
        }
        public void updateSocialType(SocialType socialType){
            this.socialType = socialType;
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        @ApiModelProperty(value = "이메일",example = "yapp@example.com")
        private String email;
        @ApiModelProperty(value = "닉네임",example = "미닝")
        private String nickname;
        @ApiModelProperty(value = "프로필",example = "s3/profile/미닝")
        private String profile;

        public static UserResponse of(Account account) {
            return new UserResponse(account.getEmail(), account.getNickname(), account.getProfile());
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserResponseMessage{
        private Message message;
        private UserResponse data;

        public static UserResponseMessage of(StatusEnum status, String message, UserResponse data){
            return UserResponseMessage.builder().data(data).message(
                    Message.builder().status(status).msg(message).build()
            ).build();
        }
    }


    @Getter
    @AllArgsConstructor
    public static class NicknameRequest{
        @ApiModelProperty(value = "닉네임",example = "미닝")
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    public static class EmailRequest{
        @ApiModelProperty(value = "이메일", example = "springboot@example.com")
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class AuthenticationNumberRequest{
        @ApiModelProperty(value = "이메일", example = "springboot@example.com")
        private String email;

        @ApiModelProperty(value = "인증번호", example = "8520")
        private String number;
    }

    @Getter
    @AllArgsConstructor
    public static class ResetPasswordRequest{
        @ApiModelProperty(value = "이메일", example = "springboot@example.com")
        private String email;

        @ApiModelProperty(value = "비밀번호", example = "Test!23$")
        private String password;
    }
}
