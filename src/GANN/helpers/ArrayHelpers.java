package helpers;

public class ArrayHelpers {
    public static double[] getSliceOfArray(double[] arr,
                                        int start, int end)
    {

        // Get the slice of the Array
        double[] slice = new double[end - start];

        // Copy elements of arr to slice
        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }

        // return the slice
        return slice;
    }
}
