/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalysis;

import static dataanalysis.Similarity.selfSim;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Task Object To Make analysis easier
 * @author joe yearsley
 */
public class Task {
    
    BigDecimal taskTotal;
    int taskNumber = 0;
    Map<String, BigDecimal> taskKeeper = new HashMap<String,BigDecimal>();
    
    //put latest big decimal in
    void put(String s, BigDecimal d){
        BigDecimal temp = taskKeeper.get(s);
        if(taskKeeper.get(s) == null) taskKeeper.put(s, BigDecimal.ZERO);
        taskKeeper.put(s, temp.add(d) );
    }
    
    BigDecimal getAverage(int i){
        BigDecimal value = BigDecimal.ZERO;
        synchronized (taskKeeper) {
                //go through each task 
                Iterator<Map.Entry<String, BigDecimal>> iterator = taskKeeper.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, BigDecimal> entry = iterator.next();
                    //divide each task by how many 
                    value = value.add(entry.getValue().divide(new BigDecimal(i),200, RoundingMode.HALF_UP));
                }
            }
        return value;
    }
}