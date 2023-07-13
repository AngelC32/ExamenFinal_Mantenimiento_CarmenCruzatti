import java.util.HashMap;
import java.util.Map;

public class RentalInfo {
  //Mejora duplicación
  public static final String variable="regular";

  public String statement(Customer customer) {

    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", variable));
    movies.put("F002", new Movie("Matrix", variable));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    //Mejora Code smell Complejidad Cognitiva
    Map<String, Double> movieAmounts = new HashMap<>();
    movieAmounts.put(variable, 2.0);
    movieAmounts.put("new", 3.0);
    movieAmounts.put("childrens", 1.5);



    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      Movie movie = movies.get(r.getMovieId());
      String movieCode = movie.getCode();
      
      double baseAmount = movieAmounts.getOrDefault(movieCode, 0.0);
      double thisAmount = baseAmount;

      if (movieCode.equals(variable) && r.getDays() > 2) {
          thisAmount += (r.getDays() - 2) * 1.5;
      } else if (movieCode.equals("childrens") && r.getDays() > 3) {
          thisAmount += (r.getDays() - 3) * 1.5;
      }
      
      frequentEnterPoints++;
      
      if (movieCode.equals("new") && r.getDays() > 2) {
          frequentEnterPoints++;
      }

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
