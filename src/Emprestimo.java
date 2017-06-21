import java.util.Calendar;

public class Emprestimo {
	private int codigo, quantidadeParcelasRestantes;
	private double valorEmprestimo, saldoDevedor;
	private boolean bloqueado;
	private Parcela parcelaAtual;
	private Calendar data;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQuantidadeParcelasRestantes() {
		return quantidadeParcelasRestantes;
	}
	public void setQuantidadeParcelasRestantes(int quantidadeParcelasRestantes) {
		this.quantidadeParcelasRestantes = quantidadeParcelasRestantes;
	}
	public double getValorEmprestimo() {
		return valorEmprestimo;
	}
	public void setValorEmprestimo(double valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public Parcela getParcelaAtual() {
		return parcelaAtual;
	}
	public void setParcelaAtual(Parcela parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	/*
	 * Métodos extras
	 */
	public boolean equals(Object o){
		if (!(o instanceof Emprestimo)){
			return false;
		}
		Emprestimo j = (Emprestimo) o;
		return this.codigo == j.getCodigo();
	}
}
