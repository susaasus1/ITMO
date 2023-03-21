public class Lab1 {
    public static void main(String[] args){
    	int k=0;
    	long[] g = new long[8];
    	for (int i=4;i<=18;i+=2){
    		g[k]=i;
    		k+=1;
    	}
        double[] x = new double[12];
        double [][] d = new double [8][12];
        for (int i = 0; i < 12; i++)
        {
            x[i] = Math.random() * 11 - 5;
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<12;j++){
                if (g[i]==8) {
                    d[i][j] = Math.cos(Math.tan(Math.atan((x[j] + 0.5) / 11.0)));
                }else if ((g[i]==6)||(g[i]==12) ||(g[i]==14) ||(g[i]==16)) {
                    d[i][j] = Math.pow(Math.E, Math.pow(Math.log(Math.abs(x[j])), Math.pow(x[j], 1.0 / 3.0) / (Math.cos(x[j]) - 3)));
                }else {
                    d[i][j] = Math.log(Math.pow(Math.E,Math.pow(Math.E,Math.pow(Math.pow(x[j],1.0/3.0),2.0/Math.tan(x[j])))));
                }

            }
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<12;j++){
                System.out.printf("%8.2f", d[i][j]);
            }
            System.out.println();
        }
    }
}