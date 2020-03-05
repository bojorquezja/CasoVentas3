package pe.edu.utp.casoventas3.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import pe.edu.utp.casoventas3.data.entity.CabGuiaRem;
import pe.edu.utp.casoventas3.data.entity.DetGuiaRem;
import pe.edu.utp.casoventas3.ui.model.MVPModel;
import pe.edu.utp.casoventas3.service.TypeService;
import pe.edu.utp.casoventas3.ui.view.MVPView;

@Controller
public class ConfiguracionPresenter implements MVPPresenter{
    private MVPView view;
    private MVPModel model;
    private Object[] result;
    private String tipoView;
    
    @Autowired
    public ConfiguracionPresenter(@Qualifier("configuracionView") MVPView view, @Qualifier("configuracionModel") MVPModel model) {
        //tipoview, 
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }
    
    @Override
    public void postConstructed(Object[] params) {
        this.result = new Object[]{(Boolean) true};
        this.tipoView = (((String) params[0]).length()>=0) ? (String) params[0] : "READ";
        try{
            Object[] ent=this.model.loadModel("Todo", null);
            this.view.updateView("Iniciar", new Object[]{"Configuracion", ent});
        }catch(Exception e){
            this.view.updateView("MsgBox", new Object[]{TypeService.breakLine(e.toString(), 100)});
        }
        this.view.showView();
    }

    @Override
    public void notifyPresenter(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Cancelar")) {
            view.closeView();
        }
        //TODO
        if (subject.equalsIgnoreCase("Aceptar")) {
            //params: String[] 6 par
            try{
                model.updateModel("Todo", params);
                view.closeView();
            }catch(Exception e){
                view.updateView("MsgBox", new Object[]{TypeService.breakLine(e.toString(), 100)});
            }
        }
        //TODO Reset y CambioConexion en textfield
        if (subject.equalsIgnoreCase("Reset")) {
            try{
                Object[] ent=this.model.loadModel("Reset", null);
                view.updateView("Reset", ent);
            }catch(Exception e){
                view.updateView("MsgBox", new Object[]{TypeService.breakLine(e.toString(), 100)});
            }
        }
        if (subject.equalsIgnoreCase("CambioConexion")) {
            //params: 4 String
            String conex = ConstruyeConexion((String[]) params);
            
            view.updateView("CambioConexion", new Object[]{conex});
        }
    }
    
    private String ConstruyeConexion(String[] valores){
        return "jdbc:mysql://" + valores[0] + ":"+
                valores[1] + "/" +
                valores[2] +
                (valores[3].isEmpty() ? "" : "?" + valores[3]);
    }

    @Override
    public Object[] getResult() {
        return result;
    }

    
    
}
