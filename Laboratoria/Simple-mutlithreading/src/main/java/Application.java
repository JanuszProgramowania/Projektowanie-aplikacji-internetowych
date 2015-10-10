import com.half.moustache.pai.lab.file.ListReader;

/**
 * Created by piotr on 10.10.15.
 */
public class Application {
    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("--list")) {
            System.out.println("LIST MODE");
            ListReader listReader = new ListReader(args[1]);
            long startTime = System.currentTimeMillis();
            listReader.actMultiThread();
            //listReader.actSingleThread();
            long endTime = System.currentTimeMillis();
            System.out.println("TIME ELAPSED " + (endTime-startTime) + "ms");
        }
        if(args[0].equalsIgnoreCase("--compare")){
            System.out.println("COMPARE MODE");
            System.out.println("Mulithread");
            long multiThreadElapsed, singleThreadElapsed;

            // Multithreading
            ListReader listReader = new ListReader(args[1]);
            long startTime = System.currentTimeMillis();
            listReader.actMultiThread();
            long endTime = System.currentTimeMillis();
            multiThreadElapsed = endTime-startTime;
            System.out.println("TIME ELAPSED " + (multiThreadElapsed)+" ms\n");

            //Single thread
            listReader = new ListReader(args[1]);
            startTime = System.currentTimeMillis();
            listReader.actSingleThread();
            endTime = System.currentTimeMillis();
            singleThreadElapsed = endTime-startTime;
            System.out.println("TIME ELAPSED " + (singleThreadElapsed)+" ms\n");

            //Comparison
            System.out.println("Single thread: "+singleThreadElapsed+" ms");
            System.out.println("Multithreading: "+multiThreadElapsed+" ms");
            long difference = Math.abs(multiThreadElapsed-singleThreadElapsed);
            System.out.println("Difference: " +difference);
        }
        else{
            System.out.println("Invalid argument!");
            System.out.println("Program will be terminated.");
        }
    }
}
