package Objetos.Buscar;

import methods.Filme;

public class NoAVL {
   public Filme filme;
   public NoAVL esq,dir;
   public int alt;

   public NoAVL(Filme filme) {
       this.filme = filme;
       this.esq = null;
       this.dir = null;
       this.alt = 1;
   }
}