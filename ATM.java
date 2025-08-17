import java.util.*;

public class ATM {
    private static final Map<String, Account> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private Account currentUser = null;

    // Clase interna para representar una cuenta
    static class Account {
        String accountNumber;
        String pin;
        double balance;
        List<String> transactionHistory;
        int failedAttempts;

        Account(String accountNumber, String pin, double initialBalance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = initialBalance;
            this.transactionHistory = new ArrayList<>();
            this.failedAttempts = 0;
            logTransaction("Cuenta creada con saldo inicial: $" + initialBalance);
        }

        void logTransaction(String description) {
            String entry = "[" + new Date() + "] " + description;
            transactionHistory.add(entry);
        }
    }

    public static void main(String[] args) {
        // Inicializar cuentas de ejemplo
        accounts.put("1001", new Account("1001", "1234", 1000.0));
        accounts.put("1002", new Account("1002", "5678", 2500.50));
        accounts.put("1003", new Account("1003", "0000", 750.25));

        ATM atm = new ATM();
        atm.start();
    }

    public void start() {
        System.out.println("==================================");
        System.out.println("   🔐 BIENVENIDO AL CAJERO ATM");
        System.out.println("==================================");

        while (currentUser == null) {
            if (!login()) {
                System.out.println("❌ Demasiados intentos fallidos. Saliendo...");
                return;
            }
        }

        showMenu();
    }

    private boolean login() {
        System.out.print("📌 Ingrese número de cuenta: ");
        String accNum = scanner.nextLine();

        System.out.print("🔑 Ingrese PIN: ");
        String pin = scanner.nextLine();

        if (accounts.containsKey(accNum)) {
            Account acc = accounts.get(accNum);
            if (acc.failedAttempts >= 3) {
                System.out.println("🚫 Cuenta bloqueada por intentos fallidos.");
                return false;
            }

            if (pin.equals(acc.pin)) {
                currentUser = acc;
                System.out.println("✅ Inicio de sesión exitoso. Bienvenido, " + acc.accountNumber);
                return true;
            } else {
                acc.failedAttempts++;
                System.out.println("❌ PIN incorrecto. Intentos fallidos: " + acc.failedAttempts + "/3");
                if (acc.failedAttempts >= 3) {
                    System.out.println("🔒 Cuenta bloqueada.");
                }
            }
        } else {
            System.out.println("❌ Cuenta no encontrada.");
        }
        return false;
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n========== MENÚ ATM ==========");
            System.out.println("1️⃣  Consultar saldo");
            System.out.println("2️⃣  Depositar dinero");
            System.out.println("3️⃣  Retirar dinero");
            System.out.println("4️⃣  Historial de transacciones");
            System.out.println("5️⃣  Cambiar PIN");
            System.out.println("6️⃣  Transferir a otra cuenta");
            System.out.println("0️⃣  Salir");
            System.out.println("==============================");
            System.out.print("👉 Elija una opción: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> checkBalance();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> showTransactionHistory();
                case "5" -> changePin();
                case "6" -> transfer();
                case "0" -> {
                    System.out.println("👋 Gracias por usar el ATM. ¡Hasta luego!");
                    return;
                }
                default -> System.out.println("⚠️  Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("💰 Saldo actual: $%.2f\n", currentUser.balance);
    }

    private void deposit() {
        System.out.print("💵 Ingrese monto a depositar: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consumir nueva línea

            if (amount > 0) {
                currentUser.balance += amount;
                currentUser.logTransaction("Depósito: +$" + amount);
                System.out.printf("✅ Depósito de $%.2f realizado con éxito.\n", amount);
            } else {
                System.out.println("❌ El monto debe ser mayor a cero.");
            }
        } else {
            System.out.println("❌ Entrada inválida. Ingrese un número.");
            scanner.nextLine(); // Limpiar entrada
        }
    }

    private void withdraw() {
        System.out.print("💸 Ingrese monto a retirar: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("❌ El monto debe ser mayor a cero.");
            } else if (amount > currentUser.balance) {
                System.out.println("❌ Fondos insuficientes. Saldo: $" + currentUser.balance);
            } else {
                currentUser.balance -= amount;
                currentUser.logTransaction("Retiro: -$" + amount);
                System.out.printf("✅ Retiro de $%.2f realizado con éxito.\n", amount);
            }
        } else {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private void showTransactionHistory() {
        System.out.println("\n📋 Historial de transacciones:");
        if (currentUser.transactionHistory.isEmpty()) {
            System.out.println("📭 No hay transacciones registradas.");
        } else {
            for (String transaction : currentUser.transactionHistory) {
                System.out.println("  • " + transaction);
            }
        }
    }

    private void changePin() {
        System.out.print("🔐 Ingrese PIN actual: ");
        String current = scanner.nextLine();
        if (!current.equals(currentUser.pin)) {
            System.out.println("❌ PIN incorrecto.");
            return;
        }

        System.out.print("🆕 Ingrese nuevo PIN (4 dígitos): ");
        String newPin = scanner.nextLine();
        if (newPin.length() != 4 || !newPin.matches("\\d{4}")) {
            System.out.println("❌ El PIN debe tener 4 dígitos numéricos.");
            return;
        }

        currentUser.pin = newPin;
        currentUser.logTransaction("PIN cambiado exitosamente.");
        System.out.println("✅ PIN actualizado con éxito.");
    }

    private void transfer() {
        System.out.print("🔁 Ingrese número de cuenta destino: ");
        String targetAcc = scanner.nextLine();

        if (!accounts.containsKey(targetAcc)) {
            System.out.println("❌ Cuenta destino no encontrada.");
            return;
        }

        if (targetAcc.equals(currentUser.accountNumber)) {
            System.out.println("❌ No puede transferirse a sí mismo.");
            return;
        }

        System.out.print("💵 Ingrese monto a transferir: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("❌ Monto inválido.");
            } else if (amount > currentUser.balance) {
                System.out.println("❌ Fondos insuficientes.");
            } else {
                currentUser.balance -= amount;
                accounts.get(targetAcc).balance += amount;

                currentUser.logTransaction("Transferencia a " + targetAcc + ": -$" + amount);
                accounts.get(targetAcc).logTransaction("Transferencia de " + currentUser.accountNumber + ": +$" + amount);

                System.out.printf("✅ Transferencia de $%.2f a la cuenta %s realizada.\n", amount, targetAcc);
            }
        } else {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }
}
