/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroos;


/**
 *
 * @author hello
 */
public class Points implements Comparable<Points>{
    static int numberofcolony = 0;
    private LinkedList<Route> route = new LinkedList<>();
    private int pointID;
    private int maxkang;
    private int numroute;
    private int colony = 3;
    private int food;
    private int numofmale=0;
    private int numoffemale = 0;
    private boolean colonized = false;
    
    public Points(){
    }
    
    public Points(int pointID, int food, int maxkang, int numroute ){
        this.pointID = pointID;
        this.food = food;
        this.maxkang = maxkang;
        this.numroute = numroute;
        
    }
    
    public int getpointID(){
        return pointID;
    }
    
    public int getnumroute(){
        return numroute;
    }
    
    public void addroute(Route newroute){
        this.route.addsortNode(newroute);
    }
    
    public Route getRoute(int i){
        return route.atindex(i);
    }

    public int getmale(){
        int totalmale=0;
        for(int i = 0;i<JumpGroove.Kangaroos.length();i++)
            if(JumpGroove.Kangaroos.atindex(i).getGender().equals('M'))
                totalmale++;
        numofmale = totalmale;
        return numofmale;
    }
    
    public int getfemale(){
        int totalfemale=0;
        for(int i = 0;i<JumpGroove.Kangaroos.length();i++)
            if(JumpGroove.Kangaroos.atindex(i).getGender().equals("F"))
                totalfemale++;
        numoffemale = totalfemale;
        return numoffemale;
    }
    
    public int totalKangaroo(){//in current point
        return getmale()+getfemale();
    }
    
    public int getfood(){
        return food;
    }
    
    public void minusfood(int minus){
        this.food-=minus;
    
    }
    //
    public void offering(){
        for(int i=0;i<JumpGroove.Kangaroos.length();i++){
            if(JumpGroove.Kangaroos.atindex(i).getpointID()==(getpointID()))
                
            
        }
    }
    
    public void tick() {
        
        //still need work will be replace inside Kangaroo.java
        LinkedList<Route> allowed = new LinkedList<Route>();
        Route choosen = null;
        Route currentRoute;
        boolean wantto = false; //decision for the kangaroo to move or not
        int tempfemale = 0;
        int foodneeded = 0;
        int numfemale = 0;
        int indexnumfemale = 0;
        int foodinpouch = 0; 
        Kangaroo currentKangaroo = null;
        //System.out.println(kangaroo.length() + "   "+ totalKangaroo());
        for (int p = 0; p < kangaroo.length(); p++) {
            
            currentKangaroo = kangaroo.atindex(p);
            if (currentKangaroo.getGender().equals("Male")) {
                foodinpouch = currentKangaroo.getFood();
                //start sini kene tukar semua
                for (int i = 0; i < route.length(); i++) {
                    currentRoute = route.atindex(i);
                    foodneeded = currentRoute.getWeight() + (foodinpouch / 2);//from new spot(not include offering)
                    if (totalKangaroo() >= colony) {

                        if (foodneeded <= currentRoute.getLink().getfood() && (foodinpouch / 2) >= colony) {
                            allowed.addNode(currentRoute);
                        }
                    } else {
                        if (foodneeded <= currentRoute.getLink().getfood()) {
                            allowed.addNode(currentRoute);

                        }
                    }
                }
                if (allowed.isEmpty() == false) {
                    int currentFood = getfood();
                    int foodindestination = 0;
                    int indexfood = 0;//index which have largest amount of food and largest amount of female. I use index to mark which one.
                    //dalam ni silap lagi high amount of food is priority
                    for (int i = 0; i < allowed.length(); i++) {
                        //System.out.println(allowed.atindex(i).getLink().getfemale() + "nn")
                        int tempfoodindestination = allowed.atindex(i).getLink().getfood()-foodneeded;//(food needed to replenish energy after arriived new spot)
                        if(foodindestination < tempfoodindestination){
                            foodindestination = tempfoodindestination;
                            indexfood = i;
                             
                        }
                        else if(foodindestination == tempfoodindestination){
                            //if same need to check the female which one is more
                            if(allowed.atindex(i).getLink().getfemale()>=allowed.atindex(indexfood).getLink().getfemale())
                                indexfood = i;
                            
                        }
                       
                    }
                    choosen = allowed.atindex(indexfood);
                    System.out.println("heldafasdf");
                    int foodleftinnewspot = choosen.getLink().getfood()-foodneeded;
                    System.out.println(foodleftinnewspot+"adsfsdf"+getfood());
                    if(foodleftinnewspot >= getfood()){
                        
                        wantto = true;
                    }
                    if(wantto == true){    
                        if (totalKangaroo() >= colony) {
                            currentKangaroo.minusfood(currentKangaroo.getFood() / 2 + totalKangaroo());
                            choosen.getLink().minusfood(foodneeded);
                            choosen.getLink().offering();
                        } else {
                            currentKangaroo.minusfood(currentKangaroo.getFood() / 2 + totalKangaroo());
                            choosen.getLink().minusfood(foodneeded);
                        }
                        System.out.println("heldafasdf");
                        choosen.getLink().kangaroo.addNode(currentKangaroo);
                        this.kangaroo.deleteNodeByPosition(p);
                        break;
                    }else{
                    
                    
                    
                    }
                    break;
                }
            }//else continue
        }
        if(totalKangaroo()>=colony&&colonized){
            colonized = false;
            numberofcolony++;
        }

        //route.getWeght()<=food
        //System.out.println(amale);
    }

    @Override
    public int compareTo(Points o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
