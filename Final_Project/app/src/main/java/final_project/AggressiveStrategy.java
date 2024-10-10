package final_project;

public class AggressiveStrategy implements Strategy {
    private double shortfall;

    public AggressiveStrategy(double shortfall) {
        this.shortfall = shortfall;
    }

    @Override
    public void execute() {
        System.out.println("Recommending aggressive strategy to increase return potential. Shortfall: " + shortfall);
    }
}
