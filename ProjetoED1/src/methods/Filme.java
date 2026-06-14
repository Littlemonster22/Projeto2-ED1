package methods;

public class Filme {
    private int id;
    private String title;
    private String vote_average;
    private String vote_count;
    private String status;
    private String release_date;
    private String revenue;
    private String runtime;
    private String adult;
    private String budget;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String popularity;
    private String poster_path;
    private String genres;

    public Filme(int id, String title, String vote_average, String vote_count, String status,
                 String release_date, String revenue, String runtime, String adult, String budget,
                 String imdb_id, String original_language, String original_title, String popularity,
                 String poster_path, String genres) {
        this.id = id;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.status = status;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.adult = adult;
        this.budget = budget;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.genres = genres;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVote_average() { return vote_average; }
    public void setVote_average(String vote_average) { this.vote_average = vote_average; }

    public String getVote_count() { return vote_count; }
    public void setVote_count(String vote_count) { this.vote_count = vote_count; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRelease_date() { return release_date; }
    public void setRelease_date(String release_date) { this.release_date = release_date; }

    public String getRevenue() { return revenue; }
    public void setRevenue(String revenue) { this.revenue = revenue; }

    public String getRuntime() { return runtime; }
    public void setRuntime(String runtime) { this.runtime = runtime; }

    public String getAdult() { return adult; }
    public void setAdult(String adult) { this.adult = adult; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getImdb_id() { return imdb_id; }
    public void setImdb_id(String imdb_id) { this.imdb_id = imdb_id; }

    public String getOriginal_language() { return original_language; }
    public void setOriginal_language(String original_language) { this.original_language = original_language; }

    public String getOriginal_title() { return original_title; }
    public void setOriginal_title(String original_title) { this.original_title = original_title; }

    public String getPopularity() { return popularity; }
    public void setPopularity(String popularity) { this.popularity = popularity; }

    public String getPoster_path() { return poster_path; }
    public void setPoster_path(String poster_path) { this.poster_path = poster_path; }

    public String getGenres() { return genres; }
    public void setGenres(String genres) { this.genres = genres; }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", vote_count='" + vote_count + '\'' +
                ", status='" + status + '\'' +
                ", release_date='" + release_date + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runtime='" + runtime + '\'' +
                ", adult='" + adult + '\'' +
                ", budget='" + budget + '\'' +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", popularity='" + popularity + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}