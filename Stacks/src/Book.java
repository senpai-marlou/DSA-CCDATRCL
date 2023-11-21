class Book {
	String title;
	String author;
	int year;

	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	@Override
	public String toString() {
		return title + " by " + author + " (" + year + ")";
	}

	public String formattedString(int index) {
		return String.format("| [%d] %-23s | %-24s | %-16d |", index, title, author, year);
	}
}