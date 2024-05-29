
package pokemon_kanto_adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Pokemon_kanto_adventure {


    public static void main(String[] args) {
        library.readallfiles();
        Player a = new Player("Jack");
        /*
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
        a.addPokemon(new Pokemon("Ponyta",15));
        a.addPokemon(new Pokemon("Pikachu",15));
        a.addPokemon(new Pokemon("Victreebel",50));
        */
        title();
        displayStartMenu(a);
        
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
    public static void displayStartMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Player loadedPlayer = null;

        while (running) {
            player = new Player("a");
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.println("Welcome to Pokemon - Kanto Adventures");
            System.out.printf("+%s+\n","-".repeat(90));

            // Check if save files exist
            boolean[] saveExists = new boolean[3];
            for (int i = 0; i < 3; i++) {
                saveExists[i] = saveFileExists("save" + (i + 1) + ".csv");
            }

            // Print the options based on save existence
            System.out.println("[1] Load Game:");
            System.out.println("a. Save 1 - " + (saveExists[0] ? getPlayerName("save1.csv") : "empty") +
                    " b. Save 2 - " + (saveExists[1] ? getPlayerName("save2.csv") : "empty") +
                    " c. Save 3 - " + (saveExists[2] ? getPlayerName("save3.csv") : "empty"));
            System.out.println("[2] Start a new Adventure:");
            if (saveExists[0] || saveExists[1] || saveExists[2]) {
                System.out.println("a. Save 1 - " + (saveExists[0] ? "Override" : "new") +
                        " b. Save 2 - " + (saveExists[1] ? "Override" : "new") +
                        " c. Save 3 - " + (saveExists[2] ? "Override" : "new"));
            } else {
                System.out.println("a. Save 1 - new b. Save 2 - new c. Save 3 - new");
            }
            System.out.println("[3] Exit");
            System.out.printf("+%s+\n","-".repeat(90));
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1a":
                // Load save 1
                loadedPlayer = loadSave("save1.csv");
                if (loadedPlayer != null) {
                    player = loadedPlayer;
                    selectionPanel(player);
                } else {
                    System.out.println("No saved game found.");
                }
                break;
                case "1b":
                // Load save 2
                loadedPlayer = loadSave("save2.csv");
                if (loadedPlayer != null) {
                    player = loadedPlayer;
                    selectionPanel(player);
                } else {
                    System.out.println("No saved game found.");
                }
                break;
                case "1c":
                // Load save 3
                loadedPlayer = loadSave("save3.csv");
                if (loadedPlayer != null) {
                    player = loadedPlayer;
                    selectionPanel(player);
                } else {
                    System.out.println("No saved game found.");
                }
                break;
                case "2a":
                // Start new adventure with save 1
                startNewAdventure(player,"save1.csv");
                selectionPanel(player);
                break;
                case "2b":
                // Start new adventure with save 2
                startNewAdventure(player,"save2.csv");
                selectionPanel(player);
                break;
                case "2c":
                // Start new adventure with save 3
                startNewAdventure(player,"save3.csv");
                selectionPanel(player);
                break;
                case "3":
                exitGame();
                running = false;
                break;
                default:
                System.out.println("Invalid choice! Please choose again.");
                break;
            }
        }
    }
    //print pokemon title when boot game
    public static boolean saveFileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public static String getPlayerName(String filename) {
        Player loadedPlayer = loadSave(filename);
        if (loadedPlayer != null) {
            return loadedPlayer.getName();
        } else {
            return "empty";
        }
    }

    public static void startNewAdventure(Player player, String saveFileName) {
        clearSaveFile(saveFileName);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("OAK: Hello there! Welcome to the world of Pokémon! My name is Oak!");
        System.out.println("People call me the Pokémon Prof! This world is inhabited by creatures");
        System.out.println("called Pokémon! For some people, Pokémon are pets. Others use them for");
        System.out.println("fights. Myself... I study Pokémon as a profession.");
        System.out.println("OAK: First, what is your name?");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println("DEBUG: Player's name set to: " + player.getName());
        System.out.println("OAK: Right! So your name is " + playerName + "! Welcome to the world of Pokemon.");
        System.out.println("It's time to choose your starting Pokémon.");

        // Present starter Pokémon options
        System.out.printf("+%s+\n","-".repeat(90));
        System.out.println("[1] Bulbasaur [Grass - Level 5]");
        System.out.println("[2] Squirtle [Water - Level 5]");
        System.out.println("[3] Charmander [Fire - Level 5]");
        System.out.printf("+%s+\n","-".repeat(90));
        System.out.print("Your choice: ");
        int starterChoice = scanner.nextInt();

        // Handle starter choice
        switch (starterChoice) {
            case 1:
                System.out.println("OAK: You chose Bulbasaur, an amazing choice. Best of luck!");
                player.addPokemon(new Pokemon("Bulbasaur", 5, false, false));
                break;
            case 2:
                System.out.println("OAK: You chose Squirtle, an amazing choice. Best of luck!");
                player.addPokemon(new Pokemon("Squirtle", 5, false, false));
                break;
            case 3:
                System.out.println("OAK: You chose Charmander, an amazing choice. Best of luck!");
                player.addPokemon(new Pokemon("Charmander", 5, false, false));
                break;
            default:
                System.out.println("Invalid choice! Please choose again.");
                return; // Return from method if choice is invalid
        }
        System.out.println("OAK: Oh, and also take these 10 Poke Balls and $1000, Poke Balls can be used to catch wild pokemons and strengthen your team, and you can use money to buy items in Poke Marts!");
        player.obtainitems("Poke Ball", 10);
        player.addMoney(1000);
        save(player, saveFileName);
        player.setSaveLocation(saveFileName);
        System.out.println(player.getSaveLocation());
    }
    public static void clearSaveFile(String saveFileName) {
        try {
            FileWriter writer = new FileWriter(saveFileName, false); // Open the file for writing, overwrite existing content
            PrintWriter pw = new PrintWriter(writer);
            pw.write(""); // Clear the contents by writing an empty string
            pw.close(); // Close the file
            System.out.println("Save file " + saveFileName + " cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the save file: " + e.getMessage());
        }
    }
    //load save after select
    public static Player loadSave(String filename) {
        Player player = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip the header line
            br.readLine();

            String line = br.readLine(); // Read the next line
            if (line != null) {
                String[] data = line.split(",");
                if (data.length >= 40) { // Ensure that there are enough elements in the array
                    String name = data[0];
                    String currentCity = data[1].trim(); // Ensure to trim any leading or trailing whitespaces
                    player = new Player(name);
                    player.setcurrentcity(currentCity); // Update the current city

                    // Load badges
                    String[] badges = new String[8];
                    for (int i = 2; i < 10; i++) {
                        badges[i - 2] = data[i];
                    }
                    player.setbadges(badges);

                    // Load money, rival wins, and battles won
                    player.setMoney(Integer.parseInt(data[10]));
                    player.setRivalRaceWins(Integer.parseInt(data[11]));
                    player.setBattleWon(Integer.parseInt(data[12]));
                    
                    // Parse Pokémon data for up to 6 Pokémon
                    for (int i = 13; i < 37; i += 4) {
                        if (i + 3 < data.length && !data[i].isEmpty() && !data[i + 1].isEmpty() && !data[i + 2].isEmpty() && !data[i + 3].isEmpty()) {
                            String pokemonName = data[i];
                            int pokemonLevel = Integer.parseInt(data[i + 1]);
                            int pokemonHP = Integer.parseInt(data[i + 2]);
                            int pokemonXP = Integer.parseInt(data[i + 3]);
                            // Create and add the Pokémon to the player's team
                            player.addPokemon(new Pokemon(pokemonName, pokemonLevel, pokemonHP, pokemonXP));
                        } else {
                            // If there's no Pokémon data, add a placeholder
                            player.addPokemon(null);
                        }
                    }   
                    
                    // Load items
                    player.getItems().put("Potion", Integer.parseInt(data[37]));
                    player.getItems().put("Super Potion", Integer.parseInt(data[38]));
                    player.getItems().put("Revive", Integer.parseInt(data[39]));
                    player.getItems().put("Poke Ball", Integer.parseInt(data[40]));
                    player.getItems().put("Great Ball", Integer.parseInt(data[41]));
                    player.getItems().put("Ultra Ball", Integer.parseInt(data[42]));
                    player.getItems().put("X Attack", Integer.parseInt(data[43]));
                    player.getItems().put("X Defend", Integer.parseInt(data[44]));
                    player.getItems().put("X Speed", Integer.parseInt(data[45]));
                    for (int i = 46; i < data.length; i += 4) {
                        if (!data[i].isEmpty() && !data[i + 1].isEmpty() && !data[i + 2].isEmpty() && !data[i + 3].isEmpty()) {
                            String pokemonName = data[i];
                            int pokemonLevel = Integer.parseInt(data[i + 1]);
                            int pokemonHP = Integer.parseInt(data[i + 2]);
                            int pokemonXP = Integer.parseInt(data[i + 3]);
                            
                            // Create and add the Pokémon to the player's PC
                            player.getPC().add(new Pokemon(pokemonName, pokemonLevel, pokemonHP, pokemonXP));
                        } else {
                            // If there's no Pokémon data, add a placeholder
                            player.getPC().add(null);
                        }
                    }
                    player.setSaveLocation(filename);
                } else {
                    System.err.println("Insufficient data in the save file.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the save file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data in the save file: " + e.getMessage());
        }
        return player;
    }
    
    public static void save(Player player, String saveFileName) {
        try {
            FileOutputStream fw = new FileOutputStream(saveFileName, false);
            PrintWriter pw = new PrintWriter(fw);   // Column headers for the CSV file
            // Player information
            String playerInfoHeaders = "PlayerName,CurrentCity,Badges1,Badges2,Badges3,Badges4,Badges5,Badges6,Badges7,Badges8,Money,RivalWins,BattlesWon,";
            
            // Pokémon team headers
            String pokemonHeaders = "";
            for (int i = 1; i <= 6; i++) {
                pokemonHeaders += "Poke" + i + "Name,Poke" + i + "Level,Poke" + i + "CurrentHP,Poke" + i + "XP,";
            }

            // Items headers
            String itemsHeaders = "Potion,SuperPotion,Revive,PokeBall,GreatBall,UltraBall,XAttack,XDefend,XSpeed,";

            // PC Pokémon headers
            String pcHeaders = "";
            int pcSize = player.getPC().size();
            for (int i = 1; i <= pcSize; i++) {
                pcHeaders += "PC" + i + "Name,PC" + i + "Level,PC" + i + "CurrentHP,PC" + i + "XP,";
            }

            // Concatenate all headers
            String columnHeaders = playerInfoHeaders + pokemonHeaders + itemsHeaders + pcHeaders;
            // Write the column headers to the CSV file
            pw.println(columnHeaders);
            
            // Save player's name, current city, badges, money, rival race wins, and battles won
            pw.print(player.getName()+ "," + player.findCurrentCity() + "," +
                       formatBadges(player.getbadges()) + "," + player.findMoney() + "," +
                       player.getrivalwins() + "," + player.getvictories());
        
             // Save player's Pokémon team
            savePokemon(pw, player.findPoke1());
            savePokemon(pw, player.findPoke2());
            savePokemon(pw, player.findPoke3());
            savePokemon(pw, player.findPoke4());
            savePokemon(pw, player.findPoke5());
            savePokemon(pw, player.findPoke6());        
            // Save player's items
            pw.print("," + player.getItems().get("Potion") + "," + player.getItems().get("Super Potion") + "," +
                       player.getItems().get("Revive") + "," + player.getItems().get("Poke Ball") + "," +
                       player.getItems().get("Great Ball") + "," + player.getItems().get("Ultra Ball") + "," +
                       player.getItems().get("X Attack") + "," + player.getItems().get("X Defend") + "," +
                       player.getItems().get("X Speed"));
        
            // Save PC Pokémon
            for (Pokemon pokemon : player.getPC()) {
                if (pokemon != null) {
                    pw.print("," + pokemon.findname() + "," + pokemon.findlvl() + "," + pokemon.findcurrenthp() + "," + pokemon.findcurrentxp());
                } else {
                pw.print(",,,,"); // If no Pokémon, print empty values
                }
            }
            pw.close();
            System.out.println("Game saved successfully.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }



        // Helper method to save Pokémon data
        public static void savePokemon(PrintWriter pw, Pokemon pokemon) {
            if (pokemon != null) {
                pw.print("," + pokemon.findname() + "," + pokemon.findlvl() + "," + pokemon.findcurrenthp() + "," + pokemon.findcurrentxp());
            } else {
                pw.print(",,,,"); // If no Pokémon, print empty values
            }
        }

        // Helper method to format badges array
    public static String formatBadges(String[] badges) {
        StringBuilder formattedBadges = new StringBuilder();
        for (String badge : badges) {
            formattedBadges.append(badge).append(",");
        }
        // Remove the trailing comma
        return formattedBadges.substring(0, formattedBadges.length() - 1);
    }




    public static void saveAndExit(Player player) {
        save(player, player.getSaveLocation());
        exitGame();
    }

    public static void exitGame() {
        System.out.println("Exiting game...");
        System.exit(0); // Exit the game
    }
    public static void guides(){
        Scanner input = new Scanner(System.in);
        loop:
        while(true){
            System.out.println("+--------------------Guides--------------------+");
            System.out.println("1. List of all pokemons");
            System.out.println("2. Typings and their weaknessess and resistance");
            System.out.println("3. Stats, move order and battle selections");
            System.out.println("4. Catching pokemons");
            System.out.println("5. Gym battles, trainer battles and wild pokemon battles");
            System.out.println("6. Back");
            String choice = input.nextLine();
            switch(choice){
                case "1":
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println(library.pokemonhp.keySet());
                    break;
                case "2":
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("1.normal:");
                    System.out.println("  resistance - ghost(0x damage)");
                    System.out.println("  weakness   - fighting(2x damage)");
                    System.out.println("2.fire:");
                    System.out.println("  resistance - fire,bug,grass,steel(0.5x damage)");
                    System.out.println("  weakness   - water,ground,rock(2x damage)");
                    System.out.println("3.water:");
                    System.out.println("  resistance - fire,water,steel(0.5x damage)");
                    System.out.println("  weakness   - electric,grass(2x damage)");
                    System.out.println("4.electric:");
                    System.out.println("  resistance - electric,flying,steel(0.5x damage)");
                    System.out.println("  weakness   - ground(2x damage)");
                    System.out.println("5.grass:");
                    System.out.println("  resistance - water,electric,grass,ground(0.5x damage)");
                    System.out.println("  weakness   - fire,poison,flying(2x damage)");
                    System.out.println("6.fighting:");
                    System.out.println("  resistance - bug,rock,dark(0.5x damage)");
                    System.out.println("  weakness   - flying,psychic(2x damage)");
                    System.out.println("7.poison:");
                    System.out.println("  resistance - grass,fighting,poison,bug(0.5x damage)");
                    System.out.println("  weakness   - ground,psychic(2x damage)");
                    System.out.println("8.ground:");
                    System.out.println("  resistance - poison,rock(0.5x damage); electric(0x damage)");
                    System.out.println("  weakness   - water,grass(2x damage)");
                    System.out.println("9.flying:");
                    System.out.println("  resistance - grass,fighting,bug(0.5x damage); ground(0x damage)");
                    System.out.println("  weakness   - electric,rock(2x damage)");
                    System.out.println("10.psychic:");
                    System.out.println("  resistance - fighting,psychic(0.5x damage)");
                    System.out.println("  weakness   - bug,ghost,dark(2x damage)");
                    System.out.println("11.bug:");
                    System.out.println("  resistance - grass,fighting,ground(0.5x damage)");
                    System.out.println("  weakness   - fire,flying,rock(2x damage)");
                    System.out.println("12.rock:");
                    System.out.println("  resistance - normal,fire,poison,flying(0.5x damage)");
                    System.out.println("  weakness   - water,grass,fighting,ground,steel(2x damage)");
                    System.out.println("13.steel:");
                    System.out.println("  resistance - normal,grass,flying,psychic,bug,rock,ghost,dark,steel(0.5x damage); poison(0x damage)");
                    System.out.println("  weakness   - fire,fighting,ground(2x damage)");
                    System.out.println("Tips, when a pokemon uses a move that damages its opponent that have the same move type as either one of its type, the damage will increase by 50%");
                    break;
                case "3":
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("Stats:");
                    System.out.println("You and your opponents' pokemons will have attack, defense, and speed stats during battles");
                    System.out.println("The higher the attack stat, the higher the damage output of the pokemon and vice versa");
                    System.out.println("The higher the defense stat, the lower the damage received from attacks and vice versa");
                    System.out.println("Speed stats determines who moves first, if the move order of both pokemons are the same");
                    System.out.println("Each pokemon have their own speed values, and this value could be altered during battles through the speed stat");
                    System.out.println("After the speed is altered, if both pokemons have the same speed, a dice roll of 50/50 will happen, which means each pokemon have a 50% chance to move first");
                    System.out.println("If a pokemon faints before its move, it will not use that move");
                    System.out.println("Whenever you or your opponent switches pokemon, all these stats will reset, so when your stats is lowered to much, try switching pokemons to clear that debuff");
                    System.out.println("");
                    System.out.println("Move Order:");
                    System.out.println("As mentioned in the 'Stats' part, there is a move order for all the moves, some moves have higher move order, which means the pokemon will use that move first");
                    System.out.println("While some move have lower move order, which mostly powerful moves.");
                    System.out.println("If a pokemon uses a move with a higher move order, the pokemon will move first regardless of both pokemons' speed stat and vice versa");
                    System.out.println("");
                    System.out.println("Battle selections:");
                    System.out.println("You could choose to use items or swap pokemons during battles");
                    System.out.println("Both actions will be executed first before your opponent uses a move, and your pokemon will not be able to move if you do any of those actions");
                    System.out.println("To be specific, if you use an item on your pokemon, your pokemon is not able to make a move while your opponent is able to do so");
                    System.out.println("If you attempt to catch a wild pokemon but it snapped out of the Poke Ball, the wild pokemon will still make a move, while your pokemon stays still");
                    System.out.println("If you switched a pokemon, your pokemon that is just switched up will be the one who takes effect of the opponents' move");
                    System.out.println("However, if you switched because of your battling pokemon is fainted, it is after the round, so this switching is safe and your opponent pokemon will not make a move");
                    break;
                case "4":
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("You can use Poke Balls, Great Balls and Ultra Balls in your bag to catch wild pokemons during battles with them");
                    System.out.println("The lower the wild pokemons' hp the higher the chance of catching it.");
                    System.out.println("However, you cannot catch another trainer's pokemon, please keep that in mind");
                    System.out.println("You can buy these balls in the Poke Mart");
                    System.out.println("Good Luck!");
                    break;
                case "5":
                    System.out.printf("+%s+\n","-".repeat(90));
                    System.out.println("Gym battles:");
                    System.out.println("There are 8 gyms in this game, with the difficulty of the gyms from low to high as below");
                    System.out.println("Pewter City Gym < Cerulean Gym < Vermilion Gym < Celadon Gym < Fuchsia City Gym = Saffron City Gym < Cinnabar Island Gym < Viridian City Gym");
                    System.out.println("To complete your journey, you have to defeat all 8 gym leaders and obtain all 8 gym badges from them");
                    System.out.println("With Giovanni in Viridian City Gym as the strongest gym leader, you have to obtain all 7 other gym badges to prove that you are a worthy opponent for the final boss");
                    System.out.println("All gyms can only be challenged once");
                    System.out.println("");
                    System.out.println("Trainer battles:");
                    System.out.println("Aside from gym battles, there are trainers that would like to battle you, you can defeat their pokemons to earn xp and money");
                    System.out.println("These trainers can be challenged more than one time");
                    System.out.println("");
                    System.out.println("Wild pokemon battles:");
                    System.out.println("You can choose to fight different wild pokemons in different areas");
                    System.out.println("The wild pokemon you encounter and their levels will be random based on the areas");
                    System.out.println("You can use pokeballs to catch them or defeat them to gain xp for your pokemons");
                    System.out.println("There is a special encounter, which is Snorlax, which you can choose in some areas");
                    System.out.println("Snorlax is a super powerful pokemon, with high hp, strong moves but super slow speed");
                    System.out.println("So prepare well before you attempt to catch it as it might wipe out your whole team if not careful");
                    break;
                case "6":
                    break loop;
                default:
                    System.out.println("Invalid choice, please choose again.");
            }
        }
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                System.out.println("A wild " + wild_pokemon + " appeared! [ " + wild_lvl + " ] ");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                        Battle gymbattle = new Battle(player,"Giovanni");
                        
                        if(gymbattle.getwin()){
                            System.out.println("Giovanni: You are sure the strongest trainer in this region, here is the Earth Badge. It is evidence of your mastery as a Pokémon Trainer.");
                            player.obtainbadge("Earth Badge");
                        }
                        
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
            
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[0].equals("Boulder Badge")){
                System.out.println("You are now challenging Gym Leader Brock!");
                Battle gymbattle = new Battle(player,"Brock");
                if(gymbattle.getwin()){
                    player.obtainbadge("Boulder Badge");
                    System.out.println("Brock: I took you for granted, and so I lost. As proof of your victory, I confer on you this...the official Boulder Badge.");
                }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Caterpie","Metapod","Pikachu"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(3,7);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight rick - caterpie lvl 6, caterpie lvl 6
                        Battle trainerbattle = new Battle(player,"Rick");
                        break;
                    case 'b':
                        //fight anthony - caterpie lvl 9
                        trainerbattle = new Battle(player,"Anthony");
                        break;
                    case 'c':
                        //fight Charlie - pikachu lvl 9
                        trainerbattle = new Battle(player,"Charlie");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
                System.out.println("Invalid choice! Please choose again");
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[1].equals("Cascade Badge")){
                System.out.println("You are now challenging Gym Leader Misty!");
                Battle gymbattle = new Battle(player,"Misty");
                if(gymbattle.getwin()){
                    player.obtainbadge("Cascade Badge");
                    System.out.println("Misty: Wow! You're too much, all right! You can have the Cascade Badge to show that you beat me.");
                }
                }else{
                    System.out.println("You have already challenged this gym");
                }
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Sandshrew", "Geodude", "Onix"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(8,13);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight grunt - machop lvl 17, sandshrew lvl 17
                        Battle trainerbattle = new Battle(player,"Rocket Grunt");
                        break;
                    case 'b':
                        //fight marcos - 2x geodude lvl 11, onix lvl 11
                        trainerbattle = new Battle(player,"Marcos");
                        break;
                    case 'c':
                        //fight Jovan - voltorb lvl 14, magnemite lvl 14
                        trainerbattle = new Battle(player,"Jovan");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[5].equals("Marsh Badge")){
                System.out.println("You are now challenging Gym Leader Sabrina!");
                Battle gymbattle = new Battle(player, "Sabrina");
                if(gymbattle.getwin()){
                    player.obtainbadge("Marsh Badge");
                    System.out.println("Sabrina: This loss shocks me! But a loss is a loss. I admit I didn't work hard enough to win. You earned the Marsh Badge.");
                }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Oddish", "Bellsprout", "Growlithe", "Abra"};
                int wild_choice = r.nextInt(4);
                int wild_lvl = r.nextInt(11,17);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight ricky - wartortle lvl 30
                        Battle trainerbattle = new Battle(player,"Ricky");
                        break;
                    case 'b':
                        //fight Jeff - 2x raticate lvl 29
                        trainerbattle = new Battle(player,"Jeff");
                        break;
                    case 'c':
                        //fight Elijah - Butterfree lvl 30
                        trainerbattle = new Battle(player,"Elijah");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[3].equals("Rainbow Badge")){
                    System.out.println("You are now challenging Gym Leader Erika!");
                    Battle gymbattle = new Battle(player,"Erika");
                    if(gymbattle.getwin()){
                        player.obtainbadge("Rainbow Badge");
                        System.out.println("Erika: Oh! I concede defeat. You are remarkably strong. I must confer on you the Rainbow Badge.");
                    }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Koffing", "Grimer", "Machop","Ponyta"};
                int wild_choice = r.nextInt(4);
                int wild_lvl = r.nextInt(20,24);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if(choice.equals("6")){
                System.out.println("A wild Snorlax is blocking the road!");
                Pokemon wild = new Pokemon("Snorlax",30,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight lao - grimer lvl 27, koffing lvl 27
                        Battle trainerbattle = new Battle(player,"Lao");
                        break;
                    case 'b':
                        //fight koji - machop lvl 27, mankey lvl 27
                        trainerbattle = new Battle(player,"Koji");
                        break;
                    case 'c':
                        //fight lea - rapidash lvl 27
                        trainerbattle = new Battle(player,"Lea");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if(choice.equals("6")){
                System.out.println("A wild Snorlax is blocking the road!");
                Pokemon wild = new Pokemon("Snorlax",30);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='7'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight luca - voltorb, electrode lvl 29
                        Battle trainerbattle = new Battle(player,"Luca");
                        break;
                    case 'b':
                        //fight justin - Nidoran-M,Nidoran-F, lvl 29
                        trainerbattle = new Battle(player,"Justin");
                        break;
                    case 'c':
                        //fight tower grunt - rattata, raticate lvl 27
                        trainerbattle = new Battle(player,"Tower Grunt");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[2].equals("Thunder Badge")){
                    System.out.println("You are now challenging Gym Leader Lt. Surge!");
                    Battle gymbattle = new Battle(player,"Lt. Surge");
                    if(gymbattle.getwin()){
                        player.obtainbadge("Thunder Badge");
                        System.out.println("Lt. Surge: Now that's a shocker! You're the real deal, kid! Fine, then, take the Thunder Badge!");
                    }
                }else{
                    System.out.println("You have already challenged this gym");
                }
                
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Diglett", "Jigglypuff", "Eevee"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(15,23);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight yasu - 2x rattata, raticate lvl 17
                        Battle trainerbattle = new Battle(player,"Yasu");
                        break;
                    case 'b':
                        //fight dave - nidoran-m,nidorino lvl 18
                        trainerbattle = new Battle(player,"Dave");
                        break;
                    case 'c':
                        //fight bernie - 2x magnemite, magneton lvl 18
                        trainerbattle = new Battle(player,"Bernie");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[4].equals("Soul Badge")){
                    System.out.println("You are now challenging Gym Leader Koga!");
                    Battle gymbattle = new Battle(player,"Koga");
                    if(gymbattle.getwin()){
                        player.obtainbadge("Soul Badge");
                        System.out.println("Koga: Humph! You have proven your worth! Here! Take the Soul Badge!");
                    }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Grimer", "Rattata", "Raticate"};
                int wild_choice = r.nextInt(3);
                int wild_lvl = r.nextInt(22,30);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight charles - koffing, weezing lvl 39
                        Battle trainerbattle = new Battle(player,"Charles");
                        break;
                    case 'b':
                        //fight jacob - charmeleon lvl 39
                        trainerbattle = new Battle(player,"Jacob");
                        break;
                    case 'c':
                        //fight connie - 3x staryu lvl 33
                        trainerbattle = new Battle(player,"Connie");
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }else if(choice.equals("7")){
                safari.SafariZone();
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
            
            System.out.println("     a.Map     b.Pokemons     c.Bag     d.Badges     e.Profile     f.Guides     g.PC     h.Save and Exit");
            System.out.printf("+%s+\n","-".repeat(90));

            // Get player's choice
            System.out.print("Your choice: ");
            String choice = input.nextLine();
        
            // Handle player's choice
            if(choice.length()!=0){
            if (choice.charAt(0)=='1'&&choice.length()==2) {
                if(choice.charAt(1)>='a'&&choice.charAt(1)<'a'+neighboringCities.size()){
                    int cityIndex = choice.charAt(1) - 'a';
                    String nextCity = neighboringCities.get(cityIndex);
                    player.movetoCity(nextCity);
                }else{
                    System.out.println("Invalid choice! Please choose again.");
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
                if(!player.getbadges()[6].equals("Volcano Badge")){
                    System.out.println("You are now challenging Gym Leader Blaine!");
                    Battle gymbattle = new Battle(player,"Blaine");
                    if(gymbattle.getwin()){
                        player.obtainbadge("Volcano Badge");
                        System.out.println("Blaine: I have burned down to nothing! Not even ashes remain! You have earned the Volcano Badge.");
                    }
                }else{
                    System.out.println("You have already challenged this gym.");
                }
            
            }else if(choice.equals("5")){
                Random r = new Random();
                String[]wilds = {"Staryu", "Tangela"};
                int wild_choice = r.nextInt(2);
                int wild_lvl = r.nextInt(17,29);
                String wild_pokemon = wilds[wild_choice];
                System.out.println("A wild " + wild_pokemon + " appeared!");
                Pokemon wild = new Pokemon(wild_pokemon,wild_lvl,true);
                Battle wildbattle = new Battle(player,wild);
            }else if (choice.charAt(0)=='6'&&choice.length()==2) {
                char player_choice = choice.charAt(1);
                switch(player_choice){
                    case 'a':
                        //fight lil - starmie lvl 33
                        Battle trainerbattle = new Battle(player,"Lil");
                        break;
                    case 'b':
                        //fight jack - starmie lvl 37
                        trainerbattle = new Battle(player,"Jack");
                        break;
                    case 'c':
                        //fight jerome - staryu lvl 33, wartortle lvl 33
                        trainerbattle = new Battle(player,"Jerome");
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
                        player.alterPC(player);
                        break;
                    case 'h':
                        save(player, player.getSaveLocation());
                        return false;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } else {
                System.out.println("Invalid choice! Please choose again.");
            }
            }else{
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
                        System.out.println("+--------------------Buy--------------------+");
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
                                System.out.println("+--------------------Poke Ball--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 2:
                                System.out.println("+--------------------Great Ball--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 3:
                                System.out.println("+--------------------Ultra Ball--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 4:
                                System.out.println("+--------------------Potion--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 5:
                                System.out.println("+--------------------Super Potion--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 6:
                                System.out.println("+--------------------Hyper Potion--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 7:
                                System.out.println("+--------------------Max Potion--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 8:
                                System.out.println("+--------------------X Attack--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 9:
                                System.out.println("+--------------------X Defend--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 10:
                                System.out.println("+--------------------X Speed--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 11:
                                System.out.println("+--------------------Revive--------------------+");
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
                                            System.out.println("Here you go, thank you for buying!");
                                        }
                                    }
                                }else{
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                System.out.printf("+%s+\n","-".repeat(90));
                                break;
                            case 12:
                                break;
                            default:
                                System.out.println("Invalid choice! Please choose again.");
                            }
                        }else{
                            System.out.println("Invalid choice! Please choose again.");
                        }
                    }else if(action.equals("2")){    
                        System.out.println("+--------------------Sell--------------------+");
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
                                System.out.println("+--------------------Poke Ball--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 2:
                                System.out.println("+--------------------Great Ball--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 3:
                                System.out.println("+--------------------Ultra Ball--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 4:
                                System.out.println("+--------------------Potion--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 5:
                                System.out.println("+--------------------Super Potion--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 6:
                                System.out.println("+--------------------Hyper Potion--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 7:
                                System.out.println("+--------------------Max Potion--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 8:
                                System.out.println("+--------------------X Attack--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 9:
                                System.out.println("+--------------------X Defend--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 10:
                                System.out.println("+--------------------X Speed--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 11:
                                System.out.println("+--------------------Revive--------------------+");
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
                                    System.out.println("Invalid choice! Please choose again.");
                                }
                                break;
                            case 12:
                                break;
                            default:
                                System.out.println("Invalid choice! Please choose again.");
                            }
                        }else{
                            System.out.println("Invalid choice! Please choose again.");
                        }
                    }else if(action.endsWith("3")){
                        System.out.println("Thank you for coming! Hope you have a nice day!");
                        break;
                    }else{
                        System.out.println("Invalid choice! Please choose again.");
                    }
                }
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

    

