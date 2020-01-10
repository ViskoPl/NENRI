package Simulator.Helpers;

import Simulator.Rules.Rule;
import zad1.Domain;
import zad1.DomainElement;
import zad1.IDomain;
import zad2.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Sets {
    public static HashMap<String, ArrayList<IFuzzySet>> sets = new HashMap<>();

    public void fillSets() {

        ArrayList<IFuzzySet> VSets= new ArrayList<>(5);
        IDomain domain = Domain.intRange(0, 60);
        VSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(10, 15)));
        VSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(10, 20, 30)));
        VSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(15, 30, 45)));
        VSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(30, 40, 50)));
        VSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(45, 50)));
        sets.put("V", VSets);

        ArrayList<IFuzzySet> ASets= new ArrayList<>(7);
        domain = Domain.intRange(-25, 26);
        ASets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(-15, -10)));
        ASets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-15, -10, -5)));
        ASets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-15, -10, -5)));
        ASets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-10, -5, 0)));
        ASets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-5, 0, 5)));
        ASets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(0, 5, 10)));
        ASets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(5, 10, 15)));
        ASets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(10, 15)));

        sets.put("A", ASets);

        ArrayList<IFuzzySet> DistanceSets= new ArrayList<>(7);
        domain = Domain.intRange(0, 1301);
        DistanceSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(20, 30)));
        DistanceSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(25, 35, 45)));
        DistanceSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(40, 55, 70)));
        DistanceSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(60, 90, 120)));
        DistanceSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(110, 125, 140)));
        DistanceSets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(135, 145, 155)));
        DistanceSets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(150, 160)));

        sets.put("D", DistanceSets);

        ArrayList<IFuzzySet> KSets= new ArrayList<>(7);
        domain = Domain.intRange(-90, 91);
        KSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(-70, -50)));
        KSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-60, -45, -30)));
        KSets.add(2, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-35, -20, -5)));
        KSets.add(3, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(-10, 0, 10)));
        KSets.add(4, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(5, 20, 35)));
        KSets.add(5, new CalculatedFuzzySet(domain, StandardFuzzySets.lambdaFunction(30, 45, 60)));
        KSets.add(6, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(50, 70)));
        sets.put("K", KSets);

        ArrayList<IFuzzySet> SSets= new ArrayList<>(2);
        domain = Domain.intRange(0, 2);
        KSets.add(0, new CalculatedFuzzySet(domain, StandardFuzzySets.IFunction(0, 1)));
        KSets.add(1, new CalculatedFuzzySet(domain, StandardFuzzySets.gammaFunction(0, 1)));
        sets.put("S", SSets);
    }
}
