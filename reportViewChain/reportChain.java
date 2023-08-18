package reportViewChain;

public class reportChain {
    private abstractReportHandler handler;

    public reportChain(abstractReportHandler handler) {
        this.handler = handler;
    }

    public void viewReport(String name) {
        this.handler.handle(name);
    }
}
