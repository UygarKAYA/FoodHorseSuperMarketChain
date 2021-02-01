import java.sql.*;
import java.util.Scanner;

public class FoodHorse {

    private static final String URL = "jdbc:mysql://localhost:3306/FoodHorse?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    private static final String USERNAME = "Your_Username";
    private static final String PASSWORD = "Your_Password";
    private static Scanner console = new Scanner(System.in);
    private static int OperationNumber;

    private static Connection connection = null;

    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void Display_SelectOperation() {
        System.out.println("------------------------------------------------");
        System.out.println("(1) Customer registration");
        System.out.println("(2) Buying a product");
        System.out.println("(3) List customers");
        System.out.println("(4) List a customer's purchases");
        System.out.println("(5) List a customer's most recent purchases");
        System.out.println("(6) List branches");
        System.out.println("(7) List a branch’s stock");
        System.out.println("(8) Add new branch store");
        System.out.println("(9) Add new product");
        System.out.println("(10) Add product to branch’s stock");
        System.out.println("(11) Search customer by their phone number");
        System.out.println("(12) Remove a user from system");
        System.out.println("(13) Exit");
        System.out.println("------------------------------------------------");
        System.out.println();
        System.out.print("Enter a code number for the operation: ");

        String stringInput = console.nextLine();
        OperationNumber = Integer.valueOf(stringInput);

        while(OperationNumber < 1 || OperationNumber > 13) {
            System.out.print("Please enter a number between 1 to 13: ");
            stringInput = console.nextLine();
            OperationNumber = Integer.valueOf(stringInput);
        }
    }

    public static void SelectOperation() {
        if (OperationNumber==1) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();

                System.out.print("Customer Name: ");
                String customerName = console.nextLine();
                while (customerName.length() > 20) {
                    System.out.print("Please enter valid name: ");
                    customerName = console.nextLine();
                }

                System.out.print("Customer Surname: ");
                String customerSurname = console.nextLine();
                while (customerSurname.length() > 20) {
                    System.out.print("Please enter valid surname: ");
                    customerSurname = console.nextLine();
                }

                System.out.print("Customer Adress: ");
                String customerAdress = console.nextLine();
                while(customerAdress.length() > 40) {
                    System.out.print("Please enter valid adress: ");
                    customerAdress = console.nextLine();
                }

                System.out.print("Customer PhoneNumber: ");
                String customerPhoneNumber = console.nextLine();
                if (customerPhoneNumber.length()<11 || customerPhoneNumber.length()>11) {
                    while (customerPhoneNumber.length() != 11) {
                        System.out.print("Please enter valid phone number: ");
                        customerPhoneNumber = console.nextLine();
                    }
                }

                String insertCustomerQuery = "insert into customer(customerName,customerSurname,customerAdress,customerPhoneNumber)"
                                            +" values ('" + customerName + "', '" + customerSurname + "', '" + customerAdress + ""+"', '"
                                            + customerPhoneNumber + "')";
                statement.executeUpdate(insertCustomerQuery);
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
	
	else if (OperationNumber==2) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();
                System.out.print("CustomerID: ");
                String CustomerId = console.nextLine();
                int customerId = Integer.valueOf(CustomerId);
                while (customerId <= 0) {
                    System.out.print("Please enter a valid CustomerID: ");
                    CustomerId = console.nextLine();
                    customerId = Integer.valueOf(CustomerId);
                }

                System.out.print("BranchID: ");
                String BranchId = console.nextLine();
                int branchId = Integer.valueOf(BranchId);
                while (branchId <= 0) {
                    System.out.print("Please enter a valid BranchID: ");
                    BranchId = console.nextLine();
                    branchId = Integer.valueOf(BranchId);
                }

                System.out.print("ProductID: ");
                String ProductId = console.nextLine();
                int productId = Integer.valueOf(ProductId);
                while (productId <= 0) {
                    System.out.print("Please enter a valid ProductID: ");
                    ProductId = console.nextLine();
                    productId = Integer.valueOf(ProductId);
                }

                System.out.print("OrderTime: ");
                String OrderTime = console.nextLine();
                while (OrderTime.length()<12 || OrderTime.length()>20) {
                    System.out.print("Please enter valid Date (YYYY-MM-DD HH:MI:SS): ");
                    OrderTime = console.nextLine();
                }

                String insertOrderTimeQuery = "insert into OrderTime(customerID,branchID,productID,Otime) values ('" + customerId
                                            + "', '" + branchId + "', '" + productId + "', '" + OrderTime + "')";
                statement.executeUpdate(insertOrderTimeQuery);

                String selectedStockQuery = "select * from stock";
                ResultSet rst = statement.executeQuery(selectedStockQuery);
                int stockNumber = 0;
                while(rst.next()){
                    if(branchId == rst.getInt(1) && productId == rst.getInt(2)) {
                        stockNumber = rst.getInt(3);
                    }
                }
                stockNumber--;
                String updateStockQuery = "update stock set stockquantity = " + stockNumber + " where (branchID = " + branchId
                                                        + " and " + " productID = " + productId + ")";
                statement.executeUpdate(updateStockQuery);
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

	else if (OperationNumber==3) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();
                String selectedCustomerQuery = "select * from customer";
                ResultSet rst = statement.executeQuery(selectedCustomerQuery);
                System.out.println("----------------------------------------------------------------------" +
                                    "-----------------------------------------------------------------");
                System.out.println("  Customer ID  " + "       Customer Name       " + "      Customer Surname      "
                                        + "     Customer Adress    " + "      Customer PhoneNumber   ");
                System.out.println("-----------------------------------------------------------------------" +
                                    "----------------------------------------------------------------");
                while (rst.next()) {
                    System.out.println("Customer ID: " + rst.getInt(1) + " ## "
                                     + " Customer Name: " + rst.getString(2) + " ## "
                                     + " Customer Surname: " + rst.getString(3) + " ## "
                                     + " Customer Adress: " + rst.getString(4) + " ## "
                                     + "Customer PhoneNumber: " + rst.getString(5));
                }
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
	
	else if (OperationNumber==4) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();
                System.out.print("CustomerID: ");
                String customerID = console.nextLine();
                int CustomerId = Integer.valueOf(customerID);
                while (CustomerId <= 0) {
                    System.out.print("Please enter a valid CustomerID: ");
                    customerID = console.nextLine();
                    CustomerId = Integer.valueOf(customerID);
                }

                String selectedParticularCustomerQuery = "select * from orderTime where customerID = " + CustomerId;
                ResultSet rst = statement.executeQuery(selectedParticularCustomerQuery);

                System.out.println("------------------------------------------------------------------------------");
                System.out.println("  CustomerID " + "       BranchID        " + "ProductID     " + "         OrderTime     ");
                System.out.println("------------------------------------------------------------------------------");
                while (rst.next()) {
                        System.out.println("CustomerID: " + rst.getInt(1) + " ## " + " BranchID: " + rst.getInt(2)
                                        + " ## " + " ProductID: " + rst.getInt(3) + " ## " + " OrderTime: " + rst.getString(4));
                }
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

	else if (OperationNumber==5) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();

                System.out.print("CustomerID: ");
                String customerId = console.nextLine();
                int CustomerId = Integer.valueOf(customerId);
                while (CustomerId<=0) {
                    System.out.print("Please enter a valid CustomerID: ");
                    customerId = console.nextLine();
                    CustomerId = Integer.valueOf(customerId);
                }

                String selectedLast5CustomerQuery = "select * from orderTime where customerID = " + CustomerId + " ORDER BY Otime DESC LIMIT 5";
                ResultSet rst = statement.executeQuery(selectedLast5CustomerQuery);

                System.out.println("------------------------------------------------------------------------------");
                System.out.println("  CustomerID " + "       BranchID        " + "ProductID     " + "         OrderTime     ");
                System.out.println("------------------------------------------------------------------------------");
                while (rst.next()) {
                    System.out.println("CustomerID: " + rst.getInt(1) + " ## " + " BranchID: " + rst.getInt(2)
                            + " ## " + " ProductID: " + rst.getInt(3) + " ## " + " OrderTime: " + rst.getString(4));
                }
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
	
	 else if (OperationNumber==6) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();
                String selectedBranchesQuery = "select * from branches";
                ResultSet rst = statement.executeQuery(selectedBranchesQuery);
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("    Branch ID    " + "            Branch Name            " + "          Branch Adress          ");
                System.out.println("------------------------------------------------------------------------------");
                while (rst.next()) {
                    System.out.println("Branch ID: " + rst.getInt(1) + "  ##  "
                                     + " Branch Name: " + rst.getString(2) + "  ##  "
                                     + " Branch Adress: " + rst.getString(3));
                }
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
	
	else if (OperationNumber==7) {
            try {
                establishConnection();
                Statement statement = connection.createStatement();
                System.out.print("BranchID: ");
                String branchId = console.nextLine();
                int BranchId = Integer.valueOf(branchId);
                while (BranchId <= 0) {
                    System.out.print("Please enter a valid BranchID: ");
                    branchId = console.nextLine();
                    BranchId = Integer.valueOf(branchId);
                }
                String selectedBranchIDQuery = "select * from orderTime where branchID = " + branchId;
                ResultSet rst = statement.executeQuery(selectedBranchIDQuery);

                System.out.println("------------------------------------------------------------------------------");
                System.out.println("  CustomerID " + "       BranchID        " + "ProductID     " + "         OrderTime     ");
                System.out.println("------------------------------------------------------------------------------");
                while (rst.next()) {
                    System.out.println("CustomerID: " + rst.getInt(1) + " ## " + " BranchID: " + rst.getInt(2)
                            + " ## " + " ProductID: " + rst.getInt(3) + " ## " + " OrderTime: " + rst.getString(4));
                }
                closeConnection();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
	
        else if (OperationNumber==13) {
            establishConnection();
            System.exit(0);
            closeConnection();
        }
    }
    public static void main(String[] args) {
        while (OperationNumber != 13) {
            Display_SelectOperation();
            SelectOperation();
        }
    }
}