package pe.edu.utp.casoventas3.ui.model;

import java.util.List;
import pe.edu.utp.casoventas3.data.dao.Dao;
import pe.edu.utp.casoventas3.data.entity.CabGuiaRem;
import pe.edu.utp.casoventas3.data.entity.Cliente;
import pe.edu.utp.casoventas3.data.entity.DetGuiaRem;
import pe.edu.utp.casoventas3.data.entity.Empresa;
import pe.edu.utp.casoventas3.data.entity.Producto;

public class GuiasRemisionModel implements MVPModel{
    private Dao<CabGuiaRem> daoCG;
    private Dao<DetGuiaRem> daoDG;
    private Dao<Empresa> daoEm;
    private Dao<Cliente> daoCl;
    private Dao<Producto> daoPr;

    public GuiasRemisionModel(Dao<CabGuiaRem> daoCG, Dao<DetGuiaRem> daoDG, 
            Dao<Empresa> daoEm, Dao<Cliente> daoCl, Dao<Producto> daoPr) {
        this.daoCG = daoCG;
        this.daoDG = daoDG;
        this.daoEm = daoEm;
        this.daoCl = daoCl;
        this.daoPr = daoPr;
    }

    @Override
    public void updateModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("InsertCabDet")) {
            //params: CabGuiaRem con Det
            CabGuiaRem ent = (CabGuiaRem) params[0];
            daoCG.insert(ent);
            ent.getDetGuiaRem().forEach((item) -> {
                daoDG.insert(item);
            });
        }
        if (subject.equalsIgnoreCase("UpdateCabDet")) {
            //params: CabGuiaRem con Det
            CabGuiaRem ent = (CabGuiaRem) params[0];
            daoCG.update(ent);
            daoDG.delete(ent.getCodGuiaRem());
            ent.getDetGuiaRem().forEach((item) -> {
                daoDG.insert(item);
            });
        }
    }

    @Override
    public Object[] loadModel(String subject, Object[] params) {
        if (subject.equalsIgnoreCase("CabDet")) {
            //params: pk CabGuiaRemDao
            String pk = (String) params[0];
            CabGuiaRem ent = daoCG.getEntity(pk).orElse(null);
            
            if (ent != null){
                List<DetGuiaRem> lista1 = daoDG.getListOfEntities01(new Object[]{pk});
                ent.setDetGuiaRem(lista1);
            }
            
            return new Object[]{ent};
        }
        if (subject.equalsIgnoreCase("CargaCliente")) {
            //params: pk Cliente
            String pk = (String) params[0];
            Cliente ent = daoCl.getEntity(pk).orElse(null);
            return new Object[]{ent};
        }
        if (subject.equalsIgnoreCase("CargaEmpresa")) {
            //params: pk Empresa
            String pk = (String) params[0];
            Empresa ent = daoEm.getEntity(pk).orElse(null);
            return new Object[]{ent};
        }
        if (subject.equalsIgnoreCase("CargaProducto")) {
            //params: pk Producto
            String pk = (String) params[0];
            Producto ent = daoPr.getEntity(pk).orElse(null);
            return new Object[]{ent};
        }
        return null;
    }
    
}
