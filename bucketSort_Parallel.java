//Problem - 2

package LSDA_Assignment1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements the Runnable interface and overrides the run method
 * as part of creating threads in Runnable_main.java
 *
 * @author Chandana Dasari
 *
 */

public class bucketSort_Parallel implements Runnable{

    private ArrayList<Integer> aList;
    //constructor
    public bucketSort_Parallel(ArrayList<Integer> aList) {
        this.aList = aList;
    }
 @Override
    public void run() {
        Collections.sort(aList);
    }

}

