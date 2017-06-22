import javax.swing.JOptionPane;
class Menu {

	public static void main(String[] args) {
		InstituicaoFinanceira i = new InstituicaoFinanceira(1, 100000);
        String menu="Selecione uma opção: \n" +
                    "  1) Cadastrar emprestimo\n" +
                    "  2) Remover emprestimo\n" +
                    "  3) Buscar clientes por nome\n" +
                    "  4) Buscar empréstimos por cliente\n" +
                    "  5) Sair\n";

        tela: while(true){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcao)
            {
                case 1:
                	  try {
                		   // Tenta logar o cliente, caso contrário procede
                		   Cliente c = loginCliente(i);
                		  } catch (IllegalArgumentException e) {}
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 5 :
                	break tela;
            }
            
        }
    }
	public static Cliente loginCliente(InstituicaoFinanceira i){
		/*
		 * Aqui iremos tentar escolher um cliente, o menu é dinamicamente gerado com base no número de clientes
		 * Quando o cliente for escolhido nós retornamos o mesmo
		 * Caso alguma opção inválida ou sair for escolhida nós retornamos uma excessão para interromper o método
		 */
		Cliente r;
        String menu="Selecione um cliente: \n" +
                "1) Cadastrar novo cliente\n";
        int x = 1;
        for (Cliente c : Cliente.getClientes()){
        	x++;
        	menu+= x + ") " + c.getNome() + " (" +c.getCpf() + ")\n";
        }
        menu+= x+1 + ") Sair";
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        if(opcao == 1){
        	String nome = JOptionPane.showInputDialog("Qual é o seu nome?");
        	String cpf = JOptionPane.showInputDialog("Qual é o seu cpf?");
            for (Cliente d : Cliente.getClientes()){
            	if(d.getNome().equals(nome) && d.getCpf().equals(cpf)){
            		// Verificamos se o cliente já existe
            		JOptionPane.showMessageDialog(null, "Cliente já cadastrado");
            		throw new IllegalArgumentException();
            	}
            }
        	r = new Cliente(nome, cpf);
        }else if(opcao == x+1){
        	throw new IllegalArgumentException();
        }else if(opcao > x+1 || opcao < x+1){
        	JOptionPane.showMessageDialog(null, "Opção Inválida");
        	throw new IllegalArgumentException();
        }else{
        	r = Cliente.getClientes().get(x-2);
        }
        return r;
	}
	public static Emprestimo novoEmprestimo(Cliente c, InstituicaoFinanceira i){
		/*
		 * Aqui iremos tentar criar um empréstimo
		 */
		Emprestimo e = new Emprestimo(c);
		double v_emprestimo = Double.parseInt(JOptionPane.showInputDialog("Valor do empréstimo?"));
    	int parcelas = Integer.parseInt(JOptionPane.showInputDialog("Em quantas parcelas você deseja pagar?"));
		
		return e;
	}
	

}