package reportViewChain;

public abstract class abstractReportHandler {
    protected abstractReportHandler nextHandler;

    public abstractReportHandler setNextReportHandler(abstractReportHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this;
    }

    public abstract void handle(String name);

    protected void handleNext(String name) {
        if (nextHandler == null) {
            System.out.println("Invalid Type");
        } else {
            nextHandler.handle(name);
        }
    }
}
