import java.util.ArrayList;

public class Cliente {
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private String cpf;
	private String nome;
	Cliente(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
		clientes.add(this);
	}
	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public static void setClientes(ArrayList<Cliente> clientes) {
		Cliente.clientes = clientes;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean equals(Cliente c) {
		if(c.getCpf().equals(this.cpf) && c.getNome().equals(this.nome)){
			return true;
		}
		return false;
	}
}
