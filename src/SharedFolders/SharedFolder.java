package SharedFolders;

public class SharedFolder {

    public String ipv4;

    public String machineName;

    public String tcpPort;

    public String[] listFilesNames;

    public SharedFolder(String ipv4, String machineName, String tcpPort, String[] listFilesNames) {
        this.ipv4 = ipv4;
        this.machineName = machineName;
        this.tcpPort = tcpPort;
        this.listFilesNames = listFilesNames;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getTcpPort() {
        return tcpPort;
    }

    public String[] getListFilesNames() {
        return listFilesNames;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public void setTcpPort(String tcpPort) {
        this.tcpPort = tcpPort;
    }

    public void setListFilesNames(String[] listFilesNames) {

        this.listFilesNames = new String[listFilesNames.length];
        this.listFilesNames = listFilesNames;
    }

}
