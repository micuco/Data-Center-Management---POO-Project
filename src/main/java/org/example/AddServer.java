package org.example;

public class AddServer implements Command {
    private String[] args;
    private int lineNumber;

    public AddServer(String[] args, int lineNumber) {
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public String execute() {
        try {
            String name = args[1];
            String ipAddress = args[2];
            String serverStatus = args[3];
            String country = args[4];
            String city = args[5];
            String address = args[6];
            String latitude = args[7];
            String longitude = args[8];
            String userName = args[9];
            String userRole = args[10];
            String userEmail = args[11];
            String department = args[12];
            String clearanceLevel = args[13];
            String cpuCores = args[14];
            String ramGB = args[15];
            String storage = args[16];

            if (ipAddress.isEmpty()) {
                throw new MissingIpAddressException();
            }

            String cityNew = null;
            String addressNew = null;
            Double latitudeNew = null;
            Double longitudeNew = null;

            if (!city.isEmpty()) {
                cityNew = city;
            }

            if (!address.isEmpty()) {
                addressNew = address;
            }

            if (!latitude.isEmpty()) {
                latitudeNew = Double.parseDouble(latitude);
            }

            if (!longitude.isEmpty()) {
                longitudeNew = Double.parseDouble(longitude);
            }

            User owner = UserCreation.createUser(userName, userRole, userEmail, department, clearanceLevel);
            Location location = new Location(country, cityNew, addressNew, latitudeNew, longitudeNew);

            Server.Builder serverBuilder = new Server.Builder().ipAddress(ipAddress).location(location).owner(owner);

            if (!name.isEmpty()) {
                serverBuilder.hostname(name);
            }

            if (!serverStatus.isEmpty()) {
                serverBuilder.status(ServerStatus.valueOf(serverStatus));
            }

            if (!cpuCores.isEmpty()) {
                serverBuilder.cpuCores(Integer.parseInt(cpuCores));
            }

            if (!ramGB.isEmpty()) {
                serverBuilder.ramGb(Integer.parseInt(ramGB));
            }

            if (!storage.isEmpty()) {
                serverBuilder.storageGb(Integer.parseInt(storage));
            }

            Server server = serverBuilder.build();
            Database.getInstance().addServer(server);

            return "ADD SERVER: " + ipAddress + ": " + serverStatus;
        } catch (MissingIpAddressException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (UserException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (LocationException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNumber;
        } catch (Exception e) {
            return "ADD SERVER: Error: " + e.getMessage() + " ## line no: " + lineNumber;
        }
    }
}
