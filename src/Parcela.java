import java.util.Calendar;

public class Parcela{
	private Calendar dataVencimento;
	private double valor;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}
