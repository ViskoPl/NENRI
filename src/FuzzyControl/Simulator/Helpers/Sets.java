package Simulator.Helpers;

import FuzzyOperations.zad1.Domain;
import FuzzyOperations.zad1.IDomain;
import FuzzyOperations.zad2.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Sets {
    public static HashMap<String, ArrayList<IFuzzySet>> sets = new HashMap<>();

    public void fillSets() {

        ArrayList<IFuzzySet> VSets= new ArrayList<>(5);
        IDomain domain = Domain.intRange(0, 60);
        // VS
        VSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(10, 15)));
        // MS
        VSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(10, 20, 30)));
        // OK
        VSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(15, 30, 45)));
        // MF
        VSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(30, 40, 50)));
        // VF
        VSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(45, 50)));
        sets.put("V", VSets);

        ArrayList<IFuzzySet> ASets= new ArrayList<>(7);
        domain = Domain.intRange(-25, 26);
        // FR
        ASets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(-15, -10)));
        // MR
        ASets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-15, -10, -5)));
        // LR
        ASets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-10, -5, 0)));
        // OK
        ASets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-5, 0, 5)));
        // LT
        ASets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(0, 5, 10)));
        // MT
        ASets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(5, 10, 15)));
        // FT
        ASets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(10, 15)));

        sets.put("A", ASets);

        ArrayList<IFuzzySet> DistanceSets= new ArrayList<>(7);
        domain = Domain.intRange(0, 1301);
        // VC
        DistanceSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(20, 30)));
        // MC
        DistanceSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(25, 35, 45)));
        // LC
        DistanceSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(40, 55, 70)));
        // OK
        DistanceSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(60, 90, 120)));
        // LF
        DistanceSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(110, 125, 140)));
        // MF
        DistanceSets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(135, 145, 155)));
        // VF
        DistanceSets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(150, 160)));

        sets.put("D", DistanceSets);

        ArrayList<IFuzzySet> KSets= new ArrayList<>(7);
        domain = Domain.intRange(-90, 91);
        // HR
        KSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(-70, -50)));
        // MR
        KSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-60, -45, -30)));
        // SR
        KSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-35, -20, -5)));
        // OK
        KSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-10, 0, 10)));
        // SL
        KSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(5, 20, 35)));
        // ML
        KSets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(30, 45, 60)));
        // HL
        KSets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(50, 70)));
        sets.put("K", KSets);

        ArrayList<IFuzzySet> SSets= new ArrayList<>(2);
        domain = Domain.intRange(0, 2);
        SSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(0, 1)));
        SSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(0, 1)));
        sets.put("S", SSets);
    }
}
