package org.example;

public class Server {
    private String ipAddress;
    private Location location;
    private User owner;
    private String hostname;
    private ServerStatus status;
    private Integer cpuCores;
    private Integer ramGb;
    private Integer storageGb;
    private AlertListener listener;

    private Server(Builder builder) {
        this.ipAddress = builder.ipAddress;
        this.location = builder.location;
        this.owner = builder.owner;
        this.hostname = builder.hostname;
        this.status = builder.status;
        this.cpuCores = builder.cpuCores;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
        this.listener = null;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Location getLocation() {
        return location;
    }

    public User getOwner() {
        return owner;
    }

    public String getHostname() {
        return hostname;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public Integer getCpuCores() {
        return cpuCores;
    }

    public Integer getRamGb() {
        return ramGb;
    }

    public Integer getStorageGb() {
        return storageGb;
    }

    public void setListener(AlertListener listener) {
        this.listener = listener;
    }

    public void clearListener(AlertListener listener) {
        this.listener = null;
    }

    public void notifyListener(Alert alert) {
        if (listener != null) {
            listener.onAlert(alert);
        }
    }

    public static class Builder {
        private String ipAddress;
        private Location location;
        private User owner;
        private String hostname;
        private ServerStatus status;
        private Integer cpuCores;
        private Integer ramGb;
        private Integer storageGb;

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder status(ServerStatus status) {
            this.status = status;
            return this;
        }

        public Builder cpuCores(Integer cpuCores) {
            this.cpuCores = cpuCores;
            return this;
        }

        public Builder ramGb(Integer ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder storageGb(Integer storageGb) {
            this.storageGb = storageGb;
            return this;
        }

        public Server build() throws MissingIpAddressException {
            if (ipAddress.isEmpty()) {
                throw new MissingIpAddressException();
            }

            if (location == null) {
                throw new IllegalArgumentException("Location is required");
            }

            if (owner == null) {
                throw new IllegalArgumentException("Owner is required");
            }

            return new Server(this);
        }
    }
}
