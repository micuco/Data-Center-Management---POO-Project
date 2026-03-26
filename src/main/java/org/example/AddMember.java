package org.example;

public class AddMember implements Command {
    private String[] args;
    private int lineNumber;

    public AddMember(String[] args, int lineNumber) {
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public String execute() {
        try {
            String ipAddress = args[1];
            String userName = args[2];
            String userRole = args[3];
            String userEmail = args[4];
            String department = args[5];
            String clearanceLevel = args[6];

            if (ipAddress.isEmpty()) {
                throw new MissingIpAddressException();
            }

            User member = UserCreation.createUser(userName, userRole, userEmail, department, clearanceLevel);

            if (Database.getInstance().getResourceGroupIp(ipAddress) != null) {
                Database.getInstance().getResourceGroupIp(ipAddress).addMember(member);
                return "ADD MEMBER: " + ipAddress + ": name = " + userName + " && role = " + userRole;
            } else {
                return "ADD MEMBER: Group not found: ipAddress = " + ipAddress;
            }
        } catch (MissingIpAddressException e) {
            return "ADD MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (UserException e) {
            return "ADD MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
