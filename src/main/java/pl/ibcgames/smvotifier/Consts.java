package pl.ibcgames.smvotifier;

public final class Consts {

    public static final String PLUGIN_NAME = "SM-Votifier";
    public static final String PLUGIN_IDENTIFIER = "smvotifier";
    public static final String PLUGIN_AUTHOR = "serwery-minecraft.pl";
    public static final String WEBPAGE_URL = "https://serwery-minecraft.pl";

    public static final String CONFIG_IDENTIFIER_NAME = "identyfikator";
    public static final String CONFIG_REQUIRE_PERMISSION_NAME = "wymagaj_uprawnien";
    public static final String CONFIG_COMMANDS_NAME = "komendy";

    public static final String DEFAULT_IDENTIFIER_VALUE = "tutaj_wpisz_identyfikator";
    public static final String PLAYER_PLACEHOLDER = "{GRACZ}";

    public static final String COMMAND_VOTE_NAME = "sm-glosuj";
    public static final String COMMAND_REWARD_NAME = "sm-nagroda";
    public static final String COMMAND_TEST_NAME = "sm-test";
    public static final String COMMAND_RELOAD_NAME = "sm-reload";

    public static final String NO_CONFIG_MESSAGE = "Nie znaleziono pliku konfiguracyjnego. Wygenerowano pusty plik";
    public static final String PLACEHOLDERAPI_FOUND_MESSAGE = "Znaleziono PlaceholderAPI, ładowanie zmiennych...";

    public static final String ERROR_MESSAGE = "Error: ";
    public static final String LOADING_DATA_MESSAGE = "Trwa pobieranie danych...";
    public static final String CHECKING_VOTE_MESSAGE = "Sprawdzamy Twój głos, proszę czekać...";
    public static final String CONFIG_RELOADED_MESSAGE = "Konfiguracja pluginu została przeładowana";

    public static final String ERROR_DOWNLOAD_SERVER_DATA_MESSAGE = "Nie udało się pobrać danych serwera";
    public static final String ERROR_DOWNLOAD_VOTE_DATA_MESSAGE = "Nie udało się pobrać danych o głosowaniu";
    public static final String ERROR_DOWNLOAD_VOTE_DATA_PLAYER_MESSAGE = ERROR_DOWNLOAD_VOTE_DATA_MESSAGE + ", spróbuj później";
    public static final String ERROR_DOWNLOAD_USER_VOTE_DATA_MESSAGE = "Nie udało się sprawdzić głosu gracza {}";
    public static final String ERROR_DOWNLOAD_USER_VOTE_DATA_PLAYER_MESSAGE = "Nie udało się pobrać danych, spróbuj później";
    public static final String ERROR_TEST_REWARD_MESSAGE = "Nie udało się przetestować nagrody";
    public static final String COMMAND_TIMEOUT_MESSAGE_1 = "Ta komenda może być użyta za ";
    public static final String COMMAND_TIMEOUT_MESSAGE_2 = " sekund";
    public static final String USER_CAN_NOT_CLAIM_REWARD_MESSAGE = "Nie udało się odebrać nagrody, spróbuj później";

    public static final String NO_IDENTIFIER_MESSAGE_1 = "Brak identyfikatora serwera w konfiguracji " + PLUGIN_NAME;
    public static final String NO_IDENTIFIER_MESSAGE_2 = "Więcej informacji znajdziesz pod adresem:";
    public static final String NO_IDENTIFIER_MESSAGE_3 = WEBPAGE_URL + "/konfiguracja-pluginu";

    public static final String TEST_REWARD_MESSAGE_1 = "Ta komenda pozwala na przetestowanie nagrody";
    public static final String TEST_REWARD_MESSAGE_2 = "Aby sprawdzić połączenie pluginu z listą serwerów";
    public static final String TEST_REWARD_MESSAGE_3 = "po prostu odbierz nagrodę za pomocą /" + COMMAND_REWARD_NAME;

    private Consts() {
    }
}
