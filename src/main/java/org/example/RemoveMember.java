package org.example;

public class RemoveMember implements Command {
    private String[] args;
    private int lineNumber;

    public RemoveMember(String[] args, int lineNumber) {
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public String execute() {
        try {
            String ipAddress = args[1];
            String userName = args[2];
            String userRole = args[3];

            if (ipAddress.isEmpty()) {
                throw new MissingIpAddressException();
            }

            if (userName.isEmpty() || userRole.isEmpty()) {
                throw new UserException();
            }

            if (Database.getInstance().getResourceGroupIp(ipAddress) != null) {
                if (Database.getInstance().getResourceGroupIp(ipAddress).hasMember(userName, userRole)) {
                    User user = new User(userName, userName, "");
                    Database.getInstance().getResourceGroupIp(ipAddress).removeMember(user);
                    return "REMOVE MEMBER: " + ipAddress + ": name = " + userName + " && role = " + userRole;
                } else {
                    return "REMOVE MEMBER: Member not found: ipAddress = " + ipAddress + ": name = " + userName + " && role = " + userRole;
                }
            } else {
                return "REMOVE MEMBER: Group not found: ipAddress = " + ipAddress;
            }
        } catch (MissingIpAddressException e) {
            return "REMOVE MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (UserException e) {
            return "REMOVE MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
