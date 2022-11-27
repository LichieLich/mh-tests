package ru.mh.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationResponse{

	@JsonProperty("certificates")
	private int certificates;

	@JsonProperty("id")
	private int id;

	@JsonProperty("login")
	private String login;

	@JsonProperty("account")
	private int account;
}