import java.util.ArrayList;

class teste {

	public static void main(String[] args) {
		// TODO Stub de método gerado automaticamente
		ArrayList<String> alphabetList = new ArrayList<String>();
		alphabetList.add("A"); // 0 index
		alphabetList.add("B"); // 1 index
		alphabetList.add("C"); // 2 index
		alphabetList.add("D"); // 3 index
		alphabetList.add("E"); // 4 index
		alphabetList.add("F"); // 5 index
		alphabetList.add("G"); // 6 index
		alphabetList.add("H"); // 7 index
		alphabetList.add("I"); // 8 index

		int position = alphabetList.indexOf("K");
		if (position == -1) {
		   System.out.println("Object not found in List");
		} else {
			System.out.println(position);
		}
	}

}
