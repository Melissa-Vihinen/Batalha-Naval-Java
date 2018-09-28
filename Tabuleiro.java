//lembrar de tirar comentarios antes da apresentação

import java.util.Scanner;
public class Tabuleiro {
	private Celula[][] tabuleiro;
	private Navio[] navios;
	private int naviosAtivos;
	/*	Construtor que cria a matriz e chama um metodo para colocar Celulas nessa matriz,
		pois criar a matriz apenas cria uma matriz de nulls
		O construtor tambem cria os navios 
	*/
	public Tabuleiro (int tamanho) throws Exception{
		if (tamanho<8 || tamanho >15){
			throw new Exception("\nTamanho do tabuleiro deve ser de 8 ate 15!\n");
		}
		if((Object)tamanho == null)
			throw new Exception("Digite um inteiro");
		tabuleiro=new Celula[tamanho][tamanho];
		inicializaCelulas(tamanho);
		inicializaNavios();
	}
	/*
	public Tabuleiro() throws Exception{
		this(10);
	}
	/*	Esse metodo  apenas cria celulas e as põe na matriz
	*/
	public void inicializaCelulas(int tamanho){
		for(int i=0;i<tamanho;i++)
			for(int j=0;j<tamanho;j++)
				tabuleiro[i][j]=new Celula();
	}
	/*	Esse metodo apenas cria os navios
	*/
	public void inicializaNavios(){
		navios=new Navio[10];
		int i=0;
		for(i=0;i<4;i++)
			navios[i]=new Submarino();
		for(i=4;i<7;i++)
			navios[i]=new ContraTorpedeiro();
		for(i=7;i<9;i++)
			navios[i]=new NavioTanque();
		navios[i]=new PortaAviao();
		naviosAtivos=10;
	}
	/*	Metodo que pergunta se o navio passado como parametro foi afundado: se foi ele decrementa o 
		numero de navios ativos
	*/
	public void checaAfundou(Navio navio){
		if(navio.checaAfundado()){
			naviosAtivos--;
			System.out.println("Foi afundado um " + navio.getTipo()+"!");
		}
	}
	/*	Ao darmos um tiro devemos saber qual navio atingimos. O metodo abaixo faz isso: procura a 
		qual navio a celula passada como parametro pertence, de outra forma, vê se a celula do parametro
		bate com alguma ocupada pelo navio. Isso e feito pra cada navio do jogo, isto e, para cada navio 
		no vetor navios
	*/
	public Navio procuraAlvo(Celula celula){
		int i;
		for(i=0;i<navios.length;i++){
			if(navios[i].checaCelulasOcupadas(celula))
				break;
		}
		return navios[i];
	}
	/*	Aqui sabemos que a celula do parametro e de algum navio. Entao procuramos esse navio alvo, depois 
		diminuimos o numero de celulas ativas dele, pois acertamos uma, e por ultimo checamos se ele afundou
		com esse tiro
	*/
	public void atiraNoNavio(Celula celula){
		Navio navioAux=procuraAlvo(celula);
		navioAux.decrementaCelulasAtivas();
		checaAfundou(navioAux);
	}
	/*	Metodo para auxiliar na hora de dar um tiro: o metodo dar tiro ficara numa classe diferente
		poir isso que temos que conseguir passar a celula para tal classe
	*/
	public Celula getCelula(int linha, int coluna){
		return tabuleiro[linha][coluna];
	}
	/*	Retorna a quantidade de navios que ainda restam 
	*/
	public Navio[] getNavios(){
		return navios;
	}	

	public int getNaviosAtivos(){
		return naviosAtivos;
	}
	
	public int tamanhoTabuleiro(){
		return tabuleiro.length;
	}

}
