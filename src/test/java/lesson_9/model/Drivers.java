package lesson_9.model;

public class Drivers {

    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String status;
    private VehicleAssigned vehicleAssigned;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VehicleAssigned getVehicleAssigned() {
        return vehicleAssigned;
    }

    public void setVehicleAssigned(VehicleAssigned vehicleAssigned) {
        this.vehicleAssigned = vehicleAssigned;
    }
}
