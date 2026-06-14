package methods;

public class Filme {
    private int id;
    private String title, vote_average, vote_count, 
    status, release_date, 
    revenue, runtime, adult, budget, 
    imdb_id, original_language, original_title, 
    popularity, poster_path, genres;

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