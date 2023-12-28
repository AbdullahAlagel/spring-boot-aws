package com.aws.epl.demo.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PasswordService {

	private final PasswordEncoder encoder;

	public String encrypt(String plain) {
		return encoder.encode(plain);
	}

	public GeneratedPassword generate() {
		return generate(8);
	}

	public GeneratedPassword generate(int len) {
		String generated = RandomStringUtils.randomAlphanumeric(len);
		return new GeneratedPassword(generated, encoder.encode(generated));
	}

	public class GeneratedPassword {
		private String plain;
		private String encrypted;

		GeneratedPassword(String plain, String encrypted) {
			this.plain = plain;
			this.encrypted = encrypted;
		}

		public String getPlain() {
			return plain;
		}

		public String getEncrypted() {
			return encrypted;
		}
	}

}
