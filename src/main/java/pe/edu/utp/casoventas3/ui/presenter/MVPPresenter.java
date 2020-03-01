package pe.edu.utp.casoventas3.ui.presenter;

import pe.edu.utp.casoventas3.ui.model.MVPModel;
import pe.edu.utp.casoventas3.ui.view.MVPView;

public interface MVPPresenter {
    public void setView(MVPView view);
    public void setModel(MVPModel model);
    public void notifyPresenter(String subject, Object[] params );
    public Object[] getResult();
    
}
