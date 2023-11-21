import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Queue;

class ReceiptQueue {

	private int receiptNumber = 0;
	private Queue<Purchase> queue = new ArrayDeque<>();
	private DecimalFormat df = new DecimalFormat("#.00");

	public void addPurchase(Purchase purchase) {
		queue.offer(purchase);
	}

	public Purchase generateReceipt() {
		Purchase purchase = queue.poll();
		if (purchase != null) {
			return purchase;
		}
		return null;
	}

	public void displayReceiptQueue() {
		if (queue.isEmpty()) {
			System.out.println("\nReceipt Queue is empty.");
		} else {
			System.out.println("\n+----------------------------------------------------------------------+");
			System.out.println("|                            RECEIPT QUEUE                             |");
			System.out.println("+-----------------------------+-------------+-------------+------------+");
			System.out.println("| ITEM NAME                   | PRICE       | QUANTITY    | TOTAL      |");
			System.out.println("+-----------------------------+-------------+-------------+------------+");

			int itemNumber = 1;
			double totalBill = 0.0;
			for (Purchase purchase : queue) {
				double itemTotal = purchase.getItemPrice() * purchase.getQuantity();
				System.out.printf("| [%d] %-23s | %-11s | %-11s | %-10s |\n", itemNumber, purchase.getItemName(),
						df.format(purchase.getItemPrice()), purchase.getQuantity(), df.format(itemTotal));
				totalBill += itemTotal;
				itemNumber++;
			}
			System.out.println("+-----------------------------+-------------+-------------+------------+");
			System.out.println("Total bill: " + df.format(totalBill) + " [Tax not included]");
		}
	}

	public void generateReceiptFormat(Purchase purchase) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String currentTime = LocalDateTime.now().format(timeFormatter);

		// Increment and display the customer number
		receiptNumber++;

		System.out.println("\n---------------------------------------------------");
		System.out.println("                 Receipt Generator");
		System.out.println("                  ");
		System.out.println("                National University");
		System.out.println("               1008 Sampaloc, Manila");
		System.out.println("                  (632) 172-1900");
		System.out.println("                  ");
		System.out.printf("               %s   %s\n", java.time.LocalDate.now(), currentTime);
		System.out.println("                  ");
		System.out.printf("                Receipt Number: %04d\n", receiptNumber);
		System.out.println();
		System.out.println("    QTY      ITEM NAME                   AMOUNT    ");
		System.out.println("---------------------------------------------------");

		int quantity = purchase.getQuantity();
		double itemPrice = purchase.getItemPrice();
		double itemTotal = itemPrice * quantity;

		double vatRate = 0.07; // 7% VAT rate of the total item
		double vat = itemTotal * vatRate;
		double Tax = 0.40 * quantity;

		System.out.printf("     %-4d    %-23s     %-7s\n", quantity, purchase.getItemName(), df.format(itemTotal));
		System.out.println("---------------------------------------------------");

		double subtotal = itemTotal - vat;

		System.out.printf("             Subtotal:                   %-7s\n", df.format(subtotal));
		System.out.printf("             VAT @ 7%%:                   %-7s\n", df.format(vat));
		System.out.printf("             Tax:                        %-7s\n", df.format(Tax));
		System.out.println();

		double totalAmount = itemTotal + Tax;

		System.out.printf("             Total Amount:               %-7s\n", df.format(totalAmount));
		System.out.println("---------------------------------------------------");
	}
}
