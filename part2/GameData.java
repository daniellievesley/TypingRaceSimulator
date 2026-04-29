package part2;

public class GameData {
    private String len;
    private String passage;
    private Integer seats;
    private boolean auto;
    private boolean caffeine;
    private boolean night;

    public String getLen(){
        return this.len;
    }

    public String getPassage(){
        return this.passage;
    }

    public Integer getSeats(){
        return this.seats;
    }

    public Boolean getAuto(){
        return this.auto;
    }

    public Boolean getCaffeine(){
        return this.caffeine;
    }

    public Boolean getNight(){
        return this.night;
    }

    public GameData(String len, String passage, Integer seats, boolean auto, boolean caffeine, boolean night) {
        this.len = len;
        this.passage = passage;
        this.seats = seats;
        this.auto = auto;
        this.caffeine = caffeine;
        this.night = night;
    }
}