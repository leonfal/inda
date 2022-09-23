import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  mik
 * @version 0.1
 */
public class SalesItemTest
{
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test that a comment can be added, and that the comment count is correct afterwards.
     */
    @Test
    public void testAddComment()
    {
        SalesItem salesIte1 = new SalesItem("Brain surgery for Dummies", 21998);
        assertEquals(true, salesIte1.addComment("James Duckling", "This book is great. I perform brain surgery every week now.", 4));
        assertEquals(1, salesIte1.getNumberOfComments());
    }

    /**
     * Test that a comment using an illegal rating value is rejected.
     */
    @Test
    public void testIllegalRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Complete Idiots, Vol 2", 19900);
        assertEquals(false, salesIte1.addComment("Joshua Black", "Not worth the money. The font is too small.", -5));
    }

    /**
     * Test that a sales item is correctly initialised (name and price).
     */
    @Test
    public void testInit()
    {
        SalesItem salesIte1 = new SalesItem("test name", 1000);
        assertEquals("test name", salesIte1.getName());
        assertEquals(1000, salesIte1.getPrice());
    }

    @Test
    public void addComment()
    {
        SalesItem salesIte1 = new SalesItem("Brain Surgery for Dummies.", 9899);
        assertEquals(true, salesIte1.addComment("Fred", "Great - I perform brain surgery every week now!", 4));
    }

    // Exercise 7.15
    @Test
    public void testAddCommentReturnsFalseWhenSameAuthor(){
        SalesItem salesIte1 = new SalesItem("Datateknik för dataloger", 1999);
        salesIte1.addComment("Lelle", "hejsan", 1);
        assertFalse(salesIte1.addComment("Lelle", "Annan text", 2));
    }

    // Exercise 7.16
    @Test
    public void testIfRangeIsCorrect(){
        SalesItem salesIte1 = new SalesItem("Datateknik för dataloger", 2000);
        assertFalse(salesIte1.addComment("Lelle", "Tjena", 0));
        assertFalse(salesIte1.addComment("Leon", "HEJSAN", 6));
    }

    // Exercise 7.18
    @Test
    public void testAuthorRatingStoredCorrectly(){
        SalesItem salesite1 = new SalesItem("Datateknik för dataloger", 2000);
        salesite1.addComment("Leon", "Good", 1);
        assertEquals("Leon", salesite1.findMostHelpfulComment().getAuthor());
        assertEquals(1,salesite1.findMostHelpfulComment().getRating());
    }

    // Exercise 7.18
    @Test
    public void testUpVoteWorks(){
        SalesItem salesIte1 = new SalesItem("Datateknik för dataloger", 123);
        salesIte1.addComment("Leon", "worst book ever", 1);
        salesIte1.upvoteComment(0);
        assertEquals(1, salesIte1.findMostHelpfulComment().getVoteCount());
    }

    // Exercise 7.18
    @Test
    public void testDownVoteWorks(){
        SalesItem salesIte1 = new SalesItem("Datateknik för dataloger", 122);
        salesIte1.addComment("Leon", "Best book ever", 5);
        salesIte1.downvoteComment(0);
        assertEquals(-1, salesIte1.findMostHelpfulComment().getVoteCount());
    }
}



