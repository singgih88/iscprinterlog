package com.iscbrazil.printerlog.managedBeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @version 2011.APR.13.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class AdminBean {

    public void aefhaeth() throws IOException {
        String filePath = "C:/Users/edilson.ales/Documents/2008.06.23.txt";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filePath));
            System.out.println(in.readLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        System.out.println(file.getFileName());
    }
}
