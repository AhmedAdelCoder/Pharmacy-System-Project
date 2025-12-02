package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.Employee;
import internal.pharmacy.system.project.Manager;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final List<Employee> employees = new ArrayList<>();
    private static final List<Manager> managers = new ArrayList<>();

    // ---------------- Add Employee ----------------
    public static boolean addEmployee(Employee emp) {
        if (emp == null) return false;
        boolean exists = employees.stream().anyMatch(e -> e.getUsername().equals(emp.getUsername()));
        if (exists) return false; // موجود مسبقًا
        employees.add(emp);
        return true;
    }

    // ---------------- Add Manager ----------------
    public static boolean addManager(Manager mgr) {
        if (mgr == null) return false;
        boolean exists = managers.stream().anyMatch(m -> m.getUsername().equals(mgr.getUsername()));
        if (exists) return false;
        managers.add(mgr);
        return true;
    }

    // ---------------- Login ----------------
    public static Object login(String username, String password) {
        for (Manager mgr : managers) {
            if (mgr.getUsername().equals(username)) {
                return mgr.getPassword().equals(password) ? mgr : "Wrong password";
            }
        }
        for (Employee emp : employees) {
            if (emp.getUsername().equals(username)) {
                return emp.getPassword().equals(password) ? emp : "Wrong password";
            }
        }
        return "Username not found";
    }

    // ---------------- Get Lists ----------------
    public static List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public static List<Manager> getManagers() {
        return new ArrayList<>(managers);
    }

    // ---------------- Remove ----------------
    public static boolean removeEmployee(String username) {
        return employees.removeIf(emp -> emp.getUsername().equals(username));
    }

    public static boolean removeManager(String username) {
        return managers.removeIf(mgr -> mgr.getUsername().equals(username));
    }
}
