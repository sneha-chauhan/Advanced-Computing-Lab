import java.util.Scanner;

class diskscheduling_algo {

    static class SSTF {

        void finddistance(int process[], int head, int distance[], int n) {
            System.out.println("\n\n-------Showing all distances (from request process to head)------");
            for (int i = 0; i < n; i++) {
                distance[i] = Math.abs(head - process[i]);
                System.out.print(distance[i] + "\t");
            }
        }

        int find_min(int distance[], int visited[], int n) {
            int index = -1;
            int min = 99999;
            System.out.println("\n-----Taking the minimum distance & updating head-----");
            for (int j = 0; j < n; j++) {
                if (visited[j] != 1 && min > distance[j]) {
                    min = distance[j];
                    index = j;
                }

            }
            System.out.println(distance[index]);
            return index;
        }

        void sstf_algo(int process[], int head, int n) {

            int distance[] = new int[n];
            int visited[] = new int[n];
            int head_pos[] = new int[n + 1];

            int head_mov = 0;

            for (int i = 0; i < n; i++) {
                distance[i] = 0;
                visited[i] = 0;
            }

            for (int i = 0; i < head_pos.length; i++) {
                head_pos[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                head_pos[i] = head;
                finddistance(process, head, distance, n);
                int min_index = find_min(distance, visited, n);
                visited[min_index] = 1;
                head_mov += distance[min_index];
                System.out.println("#head movement till now: " + head_mov);
                head = process[min_index];
                System.out.println("--current head -> " + head);
            }

            head_pos[n] = head;

            System.out.println("\nTotal Head movement: " + head_mov);
            System.out.println("\n-------Sequence:-------");
            for (int i = 0; i < head_pos.length; i++) {
                System.out.print(head_pos[i] + "\t");
            }

        }
    }

    // C-SCAN

    static class CSCAN {

        Scanner sc = new Scanner(System.in);

        int find_min(int process[], int visited[], int n) {
            int index = -1;
            int min = 999999;
            System.out.println("\n-----Taking the minimum value & making it new head-----");
            for (int j = 0; j < n; j++) {
                if (visited[j] != 1 && min > process[j]) {
                    min = process[j];
                    index = j;
                }

            }
            System.out.println(process[index]);
            return index;
        }

        void cscan_algo(int process[], int head, int n) {

            int greater[] = new int[n];
            int smaller[] = new int[n];
            int head_pos[] = new int[n + 3];
            int disk_size;

            int head_mov = 0, j = 0, k = 0;

            System.out.println("Enter disk size: ");
            disk_size = sc.nextInt();
            disk_size -= 1;

            for (int i = 0; i < n; i++) {
                greater[i] = -1;
                smaller[i] = -1;
            }

            for (int i = 0; i < head_pos.length; i++) {
                head_pos[i] = 0;
            }
            smaller[k] = 0;
            for (int i = 0; i < n; i++) {
                if (process[i] > head) {
                    greater[j++] = process[i];
                } else {
                    smaller[++k] = process[i];
                }
            }

            greater[j] = disk_size;
            int lengthg = j + 1;
            int lengths = k + 1;
            int visitedg[] = new int[lengthg];
            int visiteds[] = new int[lengths];
            System.out.println("---- Processes greater than head ---- ");
            for (int i = 0; i < lengthg; i++) {
                System.out.print(greater[i] + "\t");
                visitedg[i] = 0;
            }
            System.out.println();
            System.out.println("\n---- Processes smaller than head ---- ");
            for (int i = 0; i < lengths; i++) {
                System.out.print(smaller[i] + "\t");
                visiteds[i] = 0;
            }

            int s = 0, diff = 0;
            for (int i = 0; i < lengthg; i++) {
                head_pos[s++] = head;
                int min_index = find_min(greater, visitedg, lengthg);
                visitedg[min_index] = 1;
                diff = Math.abs(head - greater[min_index]);
                head_mov += diff;
                System.out.println("| "+head+" - "+greater[min_index]+" |"+" = " + diff);
                System.out.println("#head movement till now: " + head_mov);
                head = greater[min_index];
                System.out.println("--current head -> " + head);
            }

            head_pos[s] = head;

            for (int i = 0; i < lengths; i++) {
                head_pos[s++] = head;
                // System.out.println(head_pos[s++]);
                int min_index = find_min(smaller, visiteds, lengths);
                visiteds[min_index] = 1;
                diff = Math.abs(head - smaller[min_index]);
                head_mov += diff;
                System.out.println("| "+head+" - "+smaller[min_index]+" |"+" = " + diff);
                System.out.println("#head movement till now: " + head_mov);
                head = smaller[min_index];
                System.out.println("--current head -> " + head);
            }

            head_pos[s] = head;

            System.out.println("\nTotal Head movement: " + head_mov);
            System.out.println("\n-------Sequence:-------");
            for (int i = 0; i < head_pos.length; i++) {
                System.out.print(head_pos[i] + "\t");
            }

        }
    }

    // CLOOK
    static class CLOOK {

        Scanner sc = new Scanner(System.in);

        int find_min(int process[], int visited[], int n) {
            int index = -1;
            int min = 999999;
            System.out.println("\n-----Taking the minimum distance & making it new head-----");
            for (int j = 0; j < n; j++) {
                if (visited[j] != 1 && min > process[j]) {
                    min = process[j];
                    index = j;
                }

            }
            System.out.println(process[index]);
            return index;
        }

        void clook_algo(int process[], int head, int n) {

            int greater[] = new int[n];
            int smaller[] = new int[n];
            int head_pos[] = new int[n + 1];
            // int disk_size;

            int head_mov = 0, j = 0, k = 0;

            // System.out.println("Enter disk size: ");
            // disk_size = sc.nextInt();
            // disk_size -= 1;

            for (int i = 0; i < n; i++) {
                greater[i] = -1;
                smaller[i] = -1;
            }

            for (int i = 0; i < head_pos.length; i++) {
                head_pos[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                if (process[i] > head) {
                    greater[j++] = process[i];
                } else {
                    smaller[k++] = process[i];
                }
            }

            int lengthg = j;
            int lengths = k;
            int visitedg[] = new int[lengthg];
            int visiteds[] = new int[lengths];
            System.out.println("----Processes greater than head ---- ");
            for (int i = 0; i < lengthg; i++) {
                System.out.print(greater[i] + "\t");
                visitedg[i] = 0;
            }
            System.out.println();
            System.out.print("---- Processes smaller than head ---- ");
            for (int i = 0; i < lengths; i++) {
                System.out.print(smaller[i] + "\t");
                visiteds[i] = 0;
            }

            int s = 0, diff = 0;
            for (int i = 0; i < lengthg; i++) {
                head_pos[s++] = head;
                int min_index = find_min(greater, visitedg, lengthg);
                visitedg[min_index] = 1;
                diff = Math.abs(head - greater[min_index]);
                head_mov += diff;
                System.out.println("| "+head+" - "+greater[min_index]+" |"+" = " + diff);
                System.out.println("#head movement till now: " + head_mov);
                head = greater[min_index];
                System.out.println("--current head -> " + head);
            }

            head_pos[s] = head;

            for (int i = 0; i < lengths; i++) {
                head_pos[s++] = head;
                int min_index = find_min(smaller, visiteds, lengths);
                visiteds[min_index] = 1;
                diff = Math.abs(head - smaller[min_index]);
                head_mov += diff;
                System.out.println("| "+head+" - "+smaller[min_index]+" |"+" = " + diff);
                System.out.println("#head movement till now: " + head_mov);
                head = smaller[min_index];
                System.out.println("--current head -> " + head);
            }

            head_pos[s] = head;

            System.out.println("\nTotal Head movement: " + head_mov);
            System.out.println("\n-------Sequence:-------");
            for (int i = 0; i < head_pos.length; i++) {
                System.out.print(head_pos[i] + "\t");
            }

        }
    }
}

// Driver class
public class disk_scheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int process[] = new int[n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            c=i+1;
            System.out.print("Enter the cylinder " + c +" : ");
            process[i] = sc.nextInt();
        }

        System.out.print("Enter the current head: ");
        int head = sc.nextInt();

        System.out.println("\nPress 1 for SSTF");
        System.out.println("Press 2 for C-SCAN");
        System.out.println("Press 3 for C-LOOK");
        int choice = sc.nextInt();

        if (choice == 1) {
            diskscheduling_algo.SSTF SSTFObject = new diskscheduling_algo.SSTF();
            SSTFObject.sstf_algo(process, head, n);
        }

        if (choice == 2) {
            diskscheduling_algo.CSCAN SCANObject = new diskscheduling_algo.CSCAN();
            SCANObject.cscan_algo(process, head, n);
        }

        if (choice == 3) {
            diskscheduling_algo.CLOOK LOOKObject = new diskscheduling_algo.CLOOK();
            LOOKObject.clook_algo(process, head, n);
        }

    }
}
