public class Main {
    public static void main(String[] args) {
        LogfileCreator txt = new LogfileCreator();
        txt.createFile("weblog.txt", 90);
        LogAnalyzer l1 = new LogAnalyzer();
        l1.analyzeHourlyData();
        l1.printHourlyCounts();
        System.out.println(l1.numberOfAccesses());
        System.out.println(l1.busiestHour());
        System.out.println(l1.quietestHour());
        System.out.println(l1.busiestTwoHours());
    }
}
