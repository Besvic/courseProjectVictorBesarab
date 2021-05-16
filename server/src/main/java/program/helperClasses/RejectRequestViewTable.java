package program.helperClasses;

public class RejectRequestViewTable {
    protected String nameUser;
    protected String nameAdmin;
    protected String phoneNumber;
    protected String startDate;
    protected String definition;
    protected String reason;

    public RejectRequestViewTable(String nameUser, String nameAdmin, String phoneNumber, String startDate, String definition, String reason) {
        this.nameUser = nameUser;
        this.nameAdmin = nameAdmin;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.definition = definition;
        this.reason = reason;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
