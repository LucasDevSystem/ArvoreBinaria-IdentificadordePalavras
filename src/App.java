import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Arvore.Arvore;
import Arvore.avlNo;
import Data.Leitor;
import Data.Palavra;

public class App {
    static Queue<Palavra> fila = new LinkedList<>();
    static Queue<Palavra> amostras = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();

        GeraResultado();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double durationInMillis = duration / 1_000_000.0;

        System.out.println("Tempo de execução de GeraResultado(): " + durationInMillis + " ms");
    }

    private static void TesteArvoreBalanceada() {
        Arvore arvore = new Arvore();

        arvore.Inserir(new avlNo("abacate", 3));

        arvore.Inserir(new avlNo("bacia", 1));

        arvore.EmOrdem();
    }

    private static void GeraResultado() {
        fila = Leitor.LeArquivosECriaObjetos("src/Data/faroeste.txt");

        Arvore arvore = esvaziarFilasEPopularArvore();

        RealizaVariasBuscas(arvore, 100000000);
    }

    private static void RealizaVariasBuscas(Arvore arvore, int quant) {
        while (quant != 0) {
            arvore.buscarPalavra("sofrer");
            quant--;
        }
    }

    // #region Métodos kickoff
    private static Arvore esvaziarFilasEPopularArvore() {
        Arvore arvore = new Arvore();

        while (fila.isEmpty() != true) {

            Palavra word = fila.poll();
            arvore.Inserir(new avlNo(word.getPalavra(), word.getLinha()));

        }
        return arvore;
    }

    private static void printEmOrdemAlfabetica(Arvore arvore) {
        arvore.EmOrdem();
    }

    private static void popularAmostras() {
        for (int i = 0; i < 10; i++) {
            amostras.add(fila.poll());
        }
    }

    public static Palavra encontraPalavraMeioAlfabeto(Queue<Palavra> amostras) {
        List<Palavra> listaPalavras = new ArrayList<>(amostras);

        listaPalavras.sort(Comparator.comparing(Palavra::getPalavra));

        int meio = listaPalavras.size() / 2;
        return listaPalavras.get(meio);
    }
    // #endregion

    // #region Método teste funcionalidades
    private static void TesteFuncionalidadesArvore(Arvore arvore) {
        System.out.println("Inserindo a palavra teste \n\n");
        arvore.Inserir(new avlNo("teste", 15));

        System.out.println("Exibindo a árvore em Pos ordem \n\n");
        arvore.PosOrdem();

        System.out.println("Exibindo a árvore em pré ordem \n\n");
        arvore.PreOrdem();

        System.out.println("Exibindo a árvore em ordem \n\n");
        arvore.EmOrdem();

        System.out.println("Removendo a palavra teste \n\n");
        arvore.remover("teste");

        System.out.println("Exibindo a árvore em ordem \n\n");
        arvore.EmOrdem();
    }
    // #endregion
}
