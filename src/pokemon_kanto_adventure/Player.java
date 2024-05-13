/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_kanto_adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Player {
    private String name;
    private String [] badges = new String[8];
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private Pokemon pokemon4;
    private Pokemon pokemon5;
    private Pokemon pokemon6;
    private int money;
    private HashMap<String,Integer>items;
    private boolean rivalracecheck;
    private int rivalracewins;
    private int battlewon;
    private ArrayList<Pokemon> PC;
    public Player(String n){
        name = n;
        for(int i = 0;i<8;i++){
            badges[i]="---";
        }
        pokemon1=null;
        pokemon2=null;
        pokemon3=null;
        pokemon4=null;
        pokemon5=null;
        pokemon6=null;
        items = new HashMap<>();
        items.put("Poke Ball", 0);
        items.put("Great Ball", 0);
        items.put("Ultra Ball", 0);
        items.put("Potion", 0);
        items.put("Super Potion", 0);
        items.put("Hyper Potion", 0);
        items.put("Max Potion", 0);
        items.put("X Attack",0);
        items.put("X Defend", 0);
        items.put("X Speed", 0);
        items.put("Revive",0);
        PC = new ArrayList<Pokemon>();
    }
    public void addPokemon(Pokemon a){
        if(pokemon1==null){
            pokemon1 = a;
        }else if(pokemon2==null){
            pokemon2 = a;
        }else if(pokemon3==null){
            pokemon3 = a;
        }else if(pokemon4==null){
            pokemon4 = a;
        }else if(pokemon5==null){
            pokemon5 = a;
        }else if(pokemon6==null){
            pokemon6 = a;
        }else{
            System.out.println("Team is full, " + a.findname() + " is moved to PC");
            PC.add(a);
        }
    }
    public int findMoney(){
        return money;
    }
    public void addMoney(int add){
        money+=add;
    }
    public Pokemon findPoke1(){
        return pokemon1;
    }
    public Pokemon findPoke2(){
        return pokemon2;
    }
    public Pokemon findPoke3(){
        return pokemon3;
    }
    public Pokemon findPoke4(){
        return pokemon4;
    }
    public Pokemon findPoke5(){
        return pokemon5;
    }
    public Pokemon findPoke6(){
        return pokemon6;
    }
    public void obtainbadge(String badge){
        if(badge.equals("Boulder Badge")){
            badges[0] = badge;
        }else if(badge.equals("Cascade Badge")){
            badges[1] = badge;
        }else if(badge.equals("Thunder Badge")){
            badges[2] = badge;
        }else if(badge.equals("Rainbow Badge")){
            badges[3] = badge;
        }else if(badge.equals("Soul Badge")){
            badges[4] = badge;
        }else if(badge.equals("Marsh Badge")){
            badges[5] = badge;
        }else if(badge.equals("Volcano Badge")){
            badges[6] = badge;
        }else if(badge.equals("Earth Badge")){
            badges[7] = badge;
        }
    }
    public void showbadges(){
        System.out.println("Badges: ");
        for(int i = 0;i<badges.length;i++){
            System.out.println(badges[i] + " ");
        }
    }
    public void showinrivalrace(){
        if(rivalracecheck){
            System.out.println("In rival race");
        }else{
            System.out.println("Not in rival race");
        }
    }
    public void obtainitems(String n,int i){
        int old = items.get(n);
        int neww = old + i;
        items.replace(n,old ,neww);
    }
    public void showitems(){
        System.out.println("1. Poke Ball: " + items.get("Poke Ball"));
        System.out.println("2. Great Ball: " + items.get("Great Ball"));
        System.out.println("3. Ultra Ball: " + items.get("Ultra Ball"));
        System.out.println("4. Potion: " + items.get("Potion"));
        System.out.println("5. Super Potion: " + items.get("Super Potion"));
        System.out.println("6. Hyper Potion: " + items.get("Hyper Potion"));
        System.out.println("7. Max Potion: " + items.get("Max Potion"));
        System.out.println("8. X Attack: " + items.get("X Attack"));
        System.out.println("9. X Defend: " + items.get("X Defend"));
        System.out.println("10. X Speed: " + items.get("X Speed"));
        System.out.println("11. Revive: " + items.get("Revive"));
    }
    public int getrivalwins(){
        return rivalracewins;
    }
    public int getvictories(){
        return battlewon;
    }
    public ArrayList<Pokemon> getPC(){
        return PC;
    }
    public void wonbattle(){
        battlewon++;
    }
    public void showPC(){
        for(int i = 0;i<PC.size();i++){
            System.out.println(PC.get(i).findname());
        }
    }
    public void showteam(){
        System.out.println("--------------------------");
        System.out.println("---Pokemons---");
        if(pokemon1!=null)
            System.out.println("1. " + pokemon1.findname() + " Lvl: " + pokemon1.findlvl() + " HP: " + pokemon1.findcurrenthp() + " / " + pokemon1.findmaxhp());
        else
            System.out.println("-----");
        if(pokemon2!=null)
            System.out.println("2. " + pokemon2.findname() + " Lvl: " + pokemon2.findlvl() + " HP: " + pokemon2.findcurrenthp() + " / " + pokemon2.findmaxhp());
        else
            System.out.println("-----");
        if(pokemon3!=null)
            System.out.println("3. " + pokemon3.findname() + " Lvl: " + pokemon3.findlvl() + " HP: " + pokemon3.findcurrenthp() + " / " + pokemon3.findmaxhp());
        else
            System.out.println("-----");
        if(pokemon4!=null)
            System.out.println("4. " + pokemon4.findname() + " Lvl: " + pokemon4.findlvl() + " HP: " + pokemon4.findcurrenthp() + " / " + pokemon4.findmaxhp());
        else
            System.out.println("-----");
        if(pokemon5!=null)
            System.out.println("5. " + pokemon5.findname() + " Lvl: " + pokemon5.findlvl() + " HP: " + pokemon5.findcurrenthp() + " / " + pokemon5.findmaxhp());
        else
            System.out.println("-----");
        if(pokemon6!=null)
            System.out.println("6. " + pokemon6.findname() + " Lvl: " + pokemon6.findlvl() + " HP: " + pokemon6.findcurrenthp() + " / " + pokemon6.findmaxhp());
        else
            System.out.println("-----");
        System.out.println("---End of pokemon list---");
    }
    public String getName(){
        return name;
    }
    public void showprofile(){
        System.out.println("--------------------------");
        System.out.println("---Player Profile---");
        System.out.println("Player Name: "+ name);
        showbadges();
        showteam();
        System.out.println("Rival race wins: " + getrivalwins());
        System.out.println("Battles won: " + battlewon);
        System.out.println("---End of Player Profile---");
    }
    public void alterteam(){
        Scanner input = new Scanner(System.in);
        all:
        while(true){
            showteam();
            System.out.println("Choose a pokemon(1-6)/7 to exit: ");
            int choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    if(pokemon1==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon1,1);
                    }
                    break;
                case 2:
                    if(pokemon2==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon2,2);
                    }
                    break;
                case 3:
                    if(pokemon3==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon3,3);
                    }
                    break;
                case 4:
                    if(pokemon4==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon4,4);
                    }
                    break;
                case 5:
                    if(pokemon5==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon5,5);
                    }
                    break;
                case 6:
                    if(pokemon6==null){
                        System.out.println("--------------------------");
                        System.out.println("There is no pokemon in this slot");
                    }else{
                        choice(pokemon6,6);
                    }
                    break;
                case 7:
                    break all;
            }
        }
    }
    public void choice(Pokemon poke,int pos){
        all:
        while(true){
            System.out.println("--------------------------");
            System.out.println(poke.findname());
            System.out.println("1. Show details");
            System.out.println("2. Use items");
            System.out.println("3. Swap slots");
            System.out.println("4. Back");
            System.out.println("Select one from 1-4: ");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    poke.showPokemonInfo();
                    moves:
                    while(true){
                        System.out.println("--------------------------");
                        System.out.println("1. " + poke.findmov1());
                        System.out.println("2. " + poke.findmov2());
                        System.out.println("3. " + poke.findmov3());
                        System.out.println("4. " + poke.findmov4());
                        System.out.println("Check moves(1-4)?5 to exit: ");
                        int choicemove = input.nextInt();
                        input.nextLine();
                        switch(choicemove){
                            case 1:
                                System.out.println("--------------------------");
                                System.out.println(poke.findmov1());
                                System.out.println(library.move_description.get(poke.findmov1()));
                                break;
                            case 2:
                                System.out.println("--------------------------");
                                System.out.println(poke.findmov2());
                                System.out.println(library.move_description.get(poke.findmov2()));
                                break;
                            case 3:
                                System.out.println("--------------------------");
                                System.out.println(poke.findmov3());
                                System.out.println(library.move_description.get(poke.findmov3()));
                                break;
                            case 4:
                                System.out.println("--------------------------");
                                System.out.println(poke.findmov4());
                                System.out.println(library.move_description.get(poke.findmov4()));
                                break;
                            case 5:
                                break moves;
                        }
                    }
                    break;
                case 2:
                    itemmm:
                    while(true){
                    System.out.println("--------------------------");
                    showitems();
                    System.out.println("12. Exit");
                    System.out.println("Select items(1-11)/12 to exit: ");
                    int choiceitem = input.nextInt();
                    input.nextLine();
                    switch(choiceitem){
                        case 1: //this is supposed to be checking team while not in battle, hence all pokeballs used will output "No effect"
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 2:
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 3:
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 4:
                            System.out.println("--------------------------");
                            if(items.get("Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    poke.heal(library.pokemon_items.get("Potion").get("heal"));
                                    int old = items.get("Potion");
                                    items.replace("Potion", old, old-1);
                                }
                                
                            }else{
                                System.out.println("You do not have any Potions left");
                            }
                            break;
                        case 5:
                            System.out.println("--------------------------");
                            if(items.get("Super Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    poke.heal(library.pokemon_items.get("Super Potion").get("heal"));
                                    int old = items.get("Super Potion");
                                    items.replace("Super Potion", old, old-1);
                                }
                                
                            }else{
                                System.out.println("You do not have any Super Potions left");
                            }
                            
                            break;
                        case 6:
                            System.out.println("--------------------------");
                            if(items.get("Hyper Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    poke.heal(library.pokemon_items.get("Hyper Potion").get("heal"));
                                    int old = items.get("Hyper Potion");
                                    items.replace("Hyper Potion", old, old-1);
                                }
                                
                            }else{
                                System.out.println("You do not have any Hyper Potions left");
                            }
                            break;
                        case 7:
                            System.out.println("--------------------------");
                            if(items.get("Max Potion")!=0){
                                if(poke.findcurrenthp()==poke.findmaxhp()||poke.isFaint()){
                                    System.out.println("This item has no effect on this pokemon");
                                }else{
                                    poke.heal(library.pokemon_items.get("Max Potion").get("heal"));
                                    int old = items.get("Max Potion");
                                    items.replace("Max Potion", old, old-1);
                                }
                                
                            }else{
                                System.out.println("You do not have any Max Potions left");
                            }
                            break;
                        case 8:
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 9:
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 10:
                            System.out.println("--------------------------");
                            System.out.println("This item has no effect on this pokemon");
                            break;
                        case 11:
                            System.out.println("--------------------------");
                            if(items.get("Revive")!=0){
                                if(poke.isFaint()){
                                    poke.revive();
                                    int old = items.get("Revive");
                                    items.replace("Revive", old, old-1);
                                }else{
                                    System.out.println("This item has no effect on this pokemon");
                                }
                            }else{
                                System.out.println("You do not have any Revive left");
                            }
                            break;
                        case 12:
                            break itemmm; 
                    }
                    }
                    break;
                case 3:
                    System.out.println("--------------------------");
                    System.out.println("Choose a slot to swap with(1-6)/7 to cancel: ");
                    int choiceswap = input.nextInt();
                    input.nextLine();
                    switch(choiceswap){
                        case 1:
                            if(pokemon1!=null&&pos!=1){
                                Pokemon temp = pokemon1;
                                pokemon1 = poke;
                                if(pos==2){
                                    pokemon2 = temp;
                                }else if(pos==3){
                                    pokemon3 = temp;
                                }else if(pos==4){
                                    pokemon4 = temp;
                                }else if(pos==5){
                                    pokemon5 = temp;
                                }else if(pos==6){
                                    pokemon6 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon1!=null&&pos==1){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 2:
                            if(pokemon2!=null&&pos!=2){
                                Pokemon temp = pokemon2;
                                pokemon2 = poke;
                                if(pos==1){
                                    pokemon1 = temp;
                                }else if(pos==3){
                                    pokemon3 = temp;
                                }else if(pos==4){
                                    pokemon4 = temp;
                                }else if(pos==5){
                                    pokemon5 = temp;
                                }else if(pos==6){
                                    pokemon6 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon2!=null&&pos==2){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 3:
                            if(pokemon3!=null&&pos!=3){
                                Pokemon temp = pokemon3;
                                pokemon3 = poke;
                                if(pos==1){
                                    pokemon1 = temp;
                                }else if(pos==2){
                                    pokemon2 = temp;
                                }else if(pos==4){
                                    pokemon4 = temp;
                                }else if(pos==5){
                                    pokemon5 = temp;
                                }else if(pos==6){
                                    pokemon6 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon3!=null&&pos==3){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 4:
                            if(pokemon4!=null&&pos!=4){
                                Pokemon temp = pokemon4;
                                pokemon4 = poke;
                                if(pos==1){
                                    pokemon1 = temp;
                                }else if(pos==2){
                                    pokemon2 = temp;
                                }else if(pos==3){
                                    pokemon3 = temp;
                                }else if(pos==5){
                                    pokemon5 = temp;
                                }else if(pos==6){
                                    pokemon6 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon4!=null&&pos==4){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 5:
                            if(pokemon5!=null&&pos!=5){
                                Pokemon temp = pokemon5;
                                pokemon5 = poke;
                                if(pos==1){
                                    pokemon1 = temp;
                                }else if(pos==2){
                                    pokemon2 = temp;
                                }else if(pos==3){
                                    pokemon3 = temp;
                                }else if(pos==4){
                                    pokemon4 = temp;
                                }else if(pos==6){
                                    pokemon6 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon5!=null&&pos==5){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 6:
                            if(pokemon6!=null&&pos!=6){
                                Pokemon temp = pokemon6;
                                pokemon6 = poke;
                                if(pos==1){
                                    pokemon1 = temp;
                                }else if(pos==2){
                                    pokemon2 = temp;
                                }else if(pos==3){
                                    pokemon3 = temp;
                                }else if(pos==4){
                                    pokemon4 = temp;
                                }else if(pos==5){
                                    pokemon5 = temp;
                                }
                                System.out.println("--------------------------");
                                System.out.println("Position swapped");
                                break all;
                            }else if(pokemon6!=null&&pos==6){
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid beacuse this slot is its original slot");
                                break;
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("This swap is invalid because this slot does not have a pokemon");
                                break;
                            }
                        case 7:
                            break;
                    }
                    break;
                case 4:
                    break all;
            }
        }
    }
    
}
