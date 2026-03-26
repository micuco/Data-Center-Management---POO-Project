package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Database.reset();

        if (args.length == 2) {
            String pathType = args[0];
            String filePath = args[1];
            setupFile(pathType, filePath);
        } else if (args.length == 4) {
            String pathType = args[0];
            String serversPath = args[1];
            String groupsPath = args[2];
            String listenersPath = args[3];

            setupFile(PathTypes.SERVERS.getValue(), serversPath);
            setupFile(PathTypes.GROUPS.getValue(), groupsPath);

            Database database = Database.getInstance();
            for (Server server : database.getServers()) {
                if (database.getResourceGroupIp(server.getIpAddress()) != null) {
                    server.setListener(database.getResourceGroupIp(server.getIpAddress()));
                }
            }

            setupFile(PathTypes.LISTENER.getValue(), listenersPath);
        }
    }

    private static void setupFile(String pathType, String filePath) {
        List<String> output = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + ".in"))) {
            String line;
            int lineNumber = 0;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                lineNumber++;

                if (parts.length > 0) {
                    Command comm = createCommand(parts[0], parts, lineNumber);

                    if (comm != null) {
                        output.add(comm.execute());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeOutput(filePath + ".out", output);
    }

    private static Command createCommand(String command, String[] parts, int lineNumber) {
        switch (command) {
            case "ADD SERVER":
                return new AddServer(parts, lineNumber);
            case "ADD GROUP":
                return new AddGroup(parts, lineNumber);
            case "FIND GROUP":
                return new FindGroup(parts, lineNumber);
            case "REMOVE GROUP":
                return new RemoveGroup(parts, lineNumber);
            case "ADD MEMBER":
                return new AddMember(parts, lineNumber);
            case "FIND MEMBER":
                return new FindMember(parts, lineNumber);
            case "REMOVE MEMBER":
                return new RemoveMember(parts, lineNumber);
            case "ADD EVENT":
                return new AddEvent(parts, lineNumber);
            default:
                return null;
        }
    }

    private static void writeOutput(String filePath, List<String> output) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String out : output) {
                writer.println(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}