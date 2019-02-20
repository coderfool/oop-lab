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

class Placement {
    Student[] s;

    Placement(Student[] s) {
        this.s = s;
    }

    synchronized void place(String company) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("placed.txt", true))) {
            for (Student std : s) {
                if (company.equals("Cisco") && !std.placed && std.gpa > 9) {
                    bw.write(std.toString() + " Cisco");
                    bw.newLine();
                    std.placed = true;
                }
                else if (company.equals("TCS") && !std.placed && std.branch.equals("CSE")) {
                    bw.write(std.toString() + " TCS");
                    bw.newLine();
                    std.placed = true;
                }
                else if (company.equals("Wipro") && !std.placed && std.pcl.equals("Wipro")) {
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
}

class WriteThread implements Runnable {
    Placement p;
    String name;
    Thread t;

    WriteThread(Placement p, String name) {
        this.p = p;
        this.name = name;
        t = new Thread(this);
        t.start();
    }

    public void run() { 
        p.place(name);
    }
}

class endsem {
    public static void main(String[] args) throws IOException {
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
        
        
        Placement p = new Placement(s); 
        WriteThread cisco = new WriteThread(p, "Cisco");
        WriteThread tcs = new WriteThread(p, "TCS");
        WriteThread wipro = new WriteThread(p, "Wipro");

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
