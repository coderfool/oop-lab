import java.io.*;

class Student {
    int id;
    String name;
    String branch;
    String pcl;
    double gpa;
    boolean placed;

    Student(int id, String name, String branch, String pcl, double gpa, boolean placed) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.pcl = pcl;
        this.gpa = gpa;
        this.placed = placed;
    }

    public String toString() {
        return id + " " + name + " " + branch + " " + gpa;
    }
}

class Placement implements Runnable {
    Student[] s;
    Thread t;

    Placement(Student[] s, String name) {
        this.s = s;
        t = new Thread(this, name);
        t.start();
    }

    void place() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("placed.txt", true))) {
            for (Student std : s) {
                if (t.getName().equals("Cisco") && !std.placed && std.gpa > 9) {
                    bw.write(std.toString() + " Cisco");
                    bw.newLine();
                    std.placed = true;
                }
                else if (t.getName().equals("TCS") && !std.placed && std.branch.equals("CSE")) {
                    bw.write(std.toString() + " TCS");
                    bw.newLine();
                    std.placed = true;
                }
                else if (t.getName().equals("Wipro") && !std.placed && std.pcl.equals("Wipro")) {
                    bw.write(std.toString() + " Wipro");
                    bw.newLine();
                    std.placed = true;
                }  
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        synchronized(s) {
            place();
        }
    }
}

class File {
    public static void main(String[] args) {
        int n = 5;
        Student[] s = new Student[n];
        s[0] = new Student(100, "Ravina", "CSE", "TCS", 9.3, false);
        s[1] = new Student(101, "Anubhav", "CSE", "Cisco", 8.9, false);
        s[2] = new Student(102, "Anwesha", "CSE", "Cisco", 8.8, false);
        s[3] = new Student(103, "Ram", "ECE", "TCS", 6.3, false);
        s[4] = new Student(104, "Gulshan", "Mechanical", "Wipro", 5.1, false);

        try {
            FileWriter f = new FileWriter("placed.txt");
            f.close();
        }
        catch(IOException e) {}

        Placement cisco = new Placement(s, "Cisco");
        Placement tcs = new Placement(s, "TCS");
        Placement wipro = new Placement(s, "Wipro");

        try {
            cisco.t.join();
            tcs.t.join();
            wipro.t.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("placed.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
