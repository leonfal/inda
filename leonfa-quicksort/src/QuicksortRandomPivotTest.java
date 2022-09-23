public class QuicksortRandomPivotTest extends IntSorterTest{

  @Override
  protected IntSorter getIntSorter() {
    return new QuicksortRandomPivot();
  }
}
