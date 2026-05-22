 import java.util.Scanner;


public class App{
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);


        System.out.println("--- SOLARIA ---");
       
        // PERSONAGEM
        System.out.println("Escolha seu personagem:");
        System.out.println("1. ZION (Guardião)");
        System.out.println("2. NURI (Guardiã)");
        int opcaoGenero = leia.nextInt();
       
        String titulo;
        if (opcaoGenero == 2) {
            titulo = "Guardiã";
        } else {
            titulo = "Guardião";
        }


        System.out.println("\nBem-vindo(a), " + titulo + "! A harmonia foi destruída por Galactus.");
        System.out.println("Sua missão é recuperar as joias sagradas e restaurar a luz.");


        // Dados das Fases
        String[] locais = {"Deserto da Solidão", "Vulcão de Aurion", "Caverna Lunar", "Castelo de Solaria"};
        String[] nomesInimigos = {"Nythera", "Vyrion", "Nux", "Galactus"};
       
        double hpUsuarioBase = 100;
        double hpInimigoBase = 150;
        double danoBase = 20;


        for (int fase = 0; fase < 4; fase++) {
            // Narrativa de Ambiente e Inimigo
            System.out.println("\n========================================");
            if (fase == 0) {
                System.out.println("AMBIENTE: O " + locais[fase] + " estende-se à sua frente. O calor drena suas forças.");
                System.out.println("INIMIGO: " + nomesInimigos[fase] + " surge das areias. O " + titulo + " sente uma presença sombria.");
            } else if (fase == 1) {
                System.out.println("AMBIENTE: No " + locais[fase] + ", rios de magma cercam o caminho.");
                System.out.println("INIMIGO: " + nomesInimigos[fase] + " bloqueia a passagem com sua armadura incandescente.");
            } else if (fase == 2) {
                System.out.println("AMBIENTE: A " + locais[fase] + " é um labirinto de ecos e sombras lunares.");
                System.out.println("INIMIGO: " + nomesInimigos[fase] + " flutua no vazio, pronta para confundir o " + titulo + ".");
            } else if (fase == 3) {
                System.out.println("AMBIENTE: O " + locais[fase] + " está irreconhecível, tomado por trevas absolutas.");
                System.out.println("INIMIGO: Galactus, o Devorador de Luz, aguarda pelo confronto final.");
            }
            System.out.println("========================================");


            // Progressão de 50%
            double multiplicador = 1.0;
            for (int m = 0; m < fase; m++) {
                multiplicador *= 1.5;
            }


            double hpUsuario = hpUsuarioBase * multiplicador;
            double hpInimigo = hpInimigoBase * multiplicador;
            double danoFase = danoBase * multiplicador;
           
            boolean contragolpeDisponivel = true;
            boolean usuarioVenceuFase = false;


            // Loop de 6 Rounds
            for (int round = 1; round <= 6; round++) {
                System.out.println("\n--- ROUND " + round + " / 6 ---");
               
                // Inimigo ataca primeiro
                double danoInimigoTurno = danoFase;
                System.out.println(nomesInimigos[fase] + " ataca o " + titulo + " causando " + danoInimigoTurno + " de dano!");


                System.out.println("Sua vez! Escolha sua ação:");
                System.out.println("1. Ataque | 2. Defesa | 3. Contragolpe (1x por fase)");
                int escolha = leia.nextInt();


                if (escolha == 1) {
                    System.out.println("Você contra-atacou com força!");
                    hpInimigo -= danoFase;
                } else if (escolha == 2) {
                    System.out.println("Defesa ativada! Dano do inimigo reduzido.");
                    danoInimigoTurno /= 2;
                } else if (escolha == 3) {
                    if (contragolpeDisponivel) {
                        System.out.println("CONTRAGOLPE! Dano anulado e refletido!");
                        hpInimigo -= danoFase;
                        danoInimigoTurno = 0;
                        contragolpeDisponivel = false;
                    } else {
                        System.out.println("Contragolpe já utilizado! Realizando ataque simples.");
                        hpInimigo -= danoFase;
                    }
                }


                hpUsuario -= danoInimigoTurno;
                System.out.println("HP " + titulo + ": " + hpUsuario + " | HP " + nomesInimigos[fase] + ": " + hpInimigo);


                // Condição de Vitória ou Derrota Imediata
                if (hpInimigo <= 0) {
                    System.out.println("\nInimigo derrotado! O " + titulo + " purificou o local.");
                    usuarioVenceuFase = true;
                    break;
                }
                if (hpUsuario <= 0) {
                    break;
                }


                // Critério de Desempate no 6º Round
                if (round == 6) {
                    if (hpUsuario > hpInimigo) {
                        System.out.println("\nFim do tempo! Vitória do " + titulo + " por maior HP.");
                        usuarioVenceuFase = true;
                    } else {
                        System.out.println("\nFim do tempo! O inimigo prevaleceu.");
                    }
                }
            }


            if (!usuarioVenceuFase) {
                System.out.println("\nGAME OVER. Solaria permanece nas sombras.");
                return;
            }
        }


        System.out.println("\n***************************************************");
        System.out.println("PARABÉNS, " + titulo + "! Você salvou o mundo de solaria!");
        System.out.println("As Joias Sagradas retornaram ao seu lugar de direito.");
        System.out.println("***************************************************");
       
        leia.close();
    }
}

