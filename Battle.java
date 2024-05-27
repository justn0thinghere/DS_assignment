
package pokemon_kanto_adventure;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Player player;
    private String opponent;
    private boolean win;
    private Pokemon your_pokemon; //your pokemon in battle
    private Pokemon foe_pokemon; //opponent's pokemon in battle
    private double atk; //attack stat of your pokemon in battle
    private double def; //defense stat of your pokemon in battle
    private double sp; //speed stat of your pokemon in battle
    private double foe_atk; //attack stat of opponent's pokemon in battle
    private double foe_def; // defense stat of opponent's pokemon in battle
    private double foe_sp; // speed stat of opponent's pokemon in battle
    public Battle(Player p,String opp){ //constructor and method for trainer battles
        Random r = new Random(); //declare and initialize Random object to be used later
        win = false; //declare and initialize boolean win for outside the class uses
        player = p; //take player object from outside the class so that when the player object's datas are altered here the changes could be passed back to the method that calls this method 
        opponent = opp; //String opponent is declared to symbolize the name of the trainer you are fighting and could find their pokemon datas through the library.Trainers Hashmap by getting the Arraylist of their pokemons using their names as key
        ArrayList<Pokemon> opponent_pokemons = library.Trainers.get(opp); //get the ArrayList of the opposing trainer's pokemon with their name as key
        //find a pokemon in your team to battle, the default pokemon to sent out when the battle starts is the pokemon in the first slot, if first slot pokemon is fainted and could not battle, then check if second pokemon exists or not and is fainted or not,
        //if second pokemon exists and is not fainted, send out second pokemon, if second pokemon is also fainted, then check third pokemon exists and fainted or not, the same thing will be done if a not fainted pokemon is not found until pokemon 6
        //at least 1 pokemon is not fainted, if pokemon 1-5 is all fainted then pokemon 6 will be sent out
        //because in our program, when all your pokemons in the team is fainted, we have a teamfaint() method that checks whether all pokemons in the team is fainted or not, if true, then all pokemons will be sent to the Pokemon Center or Mum to heal up
        if(!p.findPoke1().isFaint()){ //pokemon1 will never be null, as player will choose a starter pokemon when a new Save is created.
            your_pokemon = p.findPoke1(); //p is player, findPoke1 will return the Pokemon object that contains the data of the pokemon in the first slot, same for findPoke2-6
        }else if(p.findPoke2()!=null&&!p.findPoke2().isFaint()){
            your_pokemon = p.findPoke2();
        }else if(p.findPoke3()!=null&&!p.findPoke3().isFaint()){
            your_pokemon = p.findPoke3();
        }else if(p.findPoke4()!=null&&!p.findPoke4().isFaint()){
            your_pokemon = p.findPoke4();
        }else if(p.findPoke5()!=null&&!p.findPoke5().isFaint()){
            your_pokemon = p.findPoke5();
        }else if(p.findPoke6()!=null){ //at least last pokemon is not fainted
            your_pokemon = p.findPoke6();
        }
        foe_pokemon = opponent_pokemons.get(0); //opponent_pokemons is an ArrayList that contains the data of the opponents' pokemon, get(0) means the first pokemon in the opponent's team
        //counter of how many pokemons in the opponent's team have fainted, when a pokemon of the opponent is defeated, this counter increases by 1, 
        //and when this counter is equal to the size of the opponent's team's size, opponent loses and player wins, which will be shown in the codes later
        int foe_fainted_pokemons = 0;
        System.out.println("You sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]"); //display the information of player sending out pokemon and the pokemon's level
        System.out.println(opponent + " sent out " + foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ]"); //display the information of opponent sending out pokemon and the pokemon's level
        System.out.println(your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ] [ " + your_pokemon.findcurrenthp() + "/" + your_pokemon.findmaxhp() + " ] "); //display the basic information of the player's pokemon in battle
        System.out.println("VS");//display a VS
        System.out.println(foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ] [ " + foe_pokemon.findcurrenthp() + "/" + foe_pokemon.findmaxhp() + " ] ");//display the basic information of the opponents pokemon in battle
        resetstat(); //reset all stats of both pokemons
        System.out.println("");
        System.out.println("Tips: "); //show tips of opponent's pokemon's weakness and resistance
        foe_pokemon.showWeakness(); //a method in Pokemon class that displays a pokemon's weakness
        foe_pokemon.showResistance(); // a method in Pokemon class that shown a pokemon's resistance
        System.out.println("");
        Scanner input = new Scanner(System.in); //declare and initialize a Scanner objact to be used later
        all: //label the loop so that it could be ended by the codes in it when necessary
        while(!player.teamfaint()||foe_fainted_pokemons!=opponent_pokemons.size()){ //check if all of the player's pokemons are fainted or opponent's pokemons are fainted
            boolean foe_faint = false; //a boolean that symbolizes the opponent's pokemon in battle is fainted or not
            boolean your_faint = false; // a boolean that symbolizes the player's pokemon in battle is fainted or not
            boolean move = false; //a boolean that checks if a player chose a pokemon's move or not in a turn
            Move your_move = null; //the Move object of the player's pokemon is declared initialized to null, if player uses a pokemon a move, the your_move will be set to the move used
            boolean alter_pokemons = false; // a boolean that checks if a player switches a pokemon or not in a turn
            boolean bag_items = false; // a boolean that checks if a player uses an item in a turn or not
            //the decision loop, when a decision that will end the player's turn is not made, the loop will not end
            decision:
            while(true){
                
                //-move -- choose a move to use, ends the decision loop if a move is chosen
                //-pokemon -- check all pokemons in the team, can be used to check the status of all pokemons in the team, use items on them, or switch them into battle,
                //            if switch is done or a pokemon is switched into battle, then end the decision loop
                //-bag -- display the items in the bag of the player, player can choose an item to use or not, if an item is used, then end the decision loop
                //check status -- check the status of the pokemons,will not end decision loop
                //-run -- apparently player cannot run from player battles, this is just for fun and salute to the original game will not end decision loop
                System.out.printf("+%s+\n","-".repeat(90)); //display the basic informations about both pokemons every loop
                System.out.println(your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ] [ " + your_pokemon.findcurrenthp() + "/" + your_pokemon.findmaxhp() + " ] ");
                System.out.println("VS");
                System.out.println(foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ] [ " + foe_pokemon.findcurrenthp() + "/" + foe_pokemon.findmaxhp() + " ] ");
                System.out.println("1. Fight"); //display all the choices that the playe can choose
                System.out.println("2. Pokemons");
                System.out.println("3. Bag");
                System.out.println("4. Check status");
                System.out.println("5. Run");
                System.out.println("Your move(Choose 1-5)");
                String choice_st = input.nextLine(); //receive input
                if(isNum(choice_st)){ //check whether the string input can be converted to integer, by this way, the program will not end even when invalid inputs are accepted
                    int choice = Integer.parseInt(choice_st);
                    switch(choice){
                        case 1:
                            your_move = chooseMove(); //call chooseMove() method that will return a Move object if a move is chosen
                            if(your_move!=null){
                                move = true; //if a move is made, then set boolean move to true
                                break decision; //and break the decision loop
                            }
                            break;
                        case 2:
                            alter_pokemons = pokemons(); //call pokemons() method that will return a true value if a pokemon is switched out or an item is used on a pokemon
                            if(alter_pokemons){ //when alter_pokemons is true, then break the decision loop
                                break decision;
                            }
                            break;
                        case 3:
                            bag_items = bag(); //call bag() method that will return a true value if an item in the bag is used
                            if(bag_items){ //when bag_items is true, break the decision loop
                                break decision;
                            }
                        case 4:
                            //display all stats of both pokemon
                            System.out.println("Your atk: " + atk);
                            System.out.println("Your def: " + def);
                            System.out.println("Your sp: " + sp);
                            System.out.println("Foe atk: " + foe_atk);
                            System.out.println("Foe def: " + foe_def);
                            System.out.println("Foe sp: " + foe_sp);
                            break;
                        case 5:
                            //display a player could not run away from trainer battle message
                            System.out.println("There is no running away from a true battle between trainers!");
                            break;
                        default:
                            //pop invalid choice mssage if the input does not fulfill the format or number as a valid choice, same thing will occur everytime an invalid input is accepted
                            System.out.println("Invalid choice! Please choose again");
                    }
                }else{
                    System.out.println("Invalid choice! Please choose again.");
                }
            }
            
            //enemy decision
            Move foe_move = null; //declare a Move object of the opponent's pokemon's move
            int foe_decs = r.nextInt(4)+1; //the move will be randomly chosen from the four moves the opponent's pokemon
            switch(foe_decs){
                case 1: //if the random integer generated is 1, then use move 1, if it is 2, then use move 2 etc
                    foe_move = new Move(foe_pokemon.findmov1(),foe_pokemon.findlvl());
                    break;
                case 2:
                    foe_move = new Move(foe_pokemon.findmov2(),foe_pokemon.findlvl());
                    break;
                case 3:
                    foe_move = new Move(foe_pokemon.findmov3(),foe_pokemon.findlvl());
                    break;
                case 4:
                    foe_move = new Move(foe_pokemon.findmov4(),foe_pokemon.findlvl());
                    break;
            }
            
            
            //if swap and use items then your pokemon cannot move
            if(alter_pokemons||bag_items){
                opponentmove(foe_move); //only the opponent can move
            }else if(move){ //if player chose a pokemon move
            //calculate move order, every move has its own move order, with some move faster than the other moves
                int your_move_order = your_move.getOrder(); //when a move is declared and initialized using a constructor, the move order is set, by using getOrder() method, the move order can be obtained
                int foe_move_order = foe_move.getOrder();
            //calculate speed, if move order is same, the pokemon with higher speed will move first
                double your_speed = your_pokemon.findspeed(); //get the speed value of both pokemons using findspeed() method in pokemon class
                double foe_speed = foe_pokemon.findspeed();
                double sp_rate = 1; //declare and initialize sp_rate and foe_sp_rate which is the rate of multiplication of both pokemons' speed to obtain the temporal speed in battle
                double foe_sp_rate = 1;
                //sp_rate and foe_sp rate will be altered by sp stat and foe_sp stat
                //the formula of calculation is like this:
                //if sp stat>0
                //sp_rate = (2+n)/2
                //if sp stat<0
                //sp_rate = 2/(-n+2)
                //with n being the value of the sp stat and same goes with foe_sp_rate using foe_sp stat
                if(sp>0){ 
                    sp_rate = (1.0*(2+sp))/(2*1.0);
                }else if(sp<0){
                    sp_rate = (1.0*2)/(2+(-1*sp));
                }
            
                if(foe_sp>0){
                    foe_sp_rate = (1.0*(2+foe_sp))/(2*1.0);
                }else if(foe_sp<0){
                    foe_sp_rate = (1.0*2)/(2+(-1*foe_sp));
                }
                //after obtaining the rates, multiply them with pokemons' own speed, obtaining the temporal speed to determine who moves first if move order is the same
                your_speed = your_speed*sp_rate;
                foe_speed = foe_speed*foe_sp_rate;
                
                //determine who moves first
                if(your_move_order>foe_move_order){ //if player's pokemon's move order is higher
                    //player pokemon move first
                    yourmove(your_move);
                    if(foe_pokemon.isFaint()){ //if opponent pokemon is fainted, then set foe_faint to true, while opponent's battling pokemon cannot perform the move
                        foe_faint = true;
                    }else{ //if opponent's pokemon is not fainted after player's pokemon's move, then it gets to move after player's pokemon
                        opponentmove(foe_move);
                    }
                }else if(foe_move_order>your_move_order){ //vice versa of last case
                    //foe move first
                    opponentmove(foe_move);
                    if(your_pokemon.isFaint()){ //if player's pokemon is fainted, set your_faint to true
                        your_faint = true;
                    }else{
                        yourmove(your_move);
                    }
                }else{ //same move order
                    if(your_speed>foe_speed){ //if player's pokemon's speed is higher, same thing as if player's pokemons's move order is higher
                        //player move first
                        yourmove(your_move);
                        if(foe_pokemon.isFaint()){
                            foe_faint = true;
                        }else{
                            opponentmove(foe_move);
                        }
                    }else if(foe_speed>your_speed){ //vice versa of last case
                        //foe move first
                        opponentmove(foe_move);
                        if(your_pokemon.isFaint()){
                            your_faint = true;
                        }else{
                            yourmove(your_move);
                        }
                    }else{ //same speed
                        int rando = r.nextInt(2); //coin flip to determine who move first 50 50
                        if(rando == 0){ //if coin flip says player's pokemon move first, same thing as player's pokemon's speed is faster
                            //player move first
                            yourmove(your_move);
                            if(foe_pokemon.isFaint()){
                                foe_faint = true;
                            }else{
                                opponentmove(foe_move);
                            }
                        }else{ //vice versa of last case
                            //foe move first
                            opponentmove(foe_move);
                            if(your_pokemon.isFaint()){
                                your_faint = true;
                            }else{
                                yourmove(your_move);
                            }
                        }
                    }
                }
        
            
                
            
            }
            
                if(foe_faint||foe_pokemon.isFaint()){ //foe's pokemon is fainted
                    your_pokemon.obtainxp(foe_pokemon.findlvl()*5); //player's pokemon in battle will earn xp based on the level of the pokemon it defeated times 5
                    foe_fainted_pokemons++; //increase the counter of opponent's fainted pokemon by 1
                    if(foe_fainted_pokemons!=opponent_pokemons.size()){ //check if the counter of opponent's whole team is fainted
                        foe_pokemon = opponent_pokemons.get(foe_fainted_pokemons); //if not, then opponent switches to next pokemon, using the counter to track the index of the next pokemon in the ArrayList
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println(opponent + " sent out " + foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ]"); //display message of opponent sending out pokemon
                        System.out.println("");
                        System.out.println("Tips: "); //display weaknesses and resistance of the opponent's pokemon
                        foe_pokemon.showWeakness();
                        foe_pokemon.showResistance();
                        System.out.println("");
                        resetfoestatus(); //reset all the stats of opponent's pokemon as opponent switched in a new pokemon
                    }else{ //if all the opponent's pokemon is fainted
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("Congratulations! You have defeated " + opponent + ", you earned $ " + library.TrainerReward.get(opponent)); //display message of winning the battle
                        player.addMoney(library.TrainerReward.get(opponent)); //player obtains money according to trainer, this value can be obtained from the library.TrainerReward Hashmap using opponent's name as key 
                        resetfoestatus(); //reset all the stats of opponent's pokemon status
                        win = true; //set win to true
                        player.wonabattle(); //wonabattle method increases the battleswon value in the Player object by 1
                        library.readAllTrainers(); // reset all trainers' pokemon
                        break all; //end the whole loop
                    }
                }
                
                
            
                if(your_faint||your_pokemon.isFaint()){ //if player's pokemon is fainted
                    if(player.teamfaint()){ //if all pokemon's in player's team is fainted, end the whole loop and display player has whited out message
                        break all;
                    }else{//if there are still pokemon not fainted
                            switchpoke: //the switchpoke loop will never end if the player does not switch to next pokemon that is not fainted
                            while(true){ 
                                player.showteam(); //show the pokemons in player's team
                                System.out.println("Choose a pokemon(1-6) to switch: "); //prompt player to choose which pokemon to switch
                                String choice_st = input.nextLine(); // receive player input
                                if(isNum(choice_st)){ //check if input is correct format
                                    int choice = Integer.parseInt(choice_st);//change input into integer 
                                    switch(choice){
                                        case 1: //pokemon1 is always not null as mentioned before
                                            if(player.findPoke1().equals(your_pokemon)){ //check if the pokemon chosen is the pokemon in battle or not
                                                System.out.println("This pokemon is already in battle and has fainted!");//if yes, display already in battle and fainted message
                                            }else if(player.findPoke1().isFaint()){ //if pokemon chosen is not in battle, but has already fainted
                                                System.out.println("This pokemon has already fainted!");//display is fainted message
                                            }else{ //pokemon1 is not in battle and not fainted
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]"); //display switching pokemon message
                                                your_pokemon.changebattlestatus(false); //change last pokemon's battle status to false
                                                your_pokemon = player.findPoke1(); //switch pokemon1 to pokemon in battle
                                                your_pokemon.changebattlestatus(true); // change pokemon in battle, which is pokemon1's battle status to true
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus(); //reset player's pokemon in battle status
                                                break switchpoke; // end switchpoke loop
                                            }
                                            break;
                                        case 2:
                                            if(player.findPoke2()!=null){ //check if pokemon2 exists
                                            if(player.findPoke2().equals(your_pokemon)){ //same thing that happens when pokemon1 is chosen happens onto pokemon2,check if pokemon2 is already in battle,is fainted or not, if not switch out
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke2().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke2();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{//if pokemon2 does not exist, then display slot is empty message
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 3: //everything that happens when pokemon2 is chosen will happen onto pokemon3-6 when they are chosen
                                            if(player.findPoke3()!=null){
                                            if(player.findPoke3().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke3().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke3();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 4:
                                            if(player.findPoke4()!=null){
                                            if(player.findPoke4().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke4().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke4();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 5:
                                            if(player.findPoke5()!=null){
                                            if(player.findPoke5().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke5().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke5();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 6:
                                            if(player.findPoke6()!=null){
                                            if(player.findPoke6().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke6().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke6();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        default: //if player enters invalid choice then display invalid choice message
                                            System.out.println("Invalid choice! Please choose again!");
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again!");
                                }
                            }
                        
                    }
                }
        }
    }
    public boolean getwin(){ //a method that returns the win value
        return win;
    }
    public Battle(Player p,Pokemon wild){ //battle constructor for wild pokemons
        //almost everthing in this constructor is the same with the constructor in trainer battles, we just do not have check if there is any pokemon's left when the wild pokemon is fainted and can run away 
        Random r = new Random(); //declare a random objact to be used later
        player = p; //same explanation as another constructor for this line
        //for these if else if statements, the explanation is as that in the another constructor
        if(!p.findPoke1().isFaint()){ //pokemon1 will never be null, as player will choose a starter pokemon when a new Save is created.
            your_pokemon = p.findPoke1(); //p is player, findPoke1 will return the Pokemon object that contains the data of the pokemon in the first slot, same for findPoke2-6
        }else if(p.findPoke2()!=null&&!p.findPoke2().isFaint()){
            your_pokemon = p.findPoke2();
        }else if(p.findPoke3()!=null&&!p.findPoke3().isFaint()){
            your_pokemon = p.findPoke3();
        }else if(p.findPoke4()!=null&&!p.findPoke4().isFaint()){
            your_pokemon = p.findPoke4();
        }else if(p.findPoke5()!=null&&!p.findPoke5().isFaint()){
            your_pokemon = p.findPoke5();
        }else if(p.findPoke6()!=null){ //at least last pokemon is not fainted
            your_pokemon = p.findPoke6();
        }
        System.out.println("You sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]"); //display player sent ou pokemon message
        foe_pokemon = wild;
        //display basic information of both pokemons
        System.out.println(your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ] [ " + your_pokemon.findcurrenthp() + "/" + your_pokemon.findmaxhp() + " ] ");
        System.out.println("VS");
        System.out.println(foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ] [ " + foe_pokemon.findcurrenthp() + "/" + foe_pokemon.findmaxhp() + " ] ");
        resetstat(); //reset all the stats
        System.out.println("");
        System.out.println("Tips: "); //display weakness and resistance of wild pokemon
        foe_pokemon.showWeakness();
        foe_pokemon.showResistance();
        System.out.println("");
        Scanner input = new Scanner(System.in); //declare Scanner object to be used later
        all:
        while(!player.teamfaint()||foe_pokemon.isFaint()){ //check if the all the player's pokemon is fainted
            //all these variable and object functions as the same as the another constructor but for wild pokemons instead of opponent's pokemon in battle
            boolean foe_faint = false;
            boolean your_faint = false;
            boolean move = false;
            Move your_move = null;
            boolean alter_pokemons = false;
            boolean bag_items = false;
            //the decision loop, when a decision that will end the player's turn is not made, the loop will not end
            decision:
            while(true){
                //-move -- choose a move to use, ends the decision loop if a move is chosen
                //-pokemon -- check all pokemons in the team, can be used to check the status of all pokemons in the team, use items on them, or switch them into battle,
                //            if switch is done or a pokemon is switched into battle, then end the decision loop
                //-bag -- display the items in the bag of the player, player can choose an item to use or not, if an item is used, then end the decision loop
                //check status -- check the status of the pokemons,will not end decision loop
                //-run -- run away from wild pokemon and end the all loop
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println(your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ] [ " + your_pokemon.findcurrenthp() + "/" + your_pokemon.findmaxhp() + " ] ");
                System.out.println("VS");
                System.out.println(foe_pokemon.findname() + "[ level " + foe_pokemon.findlvl() + " ] [ " + foe_pokemon.findcurrenthp() + "/" + foe_pokemon.findmaxhp() + " ] ");
                System.out.println("1. Fight");
                System.out.println("2. Pokemons");
                System.out.println("3. Bag");
                System.out.println("4. Check status");
                System.out.println("5. Run");
                System.out.println("Your move(Choose 1-5)");
                String choice_st = input.nextLine(); //receive user input
                if(isNum(choice_st)){ //check input format
                    int choice = Integer.parseInt(choice_st);//turn input into integer
                    switch(choice){
                        case 1:
                            your_move = chooseMove(); //same thing as another constructor
                            if(your_move!=null){
                                move = true;
                                break decision;
                            }
                            break;
                        case 2:
                            alter_pokemons = pokemons(); //same thing as another constructor
                            if(alter_pokemons){
                                break decision;
                            }
                            break;
                        case 3:
                            bag_items = bag(); //same thing as another constructor
                            if(bag_items){
                                break decision;
                            }
                        case 4: //display stats
                            System.out.println("Your atk: " + atk);
                            System.out.println("Your def: " + def);
                            System.out.println("Your sp: " + sp);
                            System.out.println("Foe atk: " + foe_atk);
                            System.out.println("Foe def: " + foe_def);
                            System.out.println("Foe sp: " + foe_sp);
                            break;
                        case 5: //display run away message and break all loop
                            System.out.println("You ran away safely!");
                            break all;
                        default: //display invalid choice message if invalid choice in entered
                            System.out.println("Invalid choice! Please choose again");
                    }
                }else{
                    System.out.println("Invalid choice! Please choose again.");
                }
            }
            
            //enemy decision
            Move foe_move = null; //wild pokemon's move is decided the same way as opponent's pokemon's move in the another constructor
            int foe_decs = r.nextInt(4)+1;
            switch(foe_decs){
                case 1:
                    foe_move = new Move(foe_pokemon.findmov1(),foe_pokemon.findlvl());
                    break;
                case 2:
                    foe_move = new Move(foe_pokemon.findmov2(),foe_pokemon.findlvl());
                    break;
                case 3:
                    foe_move = new Move(foe_pokemon.findmov3(),foe_pokemon.findlvl());
                    break;
                case 4:
                    foe_move = new Move(foe_pokemon.findmov4(),foe_pokemon.findlvl());
                    break;
            }
            //if wild pokemon is caught by any type of Poke Ball, as wild pokemons can be catched unlike trainer's pokemons
            //break the all loop and end battle
            if(!foe_pokemon.wild()){ 
                break all;
            }
            
            //if swap and use items then your pokemon cannot move, same thing as another constructor, only change is opponent's pokemon to wild pokemon
            if(alter_pokemons||bag_items){
                opponentmove(foe_move);
            }else if(move){
            //calculate move order, same thing as another constructor, only change is opponent's pokemon to wild pokemon
                int your_move_order = your_move.getOrder();
                int foe_move_order = foe_move.getOrder();
            //calculate speed, same thing as another constructor, only change is opponent's pokemon to wild pokemon
                double your_speed = your_pokemon.findspeed();
                double foe_speed = foe_pokemon.findspeed();
                double sp_rate = 1;
                double foe_sp_rate = 1;
                if(sp>0){
                    sp_rate = (1.0*(2+sp))/(2*1.0);
                }else if(sp<0){
                    sp_rate = (1.0*2)/(2+(-1*sp));
                }
            
                if(foe_sp>0){
                    foe_sp_rate = (1.0*(2+foe_sp))/(2*1.0);
                }else if(foe_sp<0){
                    foe_sp_rate = (1.0*2)/(2+(-1*foe_sp));
                }
                your_speed = your_speed*sp_rate;
                foe_speed = foe_speed*foe_sp_rate;
                
                //determine who moves first, same thing as another constructor, only change is opponent's pokemon to wild pokemon
                if(your_move_order>foe_move_order){
                    //player move first
                    yourmove(your_move);
                    if(foe_pokemon.isFaint()){
                        foe_faint = true;
                    }else{
                        opponentmove(foe_move);
                    }
                }else if(foe_move_order>your_move_order){
                    //foe move first
                    opponentmove(foe_move);
                    if(your_pokemon.isFaint()){
                        your_faint = true;
                    }else{
                        yourmove(your_move);
                    }
                }else{ //same move order
                    if(your_speed>foe_speed){
                        //player move first
                        yourmove(your_move);
                        if(foe_pokemon.isFaint()){
                            foe_faint = true;
                        }else{
                            opponentmove(foe_move);
                        }
                    }else if(foe_speed>your_speed){
                        //foe move first
                        opponentmove(foe_move);
                        if(your_pokemon.isFaint()){
                            your_faint = true;
                        }else{
                            yourmove(your_move);
                        }
                    }else{ //same speed
                        int rando = r.nextInt(2); //coin flip to determine who move first 50 50
                        if(rando == 0){
                            //player move first
                            yourmove(your_move);
                            if(foe_pokemon.isFaint()){
                                foe_faint = true;
                            }else{
                                opponentmove(foe_move);
                            }
                        }else{
                            //foe move first
                            opponentmove(foe_move);
                            if(your_pokemon.isFaint()){
                                your_faint = true;
                            }else{
                                yourmove(your_move);
                            }
                        }
                    }
                }
        
            
                
            
            }
                //if the wild is fainted
                if(foe_faint||foe_pokemon.isFaint()){
                    your_pokemon.obtainxp(foe_pokemon.findlvl()*5); //then player's pokemon obtains xp according to the wild pokemon's level
                    break all; //break the all loop
                }
            
                if(your_faint||your_pokemon.isFaint()){ //if player's pokemon is fainted
                    if(player.teamfaint()){ //check if all pokemon in player's team is fainted, if yes display player white out message and end the all loop and battle
                        break all;
                    }else{ //if the player still have pokemon not fainted
                        System.out.println("Do you want to switch pokemon(y) or run away(any other input)?"); //check if player wants to choose another pokemon to fight, or just run away as the wild pokemon might be too strong
                        String choice_sr = input.nextLine(); //choice to switch or run
                        if(choice_sr.equals("y")){ //if choice is yes
                            switchpoke: //then start the switchpoke loop
                            while(true){
                                player.showteam();
                                System.out.println("Choose a pokemon(1-6)/7 to run: "); //same thing as another constructor if palyer enters 1-6, the only difference is if player enters 7 they can cancel the switch and just run away
                                String choice_st = input.nextLine();
                                if(isNum(choice_st)){
                                    int choice = Integer.parseInt(choice_st);
                                    switch(choice){
                                        case 1:
                                            if(player.findPoke1().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke1().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke1();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            break;
                                        case 2:
                                            if(player.findPoke2()!=null){
                                            if(player.findPoke2().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke2().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke2();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 3:
                                            if(player.findPoke3()!=null){
                                            if(player.findPoke3().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke3().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke3();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 4:
                                            if(player.findPoke4()!=null){
                                            if(player.findPoke4().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke4().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke4();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 5:
                                            if(player.findPoke5()!=null){
                                            if(player.findPoke5().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke5().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke5();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 6:
                                            if(player.findPoke6()!=null){
                                            if(player.findPoke6().equals(your_pokemon)){
                                                System.out.println("This pokemon is already in battle and has fainted!");
                                            }else if(player.findPoke6().isFaint()){
                                                System.out.println("This pokemon has already fainted!");
                                            }else{
                                                System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                your_pokemon.changebattlestatus(false);
                                                your_pokemon = player.findPoke6();
                                                your_pokemon.changebattlestatus(true);
                                                System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                                                resetyourstatus();
                                                break switchpoke;
                                            }
                                            }else{
                                                System.out.println("This pokemon slot is empty");
                                            }
                                            break;
                                        case 7: //if player enter 7 to run away, display run away safely message
                                            System.out.println("You ran away safely!");
                                            break all;
                                        default:
                                            System.out.println("Invalid choice! Please choose again!");
                                    }
                                }else{ 
                                    System.out.println("Invalid choice! Please choose again!");
                                }
                            }
                        }else{ //if player does not enter y to run away, display run away safely message
                            System.out.println("You ran away safely!");
                            break all;
                        }
                    }
                }
        }
    }
    public boolean isNum(String s){ //a method to check if the input can be turned into integer or not
        try{
            int ss = Integer.parseInt(s);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    public void resetstat(){ // a method to reset all stats
        atk = 0;
        def = 0;
        sp = 0;
        foe_atk = 0;
        foe_def = 0;
        foe_sp = 0;
    }
    public void resetyourstatus(){ //a method to reset only the player's pokemon's stats
        atk = 0;
        def = 0;
        sp = 0;
    }
    public void resetfoestatus(){ //a method to reset only the opponent's or wild pokemon's stats
        foe_atk = 0;
        foe_def = 0;
        foe_sp = 0;
    }
    public Move chooseMove(){ //a method that returns a Move object if the player choses one
        Scanner input = new Scanner(System.in); //declare a Scanner object to be used later
        while(true){ //while the player does not make a decision, this loop will not end
            //display all the moves of the pokemon, their category, their type and power if have
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("1. " + your_pokemon.findmov1() + " [ " + library.move_cat.get(your_pokemon.findmov1()) + " move ]");
            Move move1 = new Move(your_pokemon.findmov1(),your_pokemon.findlvl()); //declare and initialize a Move object using Move constuctor, same for the other three moves, so that every one of them could be returned if chosen
            if(move1.getPower()!=0){
                System.out.println("type: " + move1.getType());
                System.out.println("power: " + move1.getPower());
            }
            System.out.println("2. " + your_pokemon.findmov2() + " [ " + library.move_cat.get(your_pokemon.findmov2()) + " move ]");
            Move move2 = new Move(your_pokemon.findmov2(),your_pokemon.findlvl());
            if(move2.getPower()!=0){
                System.out.println("type: " + move2.getType());
                System.out.println("power: " + move2.getPower());
            }
            System.out.println("3. " + your_pokemon.findmov3() + " [ " + library.move_cat.get(your_pokemon.findmov3()) + " move ]");
            Move move3 = new Move(your_pokemon.findmov3(),your_pokemon.findlvl());
            if(move3.getPower()!=0){
                System.out.println("type: " + move3.getType());
                System.out.println("power: " + move3.getPower());
            }
            System.out.println("4. " + your_pokemon.findmov4() + " [ " + library.move_cat.get(your_pokemon.findmov4()) + " move ]");
            Move move4 = new Move(your_pokemon.findmov4(),your_pokemon.findlvl());
            if(move4.getPower()!=0){
                System.out.println("type: " + move4.getType());
                System.out.println("power: " + move4.getPower());
            }
            System.out.println("5. Back"); //give option to not choose any move and go back
            System.out.println("Choose a move to use(1-4) or 5 to go back to previous selection page"); //prompt user to enter choice
            String choice_st = input.nextLine(); //receive user choice input
            if(isNum(choice_st)){ //check choice input format
                int choice = Integer.parseInt(choice_st); //turn choice input into integer
                switch(choice){
                    //return Move object according to choice
                    case 1:
                        return move1;
                    case 2:
                        return move2;
                    case 3:
                        return move3;
                    case 4:
                        return move4;
                    case 5:
                        return null;
                }
            }else{
                System.out.println("Invalid choice! Please choose again!");
            }
        }
    }
    public boolean pokemons(){ //a method that returns true if player swithes pokemon or uses an item on a pokemon
        Scanner input = new Scanner(System.in); //declare Scanner object
        all:
        while(true){
            boolean decision_made = false;
            player.showteam();
            System.out.println("Choose a pokemon(1-6)/7 to exit: ");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
            int choice = Integer.parseInt(choice_st);
            switch(choice){
                case 1:
                    decision_made = pokechoice(player.findPoke1(),1);
                    if(decision_made){
                        return decision_made;
                    }
                    break;
                case 2:
                    if(player.findPoke2()==null){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        decision_made = pokechoice(player.findPoke2(),2);
                        if(decision_made){
                            return decision_made;
                        }
                    }
                    break;
                case 3:
                    if(player.findPoke3()==null){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        decision_made = pokechoice(player.findPoke3(),3);
                        if(decision_made){
                            return decision_made;
                        }
                    }
                    break;
                case 4:
                    if(player.findPoke4()==null){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        decision_made = pokechoice(player.findPoke4(),4);
                        if(decision_made){
                            return decision_made;
                        }
                    }
                    break;
                case 5:
                    if(player.findPoke5()==null){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        decision_made = pokechoice(player.findPoke5(),5);
                        if(decision_made){
                            return decision_made;
                        }
                    }
                    break;
                case 6:
                    if(player.findPoke6()==null){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        decision_made = pokechoice(player.findPoke6(),6);
                        if(decision_made){
                            return decision_made;
                        }
                    }
                    break;
                case 7:
                    return false;
                default:
                    System.out.println("Invalid choice! Please choose again|");
            }
            }else{
                System.out.println("Invalid choice! Please choose again!");
            }
        }
    }
    public boolean pokechoice(Pokemon poke,int pos){
        all:
        while(true){
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println(poke.findname());
            System.out.println("1. Show details");
            System.out.println("2. Use items");
            System.out.println("3. Swap");
            System.out.println("4. Back");
            System.out.println("Select one from 1-4: ");
            Scanner input = new Scanner(System.in);
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
            int choice = Integer.parseInt(choice_st);
            switch(choice){
                case 1:
                    poke.showPokemonInfo();
                    moves:
                    while(true){
                        System.out.printf("+%s+\n","-".repeat(90));
                        System.out.println("1. " + poke.findmov1());
                        System.out.println("2. " + poke.findmov2());
                        System.out.println("3. " + poke.findmov3());
                        System.out.println("4. " + poke.findmov4());
                        System.out.println("Check moves(1-4)?5 to exit: ");
                        String choicemove_st = input.nextLine();
                        if(isNum(choicemove_st)){
                        int choicemove = Integer.parseInt(choicemove_st);
                        switch(choicemove){
                            case 1:
                                System.out.printf("+%s+\n","-".repeat(90));
                                Move x1 = new Move(poke.findmov1(),poke.findlvl());
                                x1.showmovdetail();
                                break;
                            case 2:
                                System.out.printf("+%s+\n","-".repeat(90));
                                Move x2 = new Move(poke.findmov2(),poke.findlvl());
                                x2.showmovdetail();
                                break;
                            case 3:
                                System.out.printf("+%s+\n","-".repeat(90));
                                Move x3 = new Move(poke.findmov3(),poke.findlvl());
                                x3.showmovdetail();
                                break;
                            case 4:
                                System.out.printf("+%s+\n","-".repeat(90));
                                Move x4 = new Move(poke.findmov4(),poke.findlvl());
                                x4.showmovdetail();
                                break;
                            case 5:
                                break moves;
                            default:
                                System.out.println("Invalid choice! Please choose again.");
                        }
                        }else{
                            System.out.println("Invalid choice! Please choose again.");
                        }
                    }
                    break;
                case 2:
                    itemmm:
                    while(true){
                    System.out.printf("+%s+\n","-".repeat(90));
                    player.showitems();
                    System.out.println("12. Exit");
                    System.out.println("Select items(1-11)/12 to exit: ");
                    String choiceitem_st = input.nextLine();
                    if(isNum(choiceitem_st)){
                    int choiceitem = Integer.parseInt(choiceitem_st);
                    switch(choiceitem){
                        case 1: //this is supposed to be checking team while not in battle, hence all pokeballs used will output "No effect"
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 2:
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 3:
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 4:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    System.out.println("You used a Potion on " + poke.findname());
                                    poke.heal(library.pokemon_items.get("Potion").get("heal"));
                                    player.deditems("Potion",1);
                                    return true;
                                }
                                
                            }else{
                                System.out.println("You do not have any Potions left");
                            }
                            break;
                        case 5:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("Super Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    System.out.println("You used a Super Potion on " + poke.findname());
                                    poke.heal(library.pokemon_items.get("Super Potion").get("heal"));
                                    player.deditems("Super Potion",1);
                                    return true;
                                }
                                
                            }else{
                                System.out.println("You do not have any Super Potions left");
                            }
                            
                            break;
                        case 6:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("Hyper Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    System.out.println("You used a Hyper Potion on " + poke.findname());
                                    poke.heal(library.pokemon_items.get("Hyper Potion").get("heal"));
                                    player.deditems("Hyper Potion",1);
                                    return true;
                                }
                                
                            }else{
                                System.out.println("You do not have any Hyper Potions left");
                            }
                            break;
                        case 7:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("Max Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    System.out.println("You used a Max Potion on " + poke.findname());
                                    poke.fullheal();
                                    player.deditems("Max Potion",1);
                                    return true;
                                }
                                
                            }else{
                                System.out.println("You do not have any Max Potions left");
                            }
                            break;
                        case 8:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("X Attack")!=0){
                                if(poke.inBattle()){
                                    if(atk==6){
                                        System.out.println("Your battling pokemon's attack cannot be raised any higher!");
                                        return false;
                                    }
                                    System.out.println("You used a X Attack!");
                                    System.out.println(poke.findname() + "'s attack rose by 1 stage");
                                    atk++;
                                    player.deditems("X Attack", 1);
                                    return true;
                                }else{
                                    System.out.println("This item has no effect on this pokemon");
                                }
                            }else{
                                System.out.println("You do not have any X Attacks left");
                            }
                            break;
                        case 9:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("X Defend")!=0){
                                if(poke.inBattle()){
                                    if(def==6){
                                        System.out.println("Your battling pokemon's defense cannot be raised any higher!");
                                        return false;
                                    }
                                    System.out.println("You used a X Defend!");
                                    System.out.println(poke.findname() + "'s defense rose by 1 stage");
                                    atk++;
                                    player.deditems("X Defend", 1);
                                    return true;
                                }else{
                                    System.out.println("This item has no effect on this pokemon");
                                }
                            }else{
                                System.out.println("You do not have any X Defends left");
                            }
                            break;
                        case 10:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("X Speed")!=0){
                                if(poke.inBattle()){
                                    if(sp==6){
                                        System.out.println("Your battling pokemon's speed cannot be raised any higher!");
                                        return false;
                                    }
                                    System.out.println("You used a X Speed!");
                                    System.out.println(poke.findname() + "'s speed rose by 1 stage");
                                    atk++;
                                    player.deditems("X Defend", 1);
                                    return true;
                                }else{
                                    System.out.println("This item has no effect on this pokemon");
                                }
                            }else{
                                System.out.println("You do not have any X Defends left");
                            }
                            break;
                        case 11:
                            System.out.printf("+%s+\n","-".repeat(90));
                            if(player.getItems().get("Revive")!=0){
                                if(poke.isFaint()){
                                    System.out.println("You used a Revive on " + poke.findname());
                                    poke.revive();
                                    player.deditems("Revive",1);
                                    return true;
                                }else{
                                    System.out.println("This item has no effect on this pokemon");
                                }
                            }else{
                                System.out.println("You do not have any Revive left");
                            }
                            break;
                        case 12:
                            break itemmm;
                        default:
                            System.out.println("Invalid choice! Please choose again!");
                    }
                    }else{
                        System.out.println("Invalid choice! Please choose again!");
                    }
                    }
                    break;
                case 3:
                    System.out.printf("+%s+\n","-".repeat(90));
                    if(pos==1){
                        if(your_pokemon.equals(player.findPoke1())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke1().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke1();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 2){
                        if(your_pokemon.equals(player.findPoke2())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke2().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke2();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 3){
                        if(your_pokemon.equals(player.findPoke3())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke3().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke3();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 4){
                        if(your_pokemon.equals(player.findPoke4())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke4().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke4();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 5){
                        if(your_pokemon.equals(player.findPoke5())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke5().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke5();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 6){
                       if(your_pokemon.equals(player.findPoke6())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke6().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke6();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname() + "[ level " + your_pokemon.findlvl() + " ]");
                            resetyourstatus();
                            return true;
                        }
                    }
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice! Please choose again!");
            }
            }else{
                System.out.println("Invalid choice! Please choose again!");
            }
        }
    }
    public boolean bag(){
        Scanner input = new Scanner(System.in);
        all:
        while(true){
            boolean decision_made = false;
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("+--------------------Bag--------------------+");
            player.showitems();
            System.out.println("+----------------End of Bag-----------------+");
            System.out.println("Choose an item(1-11)/12 to exit");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
                int choice = Integer.parseInt(choice_st);
                switch(choice){
                    case 1:
                        decision_made = choiceitem("Poke Ball");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 2:
                        decision_made = choiceitem("Great Ball");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 3:
                        decision_made = choiceitem("Ultra Ball");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 4:
                        decision_made = choiceitem("Potion");
                        if(decision_made){
                            return decision_made;
                        }
                        break;    
                    case 5:
                        decision_made = choiceitem("Super Potion");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 6:
                        decision_made = choiceitem("Hyper Potion");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 7:
                        decision_made = choiceitem("Max Potion");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 8:
                        decision_made = choiceitem("X Attack");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 9:
                        decision_made = choiceitem("X Defend");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 10:
                        decision_made = choiceitem("X Speed");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 11:
                        decision_made = choiceitem("Revive");
                        if(decision_made){
                            return decision_made;
                        }
                        break;
                    case 12:
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again");
                }
            }else{
                System.out.println("Invalid choice! Please choose again");
            }
        }
    }
    
    public boolean choiceitem(String item){
        System.out.printf("+%s+\n","-".repeat(90));
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        double foe_hp_percentage = 1.0*foe_pokemon.findcurrenthp()/foe_pokemon.findmaxhp();
        switch(item){
            case "Poke Ball":
                System.out.println("An item that can catch a wild pokemon, can be only used in battle, against wild pokemons");
                System.out.println("You have: " + player.getItems().get("Poke Ball"));
                if(player.getItems().get("Poke Ball")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(foe_pokemon.wild()){
                            System.out.println("You used a Poke Ball!");
                            int roll = r.nextInt(101);
                            int success = 0;
                            if(foe_hp_percentage<=0.25){
                                success = library.pokemon_items.get("Poke Ball").get("pokemon_25%");
                            }else if(foe_hp_percentage<=0.5){
                                success = library.pokemon_items.get("Poke Ball").get("pokemon_50%");
                            }else if(foe_hp_percentage<=0.75){
                                success = library.pokemon_items.get("Poke Ball").get("pokemon_75%");
                            }else{
                                success = library.pokemon_items.get("Poke Ball").get("base_catch_rate");
                            }
                            if(success>roll){
                                foe_pokemon.caught();
                                System.out.println("You caught " + foe_pokemon.findname() + " !");
                                player.addPokemon(foe_pokemon);

                            }else{
                                System.out.println("Oh no! " + foe_pokemon.findname() + " snapped out of the Poke Ball");
                            }
                            player.deditems("Poke Ball",1);
                            return true;
                        }else{
                            System.out.println("You cannot catch another trainer's pokemon!");
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Great Ball":
                System.out.println("An item that can catch a wild pokemon at a high rate, can be only used in battle, against wild pokemons");
                System.out.println("You have: " + player.getItems().get("Great Ball"));
                if(player.getItems().get("Great Ball")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(foe_pokemon.wild()){
                            System.out.println("You used a Great Ball!");
                            int roll = r.nextInt(101);
                            int success = 0;
                            if(foe_hp_percentage<=0.25){
                                success = library.pokemon_items.get("Great Ball").get("pokemon_25%");
                            }else if(foe_hp_percentage<=0.5){
                                success = library.pokemon_items.get("Great Ball").get("pokemon_50%");
                            }else if(foe_hp_percentage<=0.75){
                                success = library.pokemon_items.get("Great Ball").get("pokemon_75%");
                            }else{
                                success = library.pokemon_items.get("Great Ball").get("base_catch_rate");
                            }
                            if(success>roll){
                                foe_pokemon.caught();
                                System.out.println("You caught " + foe_pokemon.findname() + " !");
                                player.addPokemon(foe_pokemon);
                            }else{
                                System.out.println("Oh no! " + foe_pokemon.findname() + " snapped out of the Great Ball");
                            }
                            player.deditems("Great Ball",1);
                            return true;
                        }else{
                            System.out.println("You cannot catch another trainer's pokemon!");
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Ultra Ball":
                System.out.println("An item that can catch a wild pokemon at a very high rate, can be only used in battle, against wild pokemons");
                System.out.println("You have: " + player.getItems().get("Ultra Ball"));
                if(player.getItems().get("Ultra Ball")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(foe_pokemon.wild()){
                            System.out.println("You used a Ultra Ball!");
                            int roll = r.nextInt(101);
                            int success = 0;
                            if(foe_hp_percentage<=0.25){
                                success = library.pokemon_items.get("Ultra Ball").get("pokemon_25%");
                            }else if(foe_hp_percentage<=0.5){
                                success = library.pokemon_items.get("Ultra Ball").get("pokemon_50%");
                            }else if(foe_hp_percentage<=0.75){
                                success = library.pokemon_items.get("Ultra Ball").get("pokemon_75%");
                            }else{
                                success = library.pokemon_items.get("Ultra Ball").get("base_catch_rate");
                            }
                            if(success>roll){
                                foe_pokemon.caught();
                                System.out.println("You caught " + foe_pokemon.findname() + " !");
                                player.addPokemon(foe_pokemon);
                            }else{
                                System.out.println("Oh no! " + foe_pokemon.findname() + " snapped out of the Ultra Ball");
                            }
                            player.deditems("Ultra Ball",1);
                            return true;
                        }else{
                            System.out.println("You cannot catch another trainer's pokemon!");
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Potion":
                System.out.println("An item that can heal a pokemon for " + library.pokemon_items.get("Potion").get("heal") + " hp");
                System.out.println("You have: " + player.getItems().get("Potion"));
                if(player.getItems().get("Potion")!=0){
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You have: " + player.getItems().get("Potion") + " Potions");
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        return healchoice("Potion");
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Super Potion":
                System.out.println("An item that can heal a pokemon for " + library.pokemon_items.get("Super Potion").get("heal") + " hp");
                System.out.println("You have: " + player.getItems().get("Super Potion"));
                if(player.getItems().get("Super Potion")!=0){
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You have: " + player.getItems().get("Super Potion") + "Super Potions");
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        return healchoice("Super Potion");
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Hyper Potion":
                System.out.println("An item that can heal a pokemon for " + library.pokemon_items.get("Hyper Potion").get("heal") + " hp");
                System.out.println("You have: " + player.getItems().get("Hyper Potion"));
                if(player.getItems().get("Hyper Potion")!=0){
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You have: " + player.getItems().get("Hyper Potion") + "Hyper Potions");
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        return healchoice("Hyper Potion");
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Max Potion":
                System.out.println("An item that can heal a pokemon for " + library.pokemon_items.get("Max Potion").get("heal") + " hp");
                System.out.println("You have: " + player.getItems().get("Max Potion"));
                if(player.getItems().get("Max Potion")!=0){
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You have: " + player.getItems().get("Max Potion") + "Max Potions");
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        return healchoice("Max Potion");
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "X Attack":
                System.out.println("An item that can increase a pokemon's attack by 1 stage in battle, can be only used in battle");
                System.out.println("You have: " + player.getItems().get("X Attack"));
                if(player.getItems().get("X Attack")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(atk==6){
                            System.out.println("Your battling pokemon's attack cannot be raised any higher!");
                            return false;
                        }
                        System.out.println("You used a X Attack!");
                        System.out.println("Your " + your_pokemon.findname() + "'s attack rose by 1 stage");
                        atk++;
                        player.deditems("X Attack", 1);
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "X Defend":
                System.out.println("An item that can increase a pokemon's defense by 1 stage in battle, can be only used in battle");
                System.out.println("You have: " + player.getItems().get("X Defend"));
                if(player.getItems().get("X Defend")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(def==6){
                            System.out.println("Your battling pokemon's defense cannot be raised any higher!");
                            return false;
                        }
                        System.out.println("You used a X Defend!");
                        System.out.println("Your " + your_pokemon.findname() + "'s defense rose by 1 stage");
                        def++;
                        player.deditems("X Defend", 1);
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "X Speed":
                System.out.println("An item that can increase a pokemon's speed by 1 stage in battle, can be only used in battle");
                System.out.println("You have: " + player.getItems().get("X Speed"));;
                if(player.getItems().get("X Speed")!=0){
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        if(sp==6){
                            System.out.println("Your battling pokemon's speed cannot be raised any higher!");
                            return false;
                        }
                        System.out.println("You used a X Speed!");
                        System.out.println("Your " + your_pokemon.findname() + "'s speed rose by 1 stage");
                        sp++;
                        player.deditems("X Speed", 1);
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            case "Revive":
                System.out.println("An item that can revive a fainted pokemon with half of its hp");
                System.out.println("You have: " + player.getItems().get("Revive"));
                if(player.getItems().get("Revive")!=0){
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You have: " + player.getItems().get("Revive") + " Revives");
                    System.out.println("Do you want to use it(y to use/any other input to not use)?");
                    String use = input.nextLine();
                    if(use.equals("y")){
                        return healchoice("Revive");
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }   
        }
        return false;
    }
    
    public boolean healchoice(String item){ //only for heal item
        Scanner input = new Scanner(System.in);
        if(item.equals("Potion")||item.equals("Super Potion")||item.equals("Hyper Potion")){
        while(true){
            player.showteam();
            System.out.println("Choose a pokemon to use this item(1-6)/7 to cancel use");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
                int choice = Integer.parseInt(choice_st);
                switch(choice){
                    case 1:
                        //because is in battle hence must have at least 1 pokemon, hence no need to check whether poke1 is null
                        if(player.findPoke1().findcurrenthp()==player.findPoke1().findmaxhp()){
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on a pokemon with full hp");
                        }else{
                            if(player.findPoke1().isFaint()){
                                System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                            }else{
                                System.out.println("You used a " + item + " on " + player.findPoke1().findname());
                                player.findPoke1().heal(library.pokemon_items.get(item).get("heal"));
                                player.deditems(item, 1);
                                return true;
                            }
                        }
                        break;
                    case 2:
                        if(player.findPoke2()!=null){
                            if(player.findPoke2().findcurrenthp()==player.findPoke2().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke2().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke2().findname());
                                    player.findPoke2().heal(library.pokemon_items.get(item).get("heal"));
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 3:
                        if(player.findPoke3()!=null){
                            if(player.findPoke3().findcurrenthp()==player.findPoke3().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke3().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke3().findname());
                                    player.findPoke3().heal(library.pokemon_items.get(item).get("heal"));
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 4:
                        if(player.findPoke4()!=null){
                            if(player.findPoke4().findcurrenthp()==player.findPoke4().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke4().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke4().findname());
                                    player.findPoke4().heal(library.pokemon_items.get(item).get("heal"));
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 5:
                        if(player.findPoke5()!=null){
                            if(player.findPoke5().findcurrenthp()==player.findPoke5().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke5().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke5().findname());
                                    player.findPoke5().heal(library.pokemon_items.get(item).get("heal"));
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 6:
                        if(player.findPoke6()!=null){
                            if(player.findPoke6().findcurrenthp()==player.findPoke6().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke6().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke6().findname());
                                    player.findPoke6().heal(library.pokemon_items.get(item).get("heal"));
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 7:
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else{
                System.out.println("Invalid choice! Please choose again.");
            }
        }
        }else if(item.equals("Max Potion")){
        while(true){
            player.showteam();
            System.out.println("Choose a pokemon to use this item(1-6)/7 to cancel use");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
                int choice = Integer.parseInt(choice_st);
                switch(choice){
                    case 1:
                        //because is in battle hence must have at least 1 pokemon, hence no need to check whether poke1 is null
                        if(player.findPoke1().findcurrenthp()==player.findPoke1().findmaxhp()){
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on a pokemon with full hp");
                        }else{
                            if(player.findPoke1().isFaint()){
                                System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                            }else{
                                System.out.println("You used a " + item + " on " + player.findPoke1().findname());
                                player.findPoke1().fullheal();
                                player.deditems(item, 1);
                                return true;
                            }
                        }
                        break;
                    case 2:
                        if(player.findPoke2()!=null){
                            if(player.findPoke2().findcurrenthp()==player.findPoke2().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke2().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke2().findname());
                                    player.findPoke2().fullheal();
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 3:
                        if(player.findPoke3()!=null){
                            if(player.findPoke3().findcurrenthp()==player.findPoke3().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke3().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke3().findname());
                                    player.findPoke3().fullheal();
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 4:
                        if(player.findPoke4()!=null){
                            if(player.findPoke4().findcurrenthp()==player.findPoke4().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke4().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke4().findname());
                                    player.findPoke4().fullheal();
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 5:
                        if(player.findPoke5()!=null){
                            if(player.findPoke5().findcurrenthp()==player.findPoke5().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke5().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke5().findname());
                                    player.findPoke5().fullheal();
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 6:
                        if(player.findPoke6()!=null){
                            if(player.findPoke6().findcurrenthp()==player.findPoke6().findmaxhp()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon with full hp");
                            }else{
                                if(player.findPoke6().isFaint()){
                                    System.out.println("This item has no effect on a fainted pokemon, please revive it first");
                                }else{
                                    System.out.println("You used a " + item + " on " + player.findPoke6().findname());
                                    player.findPoke6().fullheal();
                                    player.deditems(item, 1);
                                    return true;
                                }
                            }
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 7:
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else{
                System.out.println("Invalid choice! Please choose again.");
            }
        }
        }else if(item.equals("Revive")){
        while(true){
            player.showteam();
            System.out.println("Choose a pokemon to use this item(1-6)/7 to cancel use");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
                int choice = Integer.parseInt(choice_st);
                switch(choice){
                    case 1:
                        if(!player.findPoke1().isFaint()){
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This item has no effect on a pokemon that is not fainted");
                        }else{
                            System.out.println("You used a Revive on " + player.findPoke1().findname());
                            player.findPoke1().revive();
                            player.deditems(item, 1);
                            return true;
                        }            
                        break;
                    case 2:
                        if(player.findPoke2()!=null){
                            if(!player.findPoke2().isFaint()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon that is not fainted");
                            }else{
                                System.out.println("You used a Revive on " + player.findPoke2().findname());
                                player.findPoke2().revive();
                                player.deditems(item, 1);
                                return true;
                            }    
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 3:
                        if(player.findPoke3()!=null){
                            if(!player.findPoke3().isFaint()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon that is not fainted");
                            }else{
                                System.out.println("You used a Revive on " + player.findPoke3().findname());
                                player.findPoke3().revive();
                                player.deditems(item, 1);
                                return true;
                            }    
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 4:
                        if(player.findPoke4()!=null){
                            if(!player.findPoke4().isFaint()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon that is not fainted");
                            }else{
                                System.out.println("You used a Revive on " + player.findPoke4().findname());
                                player.findPoke4().revive();
                                player.deditems(item, 1);
                                return true;
                            }    
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 5:
                        if(player.findPoke5()!=null){
                            if(!player.findPoke5().isFaint()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon that is not fainted");
                            }else{
                                System.out.println("You used a Revive on " + player.findPoke5().findname());
                                player.findPoke5().revive();
                                player.deditems(item, 1);
                                return true;
                            }    
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 6:
                        if(player.findPoke6()!=null){
                            if(!player.findPoke6().isFaint()){
                                System.out.printf("+%s+\n","-".repeat(90));
                                System.out.println("This item has no effect on a pokemon that is not fainted");
                            }else{
                                System.out.println("You used a Revive on " + player.findPoke6().findname());
                                player.findPoke6().revive();
                                player.deditems(item, 1);
                                return true;
                            }    
                        }else{
                            System.out.printf("+%s+\n","-".repeat(90));
                            System.out.println("This pokemon slot is empty");
                        }
                        break;
                    case 7:
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else{
                System.out.println("Invalid choice! Please choose again.");
            }
        }
        }
        return false;
    }
    
    public void opponentmove(Move foe_move){
        System.out.printf("+%s+\n","-".repeat(90));
        System.out.println("Opponent " + foe_pokemon.findname() + " used " + foe_move.getName() + " !");
        double def_ratio = 1;
        double foe_atk_ratio = 1;
        if(def<0){
            def_ratio = (1.0*2)/(2+(-1*def));
        }else if(def>0){
            def_ratio = (1.0*2+def)/(2*1.0);
        }
        if(foe_atk<0){
            foe_atk_ratio = (1.0*2)/(2+(-1*foe_atk));
        }else if(foe_atk>0){
            foe_atk_ratio = (1.0*2+foe_atk)/(2*1.0);
        }   
        if(foe_move.getCategory().equals("dmg")){
            int base_power = foe_move.getPower();
            String foe_move_type = foe_move.getType();
            double same_type = 1;
            if(foe_pokemon.findtype1().equals(foe_move.getType())||foe_pokemon.findtype2().equals(foe_move.getType())){
                same_type = 1.5;
            }
            double effectiveness = your_pokemon.findEffectiveness(foe_move_type);
            int damage = (int)(base_power*same_type*effectiveness*foe_atk_ratio/def_ratio);
            
            if(effectiveness>1.0){
                System.out.println("It's super effective!");
            }else if(effectiveness<1.0&&effectiveness>0){
                System.out.println("It's not very effective...");
            }else if(effectiveness==0.0){
                System.out.println("This move does not have any effect on your " + your_pokemon.findname());
            }
            your_pokemon.takedmg(damage);
        }else if(foe_move.getCategory().equals("stat")){
            if(foe_move.getFAtk()<0){ //print output to show change in stats
                if(atk>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack fell by " + -1*foe_move.getFAtk() + " stages");
                    atk+=foe_move.getFAtk(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be lowered anymore!");
                }
            }else if(foe_move.getFAtk()>0){
                if(atk<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack rose by " + foe_move.getFAtk() + " stages");
                    atk+=foe_move.getFAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be raised anymore!");
                }
            }
            if(foe_move.getFDef()<0){
                if(def>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense fell by " + -1*foe_move.getFDef() + " stages");
                    def+=foe_move.getFDef(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be lowered anymore!");
                }
            }else if(foe_move.getFDef()>0){
                if(def<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense rose by " + foe_move.getFDef() + " stages");
                    def+=foe_move.getFDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be raised anymore!");
                }
            }
            if(foe_move.getFSp()<0){
                if(sp>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed fell by " + -1*foe_move.getFSp() + " stages");
                    sp+=foe_move.getFSp(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be lowered anymore!");
                }
            }else if(foe_move.getFSp()>0){
                if(sp<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed rose by " + foe_move.getFSp() + " stages");
                    sp+=foe_move.getFSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be raised anymore!");
                }
            }
                    
            if(foe_move.getAtk()<0){ //print output to show change in stats
                if(foe_atk>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack fell by " + -1*foe_move.getAtk() + " stages");
                    foe_atk+=foe_move.getAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be lowered anymore!");
                }
            }else if(foe_move.getAtk()>0){
                if(foe_atk<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack rose by " + foe_move.getAtk() + " stages");
                    foe_atk+=foe_move.getAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be raised anymore!");
                }
            }
            if(foe_move.getDef()<0){
                if(foe_def>-6){ 
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense fell by " + -1*foe_move.getDef() + " stages");
                    foe_def+=foe_move.getDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be lowered anymore!");
                }
            }else if(foe_move.getDef()>0){
                if(foe_def<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense rose by " + foe_move.getDef() + " stages");
                    foe_def+=foe_move.getDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be raised anymore!");
                }
            }
            if(foe_move.getSp()<0){
                if(foe_sp>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed fell by " + -1*foe_move.getSp() + " stages");
                    foe_sp+=foe_move.getSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be lowered anymore!");
                }
            }else if(foe_move.getSp()>0){
                if(foe_sp<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed rose by " + foe_move.getSp() + " stages");
                    foe_sp+=foe_move.getSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be raised anymore!");
                }
            }
        }else if(foe_move.getCategory().equals("heal")){
            int healing = (int)(foe_pokemon.findmaxhp()*foe_move.gethpheal());
            foe_pokemon.heal(healing);
        }else if(foe_move.getCategory().equals("sp")){
            int damage = 0;
            int yourcurrenthp = your_pokemon.findcurrenthp();
            if(foe_move.getPower()!=0){
                int base_power = foe_move.getPower();
                String foe_move_type = foe_move.getType();
                double effectiveness = your_pokemon.findEffectiveness(foe_move_type);
                double same_type = 1;
                if(foe_pokemon.findtype1().equals(foe_move.getType())||foe_pokemon.findtype2().equals(foe_move.getType())){
                    same_type = 1.5;
                }
                damage = (int)(base_power*same_type*effectiveness*foe_atk_ratio/def_ratio);
                if(effectiveness>1.0){
                    System.out.println("It's super effective!");
                }else if(effectiveness<1.0&&effectiveness>0){
                    System.out.println("It's not very effective...");
                }else if(effectiveness==0.0){
                    System.out.println("This move does not have any effect on " + your_pokemon.findname());
                }
                your_pokemon.takedmg(damage);
            }
            
                    
            if(foe_move.getFAtk()<0){ //print output to show change in stats
                if(atk>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack fell by " + -1*foe_move.getFAtk() + " stages");
                    atk+=foe_move.getFAtk(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be lowered anymore!");
                }
            }else if(foe_move.getFAtk()>0){
                if(atk<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack rose by " + foe_move.getFAtk() + " stages");
                    atk+=foe_move.getFAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be raised anymore!");
                }
            }
            if(foe_move.getFDef()<0){
                if(def>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense fell by " + -1*foe_move.getFDef() + " stages");
                    def+=foe_move.getFDef(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be lowered anymore!");
                }
            }else if(foe_move.getFDef()>0){
                if(def<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense rose by " + foe_move.getFDef() + " stages");
                    def+=foe_move.getFDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be raised anymore!");
                }
            }
            if(foe_move.getFSp()<0){
                if(sp>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed fell by " + -1*foe_move.getFSp() + " stages");
                    sp+=foe_move.getFSp(); //you are the foe of your foe, so FAtk is your atk change,same with FDef and Fsp
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be lowered anymore!");
                }
            }else if(foe_move.getFSp()>0){
                if(sp<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed rose by " + foe_move.getFSp() + " stages");
                    sp+=foe_move.getFSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be raised anymore!");
                }
            }
                    
            if(foe_move.getAtk()<0){ //print output to show change in stats
                if(foe_atk>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack fell by " + -1*foe_move.getAtk() + " stages");
                    foe_atk+=foe_move.getAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be lowered anymore!");
                }
            }else if(foe_move.getAtk()>0){
                if(foe_atk<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack rose by " + foe_move.getAtk() + " stages");
                    foe_atk+=foe_move.getAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be raised anymore!");
                }
            }
            if(foe_move.getDef()<0){
                if(foe_def>-6){ 
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense fell by " + -1*foe_move.getDef() + " stages");
                    foe_def+=foe_move.getDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be lowered anymore!");
                }
            }else if(foe_move.getDef()>0){
                if(foe_def<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense rose by " + foe_move.getDef() + " stages");
                    foe_def+=foe_move.getDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be raised anymore!");
                }
            }
            if(foe_move.getSp()<0){
                if(foe_sp>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed fell by " + -1*foe_move.getSp() + " stages");
                    foe_sp+=foe_move.getSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be lowered anymore!");
                }
            }else if(foe_move.getSp()>0){
                if(foe_sp<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed rose by " + foe_move.getSp() + " stages");
                    foe_sp+=foe_move.getSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be raised anymore!");
                }
            }
            
            if(foe_move.getdmgheal()!=0.0){
                if(damage>yourcurrenthp){
                    int healing = (int)(yourcurrenthp*foe_move.getdmgheal());
                    foe_pokemon.heal(healing);
                }else{
                    int healing = (int)(damage*foe_move.getdmgheal());
                    foe_pokemon.heal(healing);
                }
            }
        }
        //in some occasion stats can be >6 and <-6 so adjust them back tp 6 or -6
        if(atk>6){
            atk=6;
        }
        if(atk<-6){
            atk=-6;
        }
        if(def>6){
            def=6;
        }
        if(def<-6){
            def=-6;
        }
        if(sp>6){
            sp=6;
        }
        if(sp<-6){
            sp=-6;
        }
        
        if(foe_atk>6){
            foe_atk=6;
        }
        if(foe_atk<-6){
            foe_atk=-6;
        }
        if(foe_def>6){
            foe_def=6;
        }
        if(foe_def<-6){
            foe_def=-6;
        }
        if(foe_sp>6){
            foe_sp=6;
        }
        if(foe_sp<-6){
            foe_sp=-6;
        }
    }
    
    public void yourmove(Move your_move){
        System.out.printf("+%s+\n","-".repeat(90));
        System.out.println("Your " + your_pokemon.findname() + " used " + your_move.getName() + " !");
        double atk_ratio = 1;
        double foe_def_ratio = 1;
        if(atk<0){
            atk_ratio = (1.0*2)/(2+(-1*atk));
        }else if(atk>0){
            atk_ratio = (1.0*(2+atk))/(1.0*2);
        }
        if(foe_def<0){
            foe_def_ratio = (2*1.0)/(2+(-1*foe_def));
        }else if(foe_def>0){
            foe_def_ratio = (1.0*(2+foe_def))/(2*1.0);
        }
                
        if(your_move.getCategory().equals("dmg")){
            int base_power = your_move.getPower();
            String your_move_type = your_move.getType();
            double effectiveness = foe_pokemon.findEffectiveness(your_move_type);
            double same_type = 1;
            if(your_pokemon.findtype1().equals(your_move.getType())||your_pokemon.findtype2().equals(your_move.getType())){
                same_type = 1.5;
            }
            int damage = (int)(base_power*same_type*effectiveness*atk_ratio/foe_def_ratio);
            
            if(effectiveness>1.0){
                System.out.println("It's super effective!");
            }else if(effectiveness<1.0&&effectiveness>0){
                System.out.println("It's not very effective...");
            }else if(effectiveness==0.0){
                System.out.println("This move does not have any effect on " + foe_pokemon.findname());
            }
            foe_pokemon.takedmg(damage);
        }else if(your_move.getCategory().equals("stat")){
            if(your_move.getAtk()<0){ //print output to show change in stats
                if(atk>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack fell by " + -1*your_move.getAtk() + " stages");
                    atk+=your_move.getAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be lowered anymore");
                }
            }else if(your_move.getAtk()>0){
                if(atk<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack rose by " + your_move.getAtk() + " stages");
                    atk+=your_move.getAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be raised anymore");
                }
            }
            if(your_move.getDef()<0){
                if(def>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense fell by " + -1*your_move.getDef() + " stages");
                    def+=your_move.getDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be lowered anymore");
                }
            }else if(your_move.getDef()>0){
                if(def<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense rose by " + your_move.getDef() + " stages");
                    def+=your_move.getDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be raised anymore");
                }
            }
            if(your_move.getSp()<0){
                if(sp>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed fell by " + -1*your_move.getSp() + " stages");
                    sp+=your_move.getSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be lowered anymore");
                }
            }else if(your_move.getSp()>0){
                if(sp<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed rose by " + your_move.getSp() + " stages");
                    sp+=your_move.getSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be raised anymore");
                }
            }
                    
            if(your_move.getFAtk()<0){ //print output to show change in stats
                if(foe_atk>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack fell by " + -1*your_move.getFAtk() + " stages");
                    foe_atk+=your_move.getFAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be lowered anymore");
                }
            }else if(your_move.getFAtk()>0){
                if(foe_atk<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack rose by " + your_move.getFAtk() + " stages");
                    foe_atk+=your_move.getFAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be raised anymore");
                }
            }
            if(your_move.getFDef()<0){
                if(foe_def>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense fell by " + -1*your_move.getFDef() + " stages");
                    foe_def+=your_move.getFDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be lowered anymore");
                }
            }else if(your_move.getFDef()>0){
                if(foe_def<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense rose by " + your_move.getFDef() + " stages");
                    foe_def+=your_move.getFDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be raised anymore");
                }
            }
            if(your_move.getFSp()<0){
                if(foe_sp>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed fell by " + -1*your_move.getFSp() + " stages");
                    foe_sp+=your_move.getFSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be lowered anymore");
                }
            }else if(your_move.getFSp()>0){
                if(foe_sp<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed rose by " + your_move.getFSp() + " stages");
                    foe_sp+=your_move.getFSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be raised anymore");
                }
            }
        }else if(your_move.getCategory().equals("heal")){
            int healing = (int)(your_pokemon.findmaxhp()*your_move.gethpheal());
            your_pokemon.heal(healing);
        }else if(your_move.getCategory().equals("sp")){
            int damage = 0;
            int currentfoehp = foe_pokemon.findcurrenthp();
            if(your_move.getPower()!=0){
                int base_power = your_move.getPower();
                String your_move_type = your_move.getType();
                double effectiveness = foe_pokemon.findEffectiveness(your_move_type);
                double same_type = 1;
                if(your_pokemon.findtype1().equals(your_move.getType())||your_pokemon.findtype2().equals(your_move.getType())){
                    same_type = 1.5;
                }
                damage = (int)(base_power*same_type*effectiveness*atk_ratio/foe_def_ratio);
                if(effectiveness>1.0){
                    System.out.println("It's super effective!");
                }else if(effectiveness<1.0&&effectiveness>0){
                    System.out.println("It's not very effective...");
                }else if(effectiveness==0.0){
                    System.out.println("This move does not have any effect on " + foe_pokemon.findname());
                }
                foe_pokemon.takedmg(damage);
            }
            if(your_move.getAtk()<0){ //print output to show change in stats
                if(atk>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack fell by " + -1*your_move.getAtk() + " stages");
                    atk+=your_move.getAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be lowered anymore");
                }
            }else if(your_move.getAtk()>0){
                if(atk<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack rose by " + your_move.getAtk() + " stages");
                    atk+=your_move.getAtk();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Attack cannot be raised anymore");
                }
            }
            if(your_move.getDef()<0){
                if(def>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense fell by " + -1*your_move.getDef() + " stages");
                    def+=your_move.getDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be lowered anymore");
                }
            }else if(your_move.getDef()>0){
                if(def<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense rose by " + your_move.getDef() + " stages");
                    def+=your_move.getDef();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Defense cannot be raised anymore");
                }
            }
            if(your_move.getSp()<0){
                if(sp>-6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed fell by " + -1*your_move.getSp() + " stages");
                    sp+=your_move.getSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be lowered anymore");
                }
            }else if(your_move.getSp()>0){
                if(sp<6){
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed rose by " + your_move.getSp() + " stages");
                    sp+=your_move.getSp();
                }else{
                    System.out.println("Your " + your_pokemon.findname() + "'s Speed cannot be raised anymore");
                }
            }
                    
            if(your_move.getFAtk()<0){ //print output to show change in stats
                if(foe_atk>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack fell by " + -1*your_move.getFAtk() + " stages");
                    foe_atk+=your_move.getFAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be lowered anymore");
                }
            }else if(your_move.getFAtk()>0){
                if(foe_atk<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack rose by " + your_move.getFAtk() + " stages");
                    foe_atk+=your_move.getFAtk();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Attack cannot be raised anymore");
                }
            }
            if(your_move.getFDef()<0){
                if(foe_def>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense fell by " + -1*your_move.getFDef() + " stages");
                    foe_def+=your_move.getFDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be lowered anymore");
                }
            }else if(your_move.getFDef()>0){
                if(foe_def<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense rose by " + your_move.getFDef() + " stages");
                    foe_def+=your_move.getFDef();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Defense cannot be raised anymore");
                }
            }
            if(your_move.getFSp()<0){
                if(foe_sp>-6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed fell by " + -1*your_move.getFSp() + " stages");
                    foe_sp+=your_move.getFSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be lowered anymore");
                }
            }else if(your_move.getFSp()>0){
                if(foe_sp<6){
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed rose by " + your_move.getFSp() + " stages");
                    foe_sp+=your_move.getFSp();
                }else{
                    System.out.println("Opponent " + foe_pokemon.findname() + "'s Speed cannot be raised anymore");
                }
            }
                    
            if(your_move.getdmgheal()!=0.0){
                if(damage>currentfoehp){ //to prevent overhealing from overkill, etc opponent have 10hp left, you hit 1000 and heal rate is 50% you are supposed to leech only 5 hp from your attack and not 500
                    int healing = (int)(currentfoehp*your_move.getdmgheal());
                    your_pokemon.heal(healing);
                }else{
                    int healing = (int)(damage*your_move.getdmgheal());
                    your_pokemon.heal(healing);
                }
            }
        }
        if(atk>6){
            atk=6;
        }
        if(atk<-6){
            atk=-6;
        }
        if(def>6){
            def=6;
        }
        if(def<-6){
            def=-6;
        }
        if(sp>6){
            sp=6;
        }
        if(sp<-6){
            sp=-6;
        }
        
        if(foe_atk>6){
            foe_atk=6;
        }
        if(foe_atk<-6){
            foe_atk=-6;
        }
        if(foe_def>6){
            foe_def=6;
        }
        if(foe_def<-6){
            foe_def=-6;
        }
        if(foe_sp>6){
            foe_sp=6;
        }
        if(foe_sp<-6){
            foe_sp=-6;
        }
    }
}
