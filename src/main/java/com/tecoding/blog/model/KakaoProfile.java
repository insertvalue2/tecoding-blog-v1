package com.tecoding.blog.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * 
 * @JsonInclude는 특정 조건에 해당하는 property를 제외하고
 *  se/deserialize 할 수 있도록 도와준다. 
 * 아래의 예시는 NON_NULL 조건으로 null인 property는
 *  대상에서 제외하게 된다. 
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "connected_at", "properties", "kakao_account" })
@Generated("jsonschema2pojo")
@Data
public class KakaoProfile {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("connected_at")
	private String connectedAt;
	@JsonProperty("properties")
	private Properties properties;
	@JsonProperty("kakao_account")
	private KakaoAccount kakaoAccount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "nickname" })
	@Data
	public class Properties {
		@JsonProperty("nickname")
		private String nickname;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "profile_nickname_needs_agreement", "profile", "has_email", "email_needs_agreement",
			"is_email_valid", "is_email_verified", "email" })
	@Data
	public class KakaoAccount {

		@JsonProperty("profile_nickname_needs_agreement")
		private Boolean profileNicknameNeedsAgreement;
		@JsonProperty("profile")
		private Profile profile;
		@JsonProperty("has_email")
		private Boolean hasEmail;
		@JsonProperty("email_needs_agreement")
		private Boolean emailNeedsAgreement;
		@JsonProperty("is_email_valid")
		private Boolean isEmailValid;
		@JsonProperty("is_email_verified")
		private Boolean isEmailVerified;
		@JsonProperty("email")
		private String email;
		
		
		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
		"nickname"
		})
		@Data
		public class Profile {
			@JsonProperty("nickname")
			private String nickname;
		}
		
	}

}