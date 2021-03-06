package pe.edu.utp.casoventas3.ui.model;

import pe.edu.utp.casoventas3.data.dao.Dao;
import pe.edu.utp.casoventas3.data.entity.Producto;

public class ProductoModel implements MVPModel{
    private Dao<Producto> daoCG;

    public ProductoModel(Dao<Producto> daoCG) {
        this.daoCG = daoCG;
    }
    
    @Override
    public void updateModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("InsertCab")) {
            //params: Producto 
            Producto ent = (Producto) params[0];
            daoCG.insert(ent);
        }
        if (subject.equalsIgnoreCase("UpdateCab")) {
            //params: Producto
            Producto ent = (Producto) params[0];
            daoCG.update(ent);
        }
    }

    @Override
    public Object[] loadModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("Cab")) {
            //params: pk ProductoDao
            String pk = (String) params[0];
            Producto ent = daoCG.getEntity(pk).orElse(null);
            
            return new Object[]{ent};
        }
        return null;
    }
    
}
