package org.acme;

public class TestApplication {
	public static void main(String[] args) {
		org.springframework.boot.SpringApplication
			.from(SpringApplication::main)
			.with(ContainersConfig.class)
			.run(args);
	}
}
