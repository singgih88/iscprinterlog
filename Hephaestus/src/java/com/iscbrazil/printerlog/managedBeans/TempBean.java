package com.iscbrazil.printerlog.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @version 2011.MAY.05.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class TempBean {

    public boolean validateBrowser(String browser) {
        if(browser.contains("MSIE")) {
            FacesContext.getCurrentInstance().addMessage("formMaster:formMenu:growlMsg",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Browser not recommended", "We strongly recommend you to use another browser"));
            return false;
        }
        else
            return true;
    }
}
