package FuzzyRelations.zad4;

import FuzzyOperations.zad1.CompositeDomain;
import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad1.IDomain;
import FuzzyOperations.zad1.SimpleDomain;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad2.MutableFuzzySet;

import java.util.Arrays;

public class Relations {
    Relations() {
        super();
    }

    public static boolean isMaxMinTransitive(IFuzzySet set) {
        double[] members = ((MutableFuzzySet) set).getMemberships();
        if((Math.sqrt(set.getDomain().getCardinality()))%1 == 0) {
            int dimension = (int) Math.sqrt(set.getDomain().getCardinality());
            double[] temp = new double[dimension];
            for(int i = 0; i < dimension; i++) {
                for( int j = 0; j < dimension; j++) {
                    for ( int k = 0; k < dimension; k++) {
                        temp[k] = members[i * (dimension) + k] > members[k * (dimension) + j] ? members[k * (dimension ) + j] : members[i * (dimension) + k];
                    }

                    Arrays.sort(temp);
                    double max = temp[temp.length-1];

                    if (members[i * dimension + j] < max ) {
                        return false;
                    }

                    Arrays.fill(temp, 0.0);
                }
            }

            return true;
        }
        return false;
    }

    public static boolean isReflexive(IFuzzySet set) {
        double[] members = ((MutableFuzzySet) set).getMemberships();
        if((Math.sqrt(set.getDomain().getCardinality()))%1 == 0) {
            int dimension = (int) Math.sqrt(set.getDomain().getCardinality());
            double[] list = members;
            for(int i = 0; i < list.length; i += (dimension + 1) ) {
               if(members[i] != 1) {
                   return false;
               }
            }
            return true;
        }
        return false;
    }

    public static boolean isUtimesURelation(IFuzzySet r1) {
        CompositeDomain domain = ((CompositeDomain) r1.getDomain());
        IDomain[] list = domain.getList();
        for(int i = 1; i < list.length; i++) {
           if (((SimpleDomain) list[0]).getFirst() != ((SimpleDomain) list[i]).getFirst() || ((SimpleDomain) list[0]).getLast() != ((SimpleDomain) list[i]).getLast()) {
               return false;
           }
        }

        return true;
    }

    public static boolean isSymmetric(IFuzzySet set) {

        double[] members = ((MutableFuzzySet) set).getMemberships();

        if((Math.sqrt(set.getDomain().getCardinality()))%1 == 0) {
            int dimension = (int) Math.sqrt(set.getDomain().getCardinality());
            for(int i = 0; i < members.length; i++ ) {
                if (members[i] != members[ (i % dimension) * dimension + (int)Math.ceil(i / dimension)] && (i % (dimension + 1) != 0)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static IFuzzySet compositionOfBinaryRelations(IFuzzySet set1, IFuzzySet set2) {
        IDomain domain1 =  set1.getDomain();
        IDomain domain2 =  set2.getDomain();
        MutableFuzzySet newSet = new MutableFuzzySet(new CompositeDomain(domain1.getComponent(0), domain2.getComponent(1) ));



        int firstCardinality = domain1.getComponent(0).getCardinality();
        int secondCardinality = domain2.getComponent(1).getCardinality();
        int middleCardinality = domain2.getComponent(0).getCardinality();

        double[] set1Members = ((MutableFuzzySet) set1).getMemberships();
        double[] set2Members = ((MutableFuzzySet) set2).getMemberships();

        double[] temp = new double[firstCardinality];
        for(int j = 0; j < firstCardinality; j++) {
            for(int k = 0; k < secondCardinality; k++) {
                for (int i = 0; i < middleCardinality; i++) {
                    temp[i] = set1Members[((j * (middleCardinality - 1)) + i) % set1Members.length] > set2Members[(i * (firstCardinality -1 ) + k) % set2Members.length ] ? set2Members[(i * (firstCardinality -1 ) + k) % set2Members.length] : set1Members[((j * (middleCardinality - 1)) + i) % set1Members.length];
                } ;

                Arrays.sort(temp);

                newSet.set(DomainElement.of(j + 1, k + 1),  temp[temp.length -1] );
                Arrays.fill(temp, 0.0);
            }
        };

        return newSet;
    }


    public static boolean isFuzzyEquivalence(IFuzzySet set) {
        return Relations.isReflexive(set) && Relations.isSymmetric(set) && Relations.isMaxMinTransitive(set);
    }
}
