package kangaroos;

import java.util.Scanner;

public class JumpGroove {

    /**
     * @param args the command line arguments
     */
    static LinkedList<Kangaroo> Kangaroos = new LinkedList<Kangaroo>();// Kangaroos static so other class can use it
    static Map Hafiz = new Map();// Map is static so that other class can access it

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n; // number of points
        int a; // pointID
        int f; // number of food available at the point
        int l; // maxkang of the point
        int m; // number of route connected

        // ask the user to input the number of points
        n = 4;// sini patutnya scanner(user input)
        Hafiz = new Map(n);// set the number of points, tapi point masih belum ada
        System.out.println("enter information, kat sini kene baiki cara user input");
        System.out.println("Please enter information about points");
        for (int i = 0; i < n; i++) {
            System.out.println("Point " + ( i+1 )+ ": ");
            a = s.nextInt();
            f = s.nextInt();
            l = s.nextInt();
            m = s.nextInt();
            Hafiz.points.addNode(new Points(a, f, l, m));

        }
        System.out.println();
        for (int i = 0; i < Hafiz.numberofpoints; i++) {
            System.out.println("Point ID: " + Hafiz.points.atindex(i).getpointID());
            for (int r = 0; r < Hafiz.points.atindex(i).getnumroute(); r++) {
                int tempID;
                System.out.println("Route to Point:[pointID] ");
                tempID = s.nextInt();
                for (int t = 0; t < Hafiz.numberofpoints; t++) {
                    if (Hafiz.points.atindex(t).getpointID() == tempID) {

                        System.out.println("with height of: ");
                        int tempheight = s.nextInt();
                        Hafiz.points.atindex(i).addroute(new Route(Hafiz.points.atindex(t), tempheight));// tambah jalan

                    }

                }

            }
        }
        int tempnumkang = s.nextInt();
        System.out.println("Adding kangaroos id,gender(M/F),pouchcapacity");
        for (int i = 0; i < tempnumkang; i++) {
            System.out.println("ID: ");
            int tempID = s.nextInt();
            System.out.println("Gender: ");
            s.nextLine();
            char c = s.nextLine().charAt(0);
            Character tempgender = c;
            System.out.println("Pouch Capacity");
            int temppouch = s.nextInt();
            Kangaroos.addNode(new Kangaroo(tempID, tempgender, temppouch));

        }

        // for (int i = 0; i < Hafiz.numberofpoints; i++) {
        //     int tempID = Hafiz.points.atindex(i).getpointID();
        //     boolean available = false;
        //     System.out.println(
        //             "In point " + Hafiz.points.atindex(i).getpointID() + " Food: " + Hafiz.points.atindex(i).getfood());
        //     for (int t = 0; t < Kangaroos.length(); t++) {
        //         if (Kangaroos.atindex(i).getpointID() == tempID) {
        //             System.out.println(Kangaroos.atindex(t).getGender() + " " + Kangaroos.atindex(t).getfood());
        //             available = true;
        //         }
        //     }
        //     if (available == false)
        //         System.out.println("There is no Kangaroo (SADDDDDD)");
        //     else
        //         available = false;
        // }

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int hello = 0;
        boolean running = false;
        int start = 0;
        System.out.println("press 1 to start");
        start = s.nextInt();
        if (start == 1)
            running = true;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1 && running) {
                Hafiz.tick();

                delta--;
                System.out.println("one round");
                hello = s.nextInt();
                if (hello == 1)
                    running = false;

            }
            if (running)
                // render();
                frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: "+ frames);
                frames = 0;
            }

        }
        System.out.println("Number of colony formed: "+Points.numberofcolony);
    }

}