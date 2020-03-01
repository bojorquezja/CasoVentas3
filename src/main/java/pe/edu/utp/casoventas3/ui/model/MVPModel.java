package pe.edu.utp.casoventas3.ui.model;

public interface MVPModel {
    void updateModel(String subject, Object[] params );
    Object[] loadModel(String subject, Object[] params );
}
