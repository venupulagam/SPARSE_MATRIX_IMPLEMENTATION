import java.util.Arrays;

public class SPARSE {

    int[][] mat = new int[3][3];

// METHOD TO PRINT A 2-D MATRIX 

    public void print(int[][] sus){

        for (int i = 0; i<sus.length; i++){

            if (i==0){System.out.print("\nR : ");}
            if (i==1){System.out.print("\nC : ");}
            if (i==2){System.out.print("\nV : ");}

            for (int j = 0; j<sus[1].length; j++){
                System.out.print(sus[i][j] + "  ");}}}

// METHOD TO GET THE RCV OF A MATRIX :

    public int[][] RCV(int[][] mat){

        int[][] rcv;
        int ind = -1;
        int num = 0;

        for (int i = 0; i<mat.length; i++){
            for (int j = 0; j<mat[1].length; j++){
                if (mat[i][j] != 0){
                    num = num+1;}}}

                    // System.out.println(num);
                    rcv = new int[3][num];

                    for (int i = 0; i<mat.length; i++){
                        for (int j = 0; j<mat[0].length; j++){
                            if (mat[i][j] != 0){

                                ind = ind+1;
                                rcv[0][ind] = i+1;
                                rcv[1][ind] = j+1;
                                rcv[2][ind] = mat[i][j]; }}}
                            
    return rcv;}

// METHOD TO GET THE RCV OF TRANSPOSE OF A MATRIX :

    public int[][] RCVT(int[][] mat){
        SPARSE sp = new SPARSE();
        int[][] rcv = sp.RCV(mat);
        
        for (int i = 0; i<rcv[1].length; i++){
                int temp = rcv[0][i];
                rcv[0][i] = rcv[1][i];
                rcv[1][i] = temp;}

        return rcv;}

// METHOD TO CONVERT RCV TO MATRIX 

    public int[][] R2M(int[][] rcv){

        int[] r = rcv[0];
        int[] c = rcv[1];
        int[] v = rcv[2];

        int[] rc = r;
        int[] cc = c;

        int size = 0;

        int rmax = r[0];
        int cmax = c[0];

        for (int i = 1; i < r.length; i++){
            if (r[i] > rmax){
                rmax = r[i];}}
        // System.out.println(rmax);

        for (int i = 1; i < c.length; i++){
            if (c[i] > cmax){
                cmax = c[i];}}
        // System.out.println(cmax);

        if (rmax > cmax){size = rmax;}
           else {size = cmax;}

        // System.out.println(size);

        int[][] mat = new int[size][size];

        for(int i=0; i<v.length; i++){
            int ri = r[i];
            int ci = c[i];
            int vi = v[i];
            mat[ri-1][ci-1] = vi;
        }

            return mat;}

// METHOD TO MULYIPLY TWO MATRICES : (gives the original product)

    public int[][] mult(int[][] mat1, int[][] mat2){

        SPARSE sp = new SPARSE();
        int[][] mat_1 = sp.RCV(mat1);
        int[][] mat_2 = sp.RCVT(mat2);

        int[][] prod = new int[mat1.length][mat2[1].length];

        int[] r1 = mat_1[0];
        int[] r2 = mat_2[0];

        int[] c1 = mat_1[1];
        int[] c2 = mat_2[1];

        int[] v1 = mat_1[2];
        int[] v2 = mat_2[2];
        
        for (int c = 0; c<c1.length; c++){
            for (int n = 0; n<c2.length; n++){
                if (c1[c] == c2[n]){
                    if (prod[r1[c]-1][r2[n]-1] == 0){
                        prod[r1[c]-1][r2[n]-1] = v1[c]*v2[n];}
                    else {
                        prod[r1[c]-1][r2[n]-1] = prod[r1[c]-1][r2[n]-1] + v1[c]*v2[n];}}}}

        return prod;}

// METHOD TO ADD TWO MATRICES :

        public int[][] add(int[][] mat1, int[][] mat2){

        SPARSE sp = new SPARSE();
        int[][] mat_1 = sp.RCV(mat1);
        int[][] mat_2 = sp.RCV(mat2);
        int n=0;
        int kx=0;

        for (int i=0; i<mat_1[1].length; i++){
            for (int j=i; j<mat_2[1].length; j++){
            if (mat_1[0][i] == mat_2[0][j] && mat_1[1][i] == mat_2[1][j]){
                mat_1[2][i] = mat_2[2][j] + mat_1[2][i];
                mat_2[0][j] = mat_2[1][j] = mat_2[2][j] = 0;
                n = n+1;}}}

        int[][] matr = new int[3][mat_2[1].length-n];

            for (int j=0; j<mat_2[1].length; j++){
                    if(mat_2[1][j] != 0){
                        matr[0][kx] = mat_2[0][j];
                        matr[1][kx] = mat_2[1][j];
                        matr[2][kx] = mat_2[2][j];
                        kx = kx+1;}}

        int[][] sum = new int [3][mat_1[1].length + matr[1].length];
        
        for (int i=0; i<mat_1.length; i++){
            for (int j=0; j<mat_1[1].length; j++){
                sum[i][j] = mat_1[i][j];}
            for (int k=0; k<matr[1].length; k++){
                sum[i][mat_1.length + k + 1] = matr[i][k];}}

        return sum;}


    public static void main(String[] args){

        int[][] mat1 = {{0,10,12},{1,0,2},{0,0,0}};
        int[][] mat2 = {{2,5,0},{0,1,0},{8,0,0}};
        SPARSE sp = new SPARSE();
        int[][] rcv = sp.RCV(mat1);
        int[][] rcv1 = sp.RCV(mat2);
        int[][] rcvt = sp.RCVT(mat2);
        int[][] prod = sp.mult(mat1, mat2);
        int[][] sum = sp.add(mat1,mat2);
        int[][] ex = sp.R2M(rcv);

        System.out.println("RCV matrix for mat1 :");
        sp.print(rcv);
        System.out.println("\nRCV matrix for mat2 :");
        sp.print(rcv1);
        /**System.out.println("\nRCVT matrix for mat2 :");
        sp.print(rcvt);
        System.out.println("\nThe product of mat1 and mat2 :");
        sp.print(prod);
        System.out.println("\nRCV matrix for the product of mat1 and mat2 :");
        sp.print(sp.RCV(prod));
        System.out.println("\nThe sum of mat1 and mat2 :");
        sp.print(sum);**/

    
    }}
