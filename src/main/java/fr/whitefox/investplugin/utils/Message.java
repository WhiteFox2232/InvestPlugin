package fr.whitefox.investplugin.utils;

public class Message {

    // Global
    public static final String ERROR_NOT_A_PLAYER = "§cVeuillez effectuer cette commande en tant que joueur !";

    // Chunk Event
    public static final String TITLE_JOIN_ZONE = "§aVous entrez dans une zone d'invest";
    public static final String CHAT_LEAVE_INTERRUPT_INVEST = "§cVous avez quitté la zone ! Votre investissement a été annulé.";
    public static final String TITLE_LEAVE_ZONE = "§cVous sortez d'une zone d'invest";

    // Invest Command
    public static final String CHAT_NOT_IN_INVEST_ZONE = "§cVous devez être dans une zone d'investissement !";
    public static final String CHAT_INVEST_STARTED = "§aInvestissement lancé !";
    public static final String CHAT_BAD_ARGS_INVEST = "§cUtilisez la commande correctement !";

    // Config Command
    public static final String CHAT_BAD_ARGS_CONFIG = "§cUsage : /config area";
    public static final String CHAT_COORDINATES = "§dCoordinates : ";
}
