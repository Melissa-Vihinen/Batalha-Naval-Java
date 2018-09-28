import java.util.Random;
import java.util.Scanner;

public class BatalhaNavalComputador extends BatalhaNaval {
	private int sorteio, tamanho, linha, coluna;
	private char orientacao;
	private String celulaAnterior = "~~~";
	private int sequencia=4;

	public BatalhaNavalComputador(){
		criaTabuleiros();
	}

	@Override
	public void jogar(){
		posicionaNaviosTabuleiros();
		while(true){
			darTiro(1);
			imprimir(1);
			if(checaFimDeJogo()){
				System.out.println("\t\t-----Parabens! Voce ganhou!------");
				break;
			}
			tipoTiro();
			if(checaFimDeJogo()){
				System.out.println("\t\t-----Computador ganhou! Mais sorte da proxima vez!------");
				break;
			}
		}
	}
	public int aleatorio() {
		Random gerador = new Random();
		tamanho = getTabuleiro(1).tamanhoTabuleiro();
		sorteio = gerador.nextInt(tamanho);
		return sorteio;
	}
	public char orientacao() {
		if (aleatorio() < ((tamanho)/2))
			orientacao = 'h';
		else
			orientacao = 'v';
		return orientacao;
	}
	
	public void darTiroNaCelula(int linha, int coluna)throws Exception{
		Tabuleiro tabuleiroAux=getTabuleiro(1);
		if (coluna>=tabuleiroAux.tamanhoTabuleiro() || linha>=tabuleiroAux.tamanhoTabuleiro() || linha<0 || coluna<0){
			throw new Exception ("Tiro fora do tabuleiro!");
		}
		Celula celula=tabuleiroAux.getCelula(linha-1, coluna-1);
		if(celula.getTiro())
			throw new Exception("\nJa foi dado tiro nessa celula!\n");
		celula.setTiro();
		if(celula.getConteudo().equals(" X ")){
			tabuleiroAux.atiraNoNavio(celula);
			sequencia=4;
			this.linha=linha;
			this.coluna=coluna;
			celulaAnterior = " X ";
		}
		else if(sequencia==0){
			celulaAnterior = "~~~";
			sequencia=4;
		}
	}
	public void tipoTiro(){	
		if(celulaAnterior.equals(" X ")){
			darTiroInteligente(linha, coluna);
		}
		else{
			try{
				darTiroAleatorio();
			}
			catch (Exception e){
				tipoTiro();
			}
		}
	}

	public void darTiroAleatorio(){ 			
		try {	
			this.linha = aleatorio();
			this.coluna = aleatorio();
			darTiroNaCelula(linha, coluna);
		} catch (Exception e) {
			darTiroAleatorio();
		}
	}	
	
	public void darTiroInteligente(int linha, int coluna){ 
		System.out.println(sequencia+ "");
		boolean continuaExcecao=true;
		while(continuaExcecao){		
			try{
				if (sequencia == 4){
					sequencia--;
					darTiroNaCelula(this.linha-1,this.coluna);
				}
				else if (sequencia == 3){
					sequencia--;
					darTiroNaCelula(this.linha+1, this.coluna);
				}
				else if (sequencia == 2){
					sequencia--;
					darTiroNaCelula(this.linha, this.coluna-1);
				}
				else if (sequencia == 1){
					sequencia--;
					darTiroNaCelula(this.linha, this.coluna+1);
				}
				continuaExcecao=false;	
			}
			catch(Exception e){
				if(sequencia==0){
					sequencia=4;
					celulaAnterior= "~~~";
					continuaExcecao=false;
					darTiroAleatorio();
				}
			}
		}
	}
	
	public void posicionaNaviosTabuleiros(){
		System.out.println("\n\t-------Posicionamento de navios: jogador 1-------\n");
		posicionaNavios(getTabuleiro(1),1);
		System.out.println("\nSeu tabuleiro ficou como segue\n");
		imprimir(1);
		posicionaNavios();
		//imprimir(2);
	}
	public void posicionaNavios(){
		boolean continuaExcecao;
		Navio[] naviosAuxiliar=getTabuleiro(2).getNavios();
		for(int i = 0;i < naviosAuxiliar.length; i++){
			continuaExcecao=true;
			while (continuaExcecao){
				try{
					naviosAuxiliar[i].posicionaNavio(aleatorio(), aleatorio(), orientacao(),getTabuleiro(2));
					continuaExcecao = false;
				}
				catch (Exception e){
				}	
			}
		}
	}
}
