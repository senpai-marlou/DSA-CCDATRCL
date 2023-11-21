class Purchase {
	private String itemName;
	private double itemPrice;
	private int quantity;

	public Purchase(String itemName, double itemPrice, int quantity) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}
}
