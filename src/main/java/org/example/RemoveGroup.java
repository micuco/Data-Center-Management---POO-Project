package org.example;

public class RemoveGroup implements Command {
    private String[] args;
    private int lineNumber;

    public RemoveGroup(String[] args, int lineNumber) {
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public String execute() {
        try {
            String ipAddress = args[1];

            if (ipAddress.isEmpty()) {
                throw new MissingIpAddressException();
            }

            if (Database.getInstance().removeResourceGroup(ipAddress)) {
                return "REMOVE GROUP: " + ipAddress;
            } else {
                return "REMOVE GROUP: Group not found: ipAddress = " + ipAddress;
            }
        } catch (MissingIpAddressException e) {
            return "REMOVE GROUP: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
