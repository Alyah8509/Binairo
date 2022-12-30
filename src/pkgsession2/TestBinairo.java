package pkgsession2;

import java.io.*;
/**
 * Le programme tp-1 sert à résoudre un binairo à partir d'un fichier et à inscrire la solution dans un deuxième
 * fichier
 *
 * @autor Shini Yang
 * @version 1.0
 * @since 2021-03-10

 */
public class TestBinairo {
    public static void main(String[] args) throws IOException {
        System.out.print("Attention: Si le Binairo contient des chiffres ou lettres qui débordent du grille,");
        System.out.println("ils ne seront pas pris en compte lors de la résolution.");
        procedure("Binairo1.txt", "1-solution.txt");
        procedure("Binairo2.txt", "2-solution.txt");
        procedure("Binairo3.txt", "3-solution.txt");
        procedure("Binairo4.txt", "4-solution.txt");
        procedure("Binairo5.txt", "5-solution.txt");
        procedure("Binairo6.txt", "6-solution.txt");
        procedure("Binairo7.txt", "7-solution.txt");
        procedure("Binairo8.txt", "8-solution.txt");
        procedure("Binairo9.txt", "9-solution.txt");
        procedure("Binairo10.txt","10-solution.txt");//le 10 c'est le bonus
    }

    /**
     * Cette méthode combine les autres méthodes nécessaires afin de raccourcir de main
     * @param nom le nom du fichier à lire
     * @param file2 le nom du fichier à inscrire les réponses
     * @throws IOException
     */
    public static void procedure (String nom, String file2) throws IOException {//méthode pour racourcir
        lire_et_afficher_fichier(nom);//montrer le fichier
        Binairo fichier = new Binairo(nom);//instance
        fichier.resoudre(fichier.getGrille());
        afficher_reponse(fichier.getGrille());
        imprimer(fichier.getGrille(), file2);
    }

    /**
     *cette méthode va simplement afficher le Binairo (ou peut-être sortir du programme s'il est vide)
     * @param nom_fichier le nom du Binairo à ouvrire
     * @throws IOException
     */
    public static void lire_et_afficher_fichier(String nom_fichier) throws IOException {
        System.out.println("La Binairo est:");
        BufferedReader fichier = new BufferedReader(new FileReader(nom_fichier));//ouvrir le ficher
        String ligne;//convertir le fichier en String
        ligne = fichier.readLine();//lire la première ligne du fichier
        if (ligne==null){//si le fichier est vide
            System.out.println("Ce Binairo est vide!!");
            System.exit(0);
        }
        while (ligne != null) { //tant que le fichier n'est pas arrivé au vide
            System.out.println(ligne);
            ligne = fichier.readLine();//prochaine ligne
        }
        fichier.close();//fermer le fichier
    }

    /**
     * Cette méthode affiche la réponse du Binairo sur l'écran
     * @param grille la grille nécessaire qui contient le Binairo
     */
    public static void afficher_reponse(char[][] grille) {
        System.out.println("Réponse du Binairo:");
        for (int i = 0; i < grille.length; i++) {//pour montrer la réponse une fois sortie du boucle
            for (int y = 0; y < grille.length; y++) {
                System.out.print(grille[i][y]);
            }
            System.out.println();//changement de ligne
        }
    }

    /**
     * La méthode qui va inscrire la réponse dans un fichier
     * @param grille le Binairo en question
     * @param solution le nom du fichier pour inscrire la solution
     * @throws FileNotFoundException
     */
    public static void imprimer (char [][] grille, String solution) throws FileNotFoundException {
        PrintWriter reponse=new PrintWriter(new FileOutputStream(solution));//Ouvrir en Printwriter
        for (int i=0; i<grille.length; i++){
            for (int y=0; y<grille.length; y++){
                reponse.print(grille[i][y]);//imprimer le caractère
            }reponse.println();//saut de ligne
        }reponse.close();
        System.out.println("La réponse est imprimée dans le fichier solution.");
    }
}
