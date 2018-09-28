public class TesteCelula {
	public static void main(String[] args) {
		Celula celulaTeste=new Celula();
		System.out.println("Teste nuvem: " + celulaTeste.getNuvem());
		celulaTeste.setConteudo("ConteudoTeste");
		System.out.println("Teste conteudo: " + celulaTeste.getConteudo());
		System.out.println(celulaTeste.getTiro());
		System.out.print("Teste imprimir: ");
		celulaTeste.imprimir();
		System.out.println();
		celulaTeste.setTiro();
		System.out.println(celulaTeste.getTiro() + " " + celulaTeste.getConteudo());
		celulaTeste.setConteudo("~~~");
		System.out.print("Teste imprimir: ");
		celulaTeste.imprimir();
	}
}