import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;


public class Allocate {

    public static void startAllocation(final int blockCount)
    {
        System.out.println("startAllocation of " + blockCount + " blocks");
        final int blockSize = 65536;

        List<Integer> allocatedMemory = new ArrayList<Integer>();
        for (int i = 0; i < blockCount; ++i)
        {
            for (j = 0; j < blockSize; ++j)
            {
                allocatedMemory.add(j);
            }
        }

        System.out.println("finished allocation, sleep for 120 s");

        try
        {
            Thread.sleep(120 * 1000);
        }
        catch (InterruptedException ex)
        {
        }
    }

    /**
        * @param args
        */
    public static void main(String[] args) {
        final int blockCount = Integer.parseInt(args[0]);
        startAllocation(blockCount);
    }

}
