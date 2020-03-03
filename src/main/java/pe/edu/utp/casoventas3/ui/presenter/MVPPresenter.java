package pe.edu.utp.casoventas3.ui.presenter;

public interface MVPPresenter {
    public void postConstructed(Object[] params);
    public void notifyPresenter(String subject, Object[] params );
    public Object[] getResult();
    
}
