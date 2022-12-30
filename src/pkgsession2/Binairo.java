package pkgsession2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Binairo {
    private String nom_ficher;
    private char[][] grille;

    /**
     * Une instance qui passe le fichier en tableau aussi
     * @param nom_fichier le nom de fichier de Binairo qui est décidé dans l'autre classe
     * @throws IOException
     */
    Binairo(String nom_fichier) throws IOException {//ce nom_fichier est le nom de ficher utilisé
        this.nom_ficher = nom_fichier;
        grille = tableau_2d(nom_fichier);//remplacer le tableau grille par celui crée dans la méthode.
    }

    /**
     * Méthode qui donne la grille pour l'afficher et etc.
     * @return retourne la grille passé en tableau
     */

    public char[][] getGrille() {
        return grille;
    }

    /**
     * Méthode qui compte le nombre de ligne afin de décider de la longueur du tableau 2d
     * @param nom_fichier nom du fichier à utiliser
     * @return retourner le nombre de ligne qui va être utilisé plus tard
     * @throws IOException
     */
    public static int compter_ligne(String nom_fichier) throws IOException {//pour compter la grandeur du tableau 2d
        int nombre_de_ligne = 0;//initialiser
        BufferedReader fichier = new BufferedReader(new FileReader(nom_fichier));//ouvrir le ficher
        String ligne;//convertir le fichier en String
        ligne = fichier.readLine();//lire la première ligne du fichier
        while (ligne != null) { //tant que le fichier n'est pas arrivé au vide
            nombre_de_ligne++;//ajouter à chaque ligne
            ligne = fichier.readLine();//prochaine ligne
        }
        fichier.close();
        return nombre_de_ligne;//le retourner
    }

    /**
     * Méthode qui passe un fichier en tableau 2d pour être traité.
     * Il utilise la méthode compter_ligne pour décider de la longueur de ce tableau
     * et si la grille n'est pas un carré il sort du programme
     * @param nom_fichier le nom du fichier en question
     * @return retourne le tableau,la grille à résoudre
     * @throws IOException
     */
    public static char[][] tableau_2d(String nom_fichier) throws IOException {/*je peux enlever le static et le rendre
     accessible à tous les classes, mais je n'ai pas vraiment de raison de le faire vu que je ne l'utilise pas
     dans l'autre classe.
    */
        int nombre_de_ligne = compter_ligne(nom_fichier);
        int i = 0;//utilisé pour le tableau 2d grille(il est mis ici pour ne pas initialiser i après le readLine.
        char[][] grille = new char[nombre_de_ligne][nombre_de_ligne];
        BufferedReader fichier = new BufferedReader(new FileReader(nom_fichier));//ouvrir le ficher
        String ligne;//convertir le fichier en String
        ligne = fichier.readLine();//lire la première ligne du fichier
        while (ligne != null) { //tant que le fichier n'est pas arrivé au vide
            String[] grille_ligne = ligne.split("");//changer la ligne en tableau (String puisque ligne est un String)
            int x = 0;//pour traquer le tableau String
            if (grille.length%2!=0){
                System.out.println("Ce Binairo n'est pas valide");
                System.exit(0);//sort du programme si le binairo n'est pas pair
            }
            for (int y = 0; y < grille.length; y++) {//pour le tableau 2d
                String convertir = grille_ligne[x];//mettre la lettre de la position x en String (variable seule)
                char changer = convertir.charAt(0);//convertir cette variable String en char pour le mettre dans le tableau grille
                grille[i][y] = changer;//le grille de la position i y est remplacé par le char.
                x++;//passer au deuxième case du tableau String
            }
            ligne = fichier.readLine();
            i++;//prochaine ligne
        }
        fichier.close();
        return grille;
    }
    

    /**
     * Méthode longue
     * Elle va résoudre le Binairo avec les différents règles qui s'imposent au Binairo tel que: le nombre de 1
     * doit être égale au nombre de 0
     * et il sort du programme si le Binairo en question n'est pas conforme aux règles (comme un Binairo
     * qui aurait des lettres ou des emojis, qui ne peut pas être résolu donc).
     * Les détails sont dans la méthode en commentaire
     * @param grille la grille à analyser et à résoudre (passé en tableau bien sûr)
     * @return retourne la même grille, mais les ? sont tous remplacés par 1 ou 0
     */
    public char[][] resoudre(char[][] grille) {
        char a, b;//utilisé après
        //Je n'avais pas d'idée pour les noms.
        boolean actuelle = true;//la situation de résolution: pour l'instant, elle est sur true (pas encore résolu)
        int nombre_de_0_1 = 0;//Int pour le nombre de place par ligne
        for (int i = 0; i < grille.length; i++) {
            nombre_de_0_1++;//compter le nombre de caractère pour la première ligne
        }
        nombre_de_0_1 = nombre_de_0_1 / 2;/*vu que le nombre de 0= nombre de 1, alors le nombre de 0 ou 1 sont égal
        au nombre de caractère /2.
        */
        int quantite_0 = 0;//quantité de 0 par ligne: utilisé pour résoudre.
        int quantite_1 = 0;
        int essai = 0; //le nombre d'essai de remplacemet sans raison (voir la boucle)
        int essai_precedent;//utilisé après
        int enregistrer []=new int[2];//s'il y a eu un erreur au remplacement
        for (int i=0; i<grille.length; i++){
            for (int y=0; y<grille.length; y++){
                if (grille[i][y]!='?'&&grille[i][y]!='1'&&grille[i][y]!='0'){
                    System.out.println("Ce Binairo a des caractères non-permis");
                    System.exit(0);//sort du programme si le binairo a des caracteres bizarre
                }
            }
        }
        while (actuelle) {//tant que le binairo n'est pas résolu, il va rester dans cette boucle.
            boolean facon=false;//si au moins une résolution a été utilisé, il devient true
            for (int i = 0; i < grille.length; i++) {//résoudre l'horizontale
                for (int y = 0; y < grille.length; y++) {//pour chaque ligne,
                    if (grille[i][y] == '0') {//si c'est 0
                        quantite_0++;
                    } else if (grille[i][y] == '1') {//si c'est 1
                        quantite_1++;
                    }
                }
                if (quantite_0>nombre_de_0_1){
                    grille[enregistrer[0]][enregistrer[1]]='1';
                }
                else if (quantite_1>nombre_de_0_1){
                    grille[enregistrer[0]][enregistrer[1]]='0';
                }
                for (int w = 0; w < grille.length; w++) {//toujours sur la même ligne,
                    if (grille[i][w] == '?') {//s'il y a ?
                        if (quantite_0 == nombre_de_0_1) {//si le nombre de 0 sur la ligne est la moitié des caractères
                            grille[i][w] = '1';//alors la réponse c'est un
                            facon=true;//vu qu'il y a eu un changement dans le binairo
                                if (w!=0&&w!=grille.length-1&&grille[i][w] == grille[i][w - 1] && grille[i][w] == grille[i][w + 1]) {
                                    /*
                                    n'importe lequel de ces 3 if signifie qu'il y a 3 chiffres identiques qui se suivent,
                                    ce qui ne marche pas, et donc le programme reset les changements apportés jusqu'au dernier
                                    remplacement sans logique (dont les coordonnées sont enregistrés dans le tableau enregistrer)
                                     */
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w>1&& grille[i][w] == grille[i][w - 1] && grille[i][w] == grille[i][w - 2]) {
                                    grille[i][w] = '?';//le w>1 est pour éviter de dépasser le tableau
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w<grille.length-2&&grille[i][w] == grille[i][w + 1] && grille[i][w] == grille[i][w + 2]) {
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                    //bien sûr, ça veut dire qu'aucune changement n'a eu lieu et facon redevient faux
                                }
                        } else if (quantite_1 == nombre_de_0_1) {//vice-versa mais c'est 0
                            grille[i][w] = '0';
                            facon=true;
                                if (w!=0&&w!=grille.length-1&&grille[i][w] == grille[i][w - 1] && grille[i][w] == grille[i][w + 1]) {
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w>1&&grille[i][w] == grille[i][w - 1] && grille[i][w] == grille[i][w - 2]) {
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w<grille.length-2&&grille[i][w] == grille[i][w + 1] && grille[i][w] == grille[i][w + 2]) {
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                }

                        }/*exemple: sur un binairo 6x6, le nombre de 0_1 est 6. Donc il devrait avoir trois 0 et trois 1.
                        si sur une ligne, il y a une ? et c'est la seule de la ligne, alors on n'a qu'à voir si 0 est à
                        trois ou si 1 est à trois.
                        */
                    }
                }
                quantite_0 = 0;
                quantite_1 = 0;//initialiser ces chiffres pour la prochaine ligne.
            }
            for (int i = 0; i < grille.length; i++) {//la même choise en verticale
                for (int y = 0; y < grille.length; y++) {//donc pour chaque colonne
                    if (grille[y][i] == '0') {
                        quantite_0++;
                    } else if (grille[y][i] == '1') {
                        quantite_1++;
                    }
                }
                for (int w = 0; w < grille.length; w++) {
                    if (grille[w][i] == '?') {
                        if (quantite_0 == nombre_de_0_1) {
                            grille[w][i] = '1';
                            facon=true;
                                if (w!=0&&w!=grille.length-1&&grille[w][i] == grille[w - 1][i] && grille[w][i] == grille[w + 1][i]) {
                                    grille[w][i] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w>1&&grille[w][i] == grille[w - 1][i] && grille[w][i] == grille[w - 2][i]) {
                                    grille[i][w] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                } else if (w<grille.length-2&&grille[w][i] == grille[w + 1][i] && grille[w][i] == grille[w + 2][i]) {
                                    grille[w][i] = '?';
                                    facon = false;
                                    grille[enregistrer[0]][enregistrer[1]] = '?';
                                }
                        } else if (quantite_1 == nombre_de_0_1) {
                            grille[w][i] = '0';
                            facon=true;
                                if (w!=0&&w!=grille.length-1&&grille[w][i]==grille[w-1][i]&&grille[w][i]==grille[w+1][i]){
                                    grille[w][i]='?';facon=false;grille[enregistrer[0]][enregistrer[1]]='?';
                                }
                                else if (w>1&&grille[w][i]==grille[w-1][i]&&grille[w][i]==grille[w-2][i]){
                                    grille[i][w]='?';facon=false;grille[enregistrer[0]][enregistrer[1]]='?';
                                }
                                else if (w<grille.length-2&&grille[w][i]==grille[w+1][i]&&grille[w][i]==grille[w+2][i]){
                                    grille[w][i]='?';facon=false;grille[enregistrer[0]][enregistrer[1]]='?';
                                }
                        }
                    }
                }
                quantite_0 = 0;
                quantite_1 = 0;
            }
            for (int i = 0; i < grille.length; i++) {//c'est une résolution pour les binairo plus gros
                for (int y = 0; y < grille.length; y++) {//lire le binairo au complet
                    if (grille[i][y] != '?') {//si le charac. n'est pas 'vide'
                        if (y != grille.length - 1) {//et qu'il n'est pas situé au bout droit du binairo (éviter les bug)
                            if (grille[i][y] == grille[i][y + 1]) {//si ce chiffre est égale au prochain chiffre d'à gauche
                                if (grille[i][y] == '0') {//si c'est 0 le chiffre répété alors il faut absolument 1
                                    a = '1';//donc j'enregistre 1
                                } else {//vice versa
                                    a = '0';
                                }
                                if (y != 0) {//tant que la position n'est pas au bout gauche
                                    if (grille[i][y - 1] == '?') {//et que la place à gauche de la position est vide,
                                        grille[i][y - 1] = a;//alors surement il est le chiffre enregistré
                                        facon=true;
                                    }
                                }
                                if (y != grille.length - 2) {//même chose, mais pour la droite (2 position arrière)
                                    if (grille[i][y + 2] == '?') {/*donc la position actuelle ne être minimum éloigné de
                                    2 grilles du bout droit
                                        */
                                        grille[i][y + 2] = a;
                                        facon=true;
                                    }
                                }
                            }
                        }
                        if (i != grille.length - 1) {//la même chose en verticale (c'est i qui change)
                            if (grille[i][y] == grille[i + 1][y]) {
                                if (grille[i][y] == '0') {
                                    b = '1';
                                } else {
                                    b = '0';
                                }
                                if (i != 0) {
                                    if (grille[i - 1][y] == '?') {
                                        grille[i - 1][y] = b;
                                        facon=true;
                                    }
                                }
                                if (i != grille.length - 2) {
                                    if (grille[i + 2][y] == '?') {
                                        grille[i + 2][y] = b;
                                        facon=true;
                                    }
                                }
                            }
                        }
                    }
                    /*
                    exemple: si j'ai 011?10
                                     101100
                                     10010?
                    rendu à ligne 0 position 2, le programme détecte que le prochain est la même chose: selon les régles,
                    il doit être suivi par 0. Donc il cherche des ? à gauche: il est à la position 2 doncpas de risque
                     de déborder, et il n'a pas de vide à gauche. Puis à droite: pas de débordement non-plus et il y a un
                     vide, donc c'est nécessairement 0.
                     rendu à la dernière ligne le programme horizontale n'aurait rien détecté
                     le programme verticale, à la dernière colonne ligne 0, il va détecter 0 qui arrive 2 fois:
                     le haut ne marche pas vu sa position (il va déborder donc il passe à la prochaine if) mais le bas
                     marche et il y a un vide.
                     Ce programme ne va rien servir au début mais rendu au Binairo 9 il serait très utile.
                     */
                }
            }
            for (int i = 0; i < grille.length; i++) {/*si c'est un gros binairo, alors certainement il va rester des vides:
            donc j'ai remis le même programme, détectant les vides cette fois pour plus de variétés.
            */
                for (int y = 0; y < grille.length; y++) {
                    if (grille[i][y] == '?') {
                        if (y != 0 && y != grille.length - 1) {
                            if (grille[i][y - 1] == grille[i][y + 1]) {
                                if (grille[i][y - 1] != '?') {
                                    if (grille[i][y - 1] == '0') {
                                        grille[i][y] = '1';
                                        facon = true;
                                    } else {
                                        grille[i][y] = '0';
                                        facon = true;
                                    }
                                }
                            }
                        }
                        if (i != 0 && i != grille.length - 1) {
                            if (grille[i - 1][y] == grille[i + 1][y]) {
                                if (grille[i - 1][y] != '?') {
                                    if (grille[i - 1][y] == '0') {
                                        grille[i][y] = '1';
                                        facon = true;
                                    } else {
                                        grille[i][y] = '0';
                                        facon = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            actuelle = false;//ici, à moins d'avis contraire, le binairo est résolu.
            for (int i = 0; i < grille.length; i++) {
                for (int y = 0; y < grille.length; y++) {
                    if (grille[i][y] == '?') {//mais s'il y a un vide, alors finalement il revient à true.
                        actuelle = true;
                    }
                }
            }
            essai_precedent=essai;/*
            Le if suivant s'agit d'intervenir si le binairo bloque: il n'y a donc plus aucune possibilité de
            résolution logique. Alors il va remplacer un vide par 0 ou 1 aléatoirement (et si le binairo ne marche
            pas, ce changement est reset comme dans la première méthode de résolution)
            */
            if (!facon){//si aucune résolution n'a eu lieu, donc soit c'esr résolu ou c'est bloqué
                    for (int i = 0; i < grille.length; i++) {
                        for (int w=0; w<grille.length; w++){
                            if (grille[i][w]=='0'){
                                quantite_0++;
                            }
                            else if (grille[i][w]=='1'){
                                quantite_1++;
                            }
                        }
                        for (int y = 0; y < grille.length; y++) {
                            if (grille[i][y] == '?') {
                                if (essai==0||essai%2==0) {//pour tous les essais pairs
                                    if (quantite_0 < nombre_de_0_1){
                                        essai++;
                                    grille[i][y] = '0';//le vide est 0 (il n'y a pas d'explication logique)
                                    enregistrer[0] = i;
                                    enregistrer[1] = y;//enregistrer la case qui a été modifié
                                        quantite_0=0;quantite_1=0;
                                    break;//et sortir du for
                                }
                                    else {
                                        essai+=2;
                                        grille[i][y]='1';
                                        enregistrer[0]=i;enregistrer[1]=y;
                                        quantite_0=0;quantite_1=0;break;
                                    }
                                }
                                else {//la même chose mais avec les essais impairs
                                    if (quantite_1<nombre_de_0_1){
                                    grille[i][y]='1';
                                    essai++;//pour traquer le nombre d'essais fait
                                    enregistrer[0]=i;
                                    enregistrer[1]=y;
                                    quantite_0=0;quantite_1=0;
                                    break;}
                                    else {
                                        essai+=2;
                                        grille[i][y]='0';
                                        enregistrer[0]=i;enregistrer[1]=y;
                                        quantite_0=0;quantite_1=0;break;
                                    }
                                }
                            }
                        }
                        if (essai_precedent!=essai){//si il y a eu une addition d'essai, donc un remplacement
                            break;
                            /*
                            alors le programme sort de ce for, puisqu'il n'a besoin de changer qu'un vide.
                            cette procédure est nécessaire puisque si je ne met pas de if, le programme va sortir
                            du for avec break; même s'il n'a rien changé (donc il aurait juste lu la première ligne
                            et il sortirait de ce for sans rien changer si la première ligne est remplie)
                             */
                            /*
                            Exemple: le programme lit la première ligne. Il y a eu un changement. Le break nous envoie
                            en dehors du for pour lire les lignes. Le système détecte un changement, et donc un deuxième
                            break nous amène en dehors de ce système de remplacement et nous ramène au début du while. S'il
                            ne fait pas ça, le système irait à la deuxième ligne et remplaçera un deuxième '?', jusqu'à ce qu'il
                            se rende à la dernière ligne.
                            Sinon: la première ligne était remplie. Il n'y a pas eu de changement d'essai, donc ce if ne
                            détecte rien et il passe à la prochaine ligne jusq'à changement.
                             */
                        }
                    }/*Mais si aucun '?' n'est détecté(deuxième situation: le Binairo est déjà résolu)
                    (ce qui sera le cas des Binairos simplesnau début), alors le
                    système aurait simplement lu la grille sans rien faire.
                */
            }
            /*et la boucle recommence, jusqu'à ce que tout est résolu. Mais la deuxième fois va avoir moins de vide, et encore
        moins de vide jusqu'à ce qu'il n'en reste plus.
        */
        }return grille;
    }
}

