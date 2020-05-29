
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroos;

import java.util.Random;

/**
 *
 * @author hello
 */
public class Kangaroo implements Comparable<Kangaroo> {
    private Character gender;
    private int pouchcapacity;
    private int food = 0;//food in pouch
    private int pointID;
    private Random r = new Random();

    public Kangaroo() {
        this.gender = null;
    }

    public Kangaroo(int pointID, Character gender, int pouchcapacity) {
        this.pointID = pointID;
        this.pouchcapacity = pouchcapacity;
        this.gender = gender;

    }

    public int getpointID() {
        return pointID;
    }

    public void setpointID(int newpointID) {
        this.pointID = newpointID;
    }

    public Points getPoint() {
        return JumpGroove.Hafiz.getPoint(this.pointID);
    }

    public int getfood() {
        return food;
    }

    public void minusfood(int food) {
        this.food -= food;
    }

    public void addFood(int food) {
        if(this.food+food>pouchcapacity)
            getPoint().addfood(food-pouchcapacity);
        else
            this.food += food;
    }

    public Character getGender() {
        return gender;
    }

    @Override
    public int compareTo(Kangaroo o) {
        if (this.getGender().equals(o.getGender()))
            return 0;// true
        else
            return -1;// false

    }

    public void tick() {
        
        //collect food first in their point
        if ((this.food != this.pouchcapacity) && (getPoint().getfood() != 0)) {
            int tempfood = this.pouchcapacity - this.food;// amount of food needed to fill the pouch
            if (getPoint().getfood() - tempfood < 0) {//check kalau makanan at point sikit
                this.food += getPoint().getfood();
            } else {
                getPoint().minusfood(tempfood);
                this.food += tempfood;
            }
        }else if (this.gender == 'M') {//if makanan dah dlm poket penuh atau makanan at point habis

            int tempnumroute = getPoint().getnumroute();// number of route from the point(point that current kangaroo
                                                        // is)
            int foodneeded = 0;
            LinkedList<Route> allowed = new LinkedList<Route>();
            Route choosen = null;
            boolean wantto = false; // decision for the kangaroo to move or not
            for (int j = 0; j < tempnumroute; j++) {
                Route currentRoute = getPoint().getRoute(j);
                foodneeded = currentRoute.getWeight() + (this.food / 2);// from new spot(not include offering)
                if (currentRoute.getLink().totalKangaroo() >= Points.colony) {
                    //destination point is a colony
                    if (foodneeded <= currentRoute.getLink().getfood() && (food / 2) >= Points.colony) {
                        allowed.addNode(currentRoute);
                    }
                }else {
                    if (foodneeded <= currentRoute.getLink().getfood()) {
                        allowed.addNode(currentRoute);

                    }
                }
            }

            if (allowed.isEmpty() == false) {
                int currentFood = getfood();
                int foodindestination = 0;
                int indexfood = 0;// index which have largest amount of food and largest amount of female. I use
                                  // index to mark which one.
                // dalam ni silap lagi high amount of food is priority
                for (int i = 0; i < allowed.length(); i++) {
                    // System.out.println(allowed.atindex(i).getLink().getfemale() + "nn")
                    int tempfoodindestination = allowed.atindex(i).getLink().getfood() - foodneeded;// (food needed to
                                                                                                    // replenish energy
                                                                                                    // after arriived
                                                                                                    // new spot)
                    if (foodindestination < tempfoodindestination) {
                        foodindestination = tempfoodindestination;
                        indexfood = i;

                    } else if (foodindestination == tempfoodindestination) {
                        // if same need to check the female which one is more
                        if (allowed.atindex(i).getLink().getfemale() >= allowed.atindex(indexfood).getLink()
                                .getfemale())
                            indexfood = i;

                    }

                }
                choosen = allowed.atindex(indexfood);
                int foodleftinnewspot = choosen.getLink().getfood() - foodneeded;
                if (foodleftinnewspot >= getfood()) {

                    wantto = true;
                }
                if (wantto == true) {
                    if (getPoint().totalKangaroo() >= getPoint().getColony()) {
                        minusfood(getfood() / 2 + choosen.getLink().totalKangaroo());
                        choosen.getLink().minusfood(foodneeded);//foodneeded = height + 1/2 * food in pouch
                        choosen.getLink().offering();
                    } else {
                        minusfood(getfood() / 2 + getPoint().totalKangaroo());
                        choosen.getLink().minusfood(foodneeded);//height food from Point
                    }
                    setpointID(choosen.getLink().getpointID());
                }
            }
        }
        if(getPoint().totalKangaroo()>=getPoint().getColony()&&getPoint().iscolonized()){
            getPoint().setcolonized(true);
            Points.numberofcolony++;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Kangaroo{" + "gender=" + gender + '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // To change body of generated methods, choose Tools | Templates.
    }

}
