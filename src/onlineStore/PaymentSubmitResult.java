package onlineStore;

public class PaymentSubmitResult {

	private String message = "";
	private PaymentStatus status = PaymentStatus.Fail;

	public PaymentSubmitResult(String message) {
		this.message = message;
	}

	public PaymentSubmitResult(PaymentStatus success) {
		this.status = success;
	}

	public String getMessage() {
		return message;
	}

	public PaymentStatus getStatus() {
		return status;
	}

}
