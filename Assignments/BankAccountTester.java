import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Instructor-provided CS1 bank project tester. No account implementations are
 * included. Keep this entire file unchanged. Compile with the student classes.
 * Reflection allows a missing class or method to fail its tests without stopping
 * the remaining tests. Each test uses new objects to isolate failures.
 */
public class BankAccountTester {
    private static final double EPSILON = 0.000001;
    private interface Action { void run() throws Exception; }
    private static final class Test {
        final String section, label;
        final int points;
        final Action action;
        Test(String section, String label, int points, Action action) {
            this.section=section; this.label=label; this.points=points; this.action=action;
        }
    }
    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    private static void eq(double expected, double actual) {
        require(Double.isFinite(actual) && Math.abs(expected-actual)<=EPSILON,
                "Expected " + expected + ", actual " + actual);
    }
    private static Object account(String type, double balance, double rate) throws Exception {
        return Class.forName(type).getConstructor(double.class, double.class)
                .newInstance(balance, rate);
    }
    private static Object bank() throws Exception {
        return Class.forName("CS1Bank").getConstructor().newInstance();
    }
    private static Object call(Object object, String method) throws Exception {
        return object.getClass().getMethod(method).invoke(object);
    }
    private static void amount(Object object, String method, double value) throws Exception {
        object.getClass().getMethod(method, double.class).invoke(object, value);
    }
    private static double number(Object object, String method) throws Exception {
        Object value=call(object,method);
        require(value instanceof Number, method + " must return a number");
        return ((Number)value).doubleValue();
    }
    private static boolean active(Object object) throws Exception {
        Object value=call(object,"isActive");
        require(value instanceof Boolean, "isActive must return boolean");
        return (Boolean)value;
    }
    private static void add(Object bank, Object account) throws Exception {
        bank.getClass().getMethod("addAccount",Class.forName("BankAccount"))
                .invoke(bank,account);
    }
    private static Throwable cause(Throwable error) {
        while(error instanceof InvocationTargetException
                && ((InvocationTargetException)error).getCause()!=null) {
            error=((InvocationTargetException)error).getCause();
        }
        return error;
    }
    /** Runs all weighted tests and prints the student's score as plain text. */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        ArrayList<Test> tests=new ArrayList<>();
        tests.add(new Test("Design", "Interface declares all four operations", 2, () -> {
            Class<?> c = Class.forName("BankAccountInterface"); require(c.isInterface(), "BankAccountInterface must be an interface"); for (String n : new String[]{"deposit", "withdraw", "calculateInterest", "monthlyProcess"}) { java.lang.reflect.Method m = n.equals("deposit") || n.equals("withdraw") ? c.getMethod(n, double.class) : c.getMethod(n); require(m.getReturnType() == void.class, n + " must return void"); }
        }));
        tests.add(new Test("Design", "Abstract base implements interface and declares common operations", 2, () -> {
            Class<?> b = Class.forName("BankAccount"); require(java.lang.reflect.Modifier.isAbstract(b.getModifiers()), "BankAccount must be abstract"); require(Class.forName("BankAccountInterface").isAssignableFrom(b), "Base must implement interface"); for (String n : new String[]{"deposit", "withdraw", "calculateInterest", "monthlyProcess"}) { java.lang.reflect.Method m = n.equals("deposit") || n.equals("withdraw") ? b.getDeclaredMethod(n, double.class) : b.getDeclaredMethod(n); require(java.lang.reflect.Modifier.isPublic(m.getModifiers()) && !java.lang.reflect.Modifier.isAbstract(m.getModifiers()) && m.getReturnType() == void.class, "Base must implement public void " + n); }
        }));
        tests.add(new Test("Design", "Savings and checking directly extend base", 1, () -> {
            Class<?> b = Class.forName("BankAccount"); require(Class.forName("SavingsAccount").getSuperclass() == b && Class.forName("CheckingAccount").getSuperclass() == b, "Both accounts must extend BankAccount");
        }));
        tests.add(new Test("Common account behavior", "Constructor and all common getters", 3, () -> {
            Object a = account("SavingsAccount", 100, 0.12); eq(100, number(a,"getBalance")); eq(0.12, number(a,"getAnnualInterestRate")); eq(0, number(a,"getNumDeposits")); eq(0, number(a,"getNumWithdrawals")); eq(0, number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Common account behavior", "Deposit updates balance and deposit count", 2, () -> {
            Object a=account("SavingsAccount",100,0); amount(a,"deposit",30); amount(a,"deposit",20); eq(150,number(a,"getBalance")); eq(2,number(a,"getNumDeposits")); eq(0,number(a,"getNumWithdrawals"));
        }));
        tests.add(new Test("Common account behavior", "Withdrawal updates balance and withdrawal count", 2, () -> {
            Object a=account("SavingsAccount",100,0); amount(a,"withdraw",20); eq(80,number(a,"getBalance")); eq(1,number(a,"getNumWithdrawals")); eq(0,number(a,"getNumDeposits"));
        }));
        tests.add(new Test("Common account behavior", "Interest is annual rate divided by 12", 3, () -> {
            Object a=account("SavingsAccount",1200,0.12); call(a,"calculateInterest"); eq(1212,number(a,"getBalance")); eq(0,number(a,"getNumDeposits"));
        }));
        tests.add(new Test("Common account behavior", "Monthly interest and counter reset", 3, () -> {
            Object a=account("SavingsAccount",100,0.12); amount(a,"deposit",20); amount(a,"withdraw",10); call(a,"monthlyProcess"); eq(111.1,number(a,"getBalance")); eq(0,number(a,"getNumDeposits")); eq(0,number(a,"getNumWithdrawals")); eq(0,number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Common account behavior", "Repeated zero-interest month is stable", 2, () -> {
            Object a=account("SavingsAccount",100,0); call(a,"calculateInterest"); call(a,"monthlyProcess"); call(a,"monthlyProcess"); eq(100,number(a,"getBalance"));
        }));
        tests.add(new Test("Savings", "Active threshold includes exactly 25", 2, () -> {
            require(!active(account("SavingsAccount",24.99,0)), "24.99 must be inactive"); require(active(account("SavingsAccount",25,0)), "25 must be active");
        }));
        tests.add(new Test("Savings", "Inactive withdrawal rejected with no mutation", 3, () -> {
            Object a=account("SavingsAccount",20,0); amount(a,"withdraw",5); eq(20,number(a,"getBalance")); eq(0,number(a,"getNumWithdrawals")); require(!active(a), "Must remain inactive");
        }));
        tests.add(new Test("Savings", "Small deposit leaves account inactive", 2, () -> {
            Object a=account("SavingsAccount",20,0); amount(a,"deposit",4); eq(24,number(a,"getBalance")); eq(1,number(a,"getNumDeposits")); require(!active(a), "Must remain inactive");
        }));
        tests.add(new Test("Savings", "Deposit reactivates at 25 and allows withdrawal", 3, () -> {
            Object a=account("SavingsAccount",20,0); amount(a,"deposit",5); require(active(a), "Must reactivate at 25"); amount(a,"withdraw",1); eq(24,number(a,"getBalance")); eq(1,number(a,"getNumWithdrawals")); require(!active(a), "Must deactivate below 25");
        }));
        tests.add(new Test("Savings", "Withdrawal deactivation blocks next withdrawal", 3, () -> {
            Object a=account("SavingsAccount",30,0); amount(a,"withdraw",6); amount(a,"withdraw",1); eq(24,number(a,"getBalance")); eq(1,number(a,"getNumWithdrawals")); require(!active(a), "Must be inactive");
        }));
        tests.add(new Test("Savings", "Four withdrawals incur no monthly fee", 2, () -> {
            Object a=account("SavingsAccount",100,0); for(int i=0;i<4;i++) amount(a,"withdraw",1); call(a,"monthlyProcess"); eq(96,number(a,"getBalance"));
        }));
        tests.add(new Test("Savings", "Fifth withdrawal incurs one dollar fee", 3, () -> {
            Object a=account("SavingsAccount",100,0); for(int i=0;i<5;i++) amount(a,"withdraw",1); call(a,"monthlyProcess"); eq(94,number(a,"getBalance")); eq(0,number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Savings", "Excess fees precede interest and do not recur", 3, () -> {
            Object a=account("SavingsAccount",100,0.12); for(int i=0;i<6;i++) amount(a,"withdraw",1); call(a,"monthlyProcess"); eq(92.92,number(a,"getBalance")); eq(0,number(a,"getNumWithdrawals")); call(a,"monthlyProcess"); eq(93.8492,number(a,"getBalance"));
        }));
        tests.add(new Test("Savings", "Monthly fee can deactivate account", 2, () -> {
            Object a=account("SavingsAccount",31,0); for(int i=0;i<6;i++) amount(a,"withdraw",1); require(active(a), "25 must initially be active"); call(a,"monthlyProcess"); eq(23,number(a,"getBalance")); require(!active(a), "Fee must deactivate account");
        }));
        tests.add(new Test("Savings", "Monthly interest can reactivate account", 2, () -> {
            Object a=account("SavingsAccount",24.9,0.12); call(a,"monthlyProcess"); eq(25.149,number(a,"getBalance")); require(active(a), "Interest must reactivate account");
        }));
        tests.add(new Test("Checking", "Constructor, deposit, and getters", 2, () -> {
            Object a=account("CheckingAccount",100,0.12); amount(a,"deposit",20); eq(120,number(a,"getBalance")); eq(0.12,number(a,"getAnnualInterestRate")); eq(1,number(a,"getNumDeposits")); eq(0,number(a,"getNumWithdrawals")); eq(0,number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Checking", "Each withdrawal deducts a 50-cent fee", 3, () -> {
            Object a=account("CheckingAccount",100,0); amount(a,"withdraw",10); amount(a,"withdraw",10); eq(79,number(a,"getBalance")); eq(2,number(a,"getNumWithdrawals"));
        }));
        tests.add(new Test("Checking", "Withdrawal may reduce balance below 50", 2, () -> {
            Object a=account("CheckingAccount",55,0); amount(a,"withdraw",10); eq(44.5,number(a,"getBalance")); eq(1,number(a,"getNumWithdrawals"));
        }));
        tests.add(new Test("Checking", "Direct interest calculation", 2, () -> {
            Object a=account("CheckingAccount",100,0.12); call(a,"calculateInterest"); eq(101,number(a,"getBalance")); eq(0,number(a,"getNumDeposits"));
        }));
        tests.add(new Test("Checking", "Exactly 50 incurs no monthly fee", 3, () -> {
            Object a=account("CheckingAccount",50,0); call(a,"monthlyProcess"); eq(50,number(a,"getBalance"));
        }));
        tests.add(new Test("Checking", "Below 50 incurs ten dollar monthly fee", 3, () -> {
            Object a=account("CheckingAccount",49.99,0); call(a,"monthlyProcess"); eq(39.99,number(a,"getBalance")); eq(0,number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Checking", "Monthly fee precedes interest", 3, () -> {
            Object a=account("CheckingAccount",49,0.12); call(a,"monthlyProcess"); eq(39.39,number(a,"getBalance"));
        }));
        tests.add(new Test("Checking", "Monthly process resets both counters", 2, () -> {
            Object a=account("CheckingAccount",100,0); amount(a,"deposit",20); amount(a,"withdraw",10); call(a,"monthlyProcess"); eq(109.5,number(a,"getBalance")); eq(0,number(a,"getNumDeposits")); eq(0,number(a,"getNumWithdrawals")); eq(0,number(a,"getMonthlyServiceCharge"));
        }));
        tests.add(new Test("Checking", "Recovery above threshold avoids monthly fee", 2, () -> {
            Object a=account("CheckingAccount",40,0); amount(a,"deposit",20); call(a,"monthlyProcess"); eq(60,number(a,"getBalance"));
        }));
        tests.add(new Test("Checking", "Low-balance fee repeats once per month", 3, () -> {
            Object a=account("CheckingAccount",40,0); call(a,"monthlyProcess"); eq(30,number(a,"getBalance")); call(a,"monthlyProcess"); eq(20,number(a,"getBalance"));
        }));
        tests.add(new Test("CS1Bank", "Empty bank constructor and monthly processing", 2, () -> {
            Object b=bank(); eq(0,number(b,"getAccountCount")); call(b,"processMonthlyUpdates"); eq(0,number(b,"getAccountCount"));
        }));
        tests.add(new Test("CS1Bank", "Add and count both account types", 2, () -> {
            Object b=bank(); add(b,account("SavingsAccount",100,0)); add(b,account("CheckingAccount",100,0)); eq(2,number(b,"getAccountCount"));
        }));
        tests.add(new Test("CS1Bank", "Monthly updates dispatch to both account types", 4, () -> {
            Object b=bank(); Object s=account("SavingsAccount",100,0.12); Object c=account("CheckingAccount",49,0.12); for(int i=0;i<5;i++) amount(s,"withdraw",1); add(b,s); add(b,c); call(b,"processMonthlyUpdates"); eq(94.94,number(s,"getBalance")); eq(39.39,number(c,"getBalance")); eq(0,number(s,"getNumWithdrawals")); eq(2,number(b,"getAccountCount"));
        }));
        tests.add(new Test("CS1Bank", "Multiple months update every account once", 2, () -> {
            Object b=bank(); Object s=account("SavingsAccount",100,0.12); Object c=account("CheckingAccount",40,0); add(b,s); add(b,c); call(b,"processMonthlyUpdates"); call(b,"processMonthlyUpdates"); eq(102.01,number(s,"getBalance")); eq(20,number(c,"getBalance"));
        }));

        int possible=0;
        for(Test test:tests) possible+=test.points;
        if(possible!=80) throw new IllegalStateException("Tester weights must total 80");
        int score=0, passed=0, id=0;
        Map<String,int[]> totals=new LinkedHashMap<>();
        System.out.println("CS1 BANK FINAL PROJECT - WEIGHTED TEST RESULTS");
        System.out.println("Each test awards all its points only when all its checks pass.");
        for(Test test:tests) {
            id++;
            int[] section=totals.computeIfAbsent(test.section, key -> new int[2]);
            section[1]+=test.points;
            try {
                test.action.run();
                score+=test.points; section[0]+=test.points; passed++;
                System.out.printf("PASS %02d [%s] %s: %d/%d points%n",
                        id,test.section,test.label,test.points,test.points);
            } catch(Throwable error) {
                if(error instanceof VirtualMachineError || error instanceof ThreadDeath)
                    throw (Error)error;
                Throwable root=cause(error);
                System.out.printf("FAIL %02d [%s] %s: 0/%d points%n",
                        id,test.section,test.label,test.points);
                System.out.println("  " + root.getClass().getSimpleName() + ": " + root.getMessage());
            }
        }
        System.out.println();
        System.out.println("SECTION SCORES");
        for(Map.Entry<String,int[]> entry:totals.entrySet()) {
            int[] value=entry.getValue();
            System.out.printf("%s: %d/%d%n",entry.getKey(),value[0],value[1]);
        }
        System.out.printf("Tests passed: %d/%d%n",passed,tests.size());
        System.out.printf("FINAL SCORE: %d/80%n",score);
        System.out.printf("PERCENTAGE: %.2f%%%n",score*100.0/80);
    }
}
