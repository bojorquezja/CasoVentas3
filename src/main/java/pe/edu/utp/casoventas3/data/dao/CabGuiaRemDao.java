package pe.edu.utp.casoventas3.data.dao;

import pe.edu.utp.casoventas3.service.DataBaseService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import pe.edu.utp.casoventas3.data.entity.CabGuiaRem;
import pe.edu.utp.casoventas3.data.entity.Cliente;
import pe.edu.utp.casoventas3.data.entity.Empresa;
import pe.edu.utp.casoventas3.service.TypeService;

public class CabGuiaRemDao implements Dao<CabGuiaRem>{

    @Override
    public Optional<CabGuiaRem> getEntity(Object pk) {
        Objects.requireNonNull(pk, "Codigo Guia Remision no debe ser nulo");
        Class[] tipoObjeto = {String.class};
        String sql = "SELECT g.codGuiaRem, g.fechaEmi, g.rucEmpresa, " +
                    "e.razSocEmpresa, g.rucCliente, c.razSocCliente, c.direcCliente, " +
                    "g.almacenero, g.bultos " +
                    "FROM CabGuiaRem g " +
                    "LEFT JOIN Cliente c on (g.rucCliente = c.rucCliente) " +
                    "LEFT JOIN Empresa e on (g.rucEmpresa = e.rucEmpresa) " +
                    "WHERE g.codGuiaRem = ?";
        Object[] valores = {(String) pk};
        List<CabGuiaRem> tlista = DataBaseService.traeListaBD(sql, tipoObjeto, valores, (t, u) -> {
            try{
                Empresa em = new Empresa(u.getString(3), u.getString(4));
                Cliente cl = new Cliente(u.getString(5), u.getString(6), u.getString(7));
                CabGuiaRem cb = new CabGuiaRem(u.getString(1), TypeService.toLocalDate(u.getDate(2)), 
                        em, cl, u.getString(8), u.getInt(9));
                t.add(cb);
            }catch(SQLException e){
                throw new UnsupportedOperationException("Error: " + e);
            }
        });
        return tlista.stream().findFirst();
    }

    @Override
    public List<CabGuiaRem> getListOfEntities01(Object[] valores) {
        Objects.requireNonNull(valores[0], "Codigo Guia Remision no debe ser nulo");
        Objects.requireNonNull(valores[1], "Razon Social Cliente no debe ser nulo");
        Class[] tipoObjeto = {String.class, String.class};
        String sql = "SELECT g.codGuiaRem, g.fechaEmi, g.rucEmpresa, " +
                    "e.razSocEmpresa, g.rucCliente, c.razSocCliente, c.direcCliente, " +
                    "g.almacenero, g.bultos " +
                    "FROM CabGuiaRem g " +
                    "LEFT JOIN Cliente c on (g.rucCliente = c.rucCliente) " +
                    "LEFT JOIN Empresa e on (g.rucEmpresa = e.rucEmpresa) " +
                    "WHERE g.codGuiaRem like ? AND (c.razSocCliente like ? OR c.razSocCliente is null)";
        valores[0] = "%"+valores[0]+"%";
        valores[1] = "%"+valores[1]+"%";
        List<CabGuiaRem> tlista = DataBaseService.traeListaBD(sql, tipoObjeto, valores, (t, u) -> {
            try{
                Empresa em = new Empresa(u.getString(3), u.getString(4));
                Cliente cl = new Cliente(u.getString(5), u.getString(6), u.getString(7));
                CabGuiaRem cb = new CabGuiaRem(u.getString(1), TypeService.toLocalDate(u.getDate(2)), 
                        em, cl, u.getString(8), u.getInt(9));
                t.add(cb);
            }catch(SQLException e){
                throw new UnsupportedOperationException("Error: " + e);
            }
            
        });
        return tlista;
    }

    @Override
    public boolean insert(CabGuiaRem entidad) {
        String sqlA = "INSERT CabGuiaRem (codGuiaRem, fechaEmi, rucEmpresa, " +
                    "rucCliente, almacenero, bultos) "
                + "VALUES (?,?,?, "
                + "?,?,?) ";
        
        Class[] tipoObjetoA = {String.class, LocalDate.class, String.class,  
                String.class, String.class, Integer.class};
        Object[] valoresA = {entidad.getCodGuiaRem(), entidad.getFechaEmi(), entidad.getEmpresa().getRucEmpresa(), 
                            entidad.getCliente().getRucCliente(), entidad.getAlmacenero(), entidad.getBultos()};
        
        String[] sql = {sqlA};
        Class[][] tipoObjeto = {tipoObjetoA};
        Object[][] valores = {valoresA};
        
        return DataBaseService.grabaTransaccionBD(sql, tipoObjeto, valores);
    }

    @Override
    public boolean update(CabGuiaRem entidad) {
        String sqlA = "UPDATE CabGuiaRem "
                + "SET fechaEmi = ?, rucEmpresa = ?, "
                + "rucCliente = ?, almacenero = ?, bultos = ? "
                + "WHERE codGuiaRem = ?";
        
        Class[] tipoObjetoA = {LocalDate.class, String.class,
                            String.class, String.class, Integer.class,
                            String.class};
        Object[] valoresA = {entidad.getFechaEmi(), entidad.getEmpresa().getRucEmpresa(), 
                            entidad.getCliente().getRucCliente(), entidad.getAlmacenero(), entidad.getBultos(),
                            entidad.getCodGuiaRem()};
        
        String[] sql = {sqlA};
        Class[][] tipoObjeto = {tipoObjetoA};
        Object[][] valores = {valoresA};
        
        return DataBaseService.grabaTransaccionBD(sql, tipoObjeto, valores);
    }

    @Override
    public boolean delete(Object pk) {
        Objects.requireNonNull(pk, "Codigo Guia Remision no debe ser nulo");
        String sqlA = "DELETE FROM CabGuiaRem "
                + "WHERE codGuiaRem = ?";
        
        Class[] tipoObjetoA = {String.class};
        Object[] valoresA = {(String) pk};
        
        String[] sql = {sqlA};
        Class[][] tipoObjeto = {tipoObjetoA};
        Object[][] valores = {valoresA};
        return DataBaseService.grabaTransaccionBD(sql, tipoObjeto, valores);
    }
    
}
