package  controller.pojo;

public class Printer {

    private String printerName;
    private String printerPlace;
    private int printerPrintCounter;
    private int printerID;

    public Printer(String printerName, String printerPlace, int printerPrintCounter, int printerID) {
        this.printerName = printerName;
        this.printerPlace = printerPlace;
        this.printerPrintCounter = printerPrintCounter;
        this.printerID = printerID;
    }

    public Printer() {
        
    }
    
    public int getPrinterID() {
        return printerID;
    }

    public void setPrinterID(int printerID) {
        this.printerID = printerID;
    }

    public int getPrinterPrintCounter() {
        return printerPrintCounter;
    }

    public void setPrinterPrintCounter(int printCounter) {
        this.printerPrintCounter = printCounter;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterPlace() {
        return printerPlace;
    }

    public void setPrinterPlace(String printerPlace) {
        this.printerPlace = printerPlace;
    }

}
