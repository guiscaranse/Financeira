
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

class Menu {

    public static void main(String[] args) {
        InstituicaoFinanceira i = new InstituicaoFinanceira(1, 100000);
        String menu = "Selecione uma opção: \n"
                + "  1) Cadastrar emprestimo\n"
                + "  2) Remover emprestimo\n"
                + "  3) Buscar clientes por nome\n"
                + "  4) Buscar empréstimos por cliente\n"
                + "  5) Quitar empréstimo\n"
                + "  6) Relatório\n"
                + "  7) Sair\n";

        tela:
        while (true) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcao) {
                case 1:
                    try {
                        // Tenta logar o cliente, caso contrário aborta.
                        Cliente c = selecionaCliente(i);
                        try {
                            // Tenta criar empréstimo, caso contrário aborta.
                            novoEmprestimo(c, i);
                        } catch (IllegalArgumentException x) {

                        }
                    } catch (IllegalArgumentException x) {
                    }
                    break;
                case 2:
                    try {
                        Emprestimo e = selecionaEmprestimo(i);
                        if (!i.removeEmprestimo(e)) {
                            JOptionPane.showMessageDialog(null, "Não foi possível remover seu impréstimo. Alguns motivos podem ser:\n1. O empréstimo está bloqueado.\n2. O empréstimo selecionado é válido?");
                        } else {
                            JOptionPane.showMessageDialog(null, "Empréstimo removido!");
                        }
                    } catch (IllegalArgumentException x) {

                    }
                    break;
                case 3:
                    try {
                        buscaClientePorNome(i);
                    } catch (IllegalArgumentException x) {

                    }
                    break;
                case 4:
                    try {
                        Cliente c = selecionaCliente(i);
                        exibeEmprestimosCliente(c, i);

                    } catch (IllegalArgumentException x) {
                        JOptionPane.showMessageDialog(null, "Oops algo deu errado...");
                    }
                    break;
                case 5:
                    try {
                        Cliente c = selecionaCliente(i);
                        quitarEmprestimosPorCliente(c, i);

                    } catch (IllegalArgumentException x) {
                        JOptionPane.showMessageDialog(null, "Oops... Empréstimo inválido!");
                    }
                    break;
                case 6:
                    relatorio(i);
                    break;
                case 7:
                    break tela;
            }

        }
    }

    private static void exibeEmprestimosCliente(Cliente c, InstituicaoFinanceira i) {
        String r = "Emprestimos encontrados:\n";
        int x = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:m");
        System.out.print(i.getEmprestimosPorCliente(c).size());
        for (Emprestimo e : i.getEmprestimosPorCliente(c)) {
            x++;
            r += x + ") Cliente: " + e.getCliente().getNome()
                    + "\n     Código: " + e.getCodigo()
                    + "\n     Valor do Empréstimo: R$" + e.getValorEmprestimo()
                    + "\n     Número de parcelas restantes: " + e.getQuantidadeParcelasRestantes()
                    + "\n     Saldo devedor: R$" + e.getSaldoDevedor()
                    + "\n     Data: " + sdf.format((Date) e.getData().getTime())
                    + "\n";

        }
        JOptionPane.showMessageDialog(null, r);

    }

    public static Cliente selecionaCliente(InstituicaoFinanceira i) {
        /*
         * Aqui iremos tentar escolher um cliente, o menu é dinamicamente gerado com base no número de clientes
         * Quando o cliente for escolhido nós retornamos o mesmo
         * Caso alguma opção inválida ou sair for escolhida nós retornamos uma excessão para interromper o método
         */
        Cliente r;
        String menu = "Selecione um cliente: \n"
                + "1) Cadastrar novo cliente\n";
        int x = 1;
        for (Cliente c : Cliente.getClientes()) {
            x++;
            menu += x + ") " + c.getNome() + " (" + c.getCpf() + ")\n";
        }
        menu += x + 1 + ") Sair";
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        if (opcao == 1) {
            String nome = JOptionPane.showInputDialog("Qual é o seu nome?");
            String cpf = JOptionPane.showInputDialog("Qual é o seu cpf?");
            for (Cliente d : Cliente.getClientes()) {
                if (d.getNome().equals(nome) && d.getCpf().equals(cpf) || d.getCpf().equals(cpf)) {
                    // Verificamos se o cliente já existe
                    JOptionPane.showMessageDialog(null, "Cliente ou CPF já cadastrado");
                    throw new IllegalArgumentException();
                }
            }
            r = new Cliente(nome, cpf);
        } else if (opcao == x + 1) {
            throw new IllegalArgumentException();
        } else if (opcao > x + 1 || opcao < 1) {
            JOptionPane.showMessageDialog(null, "Opção Inválida");
            throw new IllegalArgumentException();
        } else {
            r = Cliente.getClientes().get(x - 2);
        }
        return r;
    }

    public static Emprestimo novoEmprestimo(Cliente c, InstituicaoFinanceira i) {
        /*
         * Aqui iremos tentar criar um empréstimo
         */
        Emprestimo e = new Emprestimo(c);
        double v_emprestimo = Double.parseDouble(JOptionPane.showInputDialog("Valor do empréstimo?"));
        int parcelas = Integer.parseInt(JOptionPane.showInputDialog("Em quantas parcelas você deseja pagar?"));
        e.setBloqueado(false);
        e.setValorEmprestimo(v_emprestimo);
        e.setSaldoDevedor(v_emprestimo);
        e.setQuantidadeParcelasRestantes(parcelas);
        e.setCodigo(i.getEmprestimos().size() + 1);
        Calendar data = Calendar.getInstance();
        data.add(Calendar.MONTH, 1); // Parcela atual é para próximo mês
        e.setParcelaAtual(new Parcela(data, v_emprestimo / parcelas));
        if (i.adicionaEmprestimo(e)) {
            return e;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível criar seu impréstimo. Alguns motivos podem ser:\n1. Você já possui um empréstimo\n2. A instituição não possuí dinheiro suficiente");
            throw new IllegalArgumentException();
        }

    }

    public static Emprestimo selecionaEmprestimo(InstituicaoFinanceira i) {
        /*
         * Aqui iremos tentar escolher um empréstmo, o menu é dinamicamente gerado com base no número de empréstimos
         * Quando o empréstimo for escolhido nós retornamos o mesmo
         * Caso alguma opção inválida ou sair for escolhida nós retornamos uma excessão para interromper o método
         */
        Emprestimo r;
        String menu = "Selecione um empréstimo: \n";
        int x = 0;
        for (Emprestimo z : i.getEmprestimos()) {
            x++;
            menu += x + ") Código: " + z.getCodigo()
                    + "\n     De: " + z.getCliente().getNome()
                    + "\n     Valor: " + z.getValorEmprestimo() + "\n";
        }
        menu += x + 1 + ") Sair";
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        if (opcao == x + 1) {
            throw new IllegalArgumentException();
        } else if (opcao > x + 1 || opcao < 1) {
            JOptionPane.showMessageDialog(null, "Opção Inválida");
            throw new IllegalArgumentException();
        } else {
            r = i.getEmprestimos().get(x - 1);
        }
        return r;
    }

    public static void buscaClientePorNome(InstituicaoFinanceira i) {
        /*
         * Aqui iremos tentar encontrar o cliente, o menu é dinamicamente gerado com base no número de clientes encontrados
         */
        String r = "Usuários encontrados:\n";
        String nome = JOptionPane.showInputDialog("Qual é o nome do cliente?");
        int x = 0;
        for (Cliente z : i.buscaClientesPorNome(nome)) {
            x++;
            r += x + ") Nome: " + z.getNome()
                    + "\n     CPF: " + z.getCpf()
                    + "\n     Número de empréstimos: " + i.getEmprestimosPorCliente(z).size() + "\n";
        }
        JOptionPane.showMessageDialog(null, r);
    }

    public static void relatorio(InstituicaoFinanceira i) {
        String r = "Relatório:"
                + "\nInstituição (Código): " + i.getCodigo()
                + "\nNúmero de Clientes: " + Cliente.getClientes().size()
                + "\nNúmero de Empréstimos: " + i.getEmprestimos().size()
                + "\n########################################\n"
                + "Clientes: \n";
        int x = 0;
        for (Cliente z : Cliente.getClientes()) {
            x++;
            r += x + ") Nome: " + z.getNome()
                    + "\n     CPF: " + z.getCpf()
                    + "\n     Número de empréstimos: " + i.getEmprestimosPorCliente(z).size() + "\n";
        }
        r += "########################################\n";
        r += "Empréstimos: \n";
        x = 0;
        for (Emprestimo z : i.getEmprestimos()) {
            x++;
            r += x + ") Código: " + z.getCodigo()
                    + "\n     De: " + z.getCliente().getNome()
                    + "\n     Valor: R$" + z.getValorEmprestimo()
                    + "\n     Saldo devedor: R$" + z.getSaldoDevedor() + "\n";
        }
        JOptionPane.showMessageDialog(null, r);
    }

    private static void quitarEmprestimosPorCliente(Cliente c, InstituicaoFinanceira i) {
        String r = "Emprestimos encontrados:\n";
        int x = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:m");
        System.out.print(i.getEmprestimosPorCliente(c).size());
        for (Emprestimo e : i.getEmprestimosPorCliente(c)) {
            x++;
            if (e.getSaldoDevedor() > 0) {
                r += x + ") Cliente: " + e.getCliente().getNome()
                        + "\n     Código: " + e.getCodigo()
                        + "\n     Valor do Empréstimo: R$" + e.getValorEmprestimo()
                        + "\n     Número de parcelas restantes: " + e.getQuantidadeParcelasRestantes()
                        + "\n     Saldo devedor: R$" + e.getSaldoDevedor()
                        + "\n     Data: " + sdf.format((Date) e.getData().getTime())
                        + "\n";
            }else{
                        r += x + ") Cliente: " + e.getCliente().getNome()
                        + "\n     Código: " + e.getCodigo()
                        + "\n     Valor do Empréstimo: R$" + e.getValorEmprestimo()
                        + "\n     [QUITADO]"
                        + "\n     Data: " + sdf.format((Date) e.getData().getTime())
                        + "\n";
            }
        }
        r += "\nQual emprestimo deseja quitar?\n";
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(r));
        if (opcao == x + 1) {
            throw new IllegalArgumentException();
        } else if (opcao > x + 1 || opcao < 1) {
            JOptionPane.showMessageDialog(null, "Opção Inválida");
            throw new IllegalArgumentException();
        } else {
            if(i.getEmprestimos().get(opcao - 1).getSaldoDevedor() > 0){
                if (i.quitarEmprestimo(i.getEmprestimos().get(opcao - 1))) {
                    JOptionPane.showMessageDialog(null, "Quitado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Empréstimo não pode ser removido");
                    throw new IllegalArgumentException();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Empréstimo já quitado!");
            }
        }

    }
}
