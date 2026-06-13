package Objetos.Buscar;

import methods.Filme;

public class NoABB {
	public Filme filme;
	public NoABB esq,dir;
	
	public NoABB(Filme filme) {
		this.filme = filme;
		this.esq = null;
		this.dir = null;
	}
	
}
