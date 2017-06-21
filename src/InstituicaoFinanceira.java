import java.util.ArrayList;

public class InstituicaoFinanceira {
	private int codigo;
	private double montante;
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	InstituicaoFinanceira(double m){
		this.montante = m;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getMontante() {
		return montante;
	}
	public void setMontante(double montante) {
		this.montante = montante;
	}
	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	/*
	 * Métodos pedidos do PDF
	 */
	public boolean removeEmprestimo(ArrayList<Emprestimo> emprestimo){
		int posicao = this.emprestimos.indexOf(emprestimo);
		if (posicao != -1) {
			// Encontrou
			if(this.emprestimos.get(posicao).isBloqueado()){
				// Emprestimo bloqueado
				return false;
			}else{
				this.emprestimos.remove(this.emprestimos.get(posicao));
			}
		} 
		return false;
	}
	public ArrayList<Cliente> buscaClientesPorNome(String nome){
		ArrayList<Cliente> busca = new ArrayList<Cliente>();
		for (Cliente c : Cliente.getClientes()){
			if(c.getNome().contains(nome)){
				busca.add(c);
			}
		}
		return busca;
	}
}
