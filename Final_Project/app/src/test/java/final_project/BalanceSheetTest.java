package final_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import final_project.*;

public class BalanceSheetTest {
    private AssetAndDebtManager assetManager;
    private BalanceSheet balanceSheet;

    @BeforeEach
    public void setUp() {
        assetManager = AssetAndDebtManager.getInstance();
        balanceSheet = new BalanceSheet(null, assetManager, 0.05);
    }

    @Test
    public void testCalculateTotalAssets() throws IOException {
        // Given
        assetManager.addBond(new Bond(1000, 0.05, 0.1, 2025, 0.05, "Bond 1"));
        assetManager.addStock(new Stock(2000, 0.05, 0.1, 50, 0.03, "Stock 1"));
        assetManager.addRealEstate(new RealEstate(3000, 0.05, 0.1, 200, "RealEstate 1"));

        // When
        double totalAssets = balanceSheet.calculateTotalAssets();

        // Then
        assertEquals(10452.47619047619, totalAssets, 0.01);
    }
}

