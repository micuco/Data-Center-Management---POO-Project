package org.example;

public class UserCreation {
    public static User createUser(String name, String role, String email, String department, String clearanceLevel) throws UserException {
        if (name.isEmpty() || role.isEmpty()) {
            throw new UserException();
        }

        if (role.equals("Admin") && !clearanceLevel.isEmpty()) {
            return new Admin(name, role, email, department, Integer.parseInt(clearanceLevel));
        } else if (role.equals("Operator")) {
            return new Operator(name, role, email, department);
        } else {
            return new User(name, role, email);
        }
    }
}
