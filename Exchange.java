import java.util.Map;

public class Exchange {
    private boolean result;
    private String documentation;
    private String terms_of_use;
    private String time_last_update_unix;
    private String time_last_update_utc;
    private int time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private Map<String, Double> conversion_rates;

    // Constructor, getters y setters

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }
}
