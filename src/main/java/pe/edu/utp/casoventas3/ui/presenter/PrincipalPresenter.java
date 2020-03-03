package pe.edu.utp.casoventas3.ui.presenter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import pe.edu.utp.casoventas3.data.dao.CabFacturaDao;
import pe.edu.utp.casoventas3.data.dao.CabGuiaRemDao;
import pe.edu.utp.casoventas3.data.dao.ClienteDao;
import pe.edu.utp.casoventas3.data.dao.DetFacturaDao;
import pe.edu.utp.casoventas3.data.dao.DetGuiaRemDao;
import pe.edu.utp.casoventas3.data.dao.EmpresaDao;
import pe.edu.utp.casoventas3.data.dao.ProductoDao;
import pe.edu.utp.casoventas3.ui.model.ConfiguracionModel;
import pe.edu.utp.casoventas3.ui.model.ListaClienteModel;
import pe.edu.utp.casoventas3.ui.model.ListaEmpresaModel;
import pe.edu.utp.casoventas3.ui.model.ListaFacturaModel;
import pe.edu.utp.casoventas3.ui.model.ListaGuiasRemisionModel;
import pe.edu.utp.casoventas3.ui.model.ListaProductoModel;
import pe.edu.utp.casoventas3.ui.model.MVPModel;
import pe.edu.utp.casoventas3.service.FileService;
import pe.edu.utp.casoventas3.ui.view.ConfiguracionView;
import pe.edu.utp.casoventas3.ui.view.ListaClienteView;
import pe.edu.utp.casoventas3.ui.view.ListaEmpresaView;
import pe.edu.utp.casoventas3.ui.view.ListaFacturaView;
import pe.edu.utp.casoventas3.ui.view.ListaGuiasRemisionView;
import pe.edu.utp.casoventas3.ui.view.ListaProductoView;
import pe.edu.utp.casoventas3.ui.view.MVPView;

@Controller
public class PrincipalPresenter implements MVPPresenter{
    private MVPView view;
    private MVPModel model;
    private Object[] result;
    private String tipoView;

    @Autowired
    public PrincipalPresenter(@Qualifier("principalView") MVPView view, @Qualifier("principalModel") MVPModel model) {
        //tipoview  
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }
    
    @Override
    public void postConstructed(Object[] params) {
        this.result = new Object[]{(Boolean) true};
        this.tipoView = (((String) params[0]).length()>=0) ? (String) params[0] : "READ";
        this.view.updateView("Iniciar", new Object[]{"Sistema de Ventas"});
        this.view.showView();
    }
    
    @Override
    public void notifyPresenter(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Cancelar")) {
            view.closeView();
        }
        
        if (subject.equalsIgnoreCase("Menu")) {
            //params: Opcion
            /*
            if (((String) params[0]).equalsIgnoreCase("MantGuiaRemision")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ListaGuiasRemisionPresenter(
                            new ListaGuiasRemisionView((JFrame)view, true), 
                            new ListaGuiasRemisionModel(new CabGuiaRemDao(), new DetGuiaRemDao()), 
                            new Object[]{"MAINTENANCE"});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("MantFactura")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ListaFacturaPresenter(
                            new ListaFacturaView((JFrame)view, true), 
                            new ListaFacturaModel(new CabFacturaDao(), new DetFacturaDao()), 
                            new Object[]{"MAINTENANCE"});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("MantClientes")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ListaClientePresenter(
                            new ListaClienteView((JFrame)view, true), 
                            new ListaClienteModel(new ClienteDao()), 
                            new Object[]{"MAINTENANCE"});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("MantProductos")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ListaProductoPresenter(
                            new ListaProductoView((JFrame)view, true), 
                            new ListaProductoModel(new ProductoDao()), 
                            new Object[]{"MAINTENANCE"});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("MantEmpresas")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ListaEmpresaPresenter(
                            new ListaEmpresaView((JFrame)view, true), 
                            new ListaEmpresaModel(new EmpresaDao()), 
                            new Object[]{"MAINTENANCE"});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("Configuracion")){
                SwingUtilities.invokeLater(() -> {
                    MVPPresenter p = new ConfiguracionPresenter(
                            new ConfiguracionView((JFrame) view, true), 
                            new ConfiguracionModel(), 
                            new Object[]{""});
                });
            }
            if (((String) params[0]).equalsIgnoreCase("Descarga SQL")){
                boolean result = true;
                result = result && FileService.exportResourceFile("BDVentas2_1.sql", "BDVentasV2.1.sql");
                result = result && FileService.exportResourceFile("BDVentas2_2.sql", "BDVentasV2.2.sql");
                result = result && FileService.exportResourceFile("BDVentas2_3.sql", "BDVentasV2.3.sql");
                if(result){
                    view.updateView("MsgBox", new Object[]{"Archivos descargados en la carpeta del aplicativo"});
                }else{
                    view.updateView("MsgBox", new Object[]{"ERROR al descargar Archivos!"});
                }
            }
*/
        }
        
    }

    @Override
    public Object[] getResult() {
        return result;
    }
    
}
