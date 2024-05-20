
package pokemon_kanto_adventure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Pokemon_kanto_adventure {


    public static void main(String[] args) {
        /*
        while(true){
            title();
            selectSave();
        }
        */
        library.readallfiles();
        Player a = new Player("Jack");
        a.obtainitems("Potion", 2);
        a.obtainitems("Great Ball", 3);
        a.addMoney(100000);
        a.obtainitems("Ultra Ball", 3);
        a.obtainitems("X Attack", 3);
        a.obtainitems("X Defend", 3);
        a.obtainitems("X Speed", 3);
        a.obtainitems("Poke Ball", 4);
        a.obtainitems("Revive",10);
        a.obtainitems("Super Potion",5);
        a.obtainitems("Hyper Potion", 1);
        a.obtainitems("Max Potion", 3);
        a.addPokemon(new Pokemon("Bulbasaur",5));
        a.addPokemon(new Pokemon("Charmander",6));
        a.addPokemon(new Pokemon("Snorlax",100));
        a.addPokemon(new Pokemon("Ponyta",5));
        a.addPokemon(new Pokemon("Pikachu",5));
        a.addPokemon(new Pokemon("Squirtle",5));
        a.findPoke1().takedmg(100);
        a.findPoke2().takedmg(200);
        a.findPoke3().takedmg(3000);
        a.findPoke4().takedmg(1000);
        a.findPoke5().takedmg(1200);
        a.findPoke6().takedmg(2000);
        System.out.println("");
        System.out.println("");
        title();
        selectionPanel(a);
        
    }
    //print pokemon title when boot game
    public static void title(){
        try{
            Scanner sc = new Scanner(new FileInputStream("PokemonLogo.txt"));
            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());}
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    //select a save to start or create a new account
    public static void selectSave(){
        
    }
    //load save after select
    public static void loadSave(){
        
    }
    public static void guides(){
        
    }
    //everytime a new account is created, mom will talk to you, prof oak will let you choose starter with lvl 5
    public static void newPlayer(){
        
    }
    //save progress by writing out
    public static void save(){
        
    }
    public static void selectionPanel(Player player){
        boolean tf = true;
        while(tf){
            System.out.printf("+%s+\n","-".repeat(90));
            String currentCity = player.findCurrentCity();
            System.out.println("You are currently in: " + currentCity);
            System.out.printf("+%s+\n","-".repeat(90));
            switch(currentCity){
                case "Pallet Town":
                    tf = selectionPalletTown(player);
                    break;
                case "Viridian City":
                    tf = selectionViridianCity(player);
                    break;
                case "Pewter City":
                    tf = selectionPewterCity(player);
                    break;
                case "Cerulean City":
                    tf = selectionCeruleanCity(player);
                    break;
                case "Saffron City":
                    tf = selectionSaffronCity(player);
                    break;
                case "Celadon City":
                    tf = selectionCeladonCity(player);
                    break;
                case "Lavender Town":
                    tf = selectionLavenderTown(player);
                    break;
                case "Vermillion City":
                    tf = selectionVermillionCity(player);
                    break;
                case "Fuschia City":
                    tf = selectionFuschiaCity(player);
                    break;
                case "Cinnabar Island":
                    tf = selectionCinnabarIsland(player);
                    break;
            }
        }
    }
    public static boolean selectionPalletTown(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Pallet Town");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Talk to Mom(heal all pokemon to full status)");
            System.out.println("[3] Fight Wild Pokemon [Caterpie, Rattata, Mankey][max lvl 5]");
            System.out.println("[4] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You talked to Mom");
                System.out.println("Mom: " + player.getName() + " ! Welcome home. It sounds like you had quite an experience. Maybe you should take a quick rest.");
                player.allhealup();
                System.out.println("Mom: Oh, good! You and your Pokémon are looking great. I just heard from Prof. Oak. He said that Pokémon's energy is measured in HP. If your Pokémon lose their HP, you can restore them at any Pokémon Center. If you're going to travel far away, the smart Trainer stocks up on Potions at the Pokémon Mart. Make me proud, honey! Take care!");
                // Talk to Mom
            }else if (choice.equals("3")) {
                Random r = new Random();
                String[]wilds = {"Caterpie","Rattata","Mankey"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(2,6);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
                // Implement wild Pokémon battle logic
            }else if (choice.charAt(0)=='4'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionViridianCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Viridian City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Giovanni - Ground type ] [Recommended Pokemon Level: 50]");
            System.out.println("[5] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Talk to Mom
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                if(!player.getbadges()[7].equals("Earth Badge")){
                    if(player.getbadges()[0].equals("Boulder Badge")&&player.getbadges()[1].equals("Cascade Badge")&&player.getbadges()[2].equals("Thunder Badge")&&player.getbadges()[3].equals("Rainbow Badge")&&player.getbadges()[4].equals("Soul Badge")&&player.getbadges()[5].equals("Marsh Badge")&&player.getbadges()[6].equals("Volcano Badge")){
                        System.out.println("You have all other gym badges! This is your final battle. Good luck!");
                        System.out.println("You are now challenging Gym Leader Giovanni!");
                        //implement viridiangym()
                    }else{
                        System.out.println("You have not obtained all the other badges yet, Giovanni is the strongest leader in the region.");
                        System.out.println("You are not strong enough to face him at this moment, please come back with all other badges to prove that you are a worthy opponent");
                    }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            }
            else if (choice.charAt(0)=='5'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionPewterCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Pewter City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Brock - Rock type ] [Recommended Pokemon Level: 14]");
            System.out.println("[5] Fight Wild Pokemon [Caterpie, Metapod, Pikachu][max lvl 6]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Rick[Bug type][lvl 6]    b.Anthony[Bug type][lvl 9]     c.Charlie[Electric type][lvl 8]");
            System.out.println("[7] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Brock!");
                //implement pewtergym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Caterpie","Metapod","Pikachu"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(3,7);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight rick - caterpie lvl 6, caterpie lvl 6
                        break;
                    case 'b':
                        //fight anthony - caterpie lvl 9
                        break;
                    case 'c':
                        //fight Charlie - pikachu lvl 9
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionCeruleanCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Cerulean City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Misty - Water type ] [Recommended Pokemon Level: 21]");
            System.out.println("[5] Fight Wild Pokemon [Sandshrew, Geodude, Onix][max lvl 12]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Rocket Grunt[Ground, Fighting type][lvl 17]    b.Marcos[Rock type][lvl 11]     c.Jovan[Electric type][lvl 14]");
            System.out.println("[7] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Misty!");
                //implement ceruleangym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Sandshrew", "Geodude", "Onix"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(8,13);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight grunt - machop lvl 17, sandshrew lvl 17
                        break;
                    case 'b':
                        //fight marcos - 2x geodude lvl 11, onix lvl 11
                        break;
                    case 'c':
                        //fight Jovan - voltorb lvl 14, magnemite lvl 14
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionSaffronCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Saffron City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Sabrina - Psychic type ] [Recommended Pokemon Level: 43]");
            System.out.println("[5] Fight Wild Pokemon [Oddish, Bellsprout, Growlithe, Abra][max lvl 16]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Ricky[Water type][lvl 30]    b.Jeff[Normal type][lvl 29]     c.Elijah[Bug type][lvl 30]");
            System.out.println("[7] Rival Race");
            System.out.println("[8] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Sabrina!");
                //implement saffrongym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Oddish", "Bellsprout", "Growlithe", "Abra"};
                int wild_choice = r.nextInt(4);
                int wild_lvl = r.nextInt(11,17);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight ricky - wartortle lvl 30
                        break;
                    case 'b':
                        //fight Jeff - 2x raticate lvl 29
                        break;
                    case 'c':
                        //fight Elijah - Butterfree lvl 30
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else if(choice.equals("7")){
                player.startrivalrace();
            }else if (choice.charAt(0)=='8'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionCeladonCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Celadon City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Erika - Grass type ] [Recommended Pokemon Level: 29]");
            System.out.println("[5] Fight Wild Pokemon [Koffing, Grimer, Machop, Ponyta][max lvl 23]");
            System.out.println("[6] Fight Snorlax [lvl 30]");
            System.out.println("[7] Fight other trainers");
            System.out.println("     a.Lao[Poison type][lvl 27]    b.Koji[Fighting type][lvl 27]     c.Lea[Fire type][lvl 27]");
            System.out.println("[8] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Erika!");
                //implement celadongym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Koffing", "Grimer", "Machop","Ponyta"};
                int wild_choice = r.nextInt(4);
                int wild_lvl = r.nextInt(20,24);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if(choice.equals("6")){
                System.out.println("A wild Snorlax is blocking the road!");
                Pokemon wild = new Pokemon("Snorlax",30);
            }else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight lao - grimer lvl 27, koffing lvl 27
                        break;
                    case 'b':
                        //fight koji - machop lvl 27, mankey lvl 27
                        break;
                    case 'c':
                        //fight lea - rapidash lvl 27
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='8'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionLavenderTown(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Lavender Town");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Pokemon Tower - Poke Maze");
            System.out.println("[5] Fight Wild Pokemon [Magnemite, Voltorb, Nidoran-M, Nidoran-F, Venonat][max lvl 20]");
            System.out.println("[6] Fight Snorlax [lvl 30]");
            System.out.println("[7] Fight other trainers");
            System.out.println("     a.Luca[Electric type][lvl 29]    b.Justin[Poison type][lvl 29]     c.Tower Grunt[Normal type][lvl 27]");
            System.out.println("[8] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                PokeMaze maze = new PokeMaze();
                maze.simulation(player);
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Magnemite", "Voltorb", "Nidoran-M", "Nidoran-F", "Venonat"};
                int wild_choice = r.nextInt(5);
                int wild_lvl = r.nextInt(14,21);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if(choice.equals("6")){
                System.out.println("A wild Snorlax is blocking the road!");
                Pokemon wild = new Pokemon("Snorlax",30);
            }else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight luca - voltorb, electrode lvl 29
                        break;
                    case 'b':
                        //fight justin - Nidoran-M,Nidoran-F, lvl 29
                        break;
                    case 'c':
                        //fight tower grunt - rattata, raticate lvl 27
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='8'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionVermillionCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Vermillion City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Lt. Surge - Electric type ] [Recommended Pokemon Level: 24]");
            System.out.println("[5] Fight Wild Pokemon [Diglett, Jigglypuff, Eevee][max lvl 22]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Yasu[Normal type][lvl 17]    b.Dave[Poison type][lvl 18]     c.Bernie[Electric type][lvl 18]");
            System.out.println("[7] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Lt. Surge!");
                //implement gym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Diglett", "Jigglypuff", "Eevee"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(15,23);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight yasu - 2x rattata, raticate lvl 17
                        break;
                    case 'b':
                        //fight dave - nidoran-m,nidorino lvl 18
                        break;
                    case 'c':
                        //fight bernie - 2x magnemite, magneton lvl 18
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionFuschiaCity(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Fuschia City");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Koga - Poison type ] [Recommended Pokemon Level: 43]");
            System.out.println("[5] Fight Wild Pokemon [Grimer, Rattata, Raticate][max lvl 29]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Charles[Poison type][lvl 39]    b.Jacob[Fire type][lvl 39]     c.Connie[Water type][lvl 33]");
            System.out.println("[7] Safari Zone");
            System.out.println("[8] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Koga!");
                //implement gym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Grimer", "Rattata", "Raticate"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(22,30);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight charles - koffing, weezing lvl 39
                        break;
                    case 'b':
                        //fight jacob - charmeleon lvl 39
                        break;
                    case 'c':
                        //fight connie - 3x staryu lvl 33
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else if(choice.equals("7")){
                SafariZone(player);
            }else if (choice.charAt(0)=='8'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static boolean selectionCinnabarIsland(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("[1] Move to:");
            ArrayList<String> neighboringCities = library.kantoMap.getNeighbours("Cinnabar Island");
            for (int i = 0; i < neighboringCities.size(); i++) {
                System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
            }
            System.out.println("[2] Pokemon Center");
            System.out.println("[3] Poke Mart");
            System.out.println("[4] Fight Gym Leader [ Blaine - Fire type ] [Recommended Pokemon Level: 47]");
            System.out.println("[5] Fight Wild Pokemon [Staryu, Tangela][max lvl 28]");
            System.out.println("[6] Fight other trainers");
            System.out.println("     a.Lil[Water type][lvl 33]    b.Jack[Water type][lvl 37]     c.Jerome[Water type][lvl 33]");
            System.out.println("[7] Player Options");
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice");
                }
                // Move the next city
            }else if (choice.equals("2")) {
                System.out.printf("+%s+\n","-".repeat(90));
                System.out.println("You entered the Pokemon Center");
                System.out.println("Nurse: " + player.getName() + " ! Welcome to the Pokemon Center. This is a place where you can heal up all your pokemons to their best status, let me heal up your pokemons real quick!");
                player.allhealup();
                System.out.println("Nurse: Oh, good! You and your Pokémon are looking great. Good luck on your journey and take care of your pokemons!");
                // Poke Center
            }else if (choice.equals("3")) {
                pokeMart(player);
            }else if(choice.equals("4")){
                System.out.println("You are now challenging Gym Leader Blaine!");
                //implement gym()
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Staryu", "Tangela"};
                int wild_choice = r.nextInt(2);
                int wild_lvl = r.nextInt(17,29);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight lil - starmie lvl 33
                        break;
                    case 'b':
                        //fight jack - starmie lvl 37
                        break;
                    case 'c':
                        //fight jerome - staryu lvl 33, wartortle lvl 33
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
            else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        displayMap(player);
                        break;
                    case 'b':
                        player.alterteam();
                        break;
                    case 'c':
                        player.bag();
                        break;
                    case 'd':
                        player.showbadges();
                        break;
                    case 'e':
                        player.showprofile();
                        break;
                    case 'f':
                        guides();
                        break;
                    case 'g':
                        save();
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            return true;
    }
    public static void pokeMart(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("You entered the Poke Mart");
                while(true){
                    System.out.println("Hello trainer, welcome to the PokeMart.How can I help you?");
                    System.out.println("1. Buy");
                    System.out.println("2. Sell");
                    System.out.println("3. Exit");
                    System.out.print("Choose 1 action: ");
                    String action = input.nextLine();
                    if(action.equals("1")){
                        System.out.println("+----------------------Buy-----------------------+");
                        System.out.println("You have: $" + player.findMoney());
                        System.out.println("1. Poke Ball    - $ " + library.pokemon_items.get("Poke Ball").get("price"));
                        System.out.println("2. Great Ball   - $ " + library.pokemon_items.get("Great Ball").get("price"));
                        System.out.println("3. Ultra Ball   - $ " + library.pokemon_items.get("Ultra Ball").get("price"));
                        System.out.println("4. Potion       - $ " + library.pokemon_items.get("Potion").get("price"));
                        System.out.println("5. Super Potion - $ " + library.pokemon_items.get("Super Potion").get("price"));
                        System.out.println("6. Hyper Potion - $ " + library.pokemon_items.get("Hyper Potion").get("price"));
                        System.out.println("7. Max Potion   - $ " + library.pokemon_items.get("Max Potion").get("price"));
                        System.out.println("8. X Attack     - $ " + library.pokemon_items.get("X Attack").get("price"));
                        System.out.println("9. X Defend     - $ " + library.pokemon_items.get("X Defend").get("price"));
                        System.out.println("10. X Speed     - $ " + library.pokemon_items.get("X Speed").get("price"));
                        System.out.println("11. Revive      - $ " + library.pokemon_items.get("Revive").get("price"));
                        System.out.println("12. Back");
                        System.out.println("Select items(1-11) to buy/12 to go back: ");
                        String choiceitem_st = input.nextLine();
                        if(player.isNum(choiceitem_st)){
                            int choiceitem = Integer.parseInt(choiceitem_st);
                            switch(choiceitem){
                            case 1: //this is supposed to be checking team while not in battle, hence all pokeballs used will output "No effect"
                                System.out.println("+-----Poke Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Poke Ball"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Poke Ball").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                String number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Poke Ball").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Poke Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 2:
                                System.out.println("+-----Great Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Great Ball"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Great Ball").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Great Ball").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Great Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 3:
                                System.out.println("+-----Ultra Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Ultra Ball"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Ultra Ball").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Ultra Ball").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Ultra Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 4:
                                System.out.println("+-----Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Potion"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Potion").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Potion").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 5:
                                System.out.println("+-----Super Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Super Potion"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Super Potion").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Super Potion").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Super Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 6:
                                System.out.println("+-----Hyper Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Hyper Potion"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Hyper Potion").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Hyper Potion").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Hyper Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 7:
                                System.out.println("+-----Max Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Max Potion"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Max Potion").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Max Potion").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Max Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 8:
                                System.out.println("+-----X Attack-----+");
                                System.out.println("You have: " + player.getItems().get("X Attack"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("X Attack").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Attack").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("X Attack", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 9:
                                System.out.println("+-----X Defend-----+");
                                System.out.println("You have: " + player.getItems().get("X Defend"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("X Defend").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Defend").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("X Defend", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 10:
                                System.out.println("+-----X Speed-----+");
                                System.out.println("You have: " + player.getItems().get("X Speed"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("X Speed").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Speed").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("X Speed", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 11:
                                System.out.println("+-----Revive-----+");
                                System.out.println("You have: " + player.getItems().get("Revive"));
                                System.out.println("Buy Price: $ " + library.pokemon_items.get("Revive").get("price"));
                                System.out.println("How many would you like to buy?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Revive").get("price");
                                    if(price>player.findMoney()){
                                        System.out.println("You don't have enough money.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to buy the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.deductMoney(price);
                                            player.obtainitems("Revive", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 12:
                                break;
                            default:
                                System.out.println("Invalid choice");
                            }
                        }else{
                            System.out.println("Invalid choice");
                        }
                    }else if(action.equals("2")){    
                        System.out.println("+-----------------------Sell-----------------------+");
                        System.out.println("Your items: ");
                        player.showitems();
                        System.out.println("12. Back");
                        System.out.println("Note: Sell price of each items is 70% of their buy price.");
                        System.out.println("Select items(1-11) to sell/12 to go bak: ");
                        String choiceitem_st = input.nextLine();
                        if(player.isNum(choiceitem_st)){
                            int choiceitem = Integer.parseInt(choiceitem_st);
                            switch(choiceitem){
                            case 1: //this is supposed to be checking team while not in battle, hence all pokeballs used will output "No effect"
                                System.out.println("+-----Poke Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Poke Ball"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Poke Ball").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                String number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Poke Ball").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Poke Ball")){
                                        System.out.println("You don't have enough Poke Balls.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Poke Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 2:
                                System.out.println("+-----Great Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Great Ball"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Great Ball").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Great Ball").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Great Ball")){
                                        System.out.println("You don't have enough Great Balls.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Great Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 3:
                                System.out.println("+-----Ultra Ball-----+");
                                System.out.println("You have: " + player.getItems().get("Ultra Ball"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Ultra Ball").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Ultra Ball").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Ultra Ball")){
                                        System.out.println("You don't have enough Ultra Balls.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Ultra Ball", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 4:
                                System.out.println("+-----Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Potion"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Potion").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Potion").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Potion")){
                                        System.out.println("You don't have enough Potions.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 5:
                                System.out.println("+-----Super Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Super Potion"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Super Potion").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Super Potion").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Super Potion")){
                                        System.out.println("You don't have enough Super Potions.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Super Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 6:
                                System.out.println("+-----Hyper Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Hyper Potion"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Hyper Potion").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Hyper Potion").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Hyper Potion")){
                                        System.out.println("You don't have enough Hyper Potions.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Hyper Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 7:
                                System.out.println("+-----Max Potion-----+");
                                System.out.println("You have: " + player.getItems().get("Max Potion"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Max Potion").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Max Potion").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Max Potion")){
                                        System.out.println("You don't have enough Max Potions.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Max Potion", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 8:
                                System.out.println("+-----X Attack-----+");
                                System.out.println("You have: " + player.getItems().get("X Attack"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("X Attack").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Attack").get("price") * 7 / 10;
                                    if(number>player.getItems().get("X Attack")){
                                        System.out.println("You don't have enough X Attacks.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("X Attack", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 9:
                                System.out.println("+-----X Defend-----+");
                                System.out.println("You have: " + player.getItems().get("X Defend"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("X Defend").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Defend").get("price") * 7 / 10;
                                    if(number>player.getItems().get("X Defend")){
                                        System.out.println("You don't have enough X Defends.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("X Defend", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 10:
                                System.out.println("+-----X Speed-----+");
                                System.out.println("You have: " + player.getItems().get("X Speed"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("X Speed").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("X Speed").get("price") * 7 / 10;
                                    if(number>player.getItems().get("X Speed")){
                                        System.out.println("You don't have enough X Speeds.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("X Speed", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 11:
                                System.out.println("+-----Revive-----+");
                                System.out.println("You have: " + player.getItems().get("Revive"));
                                System.out.println("Sell Price: $ " + library.pokemon_items.get("Revive").get("price") * 7 / 10);
                                System.out.println("How many would you like to sell?");
                                System.out.print("Enter a number: ");
                                number_st = input.nextLine();
                                if(player.isNum(number_st)){
                                    int number = Integer.parseInt(number_st);
                                    int price = number * library.pokemon_items.get("Revive").get("price") * 7 / 10;
                                    if(number>player.getItems().get("Revive")){
                                        System.out.println("You don't have enough Revives.");
                                    }else{
                                        System.out.println("That will be $ " + price);
                                        System.out.println("Would you like to sell the item(s) you selected?(yes-y/no-any other input)");
                                        String ccc = input.nextLine();
                                        if(ccc.equals("y")){
                                            player.addMoney(price);
                                            player.deditems("Revive", number);
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid input");
                                }
                                break;
                            case 12:
                                break;
                            default:
                                System.out.println("Invalid choice");
                            }
                        }else{
                            System.out.println("Invalid choice");
                        }
                    }else if(action.endsWith("3")){
                        System.out.println("Thank you for coming! Hope you have a nice day!");
                        break;
                    }else{
                        System.out.println("Invalid choice");
                    }
                }
    }
    public static void SafariZone(Player player){
        Scanner sc = new Scanner(System.in);
        System.out.printf("+%s+\n","-".repeat(90));
        System.out.println("Welcome to the Safari Zone! Today's challenge: Sort the Pokemon!");
        System.out.printf("+%s+\n","-".repeat(90));
        while (true) {
            System.out.print("Enter the Pokemon in your party (separated by a comma): ");
            String input = sc.nextLine();
            String[] pokemonArray = input.split(", ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(pokemonArray));
            while (true) {
                if (CheckInput(list)) {
                    break;
                } else {
                    System.out.println("Enter correct input (Example: Pikachu, Eevee, Snorlax)");
                    input = sc.nextLine();
                    pokemonArray = input.split(", ");
                    list = new ArrayList<>(Arrays.asList(pokemonArray));
                }
            }
            
            System.out.println("");
            System.out.println("You entered: " + displayList(list));
            System.out.println("");
            System.out.println("Sorting your Pokemon according to their unique preferences...");
            System.out.println("");
            int step = 1;
            if (list.contains("Eevee")) {
                if(list.lastIndexOf("Eevee")!=list.indexOf("Eevee")){//more than one eevee. Which means one of the eevees cannot become the first one, hence list is unsortable
                    System.out.println("This set of pokemons is unsortable");
                    return;
                }
                System.out.println("Step " + step + " : Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability.");
                String temp = list.get(0);
                int indexEevee = list.indexOf("Eevee");
                list.set(0, "Eevee");
                list.set(indexEevee, temp);
                System.out.println("Partial sort: " + displayList(list));
                System.out.println("");
                step++;
            }
            if (list.contains("Pikachu")) {
                System.out.println("Step " + step + " : Pikachu demands to be placed at the center of the arrangement.");
                String temp = list.get(list.size() / 2);
                int indexPikachu = list.indexOf("Pikachu");
                list.set(list.size() / 2, "Pikachu");
                list.set(indexPikachu, temp);
                System.out.println("Partial sort: " + displayList(list));
                System.out.println("");
                step++;
            }
            if (list.contains("Snorlax")) {
                System.out.println("Step " + step + " : Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation.");
                String temp = list.get(list.size() - 1);
                int indexSnorlax = list.indexOf("Snorlax");
                list.set(list.size() - 1, "Snorlax");
                list.set(indexSnorlax, temp);
                System.out.println("Partial sort: " + displayList(list));
                System.out.println("");
                step++;
            }
            if (list.contains("Jigglypuff")) {
                for (int i = 0; i < list.size() - 1; i++) {
                    if (library.pokemon_cute.get(list.get(i)) && !(list.get(i).equals("Jigglypuff"))) {
                        System.out.println("Step " + step + " : Jigglypuff prefers to be surrounded by other \"cute\" Pokemon for morale purposes.");
                        String temp = list.get(i + 1);
                        if (!(list.get(i + 1).equals("Eevee") || list.get(i + 1).equals("Pikachu") || list.get(i + 1).equals("Snorlax"))) {
                            int indexJigglypuff = list.indexOf("Jigglypuff");
                            list.set(i + 1, "Jigglypuff");
                            list.set(indexJigglypuff, temp);
                        }
                        System.out.println("Partial sort: " + displayList(list));
                        System.out.println("");
                        step++;
                        break;
                    }
                }

            }

            if (list.contains("Bulbasaur") && list.contains("Charmander")) {
                System.out.println("Step " + step + " : Bulbasaur refuses to be placed next to Charmander.");
                int indexB = list.indexOf("Bulbasaur");
                int indexC = list.indexOf("Charmander");
                if (Math.abs(indexB - indexC) == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        if (!(list.get(i).equals("Eevee") || list.get(i).equals("Pikachu") || list.get(i).equals("Snorlax") || i == indexB || i == indexC)) {
                            if (indexB > indexC) {
                                list.set(indexB, list.get(i));
                                list.set(i, "Bulbasaur");
                            } else {
                                list.set(indexC, list.get(i));
                                list.set(i, "Charmander");
                            }
                            break;
                        }
                    }
                }
                System.out.println("Partial sort: " + displayList(list));
                System.out.println("");
                step++;
            }
            if (list.contains("Machop")) {
                System.out.println("Step " + step + " : Machop demands to be placed next to the heaviest Pokemon in the lineup to show off its strength.");
                double maxWeight = 0;
                String maxName = "";
                for (int i = 0; i < list.size(); i++) {
                    if (library.pokemon_weight.get(list.get(i)) > maxWeight && !(list.get(i).equals("Machop"))) {
                        maxWeight = library.pokemon_weight.get(list.get(i));
                        maxName = list.get(i);
                    }
                }
                int indexMax = list.indexOf(maxName);
                if (indexMax - 1 >= 0) {
                    String temp = list.get(indexMax - 1);
                    if (!(temp.equals("Eevee") || temp.equals("Pikachu") || temp.equals("Snorlax"))) {
                        int indexMachop = list.indexOf("Machop");
                        list.set(indexMax - 1, "Machop");
                        list.set(indexMachop, temp);
                    }
                }
                else if (indexMax + 1 < list.size()) {
                    String temp = list.get(indexMax + 1);
                    if (!(temp.equals("Eevee") || temp.equals("Pikachu") || temp.equals("Snorlax"))) {
                        int indexMachop = list.indexOf("Machop");
                        list.set(indexMax + 1, "Machop");
                        list.set(indexMachop, temp);
                    }
                }else{
                    System.out.println("These pokemons are impossible to sort to fulfill all pokemon's requirements.");
                    return;
                }
                System.out.println("Partial sort: " + displayList(list));
                System.out.println("");
                step++;
            }

            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("Final Sorted List: " + displayList(list));
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("Your Pokemon are now sorted! Enjoy your adventure in the Safari Zone!");
            System.out.printf("+%s+\n","-".repeat(90));
            break;
        }
    }
    public static boolean CheckInput(ArrayList<String> list) {
        boolean tf = false;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if(library.pokemonhp.containsKey(list.get(i))){
                count++;
            }
        }
        if (count == list.size()) {
            tf = true;
        }
        return tf;
    }

    public static String displayList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i)).append(", ");
        }
        sb.append(list.get(list.size() - 1));
        return sb.toString();
    }

    public static void displayMap(Player player) {
        switch(player.findCurrentCity()){
            case "Pallet Town":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[**Pallet Town**]          |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Viridian City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[**Viridian City**]        |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Pewter City":
                System.out.println("Map of Kanto:");
                System.out.println("[**Pewter City**]-------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Cerulean City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]---------------------[**Cerulean City**]---------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Saffron City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]--[**Saffron City**]---[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Celadon City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |          [**Celadon City**]--[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Lavender Town":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]---[**Lavender Town**]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Vermillion City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |      [**Vermillion City**]-------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Fuschia City":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |           [**Fuchsia City**]-------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[Cinnabar Island]----------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
            case "Cinnabar Island":
                System.out.println("Map of Kanto:");
                System.out.println("[Pewter City]-----------------------[Cerulean City]-----------------|");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |                                    |                       |");
                System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
                System.out.println("       |                   |                |                       |");
                System.out.println("[Viridian City]            |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |                |                       |");
                System.out.println("       |                   |        [Vermillion City]---------------|");
                System.out.println("       |                   |                                        |");
                System.out.println("[Pallet Town]              |                                        |");
                System.out.println("       |                   |                                        |");
                System.out.println("       |             [Fuchsia City]---------------------------------|");
                System.out.println("       |                   |");
                System.out.println("       |                   |");
                System.out.println("[**Cinnabar Island**]------|");
                System.out.printf("+%s+\n","-".repeat(90));
                break;
        }
        
    }
}

    

