public class TesteNavio {
	public static void main(String[] args){
		//teste construtor e do metodo getTipo
		Navio navioTeste=new Navio("Navio Teste",5);
		System.out.println("Tipo do Navio: " + navioTeste.getTipo());
		System.out.println("Navio afundado: " + navioTeste.checaAfundado());
		
		//teste do metodo de decremento das celulas ativas e chaca afundado
		for(int i=0;i<4;i++)
			navioTeste.decrementaCelulasAtivas();
		System.out.println("Navio afundado: " + navioTeste.checaAfundado());
		navioTeste.decrementaCelulasAtivas();
		System.out.println("Navio afundado: " + navioTeste.checaAfundado());
		
		
		System.out.println();
		
		System.out.println("Teste finalizado");
	}
}