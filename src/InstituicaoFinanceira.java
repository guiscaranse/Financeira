import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InstituicaoFinanceira {
	private int codigo;
	private double montante;
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	InstituicaoFinanceira(int codigo, double m){
		this.montante = m;
		this.codigo = codigo;
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
	public ArrayList<Emprestimo> getEmprestimosPorCliente(Cliente c){
		ArrayList<Emprestimo> busca = new ArrayList<Emprestimo>();
		for (Emprestimo e : this.emprestimos){
			if(e.getCliente().equals(c)){
				busca.add(e);
			}
		}
		/*
		 * Aqui iremos inserir um in-line comparator e construir para comparar o valor das parcelas
		 * Como por padrão "compare" só retorna inteiro, é possível usar o compare do da própria classe do double
		 * para realizar a comparação ao invés de simplesmente subtraí-los
		 */
	    Collections.sort(busca, new Comparator<Emprestimo>() {
	        @Override public int compare(Emprestimo e1, Emprestimo e2) {
	        	return Double.compare(e1.getParcelaAtual().getValor(), e2.getParcelaAtual().getValor());  
	        }
	    });
		return busca;
	}
	public boolean adicionaEmprestimo(Emprestimo e){
		ArrayList <Emprestimo> e_cliente = getEmprestimosPorCliente(e.getCliente());
		for (Emprestimo o : e_cliente){
			if(o.getSaldoDevedor() > 0){
				return false;
			}
		}
		if(this.montante - e.getValorEmprestimo() < 0){
			return false;
		}else{
			// Empréstimo aceito
			this.emprestimos.add(e);
			return true;
		}
	}
}
