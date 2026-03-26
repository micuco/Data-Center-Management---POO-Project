package org.example;

public class AddGroup implements Command {
    private String[] args;
    private int lineNumber;

    public AddGroup(String[] args, int lineNumber) {
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

            ResourceGroup group = new ResourceGroup(ipAddress);
            Database.getInstance().addResourceGroup(group);

            return "ADD GROUP: " + ipAddress;
        } catch (MissingIpAddressException e) {
            return "ADD GROUP: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
