package fiap.tech.challenge.restaurant_manager.exceptions.custom;

public class InvalidLoginException extends RuntimeException{
	
	public InvalidLoginException() {
		super("O login deve ser preenchido");
	}

}
