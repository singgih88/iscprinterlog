package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
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
    //private List<Line> lines = new ArrayList<Line>();

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.lastFileUploaded = file.getFileName();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputstream()));
            String dirtyLine;
            //Line line = new Line();
            PrintService printService = new PrintService();

            while ((dirtyLine = in.readLine()) != null) {
                printService.processLine(dirtyLine);
                
            }

        } catch (IOException ex) {
            
        }
    }

    public String getLastFileUploaded() {
        return lastFileUploaded;
    }

    public void setLastFileUploaded(String lastFileUploaded) {
        this.lastFileUploaded = lastFileUploaded;
    }

//    public List<Line> getLines() {
//        return lines;
//    }
//
//    public void setLines(List<Line> lines) {
//        this.lines = lines;
//    }
}
