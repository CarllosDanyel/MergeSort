import java.util.Arrays;
import java.util.Random;

public class RadixSort {
    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000000); // Preenche o array com números aleatórios
        }

        long startTime = System.nanoTime();
        radixSort(arr);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        long seconds = duration / 1_000_000_000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;

        System.out.printf("Sorted array: %s%n", Arrays.toString(Arrays.copyOf(arr, 10))); // Mostra os primeiros 10 elementos
        System.out.printf("Execution time: %02d:%02d:%02d%n", hours, minutes, seconds);
    }
}