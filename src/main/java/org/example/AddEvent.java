package org.example;

public class AddEvent implements Command {
    private String[] args;
    private int lineNumber;

    public AddEvent(String[] args, int lineNumber) {
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public String execute() {
        try {
            String alertTypeStr = args[1];
            String severityStr= args[2];
            String ipAddress = args[3];
            String message = args[4];

            AlertType alertType = AlertType.valueOf(alertTypeStr);
            Severity severity = Severity.valueOf(severityStr);

            Alert alert = new Alert(alertType, severity, ipAddress, message);
            Database.getInstance().addAlert(alert);

            if (Database.getInstance().getServerIp(ipAddress) != null) {
                Database.getInstance().getServerIp(ipAddress).notifyListener(alert);
            }

            return "ADD EVENT: " + ipAddress + ": type = " + alertTypeStr + " && severity = " + severityStr + " && message = " + message;
        } catch (Exception e) {
            return "ADD EVENT: Error: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
