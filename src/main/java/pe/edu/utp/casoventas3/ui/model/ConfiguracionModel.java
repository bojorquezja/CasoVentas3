package pe.edu.utp.casoventas3.ui.model;

import org.springframework.stereotype.Component;
import pe.edu.utp.casoventas3.service.ConfigurationService;

@Component
public class ConfiguracionModel implements MVPModel{

    @Override
    public void updateModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Todo")) {
            //params: String[]
            String[] propiedades = new String[]{"JDBC.Server", "JDBC.Port", "JDBC.DataBase",
                            "JDBC.Parameters", "JDBC.Connection", "JDBC.User", 
                            "JDBC.Password"};
                
            ConfigurationService.setArray(propiedades, (String[]) params);
        }
        
    }

    @Override
    public Object[] loadModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Todo")) {
            //params: 
            return ConfigurationService.getArray(new String[]{"JDBC.Server", "JDBC.Port", "JDBC.DataBase",
                            "JDBC.Parameters", "JDBC.Connection", "JDBC.User", 
                            "JDBC.Password"}
            );
        }
        if (subject.equalsIgnoreCase("Reset")) {
            //params: 
            return ConfigurationService.getDefaultArray(new String[]{"JDBC.Server", "JDBC.Port", "JDBC.DataBase",
                            "JDBC.Parameters", "JDBC.Connection", "JDBC.User", 
                            "JDBC.Password"}
            );
        }
        return null;
    }
    
}
