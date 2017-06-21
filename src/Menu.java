import javax.swing.JOptionPane;
class Menu {

	public static void main(String[] args) {
        String menu="Select an option: \n" +
                    "  1) Dive\n" +
                    "  2) High Jump\n" +
                    "  3) Back\n ";

        tela: while(true){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcao)
            {
                case 1:
                    //comando do caso 1
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default :
                	break tela;
            }
            
        }
    }

}