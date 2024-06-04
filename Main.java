abstract class PersonagemDragonBall {
    protected String nome;
    protected int idade;
    protected char sexo;
    protected int temporada;
    protected int ki;
    protected int poderEspecial;

    public abstract int calcularPoder();
}

interface Transformavel {
    void transformar(int nivel);
}

class Terraqueo extends PersonagemDragonBall {
    private String pais;
    private String cidade;

    @Override
    public int calcularPoder() {
        return ki;
    }

    @Override
    public String toString() {
        return "Terraqueo: " + nome;
    }
}

class Sayajin extends PersonagemDragonBall implements Transformavel {
    private int nivelMaximoSSJ;
    private boolean temRabo;
    private int nivelSSJ;

    @Override
    public int calcularPoder() {
        return (int)(ki * (1 + nivelMaximoSSJ * 0.1));
    }

    @Override
    public void transformar(int nivel) {
        if((nome.equals("Goku") || nome.equals("Vegeta")) && nivel > 3) {
            System.out.println(nome + " transformou para super sayajin nível " + nivel);
            nivelSSJ = nivel;
        } else if(nivel <= 3) {
            System.out.println(nome + " transformou para super sayajin nível " + nivel);
            nivelSSJ = nivel;
        } else {
            System.out.println("Não foi possível transformar esse sayajin");
        }
    }

    @Override
    public String toString() {
        return "Sayajin: " + nome;
    }
}

class Namekuseijin extends PersonagemDragonBall {
    private int quantidadeEsferas;
    private boolean podeCurar;

    @Override
    public int calcularPoder() {
        return (int)(ki * (1 + (podeCurar ? 0.2 : 0)));
    }

    public String fazerDesejo(String desejo) {
        return "Desejo em Namekusei: " + desejo;
    }

    @Override
    public String toString() {
        return "Namekuseijin: " + nome;
    }
}

class PersonagemFactory {
    public static PersonagemDragonBall criarPersonagem(String nome) {
        switch(nome) {
            case "Kuririn":
                Terraqueo kuririn = new Terraqueo();
                kuririn.nome = "Kuririn";
                return kuririn;
            case "Goku":
                Sayajin goku = new Sayajin();
                goku.nome = "Goku";
                return goku;
            case "Gohan":
                Sayajin gohan = new Sayajin();
                gohan.nome = "Gohan";
                return gohan;
            case "Dendê":
                Namekuseijin dende = new Namekuseijin();
                dende.nome = "Dendê";
                return dende;
            default:
                return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Sayajin goku = (Sayajin) PersonagemFactory.criarPersonagem("Goku");
        goku.transformar(5);

        Sayajin gohan = (Sayajin) PersonagemFactory.criarPersonagem("Gohan");
        if(gohan == null) {
            System.out.println("Não foi possível criar Gohan");
        } else {
            gohan.transformar(5);
            if(gohan.calcularPoder() == 0) {
                gohan.transformar(3);
            }
        }

        Namekuseijin dende = (Namekuseijin) PersonagemFactory.criarPersonagem("Dendê");
        System.out.println(dende.fazerDesejo("Traga paz para a Terra"));
    }
}
