import java.io.*; // for File
import java.util.*; // for Scanner

public class personality {
    public static final String[][] DIMENSIONOPTIONS = {{"E", "I"}, {"S", "N"}, {"T", "F"}, {"J", "P"}};
    public static final int[] DIMENSIONDIVISION = {10, 20, 20, 20};
    public static final int DIMENSION = 4;
        public static void main(String[] args) throws IOException, IndexOutOfBoundsException   {

        System.out.print(" File Name Please: ");
        Scanner console = new Scanner(System.in); 
        Scanner input = new Scanner(new File(console.next()));


        System.out.print(" Ouput File Name Please: ");
        PrintStream output = new PrintStream(new File((console.next())));
        




    
        while (input.hasNextLine()) {
            output.print(input.nextLine() + ": ");
            String line = input.next();
            String[] breakDown = new String[4];
            breakDown = breakDownTheData(line);
            int[] collape = getData(breakDown);
            int[] bPercent = bPercentage(collape);
            output.println(Arrays.toString(bPercent) + " = " + findPersonality(bPercent));

            if (input.hasNextLine()) {
                input.nextLine();
            }
        }
        output.close();
        console.close();
    }

    public static int[] getData ( String[] breakDown) {
        int[] array = new int[8];
        int indx = 0; 
        for(int i = 0; i < breakDown.length; i++) {
            for (int j = 0; j < breakDown[i].length(); j++) {
                if(breakDown[i].charAt(j) == 'A') {
                    array[indx] = array[indx] + 1;
                } else if (breakDown[i].charAt(j) == 'B') {
                    array[indx + 1] = array[indx + 1] + 1;
                }
            }
            indx = indx + 2;
        }
        return array;
    }

    public static String[] breakDownTheData (String line) {
        String[] data = new String [4];
        int j = 0;
        for( int i = 0; i < data.length; i++ ) {
            data[i] = "";
        }

        for(int i = 0; i < 70; i++) {

            if ( j > 6 ) {
                j = 0;
            }

            int arrayIndex = (int)Math.round((j+1)/2 );
            data[arrayIndex] += Character.toUpperCase(line.charAt(i));
            j++;
        }
        return data; 
    }

    public static int[] bPercentage  (int[] data) {
        int[] length = new int[4];

        for (int i = 0; i < DIMENSION; i++) {
			length[i] = (int) Math.round((double) data[(i * 2) + 1] / (data[i * 2] + data[(i * 2) + 1]) * 100);
        }
        return length;
    }

    public static String findPersonality (int[] collape) {
        String personality = "";
        for(int i = 0; i < DIMENSION; i++) {
            if (collape[i] > 50) {
                personality = personality + DIMENSIONOPTIONS[i][1];
            } else if (collape[i] < 50) {
                personality += DIMENSIONOPTIONS[i][0];
            } else {
                personality += 'X';
            }
        }
        return personality;
    }

  
}

