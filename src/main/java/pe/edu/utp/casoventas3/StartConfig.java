package pe.edu.utp.casoventas3;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartConfig {

	public static void main(String[] args) {
            ConfigurableApplicationContext cac = new SpringApplicationBuilder(StartConfig.class)
            .headless(false)
            .web(WebApplicationType.NONE)
            .run(args);
            
            //cac.getBeanFactory(clase + arg)
	}
        

}
