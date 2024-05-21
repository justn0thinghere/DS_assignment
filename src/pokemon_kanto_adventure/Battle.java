
package pokemon_kanto_adventure;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Player player;
    private String opponent;
    private Pokemon your_pokemon;
    private Pokemon foe_pokemon;
    private double atk;
    private double def;
    private double sp;
    private double foe_atk;
    private double foe_def;
    private double foe_sp;
    public Battle(Player p,String opp){
        player = p;
        opponent = opp;
        resetstat();
    }
    public Battle(Player p,Pokemon wild){
        Random r = new Random();
        player = p;
        if(!p.findPoke1().isFaint()){ //at least one pokemon is not fainted because if all fainted will automatically be healed
            your_pokemon = p.findPoke1();
        }else if(!p.findPoke2().isFaint()&&p.findPoke2()!=null){
            your_pokemon = p.findPoke2();
        }else if(!p.findPoke3().isFaint()&&p.findPoke3()!=null){
            your_pokemon = p.findPoke3();
        }else if(!p.findPoke4().isFaint()&&p.findPoke4()!=null){
            your_pokemon = p.findPoke4();
        }else if(!p.findPoke5().isFaint()&&p.findPoke5()!=null){
            your_pokemon = p.findPoke5();
        }else if(p.findPoke6()!=null){ //at least last pokemon is not fainted
            your_pokemon = p.findPoke6();
        }
        System.out.println("You sent out " + your_pokemon.findname());
        foe_pokemon = wild;
        resetstat();
        System.out.println("");
        System.out.println("Tips: ");
        foe_pokemon.showWeakness();
        foe_pokemon.showResistance();
        System.out.println("");
        Scanner input = new Scanner(System.in);
        all:
        while(!player.teamfaint()||foe_pokemon.isFaint()){
            boolean move = false;
            Move your_move = null;
            boolean alter_pokemons = false;
            boolean bag_items = false;
            //your desicion
            decision:
            while(true){
            //-move
            //-pokemon
            //-bag
            //-run -- break
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("1. Fight");
                System.out.println("2. Pokemons");
                System.out.println("3. Bag");
                System.out.println("4. Run");
                System.out.println("Your move(Choose 1-4)");
                String choice_st = input.nextLine();
                if(isNum(choice_st)){
                    int choice = Integer.parseInt(choice_st);
                    switch(choice){
                        case 1:
                            your_move = chooseMove();
                            if(your_move!=null){
                                move = true;
                                break decision;
                            }
                            break;
                        case 2:
                            alter_pokemons = pokemons();
                            if(alter_pokemons){
                                break decision;
                            }
                            break;
                        case 3:
                            bag_items = bag();
                            if(bag_items){
                                break decision;
                            }
                        case 4:
                            System.out.println("You ran away safely!");
                            break all;
                        default:
                            System.out.println("Invalid choice, choose again");
                    }
                }else{
                    System.out.println("Invalid input");
                }
            }
            
            //enemy decision
            Move foe_move = null;
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
            //if wild pokemon is caught
                //break and end battle
            if(!foe_pokemon.wild()){
                break all;
            }
            
            //if swap and use items then your pokemon cannot move
            if(alter_pokemons||bag_items){
                double def_ratio = 1;
                double foe_atk_ratio = 1;
                
            }else if(move){
            //calculate move order
                int your_move_order = your_move.getOrder();
                int foe_move_order = foe_move.getOrder();
            //calculate speed
                double your_speed = your_pokemon.findspeed();
                double foe_speed = foe_pokemon.findspeed();
                double sp_rate = 1;
                double foe_sp_rate = 1;
                if(sp>0){
                    sp_rate = (1.0*(2+sp))/2;
                }else if(sp<0){
                    sp_rate = 2/(1.0*(2+sp));
                }
            
                if(foe_sp>0){
                    foe_sp_rate = (1.0*(2+foe_sp))/2;
                }else if(foe_sp<0){
                    foe_sp_rate = 2/(1.0*(2+foe_sp));
                }
                your_speed = your_speed*sp_rate;
                foe_speed = foe_speed*foe_sp_rate;
                
                //determine who moves first
                if(your_move_order>foe_move_order){
                    //you move first
                }else if(foe_move_order>your_move_order){
                    //foe move first
                }else{ //same move order
                    if(your_speed>foe_speed){
                        //you move first
                    }else if(foe_speed>your_speed){
                        //foe move first
                    }else{ //same speed
                        int rando = r.nextInt(2);
                        if(rando == 0){
                            //you move first
                        }else{
                            //foe move first
                        }
                    }
                }
            //calculate stat change
            //calculate damage
            //inflict dmg,stat change,heal,dmgheal
            //if slower is not faint, slower move
            //inflict dmg,stat change,heal,dmgheal
        
            
                
            //if wild faint 
                //obtainxp()
                //break
            
            //if your pokemon faint
            //-check allfaint?
            //-next pokemon
            //-run -- break
            }
        }
    }
    public boolean isNum(String s){
        try{
            int ss = Integer.parseInt(s);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    public void resetstat(){
        atk = 0;
        def = 0;
        sp = 0;
        foe_atk = 0;
        foe_def = 0;
        foe_sp = 0;
    }
    public void resetyourstatus(){
        atk = 0;
        def = 0;
        sp = 0;
    }
    public void resetfoestatus(){
        foe_atk = 0;
        foe_def = 0;
        foe_sp = 0;
    }
    public Move chooseMove(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("1. " + your_pokemon.findmov1() + " [ " + library.move_cat.get(your_pokemon.findmov1()) + " move ]");
            Move move1 = new Move(your_pokemon.findmov1(),your_pokemon.findlvl());
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
            System.out.println("5. Back");
            System.out.println("Choose a move to use(1-4) or 5 to go back to previous selection page");
            String choice_st = input.nextLine();
            if(isNum(choice_st)){
                int choice = Integer.parseInt(choice_st);
                switch(choice){
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
                System.out.println("Invalid choice, choose again!");
            }
        }
    }
    public boolean pokemons(){
        Scanner input = new Scanner(System.in);
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
                    System.out.println("Invalid choice, choose again|");
            }
            }else{
                System.out.println("Invalid choice, choose again!");
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
                                System.out.println("Invalid choice");
                        }
                        }else{
                            System.out.println("Invalid input format");
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
                            System.out.println("Invalid choice, choose again!");
                    }
                    }else{
                        System.out.println("Invalid choice, choose again!");
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
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke1();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 2){
                        if(your_pokemon.equals(player.findPoke2())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke2().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke2();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 3){
                        if(your_pokemon.equals(player.findPoke3())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke3().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke3();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 4){
                        if(your_pokemon.equals(player.findPoke4())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke4().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke4();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 5){
                        if(your_pokemon.equals(player.findPoke5())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke5().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke5();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }else if(pos == 6){
                       if(your_pokemon.equals(player.findPoke6())){
                            System.out.println("This pokemon is already in battle!");
                        }else if(player.findPoke6().isFaint()){
                            System.out.println("This pokemon is already fainted");
                        }else{
                            System.out.print("You withdrew " + your_pokemon.findname());
                            your_pokemon.changebattlestatus(false);
                            your_pokemon = player.findPoke6();
                            your_pokemon.changebattlestatus(true);
                            System.out.println(" and sent out " + your_pokemon.findname());
                            resetyourstatus();
                            return true;
                        }
                    }
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice, choose again!");
            }
            }else{
                System.out.println("Invalid choice, choose again!");
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
                        System.out.println("Invalid choice, choose again");
                }
            }else{
                System.out.println("Invalid choice, choose again");
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
                        System.out.println("You used a Poke Ball!");
                        if(foe_pokemon.wild()){
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
                        System.out.println("You used a Great Ball!");
                        if(foe_pokemon.wild()){
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
                        System.out.println("You used a Ultra Ball!");
                        if(foe_pokemon.wild()){
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
                        System.out.println(your_pokemon.findname() + "'s attack rose by 1 stage");
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
                        System.out.println(your_pokemon.findname() + "'s defense rose by 1 stage");
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
                        System.out.println(your_pokemon.findname() + "'s speed rose by 1 stage");
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
                        System.out.println("Invalid input, choose again");
                }
            }else{
                System.out.println("Invalid input, choose again");
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
                        System.out.println("Invalid input, choose again");
                }
            }else{
                System.out.println("Invalid input, choose again");
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
                        System.out.println("Invalid input, choose again");
                }
            }else{
                System.out.println("Invalid input, choose again");
            }
        }
        }
        return false;
    }
}
