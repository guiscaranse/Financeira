import java.util.Calendar;

public class Parcela{
	private Calendar dataVencimento;
	private double valor;
	Parcela(Calendar vencimento, double valor){
		this.dataVencimento = vencimento;
		this.valor = valor;
	}
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
