import java.util.Scanner;

abstract class person {
    private String name;

    public person(String name) {
        this.name = name;
    }

    void showInfo() {
        System.out.println(" Name : " + name);
    }

    public String getName() {
        return name;
    }
}

class patient extends person {
    private int age;

    public patient(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

class doctor extends person {
    private String specialization;

    public doctor(String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}

class appointment {
    private String dateTime;
    private double fee;

    public appointment(String dateTime, double fee) {
        this.dateTime = dateTime;
        this.fee = fee;
    }

    public String getDateTime() { return dateTime; }
    public double getFee() { return fee; }

    // adds the 10% hospital charge on top of consultation fee
    public double getServiceCharge() {
        return fee * 0.10;
    }

    public double getTotal() {
        return fee + getServiceCharge();
    }
}

public class ClinicBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        patient p = null;
        doctor d = null;
        appointment appt = null;

        System.out.println("=== Welcome to the Advanced Clinic System ===");

        while (true) {
            System.out.println("\n--- RECEPTIONIST DESK ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Assign Doctor");
            System.out.println("3. Book Appointment & Consultation Fee");
            System.out.println("4. Print Final Summary Invoice");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Exit System");
            System.out.print("Select an option (1-6): ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient Name: ");
                    String pName = sc.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    p = new patient(pName, age);
                    System.out.println("Patient successfully registered!");
                    p.showInfo();
                    break;

                case 2:
                    System.out.print("Enter Doctor Name: ");
                    String dName = sc.nextLine();
                    System.out.print("Enter Doctor Specialization: ");
                    String spec = sc.nextLine();

                    d = new doctor(dName, spec);
                    System.out.println("Doctor assigned successfully!");
                    d.showInfo();
                    break;

                case 3:
                    System.out.print("Enter Appointment Date & Time (e.g., 2026-07-15 10:00): ");
                    String time = sc.nextLine();
                    System.out.print("Enter Doctor Consultation Fee (LKR): ");
                    double fee = sc.nextDouble();
                    sc.nextLine();

                    appt = new appointment(time, fee);
                    System.out.println("Appointment scheduled!");
                    break;

                case 4:
                    System.out.println("\n========================================");
                    System.out.println("       MEDICAL CLINIC FINAL BILL        ");
                    System.out.println("========================================");

                    if (p != null) {
                        System.out.println(" Patient Name:    " + p.getName() + " (Age: " + p.getAge() + ")");
                    } else {
                        System.out.println(" Patient Name:    [No Patient Registered]");
                    }

                    if (d != null) {
                        System.out.println(" Assigned Doctor: Dr. " + d.getName() + " (" + d.getSpecialization() + ")");
                    } else {
                        System.out.println(" Assigned Doctor: [No Doctor Assigned]");
                    }

                    if (appt != null) {
                        System.out.println(" Schedule:        " + appt.getDateTime());
                        System.out.println("----------------------------------------");
                        System.out.println(" Consultation Fee:        LKR" + appt.getFee());
                        System.out.println(" Hospital Service Charge: LKR" + appt.getServiceCharge());
                        System.out.println("----------------------------------------");
                        System.out.println(" TOTAL AMOUNT DUE:        LKR" + appt.getTotal());
                    } else {
                        System.out.println(" Schedule:        [No Appointment Scheduled]");
                        System.out.println("----------------------------------------");
                        System.out.println(" TOTAL AMOUNT DUE:        LKR0.00");
                    }
                    System.out.println("========================================");
                    break;

                case 5:
                    appt = null;
                    System.out.println(" Appointment and pending charges canceled.");
                    break;

                case 6:
                    System.out.println("Closing clinic system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}