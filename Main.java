import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //1.Find equilibrium index of an array
        int[] arr = {1, 2, 4, -1, -4, -3};
        findEquilibriumIndex(arr);
        //2.Find majority element in an array (Boyer–Moore majority vote algorithm)
        int[] arr1 = {1, 1, 1, 2, 1, 5, 3};
        majorityElementBoyerMoore(arr1);
        //3.Move all zeros present in the array to the end
        int[] arr3 = {0, 3, 5, 2, 0, 0, 9, 0};
        moveAllZerosEnd(arr3);
        //4.Replace each element of array with product of every other element without using / operator
        productOtherElements(arr);
        //5.Find Longest Bitonic Subarray in an array
        int[] arr5={1,2,3,5,3,1};
        bitonicSubArray(arr5);
        //6.Find maximum difference between two elements in the array by satisfying given constraints
        maximumDifference(arr);
        //7.Maximum subarray problem (Kadane’s algorithm)
        int maximum = maxSum(arr);
        System.out.println("maximum sum:" + maximum);
        //8. Print continuous subarray with maximum sum
        printMaxSumSubArray(arr);
        // 9.Maximum Sum Circular Subarray
        maxSumCircularArray(arr);
        //10.Find distinct combinations of given length
        ArrayList<Integer> listP = new ArrayList<>();
        ArrayList<Integer> listUp = new ArrayList<>();
        listUp.add(1);
        listUp.add(2);
        listUp.add(3);
        listUp.add(4);
        int k = 2;
        ArrayList<ArrayList<Integer>> list = subArray(listP, listUp, k);
        System.out.println("Distinct combinations of given length without repitition");
        System.out.println(list);
        //11.Find distinct combinations of given length with repititions allowed
        ArrayList<Integer> listPt = new ArrayList<>();
        ArrayList<Integer> listUpt = new ArrayList<>();
        listUpt.add(1);
        listUpt.add(2);
        listUpt.add(3);
        listUpt.add(4);
        ArrayList<ArrayList<Integer>> result = subArrayRepititionsAllowed(listPt, listUpt, k);
        System.out.println("Distinct combinations of given length with repitition");
        System.out.println(result);
        //12.Find maximum sequence of continuous 1’s formed by replacing at-most k zeroes by ones
        int[] zer1 = {0, 1, 1, 0, 1, 0, 1};
        indexKZeros(zer1, 2);
        //13. Find minimum sum subarray of given size k
        minSumSubK(arr, k);
        //14. Find subarray having given sum in given array of integers
        int[] arr4 = {-1, 5, 6, 7, -2, -3};
        boolean yes = targetSumSubArray(arr4, 4);
        System.out.println();
        if (yes) {
            System.out.println("Subarray found ");
        } else {
            System.out.println("Subarray is not found");
        }
        //15. Find the length of smallest subarray whose sum of elements is greater than the given number
        maxSumTarget(arr4, 9);
    }

    static void findEquilibriumIndex(int[] arr) {
        int[] leftSum = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        leftSum[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            leftSum[i] = leftSum[i - 1] + arr[i - 1];
        }
        int right = 0;
        int i;
        for (i = 0; i < arr.length; i++) {
            right = sum - arr[i] - leftSum[i];
            if (right == leftSum[i]) {
                System.out.println("Equilibrium index at " + i);
            }
        }
        if (i == arr.length) {
            System.out.println("No equilibrium indices");
        }
    }

    static void majorityElementBoyerMoore(int[] arr) {
        int n = arr.length;
        int count = 0;
        int majorityElement = -1;
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                majorityElement = arr[i];
                count = 1;
            } else {
                if (majorityElement == arr[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            if (majorityElement == arr[i]) {
                count1++;
            }
        }
        if (count1 >= (n / 2)) {
            System.out.println("Majority element=" + majorityElement);
        } else {
            System.out.println("No majority element");
        }
    }

    static void moveAllZerosEnd(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            }
        }
        for (int i = j; i < arr.length; i++) {
            arr[i] = 0;
        }
        System.out.println("Array with zeros at last:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void productOtherElements(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] product = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * arr[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * arr[i + 1];
        }
        System.out.println();
        System.out.println("Product of every other elements:");
        for (int i = 0; i < n; i++) {
            product[i] = left[i] * right[i];
            System.out.print(product[i] + " ");
        }

    }

    static void maximumDifference(int[] arr) {
        int max = Integer.MIN_VALUE;
        int diff;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                diff = arr[j] - arr[i];
                if (diff > max) {
                    max = diff;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        System.out.println();
        System.out.println("Maximum difference is " + max);
        System.out.println("Pair: " + arr[index1] + " " + arr[index2]);
    }

    static void printMaxSumSubArray(int[] arr) {
        int startDup = 0;
        int start = 0;
        int end = -1;
        int maximum = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum < arr[i]) {
                maximum = arr[i];
                startDup = i;
            }
            if (maximum < sum) {
                maximum = sum;
                start = startDup;
                end = i;
            }
        }
        System.out.println("subarray with maximum sum");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    static int maxSum(int[] arr) {
        int maximum = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum < arr[i]) {
                maximum = arr[i];
            }
            if (maximum < sum) {
                maximum = sum;
            }
        }
        return maximum;

    }

    static void maxSumCircularArray(int[] arr) {
        int n = arr.length;
        int arraySum = maxSum(arr);
        int[] invArr = new int[n];
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
            invArr[i] = -arr[i];
        }
        int invArraySum = maxSum(invArr);
        int circularSumWithoutMin = totalSum + invArraySum;
        int circularArraySum;
        if (circularSumWithoutMin < arraySum) {
            circularArraySum = arraySum;
        } else {
            circularArraySum = circularSumWithoutMin;
        }
        System.out.println();
        System.out.println("Maximum sum in circular array:" + circularArraySum);
    }

    static ArrayList<ArrayList<Integer>> subArray(ArrayList<Integer> listP, ArrayList<Integer> listUp, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (listUp.isEmpty() && listP.size() == k) {
            result.add(new ArrayList<>(listP));
            return result;
        }
        if (listUp.isEmpty()) {
            return result;
        }
        int val = listUp.get(0);
        ArrayList<Integer> listUpCopy = new ArrayList<>(listUp);
        listUpCopy.remove(0);
        result.addAll(subArray(new ArrayList<>(listP), listUpCopy, k));
        listP.add(val);
        result.addAll(subArray(listP, listUpCopy, k));

        return result;
    }

    static void indexKZeros(int[] arr, int k) {
        int left = 0, right = 0, zeroCount = 0, maxCount = 0, start = 0, end = 0;
        while (right < arr.length) {
            if (arr[right] == 0) {
                zeroCount++;
            }
            if (k < zeroCount) {
                if (arr[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            if (maxCount < right - left + 1) {
                maxCount = right - left + 1;
                start = left;
                end = right;
            }
            right++;
        }
        for (int i = start; i <= end; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            }
        }
        System.out.println("k-Zeros Replaced array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void minSumSubK(int[] arr, int k) {
        int sum = 0, left = 0, right = 0, minSum = 0, start = 0, end = 0;
        while (right < arr.length) {
            sum += arr[right];
            if (right - left + 1 == k) {
                if (minSum > sum) {
                    minSum = sum;
                    start = left;
                    end = right;
                }
                sum -= arr[left];
                left++;
            }
            right++;
        }
        System.out.println();
        System.out.println("minimum sum subarray of given size k: ");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static boolean targetSumSubArray(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int sum = 0;
        for (int i : nums) {
            sum += i;
            if (set.contains(sum - target)) {
                return true;
            }
            set.add(sum);
        }
        return false;
    }

    static void maxSumTarget(int[] arr, int k) {
        int n = arr.length;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        int start = -1, end = -1;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > k) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left];
                start = left;
                end = right;
                left++;
            }
        }
        System.out.println(start + " " + end);
    }

    static ArrayList<ArrayList<Integer>> subArrayRepititionsAllowed(ArrayList<Integer> listP, ArrayList<Integer> listUp, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (listUp.isEmpty() && listP.size() == k) {
            result.add(new ArrayList<>(listP));
            return result;
        }
        if (listUp.isEmpty()) {
            return result;
        }
        int val = listUp.get(0);
        ArrayList<Integer> listUpCopy = new ArrayList<>(listUp);
        listUpCopy.remove(0);
        result.addAll(subArray(new ArrayList<>(listP), listUpCopy, k));
        listP.add(val);
        result.addAll(subArray(listP, listUpCopy, k));
        ArrayList<ArrayList<Integer>> repetitionList = new ArrayList<>();
        for (int i = 0; i < listUp.size(); i++) {
            ArrayList<Integer> inner = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                inner.add(listUp.get(i));
            }
            repetitionList.add(inner);
        }
        result.addAll(repetitionList);
        return result;
    }
    static void bitonicSubArray(int[] arr){
        int n=arr.length;
        int[] inc=new int[n];
        int[] dec=new int[n];
        for(int i=0;i<n;i++){
            inc[i]=1;
            dec[i]=1;
        }
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                inc[i]=inc[i-1]+1;
            }
        }
        for(int i=n-2;i>0;i--){
            if(arr[i]>arr[i+1]){
                dec[i]=dec[i+1]+1;
            }
        }
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, inc[i] + dec[i] - 1);
        }
        System.out.println();
       System.out.println("Length of bitonic subarray:"+maxLen);
    }
}