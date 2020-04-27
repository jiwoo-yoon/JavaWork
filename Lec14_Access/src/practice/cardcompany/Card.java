package practice.cardcompany;

public class Card {
	
	    // 멤버변수
		private int cardNumber;  // 카드 번호
		
		public Card(int cardNumber) {
			this.cardNumber = cardNumber;
		}

		public int getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(int cardNumber) {
			this.cardNumber = cardNumber;
		}
	
} // end class
