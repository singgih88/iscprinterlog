package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @version 2011.MAY.04.01
 * @author edilson.ales
 */
@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {

    private String lastFileUploaded;

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        PrintService printService = PrintService.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();

        if (!printService.validateFile(file.getFileName())) {
            context.addMessage("formMaster:formMenu:growlMsg", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "File has already been uploaded once", "File isn't going to be uploaded again to avoid data duplicity"));
            return;
        }
        this.lastFileUploaded = file.getFileName();
        FacesMessage msg = new FacesMessage();
        String dirtyLine;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputstream()));
            while ((dirtyLine = in.readLine()) != null) {
                if ((msg = printService.processLine(dirtyLine, file.getFileName())) != null) {
                    Logger.getLogger(FileUploadBean.class.getName()).log(Level.INFO, msg.getSummary() + " " + msg.getDetail());
                    context.addMessage("formMaster:formMenu:growlMsg", msg);
                    return;
                }
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Well done!",
                    "File " + this.lastFileUploaded + " has been successfully uploaded!");
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.INFO, msg.getSummary() + " " + msg.getDetail());
            context.addMessage("formMaster:formMenu:growlMsg", msg);
        }
        catch (IOException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problem reading file source " + this.lastFileUploaded, ex.getMessage());
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.ERROR, msg.getSummary() + " " + msg.getDetail());
            context.addMessage("formMaster:formMenu:growlMsg", msg);
        }
    }

    public String getLastFileUploaded() {
        if (this.lastFileUploaded == null) {
            this.lastFileUploaded = PrintService.getInstance().getLastFileUploaded();
        }
        return lastFileUploaded;
    }

    public void setLastFileUploaded(String lastFileUploaded) {
        this.lastFileUploaded = lastFileUploaded;
    }
}
