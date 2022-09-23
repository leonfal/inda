import java.time.LocalDate;

public class EventPost extends Post{

  //the additional fields of EventPost
  private String title;
  private String location;
  private LocalDate date = LocalDate.of(2022, 1, 14);

  /**
   * Constructs a new post with author, title and location.
   * @param author
   * @param title
   * @param location
   * @param date
   */
  public EventPost(String author, String title, String location) {
    super(author);
    this.title = title;
    this.location = location;
  }

  public void display() {
    System.out.println("  [" + title + "]");
    System.out.println("  @" + location + ", " + date);
    System.out.println("*********************");
    super.display();
    }
}
