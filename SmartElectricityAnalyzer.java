import java.util.*;

class Appliance {
    String name;
    double power;
    double hours;
    int days;

    Appliance(String name, double power, double hours, int days) {
        this.name = name;
        this.power = power;
        this.hours = hours;
        this.days = days;
    }

    double getUnits() {
        return (power * hours * days) / 1000.0;
    }

    double getCost() {
        return getUnits() * 7.0;
    }
}

public class SmartElectricityAnalyzer {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Appliance> appliances = new ArrayList<>();

    static void addAppliance() {
        System.out.println("\n--- Add Appliance ---");

        System.out.print("Enter appliance name: ");
        String name = sc.nextLine();

        System.out.print("Enter power consumption (Watts): ");
        double power = sc.nextDouble();

        System.out.print("Enter usage hours per day: ");
        double hours = sc.nextDouble();

        System.out.print("Enter number of days used: ");
        int days = sc.nextInt();
        sc.nextLine();

        if (power <= 0 || hours < 0 || days <= 0) {
            System.out.println("Invalid input!");
            return;
        }

        appliances.add(new Appliance(name, power, hours, days));

        System.out.println("Appliance added successfully!");
    }

    static void displayAppliances() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo appliances added yet.");
            return;
        }

        System.out.println("\n================ APPLIANCE DETAILS ================");

        System.out.printf("%-20s %-10s %-10s %-10s %-12s%n",
                "Appliance", "Power", "Hours", "Days", "Units");

        System.out.println("----------------------------------------------------");

        for (Appliance a : appliances) {
            System.out.printf("%-20s %-10.1f %-10.1f %-10d %-12.2f%n",
                    a.name, a.power, a.hours, a.days, a.getUnits());
        }
    }

    static double calculateTotalUnits() {
        double total = 0;

        for (Appliance a : appliances) {
            total += a.getUnits();
        }

        return total;
    }

    static double calculateTotalCost() {
        double total = 0;

        for (Appliance a : appliances) {
            total += a.getCost();
        }

        return total;
    }

    static void showBill() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo appliance data available.");
            return;
        }

        double units = calculateTotalUnits();
        double cost = calculateTotalCost();

        System.out.println("\n============== ELECTRICITY REPORT ==============");

        System.out.printf("Total appliances      : %d%n", appliances.size());
        System.out.printf("Total energy usage    : %.2f units%n", units);
        System.out.printf("Estimated electricity : Rs. %.2f%n", cost);

        if (units <= 100) {
            System.out.println("Usage level           : LOW");
        } else if (units <= 250) {
            System.out.println("Usage level           : MODERATE");
        } else if (units <= 400) {
            System.out.println("Usage level           : HIGH");
        } else {
            System.out.println("Usage level           : VERY HIGH");
        }

        System.out.println("===============================================");
    }

    static void findHighestConsumer() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo data available.");
            return;
        }

        Appliance highest = appliances.get(0);

        for (Appliance a : appliances) {
            if (a.getUnits() > highest.getUnits()) {
                highest = a;
            }
        }

        System.out.println("\n========== HIGHEST ENERGY CONSUMER ==========");

        System.out.println("Appliance : " + highest.name);
        System.out.printf("Power     : %.1f W%n", highest.power);
        System.out.printf("Usage     : %.1f hours/day%n", highest.hours);
        System.out.printf("Units     : %.2f%n", highest.getUnits());
        System.out.printf("Cost      : Rs. %.2f%n", highest.getCost());

        System.out.println("=============================================");
    }

    static void sortByConsumption() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo data available.");
            return;
        }

        ArrayList<Appliance> sorted = new ArrayList<>(appliances);

        sorted.sort((a, b) -> Double.compare(b.getUnits(), a.getUnits()));

        System.out.println("\n========== CONSUMPTION RANKING ==========");

        int rank = 1;

        for (Appliance a : sorted) {
            System.out.printf("%d. %-20s %.2f units%n",
                    rank, a.name, a.getUnits());
            rank++;
        }

        System.out.println("=========================================");
    }

    static void savingSuggestions() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo data available.");
            return;
        }

        System.out.println("\n========== SMART SAVING SUGGESTIONS ==========");

        boolean suggestionFound = false;

        for (Appliance a : appliances) {

            if (a.hours >= 8) {
                System.out.println("- Reduce usage of " + a.name +
                        " because it runs for many hours.");
                suggestionFound = true;
            }

            if (a.power >= 1000) {
                System.out.println("- " + a.name +
                        " is a high-power appliance. Use it efficiently.");
                suggestionFound = true;
            }

            if (a.getUnits() > 100) {
                System.out.println("- " + a.name +
                        " consumes more than 100 units. Consider reducing usage.");
                suggestionFound = true;
            }
        }

        if (!suggestionFound) {
            System.out.println("Your electricity usage looks efficient!");
        }

        System.out.println("\nGeneral Recommendations:");
        System.out.println("1. Switch off appliances when not required.");
        System.out.println("2. Prefer energy-efficient appliances.");
        System.out.println("3. Avoid unnecessary standby power.");
        System.out.println("4. Reduce usage of high-power appliances.");
        System.out.println("==============================================");
    }

    static void simulateSaving() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo appliance data available.");
            return;
        }

        double currentCost = calculateTotalCost();

        System.out.print("\nEnter percentage of usage you want to reduce: ");
        double reduction = sc.nextDouble();
        sc.nextLine();

        if (reduction <= 0 || reduction >= 100) {
            System.out.println("Enter a percentage between 0 and 100.");
            return;
        }

        double savedCost = currentCost * reduction / 100;
        double newCost = currentCost - savedCost;

        System.out.println("\n========== SAVING SIMULATION ==========");

        System.out.printf("Current monthly cost : Rs. %.2f%n", currentCost);
        System.out.printf("Reduction            : %.1f%%%n", reduction);
        System.out.printf("Estimated saving     : Rs. %.2f%n", savedCost);
        System.out.printf("New estimated cost   : Rs. %.2f%n", newCost);

        System.out.println("=======================================");
    }

    static void searchAppliance() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo appliances available.");
            return;
        }

        System.out.print("\nEnter appliance name to search: ");
        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Appliance a : appliances) {
            if (a.name.toLowerCase().contains(search)) {

                System.out.println("\nAppliance Found");
                System.out.println("-------------------------");
                System.out.println("Name  : " + a.name);
                System.out.printf("Power : %.1f W%n", a.power);
                System.out.printf("Hours : %.1f%n", a.hours);
                System.out.println("Days  : " + a.days);
                System.out.printf("Units : %.2f%n", a.getUnits());
                System.out.printf("Cost  : Rs. %.2f%n", a.getCost());

                found = true;
            }
        }

        if (!found) {
            System.out.println("Appliance not found.");
        }
    }

    static void removeAppliance() {
        if (appliances.isEmpty()) {
            System.out.println("\nNo appliances available.");
            return;
        }

        System.out.print("\nEnter appliance name to remove: ");
        String name = sc.nextLine();

        boolean removed = appliances.removeIf(
                a -> a.name.equalsIgnoreCase(name)
        );

        if (removed) {
            System.out.println("Appliance removed successfully.");
        } else {
            System.out.println("Appliance not found.");
        }
    }

    static void menu() {

        while (true) {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("       SMART ELECTRICITY ANALYZER");
            System.out.println("==============================================");
            System.out.println("1. Add Appliance");
            System.out.println("2. Display Appliances");
            System.out.println("3. Electricity Bill");
            System.out.println("4. Find Highest Consumer");
            System.out.println("5. Consumption Ranking");
            System.out.println("6. Smart Saving Suggestions");
            System.out.println("7. Simulate Electricity Saving");
            System.out.println("8. Search Appliance");
            System.out.println("9. Remove Appliance");
            System.out.println("10. Exit");
            System.out.println("==============================================");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addAppliance();
                    break;

                case 2:
                    displayAppliances();
                    break;

                case 3:
                    showBill();
                    break;

                case 4:
                    findHighestConsumer();
                    break;

                case 5:
                    sortByConsumption();
                    break;

                case 6:
                    savingSuggestions();
                    break;

                case 7:
                    simulateSaving();
                    break;

                case 8:
                    searchAppliance();
                    break;

                case 9:
                    removeAppliance();
                    break;

                case 10:
                    System.out.println("\nThank you for using Smart Electricity Analyzer!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("     SMART ELECTRICITY USAGE ANALYZER");
        System.out.println("==============================================");
        System.out.println("Analyze your electricity consumption");
        System.out.println("and discover possible savings.");
        System.out.println("==============================================");

        menu();

        sc.close();
    }
}