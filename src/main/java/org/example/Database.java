package org.example;

import java.util.*;

public class Database {
    private static Database instance;
    private Set<Server> servers;
    private Set<ResourceGroup> resourceGroups;
    private Set<Alert> alerts;

    private Database() {
        this.servers = new HashSet<>();
        this.resourceGroups = new HashSet<>();
        this.alerts = new HashSet<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }

    public void addResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroups.add(resourceGroup);
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public void addServers(Set<Server> servers) {
        this.servers.addAll(servers);
    }

    public void addResourceGroups(Set<ResourceGroup> resourceGroups) {
        this.resourceGroups.addAll(resourceGroups);
    }

    public Set<Server> getServers() {
        return this.servers;
    }

    public Set<ResourceGroup> getResourceGroups() {
        return this.resourceGroups;
    }

    public Set<Alert> getAlerts() {
        return this.alerts;
    }

    public Server getServerIp(String ipAddress) {
        for (Server server : servers) {
            if (server.getIpAddress().equals(ipAddress)) {
                return server;
            }
        }

        return null;
    }

    public ResourceGroup getResourceGroupIp(String ipAddress) {
        for (ResourceGroup group : resourceGroups) {
            if (group.getIpAddress().equals(ipAddress)) {
                return group;
            }
        }

        return null;
    }

    public boolean removeResourceGroup(String ipAddress) {
        ResourceGroup removeGroup = null;

        for (ResourceGroup group : resourceGroups) {
            if (group.getIpAddress().equals(ipAddress)) {
                removeGroup = group;
                break;
            }
        }

        if (removeGroup == null) {
            return false;
        }

        resourceGroups.remove(removeGroup);
        return true;
    }

    public static void reset() {
        instance = null;
    }
}
