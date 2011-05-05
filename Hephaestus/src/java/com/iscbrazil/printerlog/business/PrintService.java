package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrintDAO;
import com.iscbrazil.printerlog.model.Print;
import com.iscbrazil.printerlog.model.Printer;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 * @version 2011.MAY.04.01
 * @author edilson.ales
 */
public class PrintService {

    private static PrintService instance;

    private PrintService() {
    }

    public static PrintService getInstance() {
        if (instance == null) {
            instance = new PrintService();
        }
        return instance;
    }

    public FacesMessage processLine(String dirtyLine, String fileName) {

        String[] parts = null;
        String ip;
        String month;

        try {
            parts = dirtyLine.split(";", 6);
            
            parts[2] = parts[2].replace("[", "");
            parts[2] = parts[2].replace("]", "");
            ip = parts[5].replace("::ffff:", "");
            month = parts[2].substring(3, 6);
            
            parts[2] = parts[2].replace(month, convertMonth(month));

        } catch (Exception e) {
            return new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problem with the source file " + fileName,
                    ". String malformad: " + dirtyLine + " " + e.getMessage());
        }


        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        PrinterUserService pus = PrinterUserService.getInstance();
        PrinterService ps = PrinterService.getInstance();
        Print print = new Print();

        if (parts[0].trim().equalsIgnoreCase("")) {
            return new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problem with the source file " + fileName + ". No Printer name. ",
                    "String malformad: " + dirtyLine);
        }

        if (parts[0].equalsIgnoreCase("HS_Lab") || parts[0].equalsIgnoreCase("High_School")) { //it trys convert to unique name here
            parts[0] = "High.School.Lab";
        }
        if (parts[0].equalsIgnoreCase("MS_Lab")) { //and here
            parts[0] = "Middle_School";
        }

        Printer printer;
        PrinterUser user;

        try {
            date = df.parse(parts[2]);
            if ((printer = ps.getByName(parts[0])) == null) {
                printer = new Printer();
            }

            if ((user = pus.getByLogin(parts[1])) == null) {
                user = new PrinterUser();
            }

            if (printer.getId() == null) { //if system didn't find a printer
                printer.setCounter(Long.parseLong("1"));
                printer.setName(parts[0]);
                printer.setPlace(parts[0]);
                ps.save(printer);
                printer = ps.getByName(printer.getName());
            }
            if (user.getId() == null) { //if system didn't find the user it saves a new one
                user.setLogin(parts[1]);
                user.setCounter(Long.parseLong("1"));
                user.setCategory("");
                user.setName(parts[1]);
                pus.save(user);
                user = pus.getByLogin(user.getLogin());
            }
            print.setPrinter(printer);
            print.setPrinterUser(user);
            print.setPrintDate(date);
            print.setPage(Integer.parseInt(parts[3]));
            print.setIp(ip);
            print.setFileName(fileName);

            pus.addCounter(user.getId());
            ps.addCounter(printer.getId());

            this.save(print);
        } catch (Exception ex) {
            return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problem persisting print. File " + fileName, ex.getMessage());
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

    public List<String> populateMonths(List<String> months) {
        months.add("All");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        return months;
    }

    public List<String> populateschoolYears(List<String> schoolYears) {
        schoolYears.add("All");
        Calendar dt = new GregorianCalendar();
        int actual = dt.get(Calendar.YEAR);
        int y1;
        String sYear;
        for (int y2 = 2007; y2 < actual + 1; y2++) {
            y1 = y2 + 1;
            sYear = Integer.toString(y2) + "-" + Integer.toString(y1);
            schoolYears.add(sYear);
        }
        return schoolYears;
    }

    public boolean validateFile(String fileName) {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrintDAO printDAO = factory.getPrintDAO();
        boolean aux = printDAO.validateFile(fileName);
        factory.shutTx();
        return aux;
    }

    public String getLastFileUploaded() {
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrintDAO printDAO = factory.getPrintDAO();
        String lastFileUploaded = printDAO.getLastFileUploaded();
        factory.shutTx();
        return lastFileUploaded;
    }

    public Long getFilteredPrints(String schoolYear, String month, int printerUserId) {
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrintDAO printDAO = factory.getPrintDAO();
        month = convertMonth(month);
        int year = getYearFromSchollYear(schoolYear, month);
        Long prints = printDAO.getFilteredPrints(year, month, printerUserId);
        factory.shutTx();
        return prints;
    }

    private int getYearFromSchollYear(String schoolYear, String month) {
                
        if(Integer.parseInt(month) <= 7){
            return Integer.parseInt(schoolYear.substring(5));
        }
        else {
            return Integer.parseInt(schoolYear.substring(0, 4));
        }
    }

    private String convertMonth(String month) {

        String m = "";

        if (month.startsWith("Jan")) {
            m = "01";
        } else if (month.startsWith("Feb")) {
            m = "02";
        } else if (month.startsWith("Mar")) {
            m = "03";
        } else if (month.startsWith("Apr")) {
            m = "04";
        } else if (month.startsWith("May")) {
            m = "05";
        } else if (month.startsWith("Jun")) {
            m = "06";
        } else if (month.startsWith("Jul")) {
            m = "07";
        } else if (month.startsWith("Aug")) {
            m = "08";
        } else if (month.startsWith("Sep")) {
            m = "09";
        } else if (month.startsWith("Oct")) {
            m = "10";
        } else if (month.startsWith("Nov")) {
            m = "11";
        } else if (month.startsWith("Dec")) {
            m = "12";
        }

        return m;
    }
}
