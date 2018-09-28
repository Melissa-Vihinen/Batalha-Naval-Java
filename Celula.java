public class Celula implements Imprimivel{
	/*	O atributo nuvem ajuda na impressao: 
	*/
	private String nuvem="oOo";
	private String conteudo;
	private boolean tiro;
	
	public Celula(){
		tiro=false;
		setConteudo("~~~");
	}
	/*	Aqui vamos ter agua (~~~) ou navio ( N ), ou ate mesmo x ( X ) que indica um navio afundado.
	*/
	public void setConteudo(String conteudo){
		this.conteudo=conteudo;
	}
	
	public String getConteudo(){
		return conteudo;
	}
	
	public boolean getTiro(){
		return tiro;
	}
	
	public void setTiro(){
		if(conteudo!="~~~")
			conteudo=" X ";
		tiro=true;
	}
	
	public String getNuvem(){
		return nuvem;
	}
	/*	Ha dois metodos de impressao, pois devemos conseguir ver nosso tabuleiro e tambem
		ter um tabuleiro suporte para ver onde ja atiramos pra nao dar tiro no mesmo lugar
	*/
	@Override
	public void imprimir(){
		System.out.print(getConteudo());
	}
	@Override
	public void imprimirAuxiliar(){
		if(tiro)
			System.out.print(getConteudo());
		else
			System.out.print(getNuvem());
	}
}
