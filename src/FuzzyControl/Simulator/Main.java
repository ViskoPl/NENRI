package Simulator;

import Simulator.Databases.AccelerationDatabase;
import Simulator.Databases.HelmDatabase;
import Simulator.Decoders.COADefuzzifier;
import Simulator.Decoders.IDecoder;
import Simulator.Helpers.Sets;
import Simulator.Systems.AccelerationFuzzySystem;
import Simulator.Systems.HelmFuzzySystem;
import Simulator.Systems.IFuzzySystem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Sets set = new Sets();
        set.fillSets();
        int L=0, D=0, LK=0, DK=0, V=0, S=0;
        String line = null;
        IDecoder def = new COADefuzzifier();

        IFuzzySystem accSystem = new AccelerationFuzzySystem(def, new AccelerationDatabase());
        IFuzzySystem hemlSystem = new HelmFuzzySystem(def, new HelmDatabase());
        while(true){
            if((line = input.readLine())!=null){
                if(line.charAt(0)=='K') break;
                Scanner s = new Scanner(line);
                L = s.nextInt();
                D = s.nextInt();
                LK = s.nextInt();
                DK = s.nextInt();
                V = s.nextInt();
                S = s.nextInt();
            }
            double A = accSystem.conclude(L, D, LK, DK, V, S);
            double K = hemlSystem.conclude(L, D, LK, DK, V, S);

            System.out.println(A + " " + K);
            System.out.flush();
        }

    }
}
