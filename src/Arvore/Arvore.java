package Arvore;

public class Arvore {
  avlNo raiz;

  public Arvore(avlNo raiz) {
    this.raiz = raiz;
  }

  public void Inserir(avlNo novo) {
    raiz = InserirNovo(novo, raiz);
  }

  // #region Inserção
  private avlNo InserirNovo(avlNo novo, avlNo atual) {
    if (atual == null)
      return novo;

    if (novo.palavra.compareTo(atual.palavra) < 0)
      atual.esquerda = InserirNovo(novo, atual.esquerda);
    else if (novo.palavra.compareTo(atual.palavra) > 0)
      atual.direita = InserirNovo(novo, atual.direita);
    else if (novo.palavra.compareTo(atual.palavra) == 0) {
      atual.adicionarLinha(novo.linha);
    }

    return atual;
  }
  // #endregion

  // #region Remoção
  public boolean remover(String palavra) {
    if (raiz == null)
      return false;
    else {
      avlNo pai;
      avlNo noX;
      if (raiz.palavra.compareTo(palavra) == 0) {
        pai = raiz;
        noX = raiz;
      } else {

        pai = encontrarElemento(raiz, palavra);
        if (pai.palavra.compareTo(palavra) < 0)
          noX = pai.direita;
        else
          noX = pai.esquerda;
      }

      if (noX.direita == null && noX.esquerda == null) {
        if (pai.palavra.compareTo(palavra) < 0)
          pai.direita = null;
        else
          pai.esquerda = null;

      } else {
        if (noX.direita != null && noX.esquerda != null) {
          avlNo noPaiDireitaEsquerda = maisEsquerdaPossivel(noX, noX.direita);
          avlNo substituto = noPaiDireitaEsquerda.esquerda;
          noPaiDireitaEsquerda.esquerda = null;
          substituto.direita = noX.direita;
          substituto.esquerda = noX.esquerda;
          noX.esquerda = null;
          noX.direita = null;

        } else {
          if (noX.direita == null) {
            if (pai.palavra.compareTo(palavra) > 0)
              pai.direita = noX.esquerda;
            else
              pai.esquerda = noX.esquerda;
          }
          if (noX.esquerda == null) {
            if (pai.palavra.compareTo(palavra) > 0)
              pai.direita = noX.direita;
            else
              pai.esquerda = noX.direita;
          }

        }
      }
      return true;
    }
  }

  avlNo encontrarElemento(avlNo atual, String palavra) {
    if (atual == null)
      return null;

    if (palavra.compareTo(atual.palavra) == 0)
      return atual;

    if (palavra.compareTo(atual.palavra) > 0) {

      if (atual.direita != null && atual.direita.palavra.compareTo(palavra) == 0)
        return atual;
      return encontrarElemento(atual.direita, palavra);
    } else {
      if (atual.esquerda != null && atual.esquerda.palavra.compareTo(palavra) == 0)
        return atual;
      return encontrarElemento(atual.esquerda, palavra);
    }
  }

  private avlNo maisEsquerdaPossivel(avlNo pai, avlNo filhoAtual) {
    if (filhoAtual.esquerda == null)
      return pai;
    return maisEsquerdaPossivel(filhoAtual, filhoAtual.esquerda);
  }
  // #endregion

  // #region Exibição
  public void PreOrdem() {
    PreOrdem(raiz);
    System.out.println();
  }

  private void PreOrdem(avlNo elemento) {
    if (elemento != null) {
      System.out.print(elemento.palavra + " ");
      PreOrdem(elemento.esquerda);
      PreOrdem(elemento.direita);
    }
  }

  public void PosOrdem() {
    PosOrdem(raiz);
    System.out.println();
  }

  private void PosOrdem(avlNo elemento) {
    if (elemento != null) {
      PosOrdem(elemento.esquerda);
      PosOrdem(elemento.direita);
      System.out.print(elemento.palavra + " ");
    }
  }

  public void EmOrdem() {
    EmOrdem(raiz);
    System.out.println();
  }

  private void EmOrdem(avlNo elemento) {
    if (elemento != null) {

      EmOrdem(elemento.esquerda);
      System.out.print(elemento.palavra + " ");
      for (int i = 0; i < elemento.linhas.size(); i++) {
        System.out.print(elemento.linhas.get(i) + " ");
      }
      System.out.println("");
      EmOrdem(elemento.direita);
    }
  }
  // #endregio

}
