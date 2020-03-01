package pe.edu.utp.casoventas3.ui.view;

import pe.edu.utp.casoventas3.ui.presenter.MVPPresenter;

public interface MVPView {
    public void setPresenter(MVPPresenter presenter);
    
    void showView();
    void hideView();
    Object[] updateView(String subject, Object[] params );
    
    void closeView();
}
