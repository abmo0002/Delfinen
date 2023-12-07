package domain_model.comparators;
import domain_model.TrainingResult;

import java.util.Comparator;
public class ResultComparator implements Comparator<TrainingResult> {
    public int compare (TrainingResult p1, TrainingResult p2) {
        return Double.compare (p1.getResult(), p2.getResult());
    }

}
