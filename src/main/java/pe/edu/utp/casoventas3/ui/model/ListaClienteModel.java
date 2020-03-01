package pe.edu.utp.casoventas3.ui.model;

import java.util.List;
import pe.edu.utp.casoventas3.data.dao.Dao;
import pe.edu.utp.casoventas3.data.entity.Cliente;

public class ListaClienteModel implements MVPModel{
    private Dao<Cliente> daoCG;

    public ListaClienteModel(Dao<Cliente> daoCG) {
        this.daoCG = daoCG;
    }
    
    @Override
    public void updateModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("DeleteCab")) {
            //params: pk ClienteDao
            String pk = (String) params[0];
            daoCG.delete(pk);
        }
    }

    @Override
    public Object[] loadModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Listar1")) {
            List<Cliente> lista1 = daoCG.getListOfEntities01(params);
            return new Object[]{lista1};
        }
        if (subject.equalsIgnoreCase("Entidad")) {
            //params: pk Cliente
            String pk = (String) params[0];
            Cliente ent = daoCG.getEntity(pk).orElse(null);
            return new Object[]{ent};
        }
        return null;
    }
    
}
