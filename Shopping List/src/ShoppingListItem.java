
class ShoppingListItem {
	String itemName;
	String itemDescription;
	double itemPrice;
	String purchaseDescription;
	ShoppingListItem next;

	public ShoppingListItem(String itemName, String itemDescription, double itemPrice) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.purchaseDescription = "NOT PURCHASED"; // Default purchase description
		this.next = null;
	}
}
