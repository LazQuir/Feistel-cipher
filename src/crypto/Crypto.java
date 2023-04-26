/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;
import java.util.Scanner;
import java.util.Arrays;
import javax.sound.midi.SysexMessage;
/**
 *
 * @author Evodie
 */
public class Crypto {

    /**
     * @param args the command line arguments
     */
    public static String clef="", LeH="", LeN="", LePi="", LeP="", LePG0="", LePG1="", leHN="", LeC="";//ok
    public static String LeK1î="",LeK2î="", LeK11="",LeK22="", LeG0K11="", LeD0="", LeG0="", LeG1="", LeD1="";
    public static String Verif1="", Verif2="";
    public static int tl =0, At1=0, DG=0, DD=0;//ok
    public static char []K= new char[tl];//ok
    public static char []H= new char[tl];//ok
    public static char []HK= new char[tl];//ok
    public static char []HN= new char[tl];//ok
    public static char []N= new char[tl];//ok
    public static char []Pi= new char[tl];//ok
    public static char []PiM= new char[tl];
    public static char []Moins= new char[tl];
    public static char []C= new char[tl];
    public static char []CM= new char[tl];
    public static char []CC= new char[tl];
    public static char []NM= new char[tl];
    public static int tlK1î = tl/2;//ok
    public static int tlK2î = tl-tlK1î;//ok
    public static char [] K1î= new char[tlK1î];//ok
    public static char [] K2î = new char[tlK2î];//ok
    public static char [] G0= new char[tlK1î];//ok
    public static char [] D0 = new char[tlK2î];//ok
    public static char [] G1= new char[tlK1î];//ok
    public static char [] D1 = new char[tlK2î];//ok
    public static char [] G2= new char[tlK1î];//ok
    public static char [] D2 = new char[tlK2î];//ok
    public static char [] P = new char[tlK2î];//ok
    public static char [] PG0 = new char[tlK2î];//ok
    public static char [] PG1 = new char[tlK2î];//ok
    public static char [] K1= new char[tlK1î];//ok
    public static char [] K2 = new char[tlK2î];//ok
    public static char [] K11= new char[tlK1î];//ok
    public static char [] K22 = new char[tlK2î];//ok
    public static char [] K12 = new char[tlK2î];//ok
    
    public static char [] G0K11 = new char[tlK2î];//ok
    public static char [] G1K22 = new char[tlK2î];//ok
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir en une seule ligne sans espace");
        System.out.print("K : ");
        
        //Saisie de K
        clef = sc.next();
        tl=clef.length();
        K= new char[tl];
        for (int i=0;i<tl;i++){
            K[i]=clef.charAt(i);
        }
        
        //Saisie de H
        System.out.print("H : ");
        LeH = sc.next();
        H= new char[tl];
        for (int i=0;i<tl;i++){
            H[i]=LeH.charAt(i);
        }
        
        //Position de decalage
        System.out.print("Saisir l'ordre de décalage gauche de K1 : ");
        DG = sc.nextInt();
        System.out.print("Saisir l'ordre de décalage droite de K2 : ");
        DD = sc.nextInt();
        
        //Fonction de permutation
        FonctionPermutation(clef, LeH, tl);
        
        //division de K en K1î et K2î
        DivisionK1îK2î();
        //K1 et K2 sans decalage
        for(int i=0; i<tlK1î;i++){
            LeK1î +=K1î[i];
        }
        for(int i=0; i<tlK2î;i++){
            LeK2î +=K2î[i];
        }
        
        //K1 et K2 sans decalage
        ETetOUexclu(LeK1î, LeK2î, tlK1î);
        //K1 et K2 avec decalage
        Decalage();
        
        System.out.print("N : ");
        LeN = sc.next();
        N= new char[tl];
        for (int i=0;i<tl;i++){
            N[i]=LeN.charAt(i);
        }
        System.out.print("π : ");
        LePi = sc.next();
        Pi= new char[tl];
        for (int i=0;i<tl;i++){
            Pi[i]=LePi.charAt(i);
        }
        //Fonction de permutation
        FonctionPermutation(LeN, LePi, tl);
        System.out.print("P : ");
        LeP = sc.next();
        P= new char[tlK1î];
        for (int i=0;i<tlK1î;i++){
            P[i]=LeP.charAt(i);
        }
        //division de N en GO et DO
        D2= new char[tlK1î];
        G2= new char[tlK2î];
        DivisionG0D0();
        System.out.print("**************************************************\n");
        
        //Affichage
        Affichage();
    }

    public static void FonctionPermutation(String chain, String H_, int tail){
        char []TAB= new char[tail];
        char []chaine= new char[tail];
        char []H_P= new char[tail];
        String Verif="";
        for(int i=0; i<tail;i++){
            Verif +=chaine[i];
        }
        chaine= new char[tail];
        H_P= new char[tail];
        for (int i=0;i<tail;i++){
            chaine[i]=chain.charAt(i);
        }
        for (int i=0;i<tail;i++){
            H_P[i]=H_.charAt(i);
        }
        for (int i=0;i<tail;i++)
        {
            At1 = Character.getNumericValue( H_P[i]);
            for (int j=0;j<tail; j++)
            {
                if (chaine[j]==chaine[At1])
                {
                    TAB[i]=chaine[At1];
                }
            }
        }
        Verif="";
        for(int i=0; i<tail;i++){
            Verif +=chaine[i];
        }
        if (Verif.equals(clef)){
            HK= new char[tl];
            for(int i=0; i<tail;i++){
            HK[i]=TAB[i];
            }
            //System.err.println("Clef OK");
        }else if (Verif.equals(LeN)){
            HN= new char[tl];
            for(int i=0; i<tail; i++){
            HN[i]=TAB[i];
            }
            //System.err.println("N OK");
        }
        else if (Verif.equals(LePG0)){
            PG0= new char[tlK1î];
            for(int i=0; i<tail;i++){
            PG0[i]=TAB[i];
            }
            //System.err.println("PG OK");
        }
        else if (Verif.equals(LePG1)){
            PG1= new char[tlK1î];
            for(int i=0; i<tail;i++){
            PG1[i]=TAB[i];
            }
            //System.err.println("PG OK");
        }
        /*else{
            System.err.println("Verif  = " + Verif);
            System.err.println("Compar = " + chain);
        }*/
    }
    
    public static void DivisionK1îK2î(){
        tlK1î = tl/2;
        tlK2î = tl-tlK1î;
        K1î= new char[tlK1î];
        K2î= new char[tlK2î];
        for (int i = 0; i < tlK1î; i++) {

            K1î[i]=HK[i];
        }

        for (int i=tlK2î ; i < tl; i++) {

            K2î[i-tlK1î]=HK[i];

        }
    }
    
    public static void DivisionG0D0(){
        tlK1î = tl/2;
        tlK2î = tl-tlK1î;
        G0= new char[tlK1î];
        D0= new char[tlK2î];
        //G1= new char[tlK1î];
        for (int i = 0; i < tlK1î; i++) {
            G0[i]=HN[i];
        }
        for(int i=0; i<tlK1î;i++){
            LePG0 +=G0[i];
        }
        for (int i=tlK2î ; i < tl; i++) {

            D0[i-tlK1î]=HN[i];
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        LeG0 = "";
        LeD0 = "";
        for(int i=0; i<tlK1î;i++){
            LeG0 +=G0[i];
        }
        for(int i=0; i<tlK1î;i++){
            LeD0 +=D0[i];
        }
        //Fonction de permutation pour G0
        FonctionPermutation(LeG0, LeP, tlK1î);
        LePG0="";
        for(int i=0; i<tlK1î;i++){
            LePG0 +=PG0[i];
            }
        //Premier round
        G1= new char[tlK1î];
        ETetOUexclu(LePG0, LeK11, tlK1î);
        LePG1="";
            for (int i = 0; i < tlK1î; i++){
                LePG1 +=G1[i];
            }
            LeK22="";
            for (int i = 0; i < tlK2î; i++){
                LeK22 +=K22[i];
            }
        //Deuxieme round
        ETetOUexclu(LePG1, LeK22, tlK1î);
    }
    
    public static void OU(String A, String B, int tail){
        char []A_= new char[tail];
        char []B_= new char[tail];
        for (int i=0;i<tail;i++){
            A_[i]=A.charAt(i);
        }
        for (int i=0;i<tail;i++){
            B_[i]=B.charAt(i);
        }
        if (A.equals(LeG0) && B.equals(LeK11)){
            G0K11= new char[tail];
            for (int i = 0; i < tlK1î; i++) {
                G0K11[i]= (A_[i]==1 || B_[i] ==1)?'1':'0';
            }
            //System.err.println("Clef OK");
        }else if (A.equals(LeG1) && B.equals(LeK22)){
            G1K22= new char[tail];
            for (int i = 0; i < tlK1î; i++) {
                G1K22[i]= (A_[i]==1 || B_[i] ==1)?'1':'0';
            }
            //System.err.println("N OK");
        }
    }
    
    public static void ETetOUexclu(String A, String B, int tail){
        char []TAB= new char[tail];
        char []A_= new char[tail];
        char []B_= new char[tail];
        for (int i=0;i<tail;i++){
            A_[i]=A.charAt(i);
        }
        for (int i=0;i<tail;i++){
            B_[i]=B.charAt(i);
        }
        //System.err.println(A + " * " + LeK1î);
        //System.err.println(B + " * " + LeK2î);
        if (A.equals(LeK1î) && B.equals(LeK2î)){
            K1= new char[tail];
            K2 = new char[tail];
            for (int i = 0; i < tail; i++) {
                K1[i]= (A_[i]==B_[i])?'0':'1';
            }
            for (int i = 0; i < tail; i++) {
                K2[i]= (B_[i]=='1'&& A_[i]=='1')?'1':'0';
            }
            //System.err.println("Clef OK");
        }else if (A.equals(LePG0) && B.equals(LeK11)){
            D1= new char[tail];
            //G1= new char[tail];
            for (int i = 0; i < tail; i++) {
                D1[i]= (A_[i]==B_[i])?'0':'1';
            }
            OU(LeG0,LeK11,tail);
            for (int i = 0; i < tail; i++) {
               G1[i]= (D0[i]==G0K11[i])?'0':'1';
            }
        }
        else if (A.equals(LePG1) && B.equals(LeK22)){
            for (int i = 0; i < tail; i++) {
                D2[i]= (A_[i]==B_[i])?'0':'1';
                //System.err.println("D2[" + i + "] : " + D2[i]);
            }
            LeG1="";
            for (int i = 0; i < tlK1î; i++){
                LeG1 +=G1[i];
            }
            OU(LeG1,LeK22,tail);
            for (int i = 0; i < tail; i++) {
               G2[i]= (D1[i]==G1K22[i])?'0':'1';
               //System.err.println("G2[" + i + "] : " + G2[i]);
            }
        }
    }
    
    public static void Decalage(){
        K11= new char[tlK1î];
        K22= new char[tlK2î];
        char []K_1= new char[tlK2î];
        char []K_2= new char[tlK2î];
        for (int i = 0; i < tlK1î; i++) {
                K_1[i]=K1[i];
            }
        for (int i = 0; i < tlK2î; i++) {
                K_2[i]=K2[i];                
            }
        //Decalage LEFT
        for (int i = 0; i < tlK1î; i++) {
             //System.out.println("K1["+i+"] = " + K1[i]);
             //System.out.println("DG = " + DG);
            if ((DG-1)==i){
                K11[(tlK1î-1)]=K1[i];
             }else{
                if (i>DG-1){
                    K11[i-1]=K1[i];
                }else{
                    K11[i]=K1[i];
                }  
             }
        }
         LeK11="";
         for (int i = 0; i < tlK1î; i++) {
             LeK11 +=K11[i];
         }
         
         for (int j = 0; j < DD; j++){
            for (int i = 0; i < tlK1î - 1; i++) {
                K22[0]=K_2[tlK1î-1];
                K22[i+1]=K_2[i];
            }
            for (int i = 0; i < tlK1î; i++) {
                K_2[i]=K22[i];
            }
        }
         LeK22="";
         for (int i = 0; i < tlK1î; i++) {
             LeK22 +=K22[i];
         }
    }

    public static void PiNeg(String Pi_P, String C_N_G_D, int tail, String HN_HC_PG0_PD0){
        char []Pi_P_= new char [tail];
        char []HN_HC_PG0_PD0_= new char [tail];
        char []C_N_G_D_= new char [tail];
        Pi_P_= new char[tail];
        for (int i=0;i<tail;i++){
            Pi_P_[i]=Pi_P.charAt(i);
        }
        C_N_G_D_ = new char[tail];
        for (int i=0;i<tail;i++){
            C_N_G_D_[i]=C_N_G_D.charAt(i);
        }
        HN_HC_PG0_PD0_= new char[tail];
        for (int i=0;i<tail;i++){
            HN_HC_PG0_PD0_[i]=HN_HC_PG0_PD0.charAt(i);
        }
    System.out.print("Pi- = [ ");
        for (int i=0;i<tail;i++)
        {
            At1 = Character.getNumericValue(HN_HC_PG0_PD0_[i]);
            for (int j=0;j<tail; j++)
            {
                if ((Pi_P_[j])==At1)
                {
                    PiM[j]=HN_HC_PG0_PD0_[j];
                    Moins[i]=C_N_G_D_[j];
                    System.out.print("" + PiM[j]+ " ");
                }
            }
        }
        String Verif="";
        System.out.print("]\n");
        if (Verif.equals(LeN)){
            NM= new char[tl];
            for(int i=0; i<tail;i++){
            NM[i]=Moins[i];
            }
            //System.err.println("Clef OK");
        }else if (Verif.equals(LeC)){
            CM= new char[tl];
            for(int i=0; i<tail; i++){
            CM[i]=Moins[i];
            }
            //System.err.println("N OK");
        }
        /*else if (Verif.equals(LePG)){
            PG0= new char[tlK1î];
            for(int i=0; i<tail;i++){
            PGM[i]=Moins[i];
            }
            //System.err.println("PG OK");
        }*/
    }
    
    public static void Affichage(){
        //Affichage
        System.out.println("taille : " + tl);
        System.out.print("K = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(K[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("H = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(H[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("H(K) = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(HK[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("K1' = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(K1î[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("K2' = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(K2î[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("Sans Decalage\nK1 = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(K1[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("K2 = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(K2[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("Avec Decalage\nK1 = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(K11[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("K2 = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(K22[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("********************************************************\n");
        //********************************
        System.out.print("N = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(N[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("π = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(Pi[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("H(N) = [ ");
        for (int i=0;i<tl;i++)
        {
        System.out.print(HN[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("G0 = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(G0[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("D0 = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(D0[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("Premier round\n");
        System.out.print("G1 = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(G1[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("D1 = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(D1[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("Deuxieme round\n");
        System.out.print("G2 = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(G2[i]+ " ");
        }
        System.out.print("]\n");
        System.out.print("D2 = [ ");
        for (int i=0;i<tlK2î;i++)
        {
        System.out.print(D2[i]+ " ");
        }
        System.out.print("]\n");
        //********************************
        System.out.print("Mot chiffré\n");
        System.out.print("C = [ ");
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(G2[i]+ " ");
        }
        for (int i=0;i<tlK1î;i++)
        {
        System.out.print(D2[i]+ " ");
        }
        System.out.print("]\n");
        /*System.out.print("C-  = [ ");
        for (int i=0;i<tl;i++)
        {
            System.out.print(CM[i]+ " ");
        }
        System.out.print("]\n");*/    
    }
    
}


