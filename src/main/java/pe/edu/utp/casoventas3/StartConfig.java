package pe.edu.utp.casoventas3;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StartConfig {

	public static void main(String[] args) {
            new SpringApplicationBuilder(StartConfig.class)
            .headless(false)
            .web(WebApplicationType.NONE)
            .run();
	}

}
