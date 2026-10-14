# Final Project: Bank Account Management System

**Course:** CSCI 2001 - Object Oriented Programming (CS1)  
**Instructor email:** Siva.Jasthi@nhcc.edu  
**Total points:** 80

## Project Overview

Implement a Bank Account Management System in Java using interfaces, an abstract class, inheritance, encapsulation, and polymorphism. The system supports checking and savings accounts, deposits, withdrawals, interest calculation, and monthly processing.

This project is inspired by Programming Assignment 9, “BankAccount and SavingsAccount Classes,” on page 698 of the printed textbook, Chapter 10, Inheritance. This project extends that assignment with an interface, a checking account, and a bank that manages multiple accounts.

Implement the five types specified below. The sixth file, [BankAccountTester.java](BankAccountTester.java), is supplied by the instructor and contains the `main()` method and weighted tests. No implementations of the five student types are provided.

## Files and Required Public API

Use the exact names, capitalization, parameter types, and return types shown below. Put each public type in its matching `.java` file. Use the default package: **do not add a `package` declaration**. You may add private or protected helpers, but they are not separate scored methods.

### 1. BankAccountInterface.java

Define the public interface `BankAccountInterface` with these four operations:

```java
void deposit(double amount);
void withdraw(double amount);
void calculateInterest();
void monthlyProcess();
```

### 2. BankAccount.java

Define `public abstract class BankAccount implements BankAccountInterface`.

Maintain these instance attributes. Keep them private or protected, never public, and do not make account state static:

| Attribute | Type | Meaning |
| --- | --- | --- |
| `balance` | `double` | Current account balance |
| `numDeposits` | `int` | Successful deposits since the last monthly process |
| `numWithdrawals` | `int` | Successful withdrawals since the last monthly process |
| `annualInterestRate` | `double` | Annual rate as a decimal, such as `0.12` for 12% |
| `monthlyServiceCharge` | `double` | Monthly charges pending deduction |

Provide a public or protected constructor `BankAccount(double balance, double annualInterestRate)`. Initialize the balance and annual rate from its arguments, and initialize both counters and the service charge to zero.

Implement all four interface methods as public concrete methods in this abstract class:

| Method | Required behavior |
| --- | --- |
| `deposit(double amount)` | Add the amount to the balance and increment `numDeposits` once. |
| `withdraw(double amount)` | Subtract the amount from the balance and increment `numWithdrawals` once. |
| `calculateInterest()` | Add `balance * (annualInterestRate / 12.0)` to the balance. Do not count interest as a deposit. |
| `monthlyProcess()` | Deduct the pending monthly service charge, calculate interest on the resulting balance, then reset both counters and the pending service charge to zero. |

Provide these public, non-mutating accessors; inherited accessors are sufficient in the subclasses:

```java
double getBalance();
int getNumDeposits();
int getNumWithdrawals();
double getAnnualInterestRate();
double getMonthlyServiceCharge();
```

Accessor calls must not change balances, counters, or fees. Do not round stored balances or interest to cents; the tester compares floating-point results with an absolute tolerance of `0.000001`.

### 3. SavingsAccount.java

Define `public class SavingsAccount extends BankAccount` with a public constructor `SavingsAccount(double balance, double annualInterestRate)` and a public accessor `boolean isActive()`.

Maintain an `isActive` flag. An account is active when its current balance is **at least $25** and inactive below $25. Initialize this flag in the constructor and refresh it after balance changes, including interest and monthly processing.

- Deposits are allowed while inactive. A deposit that raises the balance to $25 or more reactivates the account.
- Withdrawals are allowed only when the account is active immediately before the withdrawal. An inactive withdrawal is a normal rejected operation: return without throwing, changing the balance, or incrementing the withdrawal count.
- A successful withdrawal may lower the balance below $25; immediately mark the account inactive and reject subsequent withdrawals until it reactivates.
- The first four successful withdrawals in a month incur no monthly withdrawal charge. Charge **$1 for each successful withdrawal beyond four**.
- In `monthlyProcess()`, add the excess-withdrawal charge to the pending monthly service charge, then perform the base monthly process. Refresh activity using the final balance. Fee deductions occur before interest.
- Resetting the monthly counters must prevent old excess-withdrawal charges from recurring in the next month.

Use or override the inherited operations as needed while preserving the public API. For example, five $1 withdrawals from a $100 account with zero interest leave $95 before monthly processing and $94 afterward.

### 4. CheckingAccount.java

Define `public class CheckingAccount extends BankAccount` with a public constructor `CheckingAccount(double balance, double annualInterestRate)`.

- Each successful withdrawal deducts the requested amount **plus $0.50**, immediately. Increment the withdrawal counter once, not twice. The fee is not another withdrawal or deposit and must not be charged again during monthly processing.
- The **$50 minimum balance is a monthly fee threshold**, not a restriction on withdrawals. A withdrawal may reduce the balance below $50.
- At the start of monthly processing, if the current balance is **below $50**, add a **$10 monthly fee** to the pending monthly service charge. A balance of exactly $50 incurs no such fee.
- Then perform the base monthly process: deduct fees, calculate interest, and reset the counters and pending service charge.
- A deposit that restores the balance to at least $50 before monthly processing avoids that month's low-balance fee. If the account remains below $50, apply one $10 fee in each subsequent month.

Example: with balance $49 and annual rate `0.12`, monthly processing deducts $10 first, then adds 1% interest: the resulting balance is $39.39.

### 5. CS1Bank.java

Define the public class `CS1Bank` with a public no-argument constructor and these public methods:

```java
void addAccount(BankAccount account);
void processMonthlyUpdates();
int getAccountCount();
```

Maintain one instance list, such as `ArrayList<BankAccount>`, that holds both savings and checking accounts.

- `addAccount` stores the supplied account object; do not create a copy. Each call adds one account.
- `getAccountCount` returns the number of stored accounts without changing the list.
- `processMonthlyUpdates` calls `monthlyProcess()` exactly once on every stored account. Use polymorphism so each account's own monthly rules apply.
- Processing an empty bank must complete normally and leave its count at zero.
- Repeated monthly updates must continue to process all accounts without deleting them or duplicating charges within a month.

### Input Scope

The scored tests use finite, nonnegative starting balances and annual rates, positive deposit and withdrawal amounts, and non-null, distinct accounts. There is no additional overdraft rejection rule in this assignment: the only required withdrawal rejection is an inactive savings account. Do not impose an undocumented balance floor or clamp balances after fee deductions. You may validate inputs outside this scope, but that validation must not change the specified behavior for tested inputs.

## Instructor-Provided Tester

Download [BankAccountTester.java](BankAccountTester.java) into the same directory as your five implemented types.

**Do not change `main()` or any other part of `BankAccountTester.java`.** Do not alter the tests, expected values, point weights, scoring logic, or output. Your task is to implement the five student types so the unchanged tester passes. A modified tester's reported score is not valid for grading.

After all five types are implemented, compile and run the supplied `main()` method. The tester runs 33 independent test cases. Each test creates fresh objects so a failure in one test does not contaminate later tests. A test receives its listed points only when every check in that test passes; otherwise it receives zero. Partial credit comes from passing other tests.

The tester covers all required public operations and accessors, constructors, inheritance, and interface relationships. It also tests threshold boundaries, account reactivation, fee and interest order, monthly resets, and polymorphic bank processing. The common behavior section exercises the shared operations through a savings account; the design section verifies that `BankAccount` itself declares concrete implementations of all four common operations.

The tester uses reflection internally to report missing classes or methods as failed tests and continue to later tests. Students do not need to use reflection in their implementations. Passing these tests demonstrates the listed cases; it is not exhaustive verification of every possible input.

## Weighted Grading Rubric

| Section | Points |
| --- | ---: |
| Interface and inheritance design | 5 |
| Common account behavior and accessors | 15 |
| Savings account behavior | 25 |
| Checking account behavior | 25 |
| Bank account management and monthly updates | 10 |
| **Total** | **80** |

### Individual Test Weights

| Test | Section | Behavior tested | Points |
| ---: | --- | --- | ---: |
| 1 | Design | Interface declares all four operations | 2 |
| 2 | Design | Abstract base implements interface and declares common operations | 2 |
| 3 | Design | Savings and checking directly extend base | 1 |
| 4 | Common account behavior | Constructor and all common getters | 3 |
| 5 | Common account behavior | Deposit updates balance and deposit count | 2 |
| 6 | Common account behavior | Withdrawal updates balance and withdrawal count | 2 |
| 7 | Common account behavior | Interest is annual rate divided by 12 | 3 |
| 8 | Common account behavior | Monthly interest and counter reset | 3 |
| 9 | Common account behavior | Repeated zero-interest month is stable | 2 |
| 10 | Savings | Active threshold includes exactly 25 | 2 |
| 11 | Savings | Inactive withdrawal rejected with no mutation | 3 |
| 12 | Savings | Small deposit leaves account inactive | 2 |
| 13 | Savings | Deposit reactivates at 25 and allows withdrawal | 3 |
| 14 | Savings | Withdrawal deactivation blocks next withdrawal | 3 |
| 15 | Savings | Four withdrawals incur no monthly fee | 2 |
| 16 | Savings | Fifth withdrawal incurs one dollar fee | 3 |
| 17 | Savings | Excess fees precede interest and do not recur | 3 |
| 18 | Savings | Monthly fee can deactivate account | 2 |
| 19 | Savings | Monthly interest can reactivate account | 2 |
| 20 | Checking | Constructor, deposit, and getters | 2 |
| 21 | Checking | Each withdrawal deducts a 50-cent fee | 3 |
| 22 | Checking | Withdrawal may reduce balance below 50 | 2 |
| 23 | Checking | Direct interest calculation | 2 |
| 24 | Checking | Exactly 50 incurs no monthly fee | 3 |
| 25 | Checking | Below 50 incurs ten dollar monthly fee | 3 |
| 26 | Checking | Monthly fee precedes interest | 3 |
| 27 | Checking | Monthly process resets both counters | 2 |
| 28 | Checking | Recovery above threshold avoids monthly fee | 2 |
| 29 | Checking | Low-balance fee repeats once per month | 3 |
| 30 | CS1Bank | Empty bank constructor and monthly processing | 2 |
| 31 | CS1Bank | Add and count both account types | 2 |
| 32 | CS1Bank | Monthly updates dispatch to both account types | 4 |
| 33 | CS1Bank | Multiple months update every account once | 2 |

## Compile, Run, and Capture Plain-Text Output

Use JDK 8 or later. From the directory containing all six files, run:

```text
javac BankAccountInterface.java BankAccount.java SavingsAccount.java CheckingAccount.java CS1Bank.java BankAccountTester.java
java BankAccountTester
```

To save the same plain-text test output for submission, run:

```text
java BankAccountTester > BankAccountTester_output.txt
```

In an IDE, run `BankAccountTester.main()` and save the complete console output to `BankAccountTester_output.txt`. No interactive input is needed.

Every test prints `PASS` or `FAIL`, its description, and points earned versus available. Failed tests include diagnostic details. At the end, the tester prints section subtotals, the number of tests passed, the final score out of 80, and the percentage. A fully passing submission ends with:

```text
SECTION SCORES
Design: 5/5
Common account behavior: 15/15
Savings: 25/25
Checking: 25/25
CS1Bank: 10/10
Tests passed: 33/33
FINAL SCORE: 80/80
PERCENTAGE: 100.00%
```

## Coding Standards and Documentation

Use meaningful names, consistent indentation, and Java naming conventions. Include Javadoc for student classes, constructors, and public methods, and inline comments explaining non-obvious business rules. Use encapsulated instance state and reuse the base class behavior where appropriate. Keep grading and console-output logic in the supplied tester rather than printing from account operations.

The instructor may inspect implementations and use additional tests conforming to these contracts. The automated score does not assess comment quality or guarantee correctness beyond its test cases.

## What to Submit

1. One ZIP file containing your five implemented Java files and the unchanged `BankAccountTester.java`.
2. `BankAccountTester_output.txt` containing the complete output from running the unchanged tester against your implementation.

Submit both items to the assignment portal by the instructor's due date. Do not submit `.class` files in place of Java source. Implement the account and bank types yourself; this specification and tester provide no completed account implementations.
