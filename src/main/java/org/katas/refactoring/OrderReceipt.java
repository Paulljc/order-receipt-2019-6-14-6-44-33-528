package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;
    public static final String RECEIPT_HEADERS = "======Printing Orders======\n";
    public static final double TAX_RATE = .10;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        printReceiptHeaders(output);

        // prints lineItems
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            printLineItems(lineItem, output);
            double salesTax = getSalesTax(lineItem);
            totalSalesTax += salesTax;
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        //print sales tax and total amount
        printStateTaxAndTotalAmount(output, totalSalesTax, totalAmount);

        return output.toString();
    }

    public void printReceiptHeaders(StringBuilder output) {
        output.append(RECEIPT_HEADERS);
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    public void printLineItems(LineItem lineItem, StringBuilder output) {
        output.append(String.format("%s\t%s\t%s\t%s\n", lineItem.getDescription(), lineItem.getPrice(), lineItem.getQuantity(), lineItem.totalAmount()));
    }

    public void printStateTaxAndTotalAmount(StringBuilder output, double salesTax, double totalAmount) {
        output.append(String.format("Sales Tax\t%s\nTotal Amount\t%s\n", salesTax, totalAmount));
    }

    public Double getSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * TAX_RATE;
    }
}