package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrintDAO;
import com.iscbrazil.printerlog.model.Print;
import com.iscbrazil.printerlog.model.Printer;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import javax.faces.application.FacesMessage;

/**
 * @version 2011.APR.20.01
 * @author edilson.ales
 */
public class PrintService {

    public FacesMessage processLine(String dirtyLine) {

        String[] parts = null;
        try {
            parts = dirtyLine.split(";", 6);
        } catch (PatternSyntaxException e) {
            return new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Problem with the source file. ",
                                    "Maybe a problem creating the file occured. " +
                                    "Impossible to find all the fields requiered. String malformad: " +
                                    dirtyLine);
        }

        parts[2] = parts[2].replace("[", "");
        parts[2] = parts[2].replace("]", "");
        parts[5] = parts[5].replace("::ffff:", "");
        String month = parts[2].substring(3, 6);

        if (month.equalsIgnoreCase("Jan")) {
            parts[2] = parts[2].replace(month, "01");
        } else if (month.equalsIgnoreCase("Feb")) {
            parts[2] = parts[2].replace(month, "02");
        } else if (month.equalsIgnoreCase("Mar")) {
            parts[2] = parts[2].replace(month, "03");
        } else if (month.equalsIgnoreCase("Apr")) {
            parts[2] = parts[2].replace(month, "04");
        } else if (month.equalsIgnoreCase("May")) {
            parts[2] = parts[2].replace(month, "05");
        } else if (month.equalsIgnoreCase("jun")) {
            parts[2] = parts[2].replace(month, "06");
        } else if (month.equalsIgnoreCase("jul")) {
            parts[2] = parts[2].replace(month, "07");
        } else if (month.equalsIgnoreCase("aug")) {
            parts[2] = parts[2].replace(month, "08");
        } else if (month.equalsIgnoreCase("Sep")) {
            parts[2] = parts[2].replace(month, "09");
        } else if (month.equalsIgnoreCase("Oct")) {
            parts[2] = parts[2].replace(month, "10");
        } else if (month.equalsIgnoreCase("Nov")) {
            parts[2] = parts[2].replace(month, "11");
        } else if (month.equalsIgnoreCase("Dec")) {
            parts[2] = parts[2].replace(month, "12");
        }

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        PrinterUserService pus = new PrinterUserService();
        PrinterService ps = new PrinterService();
        Print print = new Print();
        Printer printer = new Printer();
        PrinterUser user = new PrinterUser();

        try {

            date = df.parse(parts[2]);

            if((printer = ps.getByName(parts[0])) == null ) { //if system didn't find a printer
                if(parts[0].equalsIgnoreCase("HS_Lab")) { //it trys convert to unique name here
                    parts[0] = "High.School.Lab";
                }
                else if(parts[0].equalsIgnoreCase("MS_Lab")) { //and here
                     parts[0] = "Middle_School";
                }
                else { //if ti still cant find the printer, it saves aa a new one
                    Printer newPrinter = new Printer();
                    newPrinter.setCounter(Long.parseLong("1"));
                    newPrinter.setName(parts[0]);
                    newPrinter.setPlace(parts[0]);
                    ps.save(newPrinter);
                    printer = newPrinter;
                }
            }
            if(((user = pus.getByLogin(parts[1]))) == null ) { //if system didn't find the user it saves a new one
                PrinterUser newUser = new PrinterUser();
                newUser.setLogin(parts[1]);
                newUser.setCounter(Long.parseLong("1"));
                newUser.setCategory("");
                newUser.setName(parts[1]);
                pus.save(newUser);
                user = newUser;
            }
            print.setPrinter(printer);
            print.setPrinterUser(user);
            print.setPrintDate(date);
            print.setPage(Integer.parseInt(parts[3]));
            print.setIp(parts[5]);

            pus.addCounter(user.getId());
            ps.addCounter(printer.getId());

            this.save(print);
        } catch (Exception ex) {
            return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problem persisting print. ", ex.getMessage());
        }
        return null;
    }

    public void save(Print print) {
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrintDAO printDAO = factory.getPrintDAO();
        factory.beginTx();
        printDAO.save(print);
        factory.shutTx();
    }

}
