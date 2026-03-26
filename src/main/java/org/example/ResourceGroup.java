package org.example;

import java.util.*;

public class ResourceGroup implements AlertListener {
    private List<User> members;
    private String ipAddress;
    private List<Alert> alerts = new ArrayList<>();

    public ResourceGroup(String ipAddress) {
        this.ipAddress = ipAddress;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
       members.add(user);
    }

    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    public void removeMember(User user) {
        User memberRemove = null;

        for (User m : members) {
            if (m.getName().equals(user.getName()) && m.getRole().equals(user.getRole())) {
                memberRemove = m;
                break;
            }
        }

        if (memberRemove != null) {
            members.remove(memberRemove);
        }
    }

    public boolean hasMember(String name, String role) {
        for (User m : members) {
            if (m.getName().equals(name) && m.getRole().equals(role)) {
                return true;
            }
        }

        return false;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public void onAlert(Alert alert) {
        if (alert != null) {
            alerts.add(alert);
        }
    }
}
