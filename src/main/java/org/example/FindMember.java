package org.example;

public class FindMember implements Command {
    private String[] args;
    private int lineNumber;

    public FindMember(String[] args, int lineNumber) {
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
                    return "FIND MEMBER: " + ipAddress + ": name = " + userName + " && role = " + userRole;
                } else {
                    return "FIND MEMBER: Member not found: ipAddress = " + ipAddress + ": name = " + userName + " && role = " + userRole;
                }
            } else {
                return "FIND MEMBER: Group not found: ipAddress = " + ipAddress;
            }
        } catch (MissingIpAddressException e) {
            return "FIND MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (UserException e) {
            return "FIND MEMBER: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
