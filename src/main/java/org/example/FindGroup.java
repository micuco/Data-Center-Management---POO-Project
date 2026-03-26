package org.example;

public class FindGroup implements Command {
    private String[] args;
    private int lineNumber;

    public FindGroup(String[] args, int lineNumber) {
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

            if (Database.getInstance().getResourceGroupIp(ipAddress) != null) {
                return "FIND GROUP: " + ipAddress;
            } else {
                return "FIND GROUP: Group not found: ipAddress = " + ipAddress;
            }
        } catch (MissingIpAddressException e) {
            return "FIND GROUP: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
