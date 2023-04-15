package org.irushu.login;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

	private static final String DOWN = "down";

	public static void main(String[] args){
		SpringApplication.run(LoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length > 0 && args[0].equals(DOWN)){
			throw new DownException();
		}
	}

	class DownException extends RuntimeException implements ExitCodeGenerator{
		@Override
		public int getExitCode() {
			return 50;
		}
	}
}
