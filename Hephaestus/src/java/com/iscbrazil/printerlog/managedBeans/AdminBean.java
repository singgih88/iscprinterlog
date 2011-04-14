package com.iscbrazil.printerlog.managedBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @version 2011.APR.14.01
 * @author edilson.ales
 */
@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {

    private String lastFileUploaded;
    private List<String> lines;

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.lastFileUploaded = file.getFileName();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputstream()));
            this.lastFileUploaded = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public String getLastFileUploaded() {
        return lastFileUploaded;
    }

    public void setLastFileUploaded(String lastFileUploaded) {
        this.lastFileUploaded = lastFileUploaded;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

}
